package LeetCode.AimToOffer;

/**
 * @Auther: Lil_boat
 * @Date: 14:40 2022/1/18
 * @Description: 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 */
public class IsBalanced_55 {
    public boolean isBalanced(TreeNode root) {
        if (root==null)return true;
        int left = maxDepth(root.left);
        int right = maxDepth(root.right);
        return Math.abs(left-right) <=1 && isBalanced(root.right) && isBalanced(root.left);
    }

    public int maxDepth(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth(root.left),maxDepth(root.right)) + 1;
    }

}
