package Assignment5;

import java.io.Serializable;

public class Tree implements Serializable {

	public final int MAX_NODES = 100;
	private Node root; 
	private Node free;
	
	public Tree() {
		setRoot(null);
		setFree(null);
	}
	
	public Tree(String name) {
		Node root = new Node(name);
		setRoot(root);
		setFree(root.getLeftChild());
		
	}

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	public Node getFree() {
		return free;
	}

	public void setFree(Node free) {
		this.free = free;
	}

}
