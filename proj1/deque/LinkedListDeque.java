package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Iterable<T>, Deque<T> {
    private int size;
    private Node sentinel;

    private class Node {
        T item;
        Node prev;
        Node next;
        Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    // Creates an empty linked list deque
    public LinkedListDeque() {
        size = 0;
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
    }
    // No looping or recursion
    @Override
    public void addFirst(T item) {
        Node first = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = first;
        sentinel.next = first;
        size += 1;
    }
    // No looping or recursion
    @Override
    public void addLast(T item) {
        Node last = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = last;
        sentinel.prev = last;
        size += 1;
    }
    @Override
    public int size() {
        return size;
    }
    @Override
    public void printDeque() {
        if (isEmpty()) {
            return;
        }
        Node p = sentinel.next;
        while (p != sentinel) {
            System.out.println(p.item + " ");
            p = p.next;
        }
    }
    // No looping or recursion
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        Node first = sentinel.next;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        T removedItem = first.item;

        first = null;
        size -= 1;
        return removedItem;
    }
    // No looping or recursion
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        Node last = sentinel.prev;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;
        T removedItem = last.item;

        last = null;
        size -= 1;
        return removedItem;
    }
    // Use iteration
    @Override
    public T get(int index) {
        if (index > size || index == 0) {
            return null;
        }

        Node p = sentinel.next;
        T itemAtIndex = p.item;

        for (int i = 1; i <= size; i++) {
            if (i != index) {
                p = p.next;
            } else {
                itemAtIndex = p.item;
                break;
            }
        }

        return itemAtIndex;
    }
    public T getRecursive(int index) {
        Node p = sentinel.next;
        return getRecursiveHelper(p, index);
    }
    private T getRecursiveHelper(Node p, int index) {
        if (index > size) {
            return null;
        }

        if (index == 1) {
            return p.item;
        }

        return getRecursiveHelper(p.next, index - 1);
    }

    private class LinkedListDequeIterator implements Iterator<T>{
        private int wizPos;
        public LinkedListDequeIterator() {
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
        return new LinkedListDequeIterator();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        Deque<T> ol = (Deque<T>) o;
        if (ol.size() != this.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!(ol.get(i).equals(this.get(i)))) {
                return false;
            }
        }
        return true;
    }
}