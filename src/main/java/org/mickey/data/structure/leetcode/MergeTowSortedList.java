package org.mickey.data.structure.leetcode;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author mickey
 * @date 2020/6/9 18:12
 */
public class MergeTowSortedList {

    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        Map<Integer, Integer> map = new TreeMap<>(Comparator.reverseOrder());
        insertToMap(map, l1);
        insertToMap(map, l2);
        ListNode dummyNode = new ListNode(-1, null);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            for (int i = 0; i < entry.getValue(); i++) {
                inert(dummyNode, entry.getKey());
            }
        }
        return dummyNode.next;
    }

    private void insertToMap(Map<Integer, Integer> map, ListNode node) {
        for (ListNode cur = node; cur != null; cur = cur.next) {
            if (map.containsKey(cur.val))
                map.put(cur.val, map.get(cur.val) + 1);
            else
                map.put(cur.val, 1);
        }
    }

    // 插入元素
    private void inert(ListNode dummyNode, int val) {
        if (dummyNode.next == null) {
            dummyNode.next = new ListNode(val);
            return;
        }
        if (val < dummyNode.next.val) {
            dummyNode.next = new ListNode(val, dummyNode.next);
            return;
        }

        ListNode cur = dummyNode.next;
        while (cur != null) {
            if (cur.val >= val) {
                cur.next = new ListNode(val, cur.next);
                break;
            }else
                cur = cur.next;
        }

    }

    public static void main(String[] args) {
        ListNode l1 = new ListNode(new int[]{-1,-9, 2, 4});
        ListNode l2 = new ListNode(new int[]{1, 3, 4});
        ListNode res = new MergeTowSortedList().mergeTwoLists(l1, l2);
        System.out.println(res);

        Map<Integer, Integer> map = new TreeMap<>(Comparator.reverseOrder());
        map.put(-1, 2);
        map.put(-2, 2);
        System.out.println(map);

    }
}
