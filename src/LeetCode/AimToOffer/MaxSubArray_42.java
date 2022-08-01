package LeetCode.AimToOffer;

/**
 * @Auther: Lil_boat
 * @Date: 15:56 2021/12/28
 * @Description: 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 * <p>
 * 要求时间复杂度为O(n)。
 */
public class MaxSubArray_42 {
    /**
     * 直接在原数组上操作 不用新建 dp 数组
     * 这种方法使空间复杂度降低到了 O(1)
     * @param nums
     * @return
     */
    public static int maxSubArray(int[] nums) {
        int n = nums.length;
        // 构建动规数组
        int res = nums[0];
        // 初始化数据

        for (int i = 1; i < nums.length; i++) {
            // 贪心 舍弃前面和小于0的数 从这个数开始累加
            nums[i] += Math.max(nums[i - 1], 0);
            res = Math.max(nums[i], res);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-1, -2};
        System.out.println(maxSubArray(nums));
    }
}
