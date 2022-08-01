package LeetCode.BinaryTree;

import java.util.*;

/**
 * @Auther: Lil_boat
 * @Date: 17:09 2021/12/13
 * @Description: 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * <p>
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 */
public class LowestCommonAncestor {

    /**
     * 递归的写法
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor_Recursion(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor_Recursion(root.left, p, q);
        TreeNode right = lowestCommonAncestor_Recursion(root.right, p, q);
        // 如果left为空，说明这两个节点在cur结点的右子树上，我们只需要返回右子树查找的结果即可
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

    /**
     * 迭代的方式
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor_Iteration(TreeNode root, TreeNode p, TreeNode q) {
        // 记录遍历到的每个节点的父节点。
        Map<TreeNode, TreeNode> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        // 根节点没有父节点，所以为空
        map.put(root, null);
        queue.add(root);
        // 直到两个节点都找到
        while (!map.containsKey(p) || !map.containsKey(q)) {
            TreeNode cur = queue.poll();
            if (cur.left != null) {
                // 把父亲节点加入到HashMap中
                map.put(cur.left, cur);
                queue.add(cur.left);
            }
            if (cur.right != null) {
                map.put(cur.right, cur);
                queue.add(cur.right);
            }
        }
        Set<TreeNode> ancestors = new HashSet<>();
        // 记录下p和他的祖先节点，从p节点开始一直到根节点。
        while (p != null) {
            ancestors.add(p);
            p = map.get(p);
        }
        // 查看p和他的祖先节点是否包含q节点，如果不包含再看是否包含q的父节点……
        while (!ancestors.contains(q))
            q = map.get(q);
        return q;
    }

}
