package cs2321;

import java.util.Comparator;

import net.datastructures.Entry;
import net.datastructures.SortedMap;

public class BinarySearchTree<K, V> extends LinkedBinaryTree<Entry<K, V>> implements SortedMap<K, V> {
	// Nested entry class
	private class TreeEntry implements Entry<K, V> {

		K key;
		V value;

		public TreeEntry(K key, V value) {
			this.key = key;
			this.value = value;
		}

		public TreeEntry() {
			this.key = null;
			this.value = null;
		}

		@Override
		public K getKey() {
			// TODO Auto-generated method stub
			return key;
		}

		@Override
		public V getValue() {
			// TODO Auto-generated method stub
			return value;
		}

		public void setValue(V value) {
			this.value = value;
		}

	}

	LinkedBinaryTree<Entry<K, V>> tree = new LinkedBinaryTree<Entry<K, V>>(); // Linked
																				// binary
																				// tree
																				// that
																				// holds
																				// values
	Comparator<K> c; // Comparator for determining key placement
	private int size = 0; // Number of entries in the tree

	/*
	 * default constructor
	 */
	public BinarySearchTree() {
		// TODO Add necessary initialization
		c = new DefaultComparator<K>();
	}

	/*
	 * @param comparator - on construction, set comparator
	 */
	public BinarySearchTree(Comparator<K> comparator) {
		// TODO Add necessary initialization
		c = comparator;
	}

	/*
	 * Binary search through the tree. Returns the entry if found, or where the
	 * entry would go if it's not in the tree
	 */
	@TimeComplexity("O(h)")
	protected Node treeSearch(Node node, K key) {
		/*
		 * TCJ: All the nodes could be in a line and every element must be
		 * checked
		 */
		if (isExternal(node)) {
			return node;
		}
		if (c.compare(node.getElement().getKey(), key) < 0) {
			if (node.hasRight()) {
				return treeSearch(node.right(), key);
			}
			return node;
		}
		if (c.compare(node.getElement().getKey(), key) > 0) {
			if (node.hasleft()) {
				return treeSearch(node.left(), key);
			}
			return node;
		}
		return node;
	}

	/*
	 * Returns the root of the tree
	 */
	@TimeComplexity("O(1)")
	protected Node getRoot() {
		/*
		 * TCJ: Simple return statement that's a single operation
		 */
		return (Node) root();
	}

	@Override
	@TimeComplexity("O(1)")
	public int size() {
		// TODO Auto-generated method stub
		/*
		 * Simply returns a global variable
		 */
		return size;
	}

	@Override
	@TimeComplexity("O(h)")
	public V get(K key) {
		// TODO Auto-generated method stub
		/*
		 * Calls treeSearch which is O(h)
		 */
		Node node = treeSearch(getRoot(), key);
		if (isExternal(node)) {
			if (c.compare(node.getElement().getKey(), key) == 0) {
				return node.getElement().getValue();
			}
			return null;
		} else if (numChildren(node) == 1) {
			if (c.compare(node.getElement().getKey(), key) == 0) {
				return node.getElement().getValue();
			}
			return null;
		}
		return node.getElement().getValue();
	}

	@Override
	@TimeComplexity("O(h)")
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Calls treeSearch which is O(h)
		 */
		if (getRoot() == null) {
			addRoot(new TreeEntry(key, value));
			size++;
			return null;
		}

