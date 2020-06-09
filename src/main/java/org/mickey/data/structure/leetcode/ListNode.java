package org.mickey.data.structure.leetcode;

/**
 * @author mickey
 * @date 6/6/20 00:05
 */
public class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }


    public ListNode(int[] arr){
        this(arr[0]);
        ListNode cur = this;
        for (int i = 1; i < arr.length; i++) {
            cur.next = new ListNode(arr[i]);
            cur = cur.next;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (ListNode cur = this; cur != null; cur = cur.next) {
            sb.append(cur.val).append("->");
        }
        sb.append("NULL");
        return sb.toString();
    }

    public static void main(String[] args) {
        final ListNode listNode = new ListNode(new int[]{5, 1, 2, 3, 4});
        System.out.println(listNode);
    }
}
