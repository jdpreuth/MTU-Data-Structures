package cs2321;

import java.util.Comparator;

import net.datastructures.Entry;
import net.datastructures.Position;
import net.datastructures.SortedMap;

public class AVL<K, V> extends BinarySearchTree<K, V> implements SortedMap<K, V> {

	public AVL() {
		// TODO Auto-generated constructor stub
		super();
	}

	public AVL(Comparator<K> c) {
		// TODO Auto-generated constructor stub
		super(c);
	}

	/*
	 * Sets the height of a given node
	 */
	private void setHeight(Node node) {
		node.setHeight(Math.max(node.left().getHeight(), (node.right().getHeight())) + 1);
	}

	/*
	 * Checks to see if the tree is out of balance and rebalances it if so
	 */
	@TimeComplexity("O(log n)")
	private void rebalance(Node node) {
		/*
		 * TCJ: This method makes potentially multiple calls to reconstruct
		 * which is O(1). Since the tree is guaranteed to be balanced before
		 * this method had to be called, this method will at most have to look
		 * at half of the entire tree every time it is called again which means
		 * it makes log n O(1) calls
		 */
		if (node.hasleft()) {
			setHeight(node.left());
		}
		if (node.hasRight()) {
			setHeight(node.right());
		}

		if (Math.abs(node.left().getHeight() - node.right().getHeight()) > 1) {
			reconstruct(node);
		}

		int old = node.getHeight();
		setHeight(node);

		if (old != node.getHeight() && !isRoot(node)) {
			rebalance(node.parent());
		}
	}

	/*
	 * Changes the tree's construction to be balanced
	 */
	@TimeComplexity("O(1)")
	private Node reconstruct(Node z) {
		/*
		 * TCJ: This method only changes pointers around which are all single
		 * operations
		 */
		Node p = z.parent();

		Node y;
		if (z.left().getHeight() > z.right().getHeight()) {
			y = z.left();
		} else {
			y = z.right();
		}

		Node x;
		if (y.left().getHeight() > y.right().getHeight()) {
			x = y.left();
		} else {
			x = y.right();
		}

		Node a = null;
		Node b = null;
		Node c = null;
		Node t0 = null;
		Node t1 = null;
		Node t2 = null;
		Node t3 = null;
		if (z.right() == y && y.left() == x) {
			a = z;
			b = x;
			c = y;
			t0 = a.left();
			t1 = b.left();
			t2 = b.right();
			t3 = c.right();
		} else if (z.right() == y && y.right() == x) {
			a = z;
			b = y;
			c = x;
			t0 = a.left();
			t1 = b.left();
			t2 = c.left();
			t3 = c.right();
		} else if (z.left() == y && y.left() == x) {
			a = x;
			b = y;
			c = z;
			t0 = a.left();
			t1 = a.right();
			t2 = b.right();
			t3 = c.right();
		} else if (z.left() == y && y.right() == x) {
			a = y;
			b = x;
			c = z;
			t0 = a.left();
			t1 = b.left();
			t2 = b.right();
			t3 = c.right();
		}

		a.setLeft(t0);
		a.setRight(t2);
		setHeight(a);

		c.setLeft(t2);
		c.setRight(t3);
		setHeight(c);

		b.setLeft(a);
		b.setRight(c);
		setHeight(b);

		if (p == null) {
			root = b;
		} else {
			if (p.left() == z) {
				p.setLeft(b);
			} else {
				p.setRight(b);
			}
		}
		return b;
	}

	/*
	 * Returns the height of a given node
	 */
	@TimeComplexity("O(1)")
	public int height(Position<Entry<K, V>> v) {
		// TODO return the height of subtree v
		/*
		 * TCJ: Returning a single value is a single operation
		 */
		Node node = castToNode(v);
		return node.getHeight();
	}

	@Override
	@TimeComplexity("O(log n)")
	public V put(K key, V value) {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Since the tree is guranteed to be balanced, the binary search
		 * has a guranteed O(log n) time and the rebalancing is O(log n) which
		 * means this method is O(log n)
		 */
		V place = super.put(key, value);
		Node w = treeSearch(getRoot(), key);
		if (place == null) {
			rebalance(w);
			System.out.println();
			return null;
		}
		return place;
	}

	@Override
	@TimeComplexity("O(log n)")
	public V remove(K key) {
		// TODO Auto-generated method stub
		/*
		 * After the removal of the node, remove calls rebalance which is a
		 * O(log n) operation
		 */
		Node node = treeSearch(getRoot(), key);
		if (!(c.compare(node.getElement().getKey(), key) == 0)) {
			return null;
		}
		Node w;
		if (node.left().isSentinel() || node.right().isSentinel()) {
			w = node.parent();
		} else {
			w = node.right();
			while (!w.left().isSentinel()) {
				w = w.left();
			}
		}
		V oldValue = super.remove(node).getElement().getValue();
		if (oldValue != null) {
			rebalance(w);
		}
		return null;
	}

}
