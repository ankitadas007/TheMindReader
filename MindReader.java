package Assignment5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 *Mindreader game
 *@author ankita
 *COSC 2336.001
 *Dec 06, 2017
 */

public class MindReader {
	public static Scanner userInput = new Scanner(System.in);

	/**
	 * Printing the tree with depth first search
	 * @param node starting node from where tree has to be printed
	 * @param tabCnt used to print nodes with tab space in it
	 */
	public static void printTree(Node node, int tabCnt) {
		String tabs = "";
		for (int i = tabCnt; i > 0; i--) {
			tabs += "\t ";
		}
		;
		String print = tabs + node.getItem();

		System.out.println(print);
		tabCnt++;
		if (node.getLeftChild() != null) {
			printTree(node.getLeftChild(), tabCnt);
		}
		if (node.getRightChild() != null) {
			printTree(node.getRightChild(), tabCnt);
		}
	};

	/**
	 * This method adds the distinguishing questions when answered no
	 * @param rightChild if an animal is not it is set as rightchild
	 * @return new distinguishing question
	 */
	public static Node addFeatureQuestion(Node rightChild) {
		System.out.println("What is your animal? ");
		String animalName = userInput.nextLine();
		System.out.println("Enter a yes/no question distinguishing " + rightChild.getItem() + " and " + animalName);
		String animalFeature = userInput.nextLine();
		Node feature = new Node(animalFeature);
		Node animal = new Node(animalName);
		feature.setLeftChild(animal);
		feature.setRightChild(rightChild);
		return feature;
	}

	public static void main(String args[]) throws IOException, ClassNotFoundException {
		
		// Initializing new tree with Dog as only animal
		String checkAnimal = null;
		File filePath = new File(args[0]);
		
		/*
		 * If input file exists read from it or create from default: Tree.dat
		 */
		Tree gameTree = new Tree("Dog");
		if (!filePath.createNewFile()) {
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath));
			gameTree = (Tree) ois.readObject();
			ois.close();
		} 
		
		boolean found = false;
		Node currentNode = gameTree.getRoot();

		/*
		 * Do while loop to run until the required animal is found
		 */
		do {
			if (currentNode.isLeaf()) {		// Root case: initial case with only dog
				System.out.println("Is your animal " + currentNode.getItem());
				checkAnimal = userInput.nextLine();
				if (checkAnimal.equalsIgnoreCase("no")) {
					Node feature = addFeatureQuestion(currentNode);
					gameTree.setRoot(feature);
					System.out.println("New Animal Added!! Thanks.");
					printTree(gameTree.getRoot(), 0);
					currentNode = gameTree.getRoot();
					System.out.println("Lets try again...");
				} else {
					System.out.println("Read mind correctly!");
					found = true;
				}
			} else {
				/*
				 * Show distinguishing question and take feedback from the user
				 */
				System.out.println(currentNode.getItem());
				checkAnimal = userInput.nextLine();
				if (checkAnimal.equalsIgnoreCase("yes")) {
					Node leftChild = currentNode.getLeftChild();
					if (leftChild.isLeaf()) {
						System.out.println("Is your animal " + leftChild.getItem());
						checkAnimal = userInput.nextLine();
						
						// if the leaf is the animal then print out otherwise add the animal
						if (checkAnimal.equalsIgnoreCase("no")) {
							Node feature = addFeatureQuestion(leftChild);
							currentNode.setLeftChild(feature);
							printTree(gameTree.getRoot(), 0);
							currentNode = gameTree.getRoot();
							System.out.println("Lets try again...");
						} else {
							System.out.println("Read mind correctly!");
							found = true;
						}
					} else {
						// if there is another question move to the new question
						currentNode = leftChild;
					}

				} else {
					Node rightChild = currentNode.getRightChild();
					if (rightChild.isLeaf()) {
						System.out.println("Is your animal " + rightChild.getItem());
						checkAnimal = userInput.nextLine();
						
						// if the leaf is the animal then print out otherwise add the animal
						if (checkAnimal.equalsIgnoreCase("no")) {
							Node feature = addFeatureQuestion(rightChild);
							currentNode.setRightChild(feature);
							printTree(gameTree.getRoot(), 0);

							currentNode = gameTree.getRoot();
							System.out.println("Lets try again...");
						} else {
							System.out.println("Read mind correctly!");
							found = true;
						}
					} else {
						
						// if there is another question move to the new question
						currentNode = rightChild;
					}
				}
			}
		} while (found == false); // The loop continues until the correct animal is found

		//Print tree to file
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath));
		oos.writeObject(gameTree);
		userInput.close();
		oos.close();

	}

}
