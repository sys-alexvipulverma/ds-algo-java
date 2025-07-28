package io.github.sysalexvipulverma.ds.list;

import io.github.sysalexvipulverma.ds.core.Container;

/*
 * Implementation of an inner class Node based singly linked list
 *
 * Asked in IBM second coding round
 * */

/*
 * This class contains methods which are specific only to a LinkedList
 * The implementations of other methods of Container<E> and IndexedContainer<E> will
 * be written in concrete class SinglyLinkedList
 * */

public abstract class AbstractSinglyLinkedList<E> implements Container<E>, IndexedContainer<E> {
    protected Node head;
    protected Node tail;
    protected int currentSize;

    // Singly Linked List operations

    /*
     * Add at the last node of the linked list addLast() operation
     * Time Complexity O(1)
     * */
    public AbstractSinglyLinkedList<E> addLast(E element) {
        Node nodeToBeAdded = new Node(element);

        synchronized (this) {
            if (null == head) {
                head = tail = nodeToBeAdded;
            } else {
                tail.next = nodeToBeAdded;
                tail = nodeToBeAdded;
            }
            ++currentSize;
        }
        return this;
    }

    /*
     * Add at the first node of the linked list addFirst() operation
     * Time Complexity O(1)
     * */
    public AbstractSinglyLinkedList<E> addFirst(E element) {
        Node nodeToBeAdded = new Node(element);

        synchronized (this) {
            if (null == head) {
                head = tail = nodeToBeAdded;
            } else {
                nodeToBeAdded.next = head;
                head = nodeToBeAdded;
            }
            ++currentSize;
            return this;
        }
    }

    /*
     * Remove the first node of the linked list
     * Time Complexity O(1)
     * */
    public synchronized AbstractSinglyLinkedList<E> removeFirst() {
        if (1 == currentSize) {
            clear();
            return this;
        }

        synchronized (this) {
            head.element = null;
            head = head.next;
            --currentSize;
            return this;
        }
    }

    /*
     * A helper method to find second last node of the linked list
     * Since this is a singly linked list so, we can't move from tail node in backward direction
     * So time time complexity is O(n) which will be O(1) in Doubly Linked List
     *
     * Given that this method will be called only when the size of Singly Linked List
     * is 2 or more so it may not throw java.lang.NullPointerException
     * */
    protected Node findSecondLastNode() {

        // This is thread-safe area

        Node currentNode = head;

        Node nextNode = null;
        while (currentNode != null) {
            nextNode = currentNode.next;

            if (tail == nextNode) {
                return currentNode;
            }
            currentNode = nextNode;
        }
        return nextNode;
    }


    /*
     * Remove the first node of singly linked list
     * Time Complexity O(1) but O(n) for doubly linked list
     * */
    public synchronized AbstractSinglyLinkedList<E> removeLast() {
        if (1 == currentSize) {
            clear();
            return this;
        }

        synchronized (this) {
            tail.element = null;

            Node secondLastNode = findSecondLastNode();
            secondLastNode.next = null;
            tail = secondLastNode;
            --currentSize;
            return this;
        }
    }

    public E first() {
        return null == head.element ? null : head.element;
    }

    public E last() {
        return null == tail.element ? null : tail.element;
    }

    @Override
    public String toString() {
        return Container.toString(this);
    }

    // Instance inner class node

    protected class Node {
        Node next;
        E element;

        Node(E element) {
            this.element = element;
        }
    }
}
