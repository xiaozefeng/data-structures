package org.mickey.data.structure.queue;

/**
 * @author mickey
 * @date 6/5/20 23:30
 */
public class LinkedListQueue<E> implements Queue<E> {


    private class Node {
        public E e;
        private Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

    }

    private Node head, tail;
    private int size;

    public LinkedListQueue() {
        head = null;
        tail = null;
        size = 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void enqueue(E e) {
        if (tail == null) {
            tail = new Node(e);
            head = tail;
        } else {
            tail.next = new Node(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {
        if (size == 0) {
            throw new IllegalArgumentException("Dequeue failed. The queue is empty.");
        }
        Node delNode = head;
        head = head.next;
        delNode.next = null;
        if (head == null)
            tail = null;
        size--;
        return delNode.e;
    }

    @Override
    public E getFront() {
        if (head != null) {
            return head.e;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("LinkedListQueue head[");
        for (Node cur = head; cur != null; cur = cur.next) {
            sb.append(cur.e).append(" ->");
        }
        sb.append(" NULL");
        return sb.toString();
    }

    public static void main(String[] args) {
        Queue<Integer> queue = new LinkedListQueue<>();
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
