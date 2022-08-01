package LeetCode.Practise_Everyday.Date_2022_05;
/**
 * @Auther: Lil_boat
 * @Date: 11:17 2022/5/30
 * @Tile: 从根到叶的二进制数之和
 * @Description: 给出一棵二叉树，其上每个结点的值都是 0 或 1 。每一条从根到叶的路径都代表一个从最高有效位开始的二进制数。
 * 例如，如果路径为 0 -> 1 -> 1 -> 0 -> 1，那么它表示二进制数 01101，也就是 13 。
 * 对树上的每一片叶子，我们都要找出从根到该叶子的路径所表示的数字。
 * 返回这些数字之和。题目数据保证答案是一个 32 位 整数。
 *
 * 链接：https://leetcode.cn/problems/sum-of-root-to-leaf-binary-numbers
 */
public class D30_SumRootToLeaf_1022 {

    public int sumRootToLeaf(TreeNode root) {
        if (root == null) return 0;
        return proOrder(root, 0);
    }

    private int proOrder(TreeNode root, int sum) {
        if (root == null) return 0;
        sum = (sum << 1) | root.val;
        // 判断是否是叶子节点
        if (root.left == null && root.right == null) return sum;
        return proOrder(root.left, sum) + proOrder(root.right, sum);
    }

}
