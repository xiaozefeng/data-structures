package org.mickey.data.structure.tree;

import java.util.LinkedList;
import java.util.Stack;
import java.util.function.Consumer;

/**
 * @author mickey
 * @date 6/6/20 18:58
 */
public class BST<E extends Comparable<E>> {

    private class Node {
        public E e;
        public Node left, right;

        public Node(E e) {
            this.e = e;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            inOrder(this, e -> sb.append(e).append("->"));
            return sb.toString();
        }

    }

    private Node root;
    private int size;

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public BST() {
        root = null;
        size = 0;
    }

    public void add(E e) {
        root = add(root, e);
    }

    private Node add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node(e);
        }

        if (e.compareTo(node.e) < 0) {
            node.left = add(node.left, e);
        } else if (e.compareTo(node.e) > 0)
            node.right = add(node.right, e);

        return node;
    }

    private void inOrder(Node root, Consumer<E> c) {
        if (root == null) {
            return;
        }
        inOrder(root.left, c);
        c.accept(root.e);
        inOrder(root.right, c);
    }


    private void postOrder(Node root, Consumer<E> c) {
        if (root == null) {
            return;
        }
        postOrder(root.left, c);
        postOrder(root.right, c);
        c.accept(root.e);
    }


    private void preOrder(Node root, Consumer<E> c) {
        if (root == null) {
            return;
        }
        c.accept(root.e);
        preOrder(root.left, c);
        preOrder(root.right, c);
    }


    public boolean contains(E e) {
        return contains(root, e);
    }

    public boolean contains(Node node, E e) {
        if (node == null) {
            return false;
        }
        if (node.e.compareTo(e) == 0) {
            return true;
        } else if (e.compareTo(node.e) < 0) {
            return contains(node.left, e);
        } else {
            return contains(node.right, e);
        }

    }

    public void preOrderNR(Consumer<E> c) {
        //Stack<Node> stack = new ArrayStack<>();
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            final Node cur = stack.pop();
            if (cur == null) {
                continue;
            }
            //System.out.print(cur.e + "->");
            c.accept(cur.e);
            if (cur.right != null) {
                stack.push(cur.right);
            }
            if (cur.left != null) {
                stack.push(cur.left);
            }
        }
    }

    public void levelOrder(Consumer<E> c) {
        //Queue<Node> queue = new LinkedListQueue<>();
        LinkedList<Node> queue = new LinkedList<>();
        queue.addLast(root);
        while (!queue.isEmpty()) {
            final Node cur = queue.removeFirst();
            //System.out.print(cur.e + "->");
            c.accept(cur.e);
            if (cur.left != null)
                queue.addLast(cur.left);
            if (cur.right != null)
                queue.addLast(cur.right);
        }
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BST size:").append(size).append("\n");
        sb.append("pre order ");
        preOrder(root, (e) -> {
            sb.append(e).append("->");
        });
        sb.append("\n");

        sb.append("in order ");
        inOrder(root, e -> sb.append(e).append("->"));
        sb.append("\n");

        sb.append("post order ");
        postOrder(root, e -> sb.append(e).append("->"));

        return sb.toString();
    }


    public E minimum() {
        if (isEmpty()) {
            throw new IllegalArgumentException("The BST isEmpty!");
        }
        return minimum(root).e;
    }

    private Node minimum(Node node) {
        //if (node == null) {
        //    return null;
        //}
        //final E res = minimum(node.left);
        //return res == null ? node.e : res;
        if (node.left == null) {
            return node;
        }
        return minimum(node.left);

    }

    public E maximum() {
        if (isEmpty()) {
            throw new IllegalArgumentException("The BST isEmpty!");
        }
        return maximum(root).e;
    }

    private Node maximum(Node node) {
        //if (node == null) {
        //    return null;
        //}
        //final E res = maximum(node.right);
        //return res == null ? node.e : res;
        if (node.right == null) {
            return node;
        }
        return maximum(node.right);
    }

    public E removeMinimum() {
        final E ret = minimum();
        root = removeMinimum(root);
        return ret;
    }

    private Node removeMinimum(Node node) {
        if (node.left == null) {
            Node rightNode = node.right;
            node.right = null;
            size--;
            return rightNode;
        }
        node.left = removeMinimum(node.left);
        return node;
    }

    public E removeMaximum() {
        final E ret = maximum();
        root = removeMaximum(root);
        return ret;
    }

    private Node removeMaximum(Node node) {
        if (node.right == null) {
            Node leftNode = node.left;
            node.left = null;
            size--;
            return leftNode;
        }
        node.right = removeMaximum(node.right);
        return node;
    }

    public void remove(E e) {
        if (isEmpty()) {
            throw new IllegalArgumentException("The BST is empty!");
        }
        root = remove(root, e);
    }

    private Node remove(Node node, E e) {
        if (node == null) {
            return null;
        }

        if (e.compareTo(node.e) < 0) {
            node.left = remove(node.left, e);
            return node;
        } else if (e.compareTo(node.e) > 0) {
            node.right = remove(node.right, e);
            return node;
        } else {  // e == node.e
            if (node.left == null) {
                Node rightNode = node.right;
                node.right = null;
                size--;
                return rightNode;
            }

            if (node.right == null) {
                Node leftNode = node.left;
                node.left = null;
                size--;
                return leftNode;
            }

            // 左右子树都不为空
            Node successor = minimum(node.right);
            successor.right = removeMinimum(node.right);
            successor.left = node.left;

            node.left = node.right = null;

            return successor;
        }

    }


    public static void main(String[] args) {
        final BST<Integer> bst = new BST<>();
        bst.add(28);
        bst.add(16);
        bst.add(13);
        bst.add(22);
        bst.add(30);
        bst.add(29);
        bst.add(42);
        System.out.println(bst);
        //
        System.out.println("contains 30 = " + bst.contains(30));
        System.out.println("pre order nr ");
        bst.preOrderNR(e-> System.out.print(e+"->"));
        System.out.println();
        System.out.println("level order");
        bst.levelOrder(e -> System.out.print(e + "->"));
        System.out.println();
        System.out.println("minimum:" + bst.minimum());
        System.out.println("maximum:" + bst.maximum());

        System.out.println(bst.removeMinimum());
        //bst.removeMinimum();
        System.out.println(bst);
        bst.removeMaximum();
        System.out.println(bst);


        bst.remove(28);
        System.out.println(bst);
        bst.remove(16);
        System.out.println(bst);
        bst.remove(30);
        System.out.println(bst);
    }
}
