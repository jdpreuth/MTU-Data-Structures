/**
 * Implements a list by using an array 
 * 
 * @author 	Jon Preuth
 * @date	1/24/17 
 * @param <E> - Formal Type
 */

package cs2321;

import java.util.Iterator;

import net.datastructures.List;

public class ArrayList<E> implements List<E> {

	private int size = 0; // Integer that represents the current number of
							// elements
	private int capacity = 100; // Integer to determine the array capacity.
								// Arbitrarily initialized at 100
	private E[] list = (E[]) new Object[100]; // The array that will be used to
												// implement the ArrayList

	/**
	 * Constructor to declare a new ArrayList
	 */
	public ArrayList() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * Returns the number of elements in the list.
	 * 
	 * @return number of elements in the list
	 */
	@TimeComplexity("O(1)")
	public int size() {
		/*
		 * TCJ: Returns a global value which has a cost of 1 and only executes
		 * once so it's worst case O(1)
		 */
		// TODO Auto-generated method stub
		return this.size;
	}

	/**
	 * Tests whether the list is empty.
	 * 
	 * @return true if the list is empty, false otherwise
	 */
	@TimeComplexity("O(1)")
	public boolean isEmpty() {
		/*
		 * TCJ: Determines if size is equal to 0 and returns true or false. This
		 * has a cost of 2 and only runs once so it's worse case O(1)
		 */
		// TODO Auto-generated method stub
		return (this.size == 0);
	}

	/**
	 * Returns (but does not remove) the element at index i.
	 * 
	 * @param i
	 *            the index of the element to return
	 * @return the element at the specified index
	 * @throws IndexOutOfBoundsException
	 *             if the index is negative or greater than size()-1
	 */
	@TimeComplexity("O(1)")
	public E get(int i) throws IndexOutOfBoundsException {
		/*
		 * TCJ: Indexes into an array and returns a value which has a cost of 1
		 * and only executes once making it worse case O(1)
		 */
		// TODO Auto-generated method stub
		if (i < 0 || i > this.size) {
			throw new IndexOutOfBoundsException();
		}
		return list[i];

	}

