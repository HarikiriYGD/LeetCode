package LeetCode.Recursion;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: Lil_boat
 * @Date: 17:03 2021/12/8
 * @Description: 给定一个二叉树，检查它是否是镜像对称的。
 */

public class IsSymmetric {

    /**
     * 递归的方式
     *
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    private boolean isSymmetric(TreeNode left, TreeNode right) {
        // 如果都是叶子节点则表明是对称的
        if (left == null && right == null) return true;
        // 如果当前节点只有一个子节点或者有两个子节点，但两个子节点的值不相同，直接返回false
        if (left == null || right == null || left.val != right.val) return false;
        return isSymmetric(left.left, right.right) && isSymmetric(left.right, right.left);

    }

    /**
     * 迭代的方式
     *
     * @param root
     * @return
     */
    public boolean isSymmetric_Iteration(TreeNode root) {
        if (root == null) return true;
        Queue<TreeNode> q = new LinkedList<>();
        // 将左右结点入队
        q.add(root.left);
        q.add(root.right);
        // 队列中还有元素就继续遍历
        while (!q.isEmpty()) {
            TreeNode left = q.poll();
            TreeNode right = q.poll();
            // 如果都为空继续循环
            if (left == null && right == null) continue;
            // 如果一个为空一个不为空，说明不是对称的，直接返回false
            if (left == null || right == null) return false;
            if (left.val != right.val) return false;
            // 这里要记住入队的顺序，他会每两个两个的出队。
            // 左子节点的左子节点和右子节点的右子节点同时
            // 入队，因为他俩会同时比较。
            // 左子节点的右子节点和右子节点的左子节点同时入队，
            // 因为他俩会同时比较
            q.add(left.left);
            q.add(right.right);
            q.add(left.right);
            q.add(right.left);
        }
        return true;
    }


}
