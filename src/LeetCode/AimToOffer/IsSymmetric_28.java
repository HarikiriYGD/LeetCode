package LeetCode.AimToOffer;

/**
 * @Auther: Lil_boat
 * @Date: 16:23 2022/1/6
 * @Description: 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 * <p>
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 */
public class IsSymmetric_28 {
    /**
     * 递归的方式
     * 判断左右子树 是否自己都是对称的树
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root==null)return true;
        return method(root.left, root.right);
    }

    public boolean method(TreeNode left, TreeNode right) {
        // 如果left 与 right都是空 表明都是叶子结点 返回true
        if (left == null && right == null) return true;
        // 值不相等 或者有一个为空 表明不是对称的树
        if (left == null || right == null || left.val != right.val) return false;
        return method(left.left, right.right) && method(left.right, right.left);
    }
}
