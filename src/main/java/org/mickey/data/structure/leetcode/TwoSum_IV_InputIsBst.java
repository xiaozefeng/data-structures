package org.mickey.data.structure.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author mickey
 * @date 2020/6/9 20:33
 */
public class TwoSum_IV_InputIsBst {
    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public boolean findTarget(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        inOrder(root, e -> list.add(e.val));
        for (int i = 0; i < list.size(); i++) {
            for (int j = i+1; j < list.size(); j++) {
                if ((list.get(i) + list.get(j)) == k)
                    return true;
            }
        }
        return false;
    }

    private void inOrder(TreeNode node, Consumer<TreeNode> c) {
        if (node== null) {
            return;
        }
        inOrder(node.left,c);
        c.accept(node);
        inOrder(node.right, c);
    }

}
