package vandy.mooc.functional;

import java.util.*;
import java.util.function.*;

/**
 * An array implementation that supports dynamic resizing and various operations.
 *
 * @param <E> the type of elements in this array
 */
public class Array<E> {

    // Default initial capacity
    private static final int DEFAULT_CAPACITY = 10;

    // Internal array to hold elements
    Object[] mElementData;

    // Number of elements in the array
    private int mSize;

    /**
     * Constructs an empty array with an initial capacity of ten.
     */
    public Array() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs an empty array with the specified initial capacity.
     *
     * @param initialCapacity the initial capacity of the array
     * @throws IllegalArgumentException if the specified initial capacity is negative
     */
    public Array(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        mElementData = new Object[initialCapacity];
    }

    /**
     * Returns the number of elements in this array.
     *
     * @return the number of elements in this array
     */
    public int size() {
        return mSize;
    }

    /**
     * Returns true if this array contains no elements.
     *
     * @return true if this array contains no elements
     */
    public boolean isEmpty() {
        return mSize == 0;
    }

    /**
     * Adds the specified element to the end of this array.
     *
     * @param element the element to add
     * @return true (as specified by Collection.add)
     */
    public boolean add(E element) {
        ensureCapacityInternal(mSize + 1);  // Ensure capacity
        mElementData[mSize++] = element;    // Add element
        return true;
    }

    /**
     * Adds all of the elements in the specified collection to this array.
     *
     * @param c collection containing elements to be added to this array
     * @return true if this array changed as a result of the call
     */
    public boolean addAll(Collection<? extends E> c) {
        Object[] a = c.toArray();
        int numNew = a.length;
        ensureCapacityInternal(mSize + numNew);  // Ensure capacity
        System.arraycopy(a, 0, mElementData, mSize, numNew);
        mSize += numNew;
        return numNew != 0;
    }

    /**
     * Adds all of the elements in the specified array to this array.
     *
     * @param a array containing elements to be added to this array
     * @return true if this array changed as a result of the call
     */
    public boolean addAll(Array<? extends E> a) {
        int numNew = a.size();
        ensureCapacityInternal(mSize + numNew);  // Ensure capacity
        System.arraycopy(a.mElementData, 0, mElementData, mSize, numNew);
        mSize += numNew;
        return numNew != 0;
    }

    /**
     * Removes all of the elements from this array. The array will be empty after this call returns.
     */
    public void clear() {
        // Let GC do its work
        Arrays.fill(mElementData, null);
        mSize = 0;
    }

    /**
     * Returns true if this array contains the specified element.
     *
     * @param o element whose presence in this array is to be tested
     * @return true if this array contains the specified element
     */
    public boolean contains(Object o) {
        return indexOf(o) >= 0;
    }

