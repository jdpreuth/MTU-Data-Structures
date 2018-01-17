package cs2321;

import java.util.Comparator;

import net.datastructures.Entry;
import net.datastructures.Position;
import net.datastructures.PriorityQueue;

/**
 * A PriorityQueue based on an Unordered Doubly Linked List.
 * 
 * Course: CS2321 Section ALL Assignment: #3
 * 
 * @author Jon Preuth
 */

public class UnorderedPQ<K, V> implements PriorityQueue<K, V> {

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
	private Comparator<K> c; // Comparator to be used to sort the PQ

	/**
	 * Default Constructor for the PQ. Sets the comparator to the default
	 * comparator
	 */
	public UnorderedPQ() {
		// TODO implement this method
		c = new DefaultComparator();
	}

	/**
	 * Constructor for a PQ with a given comparator
	 * 
	 * @param c
	 *            the comparator to be used to sort the PQ
	 */
	public UnorderedPQ(Comparator<K> c) {
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
	public Entry<K, V> insert(K key, V value) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		Entry<K, V> entry = new PQEntry<K, V>(key, value);
		queue.addLast(entry);
		size++;
		return entry;
	}

	/**
	 * Returns (but does not remove) an entry with minimal key.
	 * 
	 * @return entry having a minimal key (or null if empty)
	 */
	@TimeComplexity("O(n)")
	public Entry<K, V> min() {
		/*
		 * TCJ: To find the minimum value the method has to iterate through all
		 * n elements of the array
		 */
		if (isEmpty()) {
			return null;
		}
		Entry<K, V> min = queue.first().getElement();
		Position<Entry<K, V>> minPosition = queue.first();
		Position<Entry<K, V>> cursor = queue.after(minPosition);
		while (cursor != null) {
			if (c.compare(minPosition.getElement().getKey(), cursor.getElement().getKey()) > 0) {
				min = cursor.getElement();
				minPosition = cursor;
			}
			cursor = queue.after(cursor);
		}
		return min;
	}

	/**
	 * Removes and returns an entry with minimal key.
	 * 
	 * @return the removed entry (or null if empty)
	 */
	@TimeComplexity("O(n)")
	public Entry<K, V> removeMin() {
		/*
		 * TCJ: To find the minimum value the method has to iterate through all
		 * n elements of the array
		 */
		if (isEmpty()) {
			return null;
		}
		Entry<K, V> min = queue.first().getElement();
		Position<Entry<K, V>> minPosition = queue.first();
		Position<Entry<K, V>> cursor = queue.after(minPosition);
		while (cursor != null) {
			if (c.compare(minPosition.getElement().getKey(), cursor.getElement().getKey()) > 0) {
				min = cursor.getElement();
				minPosition = cursor;
			}
			cursor = queue.after(cursor);
		}
		queue.remove(minPosition);
		size--;
		return min;
	}

}
