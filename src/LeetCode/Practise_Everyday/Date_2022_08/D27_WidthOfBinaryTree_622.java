package LeetCode.Practise_Everyday.Date_2022_08;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @Author: Lil_boat
 * @Date: 2022/8/27 22:07
 * @Title: 二叉树最大宽度
 * @Description: 给你一棵二叉树的根节点 root ，返回树的 最大宽度 。
 * 树的 最大宽度 是所有层中最大的 宽度 。
 * 每一层的 宽度 被定义为该层最左和最右的非空节点（即，两个端点）之间的长度。
 * 将这个二叉树视作与满二叉树结构相同，两端点间会出现一些延伸到这一层的 null 节点，这些 null 节点也计入长度。
 * 题目数据保证答案将会在  32 位 带符号整数范围内。
 *
 * 链接：https://leetcode.cn/problems/maximum-width-of-binary-tree
 */
public class D27_WidthOfBinaryTree_622 {
    Map<Integer, Integer> map = new HashMap<>();
    int ans;

    public int widthOfBinaryTree(TreeNode root) {
        dfs(root, 1, 0);
        return ans;
    }

    private void dfs(TreeNode root, int u, int depth) {
        if (root == null) return;
        if (!map.containsKey(depth)) map.put(depth, u);
        ans = Math.max(ans, u - map.get(depth) + 1);
        u = u - map.get(depth) + 1;
        dfs(root.left, u << 1, depth + 1);
        dfs(root.right, u << 1 | 1, depth + 1);
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
    }

}
