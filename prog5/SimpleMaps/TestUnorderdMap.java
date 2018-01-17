package cs2321;

import static org.junit.Assert.*;

import java.util.Iterator;

import net.datastructures.Entry;

import org.junit.Test;

public class TestUnorderdMap {

	@Test
	public void testgetandput() {
		UnorderedMap<Integer, Integer> um = new UnorderedMap<Integer, Integer>();
		
		for(int i = 0; i <= 10; i++){
			um.put(i, i);
		}
		
		if(um.remove(99) != null)
			fail();
		
		int p = 0;
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
		UnorderedMap<Integer, Integer> um = new UnorderedMap<Integer, Integer>();
		
		for(int i = 0; i <= 10; i++){
			um.put(i, i);
		}
		
		Iterator<Integer> it = um.keySet().iterator();
		
		int i = 0;
		while (it.hasNext()) {
			it.next();
			
			i++;
		}
		boolean fail = false;
		try{
			if(it.next() != null)
				fail();
		}catch( Exception IllegalStateExeption ){
			fail = true;
		}
		if(!fail)
			fail();

		Iterator<Integer> it2 = um.values().iterator();
		
		i = 0;
		while (it2.hasNext()) {
			it2.next();
			i++;
		}
		
		Iterator<Entry<Integer, Integer>> it3 = um.entrySet().iterator();
		i = 0;
		while(it3.hasNext()){
			Entry<Integer, Integer> en = it3.next();
			i++;
		}
		
	}

	@Test
	public void testEmptyMap(){
		UnorderedMap<Integer, Integer> um = new UnorderedMap<Integer, Integer>();
		
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
		
		boolean fail = false;
		try{
			if(it.next() != null)
				fail();
		}catch( Exception IllegalStateExeption ){
			fail = true;
		}
		
		if(!fail)
			fail();
		
		if(um.remove(0) != null)
			fail();
		
		if(um.get(32582) != null);
	}
	
	@Test
	public void testPutCollision(){
		UnorderedMap<Integer, Integer> um = new UnorderedMap<Integer, Integer>();
		
		for(int i = 0; i <= 10; i++){
			um.put(i, i);
		}
		
		if(um.put(5, 99) != 5)
			fail();
		if(um.put(5, 5) != 99)
			fail();
		
	}
	
}
