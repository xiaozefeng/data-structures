package org.mickey.data.structure.stack;

/**
 * @author mickey
 * @date 2020/6/3 18:02
 */
public class StackTest {

    public static void main(String[] args) {
        Stack<Integer> stack = new ArrayStack<>();
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        System.out.println(stack);
        stack.pop();
        System.out.println(stack);
    }
}
