package org.mickey.data.structure.linkedlist;

/**
 * 真正意义上的动态数据结构， 线性的
 *
 * @author mickey
 * @date 2020/6/4 17:36
 */
public class LinkedList<E> {

    private class Node {
        public E e;
        public Node next;

        public Node(E e, Node next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this(e, null);
        }

        public Node() {
            this(null, null);
        }

        @Override
        public String toString() {
            if (e != null) {
                return e.toString();
            }
            return null;
        }
    }

    private Node dummyHead;

    private int size;

    public LinkedList() {
        dummyHead = new Node(null, null);
        size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. The index required >=0 and <=size.");

        Node pre = dummyHead;
        // 移动 pre 到要插入的前一个节点
        for (int i = 0; i < index; i++)
            pre = pre.next;

        // Node node = new Node(e);
        // node.next = pre.next;
        // pre.next = node;

        pre.next = new Node(e, pre.next);
        size++;
    }

    public void addLast(E e) {
        add(size, e);
    }

    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get failed. The index required  >= 0 and < size.");

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        return cur.e;
    }

    public E getFirst() {
        return get(0);
    }

    public E getLast() {
        return get(size - 1);
    }

    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set Failed. The index required >=0 and <size.");

        Node cur = dummyHead.next;
        for (int i = 0; i < index; i++)
            cur = cur.next;
        cur.e = e;
    }

    public int find(E e) {
        // Node cur = dummyHead.next;
        // for (int i = 0; i < size; i++) {
        //     if (cur.e.equals(e))
        //         return i;
        //     cur = cur.next;
        // }
        // return -1;

        int index = 0;
        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            if (cur.e.equals(e)) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public boolean contains(E e) {
        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            if (cur.e.equals(e)) {
                return true;
            }
        }
        return false;
    }

    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Remove failed. The index required >=0 and <size");
        Node pre = dummyHead;
        for (int i = 0; i < index; i++)
            pre = pre.next;

        Node delNode = pre.next;
        pre.next = delNode.next;
        // delNode 与链表彻底脱离关系
        delNode.next = null;
        size--;
        return delNode.e;
    }

    public E removeFirst() {
        return remove(0);
    }

    public E removeLast() {
        return remove(size - 1);
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node cur = dummyHead.next;
        while (cur != null) {
            sb.append(cur.e).append(" ->");
            cur = cur.next;
        }
        sb.append(" NULL");
        return sb.toString();
    }


    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.addFirst(1);
        linkedList.addFirst(2);
        linkedList.addFirst(3);
        System.out.println(linkedList);

        linkedList.removeFirst();
        System.out.println(linkedList);
        linkedList.removeLast();
        System.out.println(linkedList);
    }
}
