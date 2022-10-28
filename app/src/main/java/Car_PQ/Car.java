/**
 * Implementation of car interface for CS1501 Project 3
 * @author	Anastasia Mokhon
 */
package Car_PQ;
public class Car {
	private String vin;
	private String make;
	private String model;
	private int price;
	private int mileage;
	private String color;

	public Car(String vin, String make, String model, int price, int mileage, String color) {
		this.vin = vin;
		this.make = make;
		this.model = model;
		this.setPrice(price);
		this.setMileage(mileage);
		this.setColor(color);
	}

	public String getVIN() {
		return this.vin;
	}

	public String getMake() {
		return this.make;
	}

	public String getModel() {
		return this.model;
	}

	public int getPrice() {
		return this.price;
	}

	public int getMileage() {
		return this.mileage;
	}

	public String getColor() {
		return this.color;
	}

	public void setPrice(int newPrice) {
		this.price = newPrice;
	}

	public void setMileage(int newMileage) {
		this.mileage = newMileage;
	}

	public void setColor(String newColor) {
		this.color = newColor;
	}
}