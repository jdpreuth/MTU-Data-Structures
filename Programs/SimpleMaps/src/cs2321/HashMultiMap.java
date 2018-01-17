package cs2321;

import java.util.Comparator;

import net.datastructures.Entry;
import net.datastructures.Map;
import net.datastructures.Position;

public class HashMultiMap<K, V> {
	private Map<K, DoublyLinkedList<V>> map; // Map that represents the multimap
	private int total = 0; // total number of entries
	private Comparator c; // comparator for the map

	/*
	 * Creates a multimap with a given hashsize and comparator
	 */
	public HashMultiMap(int hashsize, Comparator<K> c) {
		// TODO Auto-generated method stub
		this.c = c;
		this.map = new HashMap<>(hashsize);
	}

	/*
	 * Creates a multimap with a default comparator and hashsize of 17
	 */
	public HashMultiMap() {
		// TODO Auto-generated method stub
		c = new DefaultComparator();
		this.map = new HashMap<>();
	}

	/*
	 * return how many entries in the map
	 */
	public int size() {
		// TODO Auto-generated method stub
		return total;
	}

	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return total == 0;
	}

	/*
	 * Returns a collection of all values associated with key k in the map.
	 * Don't return null, but return a collection that hold no data.
	 */
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(1)")
	public Iterable<V> get(K key) {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Calls the hashmap function get which is an expected O(1)
		 * operation but worse case O(n) operation
		 */
		DoublyLinkedList<V> list = map.get(key);
		if (list == null) {
			list = new DoublyLinkedList<V>();
		}
		return list;
	}

	/*
	 * Adds a new entry to the multimap associating key k with value v, without
	 * overwriting any existing mappings for key k.
	 */
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(1)")
	public void put(K key, V value) {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Calls the hashmap function get which is an expected O(1)
		 * operation but worse case O(n) operation
		 */
		DoublyLinkedList<V> list = map.get(key);
		if (list == null) {
			list = new DoublyLinkedList<V>();
			map.put(key, list);
		}
		list.addLast(value);
		total++;
	}

	/*
	 * Removes an entry mapping key k to value v from the multimap. return true
	 * if such entry exists, otherwise return false
	 */
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(1)")
	public boolean remove(K key, V value) {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Calls the hashmap function get which is an expected O(1)
		 * operation but worse case O(n) operation
		 */
		DoublyLinkedList<V> list = map.get(key);
		if (list == null) {
			return false;
		}
		for (Position<V> p : list.positions()) {
			if (c.compare(p.getElement(), value) == 0) {
				list.remove(p);
				total--;
				return true;
			}
		}
		return false;
	}

	/*
	 * Removes all entries having key equal to k from the multimap. return an
	 * empty list if no entry matched the given key. Don't return null.
	 */
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(1)")
	public Iterable<V> removeAll(K key) {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Calls the hashmap function get which is an expected O(1)
		 * operation but worse case O(n) operation
		 */
		DoublyLinkedList<V> list = map.get(key);
		if (list != null) {
			total -= list.size();
			map.remove(key);
		} else {
			list = new DoublyLinkedList<V>();
		}
		return list;
	}

	/*
	 * Returns a non-duplicative collection of keys in the multimap. return an
	 * empty list if there is no key. Don't return null.
	 */
	@TimeComplexity("O(n)")
	public Iterable<K> keySet() {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Every element must be looked through to create a set of keys
		 */
		DoublyLinkedList<K> result = new DoublyLinkedList<>();
		Iterable<Entry<K, DoublyLinkedList<V>>> itr = map.entrySet();
		if (isEmpty()) {
			result = new DoublyLinkedList<K>();
			return result;
		}
		for (Entry<K, DoublyLinkedList<V>> entry : itr) {
			if (entry != null) {
				result.addLast(entry.getKey());
			}
		}
		return result;
	}

	/*
	 * Returns a collection of values for all entries int the multimap return an
	 * empty list if there is entry. Don't return null.
	 */
	@TimeComplexity("O(n)")
	public Iterable<V> values() {
		// TODO Auto-generated method stub
		/*
		 * TCJ: every element must be looked through to create a set of values
		 */
		DoublyLinkedList<V> result = new DoublyLinkedList<>();
		Iterable<Entry<K, DoublyLinkedList<V>>> itr = map.entrySet();
		if (isEmpty()) {
			result = new DoublyLinkedList<V>();
			return result;
		}
		for (Entry<K, DoublyLinkedList<V>> entry : itr) {
			if (entry != null) {
				for (V value : entry.getValue()) {
					if (value != null) {
						result.addLast(value);
					}
				}
			}
		}
		return result;
	}

}
