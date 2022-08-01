package LeetCode.Practise_Everyday.Date_2022_05;

/**
 * @Auther: Lil_boat
 * @Date: 9:48 2022/5/24
 * @Tile: 单值二叉树
 * @Description: 如果二叉树每个节点都具有相同的值，那么该二叉树就是单值二叉树。
 * 只有给定的树是单值二叉树时，才返回 true；否则返回 false。
 * <p>
 * https://leetcode.cn/problems/univalued-binary-tree/
 */
public class D24_IsUnivalTree_965 {

    public boolean isUnivalTree(TreeNode root) {
        if (root == null) return true;
        return preOrder(root, root.val);
    }

    public boolean preOrder(TreeNode root, int v) {
        if (root == null) return true;
        if (root.val != v) return false;
        return preOrder(root.left, root.val) && preOrder(root.right, root.val);
    }
}
