import org.junit.*;
import jug.*;
import cs2321.*;
import net.datastructures.*;
import java.util.Random;
import java.util.Iterator;

@jug.SuiteName("four Entry case2 - 10 \\ 15 / 12 - 5")
public class fourEntryCase2AVL {

	private AVL<Integer,String> TARGET = init();
	private AVL<Integer,String> T = init();

	public AVL<Integer,String> init() {
		return new AVL<Integer,String>();
	}

	@Before
	public void setup() throws Throwable {
		TARGET.put(10,"A");
			TARGET.put(15,"B");
			TARGET.put(5,"D");
			TARGET.put(12,"C");
			TARGET.remove(5);
	}

	@org.junit.Test(timeout=10000)
	@jug.TestName("put 10, 15, 12 : root is 12")
	public void Test1() throws Throwable {
		
		org.junit.Assert.assertEquals("put 10, 15, 12 : root is 12", (Object)(12), (Object)(TARGET.root().getElement().getKey()));
	}

	@org.junit.Test()
	@jug.TestName("put 10, 15, 12 : root.left is 10")
	public void Test2() throws Throwable {
		
		org.junit.Assert.assertEquals("put 10, 15, 12 : root.left is 10", (Object)(10), (Object)(TARGET.left(TARGET.root()).getElement().getKey()));
	}

	@org.junit.Test()
	@jug.TestName("put 10, 15, 12 : root.right is 15")
	public void Test3() throws Throwable {
		
		org.junit.Assert.assertEquals("put 10, 15, 12 : root.right is 15", (Object)(15), (Object)(TARGET.right(TARGET.root()).getElement().getKey()));
	}

}
