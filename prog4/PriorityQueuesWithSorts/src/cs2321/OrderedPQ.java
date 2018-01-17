package cs2321;

import java.util.Comparator;

import net.datastructures.Entry;
import net.datastructures.Position;
import net.datastructures.PriorityQueue;

/**
 * A PriorityQueue based on an ordered Doubly Linked List.
 * 
 * Course: CS2321 Section ALL Assignment: #3
 * 
 * @author Jon Preuth
 */

public class OrderedPQ<K, V> implements PriorityQueue<K, V> {

	// Nested Class for each entry in the PQ
	private class PQEntry<K, V> implements Entry<K, V> {

		private K key; // The Key of the entry
		private V value; // The value stored in the entry

		/**
		 * Constructor for a PQEntry
		 * 
		 * @param key
		 *            the key of the entry
		 * @param value
		 *            the value to store in the entry
		 */
		PQEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		/**
		 * Returns the key stored in this entry.
		 * 
		 * @return the entry's key
		 */
		public K getKey() {
			// TODO Auto-generated method stub
			return key;
		}

		/**
		 * Returns the value stored in this entry.
		 * 
		 * @return the entry's value
		 */
		public V getValue() {
			// TODO Auto-generated method stub
			return value;
		}

	}

	private DoublyLinkedList<Entry<K, V>> queue = new DoublyLinkedList<Entry<K, V>>(); // List
																						// to
																						// store
																						// PQ
																						// entries
	private int size = 0; // Number of elements in the PQ
	private Comparator<K> c; // The comparator used to sort the PQ

	/**
	 * Default Constructor for the PQ. Sets the comparator to the default
	 * comparator
	 */
	public OrderedPQ() {
		// TODO implement this method
		c = new DefaultComparator<K>();
	}

	/**
	 * Constructor for a PQ with a given comparator
	 * 
	 * @param c
	 *            the comparator to be used to sort the PQ
	 */
	public OrderedPQ(Comparator<K> c) {
		// TODO implement this method
		this.c = c;
	}

	/**
	 * Returns the number of items in the priority queue.
	 * 
	 * @return number of items
	 */
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	/**
	 * Tests whether the priority queue is empty.
	 * 
	 * @return true if the priority queue is empty, false otherwise
	 */
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	/**
	 * Inserts a key-value pair and returns the entry created.
	 * 
	 * @param key
	 *            the key of the new entry
	 * @param value
	 *            the associated value of the new entry
	 * @return the entry storing the new key-value pair
	 * @throws IllegalArgumentException
	 *             if the key is unacceptable for this queue
	 */
	@TimeComplexity("O(n)")
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		/*
		 * TCJ: The method has to look through each entry one by one to
		 * determine the proper location where it will be sorted. At the worse
		 * case, it is the new minimum key and has to look through all n
		 * elements of the linked list.
		 */
		Entry<K, V> entry = new PQEntry<>(key, value);
		Position<Entry<K, V>> cursor = queue.last();
		while (cursor != null && c.compare(entry.getKey(), cursor.getElement().getKey()) < 0) {
			cursor = queue.before(cursor);
		}
		if (cursor == null) {
			queue.addFirst(entry);
		} else {
			queue.addAfter(cursor, entry);
		}
		size++;
		return entry;
	}

	/**
	 * Returns (but does not remove) an entry with minimal key.
	 * 
	 * @return entry having a minimal key (or null if empty)
	 */
	public Entry<K, V> min() {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			return null;
		}
		return queue.first().getElement();
	}

	/**
	 * Removes and returns an entry with minimal key.
	 * 
	 * @return the removed entry (or null if empty)
	 */
	public Entry<K, V> removeMin() {
		// TODO Auto-generated method stub
		if (size == 0) {
			return null;
		}
		Entry<K, V> min = min();
		queue.removeFirst();
		size--;
		return min;
	}

}
