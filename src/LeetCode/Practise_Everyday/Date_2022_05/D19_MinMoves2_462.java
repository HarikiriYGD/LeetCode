package LeetCode.Practise_Everyday.Date_2022_05;

import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 10:19 2022/5/19
 * @Tile: 最少移动次数使数组元素相等 II
 * @Description: 给你一个长度为 n 的整数数组 nums ，返回使所有数组元素相等需要的最少移动数。
 * 在一步操作中，你可以使数组中的一个元素加 1 或者减 1 。
 *
 * https://leetcode.cn/problems/minimum-moves-to-equal-array-elements-ii/
 */
public class D19_MinMoves2_462 {

    /*
        本题的关键在于寻找中位数，而不是平均数！
     */
    public int minMoves2(int[] nums) {
        int ans = 0, n = nums.length;
        Arrays.sort(nums);
        for(int x : nums)ans += Math.abs(x - nums[n / 2]);
        return ans;
    }
}
