package com.bobocode.cs;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;

public class RecursiveBinarySearchTree<T extends Comparable<T>> implements BinarySearchTree<T> {

	private Node<T> root;
	private int size = 0;

	public static <T extends Comparable<T>> RecursiveBinarySearchTree<T> of(T... elements) {
		RecursiveBinarySearchTree<T> tree = new RecursiveBinarySearchTree();
		Arrays.stream(elements).forEach(tree::insert);
		return tree;
	}

	@Override
	public boolean insert(T element) {
		Objects.requireNonNull(element);
		if (root == null) {
			root = new Node<>(element);
			size++;
			return true;
		}

		boolean isInserted = insertIntoSubTree(root, element);
		if (isInserted) {
			size++;
		}
		return isInserted;
	}

	private boolean insertIntoSubTree(Node<T> node, T element) {
		if (node.element.compareTo(element) > 0) {
			return insertIntoLeftSubtree(node, element);
		} else if (node.element.compareTo(element) < 0) {
			return insertIntoRightSubtree(node, element);
		} else {
			return false;
		}
	}

	private boolean insertIntoRightSubtree(Node<T> root, T element) {

		if (root.right == null) {
			root.right = new Node<>(element);
			return true;
		} else {
			return insertIntoSubTree(root.right, element);
		}
	}

	private boolean insertIntoLeftSubtree(Node<T> root, T element) {

		if (root.left == null) {
			root.left = Node.valueOf(element);
			return true;
		} else {
			return insertIntoSubTree(root.left, element);
		}
	}

	@Override
	public boolean contains(T element) {
		Objects.requireNonNull(element);
		return containsInSubtree(root, element) != null;
	}

	private Node<T> containsInSubtree(Node<T> node, T element) {

		if (node == null) {
			return null;
		}
		if (node.element.compareTo(element) > 0) {
			return containsInSubtree(node.left, element);
		} else if (node.element.compareTo(element) < 0) {
			return containsInSubtree(node.right, element);
		} else {
			return node;
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public int depth() {
		return root != null ? depth(root) - 1 : 0;
	}

	private int depth(Node<T> node) {
		if (node == null) {
			return 0;
		} else {
			return 1 + Math.max(depth(node.left), depth(node.right));
		}
	}

	@Override
	public void inOrderTraversal(Consumer<T> consumer) {
		inOrderTraversal(root, consumer);
	}

	private void inOrderTraversal(Node<T> node, Consumer<T> consumer) {
		if (node != null) {
			inOrderTraversal(node.left, consumer);
			consumer.accept(node.element);
			inOrderTraversal(node.right, consumer);
		}
	}

	private static class Node<T> {

		T element;
		Node<T> left;
		Node<T> right;

		private Node(T element) {
			this.element = element;
		}

		public static <T> Node<T> valueOf(T element) {
			return new Node<>(element);
		}
	}
}
