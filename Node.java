package Assignment5;

import java.io.Serializable;

public class Node implements Serializable {

	
	private String item;
	private Node leftChild; // -1 = empty
	private Node rightChild; // -1 = empty
	
	public Node() {
		setItem(null);
		setLeftChild(null);
		setRightChild(null);
	}
	
	public Node (String x) {
		setItem(x);
		setLeftChild(null);
		setRightChild(null);
	}

	public String getItem() {
		return item;
	}

	public void setItem(String item) {
		this.item = item;
	}

	public Node getLeftChild() {
		return leftChild;
	}

	public void setLeftChild(Node leftChild) {
		this.leftChild = leftChild;
	}

	public Node getRightChild() {
		return rightChild;
	}

	public void setRightChild(Node rightChild) {
		this.rightChild = rightChild;
	}
	
	public boolean isLeaf() {
		
		return(leftChild == null && rightChild == null);
	}

}
