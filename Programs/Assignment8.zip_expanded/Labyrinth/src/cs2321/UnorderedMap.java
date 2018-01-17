package cs2321;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

import net.datastructures.Entry;
import net.datastructures.Map;

public class UnorderedMap<K, V> implements Map<K, V> {

	private ArrayList<Entry<K, V>> map = new ArrayList<Entry<K, V>>(); // ArrayList
																		// that
																		// represents
																		// the
																		// unordered
																		// map
	private Comparator c; // The comparator for the map

	// Nested class for map entries
	private class MapEntry<K, V> implements Entry<K, V> {
		private K key; // The entries key
		private V value; // the entries value

		/**
		 * Constructor for the Map Entry
		 * 
		 * @param key
		 *            the key for the entry
		 * @param value
		 *            the value for the entry
		 */
		MapEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		/**
		 * returns the key value
		 * 
		 * @return the key value
		 */
		public K getKey() {
			return key;
		}

		/**
		 * returns the value of the entry
		 * 
		 * @return the value of the entry
		 */
		public V getValue() {
			return value;
		}
	}

	/**
	 * Constructor for an unordermap with a default constructor
	 */
	public UnorderedMap() {
		// TODO Auto-generated constructor stub
		c = new DefaultComparator();
	}

	/*
	 * @param comparator - on construction, set comparator
	 */
	public UnorderedMap(Comparator<K> comparator) {
		// TODO Auto-generated constructor stub
		c = comparator;
	}

	/*
	 * @param comparator - the comparator to set
	 */
	public void setComparator(Comparator<K> comparator) {
		// TODO Add necessary initialization
		c = comparator;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return map.size();
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return map.isEmpty();
	}

	@Override
	@TimeComplexity("O(n)")
	public V get(K key) {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Must iterate each element in the list to compare agaist the
		 * given key to determine if its in the list
		 */
		for (Entry<K, V> element : map) {
			if (element.getKey() == key) {
				return element.getValue();
			}
		}
		return null;
	}

	@Override
	@TimeComplexity("O(n)")
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Worse case the entry does not exist already and every element
		 * has to be searched through before that can be determined
		 */
		for (int i = 0; i < map.size(); i++) {
			Entry<K, V> element = map.get(i);
			if (element.getKey() == key) {
				V temp = element.getValue();
				MapEntry<K, V> update = new MapEntry<K, V>(key, value);
				map.set(i, update);
				return temp;
			}
		}
		map.addLast(new MapEntry<K, V>(key, value));
		return null;
	}

	@Override
	@TimeComplexity("O(n)")
	public V remove(K key) {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Must search through every element in order to find the one to
		 * remove. Worse case the entry doesn't exist and every entry much be
		 * looked at to determine that
		 */
		for (int i = 0; i < map.size(); i++) {
			Entry<K, V> element = map.get(i);
			if (element.getKey() == key) {
				V temp = element.getValue();
				map.remove(i);
				return temp;
			}
		}
		return null;
	}

	@Override
	@TimeComplexity("O(n)")
	public Iterable<K> keySet() {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Must iterate through every element in order to put the key into
		 * the arraylist
		 */
		Iterable<Entry<K, V>> buffer = entrySet();
		ArrayList<K> keys = new ArrayList<K>();
		for (Entry<K, V> entry : buffer) {
			keys.addLast(entry.getKey());
		}
		return keys;
	}

	@Override
	@TimeComplexity("O(n)")
	public Iterable<V> values() {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Must iterate through every element in order to put the values
		 * into the arraylist
		 */
		Iterable<Entry<K, V>> buffer = entrySet();
		ArrayList<V> values = new ArrayList<V>();
		for (Entry<K, V> entry : buffer) {
			values.addLast(entry.getValue());
		}
		return values;
	}

	// Nested Iterator class
	private class EntryIterator implements Iterator<Entry<K, V>> {
		private int j = 0;

		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return j < map.size();
		}

		@Override
		public Entry<K, V> next() {
			// TODO Auto-generated method stub
			if (j == map.size()) {
				throw new NoSuchElementException();
			}
			return map.get(j++);
		}

	}

	// Nested iterable class
	private class EntryIterable implements Iterable<Entry<K, V>> {

		@Override
		public Iterator<Entry<K, V>> iterator() {
			// TODO Auto-generated method stub
			return new EntryIterator();
		}

	}

	@Override
	@TimeComplexity("O(1)")
	public Iterable<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		return new EntryIterable();
	}

}
