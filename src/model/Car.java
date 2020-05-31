package model;

public class Car {
	private String model;
	private int productionYear;
	private int mileAge;
	private int price;

	public Car(String model, int productionYear, int mileAge, int price) {
		this.model = model;
		this.productionYear = productionYear;
		this.mileAge = mileAge;
		this.price = price;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public int getProductionYear() {
		return productionYear;
	}

	public void setProductionYear(int productionYear) {
		this.productionYear = productionYear;
	}

	public int getMileAge() {
		return mileAge;
	}

	public void setMileAge(int mileAge) {
		this.mileAge = mileAge;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	}
