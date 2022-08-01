package LeetCode.Practise_Everyday.Pre_Practise;

/**
 * @Auther: Lil_boat
 * @Date: 14:37 2022/2/9
 * @Description: 给你一个整数数组 nums 和一个整数 k ，请你返回数对 (i, j) 的数目，满足 i < j 且 |nums[i] - nums[j]| == k 。
 * <p>
 * |x| 的值定义为：
 * <p>
 * 如果 x >= 0 ，那么值为 x 。
 * 如果 x < 0 ，那么值为 -x 。
 */
public class CountKDifference_2006 {
    public int countKDifference(int[] nums, int k) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = nums.length - 1; j > i; j--) {
                if (nums[i] - nums[j] == k || nums[i] - nums[j] == -k) {
                    sum++;
                }
            }
        }
        return sum;
    }
}
