package org.mickey.data.structure;

import org.junit.Test;
import org.mickey.data.structure.heap.MaxHeap;

import java.util.Random;

/**
 * @author mickey
 * @date 2020/6/10 17:18
 */
public class HeapTest {


    private static double time(Integer[] arr, boolean heapify) {
        long start = System.nanoTime();
        MaxHeap<Integer> maxHeap = null;
        if (heapify)
            maxHeap = new MaxHeap<>(arr);
        else {
            maxHeap = new MaxHeap<>();
            for (Integer num : arr) {
                maxHeap.add(num);
            }
        }

        int[] nums = new int[arr.length];
        for (int i = 0; i < arr.length; i++)
            nums[i] = maxHeap.extraMax();

        for (int i = 1; i < nums.length; i++) {
            if (nums[i-1] <nums[i])
                throw new IllegalArgumentException("Error");
        }


        long end = System.nanoTime();

        return (end - start) / 1000000000.0;
    }

    @Test
    public void test() {
        int count = 1000000;
        Integer[] arr = new Integer[count];
        Random random = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = random.nextInt(Integer.MAX_VALUE);
        }
        System.out.println("no heapify: " + time(arr, false));
        System.out.println("heapify: " + time(arr, true));
    }

}
