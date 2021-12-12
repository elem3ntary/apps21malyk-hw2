package ua.edu.ucu.collections.immutable;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ImmutableLinkedListTest  {
    private static final Integer[] testArr = new Integer[]{1,2,3,4,5,6};

    @Test
    public void testConstructor() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList();
        assertEquals(0, linkedList.size());

        linkedList = new ImmutableLinkedList(testArr);
        Node iteratingNode = linkedList.getHead();

        assertEquals(testArr.length, linkedList.size());
        for (int i = 0; i < testArr.length; i++) {
            if (iteratingNode == null) {
                break;
            }
            assertEquals(testArr[i], iteratingNode.getValue());
            iteratingNode = iteratingNode.getNext();
        }
    }

    @Test
    public void testHeadSetter() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList();
        Node testNode = new Node();
        linkedList.setHead(testNode);
        assertEquals(testNode, linkedList.getHead());
    }

    @Test
    public void testAdd() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList(testArr);
        ImmutableList testList = linkedList.add(7);
        assertNotSame(linkedList, testList);
        assertEquals(testArr.length + 1, testList.size());
        assertArrayEquals(new Object[]{1, 2, 3, 4, 5, 6, 7}, testList.toArray());

    }
    @Test
    public void testAddByIndex() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList(testArr);
        ImmutableList testList = linkedList.add(1,9);
        assertNotSame(linkedList, testList);
        assertEquals(testArr.length + 1, testList.size());
        assertArrayEquals(new Object[]{1, 9,2, 3, 4, 5, 6}, testList.toArray());

    }

    @Test
    public void testAddAll() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList(testArr);
        ImmutableList testList = linkedList.addAll(new Integer[]{7,8});
        assertNotSame(linkedList, testList);
        assertEquals(testArr.length + 2, testList.size());
        assertArrayEquals(new Object[]{1, 2, 3, 4, 5, 6, 7, 8}, testList.toArray());
    }

    @Test
    public void testGet() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList(testArr);
        assertEquals(2, linkedList.get(1));
    }

    @Test
    public void testGetOutOfRange() {
        final ImmutableLinkedList linkedList = new ImmutableLinkedList(testArr);
        assertThrows(IllegalArgumentException.class, () -> linkedList.get(10));
    }

    @Test
    public void testRemove() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList(testArr);
        ImmutableList testList = linkedList.remove(1);
        assertArrayEquals(new Object[]{1, 3, 4, 5, 6}, testList.toArray());
        assertNotSame(testList, linkedList);
    }

    @Test
    public void testSet() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList(testArr);
        ImmutableList testList = linkedList.set(1,8);
        assertArrayEquals(new Object[]{1, 8, 3, 4, 5, 6}, testList.toArray());
        assertEquals(8, testList.get(1));
        assertNotSame(testList, linkedList);
    }

    @Test
    public void testIndexOf() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList(testArr);
        assertEquals(0,linkedList.indexOf(1));
        assertEquals(1,linkedList.indexOf(2));
        assertEquals(5,linkedList.indexOf(6));
    }
    @Test
    public void testIndexOfNoSuchElement() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList(testArr);
        assertThrows(NullPointerException.class, () -> linkedList.indexOf(9));
    }

    @Test
    public void testSize() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList(testArr);
        assertEquals(testArr.length, linkedList.size());
        ImmutableList testList = linkedList.addAll(new Object[]{1,2,3});
        assertNotSame(linkedList, testList);
        assertEquals(testArr.length + 3, testList.size());
    }

    @Test
    public void testClear() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList(testArr);
        linkedList.clear();
        assertNull(linkedList.getHead());
        assertEquals(0, linkedList.size());
        assertTrue(linkedList.isEmpty());
    }

    @Test
    public void testIsEmpty() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList();
        assertEquals(0, linkedList.size());
        assertNull(linkedList.getHead());
        assertNull(linkedList.getTail());
    }

    @Test
    public void testToArray() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList(testArr);
        assertArrayEquals(testArr, linkedList.toArray());
    }

    @Test
    public void testAddFirst() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList(testArr);
        ImmutableLinkedList testList = linkedList.addFirst(0);
        assertNotSame(linkedList, testList);
        assertEquals(0, testList.getHead().getValue());
    }

    @Test
    public void testAddLast() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList(testArr);
        ImmutableLinkedList testList = linkedList.addLast(0);
        assertNotSame(linkedList, testList);
        assertEquals(0, testList.getTail().getValue());
    }

    @Test
    public void testGetFirst() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList(testArr);
        assertEquals(linkedList.getFirst(), linkedList.getHead().getValue());
    }

    @Test
    public void testGetLast() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList(testArr);
        assertEquals(6, linkedList.getLast());
    }

    @Test
    public void testRemoveFirst() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList(testArr);
        ImmutableLinkedList testList = linkedList.removeFirst();
        assertEquals(testList.getHead().getValue(), linkedList.getHead().getNext().getValue());
    }

    @Test
    public void testRemoveLast() {
        ImmutableLinkedList linkedList = new ImmutableLinkedList(testArr);
        ImmutableLinkedList testList = linkedList.removeLast();
        assertArrayEquals(new Object[]{1, 2, 3, 4, 5}, testList.toArray());
        assertEquals(testList.getTail().getValue(), linkedList.getTail().getPrevious().getValue());
    }
}