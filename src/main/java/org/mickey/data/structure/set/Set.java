package org.mickey.data.structure.set;

/**
 * @author mickey
 * @date 2020/6/8 14:51
 */
public interface Set<E> {

    void add(E e);

    void remove(E e);

    boolean contains(E e);

    int getSize();

    boolean isEmpty();

}
