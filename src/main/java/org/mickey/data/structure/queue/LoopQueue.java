package org.mickey.data.structure.queue;

/**
 * 循环队列是为了解决 ArrayQueue的性能问题的，每次出队操作是 removeFirst 是 O(n)的时间复杂度
 * 通过 front 和 tail 分别指向头和尾， 入队只需要移动tail ，出队只需要移动 front
 * front == tail 时队列为空
 * (tail+1) % capacity == front 队列满了， 这里有意识的浪费了一个空间, 所以在申请数组大小时应该多申请一个空间, 在扩容时也是
 *
 * @author mickey
 * @date 2020/6/4 10:27
 */
public class LoopQueue<E> implements Queue<E> {

    private Object[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity) {
        data = new Object[capacity + 1];
        front = 0;
        tail = 0;
        size = 0;
    }

    public LoopQueue() {
        this(10);
    }

    public int getCapacity() {
        return data.length - 1;
    }


    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public void enqueue(E e) {
        if (front == (tail + 1) % data.length)
            resize( getCapacity() <<1);

        data[tail] = e;
        tail = (tail + 1) % data.length;
        size++;
    }

    private void resize(int newCapacity) {
        Object[] newData = new Object[newCapacity + 1];

        // 将原来的元素重新拿出来，放入新的数组，新的数组，从0开始
        for (int i = 0; i < size; i++)
            newData[i] = data[(front + i) % data.length];

        front = 0;
        tail = size;
        data = newData;
    }

    @Override
    public E dequeue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Cannot dequeue from an empty queue.");
        }

        @SuppressWarnings("unchecked") E ret = (E) data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;

        if (getCapacity() / 4 == size && getCapacity() >> 1 != 0)
            resize(getCapacity() >> 1);

        return ret;
    }

    @Override
    public E getFront() {
        //noinspection unchecked
        return (E) data[front];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Queue size=%d, capacity=%d", size, getCapacity()));
        sb.append(" front[");
        for (int i = front; i != tail; i = (i + 1) % data.length) {
            sb.append(data[i]);
            if ((i + 1) % data.length != tail) {
                sb.append(",");
            }
        }
        sb.append("] tail");
        return sb.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LoopQueue<>(3);
        for (int i = 0; i < 10; i++) {
            queue.enqueue(i);
            System.out.println(queue);
            if (i % 3 == 2) {
                queue.dequeue();
                System.out.println(queue);
            }
        }

    }

}
