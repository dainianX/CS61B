package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Iterable<T> {
    private int size;
    // Array to represent deque
    private T[] items;
    private int nextFirst;
    private int nextLast;

    // Creates an empty array deque
    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = 4;
        nextLast = 5;
        size = 0;
    }
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        if (nextFirst == 0) {
            nextFirst = items.length - 1;
        } else {
            nextFirst -= 1;
        }
        size += 1;
    }
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        if (nextLast == items.length - 1) {
            nextLast = 0;
        } else {
            nextLast += 1;
        }
        size += 1;
    }
    public boolean isEmpty() {
        return size == 0;
    }
    public int size() {
        return size;
    }
    public void printDeque() {
        for (T n : items) {
            System.out.print(n + " ");
        }
    }
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        if ((size < items.length / 4) && (size > 8)) {
            resize(items.length / 2);
        }
        T returnedItem = get(0);
        size -= 1;
        nextFirst = actualArrayIndex(0);
        return returnedItem;
    }
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        if ((size < items.length / 4) && (size > 8)) {
            resize(items.length / 2);
        }
        T returnedItem = get(size - 1);
        size -= 1;
        nextLast = actualArrayIndex(size - 1);
        return returnedItem;
    }
    public T get(int index) {
        if (isEmpty()) {
            return null;
        }
        return items[actualArrayIndex(index)];
    }

    // Resize, using the last idea
    private void resize(int capacity) {
        T[] newArray = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArray[capacity / 4 + i] = items[actualArrayIndex(i)];
        }
        items = newArray;
        nextFirst = capacity / 4 - 1;
        nextLast = nextFirst + size + 1;
    }

    private int actualArrayIndex(int index) {
        if (nextFirst + index + 1 >= items.length) {
            return nextFirst + index + 1 - items.length;
        } else {
            return nextFirst + index + 1;
        }
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int wizPos;
        public ArrayDequeIterator() {
            wizPos = 0;
        }

        @Override
        public boolean hasNext() {
            return wizPos < size;
        }

        @Override
        public T next() {
            T returnItem = get(wizPos);
            wizPos += 1;
            return returnItem;
        }
    }
    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if ((o instanceof ArrayDeque otherDeque)) {
            if (this.size != otherDeque.size) {
                return false;
            }
            for (int i = 0; i < size; i++) {
                if (!(otherDeque.get(i).equals(this.get(i)))) {
                    return false;
                }
            }
        }

        return true;
    }
}