import org.junit.*;
import jug.*;
import cs2321.*;
import net.datastructures.*;
import java.util.Random;

@jug.SuiteName("One Item List - Entries:{[\"1\",\"A\"]}")
public class oneItemListHMM {

	private HashMultiMap<String, String> TARGET = init();
	private HashMultiMap<String, String> T = init();

	public HashMultiMap<String, String> init() {
		return new HashMultiMap<String, String>();
	}

	@Before
	public void setup() throws Throwable {
		TARGET.put("1","A");
	}

	@org.junit.Test(timeout=60000)
	@jug.TestName("Verifying size() = 1")
	public void Test1() throws Throwable {
		
		org.junit.Assert.assertEquals("Verifying size() = 1", (Object)(1), (Object)(TARGET.size()));
	}

	@org.junit.Test()
	@jug.TestName("Verifying isEmpty() false")
	public void Test2() throws Throwable {
		
		org.junit.Assert.assertEquals("Verifying isEmpty() false", (Object)(false), (Object)(TARGET.isEmpty()));
	}

	@org.junit.Test()
	@jug.TestName("Verifying get(\"1\") = A")
	public void Test3() throws Throwable {
		
		org.junit.Assert.assertEquals("Verifying get(\"1\") = A", (Object)("A"), (Object)(TARGET.get("1").iterator().next()));
	}

	@org.junit.Test()
	@jug.TestName("Entries:{[1,A]}: Verifying get(\"1\") returns Values:{\"A\"}")
	public void Test4() throws Throwable {
		boolean found = false;
		for(String s : TARGET.get("1"))
				if(s.equals("A"))
					found = true;
		
		org.junit.Assert.assertEquals("Entries:{[1,A]}: Verifying get(\"1\") returns Values:{\"A\"}", (Object)(true), (Object)(found));
	}

	@org.junit.Test()
	@jug.TestName("Verifying insert(\"1\",\"B\"), get(\"1\") count = 2")
	public void Test5() throws Throwable {
		TARGET.put("1","B");
		int count = 0;
		for(String s: TARGET.get("1"))
				count++;
		
		org.junit.Assert.assertEquals("Verifying insert(\"1\",\"B\"), get(\"1\") count = 2", (Object)(2), (Object)(count));
	}

	@org.junit.Test()
	@jug.TestName("Verifying put(\"1\",\"B\"), get(\"1\") returns Values:{\"A\",\"B\"}")
	public void Test6() throws Throwable {
		TARGET.put("1","B");
		String [] values = {"A","B"}
		;
		Iterable<String> dictEntries = TARGET.get("1");
		boolean [] checked = new boolean[values.length];
		for(String e : dictEntries) {
				for(int j=0; j<values.length; j++) {
					if(values[j].equals(e)) {
						checked[j]=true;
					}
				}
			}
		for(int j=0; j<checked.length; j++)//;
		
		org.junit.Assert.assertEquals("Verifying put(\"1\",\"B\"), get(\"1\") returns Values:{\"A\",\"B\"}", (Object)(true), (Object)(checked[j]));
	}

	@org.junit.Test()
	@jug.TestName("Verifying put(\"1\",\"B\"); put(\"1\",\"C\"); remove(\"1\",\"B\"), get(\"1\") count = 2")
	public void Test7() throws Throwable {
		TARGET.put("1","B");
		TARGET.put("1","C");
		TARGET.remove("1","B");
		int count = 0;
		for(String e : TARGET.get("1"))
				count++;
		
		org.junit.Assert.assertEquals("Verifying put(\"1\",\"B\"); put(\"1\",\"C\"); remove(\"1\",\"B\"), get(\"1\") count = 2", (Object)(2), (Object)(count));
	}

	@org.junit.Test()
	@jug.TestName("Verifying put(\"1\",\"B\"); put(\"1\",\"C\"); removeAll(\"1\"), get(\"1\") count = 0")
	public void Test8() throws Throwable {
		TARGET.put("1","B");
		TARGET.put("1","C");
		TARGET.removeAll("1");
		int count = 0;
		for(String e : TARGET.get("1"))
				count++;
		
		org.junit.Assert.assertEquals("Verifying put(\"1\",\"B\"); put(\"1\",\"C\"); removeAll(\"1\"), get(\"1\") count = 0", (Object)(0), (Object)(count));
	}

}
