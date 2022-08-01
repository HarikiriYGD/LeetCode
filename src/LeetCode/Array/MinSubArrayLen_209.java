package LeetCode.Array;

import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 11:53 2022/4/15
 * @Tile: 和大于等于 target 的最短子数组
 * @Description: 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * <p>
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。
 * 如果不存在符合条件的子数组，返回 0 。
 */
public class MinSubArrayLen_209 {

    /*
        滑动窗口的思想
     */
    public static int minSubArrayLen(int target, int[] nums) {
        // 初始化结果最小值
        int ans = Integer.MAX_VALUE;
        // 初始化数组和
        int sum = 0;
        for (int i = 0, j = 0; j < nums.length; j++) {
            sum += nums[j];
            while (i <= j && sum >= target){
                // 更新最小值的结果
                ans = Math.min(ans,j - i + 1);
                // 缩小窗口
                sum -= nums[i++];
            }
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    public static void main(String[] args) {
        int target = 213;
        int[] nums = {12, 28, 83, 4, 25, 26, 25, 2, 25, 25, 25, 12};
        System.out.println(minSubArrayLen(target, nums));
    }

}
