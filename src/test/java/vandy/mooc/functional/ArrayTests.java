package vandy.mooc.functional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ArrayTest {

    private Array<Integer> array;

    @BeforeEach
    public void setUp() {
        array = new Array<>();
    }

    @Test
    public void testArrayIteratorHasNext() {
        assertFalse(array.iterator().hasNext());
    }

    @Test
    public void testArrayIteratorHasNext2() {
        array.add(1);
        assertTrue(array.iterator().hasNext());
    }

    @Test
    public void testArrayIteratorHasNext3() {
        array.add(1);
        array.iterator().next();
        assertFalse(array.iterator().hasNext());
    }

    @Test
    public void testArrayIteratorNext() {
        array.add(1);
        assertEquals(1, array.iterator().next());
    }

    @Test
    public void testArrayIteratorNext2() {
        array.add(1);
        array.add(2);
        array.iterator().next();
        assertEquals(2, array.iterator().next());
    }

    @Test
    public void testArrayIteratorRemove() {
        array.add(1);
        array.iterator().next();
        array.iterator().remove();
        assertEquals(0, array.size());
    }

    @Test
    public void testIsEmpty() {
        assertTrue(array.isEmpty());
    }

    @Test
    public void testIsEmpty2() {
        array.add(1);
        assertFalse(array.isEmpty());
    }

    @Test
    public void testIndexOf() {
        array.add(1);
        array.add(2);
        assertEquals(1, array.indexOf(2));
    }

    @Test
    public void testIndexOf2() {
        array.add(1);
        assertEquals(0, array.indexOf(1));
    }

    @Test
    public void testIndexOf3() {
        array.add(null);
        assertEquals(0, array.indexOf(null));
    }

    @Test
    public void testIndexOf4() {
        array.add(1);
        assertEquals(-1, array.indexOf(2));
    }

    @Test
    public void testIndexOf5() {
        assertEquals(-1, array.indexOf(null));
    }

    @Test
    public void testAddAll() {
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3));
        assertTrue(array.addAll(list));
        assertEquals(3, array.size());
    }

    @Test
    public void testAddAll2() {
        Array<Integer> other = new Array<>();
        other.add(1);
        other.add(2);
        assertTrue(array.addAll(other));
        assertEquals(2, array.size());
    }

    @Test
    public void testAddAll3() {
        assertFalse(array.addAll(new ArrayList<>()));
    }

    @Test
    public void testAddAll4() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        array.add(4);
        array.addAll(list);
        assertEquals(4, array.size());
    }

    @Test
    public void testAddAll5() {
        Array<Integer> other = new Array<>();
        array.add(1);
        array.add(2);
        assertFalse(array.addAll(other));
    }

    @Test
    public void testAddAll6() {
        assertFalse(array.addAll((Array<Integer>) null));
    }

    @Test
    public void testAddAll7() {
        assertFalse(array.addAll((Collection<Integer>) null));
    }

    @Test
    public void testRemove() {
        array.add(1);
        assertEquals(1, array.remove(0));
        assertEquals(0, array.size());
    }

    @Test
    public void testRemove2() {
        assertThrows(IndexOutOfBoundsException.class, () -> array.remove(0));
    }

    @Test
    public void testRemove3() {
        array.add(1);
        array.add(2);
        assertEquals(1, array.remove(0));
        assertEquals(2, array.get(0));
    }

    @Test
    public void testRemove4() {
        array.add(1);
        array.add(2);
        assertThrows(IndexOutOfBoundsException.class, () -> array.remove(2));
    }

    @Test
    public void testRangeCheck() {
        array.add(1);
        array.add(2);
        array.get(1);
        assertDoesNotThrow(() -> array.rangeCheck(1));
    }

    @Test
    public void testRangeCheck2() {
        assertThrows(IndexOutOfBoundsException.class, () -> array.rangeCheck(0));
    }

    @Test
    public void testRangeCheck3() {
        array.add(1);
        array.add(2);
        assertThrows(IndexOutOfBoundsException.class, () -> array.rangeCheck(2));
    }

    @Test
    public void testGet() {
        array.add(1);
        assertEquals(1, array.get(0));
    }

    @Test
    public void testGet2() {
        array.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(1));
    }

    @Test
    public void testGet3() {
        assertThrows(IndexOutOfBoundsException.class, () -> array.get(0));
    }

    @Test
    public void testSet() {
        array.add(1);
        assertEquals(1, array.set(0, 2));
        assertEquals(2, array.get(0));
    }

    @Test
    public void testSet2() {
        array.add(1);
        assertThrows(IndexOutOfBoundsException.class, () -> array.set(1, 2));
    }

    @Test
    public void testSet3() {
        assertThrows(IndexOutOfBoundsException.class, () -> array.set(0, 1));
    }

    @Test
    public void testAdd() {
        assertTrue(array.add(1));
        assertEquals(1, array.get(0));
    }

    @Test
    public void testAdd2() {
        array.add(1);
        array.add(2);
        assertEquals(2, array.get(1));
    }

    @Test
    public void testClear() {
        array.add(1);
        array.add(2);
        array.clear();
        assertEquals(0, array.size());
    }

    @Test
    public void testClear2() {
        array.clear();
        assertEquals(0, array.size());
    }

    @Test
    public void testContains() {
        array.add(1);
        assertTrue(array.contains(1));
    }

    @Test
    public void testContains2() {
        assertFalse(array.contains(1));
    }

    @Test
    public void testContains3() {
        array.add(null);
        assertTrue(array.contains(null));
    }

    @Test
    public void testContains4() {
        assertFalse(array.contains(null));
    }

    @Test
    public void testSize() {
        array.add(1);
        assertEquals(1, array.size());
    }

    @Test
    public void testSize2() {
        assertEquals(0, array.size());
    }

    @Test
    public void testToArray() {
        array.add(1);
        Object[] arr = array.toArray();
        assertEquals(1, arr.length);
        assertEquals(1, arr[0]);
    }

    @Test
    public void testToArray2() {
        array.add(1);
        Integer[] arr = new Integer[1];
        Integer[] res = array.toArray(arr);
        assertSame(arr, res);
        assertEquals(1, res.length);
        assertEquals(1, res[0]);
    }

    @Test
    public void testUncheckedToArray() {
        array.add(1);
        Object[] arr = array.uncheckedToArray();
        assertEquals(1, arr.length);
        assertEquals(1, arr[0]);
    }

    @Test
    public void testReplaceAll() {
        array.add(1);
        array.add(2);
        array.replaceAll(e -> e * 2);
        assertEquals(2, array.get(0));
        assertEquals(4, array.get(1));
    }

    @Test
    public void testReplaceAll2() {
        array.replaceAll(e -> e * 2);
        assertEquals(0, array.size());
    }

    @Test
    public void testIteratorNextThrowsNoSuchElement() {
        assertThrows(NoSuchElementException.class, () -> array.iterator().next());
    }

    @Test
    public void testIteratorRemoveThrowsIllegalState() {
        assertThrows(IllegalStateException.class, () -> array.iterator().remove());
    }

    @Test
    public void testIteratorRemoveThrowsConcurrentModification() {
        array.add(1);
        array.add(2);
        var it = array.iterator();
        it.next();
        array.add(3);
        assertThrows(ConcurrentModificationException.class, it::remove);
    }
}
