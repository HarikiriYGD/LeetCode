package LeetCode.Practise_Everyday.Date_2022_03;

/**
 * @Auther: Lil_boat
 * @Date: 11:41 2022/3/4
 * @Description: 给你一个整数数组 nums 。nums 中，子数组的 范围 是子数组中最大元素和最小元素的差值。
 * 返回 nums 中 所有 子数组范围的 和 。
 * 子数组是数组中一个连续 非空 的元素序列
 */
public class SubArrayRanges_2104 {

    public static long subArrayRanges(int[] nums) {
        // 数组的长度
        int n = nums.length;
        long ans = 0;
        // 控制长度
        for (int i = 0; i < n; i++) {
            int max = nums[i];
            int min = nums[i];
            for (int j = i; j < n; j++) {
                max = Math.max(max,nums[j]);
                min = Math.min(min,nums[j]);
                ans += max - min;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(subArrayRanges(nums));
    }
}
