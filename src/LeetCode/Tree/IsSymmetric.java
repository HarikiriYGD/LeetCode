package LeetCode.Tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 给定一个二叉树，检查它是否是镜像对称的。
 */
public class IsSymmetric {
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
     * 递归的方法
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        // 从两个子节点开始判断
        return isSymmetric_Recursion(root.left, root.right);
    }

    public boolean isSymmetric_Recursion(TreeNode left, TreeNode right) {
        // 如果都是叶子节点则表明是对称的
        if (left == null && right == null) return true;
        // 如果当前节点只有一个子节点或者有两个子节点，但两个子节点的值不相同，直接返回false
        if (left == null || right == null || left.val != right.val) return false;
        // 然后左子节点的左子节点和右子节点的右子节点比较，左子节点的右子节点和右子节点的左子节点比较
        return isSymmetric_Recursion(left.left, right.right) && isSymmetric_Recursion(left.right, right.left);
    }

    /**
     * 非递归的方法
     *
     * @param root
     * @return
     */
    public boolean isSymmetric_NOT_Recursion(TreeNode root) {
        if (root == null) return true;
        // 建立一个队列存储元素
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root.left);
        queue.add(root.right);
        // 队列中还有元素就继续遍历
        while (!queue.isEmpty()) {
            // 每两个出队
            TreeNode left = queue.poll(), right = queue.poll();
            // 如果都为空继续循环
            if (left == null && right == null) continue;
            // 如果一个为空一个不为空，说明不是对称的，直接返回false
            if (left == null || right == null) return false;
            if (left.val != right.val) return false;
            // 这里要记住入队的顺序，他会每两个两个的出队。
            // 左子节点的左子节点和右子节点的右子节点同时
            // 入队，因为他俩会同时比较。
            // 左子节点的右子节点和右子节点的左子节点同时入队，
            // 因为他俩会同时比较
            queue.add(left.left);
            queue.add(right.right);
            queue.add(left.right);
            queue.add(right.left);
        }
        return true;
    }

}
