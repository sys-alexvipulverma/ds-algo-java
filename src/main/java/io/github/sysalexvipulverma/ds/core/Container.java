package io.github.sysalexvipulverma.ds.core;

/*
 * Similar to java.util.Collection<E>, this interface will act like
 * root interface for most of the data structures
 * */

import lombok.NonNull;

import java.util.Iterator;

public interface Container<E> extends Iterable<E> {

    /*
     * This utility method can be used in overridden versions of
     * data structures like SinglyLinkedList, DynamicArray etc.
     * */
    static <T> String toString(@NonNull Iterable<T> iterable) {
        StringBuilder builder = new StringBuilder();

        builder.append("[");

        for (T element : iterable) {

            if (null == element) {
                builder.append("null");
            } else {
                builder.append(element);
            }
            builder.append(", ");
        }

        builder.append("]");
        return builder.toString().replace(", ]", "]");
    }

    // Adding elements
    Container<E> add(E element);

    default Container<E> addAll(@NonNull Iterable<E> iterable) {
        synchronized (this) {
            for (E element : iterable) {
                add(element);
            }
            return this;
        }
    }

    default Container<E> addAll(@NonNull Iterator<E> iterator) {
        /*
         * default methods can't be used as synchronized. So using a
         * synchronized block here with lock of current object
         * */

        synchronized (this) {
            while (iterator.hasNext()) {
                add(iterator.next());
            }
            return this;
        }
    }

    default Container<E> addAll(E... elements) {
        synchronized (this) {
            for (E element : elements) {
                add(element);
            }
            return this;
        }
    }

    // Removing elements
    Container<E> remove(E element);

    // Searching elements
    boolean contains(E element);

    int size();

    default boolean isEmpty() {
        return 0 == size();
    }
}
