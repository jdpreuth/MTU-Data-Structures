import org.junit.*;
import jug.*;
import cs2321.*;
import net.datastructures.*;
import java.util.Random;

@jug.SuiteName("Multiple Item List - Entries:{[\"3\",\"C\"],[\"1\",\"A\"],[\"2\",\"B\"],[\"1\",\"D\"]}")
public class multipleItemListHMM {

	private HashMultiMap<String, String> TARGET = init();
	private HashMultiMap<String, String> T = init();

	public HashMultiMap<String, String> init() {
		return new HashMultiMap<String, String>();
	}

	@Before
	public void setup() throws Throwable {
		TARGET.put("3","C");
			TARGET.put("1","A");
			TARGET.put("2","B");
			TARGET.put("1","D");
	}

	@org.junit.Test(timeout=60000)
	@jug.TestName("Verifying keySet() contains Keys:{\"1\",\"2\",\"3\"}")
	public void Test1() throws Throwable {
		String [] keys = {"1","2","3"}
		;
		Iterable<String> keyList = TARGET.keySet();
		boolean [] checked = new boolean[keys.length];
		for(String e : keyList) {
				for(int j=0; j<keys.length; j++) {
					if(keys[j].equals(e) && checked[j]==false) {
						checked[j]=true;
					}
				}
			}
		for(int j=0; j<checked.length; j++)//;
		
		org.junit.Assert.assertEquals("Verifying keySet() contains Keys:{\"1\",\"2\",\"3\"}", (Object)(true), (Object)(checked[j]));
	}

	@org.junit.Test()
	@jug.TestName("Verifying values contains Values:{\"A\",\"B\", \"C\",\"D\"}")
	public void Test2() throws Throwable {
		String [] values = {"A","B","C","D"}
		;
		Iterable<String> valueList = TARGET.values();
		boolean [] checked = new boolean[values.length];
		for(String e : valueList) {
				for(int j=0; j<values.length; j++) {
					if(values[j].equals(e) && checked[j]==false) {
						checked[j]=true;
					}
				}
			}
		for(int j=0; j<checked.length; j++)//;
		
		org.junit.Assert.assertEquals("Verifying values contains Values:{\"A\",\"B\", \"C\",\"D\"}", (Object)(true), (Object)(checked[j]));
	}

	@org.junit.Test()
	@jug.TestName("Verifying remove(\"2\",\"B\"); values() count = 3")
	public void Test3() throws Throwable {
		TARGET.remove("2","B");
		int count = 0;
		for(String e : TARGET.values())
				count++;
		
		org.junit.Assert.assertEquals("Verifying remove(\"2\",\"B\"); values() count = 3", (Object)(3), (Object)(count));
	}

	@org.junit.Test()
	@jug.TestName("Verifying removeAll(\"1\"); values() count = 2")
	public void Test4() throws Throwable {
		TARGET.removeAll("1");
		int count = 0;
		for(String e : TARGET.values())
				count++;
		
		org.junit.Assert.assertEquals("Verifying removeAll(\"1\"); values() count = 2", (Object)(2), (Object)(count));
	}

	@org.junit.Test()
	@jug.TestName("Verifying remove(\"2\",\"B\"); keySet() contains entries with Keys:{\"1\",\"3\"}")
	public void Test5() throws Throwable {
		TARGET.remove("2", "B");
		String [] keys = {"1","3"}
		;
		Iterable<String> keyList = TARGET.keySet();
		boolean [] checked = new boolean[keys.length];
		for(String e : keyList) {
				for(int j=0; j<keys.length; j++) {
					if(keys[j].equals(e) && checked[j]==false) {
						checked[j]=true;
					}
				}
			}
		for(int j=0; j<checked.length; j++)//;
		
		org.junit.Assert.assertEquals("Verifying remove(\"2\",\"B\"); keySet() contains entries with Keys:{\"1\",\"3\"}", (Object)(true), (Object)(checked[j]));
	}

	@org.junit.Test()
	@jug.TestName("Verifying remove(\"2\"); values() contains entries with Values:{\"A\",\"C\",\"D\"}")
	public void Test6() throws Throwable {
		TARGET.remove("2","B");
		String [] values = {"A","C","D"}
		;
		Iterable<String> valueList = TARGET.values();
		boolean [] checked = new boolean[values.length];
		for(String e : valueList) {
				for(int j=0; j<values.length; j++) {
					if(values[j].equals(e) && checked[j]==false) {
						checked[j]=true;
					}
				}
			}
		for(int j=0; j<checked.length; j++)//;
		
		org.junit.Assert.assertEquals("Verifying remove(\"2\"); values() contains entries with Values:{\"A\",\"C\",\"D\"}", (Object)(true), (Object)(checked[j]));
	}

}
