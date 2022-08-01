package LeetCode.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Auther: Lil_boat
 * @Date: 16:55 2021/12/8
 * @Description: 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
 */
public class LevelOrder {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Queue<TreeNode> queue = new LinkedList<>();
        // 结点入队
        queue.add(root);
        // 队列不为空时
        while (!queue.isEmpty()) {
            // 每层的结点个数
            int levelNum = queue.size();
            List<Integer> subList = new ArrayList<>();
            // 将这一层的所有结点加入subList
            for (int i = 0; i < levelNum; i++) {
                TreeNode cur = queue.poll();
                subList.add(cur.val);
                // 左子树不为空，则入队
                if (cur.left != null) {
                    queue.add(cur.left);
                }
                // 右子树不为空格，则入队
                if (cur.right != null) queue.add(cur.right);

            }
            res.add(subList);
        }
        return res;
    }

}
