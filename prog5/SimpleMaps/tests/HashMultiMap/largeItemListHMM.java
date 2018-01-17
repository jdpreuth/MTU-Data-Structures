import org.junit.*;
import jug.*;
import cs2321.*;
import net.datastructures.*;
import java.util.Random;

@jug.SuiteName("Large Item List: 1000 item Entry=<String, String> list")
public class largeItemListHMM {

	private HashMultiMap<String, String> TARGET = init();
	private HashMultiMap<String, String> T = init();

	public HashMultiMap<String, String> init() {
		return new HashMultiMap<String, String>();
	}

	@org.junit.Test(timeout=60000)
	@jug.TestName("Inserting entries <nextInt,i> where nextInt is a Random # with seed 123; i is # of values already inserted - Verifying find(K) for all elements.")
	public void Test1() throws Throwable {
		int [] keys = new int[1000];
		Random rnd = new Random(123);
		for(int i=0;i<keys.length;i++) {
				Integer v = rnd.nextInt();
				keys[i]=v;
				TARGET.put(""+v, ""+i);
			}
		for(int i=0;i<keys.length;i++)//;
		
		org.junit.Assert.assertEquals("Inserting entries <nextInt,i> where nextInt is a Random # with seed 123; i is # of values already inserted - Verifying find(K) for all elements.", (Object)((""+i)), (Object)(TARGET.get(""+keys[i]).iterator().next()));
	}

}
