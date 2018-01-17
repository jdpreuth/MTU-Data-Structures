import org.junit.Before;

import cs2321.AVL;

@jug.SuiteName("three Entry case1 - 10 \\ 15 \\ 20")
public class threeEntryCase1AVL {

	private AVL<Integer, String> TARGET = init();
	private AVL<Integer, String> T = init();

	public AVL<Integer, String> init() {
		return new AVL<Integer, String>();
	}

	@Before
	public void setup() throws Throwable {
		TARGET.put(10, "A");
		TARGET.put(15, "B");
		TARGET.put(20, "C");
	}

	@org.junit.Test(timeout = 10000)
	@jug.TestName("put 10, 15, 20 : root is 15")
	public void Test1() throws Throwable {

		org.junit.Assert.assertEquals("put 10, 15, 20 : root is 15", (Object) (15),
				(Object) (TARGET.root().getElement().getKey()));
	}

	@org.junit.Test()
	@jug.TestName("put 10, 15, 20 : root.left is 10")
	public void Test2() throws Throwable {

		org.junit.Assert.assertEquals("put 10, 15, 20 : root.left is 10", (Object) (10),
				(Object) (TARGET.left(TARGET.root()).getElement().getKey()));
	}

	@org.junit.Test()
	@jug.TestName("put 10, 15, 20 : root.right is 20")
	public void Test3() throws Throwable {

		org.junit.Assert.assertEquals("put 10, 15, 20 : root.right is 20", (Object) (20),
				(Object) (TARGET.right(TARGET.root()).getElement().getKey()));
	}

}
