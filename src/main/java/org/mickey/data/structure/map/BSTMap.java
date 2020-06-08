package org.mickey.data.structure.map;

import java.util.function.Consumer;

/**
 * @author mickey
 * @date 6/9/20 02:54
 */
public class BSTMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Node {
        public K k;
        public V v;
        private Node left;
        private Node right;

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
        }
    }

    private Node root;
    private int size;

    public BSTMap() {
        root = null;
        size = 0;
    }

    private Node getNode(K k) {
        return getNode(root, k);
    }

    private Node getNode(Node node, K k) {
        if (node == null) {
            return null;
        }
        if (k.compareTo(node.k) == 0)
            return node;
        else if (k.compareTo(node.k) < 0)
            return getNode(node.left, k);
        else // k.compareTo(node.k) >0
            return getNode(node.right, k);

    }

    @Override
    public void set(K k, V newValue) {
        Node node = getNode(k);
        if (node == null)
            throw new IllegalArgumentException(k + " doesn't exist!");

        node.v = newValue;
    }

    @Override
    public void add(K k, V v) {
        root = add(root, k, v);
    }

    private Node add(Node node, K k, V v) {
        if (node == null) {
            size++;
            return new Node(k, v);
        }
        if (k.compareTo(node.k) < 0)
            node.left = add(node.left, k, v);
        else if (k.compareTo(node.k) > 0)
            node.right = add(node.right, k, v);
        else
            node.v = v;

        return node;

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
    public void remove(K k) {
        if (isEmpty())
            throw new IllegalArgumentException("remove failed. the map is empty!");

        root = remove(root, k);
    }

    private Node remove(Node node, K k) {
        if (node == null) {
            return null;
        }
        if (k.compareTo(node.k) < 0) {
            node.left = remove(node.left, k);
            return node;
        } else if (k.compareTo(node.k) > 0) {
            node.right = remove(node.right, k);
            return node;
        } else {
            // 如果左子树为空 ，拿右子树
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            // 如果右子树为空，拿左子树
            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 如果两个子树都不为空，拿后继
            Node successor = minimum(node.right);
            successor.left = node.left;
            successor.right = removeMin(node.right);

            node.left = node.right = null;

            return successor;
        }
    }

    private Node minimum(Node node) {
        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    private Node maximum(Node node) {
        if (node.right == null)
            return node;

        return maximum(node.right);
    }

    private Node removeMin(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMin(node.left);
        return node;
    }

    private Node removeMax(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMax(node.right);
        return node;
    }


    @Override
    public boolean contains(K k) {
        return getNode(k) != null;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Map:");
        inOrder(root, e -> sb.append("key:").append(e.k).append(",value:").append(e.v).append("  "));
        return sb.toString();
    }

    private void inOrder(Node node, Consumer<Node> c) {
        if (node == null) {
            return;
        }
        inOrder(node.left, c);
        c.accept(node);
        inOrder(node.right, c);
    }

    public static void main(String[] args) {
        Map<String, Integer> map = new BSTMap<>();
        map.add("jack", 1);
        map.add("rose", 2);
        map.add("lucy", 3);
        System.out.println(map);
        System.out.println("contains jack:" +  map.contains("jack"));
        System.out.println("map size:" + map.getSize());
        System.out.println("get jack:" + map.get("jack"));

        map.remove("lucy");
        System.out.println(map);
    }
}
