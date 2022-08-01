package LeetCode.Tree;

import java.util.*;

public class LevelOrder {

    // * Definition for a binary tree node.
    public class TreeNode {
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

    /**
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
     * BFS
     *
     * @param root 根节点
     * @return 返回层次打印的结果
     */
    public List<List<Integer>> levelOrder_BFS(TreeNode root) {
        // 判断条件
        if (root == null) return new ArrayList<>();
        // 队列
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        // 节点入队
        queue.add(root);
        while (!queue.isEmpty()) {
            // levelNum是每层的节点数
            int levelNum = queue.size();
            // 存储每层的节点数
            List<Integer> subList = new ArrayList<>();
            for (int i = 0; i < levelNum; i++) {
                // 出队
                TreeNode node = queue.poll();
                subList.add(node.val);
                // 左子树不为空，则加入队列
                if (node.left != null) queue.add(node.left);
                // 右子树不为空，则加入队列
                if (node.right != null) queue.add(node.right);
            }
            list.add(subList);
        }
        return list;
    }

    /**
     * 给你一个二叉树，请你返回其按 层序遍历 得到的节点值。 （即逐层地，从左到右访问所有节点）。
     * DFS
     *
     * @param root
     * @return
     */
    public List<List<Integer>> levelOrder_DFS(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        levelHelper(list, root, 0);
        return list;
    }

    public void levelHelper(List<List<Integer>> list, TreeNode root, int level) {
        // 边界判断
        if (root == null) return;
        //level表示的是层数，如果level >= list.size()，说明到下一层了，所以
        //要先把下一层的list初始化，防止下面add的时候出现空指针异常
        if (level >= list.size()) {
            list.add(new ArrayList<>());
        }
        //level表示的是第几层，这里访问到第几层，我们就把数据加入到第几层
        list.get(level).add(root.val);
        //当前节点访问完之后，再使用递归的方式分别访问当前节点的左右子节点
        levelHelper(list, root.left, level + 1);
        levelHelper(list, root.right, level + 1);
    }
}
