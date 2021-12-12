package ua.edu.ucu.collections.immutable;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ImmutableArrayListTest {
    private ImmutableArrayList immutableArrayList;

    @BeforeEach
    public void setUp() {
        immutableArrayList = new ImmutableArrayList();
    }

    @Test
    public void testAdd() {
        ImmutableArrayList testList =  immutableArrayList.add(1);
        assertEquals(testList.get(0), 1);
        testList =  testList.add(2);
        assertEquals(testList.get(1), 2);
    }
    @Test
    public void testAddWithIndexOutOfRange() {
        assertThrows(IndexOutOfBoundsException.class, ()->immutableArrayList.add(0,1));
    }
    @Test
    public void testAddWithIndex() {
        ImmutableArrayList testList = immutableArrayList.add(1);
        testList = testList.add(0,2);
        assertEquals(2, testList.get(0));
        assertNotSame(testList, immutableArrayList);

    }

    @Test
    public void testAddAll() {
        ImmutableArrayList testList = immutableArrayList.addAll(new Object[]{1,2,3});
        assertEquals(1, testList.get(0));
        assertEquals(2, testList.get(1));
        assertEquals(3, testList.get(2));

    }

    @Test
    public void testAddAllWithIndex() {
        ImmutableArrayList testList = immutableArrayList.add(1);
        testList = testList.addAll(0,new Object[]{1,2,3});
        assertEquals(1, testList.get(0));
        assertEquals(2, testList.get(1));
        assertEquals(3, testList.get(2));

    }


    @Test
    public void testGet() {
        ImmutableArrayList testList = immutableArrayList.add(1);
        assertEquals(1, testList.get(0));
    }
    @Test
    public void testGetOutOfIndex() {
        assertThrows(ArrayIndexOutOfBoundsException.class, () -> immutableArrayList.get(0));
    }

    @Test
    public void testRemove() {
        ImmutableArrayList testList = immutableArrayList.add(1);
        ImmutableArrayList testList2 = testList.remove(0);
        assertNotSame(testList, testList2);
        assertEquals(0, testList2.size());

        ImmutableArrayList testList3 = testList.addAll(new Object[]{2,3,4});
        testList3 = testList3.remove(2);
        assertArrayEquals(new Object[]{1,2,4}, testList3.toArray());
    }

    @Test
    public void testSet() {
        ImmutableArrayList testList = immutableArrayList.add(1).set(0, 2);
        assertEquals(2, testList.get(0));
    }
    @Test
    public void testSetOutOfBound() {
        ImmutableArrayList testList = immutableArrayList.add(1);
        // base = [1, null], length = 1
        assertThrows(IndexOutOfBoundsException.class, () -> testList.set(1, 2));
    }

    @Test
    public void testIndexOf() {
        assertEquals(0, immutableArrayList.add(1).indexOf(1));
    }
    @Test
    public void testIndexOfNoElement() {
        ImmutableArrayList testList = immutableArrayList.add(1);
        assertThrows(NoSuchElementException.class, () -> testList.indexOf(2));
    }

    @Test
    public void testSize() {
        ImmutableArrayList testList = immutableArrayList.add(1).add(2).addAll(new Object[]{3,4});
        assertEquals(4, testList.size());
        testList = testList.remove(0);
        assertEquals(3, testList.size());
    }

    @Test
    public void testClear() {
        ImmutableArrayList testList = immutableArrayList.add(1);
        testList.clear();
        assertEquals(0, testList.size());
        assertThrows(IndexOutOfBoundsException.class, () -> testList.get(0));
    }


    @Test
    public void testToArray() {
        Object[] toCompare = new Object[]{2,4,5,6,7};
        ImmutableArrayList testList = immutableArrayList.addAll(toCompare);
        assertArrayEquals(toCompare, testList.toArray());
    }
}