/**
*	Altered version of my own BST class
*	submission for CS1501 Project 1
*	@author Anastasia Mokhon
*/
package Car_PQ;
import java.io.*;
import java.util.*;

public class BST<Car> {

	public BTNode<Integer, String, Car> root;

	public BST() {
		// initiating an empty BST
		this.root = null;
	}

	public void put(String key, Car car, int index) {
		// calling the recursive method
		this.root = put_REC(root, key, car, index);
	}
	
	private BTNode<Integer, String, Car> put_REC(BTNode<Integer, String, Car> node, String key, Car car, int index) {
		// either root is null or we reached the leaf node
		if (node == null)
			return new BTNode<Integer, String, Car>(index, key, car);
		// if the key is less than current node's data,
		// we go to the left branch
		else if (key.compareTo(node.getKey()) < 0) {
			node.setLeft(this.put_REC(node.getLeft(), key, car, index));
		}
		// if we are here, the key is greater than current
		// node's data, so we go to the right branch
		else {
			node.setRight(this.put_REC(node.getRight(), key, car, index));
		}
		// returning the new node pointer
		return node;
	} 

	public BTNode<Integer, String, Car> getNode(String key) {
		// setting up a cursor to traverse the tree node by node
		BTNode<Integer, String, Car> cur = this.root;

		// looping through the BST and comparing the key to nodes
		// until the cursor points either at the node with the 
		// right key or null
		while (cur != null) {
			if (key.compareTo(cur.getKey()) == 0)
				return cur;
			else if (key.compareTo(cur.getKey()) < 0) {
				cur = cur.getLeft();
			}
			else {
				cur = cur.getRight();
			}
		}
		//if we are here, the key is not in the BST
		return null;
	}

	// custom method to support update methods in CarsPQs
	public void replace(String key, Car car, int index) {
		delete(key);
		put(key, car, index);
	}

	public void delete(String key) {
		// calling the recursive function
		this.root = this.delete_REC(root, key);
	}

	private BTNode<Integer, String, Car> delete_REC(BTNode<Integer, String, Car> node, String key) {
		// base case: empty tree
		if (node == null)
			return node;
		// searching for the node to be deleted recursively
		if (key.compareTo(node.getKey()) < 0) {
			node.setLeft(delete_REC(node.getLeft(), key));
		}
		else if (key.compareTo(node.getKey()) > 0) {
			node.setRight(delete_REC(node.getRight(), key));
		}
		else {
			// if we are here, the node either has 1 child
			// or no children
			if (node.getLeft() == null && node.getRight() == null){
				return null;
			}
			else if (node.getLeft() == null) {
				return node.getRight();
			}
			else if (node.getRight() == null) {
				return node.getLeft();
			}
			
			// if we are here, the node has 2 children, so
			// we need to find the largest successor in the 
			// node's left subtree
			BTNode<Integer, String, Car> cur = node.getLeft();
			while (cur.getRight() != null) {
				cur = cur.getRight();
			}
			// setting the node's key to the largest successor's value
			node.setKey(cur.getKey());

			// deleting the largest successor via a recursive function			
			node.setLeft(this.deleteLargest(node.getLeft()));
		}

		return node;
	}

	private BTNode<Integer, String, Car> deleteLargest(BTNode<Integer, String, Car> node) {
		// if there's no right child, this is the largest node
		if (node.getRight() == null) {
			return node.getLeft();
		}
		else {
			// traversing the BST further until we find
			// the largest node
			node.setRight(this.deleteLargest(node.getRight()));
			return node;
		}
	}
}