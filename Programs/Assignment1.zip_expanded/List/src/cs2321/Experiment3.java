package cs2321;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Experiment3 {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		double start, stop;
		ArrayList<Integer>[] array = (ArrayList<Integer>[]) new ArrayList[5000];
		PrintWriter output = new PrintWriter("experiment3.txt");

		for (int i = 0; i < 200; i++) {

			for (int x = 0; x < 5000; x++) {
				ArrayList<Integer> arr = new ArrayList<Integer>();
				array[x] = arr;
			}

			start = System.nanoTime();
			for (int j = 0; j < 5000; j++) {
				for (int k = 0; k < i; k++) {
					array[j].addLast(k);
				}
			}
			stop = System.nanoTime();
			output.println((stop - start) / 5000 + "\t");
		}

		output.println("\t");
		output.println("------------------------- \t");
		output.println("\t");

		DoublyLinkedList<Integer>[] list = (DoublyLinkedList<Integer>[]) new DoublyLinkedList[5000];

		for (int i = 0; i < 200; i++) {

			for (int x = 0; x < 5000; x++) {
				DoublyLinkedList<Integer> arr = new DoublyLinkedList<Integer>();
				list[x] = arr;
			}

			start = System.nanoTime();
			for (int j = 0; j < 5000; j++) {
				for (int k = 0; k < i; k++) {
					list[j].addLast(k);
				}
			}
			stop = System.nanoTime();
			output.println((stop - start) / 5000 + "\t");
		}
		output.close();
	}

}
