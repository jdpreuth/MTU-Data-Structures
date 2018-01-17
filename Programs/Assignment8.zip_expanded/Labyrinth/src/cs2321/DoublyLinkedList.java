/**
 * @author 	Jon Preuth
 * @date	1/24/17
 * @param <E> - formal type
 */
package cs2321;

import java.util.Iterator;

import net.datastructures.Position;
import net.datastructures.PositionalList;

public class DoublyLinkedList<E> implements PositionalList<E> {

	// Nested node class
	private static class Node<E> implements Position {
		private Node<E> prev; // The pointer to the previous node
		private E element; // The element stored in the node
		private Node<E> next; // The pointer to the next node

		/**
		 * Constructor to declare a new node. Declares prev, element, and next
		 * to null
		 */
		public Node() {
			this.element = null;
			this.prev = null;
			this.next = null;
		}

		/**
		 * Constructor to declare a new node with given parameters
		 * 
		 * @param prev
		 *            the previous node to be pointed to
		 * @param element
		 *            the element to be stored in the node
		 * @param next
		 *            the next node to be pointed to
		 */
		public Node(Node<E> prev, E element, Node<E> next) {
			this.element = element;
			this.prev = prev;
			this.next = next;
		}

		/**
		 * Returns the node being pointed back to
		 * 
		 * @return the node being pointed back to
		 */
		@TimeComplexity("O(1)")
		public Node<E> getPrev() {
			/*
			 * TCJ: Returning a value is a cost 1 operation so this is worst
			 * case O(1)
			 */
			return this.prev;
		}

		/**
		 * Returns the element stored in the node
		 * 
		 * @return the element stroed in the node
		 */
		@TimeComplexity("O(1)")
		public E getElement() {
			/*
			 * TCJ: Returnign a value is a cost 1 operation so this is worst
			 * case O(1)
			 */
			return this.element;
		}

		/**
		 * Returns the next node being pointed to
		 * 
		 * @return the next node being pointed to
		 */
		@TimeComplexity("O(1)")
		public Node<E> getNext() {
			/*
			 * TCJ: Returning a value is a cost 1 operation so this is worse
			 * case O(1)
			 */
			return this.next;
		}

		/**
		 * Sets the pointer to the previous node
		 * 
		 * @param prev
		 *            the node to be pointed to
		 */
		@TimeComplexity("O(1)")
		public void setPrev(Node<E> prev) {
			/*
			 * TCJ: Setting the pointer is a cost 1 operation so this is worst
			 * case O(1)
			 */
			this.prev = prev;
		}

		/**
		 * Sets the element stored in the node
		 * 
		 * @param element
		 *            the element to be stored
		 */
		@TimeComplexity("O(1)")
		public void setElement(E element) {
			/*
			 * TCJ: Setting a variable is a cost 1 operation so this is worst
			 * case O(1)
			 */
			this.element = element;
		}

		/**
		 * Sets the pointer to the next node
		 * 
		 * @param next
		 *            the node to be pointed to
		 */
		@TimeComplexity("O(1)")
		public void setNext(Node<E> next) {
			/*
			 * TCJ: Setting a pointer is a cost 1 operation so this is worst
			 * case O(1)
			 */
			this.next = next;
		}
	}

	private Node<E> head; // Private node that keeps track of the head of the
							// list
	private Node<E> tail; // Private node that keeps track of the tail of the
							// list
	private int size = 0; // Private integer to keep track of the number of
							// elements in the list

	/**
	 * Takes a node in and returns the position of the node in the list
	 * 
	 * @param node
	 *            a node in the list
	 * @return position of the node (or null if the node is the head or tail)
	 */
	@TimeComplexity("O(1)")
	private Position<E> position(Node<E> node) {
		/*
		 * TCJ: Returning a value is a cost 1 operation so this is worst case
		 * O(1)
		 */
		if (node == head || node == tail) {
			return null;
		}
		return node;
	}

