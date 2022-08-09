package LeetCode.Practise_Everyday.Date_2022_08;

/**
 * @Author: Lil_boat
 * @Date: 2022/8/9 22:32
 * @Title: 逐步求和得到正数的最小值
 * @Description: 给你一个整数数组 nums 。你可以选定任意的 正数 startValue 作为初始值。
 * 你需要从左到右遍历 nums 数组，并将 startValue 依次累加上 nums 数组中的值。
 * 请你在确保累加和始终大于等于 1 的前提下，选出一个最小的 正数 作为 startValue 。
 * <p>
 * 链接：https://leetcode.cn/problems/minimum-value-to-get-positive-step-by-step-sum
 */
public class D09_MinStartValue_1413 {

    public int minStartValue(int[] nums) {
        // 构建前缀和数组
        int n = nums.length;
        int[] prefixSum = new int[n];
        int minValue = prefixSum[0] = nums[0];
        for (int i = 1; i < n; i++) {
            // 求前缀和
            prefixSum[i] = prefixSum[i - 1] + nums[i];
            // 寻找前缀和数组中最小的前缀和
            minValue = Math.min(minValue, prefixSum[i]);
        }
        return minValue > 0 ? 1 : 1 - minValue;
    }

}
