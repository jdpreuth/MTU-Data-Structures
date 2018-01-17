
/**
 * Implement Queue ADT using a fixed-length array in circular fashion 
 *
 * @author 		Jon Preuth
 * @date		1/24/17
 * @param <E> - formal type 
 */

package cs2321;

import net.datastructures.Queue;

public class CircularArrayQueue<E> implements Queue<E> {

	private E[] queue; // The array used to implement the queue
	private int size = 0; // An integer to maintain the number of elements in
							// the queue
	private int front = 0; // An integer to maintain the index of the front of
							// the queue
	private int capacity; // An integer to maintain the max capacity of the
							// queue

	/**
	 * Constructor to declare a new CircularArrayQueue and set the queue's
	 * capacity
	 * 
	 * @param queueSize
	 *            the maximum number of elements that the queue can store
	 */
	@TimeComplexity("O(1)")
	public CircularArrayQueue(int queueSize) {
		/*
		 * TCJ: Setting variables are cost 1 so the worst case is O(n)
		 */
		// TODO Auto-generated constructor stub
		capacity = queueSize;
		queue = (E[]) new Object[capacity];
	}

	/**
	 * Returns the number of elements in the queue.
	 * 
	 * @return number of elements in the queue
	 */
	@TimeComplexity("O(1)")
	public int size() {
		/*
		 * TCJ: Returning a variable happens at cost 1 so the worst case is O(n)
		 */
		// TODO Auto-generated method stub
		return size;
	}

	/**
	 * Tests whether the queue is empty.
	 * 
	 * @return true if the queue is empty, false otherwise
	 */
	@TimeComplexity("O(1)")
	public boolean isEmpty() {
		/*
		 * TCJ: Testing to see if a condition is true and returning the result
		 * is a cost 1 operation which means the worst case is O(1)
		 */
		// TODO Auto-generated method stub
		return size == 0;
	}

	/* Throw IllegalStateException when the queue is full */
	/**
	 * Inserts an element at the rear of the queue.
	 * 
	 * @param e
	 *            the element to be inserted
	 * @throws IllegalStateException
	 *             if trying to enqueue into a full queue
	 */
	@TimeComplexity("O(1)")
	public void enqueue(E e) throws IllegalStateException {
		/*
		 * Calculating the index and adding something to the array are cost 1
		 * opertions so the worst case is O(1)
		 */
		// TODO Auto-generated method stub
		if (size == capacity) {
			throw new IllegalStateException();
		}
		int index = (size + front) % capacity;
		queue[index] = e;
		size++;
	}

	/**
	 * Returns, but does not remove, the first element of the queue.
	 * 
	 * @return the first element of the queue (or null if empty)
	 */
	@TimeComplexity("O(1)")
	public E first() {
		/*
		 * TCJ: The cost of returning a value is 1 so this is worst case O(1)
		 */
		// TODO Auto-generated method stub
		if (size == 0) {
			return null;
		}
		return queue[front];
	}

	/**
	 * Removes and returns the first element of the queue.
	 * 
	 * @return element removed (or null if empty)
	 */
	@TimeComplexity("O(1)")
	public E dequeue() {
		/*
		 * TCJ: Setting the element to null, decrementing size, and moving the
		 * front are Cost 1 operations so the worst case O(1)
		 */
		// TODO Auto-generated method stub
		E element = queue[front];
		queue[front] = null;
		front = (front + 1) % capacity;
		size--;
		return element;
	}

}
