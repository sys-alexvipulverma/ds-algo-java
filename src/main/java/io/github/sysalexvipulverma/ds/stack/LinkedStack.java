package io.github.sysalexvipulverma.ds.stack;

import io.github.sysalexvipulverma.ds.list.SinglyLinkedList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<E> implements Stack<E>, Iterable<E> {
    private final SinglyLinkedList<E> linkedList;

    public LinkedStack() {
        this.linkedList = new SinglyLinkedList<>();
    }

    @Override
    public LinkedStack<E> push(E element) {
        linkedList.addFirst(element);
        return this;
    }

    @Override
    public E pop() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        E out = linkedList.first();
        linkedList.removeFirst();
        return out;
    }

    @Override
    public E peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return linkedList.first();
    }

    @Override
    public int size() {
        return linkedList.size();
    }

    @Override
    public String toString() {
        return linkedList.toString();
    }

    @Override
    public LinkedStack<E> clear() {
        linkedList.clear();
        return this;
    }

    @Override
    public Iterator<E> iterator() {
        return linkedList.iterator();
    }
}
