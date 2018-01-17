import cs2321.HashMap;
import net.datastructures.Entry;

public class HashMapTests {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap<Integer, Integer> hashMap = new HashMap<Integer, Integer>();
		hashMap.put(1, 1);
		hashMap.put(2, 2);
		hashMap.put(3, 3);
		hashMap.put(4, 4);
		hashMap.put(5, 5);
		hashMap.remove(3);

		Iterable<Entry<Integer, Integer>> itr = hashMap.entrySet();
		for (Entry<Integer, Integer> entry : itr) {
			System.out.print(entry.getValue() + ", ");
		}
	}

}
