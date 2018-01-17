/**
 * 
 */
package cs2321;

import java.util.Iterator;

import net.datastructures.BinaryTree;
import net.datastructures.Position;

/**
 * @author CS2321 Instructor
 *
 */
public class LinkedBinaryTree<E> implements BinaryTree<E> {
	// Inner node class
	protected class Node implements Position<E> {
		E element;
		Node parent;
		Node leftChild;
		Node rightChild;
		boolean isSentinel;
		int height;

		public Node(E element, Node parent) {
			this.element = element;
			this.leftChild = new Node();
			this.rightChild = new Node();
			this.parent = parent;
			this.isSentinel = false;
			this.height = 0;
		}

		/**
		 * Constructor for a sentinel node
		 */
		public Node() {
			this.element = null;
			this.leftChild = null;
			this.rightChild = null;
			this.parent = null;
			this.isSentinel = true;
			this.height = 0;
		}

		@Override
		public E getElement() throws IllegalStateException {
			// TODO Auto-generated method stub
			return element;
		}

		public void setElement(E element) {
			this.element = element;
		}

		public Node left() {
			return leftChild;
		}

		public void setLeft(Node leftChild) {
			this.leftChild = leftChild;
		}

		public Node right() {
			return rightChild;
		}

		public void setRight(Node rightChild) {
			this.rightChild = rightChild;
		}

		public Node parent() {
			return parent;
		}

		public void setParent(Node parent) {
			this.parent = parent;
		}

		public boolean isSentinel() {
			return isSentinel;
		}

		public boolean hasRight() {
			if (rightChild.isSentinel) {
				return false;
			}
			return true;
		}

		public boolean hasleft() {
			if (leftChild.isSentinel) {
				return false;
			}
			return true;
		}

		public int getHeight() {
			return height;
		}

		public void setHeight(int height) {
			this.height = height;
		}

	}

	protected Node root = null; // Root of the tree
	private int size = 0; // Number of nodes in the tree

	/*
	 * Casts a passed position to a node
	 */
	protected Node castToNode(Position<E> p) throws IllegalArgumentException {
		Node node = (Node) p;
		return node;
	}

	@Override
	@TimeComplexity("O(1)")
	public Position<E> root() {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Simply returning the root node is a single operation
		 */
		return root;
	}

	/*
	 * If the current root is not null, throw IllegalArgumentException
	 */
	@TimeComplexity("O(1)")
	public Position<E> addRoot(E e) throws IllegalArgumentException {
		// TODO Auto-generated meth
		/*
		 * TCJ: This just sets the root node to a given element which is a
		 * single operation
		 */
		if (root != null) {
			throw new IllegalArgumentException();
		}
		root = new Node(e, null);
		size = 1;
		return root;
	}

	@Override
	@TimeComplexity("O(1)")
	public Position<E> parent(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * TCJ: This just gets a nodes parent which is a direct reference and a
		 * single operation
		 */
		Node node = castToNode(p);
		if (node == root) {
			return null;
		}
		return node.parent();
	}

	@Override
	@TimeComplexity("O(1)")
	public Iterable<Position<E>> children(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * Adding the 2 children to a linked list are single operations
		 */
		Node node = castToNode(p);
		DoublyLinkedList<Position<E>> children = new DoublyLinkedList<Position<E>>();
		children.addLast(node.left());
		children.addLast(node.right());
		return children;
	}

	@Override
	@TimeComplexity("O(1)")
	public int numChildren(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Checks if children are sentinels are children and returns the
		 * associated value
		 */
		Node node = castToNode(p);
		Node left = node.left();
		Node right = node.right();
		if (left.isSentinel()) {

		}
		if (left.isSentinel() && right.isSentinel()) {
			return 0;
		}
		if ((left.isSentinel() && !right.isSentinel()) || (!left.isSentinel() && right.isSentinel())) {
			return 1;
		}
		return 2;
	}

	@Override
	@TimeComplexity("O(1)")
	public boolean isInternal(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Calls numChildren which is O(1)
		 */
		if (numChildren(p) > 0) {
			return true;
		}
		return false;
	}

	@Override
	@TimeComplexity("O(1)")
	public boolean isExternal(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Calls numChildren which is O(1)
		 */
		if (numChildren(p) > 0) {
			return false;
		}
		return true;
	}

