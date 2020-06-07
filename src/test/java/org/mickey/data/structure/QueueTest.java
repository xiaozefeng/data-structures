package org.mickey.data.structure;

import org.junit.Test;
import org.mickey.data.structure.queue.ArrayQueue;
import org.mickey.data.structure.queue.LinkedListQueue;
import org.mickey.data.structure.queue.LoopQueue;
import org.mickey.data.structure.queue.Queue;

import java.util.Random;

/**
 * @author mickey
 * @date 2020/6/4 15:11
 */
public class QueueTest {


    @Test
    public void testQueue(){
        long opCount = 100000;
        Queue<Integer> arrayQueue = new ArrayQueue<>();
        System.out.println("ArrayQueue time:" + time(arrayQueue, opCount) + " s");

        Queue<Integer> loopQueue = new LoopQueue<>();
        System.out.println("LoopQueue time:" + time(loopQueue, opCount) + " s");

        Queue<Integer> linkedListQueue= new LinkedListQueue<>();
        System.out.println("LinkedListQueue time:" + time(linkedListQueue, opCount) + " s");




    }


    private static double time(Queue<Integer> q, long opCount) {
        long startTime = System.nanoTime();
        Random random = new Random();
        for (int i = 0; i < opCount; i++)
            q.enqueue(random.nextInt(Integer.MAX_VALUE));
        for (int i = 0; i < opCount; i++)
           q.dequeue();

        long endTime = System.nanoTime();
        return (endTime - startTime) / 1000000000.0;
    }
}