	/**
	 * 
	 * @param p
	 *            a position of a node in the list
	 * @return the node located at the position
	 * @throws IllegalArgumentException
	 *             if p is not a valid position for this list
	 */
	@TimeComplexity("O(1)")
	private Node<E> validate(Position<E> p) throws IllegalArgumentException {
		/*
		 * TCJ: There are no loops and all the operations are cost 1 so this is
		 * worst case O(1)
		 */
		if (!(p instanceof Node)) {
			throw new IllegalArgumentException();
		}
		Node<E> node = (Node<E>) p;
		if (node.getNext() == null) {
			throw new IllegalArgumentException();
		}
		return node;
	}

	/**
	 * Constructor for a Doubly Linked List: Initializes the head and tail nodes
	 * as well as set the heads next reference to the tail
	 */
	@TimeComplexity("O(1)")
	public DoublyLinkedList() {
		/*
		 * TCJ: There are no loops and all the operations are cost 1 so this is
		 * worst case O(1)
		 */
		// TODO Auto-generated constructor stub
		head = new Node();
		tail = new Node(head, null, null);
		head.setNext(tail);
	}

	/**
	 * Returns the number of elements in the list.
	 * 
	 * @return number of elements in the list
	 */
	@TimeComplexity("O(1)")
	public int size() {
		/*
		 * TCJ: There are no loops and all the operations are cost 1 so this is
		 * worst case O(1)
		 */
		// TODO Auto-generated method stub
		return size;
	}

	/**
	 * Tests whether the list is empty.
	 * 
	 * @return true if the list is empty, false otherwise
	 */
	@TimeComplexity("O(1)")
	public boolean isEmpty() {
		/*
		 * TCJ: There are no loops and all the operations are cost 1 so this is
		 * worst case O(1)
		 */
		// TODO Auto-generated method stub
		return size == 0;
	}

	/**
	 * Returns the first Position in the list.
	 *
	 * @return the first Position in the list (or null, if empty)
	 */
	@TimeComplexity("O(1)")
	public Position<E> first() {
		/*
		 * TCJ: There are no loops and all the operations are cost 1 so this is
		 * worst case O(1)
		 */
		// TODO Auto-generated method stub
		return position(head.getNext());
	}

	/**
	 * Returns the last Position in the list.
	 *
	 * @return the last Position in the list (or null, if empty)
	 */
	@TimeComplexity("O(1)")
	public Position<E> last() {
		/*
		 * TCJ: There are no loops and all the operations are cost 1 so this is
		 * worst case O(1)
		 */
		// TODO Auto-generated method stub
		return position(tail.getPrev());
	}

	/**
	 * Returns the Position immediately before Position p.
	 * 
	 * @param p
	 *            a Position of the list
	 * @return the Position of the preceding element (or null, if p is first)
	 * @throws IllegalArgumentException
	 *             if p is not a valid position for this list
	 */
	@TimeComplexity("O(1)")
	public Position<E> before(Position<E> p) throws IllegalArgumentException {
		/*
		 * TCJ: There are no loops and all the operations are cost 1 so this is
		 * worst case O(1)
		 */
		// TODO Auto-generated method stub
		Node<E> node = validate(p);
		return position(node.getPrev());
	}

	/**
	 * Returns the Position immediately after Position p.
	 * 
	 * @param p
	 *            a Position of the list
	 * @return the Position of the following element (or null, if p is last)
	 * @throws IllegalArgumentException
	 *             if p is not a valid position for this list
	 */
	@TimeComplexity("O(1)")
	public Position<E> after(Position<E> p) throws IllegalArgumentException {
		/*
		 * TCJ: There are no loops and all the operations are cost 1 so this is
		 * worst case O(1)
		 */
		// TODO Auto-generated method stub
		Node<E> node = validate(p);
		return position(node.getNext());
	}

	/**
	 * Takes in an element and places it between two given nodes
	 * 
	 * @param e
	 *            the element to be placed in a new node
	 * @param pred
	 *            the node that goes before the new node
	 * @param succ
	 *            the node that goes after the new node
	 * @return the position of the new node
	 */
	@TimeComplexity("O(1)")
	private Position<E> addBetween(E e, Node<E> pred, Node<E> succ) {
		/*
		 * TCJ: There are no loops and all the operations are cost 1 so this is
		 * worst case O(1)
		 */
		Node<E> node = new Node<E>(pred, e, succ);
		pred.setNext(node);
		succ.setPrev(node);
		size++;
		return node;
	}

