package LeetCode.Tree;

import java.util.Deque;
import java.util.LinkedList;


/**
 * 给定一个二叉树，找出其最大深度。
 * <p>
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * <p>
 * 说明: 叶子节点是指没有子节点的节点。
 */
public class MaxDepth {

    /**
     * Definition for a binary tree node.
     */

    public class TreeNode {
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

    /**
     * 递归的方式实现
     *
     * @param root 根节点
     * @return 返回层数
     */
    public int maxDepth(TreeNode root) {
        return root == null ? 0 : Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    }

    /**
     * BFS的实现原理就是一层层遍历，统计一下总共有多少层
     *
     * @param root
     * @return
     */
    public int maxDepth_BFS(TreeNode root) {
        if (root == null) return 0;
        // 创建一个队列
        Deque<TreeNode> deque = new LinkedList<>();
        deque.push(root);
        int level = 0;
        while (!deque.isEmpty()) {
            // 每一层的个数
            int size = deque.size();
            while (size-- > 0) {
                TreeNode curr = deque.pop();
                if (curr.left != null) deque.addLast(curr.left);
                if (curr.right != null) deque.addLast(curr.right);
            }
            level++;
        }
        return level;
    }

}