	@Override
	@TimeComplexity("O(1)")
	public boolean isRoot(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Returns true or false if a node = root which is a single
		 * operation
		 */
		Node node = castToNode(p);
		if (node == root) {
			return true;
		}
		return false;
	}

	@TimeComplexity("O(1)")
	public int size() {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Returning size is a single operation
		 */
		return size;
	}

	@Override
	@TimeComplexity("O(1)")
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Checking if size is 0 is a single operation
		 */
		return size == 0;
	}

	@Override
	@TimeComplexity("O(1)")
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Creating the iterator is a simple return of an object
		 */
		Iterator<E> itr = new Iterator<E>() {

			private Iterator<Position<E>> iterator = positions().iterator();

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return iterator.hasNext();
			}

			@Override
			public E next() {
				// TODO Auto-generated method stub
				return iterator.next().getElement();
			}

		};
		return itr;
	}

	/*
	 * Adds all the nodes of the tree to a linked list
	 */
	@TimeComplexity("O(n)")
	private void postOrder(Node node, DoublyLinkedList<Position<E>> list) {
		/*
		 * TCJ: Has to visit every node in the tree
		 */
		if (node.hasleft()) {
			postOrder(node.left(), list);
		}
		if (node.hasRight()) {
			postOrder(node.right(), list);
		}
		list.addLast(node);
	}

	@Override
	@TimeComplexity("O(n)")
	public Iterable<Position<E>> positions() {
		// TODO Auto-generated method stub
		/*
		 * Calls postOrder which is an O(n) operation
		 */
		Iterable<Position<E>> nodes = new DoublyLinkedList<Position<E>>();
		postOrder(root, (DoublyLinkedList<Position<E>>) nodes);
		return nodes;
	}

	@Override
	@TimeComplexity("O(1)")
	public Position<E> left(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Simply returns the left child which is a single operation
		 */
		Node node = castToNode(p);
		if (node.hasleft()) {
			return node.left();
		}
		return null;
	}

	@Override
	@TimeComplexity("O(1)")
	public Position<E> right(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Simply returns the right child which is a single operation
		 */
		Node node = castToNode(p);
		if (node.hasRight()) {
			return node.right();
		}
		return null;
	}

	@Override
	@TimeComplexity("O(1)")
	public Position<E> sibling(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Simply follows a line of pointers which are all single
		 * operations
		 */
		Node node = castToNode(p);
		Node parent = node.parent();
		if (parent.hasleft() && parent.hasRight()) {
			if (parent.left() == node) {
				return parent.right();
			}
			if (parent.right() == node) {
				return parent.left();
			}
		}
		return null;
	}

	/*
	 * insert as the left child of P to contain the element e, return the newly
	 * inserted node
	 */
	@TimeComplexity("O(1)")
	public Position<E> insertLeft(Position<E> p, E e) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Creating a node object and setting pointers are all single
		 * operations
		 */
		Node node = castToNode(p);
		if (!node.hasleft()) {
			Node newNode = new Node(e, node);
			node.setLeft(newNode);
			size++;
			return newNode;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/*
	 * insert as the right child of P to contain the element e, return the newly
	 * inserted node
	 */
	@TimeComplexity("O(1)")
	public Position<E> insertRight(Position<E> p, E e) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * TCJ: creating a node object and setting pointers are all single
		 * operations
		 */
		Node node = castToNode(p);
		if (!node.hasRight()) {
			Node newNode = new Node(e, node);
			node.setRight(newNode);
			size++;
			return newNode;
		} else {
			throw new IllegalArgumentException();
		}
	}

	/* remove p if p has at most one child and return p's parent */
	@TimeComplexity("O(1)")
	public Position<E> remove(Position<E> p) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Removing a node object and updating pointers are all single
		 * operations
		 */
		Node node = castToNode(p);
		if (node.hasleft() && node.hasRight()) {
			throw new IllegalArgumentException();
		}

		Node subTree = new Node();
		if (node.hasleft()) {
			subTree = node.left();
		} else if (node.hasRight()) {
			subTree = node.right();
		}

		if (node == root) {
			root = subTree;
		} else {
			if (node.parent().left() == node) {
				node.parent().setLeft(subTree);
			} else {
				node.parent().setRight(subTree);
			}
		}

		if (subTree != null) {
			subTree.setParent(node.parent());
		}

		size--;

		return node;
	}

}