package io.github.sysalexvipulverma.ds.list;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class DoublyLinkedList<E> extends AbstractDoublyLinkedList<E> {

    /*
     * Helper method to remove a middle node
     * Given that this method will not be called using any null node
     * */
    private synchronized void removeMiddleNode(Node nodeToBeRemoved) {
        if (head == nodeToBeRemoved) {
            removeFirst();
            return;
        }

        if (tail == nodeToBeRemoved) {
            removeLast();
            return;
        }

        nodeToBeRemoved.element = null;
        Node previous = nodeToBeRemoved.previous;
        Node next = nodeToBeRemoved.next;

        previous.next = next;
        next.previous = previous;
        --currentSize;
    }

    /*
     * Reducing time complexity of nodeAtIndex() from O(n) to O(n / 2)
     * using middle index
     * */
    private Node nodeAtIndex(int index) {
        int middle = currentSize / 2;
        int currentIndex;

        if (middle < index) {
            // start from head

            currentIndex = 0;
            for (Node currentNode = head; currentNode != null; currentNode = currentNode.next, ++currentIndex) {
                if (currentIndex == index) {
                    return currentNode;
                }
            }
        } else {
            // start from tail

            currentIndex = currentSize - 1;
            for (Node currentNode = tail; currentNode != null; currentNode = currentNode.previous, --currentIndex) {
                if (currentIndex == index) {
                    return currentNode;
                }
            }
        }
        return null;
    }

    @Override
    public int size() {
        return currentSize;
    }

    @Override
    public synchronized DoublyLinkedList<E> clear() {
        Node currentNode = head;

        while (currentNode != null) {
            Node nextNode = currentNode.next;
            currentNode.element = null;
            currentNode.next = null;
            currentNode.previous = null;
            currentNode = nextNode;
        }
        head = tail = null;
        currentSize = 0;
        return this;
    }

    @Override
    public Iterator<E> iterator() {

        /*
         * The below feature was introduced in Java 9
         * Using diamond operator <> in anonymous inner classes
         * */

        return new Iterator<E>() {
            private Node currentNode = head;

            @Override
            public boolean hasNext() {
                return currentNode != null;
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
    public DoublyLinkedList<E> add(E element) {
        addLast(element);
        return this;
    }

    @Override
    public synchronized DoublyLinkedList<E> remove(E element) {
        for (Node currentNode = head; currentNode != null; currentNode = currentNode.next) {
            if (Objects.equals(element, currentNode.element)) {
                removeMiddleNode(currentNode);
                return this;
            }
        }
        return this;
    }

    @Override
    public boolean contains(E element) {
        for (Node currentNode = head; currentNode != null; currentNode = currentNode.next) {
            if (Objects.equals(element, currentNode.element)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int indexOf(E element) {
        int index = 0;

        for (Node currentNode = head; currentNode != null; currentNode = currentNode.next, index++) {
            if (Objects.equals(element, currentNode.element)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E element) {
        int lastIndex = currentSize - 1;

        for (Node currentNode = tail; currentNode != null; currentNode = currentNode.previous, --lastIndex) {
            if (Objects.equals(element, currentNode.element)) {
                return lastIndex;
            }
        }
        return -1;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= currentSize) {
            throw new IndexOutOfBoundsException();
        }
        return nodeAtIndex(index).element;
    }

    @Override
    public DoublyLinkedList<E> set(int index, E newElement) {
        if (index < 0 || index >= currentSize) {
            throw new IndexOutOfBoundsException();
        }
        nodeAtIndex(index).element = newElement;
        return this;
    }

    @Override
    public synchronized DoublyLinkedList<E> remove(int index) {
        if (index < 0 || index >= currentSize) {
            throw new IndexOutOfBoundsException();
        }

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
    public DoublyLinkedList<E> add(int index, E element) {
        Node nodeToBeAdded = new Node(element);

        if (0 == index) {
            addFirst(element);
            return this;
        }

        if (index == (currentSize - 1)) {
            addLast(element);
            return this;
        }

        synchronized (this) {
            Node previous = nodeAtIndex(index);
            Node next = previous.next;

            nodeToBeAdded.previous = previous;
            nodeToBeAdded.next = next;

            previous.next = nodeToBeAdded;
            next.previous = nodeToBeAdded;
            return this;
        }
    }
}
