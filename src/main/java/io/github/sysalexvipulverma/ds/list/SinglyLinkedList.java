package io.github.sysalexvipulverma.ds.list;

import io.github.sysalexvipulverma.ds.core.Container;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.Function;

public class SinglyLinkedList<E> extends AbstractSinglyLinkedList<E> implements Container<E>, IndexedContainer<E> {

    @Override
    public SinglyLinkedList<E> reversed() {
        SinglyLinkedList<E> out = new SinglyLinkedList<>();

        for (Node currentNode = head; currentNode != null; currentNode = currentNode.next) {

            // addFirst() results in reverse order — linear time, space-efficient.
            out.addFirst(currentNode.element);
        }

        return out;
    }

    /*
     * Helper method to remove an element at the middle
     * given that this method will not be called through head or tail nodes
     * because for those operations we already have removeFirst() and removeLast()
     * in abstract superclass AbstractSinglyLinkedList<E>
     * */
    private synchronized void removeMiddleNode(Node nodeToBeRemoved) {
        Node currentNode = head;

        while (currentNode != null) {
            Node nextNode = currentNode.next;

            if (nextNode == nodeToBeRemoved) {
                currentNode.next = nodeToBeRemoved.next;
                nodeToBeRemoved.next = null;
                nodeToBeRemoved.element = null;
                --currentSize;
                return;
            }
            currentNode = currentNode.next;
        }
    }

    /*
     * Helper method to get the node at specific index
     * Time Complexity O(n) which will be improved to O(n / 2)
     * */
    private synchronized Node nodeAtIndex(int index) {
        if (index < 0 || index >= currentSize) {
            throw new IndexOutOfBoundsException();
        }

        Node currentNode = head;
        for (int currentIndex = 0; currentIndex < currentSize; currentIndex++) {
            if (currentIndex == index) {
                return currentNode;
            }
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    // Implementations of Container<E>

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public <R> SinglyLinkedList<R> map(Function<E, R> function) {
        SinglyLinkedList<R> out = new SinglyLinkedList<>();

        for (E element : this) {
            out.add(function.apply(element));
        }
        return out;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private Node currentNode = head;

            @Override
            public boolean hasNext() {
                return null != currentNode;
            }

            @Override
            public E next() {
                if (hasNext()) {
                    E element = currentNode.element;
                    currentNode = currentNode.next;
                    return element;
                }
                throw new NoSuchElementException();
            }
        };
    }

    @Override
    public SinglyLinkedList<E> add(E element) {
        super.addLast(element);
        return this;
    }

    @Override
    public SinglyLinkedList<E> remove(E element) {
        for (Node currentNode = head; currentNode != null; currentNode = currentNode.next) {
            if (Objects.equals(element, currentNode.element)) {
                removeMiddleNode(currentNode);
                return this;
            }
        }
        return this;
    }

    @Override
    public synchronized SinglyLinkedList<E> clear() {
        if (0 == currentSize) {
            return this;
        }

        Node currentNode = head;
        while (currentNode != null) {
            Node nextNode = currentNode.next;
            currentNode.element = null;
            currentNode.next = null;
            currentNode = nextNode;
        }

        head = tail = null;
        currentSize = 0;
        return this;
    }

    @Override
    public boolean contains(E element) {
        for (Node node = head; node != null; node = node.next) {
            if (Objects.equals(element, node.element)) {
                return true;
            }
        }
        return false;
    }

    // Implementations of IndexedContainer<E>

    @Override
    public E get(int index) {
        return nodeAtIndex(index).element;
    }

    @Override
    public SinglyLinkedList<E> set(int index, E element) {
        nodeAtIndex(index).element = element;
        return this;
    }

    @Override
    public SinglyLinkedList<E> remove(int index) {
        if (0 == index) {
            removeFirst();
            return this;
        }

        if (index == (currentSize - 1)) {
            removeLast();
            return this;
        }

        removeMiddleNode(nodeAtIndex(index));
        return this;
    }

    @Override
    public int indexOf(E element) {
        int currentIndex = 0;

        for (Node currentNode = head; currentNode != null; currentNode = currentNode.next, ++currentIndex) {
            if (Objects.equals(element, currentNode.element)) {
                return currentIndex;
            }
        }
        return -1;
    }

    /*
     * Below implementation of lastIndexOf() will be improved in DoublyLinkedList
     * as we will move from tail node to head node instead of head to tail
     * */
    @Override
    public int lastIndexOf(E element) {
        int currentIndex = 0;
        int lastIndex = -1;

        for (Node currentNode = head; currentNode != null; currentNode = currentNode.next, ++currentIndex) {
            if (Objects.equals(element, currentNode.element)) {
                lastIndex = currentIndex;
            }
        }
        return lastIndex;
    }

    @Override
    public IndexedContainer<E> add(int index, E element) {
        Node nodeToBeAdded = new Node(element);

        if (0 == index) {
            addFirst(element);
            return this;
        }

        if (index == currentSize) {
            addLast(element);
            return this;
        }

        synchronized (this) {
            Node previousNode = nodeAtIndex(index - 1);
            Node existingNode = previousNode.next;

            previousNode.next = nodeToBeAdded;
            nodeToBeAdded.next = existingNode;
            return this;
        }
    }
}
