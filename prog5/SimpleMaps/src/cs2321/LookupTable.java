package cs2321;

import java.util.Comparator;

import net.datastructures.Entry;
import net.datastructures.SortedMap;

public class LookupTable<K, V> implements SortedMap<K, V> {

	private ArrayList<MapEntry<K, V>> map = new ArrayList<MapEntry<K, V>>(); // The
																				// arraylist
																				// that
																				// represents
																				// the
																				// sorted
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

		/**
		 * sets the value of an entry
		 * 
		 * @param value
		 *            the new value to be set
		 */
		public void setValue(V value) {
			this.value = value;
		}
	}

	/**
	 * Constructs a lookuptable with a default comparator
	 */
	public LookupTable() {
		// TODO Add necessary initialization
		c = new DefaultComparator();
	}

	/*
	 * @param comparator - on construction, set comparator
	 */
	public LookupTable(Comparator<K> comparator) {
		// TODO Add necessary initialization
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

	/**
	 * Binary search through a map to find an entry
	 * 
	 * @param array
	 *            map to search
	 * @param key
	 *            key to search for
	 * @return the position where the entry is or where the entry should be if
	 *         it isn't in the map
	 */
	@TimeComplexity("O(logn)")
	private int binarySearch(ArrayList<MapEntry<K, V>> array, K key) {
		/*
		 * TCJ: The map is split in half every iteration meaning worse case it
		 * has to run log n times
		 */
		int low = 0;
		int high = array.size() - 1;
		while (low <= high) {
			int mid = (low + high) / 2;
			if (c.compare(array.get(mid).getKey(), key) == 0) {
				return mid;
			} else if (c.compare(array.get(mid).getKey(), key) > 0) {
				high = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		return low;
	}

	@Override
	@TimeComplexity("O(logn)")
	public V get(K key) {
		// TODO Auto-generated method stub
		/*
		 * TCJ: The map is split in half every iteration meanign worse case it
		 * has to run log n times to find it
		 */
		int position = binarySearch(map, key);
		if (position == size() || c.compare(map.get(position).getKey(), key) != 0) {
			return null;
		}
		return map.get(position).getValue();
	}

	@Override
	@TimeComplexity("O(n)")
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		/*
		 * If the entry doesn't exist a new entry must be created and shift
		 * every other element over which
		 */
		int position = binarySearch(map, key);

		if (position < size() && c.compare(map.get(position).getKey(), key) == 0) {
			V temp = map.get(position).getValue();
			map.get(position).setValue(value);
			return temp;
		} else {
			map.add(position, new MapEntry<K, V>(key, value));
			return null;
		}
	}

	@Override
	@TimeComplexity("O(n)")
	public V remove(K key) {
		// TODO Auto-generated method stub
		/*
		 * TCJ: If the key is found it must be removed which requires shifting
		 * every other element
		 */
		int position = binarySearch(map, key);
		if (c.compare(map.get(position).getKey(), key) == 0) {
			V temp = map.get(position).getValue();
			map.remove(position);
			return temp;
		} else {
			return null;
		}
	}

	/**
	 * Creates an iterable based on a given start and stop point in a map
	 * 
	 * @param startIndex
	 *            the starting index of the map
	 * @param stop
	 *            the key to stop when found
	 * @return a subset of a map
	 */
	@TimeComplexity("O(n)")
	private Iterable<Entry<K, V>> snapshot(int startIndex, K stop) {
		/*
		 * Each element of the map must be added to the buffer
		 */
		ArrayList<Entry<K, V>> buffer = new ArrayList<>();
		int j = startIndex;
		while (j < map.size() && (stop == null || c.compare(stop, map.get(j).getKey()) > 0)) {
			buffer.addLast(map.get(j++));
		}
		return buffer;
	}

	@Override
	public Iterable<K> keySet() {
		// TODO Auto-generated method stub
		Iterable<Entry<K, V>> buffer = entrySet();
		ArrayList<K> keys = new ArrayList<>();
		for (Entry<K, V> entry : buffer) {
			keys.addLast(entry.getKey());
		}
		return keys;
	}

	@Override
	public Iterable<V> values() {
		// TODO Auto-generated method stub
		Iterable<Entry<K, V>> buffer = entrySet();
		ArrayList<V> values = new ArrayList<>();
		for (Entry<K, V> entry : buffer) {
			values.addLast(entry.getValue());
		}
		return values;
	}

	@Override
	@TimeComplexity("O(n)")
	public Iterable<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		/*
		 * Calls an O(n) operation
		 */
		return snapshot(0, null);
	}

	@Override
	@TimeComplexity("O(1)")
	public Entry<K, V> firstEntry() {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			return null;
		} else {
			return map.get(0);
		}
	}

	@Override
	@TimeComplexity("O(1)")
	public Entry<K, V> lastEntry() {
		// TODO Auto-generated method stub
		if (isEmpty()) {
			return null;
		} else {
			return map.get(map.size() - 1);
		}
	}

	@Override
	@TimeComplexity("O(logn)")
	public Entry<K, V> ceilingEntry(K key) {
		// TODO Auto-generated method stub
		/*
		 * TCJ: binary search for an entry which cost log n
		 */
		int position = binarySearch(map, key);
		if (position < 0 || position >= map.size()) {
			return null;
		}
		return map.get(position);
	}

	@Override
	@TimeComplexity("O(n)")
	public Entry<K, V> floorEntry(K key) {
		// TODO Auto-generated method stub
		/*
		 * TCJ: binary search for an entry which cost log n
		 */
		int position = binarySearch(map, key);
		if (position == size() || c.compare(map.get(position).getKey(), key) != 0) {
			position--;
		}
		if (position < 0 || position >= map.size()) {
			return null;
		}
		return map.get(position);
	}

	@Override
	@TimeComplexity("O(n)")
	public Entry<K, V> lowerEntry(K key) {
		// TODO Auto-generated method stub
		/*
		 * TCJ: binary search for a nentry which cost log n
		 */
		int position = binarySearch(map, key) - 1;
		if (position < 0 || position >= map.size()) {
			return null;
		}
		return map.get(position);
	}

	@Override
	@TimeComplexity("O(n)")
	public Entry<K, V> higherEntry(K key) {
		// TODO Auto-generated method stub
		/*
		 * TCJ: binary search for an entry which cost log n
		 */
		int position = binarySearch(map, key);
		if (position < size() && c.compare(map.get(position).getKey(), key) == 0) {
			position++;
		}
		if (position < 0 || position >= map.size()) {
			return null;
		}
		return map.get(position);
	}

	@Override
	@TimeComplexity("O(n)")
	public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Calls an O(n) operation
		 */
		return snapshot(binarySearch(map, fromKey), toKey);
	}

}
