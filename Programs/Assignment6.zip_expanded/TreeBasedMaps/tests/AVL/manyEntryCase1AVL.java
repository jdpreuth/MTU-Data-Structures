import org.junit.*;
import jug.*;
import cs2321.*;
import net.datastructures.*;
import java.util.Random;
import java.util.Iterator;

@jug.SuiteName("many Entry put - page482")
public class manyEntryCase1AVL {

	private AVL<Integer,String> TARGET = init();
	private AVL<Integer,String> T = init();

	public AVL<Integer,String> init() {
		return new AVL<Integer,String>();
	}

	@Before
	public void setup() throws Throwable {
		TARGET.put(44,"A");
			TARGET.put(17,"B");
			TARGET.put(78,"B");
			TARGET.put(32,"C");
			TARGET.put(50,"C");
			TARGET.put(88,"C");
			TARGET.put(48,"C");
			TARGET.put(62,"C");
	}

	@org.junit.Test(timeout=10000)
	@jug.TestName("height check: root's height is 4")
	public void Test1() throws Throwable {
		
		org.junit.Assert.assertEquals("height check: root's height is 4", (Object)(4), (Object)(TARGET.height(TARGET.root())));
	}

	@org.junit.Test()
	@jug.TestName("height check: root's height is 4")
	public void Test2() throws Throwable {
		TARGET.put(54,"C");
		
		org.junit.Assert.assertEquals("height check: root's height is 4", (Object)(4), (Object)(TARGET.height(TARGET.root())));
	}

	@org.junit.Test()
	@jug.TestName("put 54 : root's right is 62")
	public void Test3() throws Throwable {
		TARGET.put(54,"C");
		
		org.junit.Assert.assertEquals("put 54 : root's right is 62", (Object)(62), (Object)(TARGET.right(TARGET.root()).getElement().getKey()));
	}

	@org.junit.Test()
	@jug.TestName("put 54 : root's right's left is 50")
	public void Test4() throws Throwable {
		TARGET.put(54,"C");
		
		org.junit.Assert.assertEquals("put 54 : root's right's left is 50", (Object)(50), (Object)(TARGET.left(TARGET.right(TARGET.root())).getElement().getKey()));
	}

	@org.junit.Test()
	@jug.TestName("put 54 : root's right's right is 78")
	public void Test5() throws Throwable {
		TARGET.put(54,"C");
		
		org.junit.Assert.assertEquals("put 54 : root's right's right is 78", (Object)(78), (Object)(TARGET.right(TARGET.right(TARGET.root())).getElement().getKey()));
	}

}
