package LeetCode.Practise_Everyday.Date_2022_07;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Lil_boat
 * @Date: 2022/7/25 12:48
 * @Title: 完全二叉树插入器
 * @Description: 完全二叉树 是每一层（除最后一层外）都是完全填充（即，节点数达到最大）的，并且所有的节点都尽可能地集中在左侧。
 * 设计一种算法，将一个新节点插入到一个完整的二叉树中，并在插入后保持其完整。
 * <p>
 * 实现 CBTInserter 类:
 * CBTInserter(TreeNode root) 使用头节点为 root 的给定树初始化该数据结构；
 * CBTInserter.insert(int v)  向树中插入一个值为 Node.val == val的新节点 TreeNode。使树保持完全二叉树的状态，并返回插入节点 TreeNode 的父节点的值；
 * CBTInserter.get_root() 将返回树的头节点。
 * <p>
 * 链接：https://leetcode.cn/problems/complete-binary-tree-inserter
 */

public class D25_CBTInserter_919 {
    List<TreeNode> list = new ArrayList<>();
    int idx = 0;

    public D25_CBTInserter_919(TreeNode root) {
        list.add(root);
        int cur = 0;
        while (cur < list.size()) {
            TreeNode node = list.get(cur);
            if (node.left != null) list.add(node.left);
            if (node.right != null) list.add(node.right);
            cur++;
        }
    }

    /**
     * 向树中插入一个值为 Node.val == val的新节点 TreeNode。
     * 使树保持完全二叉树的状态，并返回插入节点 TreeNode 的父节点的值
     *
     * @param v
     * @return
     */
    int insert(int v) {
        TreeNode node = new TreeNode(v);
        // 左右子树表明已经该节点是父亲节点
        while (list.get(idx).left != null && list.get(idx).right != null) {
            idx++;
        }
        TreeNode fa = list.get(idx);
        if (fa.left == null) {
            fa.left = node;
        } else if (fa.right == null) {
            fa.right = node;
        }
        list.add(node);
        return fa.val;
    }

    /**
     * 将返回树的头节点
     */
    TreeNode get_root() {
        return list.get(0);
    }
}
