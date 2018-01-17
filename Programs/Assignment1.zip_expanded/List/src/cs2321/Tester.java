package cs2321;

public class Tester {

	public static void main(String[] args) {
		DoublyLinkedList<Integer>[] list = (DoublyLinkedList<Integer>[]) new DoublyLinkedList[5000];
		for (int x = 0; x < 5000; x++) {
			DoublyLinkedList<Integer> arr = new DoublyLinkedList<Integer>();
			list[x] = arr;
		}
	}
}