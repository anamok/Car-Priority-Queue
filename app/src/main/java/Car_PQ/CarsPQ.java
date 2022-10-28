/**
 * Implementation of CarsPQ specification interface 
 * for CS1501 Project 3
 * @author	Anastasia Mokhon
 */
package Car_PQ;
import java.io.*;
import java.util.*;

public class CarsPQ {
	private BST carTable = new BST();
	private DoublePQ pm;
	int indexTracker = 0;
	int targetIndex;
	String allvins = "";

	/**
	 * Constructor for CarsPQ
	 *
	 * @param filename File to construct the data structure
	 */
	public CarsPQ(String filename) {
		try {
			File file = new File(filename);
			Scanner sc = new Scanner(file);
			// since first line is not a car, disregard it
			String firstLine = sc.nextLine();
			while (sc.hasNextLine()) {
				// read in the car specification and record the
				// car in the data structure
				String newLine = sc.nextLine();
				String[] parameters = newLine.split(":");
				String vin = parameters[0];
				String make = parameters[1];
				String model = parameters[2];
				int price = Integer.parseInt(parameters[3]);
				int mileage = Integer.parseInt(parameters[4]);
				String color = parameters[5];
				Car newCar = new Car(vin, make, model, price, mileage, color);
				add(newCar);
				// records vins in a string for further use
				allvins = allvins + vin + ":";
			}
			sc.close();
		} catch (FileNotFoundException e) {
			System.out.println("file not found");
		}
	}

	/**
	 * Add a new Car to the data structure
	 * Should throw an `IllegalStateException` if there is already car with the
	 * same VIN in the datastructure.
	 *
	 * @param 	c Car to be added to the data structure
	 */
	public void add(Car c) throws IllegalStateException {
		// check if the car is already in the data structure
		// and throw exception if so, otherwise record the car
		BTNode<Integer, String, Car> target = carTable.getNode(c.getVIN());
		if (target != null) throw new IllegalStateException("This VIN is already on file.");
		carTable.put(c.getVIN(), c, indexTracker);

		// conditional to account for DoublePQ specification
		if (indexTracker == 0) {
			pm = new DoublePQ(indexTracker, c.getPrice(), c.getMileage(), c.getVIN());
		}
		else {
			pm.add(indexTracker, c.getPrice(), c.getMileage(), c.getVIN());
		}
		indexTracker++;
	}

	/**
	 * Retrieve a new Car from the data structure
	 * Should throw a `NoSuchElementException` if there is no car with the 
	 * specified VIN in the datastructure.
	 *
	 * @param 	vin VIN number of the car to be updated
	 */
	public Car get(String vin) throws NoSuchElementException {
		// checks if the car is already recorded in BST and return it
		// if the car is absent, throw exception
		BTNode<Integer, String, Car> temp = carTable.getNode(vin);
		if (temp == null) {
			throw new NoSuchElementException("No car on file matches this VIN.");
		} 
		else {
			targetIndex = temp.getIndex();
			return temp.getCar();
		}
	}

	/**
	 * Update the price attribute of a given car
	 * Should throw a `NoSuchElementException` if there is no car with the 
	 * specified VIN in the datastructure.
	 *
	 * @param 	vin VIN number of the car to be updated
	 * @param	newPrice The updated price value
	 */
	public void updatePrice(String vin, int newPrice) throws NoSuchElementException {
		Car temp = get(vin);
		if (temp == null) throw new NoSuchElementException("No car on file matches this VIN.");
		temp.setPrice(newPrice);
		carTable.replace(vin, temp, targetIndex);
		pm.updatePrice(targetIndex, newPrice);
	}

	/**
	 * Update the mileage attribute of a given car
	 * Should throw a `NoSuchElementException` if there is not car with the 
	 * specified VIN in the datastructure.
	 *
	 * @param 	vin VIN number of the car to be updated
	 * @param	newMileage The updated mileage value
	 */
	public void updateMileage(String vin, int newMileage) throws NoSuchElementException {
		Car temp = get(vin);
		if (temp == null) throw new NoSuchElementException("No car on file matches this VIN.");
		temp.setMileage(newMileage);
		carTable.replace(vin, temp, targetIndex);
		pm.updateMileage(targetIndex, newMileage);
	}

	/**
	 * Update the color attribute of a given car
	 * Should throw a `NoSuchElementException` if there is not car with the 
	 * specified VIN in the datastructure.
	 *
	 * @param 	vin VIN number of the car to be updated
	 * @param	newColor The updated color value
	 */
	public void updateColor(String vin, String newColor) throws NoSuchElementException {
		Car temp = get(vin);
		if (temp == null) throw new NoSuchElementException("No car on file matches this VIN.");
		temp.setColor(newColor);
		carTable.replace(vin, temp, targetIndex);
	}

	/**
	 * Remove a car from the data structure
	 * Should throw a `NoSuchElementException` if there is not car with the 
	 * specified VIN in the datastructure.
	 *
	 * @param 	vin VIN number of the car to be removed
	 */
	public void remove(String vin) throws NoSuchElementException {
		Car temp = get(vin);
		if (temp == null) throw new NoSuchElementException("No car on file matches this VIN.");
		// calls to two custom methods for removal
		carTable.delete(vin);
		pm.remove(targetIndex);
	}

	/**
	 * Get the lowest priced car (across all makes and models)
	 * Should return `null` if the data structure is empty
	 *
	 * @return	Car object representing the lowest priced car
	 */
	public Car getLowPrice() {
		// calls a custom DoublePQ method
		return get(pm.getLowestPrice());
	}

	/**
	 * Get the lowest priced car of a given make and model
	 * Should return `null` if the data structure is empty
	 *
	 * @param	make The specified make
	 * @param	model The specified model
	 * 
	 * @return	Car object representing the lowest priced car
	 */
	public Car getLowPrice(String make, String model) {
		String[] parameters = allvins.split(":");
		IndexMinPQ newPQ = new IndexMinPQ(indexTracker);
		Car newCar;
		int count = 0;

		// traversing the data structure and recording price of every car 
		// of a specific make and model in a Priority Queue
		for (String vin : parameters) {
			newCar = get(vin);
			if (newCar.getMake().equals(make) && newCar.getModel().equals(model)) {
				newPQ.insert(count, newCar.getPrice(), newCar.getVIN());
				count++;
			}
		}

		return get(newPQ.minVin());
	}

	/**
	 * Get the car with the lowest mileage (across all makes and models)
	 * Should return `null` if the data structure is empty
	 *
	 * @return	Car object representing the lowest mileage car
	 */
	public Car getLowMileage() {
		// calls a custom DoublePQ method
		return get(pm.getLowestMileage());
	}

	/**
	 * Get the car with the lowest mileage of a given make and model
	 * Should return `null` if the data structure is empty
	 *
	 * @param	make The specified make
	 * @param	model The specified model
	 *
	 * @return	Car object representing the lowest mileage car
	 */
	public Car getLowMileage(String make, String model) {
		String[] parameters = allvins.split(":");
		IndexMinPQ newPQ = new IndexMinPQ(indexTracker);
		Car newCar;
		int count = 0;

		// traversing the data structure and recording mileage of every car 
		// of a specific make and model in a Priority Queue
		for (String vin : parameters) {
			newCar = get(vin);
			if (newCar.getMake().equals(make) && newCar.getModel().equals(model)) {
				newPQ.insert(count, newCar.getMileage(), newCar.getVIN());
				count++;
			}
		}

		return get(newPQ.minVin());
	}
}