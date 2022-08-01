package LeetCode.BinaryTree;

import java.util.*;

/**
 * @Auther: Lil_boat
 * @Date: 15:49 2021/12/8
 * @Description: 给定一个二叉树，返回它的 后序 遍历。
 */
public class PostorderTraversal {

    /**
     * 递归的方式
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        postOrder(root, res);
        return res;
    }

    private void postOrder(TreeNode root, List<Integer> res) {
        if (root == null) return;
        postOrder(root.left, res);
        postOrder(root.right, res);
        res.add(root.val);
    }

    /**
     * 非递归的方式
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal_NonRecursion(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        TreeNode prev = null;
        while (!stack.isEmpty() || root != null) {
            while (root != null) {
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.right == null || root.right == prev) {
                res.add(root.val);
                prev = root;
                root = null;
            } else {
                stack.push(root);
                root = root.right;
            }
        }
        return res;
    }

    /**
     * 另外一种迭代的方式
     * 前序：根-左-右
     * 后序：左-右-根
     * 后序相当于 [根-右-左] 的逆序。
     * 对于迭代求前序遍历可以根节点先入栈，然后右子树进栈，最后左子树进栈；
     * 此时便可以根节点先入栈，然后左子树进栈，最后右子树进栈，最后将列表逆序即为所求。
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal_Iteration(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode curr = stack.pop();
            res.add(curr.val);
            if (curr.left != null) stack.push(curr.left);
            if (curr.right != null) stack.push(curr.right);
        }
        Collections.reverse(res);
        return res;
    }

}
