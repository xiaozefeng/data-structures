package org.mickey.data.structure.leetcode;


/**
 * @author mickey
 * @date 2020/6/9 16:48
 */
public class RemoveDuplicatesFromLinkedList_II {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummyHead = new ListNode(-1, head);
        ListNode pre = dummyHead;
        while (pre.next != null) {
            if (count(pre.next, pre.next.val) > 1)
                pre.next = removeElements2(pre.next, pre.next.val);
            else
                pre = pre.next;
        }
        return dummyHead.next;
    }

    private int count(ListNode head, int val) {
        ListNode cur = head;
        int count = 0;
        while (cur != null) {
            if (cur.val == val) {
                count++;
            }
            cur = cur.next;
        }
        return count;
    }


    private ListNode removeElements2(ListNode head, int val) {
        ListNode dummyNode = new ListNode(-1, head);
        ListNode pre = dummyNode;
        while (pre.next != null) {
            if (pre.next.val == val) {
                ListNode del = pre.next;
                pre.next = del.next;
                del.next = null;
            }else {
                pre = pre.next;
            }
        }
        return dummyNode.next;
    }
}