	/**
	 * Inserts an element at the front of the list and returns the position of
	 * the new node
	 *
	 * @param e
	 *            the new element
	 * @return the Position representing the location of the new element
	 */
	@TimeComplexity("O(1)")
	public Position<E> addFirst(E e) {
		/*
		 * TCJ: There are no loops and all the operations are cost 1 so this is
		 * worst case O(1)
		 */
		// TODO Auto-generated method stub
		return addBetween(e, head, head.getNext());
	}

	/**
	 * Inserts an element at the back of the list and returns the position of
	 * the new node
	 *
	 * @param e
	 *            the new element
	 * @return the Position representing the location of the new element
	 */
	@TimeComplexity("O(1)")
	public Position<E> addLast(E e) {
		/*
		 * TCJ: There are no loops and all the operations are cost 1 so this is
		 * worst case O(1)
		 */
		// TODO Auto-generated method stub
		return addBetween(e, tail.getPrev(), tail);
	}

	/**
	 * Inserts an element immediately before the given Position.
	 *
	 * @param p
	 *            the Position before which the insertion takes place
	 * @param e
	 *            the new element
	 * @return the Position representing the location of the new element
	 * @throws IllegalArgumentException
	 *             if p is not a valid position for this list
	 */
	@TimeComplexity("O(1)")
	public Position<E> addBefore(Position<E> p, E e) throws IllegalArgumentException {
		/*
		 * TCJ: There are no loops and all the operations are cost 1 so this is
		 * worst case O(1)
		 */
		// TODO Auto-generated method stub
		Node node = validate(p);
		return addBetween(e, node.getPrev(), node);
	}

	/**
	 * Inserts an element immediately after the given Position.
	 *
	 * @param p
	 *            the Position after which the insertion takes place
	 * @param e
	 *            the new element
	 * @return the Position representing the location of the new element
	 * @throws IllegalArgumentException
	 *             if p is not a valid position for this list
	 */
	@TimeComplexity("O(1)")
	public Position<E> addAfter(Position<E> p, E e) throws IllegalArgumentException {
		/*
		 * TCJ: There are no loops and all the operations are cost 1 so this is
		 * worst case O(1)
		 */
		// TODO Auto-generated method stub
		Node node = validate(p);
		return addBetween(e, node, node.getNext());
	}

	/**
	 * Replaces the element stored at the given Position and returns the
	 * replaced element.
	 *
	 * @param p
	 *            the Position of the element to be replaced
	 * @param e
	 *            the new element
	 * @return the replaced element
	 * @throws IllegalArgumentException
	 *             if p is not a valid position for this list
	 */
	@TimeComplexity("O(1)")
	public E set(Position<E> p, E e) throws IllegalArgumentException {
		/*
		 * TCJ: There are no loops and all the operations are cost 1 so this is
		 * worst case O(1)
		 */
		// TODO Auto-generated method stub
		Node<E> node = validate(p);
		E value = node.getElement();
		node.setElement(e);
		return value;
	}

	/**
	 * Removes the element stored at the given Position and returns it. The
	 * given position is invalidated as a result.
	 *
	 * @param p
	 *            the Position of the element to be removed
	 * @return the removed element
	 * @throws IllegalArgumentException
	 *             if p is not a valid position for this list
	 */
	@TimeComplexity("O(1)")
	public E remove(Position<E> p) throws IllegalArgumentException {
		/*
		 * TCJ: There are no loops and all the operations are cost 1 so this is
		 * worst case O(1)
		 */
		// TODO Auto-generated method stub
		Node<E> node = validate(p);
		E value = node.getElement();
		Node<E> prev = node.getPrev();
		Node<E> next = node.getNext();
		prev.setNext(next);
		next.setPrev(prev);
		size--;
		node.setElement(null);
		node.setNext(null);
		node.setPrev(null);
		return value;
	}

