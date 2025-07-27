package io.github.sysalexvipulverma.ds.list;

import io.github.sysalexvipulverma.ds.core.Container;

public interface IndexedContainer<E> extends Container<E> {

    // adding elements
    IndexedContainer<E> add(int index, E element);

    // removing elements
    IndexedContainer<E> remove(int index);

    // searching elements
    E get(int index);

    int indexOf(E element);

    int lastIndexOf(E element);

    // modifying elements
    IndexedContainer<E> set(int index, E newElement);
}
