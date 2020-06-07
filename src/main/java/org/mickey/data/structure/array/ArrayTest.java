package org.mickey.data.structure.array;

/**
 * @author mickey
 * @date 2020/6/2 14:55
 */
public class ArrayTest {
    public static void main(String[] args) {
        Array<Integer> arr = new Array<>(10);
        for (int i = 0; i <10; i++) {
            arr.addLast(i);
        }
        System.out.println(arr);
        arr.add(2, 100);
        System.out.println(arr);
        arr.addFirst(-1);
        System.out.println(arr);
        System.out.println("get index: " + arr.get(3));
        System.out.println("get first: "+ arr.getFirst());
        System.out.println("get last: "+ arr.getLast());

        arr.remove(3);
        System.out.println(arr);

        arr.removeFirst();
        System.out.println(arr);
        arr.removeLast();
        System.out.println(arr);

        arr.removeElement(8);
        arr.removeLast();
        arr.removeLast();
        arr.removeLast();
        System.out.println(arr);
    }
}