	// Nested class that defines an iterator to determine the position of each
	// node
	private class PositionIterator implements Iterator<Position<E>> {
		private Position<E> cursor = first(); // The position of the first node
												// in the list
		private Position<E> current = null; // The position of the current node
											// returned by the iterator

		/**
		 * Determines if there is another node in the list
		 * 
		 * @return true if there is another node, false otherwise
		 */
		@TimeComplexity("O(1)")
		public boolean hasNext() {
			/*
			 * TCJ: There are no loops and all the operations are cost 1 so this
			 * is worst case O(1)
			 */
			return cursor != null;
		}

		/**
		 * Returns the next position in the list and shifts the cursor and
		 * current positions forward
		 * 
		 * @return the next position in the list
		 */
		@TimeComplexity("O(1)")
		public Position<E> next() {
			/*
			 * TCJ: There are no loops and all the operations are cost 1 so this
			 * is worst case O(1)
			 */
			current = cursor;
			cursor = after(cursor);
			return current;
		}

	}

	// Nested class that defines an iterable using the Position Iterator
	private class PositionIterable implements Iterable<Position<E>> {
		/**
		 * Returns a PositionIterator
		 * 
		 * @return a PositionIterator
		 */
		@TimeComplexity("O(1)")
		public Iterator<Position<E>> iterator() {
			/*
			 * TCJ: There are no loops and all the operations are cost 1 so this
			 * is worst case O(1)
			 */
			return new PositionIterator();
		}
	}

	// Nested class that modifies the PositionIterator to return the elements at
	// each position
	private class ElementIterator implements Iterator<E> {
		Iterator<Position<E>> posItr = new PositionIterator(); // a posItr to
																// get each node

		/**
		 * Determines if there is another element in the list
		 * 
		 * @return true if there is another element in the last or false
		 *         otherwise
		 */
		@TimeComplexity("O(1)")
		public boolean hasNext() {
			/*
			 * TCJ: There are no loops and all the operations are cost 1 so this
			 * is worst case O(1)
			 */
			// TODO Auto-generated method stub
			return posItr.hasNext();
		}

		/**
		 * Finds and returns the next element in the list
		 * 
		 * @return the next element in the list
		 */
		@TimeComplexity("O(1)")
		public E next() {
			/*
			 * TCJ: There are no loops and all the operations are cost 1 so this
			 * is worst case O(1)
			 */
			// TODO Auto-generated method stub
			return posItr.next().getElement();
		}

	}

	/**
	 * Returns an iterator of the elements stored in the list.
	 * 
	 * @return iterator of the list's elements
	 */
	@TimeComplexity("O(1)")
	public Iterator<E> iterator() {
		/*
		 * TCJ: There are no loops and all the operations are cost 1 so this is
		 * worst case O(1)
		 */
		// TODO Auto-generated method stub
		return new ElementIterator();
	}

	/**
	 * Returns the positions of the list in iterable form from first to last.
	 * 
	 * @return iterable collection of the list's positions
	 */
	@TimeComplexity("O(1)")
	public Iterable<Position<E>> positions() {
		/*
		 * TCJ: There are no loops and all the operations are cost 1 so this is
		 * worst case O(1)
		 */
		// TODO Auto-generated method stub
		return new PositionIterable();
	}

	/**
	 * Removes the first element stored in the list and returns the replaced
	 * element
	 * 
	 * @return the removed element
	 * @throws IllegalArgumentException
	 */
	@TimeComplexity("O(1)")
	public E removeFirst() throws IllegalArgumentException {
		/*
		 * TCJ: There are no loops and all the operations are cost 1 so this is
		 * worst case O(1)
		 */
		// TODO Auto-generated method stub
		E value = remove(first());
		return value;
	}

	/**
	 * Removes the last element stored in teh list and returns the replaced
	 * element
	 * 
	 * @return the removed element
	 * @throws IllegalArgumentException
	 */
	@TimeComplexity("O(1)")
	public E removeLast() throws IllegalArgumentException {
		/*
		 * TCJ: There are no loops and all the operations are cost 1 so this is
		 * worst case O(1)
		 */
		// TODO Auto-generated method stub
		E value = remove(last());
		return value;
	}

}
