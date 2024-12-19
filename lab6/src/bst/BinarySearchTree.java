package bst;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;

public class BinarySearchTree<E> {
	BinaryNode<E> root; // Används också i BSTVisaulizer
	int size; // Används också i BSTVisaulizer
	private Comparator<E> comparator;

	/**
	 * Constructs an empty binary search tree.
	 */
	@SuppressWarnings("unchecked")
	public BinarySearchTree() {
		size = 0;
		root = null;
		this.comparator = (e1, e2) -> ((Comparable<E>) e1).compareTo(e2);
	}

	/**
	 * Constructs an empty binary search tree, sorted according to the specified
	 * comparator.
	 */
	public BinarySearchTree(Comparator<E> comparator) {
		size = 0;
		root = null;
		this.comparator = comparator;
	}

	public static void main(String[] args) {
		BSTVisualizer intFrame = new BSTVisualizer("Binary search tree", 600, 600);
		BSTVisualizer randIntFrame = new BSTVisualizer("Binary search tree", 600, 600);
		BSTVisualizer charFrame = new BSTVisualizer("Binary search tree", 600, 600);
		String alpha = "abcdefghijklmnopqrstuvwxyz";
		BinarySearchTree<Integer> intTree = new BinarySearchTree<Integer>((i1, i2) -> i2.compareTo(i1));
		BinarySearchTree<Integer> randIntTree = new BinarySearchTree<Integer>();
		BinarySearchTree<Character> charTree = new BinarySearchTree<Character>();
		Random rand = new Random();
		for (int i = 0; i <= 50; i++) {
			randIntTree.add(rand.nextInt(50));
			intTree.add(i);
		}

		for (int j = 0; j < alpha.length(); j++) {
			charTree.add(alpha.charAt(j));
		}

		intTree.rebuild();
		// intTree.printTree();
		intFrame.drawTree(intTree);
		randIntTree.rebuild();
		// randIntTree.printTree();
		randIntFrame.drawTree(randIntTree);
		charTree.rebuild();
		// charTree.printTree();
		charFrame.drawTree(charTree);
	}

	/**
	 * Inserts the specified element in the tree if no duplicate exists.
	 * 
	 * @param x element to be inserted
	 * @return true if the the element was inserted
	 */
	public boolean add(E x) {
		return add(root, x);
	}

	private boolean add(BinaryNode<E> n, E x) {
		if (root == null) {
			root = new BinaryNode<E>(x);
			size++;
			return true;
		}
		if (comparator.compare(x, n.element) == 0) {
			return false;
		} else if (comparator.compare(x, n.element) > 0) {
			if (n.right != null) {
				return add(n.right, x);
			} else {
				n.right = new BinaryNode<E>(x);
				size++;
				return true;
			}
		} else {
			if (n.left != null) {
				return add(n.left, x);
			} else {
				n.left = new BinaryNode<E>(x);
				size++;
				return true;
			}
		}
	}

	/**
	 * Computes the height of tree.
	 * 
	 * @return the height of the tree
	 */
	public int height() {
		return height(root);
	}

	private int height(BinaryNode<E> n) {

		if (n == null) {
			return 0;
		}
		return 1 + Math.max(height(n.left), height(n.right));
	}

	/**
	 * Returns the number of elements in this tree.
	 * 
	 * @return the number of elements in this tree
	 */
	public int size() {
		return size;
	}

	/**
	 * Removes all of the elements from this list.
	 */
	public void clear() {
		root = null;
		size = 0;
	}

	/**
	 * Print tree contents in inorder.
	 */
	public void printTree() {
		printTree(root);
	}

	private void printTree(BinaryNode<E> n) {
		if (n == null) {
			return;
		}
		printTree(n.left);
		System.out.println(n.element);
		printTree(n.right);
	}

	/**
	 * Builds a complete tree from the elements in the tree.
	 */
	public void rebuild() {
		ArrayList<E> sortedTree = new ArrayList<E>();
		toArray(root, sortedTree);
		root = buildTree(sortedTree, 0, sortedTree.size() - 1);

	}

	/*
	 * Adds all elements from the tree rooted at n in inorder to the list sorted.
	 */
	private void toArray(BinaryNode<E> n, ArrayList<E> sorted) {
		if (n == null) {
			return;
		}
		toArray(n.left, sorted);
		sorted.add(n.element);
		toArray(n.right, sorted);
	}

	/*
	 * Builds a complete tree from the elements from position first to last in the
	 * list sorted. Elements in the list a are assumed to be in ascending order.
	 * Returns the root of tree.
	 */
	private BinaryNode<E> buildTree(ArrayList<E> sorted, int first, int last) {
		if (first > last) {
			return null;
		} else {
			int mid = (first + last + 1) / 2;
			BinaryNode<E> node = new BinaryNode<E>(sorted.get(mid));
			node.left = buildTree(sorted, first, mid - 1);
			node.right = buildTree(sorted, mid + 1, last);
			return node;
		}
	}

	static class BinaryNode<E> {
		E element;
		BinaryNode<E> left;
		BinaryNode<E> right;

		private BinaryNode(E element) {
			this.element = element;
		}
	}

}
