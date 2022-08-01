package LeetCode.Practise_Everyday.Date_2022_06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Auther: Lil_boat
 * @Date: 9:08 2022/6/19
 * @Tile: 出现次数最多的子树元素和
 * @Description: 给你一个二叉树的根结点 root ，请返回出现次数最多的子树元素和。
 * 如果有多个元素出现的次数相同，返回所有出现次数最多的子树元素和（不限顺序）。
 * 一个结点的 「子树元素和」 定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。
 * <p>
 * 链接：https://leetcode.cn/problems/most-frequent-subtree-sum
 */
public class D19_FindFrequentTreeSum_508 {

    private int max = 0;
    HashMap<Integer, Integer> map = new HashMap<>();

    public int[] findFrequentTreeSum(TreeNode root) {
        // 如果根节点为空，直接返回空数组
        if (root == null) return new int[]{};
        helper(root);
        List<Integer> list = new ArrayList<>();

        for (int x : map.keySet()) {
            if (map.get(x) == max) list.add(x);
        }
        int[] ans = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            ans[i] = list.get(i);
        }
        return ans;
    }

    /**
     * 计算每个节点的子树元素和
     * @param root
     * @return
     */
    private int helper(TreeNode root) {
        if (root == null) return 0;
        int left = helper(root.left);
        int right = helper(root.right);
        int sum = left + right + root.val;
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        max = Math.max(max, map.get(sum));
        return sum;
    }


}
