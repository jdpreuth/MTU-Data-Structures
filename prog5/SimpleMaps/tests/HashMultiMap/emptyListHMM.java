import cs2321.HashMultiMap;

@jug.SuiteName("Empty multimap")
public class emptyListHMM {

	private HashMultiMap<String, String> TARGET = init();
	private HashMultiMap<String, String> T = init();

	public HashMultiMap<String, String> init() {
		return new HashMultiMap<String, String>();
	}

	@org.junit.Test(timeout = 60000)
	@jug.TestName("Verifying size() = 0")
	public void Test1() throws Throwable {

		org.junit.Assert.assertEquals("Verifying size() = 0", (Object) (0), (Object) (TARGET.size()));
	}

	@org.junit.Test()
	@jug.TestName("Verifying isEmpty() = true")
	public void Test2() throws Throwable {

		org.junit.Assert.assertEquals("Verifying isEmpty() = true", (Object) (true), (Object) (TARGET.isEmpty()));
	}

	@org.junit.Test()
	@jug.TestName("Verifying get(\"1\") returns empty iterable")
	public void Test3() throws Throwable {
		Iterable<String> e = TARGET.get("1");

		org.junit.Assert.assertEquals("Verifying get(\"1\") returns empty iterable", (Object) (false),
				(Object) (e.iterator().hasNext()));
	}

	@org.junit.Test()
	@jug.TestName("put(\"50\",\"A\"): Verifying size() = 1")
	public void Test4() throws Throwable {
		TARGET.put("50", "A");

		org.junit.Assert.assertEquals("put(\"50\",\"A\"): Verifying size() = 1", (Object) (1),
				(Object) (TARGET.size()));
	}

	@org.junit.Test()
	@jug.TestName("Entry put(\"50\",\"A\"): Verifying get(50) = \"A\"")
	public void Test5() throws Throwable {
		TARGET.put("50", "A");
		Iterable<String> e = TARGET.get("50");

		org.junit.Assert.assertEquals("Entry put(\"50\",\"A\"): Verifying get(50) = \"A\"", (Object) ("A"),
				(Object) (e.iterator().next()));
	}

	@org.junit.Test()
	@jug.TestName("put(\"50\",\"A\"); remove(\"50\", \"A\"): Verifying removed = true")
	public void Test6() throws Throwable {
		TARGET.put("50", "A");
		Boolean b = TARGET.remove("50", "A");

		org.junit.Assert.assertEquals("put(\"50\",\"A\"); remove(\"50\", \"A\"): Verifying removed = true",
				(Object) (true), (Object) (b));
	}

	@org.junit.Test()
	@jug.TestName("Entry e = put(\"50\",\"A\"); remove(\"50\", \"A\"): Verifying size() = 0")
	public void Test7() throws Throwable {
		TARGET.put("50", "A");
		TARGET.remove("50", "A");

		org.junit.Assert.assertEquals("Entry e = put(\"50\",\"A\"); remove(\"50\", \"A\"): Verifying size() = 0",
				(Object) (0), (Object) (TARGET.size()));
	}
}
