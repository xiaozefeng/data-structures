package org.mickey.data.structure.stack;

import org.mickey.data.structure.linkedlist.LinkedList;

/**
 * @author mickey
 * @date 2020/6/5 18:43
 */
public class LinkedListStack<E> implements Stack<E> {

    private LinkedList<E> linkedList;

    public LinkedListStack() {
        linkedList = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return linkedList.getSize();
    }

    @Override
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }

    @Override
    public E peek() {
        return linkedList.getFirst();
    }

    @Override
    public E pop() {
        return linkedList.removeFirst();
    }

    @Override
    public void push(E e) {
        linkedList.addFirst(e);
    }

    @Override
    public String toString() {
        return "Stack " +
                linkedList;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new LinkedListStack<>();
        for (int i = 0; i < 10; i++) {
            stack.push(i);
            System.out.println(stack);
            if (i % 3 == 2) {
                stack.pop();
                System.out.println(stack);
            }
        }
    }

}
