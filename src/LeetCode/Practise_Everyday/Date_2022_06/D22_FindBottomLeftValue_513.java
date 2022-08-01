package LeetCode.Practise_Everyday.Date_2022_06;

/**
 * @Auther: Lil_boat
 * @Date: 9:57 2022/6/22
 * @Tile: 找树左下角的值
 * @Description: 给定一个二叉树的 根节点 root，请找出该二叉树的 最底层 最左边 节点的值。
 * 假设二叉树中至少有一个节点。
 */
public class D22_FindBottomLeftValue_513 {

    int max = Integer.MIN_VALUE;
    int res = 0;

    public int findBottomLeftValue(TreeNode root) {
        dfs(root, 0);
        return res;
    }

    public void dfs(TreeNode root, int depth) {
        if (root != null) {
            // 必定是叶子节点才会是最底层 最左边的值
            if (root.left == null && root.right == null) {
                // 如果深度比之前的都深 说明是更底层的节点
                if (max < depth) {
                    max = depth;
                    res = root.val;
                    return;
                }
            }
            dfs(root.left, depth + 1);
            dfs(root.right, depth + 1);
        }

    }

}