		Node node = treeSearch(getRoot(), key);
		if (node.hasleft() && node.hasRight()) {
			V oldValue = node.getElement().getValue();
			((TreeEntry) node.getElement()).setValue(value);
			return oldValue;
		} else {
			if (c.compare(node.getElement().getKey(), key) < 0) {
				tree.insertRight(node, new TreeEntry(key, value));
				size++;
				return null;
			} else if (c.compare(node.getElement().getKey(), key) > 0) {
				tree.insertLeft(node, new TreeEntry(key, value));
				size++;
				return null;
			} else {
				V oldValue = node.getElement().getValue();
				((TreeEntry) node.getElement()).setValue(value);
				return oldValue;
			}
		}
	}

	/*
	 * Finds a given nodes succesor
	 */
	@TimeComplexity("O(h)")
	private Node succesor(Node v) {
		/*
		 * TCJ: The nodes could be in a straight line and the succesor is all
		 * the way at the top
		 */
		if (v.hasRight()) {
			Node w = (Node) right(v);
			while (w.hasleft()) {
				w = (Node) left(w);
			}
			return w;
		} else {
			while (v.parent().right() == v) {
				v = v.parent();
			}
			return v.parent();
		}
	}

	/*
	 * Finds a given nodes predecessor
	 */
	@TimeComplexity("O(h)")
	private Node predecessor(Node v) {
		/*
		 * TCJ: All the nodes could be in a straight line and the predesseocr is
		 * all the way at the bottom
		 */
		if (v.hasleft()) {
			Node w = (Node) left(v);
			while (w.hasRight()) {
				w = (Node) right(w);
			}
			return w;
		} else {
			while (v.parent().left() == v) {
				v = v.parent();
			}
			return v.parent();
		}
	}

	@Override
	@TimeComplexity("O(h)")
	public V remove(K key) {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Calls treeSearch which is O(h)
		 */
		Node node = treeSearch(getRoot(), key);
		if (isExternal(node) && c.compare(node.getElement().getKey(), key) == 0) {
			super.remove(node);
			return node.getElement().getValue();
		} else if (isExternal(node)) {
			return null;
		} else if (numChildren(node) == 1) {
			if (!(c.compare(node.getElement().getKey(), key) == 0)) {
				return null;
			}
		}
		Node succesor = succesor(node);
		V oldV = node.getElement().getValue();
		node.setElement(succesor.getElement());
		super.remove(succesor);
		return oldV;
	}

	/*
	 * Looks at each node in the tree and adds it to a doublylinkedlist
	 */
	@TimeComplexity("O(n)")
	private void postOrder(Node node, DoublyLinkedList<Node> list) {
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
	public Iterable<K> keySet() {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Calls postOrder which is O(n)
		 */
		DoublyLinkedList<Node> nodes = new DoublyLinkedList<Node>();
		DoublyLinkedList<K> keySet = new DoublyLinkedList<K>();
		postOrder(getRoot(), nodes);
		for (Node node : nodes) {
			keySet.addLast(node.getElement().getKey());
		}
		return keySet;
	}

	@Override
	@TimeComplexity("O(n)")
	public Iterable<V> values() {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Calls postOrder which is O(n)
		 */
		DoublyLinkedList<Node> nodes = new DoublyLinkedList<Node>();
		DoublyLinkedList<V> values = new DoublyLinkedList<V>();
		postOrder(getRoot(), nodes);
		for (Node node : nodes) {
			values.addLast(node.getElement().getValue());
		}
		return values;
	}

	@Override
	@TimeComplexity("O(n)")
	public Iterable<Entry<K, V>> entrySet() {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Calls postOrder which is O(n)
		 */
		DoublyLinkedList<Node> nodes = new DoublyLinkedList<Node>();
		DoublyLinkedList<Entry<K, V>> entries = new DoublyLinkedList<Entry<K, V>>();
		postOrder(getRoot(), nodes);
		for (Node node : nodes) {
			entries.addLast(node.getElement());
		}
		return entries;
	}

	@Override
	@TimeComplexity("O(h)")
	public Entry<K, V> firstEntry() {
		/*
		 * TCJ: The nodes could be in a straight line and every node must be
		 * checked
		 */
		// TODO Auto-generated method stub
		Node least = getRoot();
		if (least == null) {
			return null;
		}
		while (least.hasleft()) {
			least = (Node) left(least);
		}
		return least.getElement();
	}

	@Override
	@TimeComplexity("O(h)")
	public Entry<K, V> lastEntry() {
		// TODO Auto-generated method stub
		/*
		 * TCJ: The nodes could be in a straight line and every node must be
		 * checked
		 */
		Node least = getRoot();
		if (least == null) {
			return null;
		}
		while (least.hasRight()) {
			least = (Node) right(least);
		}
		return least.getElement();
	}

	@Override
	@TimeComplexity("O(h)")
	public Entry<K, V> ceilingEntry(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * TCJ: CAlls treeSearch which is O(h)
		 */
		Node ceiling = treeSearch(getRoot(), key);
		if (c.compare(ceiling.getElement().getKey(), key) < 0) {
			ceiling = succesor(ceiling);
		}
		return ceiling.getElement();
	}

	@Override
	@TimeComplexity("O(h)")
	public Entry<K, V> floorEntry(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Calls treeSearch which is O(h)
		 */
		Node floor = treeSearch(getRoot(), key);
		if (c.compare(floor.getElement().getKey(), key) > 0) {
			floor = predecessor(floor);
		}
		return floor.getElement();
	}

	@Override
	@TimeComplexity("O(h)")
	public Entry<K, V> lowerEntry(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Call treeSearch which is O(h)
		 */
		Node lower = treeSearch(getRoot(), key);
		while (c.compare(lower.getElement().getKey(), key) > 0) {
			lower = lower.parent();
			return lower.getElement();
		}
		return predecessor(lower).getElement();
	}

	@Override
	@TimeComplexity("O(h)")
	public Entry<K, V> higherEntry(K key) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Calls treeSearch which is O(h)
		 */
		Node higher = treeSearch(getRoot(), key);
		while (c.compare(higher.getElement().getKey(), key) < 0) {
			higher = higher.parent();
			return higher.getElement();
		}
		if (c.compare(higher.getElement().getKey(), key) == 0) {
			higher = succesor(higher);
		}
		return (higher).getElement();
	}

	@Override
	@TimeComplexity("O(n)")
	public Iterable<Entry<K, V>> subMap(K fromKey, K toKey) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Calls entrySet which is O(n)
		 */
		DoublyLinkedList<Entry<K, V>> entries = (DoublyLinkedList<Entry<K, V>>) entrySet();
		DoublyLinkedList<Entry<K, V>> subMap = new DoublyLinkedList<Entry<K, V>>();
		for (Entry<K, V> entry : entries) {
			if (c.compare(entry.getKey(), fromKey) >= 0 && c.compare(entry.getKey(), toKey) < 0) {
				subMap.addLast(entry);
			}
		}
		return subMap;
	}

}
