package cs2321;

import java.util.Comparator;

import net.datastructures.Entry;
import net.datastructures.Map;

public class HashMap<K, V> implements Map<K, V> {
	private UnorderedMap<K, V>[] hashMap; // Array of unordered maps that
											// represent the hash map
	private Comparator c; // comparator for the hash map
	private int n = 0; // The number of entries in the hashmap
	private int hashsize; // The size of the hash map

	/**
	 * Constructor that takes a hash size
	 * 
	 * @param hashsize
	 *            The number of buckets to initialize in the HashMap
	 * @param comparator
	 *            - on construction, set comparator
	 */
	public HashMap(int hashsize, Comparator<K> comparator) {
		// TODO: Add necessary initialization
		c = comparator;
		this.hashsize = hashsize;
		hashMap = (UnorderedMap<K, V>[]) new UnorderedMap[hashsize];
		for (int i = 0; i < hashsize; i++) {
			hashMap[i] = new UnorderedMap<K, V>();
		}
	}

	/**
	 * Constructor that takes a hash size
	 * 
	 * @param hashsize
	 *            The number of buckets to initialize in the HashMap
	 */
	public HashMap(int hashsize) {
		// TODO Add necessary initialization
		this.hashsize = hashsize;
		hashMap = (UnorderedMap<K, V>[]) new UnorderedMap[hashsize];
		for (int i = 0; i < hashsize; i++) {
			hashMap[i] = new UnorderedMap<K, V>();
		}
	}

	/*
	 * @param comparator - the comparator to set
	 */
	public void setComparator(Comparator<K> comparator) {
		// TODO Add necessary initialization
		c = comparator;
	}

	public HashMap() {
		// TODO: Be sure to initialize the bucket array
		// using the default hash size provided.
		hashsize = 17;
		hashMap = (UnorderedMap<K, V>[]) new UnorderedMap[hashsize];
		for (int i = 0; i < hashsize; i++) {
			hashMap[i] = new UnorderedMap<K, V>();
		}
	}

	/**
	 * Returns the hash value of a given key
	 * 
	 * @param key
	 *            the key to be hashed
	 * @return the hash value of the key
	 */
	private int hash(K key) {
		return Math.abs(key.hashCode() % hashsize);
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return n;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return n == 0;
	}

	@Override
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(1)")
	public V get(K key) {
		// TODO Auto-generated method stub
		/*
		 * TCJ: If the hash values are like random then the hashes are very
		 * unique and finding an element is O(1). However, worse case, all the
		 * elements collide and every element must be looked through
		 */
		int hash = hash(key);
		UnorderedMap<K, V> bucket = hashMap[hash];
		if (bucket == null) {
			return null;
		}
		return bucket.get(key);
	}

	@Override
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(1)")
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		/*
		 * TCJ: If the hash values are like random then the hashes are very
		 * unique and finding an element is O(1). However, worse case, all the
		 * elements collide and every element must be looked through
		 */
		int hash = hash(key);
		UnorderedMap<K, V> bucket = hashMap[hash];
		int oldSize = bucket.size();
		V answer = bucket.put(key, value);
		n += (bucket.size() - oldSize);
		return answer;
	}

	@Override
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(1)")
	public V remove(K key) {
		// TODO Auto-generated method stub
		/*
		 * TCJ: If the hash values are like random then the hashes are very
		 * unique and finding an element is O(1). However, worse case, all the
		 * elements collide and every element must be looked through
		 */
		int hash = hash(key);
		UnorderedMap<K, V> bucket = hashMap[hash];
		if (bucket == null) {
			return null;
		}
		int oldSize = bucket.size();
		V answer = bucket.remove(key);
		n -= (oldSize - bucket.size());
		return answer;
	}

	@Override
	@TimeComplexity("O(n)")
	public Iterable<K> keySet() {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Every element must be looked through to create a set of keys
		 */
		Iterable<Entry<K, V>> buffer = entrySet();
		ArrayList<K> keys = new ArrayList<>();
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
		 * TCJ: Every element must be looked through to create a set of values
		 */
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
		 * TCJ: Every element must be looked through to create a set of entries
		 */
		ArrayList<Entry<K, V>> buffer = new ArrayList<>();
		for (int h = 0; h < hashsize; h++) {
			if (hashMap[h] != null) {
				for (Entry<K, V> entry : hashMap[h].entrySet()) {
					buffer.addLast(entry);
				}
			}
		}
		return buffer;
	}

}
