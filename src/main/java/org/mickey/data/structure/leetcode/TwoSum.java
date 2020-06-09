package org.mickey.data.structure.leetcode;

import java.util.Arrays;

/**
 * @author mickey
 * @date 2020/6/9 20:28
 */
public class TwoSum {
    public int[] twoSum(int[] numbers, int target) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i + 1; j < numbers.length; j++) {
                if (numbers[i] + numbers[j] == target) {
                    return new int[]{i+1, j+1};
                }
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] arr = {2, 7, 11, 15};
        int[] res = new TwoSum().twoSum(arr, 9);
        System.out.println(Arrays.toString(res));
    }
}
