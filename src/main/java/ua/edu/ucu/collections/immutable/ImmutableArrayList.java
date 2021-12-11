package ua.edu.ucu.collections.immutable;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Objects;

public final class ImmutableArrayList implements ImmutableList {
    private Object[] base;
    private int length;
    private static final int SIZE_MULTIPLIER = 2;

    public ImmutableArrayList(Object[] elements) {
        base = Arrays.copyOf(elements, elements.length);
        length = elements.length;
    }

    public ImmutableArrayList(Object[] elements, int length) {
        base = Arrays.copyOf(elements, elements.length);
        this.length = length;
    }

    public ImmutableArrayList() {
        length = 0;
        base = new Object[0];
    }

    @Override
    public ImmutableArrayList add(Object e) {
        ImmutableArrayList arrayListCopy = copy();
        arrayListCopy.addMutable(e);
        return arrayListCopy;
    }

    private ImmutableArrayList addMutable(Object e) {
        if (isBaseArrFull()) {
            increaseBaseArraySize();
        }
        base[length++] = e;
        return this;
    }

    private void increaseBaseArraySize() {
        int previousLength = base.length == 0 ? 1 : base.length;
        base = Arrays.copyOf(base, previousLength * SIZE_MULTIPLIER);
    }

    private boolean isBaseArrFull() {
        return length == base.length;
    }

    private ImmutableArrayList copy() {
        return new ImmutableArrayList(base, length);
    }

    @Override
    public ImmutableArrayList add(int index, Object e) {
        ImmutableArrayList arrayListCopy = copy();
        arrayListCopy.addMutable(index, e);
        return arrayListCopy;
    }

    private ImmutableArrayList addMutable(int index, Object e) {
        if (index > length - 1) {
            throw new IndexOutOfBoundsException();
        }
        if(isBaseArrFull()) {
            increaseBaseArraySize();
        }

        for (int i = length - 1; i >= 0; i--) {
            base[i + 1] = base[i];
        }
        base[index] = e;
        length++;
        return this;
    }

    @Override
    public ImmutableArrayList addAll(Object[] c) {
        return addAll(length - 1, c);
    }

    @Override
    public ImmutableArrayList addAll(int index, Object[] c) {
        //
        ImmutableArrayList arrayListCopy = copy();
        if (index == length - 1) {
            for (int i = 0; i < c.length; i++) {
                arrayListCopy.addMutable(c[i]);
            }
            return arrayListCopy;
        }
        for (int i = c.length - 1; i >= 0; i--) {
            arrayListCopy.addMutable(index, c[i]);
        }
        return arrayListCopy;
    }

    @Override
    public Object get(int index) {
        return base[index];
    }

    @Override
    public ImmutableArrayList remove(int index) {
        ImmutableArrayList arrayListCopy = copy();
        arrayListCopy.removeMutable(index);
        return arrayListCopy;
    }

    private ImmutableArrayList removeMutable(int index) {
        for (int i = index; i < length - 1; i++) {
            base[i] = base[i+1];
        }
        length--;
        base[length] = null;

        return  this;
    }


    @Override
    public ImmutableArrayList set(int index, Object e) {
        ImmutableArrayList arrayListCopy = copy();
        arrayListCopy.setMutable(index, e);
        return arrayListCopy;
    }

    private ImmutableArrayList setMutable(int index, Object e) {
        if (index >= length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        base[index] = e;
        return this;
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < length; i++) {
            if(base[i] == e) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }

    @Override
    public int size() {
        return length;
    }


    public ImmutableArrayList clear() {
        base = new Object[0];
        length = 0;
        return this;
    }

    @Override
    public boolean isEmpty() {
        return length == 0;
    }

    @Override
    public Object[] toArray() {
        return Arrays.copyOf(base, length);
    }
}
