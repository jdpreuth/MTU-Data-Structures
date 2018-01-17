package cs2321;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

import net.datastructures.Position;

public class Experiment1 {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		double start, stop;
		ArrayList<Integer>[] array = (ArrayList<Integer>[]) new ArrayList[5000];
		PrintWriter output = new PrintWriter("experiment1.txt");

		for (int i = 0; i < 5000; i++) {
			ArrayList<Integer> arr = new ArrayList<Integer>();
			for (int j = 0; j < 100; j++) {
				arr.addLast(j);
			}
			array[i] = arr;
		}

		for (int i = 0; i < 100; i++) {
			start = System.nanoTime();
			for (int j = 0; j < 5000; j++) {
				array[j].get(i);
			}
			stop = System.nanoTime();
			output.println((stop - start) / 5000 + "\t");
		}

		output.println("\t");
		output.println("------------------------- \t");
		output.println("\t");

		DoublyLinkedList<Integer>[] array2 = (DoublyLinkedList<Integer>[]) new DoublyLinkedList[5000];

		for (int i = 0; i < 5000; i++) {
			DoublyLinkedList<Integer> arr = new DoublyLinkedList<Integer>();
			for (int j = 0; j < 100; j++) {
				arr.addLast(j);
			}
			array2[i] = arr;
		}

		Position<Integer> node;
		for (int i = 0; i < 100; i++) {
			start = System.nanoTime();
			for (int j = 0; j < 5000; j++) {
				node = array2[j].first();
				for (int k = 0; k < i; k++) {
					node = array2[j].after(node);
				}
				array2[j].after(node);
			}
			stop = System.nanoTime();
			output.println((stop - start) / 5000 + "\t");
		}
		output.close();
	}

}
