package connection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Car;
import model.Crawler;

@WebServlet("/AddCar")
public class AddCar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AddCar() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setCharacterEncoding("UTF-8");
	}

	public static void executeInsertCarDetails(ArrayList<Car> carList) {
		System.out.println(carList.size());
		System.out.println(carList.get(2).getProductionYear());
		try {
			Connection conn = ConnectionSingleton.getConnection();
			Statement st = conn.createStatement();
			for(int i=0;i<carList.size();i++) {
			st.executeUpdate(prepareInsertStatement(carList, i));

			}
			System.out.println("ok");
			conn.close();
		} catch (Exception exc) {
			exc.printStackTrace();
			System.out.println("cos nie pyklo");
		}
	}

	private static String prepareInsertStatement(ArrayList<Car> carList, int i) {
		String zapytanie = "insert into samochody (model, productionYear, mileAge, price) values('" + carList.get(i).getModel() + "', '" 
				+ carList.get(i).getProductionYear() + "', '" 
				+ carList.get(i).getMileAge() + "', '" 
				+ carList.get(i).getPrice() + "'); ";
		return zapytanie;
	}
}
