package com.bobocode.cs;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * {@link ArrayList} is an implementation of {@link List} interface. This resizable data structure
 * based on an array and is simplified version of {@link java.util.ArrayList}.
 */
public class ArrayList<T> implements List<T> {

	private static final int DEFAULT_CAPACITY = 5;
	private Object[] elements;
	private int size;

	/**
	 * This constructor creates an instance of {@link ArrayList} with a specific capacity of an array inside.
	 *
	 * @param initCapacity - the initial capacity of the list
	 * @throws IllegalArgumentException – if the specified initial capacity is negative or 0.
	 */
	public ArrayList(int initCapacity) {
		if (initCapacity <= 0) {
			throw new IllegalArgumentException();
		}

		this.elements = new Object[initCapacity];
	}

	/**
	 * This constructor creates an instance of {@link ArrayList} with a default capacity of an array inside.
	 * A default size of inner array is 5;
	 */
	public ArrayList() {
		this(DEFAULT_CAPACITY);
	}

	/**
	 * Creates and returns an instance of {@link ArrayList} with provided elements
	 *
	 * @param elements to add
	 * @return new instance
	 */
	public static <T> List<T> of(T... elements) {
		ArrayList<T> list = new ArrayList<>(elements.length);
		Stream.of(elements).forEach(list::add);
		return list;
	}

	/**
	 * Adds an element to the array.
	 *
	 * @param element element to add
	 */
	@Override
	public void add(T element) {
		expandIfNeed();

		elements[size] = element;
		size++;
	}

	private void expandIfNeed() {
		if (size == elements.length) {
			elements = Arrays.copyOf(elements, size * 2);
		}
	}

	/**
	 * Adds an element to the specific position in the array where
	 *
	 * @param index   index of position
	 * @param element element to add
	 */
	@Override
	public void add(int index, T element) {
		expandIfNeed();

		System.arraycopy(elements, index, elements, index + 1, size - index);
		elements[index] = element;
		size++;
	}

	/**
	 * Retrieves an element by its position index. In case provided index in out of the list bounds it
	 * throws {@link IndexOutOfBoundsException}
	 *
	 * @param index index of element
	 * @return en element
	 */
	@Override
	public T get(int index) {
		Objects.checkIndex(index, size);

		return (T) elements[index];
	}

	/**
	 * Returns the first element of the list. Operation is performed in constant time O(1)
	 *
	 * @return the first element of the list
	 * @throws java.util.NoSuchElementException if list is empty
	 */
	@Override
	public T getFirst() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		return (T) elements[0];
	}

	/**
	 * Returns the last element of the list. Operation is performed in constant time O(1)
	 *
	 * @return the last element of the list
	 * @throws java.util.NoSuchElementException if list is empty
	 */
	@Override
	public T getLast() {
		if (isEmpty()) {
			throw new NoSuchElementException();
		}

		return (T) elements[size - 1];
	}

	/**
	 * Changes the value of array at specific position. In case provided index in out of the list bounds it
	 * throws {@link IndexOutOfBoundsException}
	 *
	 * @param index   position of value
	 * @param element a new value
	 */
	@Override
	public void set(int index, T element) {
		Objects.checkIndex(index, size);

		elements[index] = element;
	}

	/**
	 * Removes an elements by its position index. In case provided index in out of the list bounds it
	 * throws {@link IndexOutOfBoundsException}
	 *
	 * @param index element index
	 * @return deleted element
	 */
	@Override
	public T remove(int index) {
		Objects.checkIndex(index, size);

		final T element = (T) elements[index];
		System.arraycopy(elements, index + 1, elements, index, size - index - 1);
		size--;
		elements[size] = null;
		return element;
	}

	/**
	 * Checks for existing of a specific element in the list.
	 *
	 * @param element is element
	 * @return If element exists method returns true, otherwise it returns false
	 */
	@Override
	public boolean contains(T element) {
		return Arrays.asList(elements)
			.contains(element);
	}

	/**
	 * Checks if a list is empty
	 *
	 * @return {@code true} if list is empty, {@code false} otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * @return amount of saved elements
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Removes all list elements
	 */
	@Override
	public void clear() {
		elements = new Object[DEFAULT_CAPACITY];
		size = 0;
	}
}
