package LeetCode.Practise_Everyday.Date_2022_09;

import java.util.Map;

/**
 * @Author: Lil_boat
 * @Date: 2022/9/2 23:13
 * @Title: 最长同值路径
 * @Description: 给定一个二叉树的 root ，返回 最长的路径的长度 ，这个路径中的 每个节点具有相同值 。 这条路径可以经过也可以不经过根节点。
 * 两个节点之间的路径长度 由它们之间的边数表示。
 * <p>
 * 链接：https://leetcode.cn/problems/longest-univalue-path
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

public class D02_LongestUnivaluePath_687 {

    private int maxL = 0;

    public int longestUnivaluePath(TreeNode root) {
        if (root == null) {
            return 0;
        }
        help(root, root.val);
        return maxL;
    }

    private int help(TreeNode root, int val) {
        if (root == null) {
            return 0;
        }
        int left = help(root.left, root.val);
        int right = help(root.right, root.val);
        maxL = Math.max(left + right, maxL);
        if (root.val == val){
            return Math.max(left, right) + 1;
        }
        return 0;
    }
}
