package LeetCode.Practise_Everyday.Date_2022_08;

/**
 * @Author: Lil_boat
 * @Date: 2022/8/17 17:50
 * @Title: 层数最深叶子节点的和
 * @Description: 给你一棵二叉树的根节点 root ，请你返回 层数最深的叶子节点的和
 */


public class D17_DeepestLeavesSum_1302 {

    int sum = 0;
    int max = 0;

    public int deepestLeavesSum(TreeNode root) {
        helper(root, 0);
        return sum;
    }

    private void helper(TreeNode root, int level) {
        if (root == null) {
            return;
        }

        if (level > max) {
            max = level;
            sum = root.val;
        } else if (level == max) {
            sum += root.val;
        }
        helper(root.left, level + 1);
        helper(root.right, level + 1);
    }

}
