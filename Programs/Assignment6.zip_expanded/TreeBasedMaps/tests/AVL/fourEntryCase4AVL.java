import org.junit.*;
import jug.*;
import cs2321.*;
import net.datastructures.*;
import java.util.Random;
import java.util.Iterator;

@jug.SuiteName("four Entry case4 - 10 / 5 \\ 8 - 20")
public class fourEntryCase4AVL {

	private AVL<Integer,String> TARGET = init();
	private AVL<Integer,String> T = init();

	public AVL<Integer,String> init() {
		return new AVL<Integer,String>();
	}

	@Before
	public void setup() throws Throwable {
		TARGET.put(10,"A");
			TARGET.put(5,"B");
			TARGET.put(20,"B");
			TARGET.put(8,"C");
			TARGET.remove(20);
	}

	@org.junit.Test(timeout=10000)
	@jug.TestName("put 10, 5, 8 : root is 8")
	public void Test1() throws Throwable {
		
		org.junit.Assert.assertEquals("put 10, 5, 8 : root is 8", (Object)(8), (Object)(TARGET.root().getElement().getKey()));
	}

	@org.junit.Test()
	@jug.TestName("ut 10, 5, 8 : root.left is 5")
	public void Test2() throws Throwable {
		
		org.junit.Assert.assertEquals("ut 10, 5, 8 : root.left is 5", (Object)(5), (Object)(TARGET.left(TARGET.root()).getElement().getKey()));
	}

	@org.junit.Test()
	@jug.TestName("ut 10, 5, 8 : root.right is 10")
	public void Test3() throws Throwable {
		
		org.junit.Assert.assertEquals("ut 10, 5, 8 : root.right is 10", (Object)(10), (Object)(TARGET.right(TARGET.root()).getElement().getKey()));
	}

}
