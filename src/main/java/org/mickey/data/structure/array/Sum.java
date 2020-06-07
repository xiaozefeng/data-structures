package org.mickey.data.structure.array;

/**
 * @author mickey
 * @date 6/6/20 13:42
 */
public class Sum {

    public static void main(String[] args) {
        final int result = new Sum().sum(new int[]{1, 2, 3});
        System.out.println(result);
    }

    public int sum(int[] arr) {
        //return sum(arr, 0);
        return a(arr, 0);
    }

    private int sum(int[] arr, int leftIndex) {
        if (leftIndex == arr.length) {
            return 0;
        }
        return arr[leftIndex] + sum(arr, leftIndex + 1);
    }

    private int a(int[] arr, int l) {
        if (l== arr.length) {
            return 0;
        }
        return arr[l] + b(arr, l+ 1);
    }

    private int b(int[] arr, int l) {
        if (l== arr.length) {
            return 0;
        }
        return arr[l] + c(arr, l+ 1);
    }

    private int c(int[] arr, int l) {
        if (l== arr.length) {
            return 0;
        }
        return arr[l] + d(arr, l+ 1);
    }


    private int d(int[] arr, int l) {
        if (l== arr.length) {
            return 0;
        }

        return arr[l];
    }



}
