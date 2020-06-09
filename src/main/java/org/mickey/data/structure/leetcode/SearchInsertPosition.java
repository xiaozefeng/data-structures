package org.mickey.data.structure.leetcode;

/**
 * @author mickey
 * @date 2020/6/9 20:15
 */
public class SearchInsertPosition {

    public int searchInsert(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target)
                return i;
        }

        for (int i = nums.length-1; i >=0; i--) {
            if (target > nums[i]) {
                return i+1;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 6};
        System.out.println(new SearchInsertPosition().searchInsert(arr, 5));
        System.out.println(new SearchInsertPosition().searchInsert(arr, 2));
        System.out.println(new SearchInsertPosition().searchInsert(arr, 7));
        System.out.println(new SearchInsertPosition().searchInsert(arr, 0));
    }
}
