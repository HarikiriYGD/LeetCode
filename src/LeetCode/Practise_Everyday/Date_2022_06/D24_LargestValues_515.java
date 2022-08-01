package LeetCode.Practise_Everyday.Date_2022_06;

import java.util.*;

/**
 * @Auther: Lil_boat
 * @Date: 9:35 2022/6/24
 * @Tile: 在每个树行中找最大值
 * @Description: 给定一棵二叉树的根节点 root ，请找出该二叉树中每一层的最大值。
 */
public class D24_LargestValues_515 {

    public List<Integer> largestValues(TreeNode root) {
        //  树为空树 直接返回空结果集
        if (root == null) return new ArrayList<>();
        // 访问队列
        Deque<TreeNode> queue = new ArrayDeque<>();
        // 构建结果集
        List<Integer> ans = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                max = Math.max(cur.val, max);
                if (cur.left != null)queue.add(cur.left);
                if (cur.right!=null)queue.add(cur.right);
            }
            ans.add(max);
        }
        return ans;
    }

}
