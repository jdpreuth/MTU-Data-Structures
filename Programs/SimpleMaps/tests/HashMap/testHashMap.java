
import static org.junit.Assert.fail;

import java.util.Iterator;

import org.junit.Test;

import cs2321.HashMap;
import cs2321.LookupTable;
import net.datastructures.Entry;

public class testHashMap {

	@Test
	public void testgetandput() {
		HashMap<Integer, Integer> um = new HashMap<Integer, Integer>();

		for (int i = 0; i <= 98; i++) {
			um.put(i, i);
		}

		if (um.size() != 99)
			fail();

		if (um.remove(99) != null)
			fail();

		for (int i = 98; i >= 0; i--) {
			if (um.get(i) != i)
				fail();
		}

		for (int i = 98; i >= 0; i--) {
			if (um.remove(i) != i)
				fail();
		}

		if (!(um.size() == 0 && um.isEmpty()))
			fail();
	}

	@Test
	public void testKeysIterable() {
		HashMap<Integer, Integer> um = new HashMap<Integer, Integer>();

		for (int i = 0; i <= 100; i++) {
			if (i % 11 != 3)
				um.put(i, i);
		}

		Iterator<Integer> it = um.keySet().iterator();

		LookupTable<Integer, Integer> lt = new LookupTable<Integer, Integer>();
		while (it.hasNext()) {
			lt.put(it.next(), 0);
		}

		Iterator<Integer> ltIt = lt.keySet().iterator();
		for (int i = 0; ltIt.hasNext(); i++) {
			if (i % 11 != 3 && ltIt.next() != i)
				fail();
			if (i > 100)
				fail();
		}

		Iterator<Integer> it2 = um.values().iterator();

		lt = new LookupTable<Integer, Integer>();

		while (it2.hasNext()) {
			lt.put(it2.next(), 0);
		}
		for (int i = 0; ltIt.hasNext(); i++) {
			if (i % 11 != 3 && ltIt.next() != i)
				fail();
			if (i > 100)
				fail();
		}

		lt = new LookupTable<Integer, Integer>();
		Iterator<Entry<Integer, Integer>> it3 = um.entrySet().iterator();

		while (it3.hasNext()) {
			lt.put(it3.next().getKey(), 0);
		}
		for (int i = 0; ltIt.hasNext(); i++) {
			if (i % 11 != 3 && ltIt.next() != i)
				fail();
			if (i > 100)
				fail();
		}

	}

	@Test
	public void testEmptyMap() {
		HashMap<Integer, Integer> um = new HashMap<Integer, Integer>();

		if (um.size() != 0)
			fail();
		if (!um.isEmpty())
			fail();

		Iterator<Integer> it = um.keySet().iterator();
		if (it.hasNext())
			fail();
		it = um.values().iterator();
		if (it.hasNext())
			fail();
		boolean fail = false;
		try {
			if (it.next() != null)
				fail();
		} catch (Exception IllegalStateException) {
			fail = true;
		}
		if (!fail)
			fail();

		if (um.remove(0) != null)
			fail();
		if (um.get(32582) != null)
			fail();
	}

	@Test
	public void testPutCollision() {
		HashMap<Integer, Integer> um = new HashMap<Integer, Integer>();

		for (int i = 0; i <= 10; i++) {
			um.put(i, i);
		}

		assert (um.put(5, 99) == 5);
		assert (um.put(5, 5) == 99);

	}

}
