package LeetCode.Tree;

import java.util.Stack;

/**
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * <p>
 * 有效 二叉搜索树定义如下：
 * <p>
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 */
public class IsValidBST {

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

    // 前一个节点，全局变量
    TreeNode prev;

    /**
     * 递归写法
     * <p>
     * 做这题之前我们首先要明白什么是二叉搜索树，
     * 就是每个节点左子树的值都比当前节点小，
     * 右子树的值都比当前节点大
     *
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    public boolean isValidBST(TreeNode root, long minVal, long maxVal) {
        if (root == null) return true;
        // 每个节点如果超过这个范围，直接返回false
        if (root.val > maxVal || root.val < minVal) return false;
        // 这里再分别以左右两个子节点分别判断，
        // 左子树范围的最小值是minVal，最大值是当前节点的值，也就是root的值，因为左子树的值要比当前节点小
        // 右子数范围的最大值是maxVal，最小值是当前节点的值，也就是root的值，因为右子树的值要比当前节点大
        return isValidBST(root.left, minVal, root.val) && isValidBST(root.right, root.val, maxVal);
    }

    /**
     * 中序遍历递归
     * <p>
     * 根据二叉搜索树的性质我们知道，中序遍历二叉搜索树，遍历的结果一定是有序的。
     * 中序遍历时，判断当前节点是否大于中序遍历的前一个节点，也就是判断是否有序，如果不大于直接返回 false。
     *
     * @param root
     * @return
     */
    public boolean isValidBST_Inorder_Traversal_Recursion(TreeNode root) {
        if (root == null) return true;
        // 访问左子树
        if (!isValidBST_Inorder_Traversal_Recursion(root.left)) return false;
        if (prev != null && prev.val >= root.val) return false;
        prev = root;
        // 访问右子树
        if (!isValidBST_Inorder_Traversal_Recursion(root.right)) return false;
        return true;
    }

    /**
     * 这里面也有树的中序遍历的递归和非递归两种写法。我们完全可以把上面中序遍历的递归改为非递归。
     *
     * @param root
     * @return
     */
    public boolean isValidBST_Inorder_Traversal(TreeNode root) {
        if (root == null) return true;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;

        while (root != null || !stack.isEmpty()) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (prev != null && prev.val >= root.val) return false;
            // 保存前一个访问的结点
            prev = root;
            root = root.right;
        }
        return true;
    }

}
