package dev.nullpanic.customarraylist;

import java.util.*;


/**
 * Is a variant of a dynamically expanding array
 *
 * @param <E> - type of elements contained in list
 */
public class CustomArrayList<E> implements List<E> {

    private static final int DEFAULT_SIZE = 5;

    private static final int INCREMENTAL_ARRAY_RANGE = 5;

    /**
     * Current array size
     */
    public int size = 0;
    E[] array;


    /**
     * Creates an array with fix size
     *
     * @param size - array size
     */
    public CustomArrayList(int size) {
        if (size < 0) {
            throw new IllegalArgumentException("Size cannot be less than zero");
        }

        this.array = (E[]) new Object[size];
    }

    /**
     * Creates a default array with DEFAULT_SIZE size
     */
    public CustomArrayList() {
        this.array = (E[]) new Object[DEFAULT_SIZE];
    }

    /**
     * Replace element at the specified index
     *
     * @param index   index of the element to replace
     * @param element element to be stored at the specified position
     * @return set value
     */
    @Override
    public E set(int index, Object element) {
        array[index] = (E) element;
        return array[index];
    }

    /**
     * Add element to the end of the array
     *
     * @param o element whose presence in this collection is to be ensured
     * @return operation result
     */
    @Override
    public boolean add(Object o) {
        if (isFull()) {
            increaseArrayRange();
        }
        array[this.size++] = (E) o;
        return true;
    }

    /**
     * Add an element at the index position
     *
     * @param index   index at which the specified element is to be inserted
     * @param element element to be inserted
     */
    @Override
    public void add(int index, E element) {
        if (isFull()) {
            increaseArrayRange();
        }
        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        this.size++;
    }

    /**
     * Returns the element at the index
     *
     * @param index index of the element to return
     * @return the element at the index
     */
    @Override
    public E get(int index) {
        return array[index];
    }

    /**
     * Removes the element at the index
     *
     * @param index the index of the element to be removed
     * @return removed value
     */
    @Override
    public E remove(int index) {
        int copyCount = this.size - index - 1;
        E removedValue = (E) array[index];

        System.arraycopy(array, index + 1, array, index, copyCount);
        array[this.size - 1] = null;
        this.size--;

        return removedValue;
    }

    /**
     * Removes the element if exist
     *
     * @param o element to be removed from this list, if present
     * @return operation result
     */
    @Override
    public boolean remove(Object o) {
        for (int i = 0; i < array.length; i++) {
            if (o.equals(array[i])) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Deletes all elements
     */
    @Override
    public void clear() {
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
        size = 0;
    }

    /**
     * Sorts array by the comparator
     *
     * @param c the {@code Comparator} used to compare list elements.
     *          A {@code null} value indicates that the elements'
     *          {@linkplain Comparable natural ordering} should be used
     */
    @Override
    public void sort(Comparator<? super E> c) {
        Arrays.sort(array, 0, array.length - 1, c);
    }

    /**
     * Returns array size
     *
     * @return array size
     */
    @Override
    public int size() {
        return array.length;
    }

    @Override
    public boolean isEmpty() {
        return array.length > 0;
    }

    @Override
    public boolean contains(Object o) {
        return false;
    }

    @Override
    public Iterator iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public Object[] toArray(Object[] a) {
        return new Object[0];
    }


    @Override
    public boolean addAll(Collection c) {
        return false;
    }

    @Override
    public boolean addAll(int index, Collection c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection c) {
        return false;
    }

    @Override
    public boolean containsAll(Collection c) {
        return false;
    }


    @Override
    public int indexOf(Object o) {
        return 0;
    }

    @Override
    public int lastIndexOf(Object o) {
        return 0;
    }

    @Override
    public ListIterator listIterator() {
        return null;
    }

    @Override
    public ListIterator listIterator(int index) {
        return null;
    }

    @Override
    public List subList(int fromIndex, int toIndex) {
        return null;
    }

    public boolean isFull() {
        return array[array.length - 1] != null;
    }

    private void increaseArrayRange() {
        E[] tempArray = array;
        array = (E[]) new Object[array.length + INCREMENTAL_ARRAY_RANGE];
        System.arraycopy(tempArray, 0, array, 0, tempArray.length);
    }
}
