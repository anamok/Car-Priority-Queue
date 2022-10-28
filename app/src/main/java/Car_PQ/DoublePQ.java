/**
 * Support class for CarsPQ for CS1501 Project 3
 * Stores two priority queues, one for price, another for mileage
 * @author	Anastasia Mokhon
 */
package Car_PQ;

public class DoublePQ {
	public IndexMinPQ<Integer> priceQueue = new IndexMinPQ<Integer>(100);
	public IndexMinPQ<Integer> mileageQueue = new IndexMinPQ<Integer>(100);

	public DoublePQ(int index, int price, int mileage, String vin) {
		priceQueue.insert(index, price, vin);
		mileageQueue.insert(index, mileage, vin);
	}

	public void add(int index, int price, int mileage, String vin) {
		priceQueue.insert(index, price, vin);
		mileageQueue.insert(index, mileage, vin);
	}

	public void updatePrice(int index, int newPrice) {
		priceQueue.changeKey(index, newPrice);
	}

	public void updateMileage(int index, int newMileage) {
		mileageQueue.changeKey(index, newMileage);
	}

	public void remove(int index) {
		priceQueue.delete(index);
		mileageQueue.delete(index);
	}

	public String getLowestPrice() {
		return priceQueue.minVin();
	}

	public String getLowestMileage() {
		return mileageQueue.minVin();
	}
}