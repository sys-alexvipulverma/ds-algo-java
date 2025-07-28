package io.github.sysalexvipulverma.cursor;

import lombok.NonNull;

import java.util.Iterator;
import java.util.NoSuchElementException;

/*
 * Implement iterator design pattern (implementation of java.util.Iterator<E>)
 * to iterate over the elements of a plain array
 *
 * Asked in - American Express
 * */

public class PlainArrayIterator<E> implements Iterator<E> {
    private final E[] array;
    private int currentIndex;

    public PlainArrayIterator(@NonNull E[] array) {
        this(0, array);
    }

    public PlainArrayIterator(int startIndex, @NonNull E[] array) {
        if (startIndex < 0 || startIndex >= array.length) {
            throw new IndexOutOfBoundsException();
        }
        this.currentIndex = startIndex;

        // Defensive copy to prevent external modifications
        this.array = array.clone();
    }

    // below methods are mandatory to override if implementing Iterator<E>

    @Override
    public boolean hasNext() {
        return this.currentIndex < this.array.length;
    }

    @Override
    public E next() {
        if (hasNext()) {
            E element = array[currentIndex];
            currentIndex++;
            return element;
        }
        throw new NoSuchElementException();
    }

    // Extra methods
    public boolean hasPrevious() {
        return currentIndex > 0;
    }

    public E previous() {
        if (!hasPrevious()) {
            throw new NoSuchElementException();
        }
        currentIndex--;
        return array[currentIndex];
    }

    public void reset() {
        currentIndex = 0;
    }
}
