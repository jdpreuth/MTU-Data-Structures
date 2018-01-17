
import static org.junit.Assert.fail;

import java.util.Iterator;

import org.junit.Test;

import cs2321.ArrayList;
import cs2321.HashMultiMap;
import cs2321.LookupTable;

public class testHashMultiMap {

	@Test
	public void testgetandput() {
		HashMultiMap<Integer, Integer> um = new HashMultiMap<Integer, Integer>();

		for (int i = 0; i <= 99; i++) {
			um.put(i, i);
		}
		for (int i = 0; i <= 99; i++) {
			um.put(i, i + 1);
		}

		if (um.remove(101, 101))
			fail();

		if (um.remove(1, 7))
			fail();

		if (um.remove(53, 7))
			fail();

		if (um.size() != 200)
			fail();

		for (int i = 98; i >= 0; i--) {
			if (!um.remove(i, i + 1))
				fail();
		}

		for (int i = 98; i >= 0; i--) {
			for (int w : um.get(i)) {
				if (w != i) {
					System.out.println("Failed w " + w + " i " + i);
					fail();
				}
			}
		}

		for (int i = 98; i >= 0; i--) {
			if (!um.remove(i, i))
				fail();
		}

		for (int i = 0; i <= 99; i++) {
			um.put(i, i);
		}
		for (int i = 0; i <= 99; i++) {
			um.put(i, i + 1);
		}

		for (int w : um.removeAll(50)) {
			if (w != 50 && w != 51)
				fail();
		}
		for (int w : um.removeAll(50)) {
			fail();
		}

		if (um.remove(50, 50))
			fail();
	}

	@Test
	public void testKeysIterable() {
		HashMultiMap<Integer, Integer> um = new HashMultiMap<Integer, Integer>();

		for (int i = 1; i <= 100; i++) {
			um.put(i, i);
		}
		for (int i = 1; i <= 100; i++) {
			um.put(i, 0);
		}

		Iterator<Integer> it = um.keySet().iterator();

		LookupTable<Integer, Integer> lt = new LookupTable<Integer, Integer>();
		while (it.hasNext()) {
			lt.put(it.next(), 0);
		}

		Iterator<Integer> ltIt = lt.keySet().iterator();
		for (int i = 1; ltIt.hasNext(); i++) {
			if (ltIt.next() != i)
				fail();
			if (i > 100)
				fail();
		}

		Iterator<Integer> it2 = um.values().iterator();

		lt = new LookupTable<Integer, Integer>();

		int itn;

		int j = 0;
		while (it2.hasNext()) {
			itn = it2.next();
			lt.put(itn, 0);
			if (itn == 0)
				j++;

		}

		if (j != 100)
			fail();

		if (um.size() != ((ArrayList<Integer>) um.values()).size())
			fail();

		for (int i = 1; ltIt.hasNext(); i++) {
			if (ltIt.next() != i)
				fail();
			if (i > 100)
				fail();
		}

	}

	@Test
	public void testEmptyMap() {
		HashMultiMap<Integer, Integer> um = new HashMultiMap<Integer, Integer>();

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

		if (um.remove(0, 0))
			fail();

		if (!((ArrayList<Integer>) um.get(32582)).isEmpty())
			fail();
	}

}
