import cs2321.LookupTable;
import net.datastructures.Entry;

public class SortedMapTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LookupTable<Integer, Integer> map = new LookupTable<Integer, Integer>();

		System.out.println("Size: " + map.size());
		map.put(1, 1);
		System.out.println("Size: " + map.size());
		map.put(2, 2);
		System.out.println("Size: " + map.size());
		map.put(3, 3);
		System.out.println("Size: " + map.size());
		map.put(4, 4);
		System.out.println("Size: " + map.size());
		map.put(6, 6);
		System.out.println("Size: " + map.size());
		map.remove(3);
		System.out.println("Size: " + map.size());

		Iterable<Entry<Integer, Integer>> itr = map.entrySet();
		System.out.println();

		for (Entry<Integer, Integer> entry : itr) {
			System.out.print(entry.getValue());
		}

	}

}