	/**
	 * Replaces the element at the specified index, and returns the element
	 * previously stored.
	 * 
	 * @param i
	 *            the index of the element to replace
	 * @param e
	 *            the new element to be stored
	 * @return the previously stored element
	 * @throws IndexOutOfBoundsException
	 *             if the index is negative or greater than size()-1
	 */
	@TimeComplexity("O(1)")
	public E set(int i, E e) throws IndexOutOfBoundsException {
		/*
		 * TCJ: Indexes into an array and storing it in a variable all happen
		 * with a cost of one and only execut once making it worse case O(1)
		 */
		// TODO Auto-generated method stub
		if (i < 0 || i >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		E value = list[i];
		list[i] = e;
		return value;
	}

	/**
	 * Inserts the given element at the specified index of the list, shifting
	 * all subsequent elements in the list one position further to make room.
	 * 
	 * @param i
	 *            the index at which the new element should be stored
	 * @param e
	 *            the new element to be stored
	 * @throws IndexOutOfBoundsException
	 *             if the index is negative or greater than size()
	 */
	@TimeComplexity("O(n)")
	public void add(int i, E e) throws IndexOutOfBoundsException {
		/*
		 * TCJ: All the elements starting at i+1 to n-1 have to be shifted to
		 * the right. The number of elements being shifted is n-i. At the worse
		 * case i=0 and there are n shifts. The worse case is then O(n)
		 */
		// TODO Auto-generated method stub
		if (i < 0 || i > this.size) {
			throw new IndexOutOfBoundsException();
		} else if (size == capacity) {
			resize();
		}
		for (int j = size - 1; j >= i; j--) {
			list[j + 1] = list[j];
		}
		list[i] = e;
		this.size++;

	}

	/**
	 * Removes and returns the element at the given index, shifting all
	 * subsequent elements in the list one position closer to the front.
	 * 
	 * @param i
	 *            the index of the element to be removed
	 * @return the element that had be stored at the given index
	 * @throws IndexOutOfBoundsException
	 *             if the index is negative or greater than size()
	 */
	@TimeComplexity("O(n)")
	public E remove(int i) throws IndexOutOfBoundsException {
		/*
		 * TCJ: All the elements starting at i+1 to n have to be shifted to the
		 * right. The number of elements being shifted is n-i. At the worse case
		 * i=0 and there are n shifts. The worse case is then O(n)
		 */
		// TODO Auto-generated method stub
		if (i < 0 || i >= this.size) {
			throw new IndexOutOfBoundsException();
		}
		E value = list[i];
		for (int j = i; j < size - 1; j++) {
			list[j] = list[j + 1];
		}
		list[size - 1] = null;
		size--;
		return value;
	}

	/**
	 * Returns an iterator of the elements stored in the list.
	 * 
	 * @return iterator of the list's elements
	 */
	@TimeComplexity("O(1)")
	public Iterator<E> iterator() {
		/*
		 * TCJ: Declares an Iterator object which happens in constant times
		 * making the worse case O(1)
		 */
		// TODO Auto-generated method stub
		Iterator<E> itr = new Iterator<E>() {

			private int index = 0; // The current index the iterator is at

			/**
			 * Determines if there is another value in the arraylist
			 * 
			 * @return true if there is another element, false otherwise
			 */
			public boolean hasNext() {
				// TODO Auto-generated method stub
				boolean test = false;
				if (index <= size - 1 && list[index] != null) {
					test = true;
				}
				return test;
			}

			/**
			 * Increments the index and returns the next element in the array
			 * 
			 * @returns the next element in the array
			 */
			public E next() {
				// TODO Auto-generated method stub
				E value = list[index];
				index++;
				return value;
			}

		};
		return itr;

	}

	/**
	 * Adds an element at the front of the ArrayList and shifts all other
	 * elements to the right
	 * 
	 * @param e
	 *            the element to be added
	 * @throws IndexOutOfBoundsException
	 */
	@TimeComplexity("O(n)")
	public void addFirst(E e) throws IndexOutOfBoundsException {
		/*
		 * TCJ: Calls the add method with its worse case where i=0 which means
		 * this is always a O(n) operation
		 */
		// TODO Auto-generated method stub
		add(0, e);
	}

	/**
	 * Adds and element at the end of the ArrayList
	 * 
	 * @param e
	 *            the element to be added
	 * @throws IndexOutOfBoundsException
	 */
	@TimeComplexity("O(1)")
	public void addLast(E e) throws IndexOutOfBoundsException {
		/**
		 * Setting the empty space at the end of the array and incrementing size
		 * are both operations that execute only once with a cost of 1 making
		 * this worse case O(1)
		 */
		// TODO Auto-generated method stub
		if (size == capacity) {
			resize();
		}
		list[size] = e;
		size++;
	}

	/**
	 * Removes the first element in the list and shifts all other elements to
	 * the left
	 * 
	 * @return the removed element
	 * @throws IndexOutOfBoundsExceptionS
	 */
	@TimeComplexity("O(n)")
	public E removeFirst() throws IndexOutOfBoundsException {
		/*
		 * TCJ: Calls the remove method with its worse case where i=0w hich
		 * means this is always a O(n) operation
		 */
		// TODO Auto-generated method stub
		return remove(0);
	}

	/**
	 * Removes the last element in the list
	 * 
	 * @return the removed element
	 * @throws IndexOutOfBoundsException
	 */
	@TimeComplexity("O(1)")
	public E removeLast() throws IndexOutOfBoundsException {
		/*
		 * TCJ: Returning and setting the last element to null and decrementing
		 * size are constant time operation which makes this worse case O(1)
		 */
		// TODO Auto-generated method stub
		E value = list[size - 1];
		list[size - 1] = null;
		size--;
		return value;
	}

	/**
	 * Dynamically resizes the array by coping all the elements into a new array
	 * of twice the capacity
	 */
	@TimeComplexity("O(n)")
	private void resize() {
		/*
		 * TCJ: This method declares a new array with doubles the original
		 * array's capacity and then has to copy all n elements over which makes
		 * the operation always run in linear time which means its worse case is
		 * O(n)
		 */
		int newCapacity = 2 * this.capacity;
		E[] newList = (E[]) new Object[newCapacity];
		for (int i = 0; i < list.length; i++) {
			newList[i] = list[i];
		}
		list = newList;
		this.capacity = newCapacity;
	}

}
