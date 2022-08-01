package LeetCode.AimToOffer;

public class KthLargest_54 {
    static int res, k;

    public static int kthLargest(TreeNode root, int k) {
        if (root == null) return -1;
        dfs(root);
        return res;
    }

    public static void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.right);
        if (--k==0)res = root.val;
        dfs(root.left);
    }
}
