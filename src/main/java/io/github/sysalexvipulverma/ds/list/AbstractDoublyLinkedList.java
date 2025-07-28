package io.github.sysalexvipulverma.ds.list;

import io.github.sysalexvipulverma.ds.core.Container;

public abstract class AbstractDoublyLinkedList<E> implements IndexedContainer<E> {

    protected Node head;
    protected Node tail;
    int currentSize;

    public AbstractDoublyLinkedList<E> addFirst(E element) {
        Node nodeToBeAdded = new Node(element);

        synchronized (this) {
            if (null == head) {
                head = tail = nodeToBeAdded;
            } else {
                nodeToBeAdded.next = head;
                head.previous = nodeToBeAdded;
                head = nodeToBeAdded;
            }
            ++currentSize;
            return this;
        }
    }

    public AbstractDoublyLinkedList<E> addLast(E element) {
        Node nodeToBeAdded = new Node(element);

        synchronized (this) {
            if (null == head) {
                head = tail = nodeToBeAdded;
            } else {
                tail.next = nodeToBeAdded;
                nodeToBeAdded.previous = tail;
                tail = nodeToBeAdded;
            }
            ++currentSize;
            return this;
        }
    }

    public synchronized AbstractDoublyLinkedList<E> removeFirst() {
        if (0 == currentSize) {
            return this;
        }

        if (1 == currentSize) {
            clear();
            return this;
        }

        head.element = null;
        head = head.next;
        head.previous = null;
        return this;
    }

    /*
     * The time complexity of below removeLast() operation of LinkedList
     * is O(1) but in SinglyLinkedList, it is O(n)
     * */
    public synchronized AbstractDoublyLinkedList<E> removeLast() {
        if (0 == currentSize) {
            return this;
        }

        if (1 == currentSize) {
            clear();
            return this;
        }

        tail.element = null;
        tail = tail.previous;
        tail.next = null;
        return this;
    }

    public E first() {
        return null == head ? null : head.element;
    }

    public E last() {
        return null == tail ? null : tail.element;
    }

    @Override
    public String toString() {
        return Container.toString(this);
    }

    protected class Node {
        protected Node previous;
        protected Node next;
        protected E element;

        protected Node(E element) {
            this.element = element;
        }
    }
}
