package LeetCode.Practise_Everyday.Date_2022_08;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: Lil_boat
 * @Date: 2022/8/22 23:12
 * @Title: 输出二叉树
 * @Description: 给你一棵二叉树的根节点 root ，请你构造一个下标从 0 开始、大小为 m x n 的字符串矩阵 res ，
 * 用以表示树的 格式化布局 。构造此格式化布局矩阵需要遵循以下规则：
 * <p>
 * 树的 高度 为 height ，矩阵的行数 m 应该等于 height + 1 。
 * 矩阵的列数 n 应该等于 2height+1 - 1 。
 * 根节点 需要放置在 顶行 的 正中间 ，对应位置为 res[0][(n-1)/2] 。
 * 对于放置在矩阵中的每个节点，设对应位置为 res[r][c] ，将其左子节点放置在 res[r+1][c-2height-r-1] ，右子节点放置在 res[r+1][c+2height-r-1] 。
 * 继续这一过程，直到树中的所有节点都妥善放置。
 * 任意空单元格都应该包含空字符串 "" 。
 * 返回构造得到的矩阵 res 。
 * <p>
 * 链接：https://leetcode.cn/problems/print-binary-tree
 */
public class D22_PrintTree_655 {

    int h, m, n;
    List<List<String>> ans;
    public List<List<String>> printTree(TreeNode root) {
        dfs1(root, 0);
        m = h + 1; n = (1 << (h + 1)) - 1;
        ans = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            List<String> cur = new ArrayList<>();
            for (int j = 0; j < n; j++) cur.add("");
            ans.add(cur);
        }
        dfs2(root, 0, (n - 1) / 2);
        return ans;
    }
    void dfs1(TreeNode root, int depth) {
        if (root == null) return ;
        h = Math.max(h, depth);
        dfs1(root.left, depth + 1);
        dfs1(root.right, depth + 1);
    }
    void dfs2(TreeNode root, int x, int y) {
        if (root == null) return ;
        ans.get(x).set(y, String.valueOf(root.val));
        dfs2(root.left, x + 1, y - (1 << (h - x - 1)));
        dfs2(root.right, x + 1, y + (1 << (h - x - 1)));
    }


}
