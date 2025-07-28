package io.github.sysalexvipulverma.ds.stack;

import java.util.NoSuchElementException;

public interface Stack<E> {

    /*
     * push() operation, add at the top of stack
     * Whenever this method will be called you need to add the element at the top
     * (as the first element) of the stack.
     * */
    Stack<E> push(E element);

    /*
     * pop() operation, remove and return the element which is at the top of stack
     * throws java.util.NoSuchElementException if the stack is empty
     * */
    E pop() throws NoSuchElementException;

    /*
     * peek() operation, used to read (but not remove) the first element of stack
     * throws java.util.NoSuchElementException if the stack is empty
     * */
    E peek() throws NoSuchElementException;


    int size();

    default boolean isEmpty() {
        return 0 == size();
    }

    Stack<E> clear();
}
