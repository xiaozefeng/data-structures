package org.mickey.data.structure;

import org.junit.Test;
import org.mickey.data.structure.stack.LinkedListStack;
import org.mickey.data.structure.stack.ArrayStack;
import org.mickey.data.structure.stack.Stack;

import java.util.Random;

/**
 * @author mickey
 * @date 2020/6/5 18:55
 */
public class StackTest {

    @Test
    public void test(){
        long opCount = 1000000;
        Stack<Integer> arrayStack = new ArrayStack<>();
        System.out.println("arrayStack, time= " + time(arrayStack, opCount) + " s");

        Stack<Integer> linkedStack = new LinkedListStack<>();
        System.out.println("linkedStack, time= " + time(linkedStack, opCount) + " s");
    }

    private static double time(Stack<Integer> stack, long opCount) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount; i++)
            stack.push(random.nextInt(Integer.MAX_VALUE));
        for (int i = 0; i < opCount; i++)
            stack.pop();

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
}
