package org.mickey.data.structure.array;


import java.util.Arrays;

/**
 * @author mickey
 * @date 2020/6/2 14:28
 */
public class Array<E> {
    private Object[] data;
    private int size;

    public Array() {
        this(10);
    }

    public Array(int capacity) {
        data = new Object[capacity];
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void swap(int i, int j) {
        if ((i < 0 || i >= size) || j < 0 || j >= size)
            throw new IllegalArgumentException("illegal index");
        Object t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    public void addLast(E e) {
        this.add(size, e);
    }

    public void addFirst(E e) {
        this.add(0, e);
    }

    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. index required >=0 and <size.");

        if (size >= data.length)
            resize(data.length * 2);


        // for (int i = size - 1; i >= index; i--)
        //     data[i + 1] = data[i];

        if (size - index >= 0)
            System.arraycopy(data, index, data, index + 1, size - index);

        data[index] = e;
        size++;
    }

    private void resize(int newCapacity) {
        if (size >= 0)
            data = Arrays.copyOf(data, newCapacity);
    }

    public boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e)
                return true;
        }
        return false;
    }

    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i] == e) {
                return i;
            }
        }
        return -1;
    }

    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. The index required >=0 and <size");

        //noinspection unchecked
        return (E) data[index];
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public E remove(int index) {
        if (isEmpty())
            throw new IllegalArgumentException("Remove failed. The array is empty");

        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove Failed. The index required >=0 and < size.");

        @SuppressWarnings("unchecked") E ret = (E) data[index];

        // for (int i = index + 1; i < size; i++)
        //     data[i - 1] = data[i];

        if (size - (index + 1) >= 0)
            System.arraycopy(data, index + 1, data, index, size - (index + 1));

        size--;
        data[size] = null;

        if (data.length / 4 == size && data.length / 2 != 0)
            resize(data.length / 2);

        return ret;
    }

    public E removeLast() {
        return remove(size - 1);
    }

    public E removeFirst() {
        return remove(0);
    }


    public void removeElement(E e) {
        int index = find(e);
        if (index != -1) {
            remove(index);
        }
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Update failed. The index required >=0 and <size.");

        data[index] = e;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Array: size:%d, capacity:%d\n", size, data.length));
        sb.append('[');
        for (int i = 0; i < size; i++) {
            sb.append(data[i]);
            if (i != size - 1) {
                sb.append(',');
            }
        }
        sb.append(']');
        return sb.toString();
    }
}
