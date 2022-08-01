package LeetCode.AimToOffer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Auther: Lil_boat
 * @Date: 17:17 2022/1/6
 * @Description: 从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 *  3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层次遍历结果：
 * [
 * [3],
 * [9,20],
 * [15,7]
 * ]
 */
public class LevelOrder_32_II {
    /**
     * 层次遍历的思想
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        // 如果为空树 直接返回空的List
        if (root == null) return new ArrayList<List<Integer>>() {
        };
        List<List<Integer>> ans = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // 队列不为空
        while (!queue.isEmpty()) {
            // 每层的元素的个数
            int levelSize = queue.size();
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < levelSize; i++) {
                TreeNode cur = queue.poll();
                // 左右节点入队
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
                subList.add(cur.val);
            }
            ans.add(subList);
        }
        return ans;
    }
}
