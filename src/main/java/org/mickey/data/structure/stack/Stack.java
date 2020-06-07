package org.mickey.data.structure.stack;

/**
 * @author mickey
 * @date 2020/6/3 17:54
 */
public interface Stack<E> {
    int getSize();

    boolean isEmpty();

    E peek();

    E pop();

    void push(E e);
}
