import org.junit.*;
import jug.*;
import cs2321.*;
import net.datastructures.*;
import java.util.Random;
import java.util.Iterator;

@jug.SuiteName("fourNodesLBT")
public class fourNodesLBT {

	private LinkedBinaryTree<String> TARGET = init();
	private LinkedBinaryTree<String> T = init();

	public LinkedBinaryTree<String> init() {
		return new LinkedBinaryTree<String>();
	}

	@Before
	public void setup() throws Throwable {
		Position<String> n1 = TARGET.addRoot("A");
			Position<String> n2 = TARGET.insertLeft(n1, "B");
			Position<String> n3 = TARGET.insertRight(n1, "C");
			Position<String> n4 = TARGET.insertLeft(n2, "D");
	}

	@org.junit.Test()
	@jug.TestName("size() is 4")
	public void Test1() throws Throwable {
		
		org.junit.Assert.assertEquals("size() is 4", (Object)(4), (Object)(TARGET.size()));
	}

	@org.junit.Test()
	@jug.TestName("isInternal(root) is true")
	public void Test2() throws Throwable {
		
		org.junit.Assert.assertEquals("isInternal(root) is true", (Object)(true), (Object)(TARGET.isInternal(TARGET.root())));
	}

	@org.junit.Test()
	@jug.TestName("isInternal(root.right) is false")
	public void Test3() throws Throwable {
		
		org.junit.Assert.assertEquals("isInternal(root.right) is false", (Object)(false), (Object)(TARGET.isInternal(TARGET.right(TARGET.root()))));
	}

	@org.junit.Test()
	@jug.TestName("isInternal(root.left) is true")
	public void Test4() throws Throwable {
		
		org.junit.Assert.assertEquals("isInternal(root.left) is true", (Object)(true), (Object)(TARGET.isInternal(TARGET.left(TARGET.root()))));
	}

	@org.junit.Test()
	@jug.TestName("insertLeft(root.right,\"E\")")
	public void Test5() throws Throwable {
		Position<String> n3 = TARGET.right(TARGET.root());
		Position<String> n5 = TARGET.insertLeft(n3, "E");
		
		org.junit.Assert.assertEquals("insertLeft(root.right,\"E\")", (Object)("E"), (Object)(TARGET.left(n3).getElement()));
	}

	@org.junit.Test()
	@jug.TestName("insertLeft(root.left,\"E\") throws exception")
	public void Test6() throws Throwable {
		Position<String> n3 = TARGET.left(TARGET.root());
		
		{ boolean thrown = false;
			try {
				TARGET.insertLeft(n3, "E");
			} catch (Throwable t) {
				thrown = true;
				org.junit.Assert.assertThat("insertLeft(root.left,\"E\") throws exception", t, org.hamcrest.CoreMatchers.instanceOf(IllegalArgumentException.class));
			}
			if(!thrown){
				org.junit.Assert.fail("insertLeft(root.left,\"E\") throws exception: Expected Throwable IllegalArgumentException");
			}
		}
	}

	@org.junit.Test()
	@jug.TestName("insertRight(root.right,\"E\")")
	public void Test7() throws Throwable {
		Position<String> n3 = TARGET.right(TARGET.root());
		Position<String> n5 = TARGET.insertRight(n3, "E");
		
		org.junit.Assert.assertEquals("insertRight(root.right,\"E\")", (Object)("E"), (Object)(TARGET.right(n3).getElement()));
	}

	@org.junit.Test()
	@jug.TestName("insertRight(root,\"E\") throws exception")
	public void Test8() throws Throwable {
		
		{ boolean thrown = false;
			try {
				TARGET.insertRight(TARGET.root(), "E");
			} catch (Throwable t) {
				thrown = true;
				org.junit.Assert.assertThat("insertRight(root,\"E\") throws exception", t, org.hamcrest.CoreMatchers.instanceOf(IllegalArgumentException.class));
			}
			if(!thrown){
				org.junit.Assert.fail("insertRight(root,\"E\") throws exception: Expected Throwable IllegalArgumentException");
			}
		}
	}

	@org.junit.Test()
	@jug.TestName("TARGET.remove(TARGET.root()) throws IllegalArgumentException;")
	public void Test9() throws Throwable {
		
		{ boolean thrown = false;
			try {
				TARGET.remove(TARGET.root());
			} catch (Throwable t) {
				thrown = true;
				org.junit.Assert.assertThat("TARGET.remove(TARGET.root()) throws IllegalArgumentException;", t, org.hamcrest.CoreMatchers.instanceOf(IllegalArgumentException.class));
			}
			if(!thrown){
				org.junit.Assert.fail("TARGET.remove(TARGET.root()) throws IllegalArgumentException;: Expected Throwable IllegalArgumentException");
			}
		}
	}

	@org.junit.Test()
	@jug.TestName("TARGET.right(TARGET.root()) equals null;")
	public void Test10() throws Throwable {
		Position<String> n3 = TARGET.right(TARGET.root());
		TARGET.remove(n3);
		
		org.junit.Assert.assertEquals("TARGET.right(TARGET.root()) equals null;", (Object)(null), (Object)(TARGET.right(TARGET.root())));
	}

	@org.junit.Test()
	@jug.TestName("TARGET.left(TARGET.root()) equals n4;")
	public void Test11() throws Throwable {
		Position<String> n2 = TARGET.left(TARGET.root());
		Position<String> n4 = TARGET.left(TARGET.left(TARGET.root()));
		TARGET.remove(n2);
		
		org.junit.Assert.assertEquals("TARGET.left(TARGET.root()) equals n4;", (Object)(n4), (Object)(TARGET.left(TARGET.root())));
	}

	@org.junit.Test()
	@jug.TestName("TARGET.size() equals 3;")
	public void Test12() throws Throwable {
		Position<String> n4 = TARGET.left(TARGET.left(TARGET.root()));
		TARGET.remove(n4);
		
		org.junit.Assert.assertEquals("TARGET.size() equals 3;", (Object)(3), (Object)(TARGET.size()));
	}

	@org.junit.Test()
	@jug.TestName("parent(\"A\") is null")
	public void Test13() throws Throwable {
		
		org.junit.Assert.assertEquals("parent(\"A\") is null", (Object)(null), (Object)(TARGET.parent(TARGET.root())));
	}

	@org.junit.Test()
	@jug.TestName("parent(\"B\") is \"A\"")
	public void Test14() throws Throwable {
		
		org.junit.Assert.assertEquals("parent(\"B\") is \"A\"", (Object)(TARGET.root()), (Object)(TARGET.parent(TARGET.left(TARGET.root()))));
	}

	@org.junit.Test()
	@jug.TestName("parent(\"C\") is \"A\"")
	public void Test15() throws Throwable {
		
		org.junit.Assert.assertEquals("parent(\"C\") is \"A\"", (Object)(TARGET.root()), (Object)(TARGET.parent(TARGET.right(TARGET.root()))));
	}

	@org.junit.Test()
	@jug.TestName("parent(\"D\") is \"B\"")
	public void Test16() throws Throwable {
		
		org.junit.Assert.assertEquals("parent(\"D\") is \"B\"", (Object)("B"), (Object)(TARGET.parent(TARGET.left(TARGET.left(TARGET.root()))).getElement()));
	}

}
