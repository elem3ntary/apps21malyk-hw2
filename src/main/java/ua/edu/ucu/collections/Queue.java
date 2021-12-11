package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {
    private ImmutableLinkedList linkedList = new ImmutableLinkedList();

    public Object peek() {
        return linkedList.getLast();
    }

    public Object dequeue() {
        Object popped = peek();
        linkedList.removeLastMutable();
        return popped;
    }

    public void enqueue(Object e) {
        linkedList.addFirstMutable(e);
    }
}
