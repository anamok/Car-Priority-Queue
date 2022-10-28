/**
 * A driver for CS1501 Project 3
 * @author	Dr. Farnan
 */
package Car_PQ;

public class App {
	public static void main(String[] args) {
		CarsPQ cpq = new CarsPQ("build/resources/test/cars.txt");
		//Car c = new Car("5", "Ford", "Fiesta", 20, 200000, "White");
		//cpq.add(c);

		System.out.println("Testing add(): should throw IllegalStateException ");
		Car q = new Car("PUAF85WU5R6L6H1P9", "Ford", "Fiesta", 15020, 1000, "Red");
		cpq.add(q);
		 
		System.out.println("Testing get(): ");
		System.out.println("Should be Red: " + cpq.get("PUAF85WU5R6L6H1P9").getColor());
		System.out.println("Should be Green: " + cpq.get("X1U2PEJSC361L10MZ").getColor());
		System.out.println("Should be Yellow: " + cpq.get("16Z2DPEHSUK5KCMEH").getColor());
		System.out.println();

		System.out.println("Testing updateColor(): ");
			String vin = "1Y5NWYGLY5F4PX4HH";
			String newColor = "White";
			cpq.updateColor(vin, newColor);
		System.out.println("Should be White: " + cpq.get(vin).getColor());
		System.out.println();
		
		System.out.println("Testing updatePrice(): ");
			vin = "1Y5NWYGLY5F4PX4HH";
			int newPrice = 100000;
			cpq.updatePrice(vin, newPrice);
		System.out.println("Should be 100000: " + cpq.get(vin).getPrice());
		System.out.println();

		System.out.println("Testing updateMileage(): ");
			vin = "1Y5NWYGLY5F4PX4HH";
			int newMileage = 101010;
			cpq.updateMileage(vin, newMileage);
		System.out.println("Should be 101010: " + cpq.get(vin).getMileage());
		System.out.println();

		// SUCCESSFUL
		System.out.println("Testing remove(): should throw NoSuchElementException");
			vin = "X1U2PEJSC361L10MZ";
			cpq.get(vin);
			System.out.println("Found");
			//cpq.remove(vin);
			System.out.println("Removed");
		System.out.println();

			//cpq.get(vin);

		System.out.println("Testing getLowestPrice(): ");
			Car b = cpq.getLowPrice();
			boolean r = b.getVIN().equals("UTJYU67091B71NGZ3")
				|| b.getVIN().equals("RAMM7ZJBSFZ0HRTTN")
				|| b.getVIN().equals("SY719WJ4MMYVN0XNG");
		// System.out.println("Printing VIN of found car: " + b.getVIN());
		// System.out.println("Printing price of found car: " + b.getPrice());
		System.out.println("Should be true: " + r);
		System.out.println();

		System.out.println("Testing getLowestMileage(): ");
			b = cpq.getLowMileage();
			r = b.getVIN().equals("PUAF85WU5R6L6H1P9")
				|| b.getVIN().equals("GNX5TS04SM5V5EXP8");
		System.out.println("Should be true: " + r);
		System.out.println();

		System.out.println("Testing specific getLowestPrice(): ");
			Car a = cpq.getLowPrice("Ford", "Escort");
		System.out.println("Should be 8BSM1K0A6GXY2CHD7 exactly: " + a.getVIN());
		System.out.println();

		System.out.println("Testing specific getLowestMileage(): ");
			a = cpq.getLowMileage("Hyundai", "Elantra");
		System.out.println("Should be GNX5TS04SM5V5EXP8 exactly: " + a.getVIN());
		System.out.println();
	}
}
