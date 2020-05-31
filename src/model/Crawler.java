package model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Crawler {

	public static String crawlString(String selectedCarModel) throws IOException {
		String carModel = selectedCarModel.replaceAll(" ", "/");
		URL gratka = new URL("https://gratka.pl/motoryzacja/osobowe/" + carModel + "/");
		BufferedReader in = new BufferedReader(new InputStreamReader(gratka.openStream()));
		String inputLine = "";
		StringBuilder stringBuilder = new StringBuilder();
		while ((inputLine = in.readLine()) != null) {
			stringBuilder.append(inputLine);
			stringBuilder.append(System.lineSeparator());
		}
		in.close();
		String content = stringBuilder.toString();
		return content;
	}

	public static LinkedList<Integer> generateMileageList(String content) {
		LinkedList<Integer> mileageList = new LinkedList<>();
//		String content = stringBuilder.toString();
		for (int i = 0; i < content.length(); i++) {
			i = content.indexOf(" <li>Przebieg: ", i);
			if (i < 0)
				break;
			String subString = content.substring(i);
			String mileageString = subString.split("</li>")[0];
			mileageString = mileageString.replaceAll(" <li>Przebieg: ", "");
			Integer mileageInteger = Integer.parseInt(mileageString);
			mileageList.add(mileageInteger);
		}
		return mileageList;
	}

	public static LinkedList<Integer> generateProductionYearList(String content) {
		LinkedList<Integer> productionYear = new LinkedList<>();
		for (int i = 0; i < content.length(); i++) {
			i = content.indexOf("<li>Rok produkcji: ", i);
			if (i < 0)
				break;
			String subString = content.substring(i);
			String productionYearString = subString.split("</li>")[0];
			productionYearString = productionYearString.replaceAll("<li>Rok produkcji: ", "");
			Integer productionYearInteger = Integer.parseInt(productionYearString);
			productionYear.add(productionYearInteger);
		}
		return productionYear;
	}

	public static LinkedList<Integer> generatePriceList(String content) {
		LinkedList<Integer> priceList = new LinkedList<>();
		for (int i = 0; i < content.length(); i++) {
			i = content.indexOf("<p class=\"teaser__price\">", i);
			if (i < 0)
				break;
			String subString = content.substring(i);
			String link = subString.split("<span> ")[0];
			link = link.replaceAll("<p class=\"teaser__price\">", "");
			link = link.replaceAll("\\s", "");
			Integer priceInteger = Integer.parseInt(link);
			priceList.add(priceInteger);
		}
		return priceList;
	}

	public static ArrayList<Car> generateCarList(String selectedCarModel) throws IOException {
		ExecutorService executorService = Executors.newFixedThreadPool(30);
		ArrayList<Car> carList = new ArrayList<Car>();
		for (int i = 0; i < generatePriceList(crawlString(selectedCarModel)).size(); i++) {
			int j = i;
			executorService.submit(() -> {
				try {
					carList.add(j,
							new Car(selectedCarModel, generateProductionYearList(crawlString(selectedCarModel)).get(j),
									generateMileageList(crawlString(selectedCarModel)).get(j),
									generatePriceList(crawlString(selectedCarModel)).get(j)));
				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
		System.out.println(carList);
		executorService.shutdown();
		return carList;
	}

}
