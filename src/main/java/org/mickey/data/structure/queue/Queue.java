package org.mickey.data.structure.queue;

/**
 * @author mickey
 * @date 2020/6/4 10:17
 */
public interface Queue<E> {
    int getSize();

    boolean isEmpty();

    void enqueue(E e);

    E dequeue();

    E getFront();
}
