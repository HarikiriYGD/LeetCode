package LeetCode.Practise_Everyday.Date_2022_07;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Author: Lil_boat
 * @Date: 2022/7/31 20:13
 * @Title: 最大层内元素和
 * @Description: 给你一个二叉树的根节点 root。设根节点位于二叉树的第 1 层，而根节点的子节点位于第 2 层，依此类推。
 * 请返回层内元素之和 最大 的那几层（可能只有一层）的层号，并返回其中 最小 的那个。
 *
 * 链接：https://leetcode.cn/problems/maximum-level-sum-of-a-binary-tree
 */
public class D31_MaxLevelSum_1161 {

    public int maxLevelSum(TreeNode root) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.add(root);
        // 定义结果集和每一层之和
        int level = 0;
        int ans = 0;
        int sum = Integer.MIN_VALUE;
        // 求解每一层的和
        while (!queue.isEmpty()) {
            int cnt = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                cnt += cur.val;
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                }
            }
            // 层数 ++
            level++;
            // 寻找最大层数的元素之和
            // 如果找到了就将该层的层数赋值给结果集
            if (sum < cnt) {
                sum = cnt;
                ans = level;
            }
        }
        return ans;
    }


}
