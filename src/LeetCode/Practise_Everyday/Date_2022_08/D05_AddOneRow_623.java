package LeetCode.Practise_Everyday.Date_2022_08;

/**
 * @Author: Lil_boat
 * @Date: 2022/8/5 21:13
 * @Title: 在二叉树中增加一行
 * @Description: 给定一个二叉树的根 root 和两个整数 val 和 depth ，在给定的深度 depth 处添加一个值为 val 的节点行。
 * <p>
 * 注意，根节点 root 位于深度 1 。
 * <p>
 * 加法规则如下:
 * <p>
 * 给定整数 depth，对于深度为 depth - 1 的每个非空树节点 cur ，创建两个值为 val 的树节点作为 cur 的左子树根和右子树根。
 * cur 原来的左子树应该是新的左子树根的左子树。
 * cur 原来的右子树应该是新的右子树根的右子树。
 * 如果 depth == 1 意味着 depth - 1 根本没有深度，那么创建一个树节点，值 val 作为整个原始树的新根，而原始树就是新根的左子树。
 * <p>
 * 链接：https://leetcode.cn/problems/add-one-row-to-tree
 */

class TreeNode {
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

public class D05_AddOneRow_623 {

    public TreeNode addOneRow(TreeNode root, int val, int depth) {
        if (depth == 0 || depth == 1) {
            TreeNode t = new TreeNode(val);
            if (depth == 1) {
                t.left = root;
            } else {
                t.right = root;
            }
            return root;
        }
        if (root != null && depth > 1) {
            root.left = addOneRow(root.left, val, depth > 2 ? depth - 1 : 1);
            root.right = addOneRow(root.right, val, depth > 2 ? depth - 1 : 0);
        }
        return root;
    }

}
