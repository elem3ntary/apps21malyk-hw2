package ua.edu.ucu.collections.immutable;

import lombok.Setter;

import java.util.NoSuchElementException;

public final class ImmutableLinkedList implements ImmutableList {
    @Setter
    private Node head;
    private Node tail;
    private int linkedListSize = 0;

    public ImmutableLinkedList(Object[] elements) {
        for (Object element : elements) {
            addMutable(element);
        }
    }

    public ImmutableLinkedList() {

    }

    @Override
    public ImmutableList add(Object e) {
        ImmutableLinkedList immutableListCopy = shallowCopy();
        immutableListCopy.addMutable(e);
        return immutableListCopy;
    }

    private ImmutableLinkedList addMutable(Object e) {
        linkedListSize++;
        Node node = new Node();
        node.setValue(e);
        if (tail == null) {
            tail = node;
            head = tail;

            return this;
        }

        node.setPrevious(tail);
        tail.setNext(node);

        tail = node;
        return this;
    }

    private ImmutableLinkedList shallowCopy() {
        ImmutableLinkedList immutableListCopy = new ImmutableLinkedList();
        if (head == null) {
            return immutableListCopy;
        }
        Node node = head;
        while (node != null) {
            immutableListCopy.addMutable(node.getValue());
            node = node.getNext();
        }
        return immutableListCopy;
    }

    @Override
    public ImmutableList add(int index, Object e) {
        ImmutableLinkedList immutableListCopy = shallowCopy();
        immutableListCopy.addMutable(index, e);
        return immutableListCopy;
    }

    private ImmutableLinkedList addMutable(int index, Object e) {
        if (index > linkedListSize || index < 0) {
            throw new IllegalArgumentException();
        }
        if (index == linkedListSize) {
            addMutable(e);
            return this;
        }
        linkedListSize++;

        Node nodeToAdd = new Node();
        nodeToAdd.setValue(e);

        Node nodeToReplace = getNodeByIndex(index);
        Node previousNode = nodeToReplace.getPrevious();
        if (previousNode != null) {
            previousNode.setNext(nodeToAdd);
        }
        nodeToAdd.setNext(nodeToReplace);
        nodeToReplace.setPrevious(nodeToAdd);

        return this;
    }

    @Override
    public ImmutableLinkedList addAll(Object[] c) {
        ImmutableLinkedList immutableListCopy = shallowCopy();
        for (Object element : c) {
            immutableListCopy.addMutable(element);
        }
        return immutableListCopy;
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        ImmutableLinkedList immutableListCopy = shallowCopy();
        for (Object element : c) {
            immutableListCopy.addMutable(index, element);
            index++;
        }
        return immutableListCopy;
    }

    @Override
    public Object get(int index) {
        Node node = getNodeByIndex(index);
        return node.getValue();
    }

    private Node getNodeByIndex(int index) {
        if (index > linkedListSize) {
            throw new IllegalArgumentException();
        }

        int i = 0;
        Node iteratingNode = head;
        while (i != index) {
            iteratingNode = iteratingNode.getNext();
            i++;
        }
        return iteratingNode;
    }

    @Override
    public ImmutableList remove(int index) {
        ImmutableLinkedList immutableListCopy = shallowCopy();
        immutableListCopy.removeMutable(index);
        return immutableListCopy;
    }

    private ImmutableLinkedList removeMutable(int index) {
        Node nodeToRemove = getNodeByIndex(index);
        if (nodeToRemove.getPrevious() != null) {
            nodeToRemove.getPrevious().setNext(nodeToRemove.getNext());
        }
        if (nodeToRemove.getNext() != null) {
            nodeToRemove.getNext().setPrevious(nodeToRemove.getPrevious());
        }

        linkedListSize--;
        return this;
    }

    @Override
    public ImmutableList set(int index, Object e) {
        ImmutableLinkedList immutableListCopy = shallowCopy();
        immutableListCopy.setMutable(index, e);
        return immutableListCopy;
    }

    private ImmutableLinkedList setMutable(int index, Object e) {
        Node nodeToChange = getNodeByIndex(index);
        nodeToChange.setValue(e);
        return this;
    }

    @Override
    public int indexOf(Object e) {
        int i = 0;
        Node iteratingNode = head;
        while (i != linkedListSize || iteratingNode != null) {
            if (iteratingNode.getValue() == e) {
                return i;
            }
            iteratingNode = iteratingNode.getNext();
            i++;
        }
        throw new NullPointerException();
    }

    @Override
    public int size() {
        return linkedListSize;
    }

    @Override
    public ImmutableList clear() {
        linkedListSize = 0;
        head = null;
        tail = null;
        return this;
    }

    @Override
    public boolean isEmpty() {
        return linkedListSize == 0 && head == null && tail == null;
    }

    @Override
    public Object[] toArray() {
        Object[] resultingArray = new Object[linkedListSize];
        Node iteratingNode = head;
        int i = 0;
        while (iteratingNode != null) {
            resultingArray[i] = iteratingNode.getValue();
            iteratingNode = iteratingNode.getNext();
            i++;
        }
        return resultingArray;
    }

    public ImmutableLinkedList addFirst(Object e) {
        ImmutableLinkedList immutableListCopy = shallowCopy();
        immutableListCopy.addFirstMutable(e);
        return immutableListCopy;
    }

    public ImmutableLinkedList addFirstMutable(Object e) {
        addMutable(0, e);
        if (head.getPrevious() != null) {
            head = head.getPrevious();
        }

        return this;
    }

    public ImmutableLinkedList addLast(Object e) {
        ImmutableLinkedList immutableListCopy = shallowCopy();
        immutableListCopy.addLastMutable(e);

        return immutableListCopy;
    }

    public ImmutableLinkedList addLastMutable(Object e) {
        addMutable(linkedListSize, e);
        return this;
    }

    public Node getHead() {
        return head;
    }

    public Node getTail() {
        return tail;
    }

    public void setTail(Node newTail) {
        this.tail = newTail;
    }

    public Object getFirst() {
        return head.getValue();
    }

    public Object getLast() {
        return tail.getValue();
    }

    public ImmutableLinkedList removeFirst() {
        ImmutableLinkedList immutableListCopy = shallowCopy();
        immutableListCopy.removeMutable(0);
        immutableListCopy.setHead(immutableListCopy.getHead().getNext());
        return immutableListCopy;
    }

    public ImmutableLinkedList removeLast() {
        ImmutableLinkedList immutableListCopy = shallowCopy();
        immutableListCopy.removeLastMutable();
        return immutableListCopy;
    }

    public ImmutableLinkedList removeLastMutable() {
        removeMutable(linkedListSize - 1);
        setTail(tail.getPrevious());

        return this;
    }
}
