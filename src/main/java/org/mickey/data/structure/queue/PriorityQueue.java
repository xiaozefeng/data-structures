package org.mickey.data.structure.queue;

import org.mickey.data.structure.heap.MaxHeap;

/**
 * @author mickey
 * @date 6/13/20 21:50
 */
public class PriorityQueue<E extends Comparable<E>> implements  Queue<E> {

    private final MaxHeap<E> maxHeap;

    public PriorityQueue(){
        maxHeap = new MaxHeap<>();
    }

    @Override
    public int getSize() {
        return maxHeap.getSize();
    }

    @Override
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        maxHeap.add(e);
    }

    @Override
    public E dequeue() {
        return maxHeap.extraMax();
    }

    @Override
    public E getFront() {
        return maxHeap.findMax();
    }
}
