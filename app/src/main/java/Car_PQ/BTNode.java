/**
*	Altered version of my BTNode
*	submission for CS1501 Project 1
*	@author Anastasia Mokhon
*/
package Car_PQ;
public class BTNode<Integer, String, Car> {
	
	/**
	 * Key held by this node
	 */
	private String key;

	/**
	* Car held by this node
	*/
	private Car car;

	/**
	* Index held by this node
	*/
	private int index;

	/**
	 * Left child reference
	 */
	private BTNode<Integer, String, Car> left;

	/**
	 * Right child reference
	 */
	private BTNode<Integer, String, Car> right;

	/**
	 * Constructor that accepts the key to be held by the new node
	 */
	public BTNode(int i, String k, Car c) {
		key = k;
		car = c;
		index = i;
	}

	/**
	 * Getter for the index
	 * 
	 * @return	Reference to the index
	 */
	public int getIndex() {
		return index;
	}
	
	/**
	 * Setter for the index
	 *
	 * @param	i value to set as index
	 */
	public void setIndex(int i) {
		index = i;
	}

	/**
	 * Getter for the car
	 * 
	 * @return	Reference to the car
	 */
	public Car getCar() {
		return car;
	}

	/**
	 * Setter for the car
	 *
	 * @param	c value to set as car
	 */
	public void setCar(Car c) {
		car = c;
	}

	/**
	 * Getter for the key
	 * 
	 * @return	Reference to the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Setter for the key
	 *
	 * @param	k value to set as key
	 */
	public void setKey(String k) {
		key = k;
	}

	/**
	 * Getter for the left child
	 *
	 * @return	Reference to the left child
	 */
	public BTNode<Integer, String, Car> getLeft() {
		return left;
	}

	/**
	 * Getter for the right child
	 *
	 * @return	Reference to the left child
	 */
	public BTNode<Integer, String, Car> getRight() {
		return right;
	}

	/**
	 * Setter for the left child
	 *
	 * @param	l BTNode to set as the left child
	 */
	public void setLeft(BTNode<Integer, String, Car> l) {
		left = l;
	}

	/**
	 * Setter for the right child
	 *
	 * @param	r BTNode to set as the right child
	 */
	public void setRight(BTNode<Integer, String, Car> r) {
		right = r;
	}
}
