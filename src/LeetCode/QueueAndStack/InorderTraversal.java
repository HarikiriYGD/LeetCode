package LeetCode.QueueAndStack;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class InorderTraversal {


    /**
     * Definition for a binary tree node.
     */
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
     * 对二叉树进行中序遍历(迭代方法)
     *
     * @param root 树结构
     * @return 返回中序遍历的结果(访问顺序 根 - > 左 - > 右)
     */
    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>(); // 构造list集合
        Deque<TreeNode> stack = new LinkedList<>(); // 用来存储根节点
        while (root != null || !stack.isEmpty()) {
            while (root != null) { // 不断向左节点深入，直到访问叶子节点
                stack.push(root);
                root = root.left;
            }
            // 弹出上一个节点
            TreeNode top = stack.pop();
            list.add(top.val);
            // 访问右子树
            root = top.right;
        }
        return list;
    }

    /**
     * 对二叉树进行中序遍历(递归方法)
     *
     * @param root 树结构
     * @return 返回中序遍历的结果
     */
    public static List<Integer> inorderTraversal1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        return list;
    }

    /**
     * @param root 当前节点
     * @param list 当前访问过的节点值
     */
    public static void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        // 访问左子树
        inorder(root.left, list);
        list.add(root.val);
        // 访问右子树
        inorder(root.right, list);
    }


    public static void main(String[] args) {

    }
}
