package org.mickey.data.structure.leetcode;


import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

/**
 * @author mickey
 * @date 2020/6/9 19:44
 */
public class RemoveDuplicatesFromSortedArray {

    public int removeDuplicates(int[] nums) {
        Set<Integer> set = new TreeSet<>();
        for (int num : nums)
            set.add(num);
        System.out.println(set);

        int index = 0;
        for (Integer num : set) {
            nums[index] = num;
            index++;
        }
        return set.size();
    }

    public static void main(String[] args) {
        int[] arr = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        int length = new RemoveDuplicatesFromSortedArray().removeDuplicates(arr);
        System.out.println(length);
        System.out.println(Arrays.toString(arr));

    }
}
