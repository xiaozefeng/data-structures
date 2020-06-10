package org.mickey.data.structure.heap;

import org.mickey.data.structure.array.Array;

import java.util.Random;

/**
 * @author mickey
 * @date 6/10/20 00:39
 */
public class MaxHeap<E extends Comparable<E>> {
    private final Array<E> data;

    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    public MaxHeap() {
        data = new Array<>();
    }

    public MaxHeap(E[] arr) {
        if (arr == null || arr.length == 0)
            throw new IllegalArgumentException("arr is null");

        data = new Array<>(arr);
        for (int i = parent(arr.length - 1); i >= 0; i--)
            siftDown(i);
    }

    public int getSize() {
        return data.getSize();
    }

    public boolean isEmpty() {
        return data.isEmpty();
    }

    private int parent(int index) {
        if (index == 0)
            throw new IllegalArgumentException("Index 0 Can't have parent!");

        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return index * 2 + 1;
    }

    private int rightChild(int index) {
        return index * 2 + 2;
    }

    private void siftUp(int k) {
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }


    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    private E findMax() {
        if (isEmpty())
            throw new IllegalArgumentException("Can't find max when heap is empty!");

        return data.getFirst();
    }

    public E extraMax() {
        E ret = findMax();
        data.swap(0, data.getSize() - 1);
        data.removeLast();
        siftDown(0);
        return ret;
    }

    private void siftDown(int k) {
        // 左孩子还在size之内, 左孩子没有越界，右孩子也不可能越界
        while (leftChild(k) < data.getSize()) {
            int j = leftChild(k);
            if (j + 1 < data.getSize() && data.get(j + 1).compareTo(data.get(j)) > 0)
                j = rightChild(k);
            //此时 data[j] 是 leftChild 和 rightChild 中的最大值
            // 拿k位置的值和 leftChild, rightChild 中的最大值做比较
            if (data.get(k).compareTo(data.get(j)) >= 0)
                break;

            // 否则交换 k 和 j
            data.swap(k, j);
            k = j;
        }
    }

    // 取出堆的最大值，再添加元素e
    public E replace(E e) {
        E ret = findMax();
        data.set(0, e);
        siftDown(0);
        return ret;
    }


    public static void main(String[] args) {
        int n = 1000000;
        MaxHeap<Integer> heap = new MaxHeap<>();
        Random random = new Random();
        for (int i = 0; i < n; i++)
            heap.add(random.nextInt(Integer.MAX_VALUE));

        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++)
            arr[i] = heap.extraMax();

        for (int i = 1; i < arr.length; i++) {
            if (arr[i - 1] < arr[i])
                throw new IllegalArgumentException("Error");
        }

        System.out.println("Test completed");

    }


}
