package LeetCode.Practise_Everyday.Date_2022_07;

/**
 * @Author: Lil_boat
 * @Date: 2022/7/17 9:20
 * @Title: 数组嵌套
 * @Description: 索引从0开始长度为N的数组A，包含0到N - 1的所有整数。
 * 找到最大的集合S并返回其大小，其中 S[i] = {A[i], A[A[i]], A[A[A[i]]], ... }且遵守以下的规则。
 * 假设选择索引为i的元素A[i]为S的第一个元素，S的下一个元素应该是A[A[i]]，之后是A[A[A[i]]]... 以此类推，不断添加直到S出现重复的元素。
 * <p>
 * 链接：https://leetcode.cn/problems/array-nesting
 */
public class D17_ArrayNesting_565 {

    private int n;
    boolean[] vis;
    public int arrayNesting(int[] nums) {
        n = nums.length;
        this.vis = new boolean[n];
        if (n == 0) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            ans = Math.max(ans, dfs(nums, nums[i], 0));
        }
        return ans;
    }

    private int dfs(int[] nums, int i, int ans) {
        if (vis[i]) {
            return ans;
        }
        vis[i] = true;
        return dfs(nums, nums[i], ans + 1);
    }

    public static void main(String[] args) {
        D17_ArrayNesting_565 t = new D17_ArrayNesting_565();
        int[] tmp = {2, 4, 0, 3, 1, 6, 5};
        int i = t.arrayNesting(tmp);
        System.out.println(i);
    }

}
