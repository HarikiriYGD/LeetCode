package LeetCode.BinaryTree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Auther: Lil_boat
 * @Date: 15:04 2021/12/8
 * @Description: 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 */
public class PreorderTraversal {


    /**
     * 递归解法
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        preOrder(root, list);
        return list;
    }

    public void preOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        res.add(root.val);
        preOrder(root.left, res);
        preOrder(root.right, res);
    }

    /**
     * 非递归解法
     *
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal_NonRecursion(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        // 将根结点压栈
        stack.push(root);
        while (!stack.isEmpty()) {
            // 出栈
            TreeNode pop = stack.pop();
            res.add(pop.val);
            if (root.right != null) {
                stack.push(root.right);
            }
            if (root.left != null) {
                stack.push(root.left);
            }
        }
        return res;
    }
}
