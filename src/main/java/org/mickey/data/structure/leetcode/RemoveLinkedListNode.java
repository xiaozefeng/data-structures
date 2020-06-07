package org.mickey.data.structure.leetcode;


/**
 * @author mickey
 * @date 6/6/20 00:05
 */
public class RemoveLinkedListNode {

    public ListNode removeElements(ListNode head, int val) {
        while (head != null && head.val == val) {
            ListNode del = head;
            head = head.next;
            del.next = null;
        }

        if (head == null) {
            return null;
        }

        ListNode pre = head;
        while (pre.next != null) {
            if (pre.next.val == val) {
                ListNode del = pre.next;
                pre.next = pre.next.next;
                del.next = null;
            } else {
                pre = pre.next;
            }
        }
        return head;
    }

    public ListNode removeElements2(ListNode head, int val) {
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode pre = dummyHead;
        while (pre.next != null) {
            if (pre.next.val == val) {
                ListNode del = pre.next;
                pre.next = del.next;
                del.next = null;
            } else {
                pre = pre.next;

            }
        }
        return dummyHead.next;
    }


    public ListNode removeElements3(ListNode node, int val) {
        if (node == null) {
            return null;
        }
        //ListNode res = removeElements3(node.next, val);
        //if (node.val == val) {
        //    return res;
        //} else {
        //    node.next = res;
        //    return node;
        //}
        node.next = removeElements3(node.next, val);
        return node.val == val ? node.next : node;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{4, 2, 3, 4};
        final ListNode l1 = new ListNode(arr);
        final ListNode l2 = new ListNode(arr);
        final ListNode l3 = new ListNode(arr);

        final ListNode l4 = new RemoveLinkedListNode().removeElements(l1, 4);
        final ListNode l5 = new RemoveLinkedListNode().removeElements2(l2, 4);
        final ListNode l6 = new RemoveLinkedListNode().removeElements3(l3, 4);
        System.out.println("l4:" + l4);
        System.out.println("l5:" + l5);
        System.out.println("l6:" + l6);

    }

}


