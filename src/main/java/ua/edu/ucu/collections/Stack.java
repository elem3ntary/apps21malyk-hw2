package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack {
    private ImmutableLinkedList linkedList;

    public Stack() {
        this.linkedList = new ImmutableLinkedList();
    }

    public void push(Object e) {
        linkedList.addLastMutable(e);
    }

    public Object pop() {
        Object poppedValue = peek();
        linkedList.removeLastMutable();
        return poppedValue;
    }

    public Object peek() {
        return linkedList.getLast();
    }
}
