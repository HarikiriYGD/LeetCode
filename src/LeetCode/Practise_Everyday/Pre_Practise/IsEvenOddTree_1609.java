package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * @Auther: Lil_boat
 * @Date: 11:28 2021/12/27
 * @Description:
 * 如果一棵二叉树满足下述几个条件，则可以称为 奇偶树 ：
 *
 * 二叉树根节点所在层下标为 0 ，根的子节点所在层下标为 1 ，根的孙节点所在层下标为 2 ，依此类推。
 * 偶数下标 层上的所有节点的值都是 奇 整数，从左到右按顺序 严格递增
 * 奇数下标 层上的所有节点的值都是 偶 整数，从左到右按顺序 严格递减
 * 给你二叉树的根节点，如果二叉树为 奇偶树 ，则返回 true ，否则返回 false 。
 *
 */
public class IsEvenOddTree_1609 {
    public boolean isEvenOddTree(TreeNode root) {
        // 记录层数
        int level = 0;
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 每层的节点数
            int levelNum = queue.size();
            // 如果是奇数层，pre = 最小值
            int pre = level % 2 == 0 ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            for (int i = 0; i < levelNum; i++) {
                TreeNode cur = queue.poll();
                int value = cur.val;
                // 奇数层和偶数层必然不一样
                if (level % 2 == value % 2) return false;
                // 判断是否是严格单调的元素
                if ((level % 2 == 0 && pre >= value) || (level % 2 == 1 && pre <= value)) return false;
                // 记录前一个节点的值
                pre = value;
                if (cur.left != null) queue.offer(cur.left);
                if (cur.right != null) queue.offer(cur.right);
            }
            level++;
        }
        return true;
    }

    private boolean isSingleArray(int[] tmp, int level) {
        if (level == 0 && tmp.length == 1) return true;
        if (level % 2 == 0) {
            // 严格递增
            for (int i = 0; i < tmp.length - 1; i++) {
                if (tmp[i] >= tmp[i + 1]) return false;
                else return true;
            }
        } else {
            // 严格递减
            for (int i = 0; i < tmp.length; i++) {
                if (tmp[i] <= tmp[i + 1]) return false;
                else return true;
            }
        }
        return false;
    }
}