    /**
     * Returns the index of the first occurrence of the specified element in this array, or -1 if
     * this array does not contain the element. More formally, returns the lowest index i such that
     * Objects.equals(o, elementData[i]), or -1 if there is no such index.
     *
     * @param o element to search for
     * @return the index of the first occurrence of the specified element in this array, or -1 if
     * this array does not contain the element
     */
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < mSize; i++) {
                if (mElementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < mSize; i++) {
                if (o.equals(mElementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * Removes the element at the specified position in this array.
     *
     * @param index the index of the element to be removed
     * @return the element that was removed from the array
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public E remove(int index) {
        rangeCheck(index);
        E oldValue = elementData(index);
        int numMoved = mSize - index - 1;
        if (numMoved > 0) {
            System.arraycopy(mElementData, index + 1, mElementData, index, numMoved);
        }
        mElementData[--mSize] = null; // clear to let GC do its work
        return oldValue;
    }

    /**
     * Returns the element at the specified position in this array.
     *
     * @param index the index of the element to return
     * @return the element at the specified position in this array
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public E get(int index) {
        rangeCheck(index);
        return elementData(index);
    }

    /**
     * Replaces the element at the specified position in this array with the specified element.
     *
     * @param index   the index of the element to replace
     * @param element the element to be stored at the specified position
     * @return the element previously at the specified position
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public E set(int index, E element) {
        rangeCheck(index);
        E oldValue = elementData(index);
        mElementData[index] = element;
        return oldValue;
    }

    /**
     * Replaces each element of this array with the result of applying the operator to that element.
     *
     * @param operator the operator to apply to each element
     */
    public void replaceAll(UnaryOperator<E> operator) {
        Objects.requireNonNull(operator);
        for (int i = 0; i < mSize; i++) {
            mElementData[i] = operator.apply(elementData(i));
        }
    }

    /**
     * Returns an array containing all of the elements in this array in proper sequence (from first
     * to last element). The returned array will be "safe" in that no references to it are
     * maintained by this array.
     *
     * @return an array containing all of the elements in this array
     */
    public Object[] toArray() {
        return Arrays.copyOf(mElementData, mSize);
    }

    /**
     * Returns an array containing all of the elements in this array in proper sequence (from first
     * to last element); the runtime type of the returned array is that of the specified array.
     * If the array fits in the specified array, it is returned therein. Otherwise, a new array is
     * allocated with the runtime type of the specified array and the size of this array.
     *
     * @param <T>   the runtime type of the array to contain the collection
     * @param a     the array into which the elements of this array are to be stored, if it is big
     *              enough; otherwise, a new array of the same runtime type is allocated for this
     *              purpose
     * @return an array containing the elements of this array
     * @throws ArrayStoreException  if the runtime type of the specified array is not a supertype
     *                              of the runtime type of every element in this array
     * @throws NullPointerException if the specified array is null
     */
    @SuppressWarnings("unchecked")
    public <T> T[] toArray(T[] a) {
        if (a.length < mSize)
            return (T[]) Arrays.copyOf(mElementData, mSize, a.getClass());
        System.arraycopy(mElementData, 0, a, 0, mSize);
        if (a.length > mSize)
            a[mSize] = null;
        return a;
    }

    /**
     * Returns an array containing all of the elements in this array.
     *
     * @return an array containing all of the elements in this array
     */
    Object[] uncheckedToArray() {
        return mElementData;
    }

    /**
     * Ensures that the array capacity can accommodate at least the specified minimum capacity.
     *
     * @param minCapacity the desired minimum capacity
     */
    private void ensureCapacityInternal(int minCapacity) {
        if (minCapacity - mElementData.length > 0)
            grow(minCapacity);
    }

    /**
     * Increases the capacity of the array, if necessary, to ensure that it can accommodate at least
     * the specified minimum number of elements.
     *
     * @param minCapacity the desired minimum capacity
     */
    private void grow(int minCapacity) {
        int oldCapacity = mElementData.length;
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        if (newCapacity - minCapacity < 0)
            newCapacity = minCapacity;
        if (newCapacity - Integer.MAX_VALUE > 0)
            newCapacity = hugeCapacity(minCapacity);
        mElementData = Arrays.copyOf(mElementData, newCapacity);
    }

    /**
     * Returns a capacity at least as large as the given minimum capacity.
     *
     * @param minCapacity the desired minimum capacity
     * @return the result of the maximum of the given minimum capacity and the maximum array size
     */
    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0)
            throw new OutOfMemoryError();
        return (minCapacity > Integer.MAX_VALUE) ? Integer.MAX_VALUE : minCapacity;
    }

    /**
     * Checks if the given index is in range. If not, throws an appropriate runtime exception.
     *
     * @param index the index to check
     */
    private void rangeCheck(int index) {
        if (index >= mSize)
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + mSize);
    }

    /**
     * Returns the element at the specified position in this array, unchecked and without range
     * checking.
     *
     * @param index the index of the element to return
     * @return the element at the specified position in this array
     */
    @SuppressWarnings("unchecked")
    private E elementData(int index) {
        return (E) mElementData[index];
    }

    /**
     * Iterator implementation for Array.
     */
    private class ArrayIterator implements Iterator<E> {
        private int cursor;       // index of next element to return
        private int lastRet = -1; // index of last element returned; -1 if no such

        ArrayIterator() {
            cursor = 0;
        }

        /**
         * Returns true if the iteration has more elements.
         *
         * @return true if the iteration has more elements
         */
        public boolean hasNext() {
            return cursor != mSize;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @SuppressWarnings("unchecked")
        public E next() {
            int i = cursor;
            if (i >= mSize)
                throw new NoSuchElementException();
            Object[] elementData = Array.this.mElementData;
            cursor = i + 1;
            return (E) elementData[lastRet = i];
        }

        /**
         * Removes from the underlying collection the last element returned by this iterator
         * (optional operation). This method can be called only once per call to next(). The behavior
         * of an iterator is unspecified if the underlying collection is modified while the iteration
         * is in progress in any way other than by calling this method.
         *
         * @throws IllegalStateException if the next method has not yet been called, or the remove
         *                               method has already been called after the last call to the next
         *                               method
         */
        public void remove() {
            if (lastRet < 0)
                throw new IllegalStateException();
            try {
                Array.this.remove(lastRet);
                cursor = lastRet;
                lastRet = -1;
            } catch (IndexOutOfBoundsException ex) {
                throw new ConcurrentModificationException();
            }
        }
    }

    /**
     * Returns an iterator over the elements in this array in proper sequence.
     *
     * @return an iterator over the elements in this array in proper sequence
     */
    public Iterator<E> iterator() {
        return new ArrayIterator();
    }
}
