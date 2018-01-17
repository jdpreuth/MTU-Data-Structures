package cs2321;

import static org.junit.Assert.*;

import java.util.Iterator;

import net.datastructures.Entry;

import org.junit.Test;

public class testLookupTable {

	@Test
	public void testgetandput() {
		LookupTable<Integer, Integer> um = new LookupTable<Integer, Integer>();
		
		for(int i = 0; i <= 10; i++){
			um.put(i, i);
		}
		
		
		assert(um.remove(99) == null);
		
		for(int i = 10; i >= 0; i--) {
			if(um.get(i) != i)
				fail();
		}
		
		for(int i = 10; i >= 0; i--) {
			if(um.remove(i) != i)
				fail();
		}
	}
	
	@Test
	public void testKeysIterable(){
		LookupTable<Integer, Integer> um = new LookupTable<Integer, Integer>();
		
		
		
		
		
		for(int i = 0; i <= 10; i++){
			um.put(i, i);

		}
		
		if(um.remove(5) != 5)
			fail();
		um.put(5, 5);
		
		
		Iterator<Integer> it = um.keySet().iterator();
		int i = 0;
		
	
		
		
		
		int j;
		while (it.hasNext()) {
			j = it.next();
			if(j!=i)
				fail();
			i++;
		}
		
		Iterator<Integer> it2 = um.values().iterator();
		
		i = 0;
		while (it2.hasNext()) {
			
			j = it2.next();
			if(j != i)
				fail();
			i++;
		}
		
		Iterator<Entry<Integer, Integer>> it3 = um.entrySet().iterator();
		i = 0;
		while(it3.hasNext()){
			Entry<Integer, Integer> en = it3.next();
			if(en.getValue() != i || en.getKey() != i)
				fail();
			i++;
		}
		
	}

	@Test
	public void testEmptyMap(){
		LookupTable<Integer, Integer> um = new LookupTable<Integer, Integer>();
		
		if(um.size() != 0)
			fail();
		if(!um.isEmpty())
			fail();
		Iterator<Integer> it = um.keySet().iterator();
		if(it.hasNext())
			fail();
		it = um.values().iterator();
		if(it.hasNext())
			fail();
		
		if(um.remove(0) != null)
			fail();
		if(um.get(32582) != null)
			fail();
	}
	
	@Test
	public void testPutCollision(){
		LookupTable<Integer, Integer> um = new LookupTable<Integer, Integer>();
		
		for(int i = 0; i <= 10; i++){
			um.put(i, i);
		}
		
		if(um.put(5, 99) != 5)
			fail();
		if(um.put(5, 5) != 99)
			fail();
	}
	
	@Test
	public void testFloorCeilingLowHigh(){
		LookupTable<Integer, Integer> um = new LookupTable<Integer, Integer>();
		
		Iterator<Entry<Integer,Integer>> subIterator0 = um.subMap(0, 999).iterator();
		assert(!subIterator0.hasNext());
		
		for(int i = 0; i <= 100; i += 10){
			um.put(i, i);
		}
		
		if(um.ceilingEntry(19).getKey() != 20)
			fail();
		if(um.ceilingEntry(20).getKey() != 20)
			fail();
		
		assert(um.floorEntry(19).getKey() == 10);
		assert(um.floorEntry(20).getKey() == 20);
		
		assert(um.higherEntry(20).getKey() == 30);
		assert(um.lowerEntry(50).getKey() == 40);
		
		Iterator<Entry<Integer,Integer>> subIterator = um.subMap(10, 60).iterator();
		
		int i = 20;
		while(subIterator.hasNext()){
			Entry<Integer, Integer> n = subIterator.next();
			assert(n.getKey() == i);
			assert(n.getKey() < 60);
			assert(n.getKey() > 10);
			i += 10;
			
		}
			
	}
	
}
