package LeetCode.Recursion;

import java.util.Stack;

/**
 * @Auther: Lil_boat
 * @Date: 16:08 2021/12/9
 * @Description: 给你二叉树的根节点 root 和一个表示目标和的整数 targetSum 。判断该树中是否存在 根节点到叶子节点 的路径，这条路径上所有节点值相加等于目标和 targetSum 。如果存在，返回 true ；否则，返回 false 。
 * <p>
 * 叶子节点 是指没有子节点的节点。
 */
public class HasPathSum {

    /**
     * 递归的方式
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum(TreeNode root, int targetSum) {
        // 如果根节点为空，或者叶子节点也遍历完了也没找到这样的结果，就返回false
        if (root == null) {
            return false;
        }
        // 如果到叶子节点了，并且剩余值等于叶子节点的值，说明找到了这样的结果，直接返回true
        if (root.left == null && root.right == null) {
            return root.val == targetSum;
        }
        // 分别沿着左右子节点走下去，然后顺便把当前节点的值减掉，左右子节点只要有一个返回true，
        // 说明存在这样的结果
        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);

    }

    /**
     * 非递归的方式
     * 在遍历的时候有两种方式，一种是从0开始累加，到叶子节点的时候如果累加的值等于sum，
     * 说明存在这样的一条路径。还一种是减，从根节点一直减下去，如果到叶子节点的时候，
     * 值等于叶子节点的值，说明也存在这样的一条路径。
     *
     * @param root
     * @param targetSum
     * @return
     */
    public boolean hasPathSum_NonRecusion(TreeNode root, int targetSum) {
        if (root == null) return false;
        Stack<TreeNode> stack = new Stack<>();
        // 将根结点入栈
        stack.push(root);
        while (!stack.isEmpty()) {
            // 目前的结点弹出
            TreeNode curr = stack.pop();
            // 判断是否为叶子结点
            if (curr.right == null && curr.left == null) {
                if (curr.val == targetSum) return true;
            }
            // 如果左子树不为空，则入栈
            if (curr.left != null) {
                curr.left.val = curr.left.val + curr.val;
                stack.push(curr.left);
            }
            // 如果右子树不为空，则入栈
            if (curr.right != null) {
                curr.right.val = curr.right.val + curr.val;
                stack.push(curr.right);
            }
        }
        return false;
    }

}
