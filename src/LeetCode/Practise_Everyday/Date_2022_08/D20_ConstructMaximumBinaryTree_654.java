package LeetCode.Practise_Everyday.Date_2022_08;

import java.util.Arrays;

/**
 * @Author: Lil_boat
 * @Date: 2022/8/20 22:04
 * @Title: 最大二叉树
 * @Description: 给定一个不重复的整数数组 nums 。 最大二叉树 可以用下面的算法从 nums 递归地构建:
 * <p>
 * 创建一个根节点，其值为 nums 中的最大值。
 * 递归地在最大值 左边 的 子数组前缀上 构建左子树。
 * 递归地在最大值 右边 的 子数组后缀上 构建右子树。
 * 返回 nums 构建的 最大二叉树 。
 * <p>
 * 链接：https://leetcode.cn/problems/maximum-binary-tree
 */
public class D20_ConstructMaximumBinaryTree_654 {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums.length == 0 || nums == null) {
            return null;
        }
        int max = 0, idx = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
                idx = i;
            }
        }
        TreeNode root = new TreeNode(max);
        root.left = constructMaximumBinaryTree(Arrays.copyOfRange(nums, 0, idx));
        root.right = constructMaximumBinaryTree(Arrays.copyOfRange(nums, idx + 1, nums.length));
        return root;
    }


}
