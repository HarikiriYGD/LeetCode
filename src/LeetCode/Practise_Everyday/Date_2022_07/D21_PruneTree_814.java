package LeetCode.Practise_Everyday.Date_2022_07;

/**
 * @Author: Lil_boat
 * @Date: 2022/7/21 8:13
 * @Title: 二叉树剪枝
 * @Description: 给你二叉树的根结点 root ，此外树的每个结点的值要么是 0 ，要么是 1 。
 * 返回移除了所有不包含 1 的子树的原二叉树。
 * 节点 node 的子树为 node 本身加上所有 node 的后代。
 *
 * 链接：https://leetcode.cn/problems/binary-tree-pruning
 */


class TreeNode {
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

public class D21_PruneTree_814 {

    public TreeNode pruneTree(TreeNode root) {
        // 边界判断条件
        if (root == null) {
            return null;
        }
        root.left = pruneTree(root.left);
        // 如果当前节点是叶子节点，且节点值为0，则删除
        root.right = pruneTree(root.right);
        if (root.val == 0 && root.left == null && root.right == null) {
            return null;
        }
        return root;
    }

}
