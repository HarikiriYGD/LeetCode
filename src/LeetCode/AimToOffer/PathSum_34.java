package LeetCode.AimToOffer;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Auther: Lil_boat
 * @Date: 17:17 2022/1/9
 * @Description: 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * 叶子节点 是指没有子节点的节点。
 */
public class PathSum_34 {
    // 定义全局变量
    static List<List<Integer>> ans;

    public static List<List<Integer>> pathSum(TreeNode root, int target) {
        // 如果是空树 返回空的列表
        if (root == null) return new ArrayList<List<Integer>>();
        ans = new ArrayList<>();
        // 深度遍历
        dfs(root, target, new ArrayList<>());
        return ans;
    }

    public static void dfs(TreeNode root, int target, ArrayList<Integer> list) {
        // 如果该节点是空 则返回
        if (root == null) return;
        // 添入当前list
        list.add(root.val);
        // 如果该节点是叶子节点且值与target与之前的祖先节点值减去剩下的相等，则说明有一个路径
        // 加入结果集
        if (root.left == null && root.right == null && root.val == target) {
            ans.add(new ArrayList<>(list));
            // 回溯到父亲节点
            list.remove(list.size() - 1);
            return;
        }
        // 遍历左子树
        dfs(root.left, target - root.val, list);
        // 遍历右子树
        dfs(root.right, target - root.val, list);
        // 回溯到父亲节点
        list.remove(list.size() - 1);
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(5);
        TreeNode l = new TreeNode(4);
        TreeNode r = new TreeNode(8);
        TreeNode ll = new TreeNode(11);
        TreeNode rl = new TreeNode(13);
        TreeNode rr = new TreeNode(4);
        TreeNode lll = new TreeNode(7);
        TreeNode llr = new TreeNode(2);
        TreeNode rrl = new TreeNode(5);
        TreeNode rrr = new TreeNode(1);
        root.left = l;
        root.right = r;
        l.left = ll;
        l.right = null;
        ll.left = lll;
        ll.right = llr;
        r.left = rl;
        rl.left = null;
        rl.right = null;
        r.right = rr;
        rr.left = rrl;
        rr.right = rrr;
        pathSum(root,22);
    }


}
