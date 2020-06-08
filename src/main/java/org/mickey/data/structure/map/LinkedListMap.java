package org.mickey.data.structure.map;

import org.mickey.data.structure.set.FileOperation;

import java.util.List;

/**
 * @author mickey
 * @date 6/8/20 22:45
 */
public class LinkedListMap<K, V> implements Map<K, V> {

    private class Node {
        public K k;
        public V v;
        public Node next;

        public Node(K k, V v, Node next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }

        public Node() {
            this(null, null, null);
        }

        public Node(K k, V v) {
            this(k, v, null);
        }

        @Override
        public String toString() {
            return "Node: k=" + this.k + ", v=" + this.v;
        }
    }


    private final Node dummyHead;
    private int size;

    public LinkedListMap() {
        dummyHead = new Node();
        size = 0;
    }

    private Node getNode(K k) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.k.equals(k))
                return cur;
            else
                cur = cur.next;
        }
        return null;
    }

    @Override
    public void set(K k, V newValue) {
        //if (isEmpty())
        //    throw new IllegalArgumentException("Can't set value to empty map!");
        //
        //Node cur = dummyHead.next;
        //while (cur != null) {
        //    if (cur.k.equals(k))
        //        cur.v = newValue;
        //    else
        //        cur = cur.next;
        //}
        Node node = getNode(k);
        if (node == null)
            throw new IllegalArgumentException(k + "doesn't exist!");

        node.v = newValue;

    }

    @Override
    public void add(K k, V v) {
        //Node pre = dummyHead;
        //while (pre.next != null) {
        //    if (pre.next.k.equals(k))
        //        pre.next.v = v;
        //    else
        //        pre = pre.next;
        //}
        //pre.next = new Node(k, v);
        //size++;
        Node node = getNode(k);
        if (node == null) {
            dummyHead.next = new Node(k, v, dummyHead.next);
            size++;
        } else
            node.v = v;
    }

    @Override
    public V get(K k) {
        Node node = getNode(k);
        return node == null ? null : node.v;
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
    public V remove(K k) {
        if (isEmpty())
            throw new IllegalArgumentException("Can't Remove Element from empty map!");

        Node pre = dummyHead;
        while (pre.next != null) {
            if (pre.next.k.equals(k)) {
                Node del = pre.next;
                pre.next = del.next;
                del.next = null;
                size--;
                return del.v;
            } else
                pre = pre.next;
        }
        return null;

        // 递归删除的逻辑
        //dummyHead.next = remove(dummyHead.next, k);
        //return null;
    }

    // 删除以node节点为头的链表中的元素，递归算法
    // 返回删除 元素k 后的链表的头节点
    private Node remove(Node node, K k) {
        if (node == null) {
            return null;
        }
        if (node.k.equals(k))
            return node.next;
        node.next = remove(node.next, k);
        return node;
    }

    @Override
    public boolean contains(K k) {
        if (isEmpty())
            return false;
        return getNode(k) != null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Map: ");
        for (Node cur = dummyHead.next; cur != null; cur = cur.next) {
            sb.append("k=").append(cur.k).append(" v=" + cur.v).append(", ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        List<String> words = FileOperation.readFromFile(System.getProperty("user.dir") + "/pride-and-prejudice.txt");
        Map<String, Integer> map = new LinkedListMap<>();
        for (String word : words) {
            if (map.contains(word))
                map.set(word, map.get(word) +1);
            else
                map.add(word, 1);
        }
        System.out.println("Total different words: " + map.getSize());
        System.out.println("Frequency of pride: " + map.get("pride"));
    }

    private static void validate() {
        Map<String, Integer> map = new LinkedListMap<>();
        map.add("jack", 98);
        map.add("rose", 99);
        map.add("lucy", 100);
        System.out.println(map);

        System.out.println("contains jack:" + map.contains("jack"));
        System.out.println("contains rose:" + map.contains("rose"));
        System.out.println("contains lucy:" + map.contains("lucy"));

        System.out.println("get jack: " + map.get("jack"));
        map.remove("jack");
        System.out.println(map);
    }
}
