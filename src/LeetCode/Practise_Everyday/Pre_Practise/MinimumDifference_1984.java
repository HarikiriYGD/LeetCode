package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 16:36 2022/2/11
 * @Description: 给你一个 下标从 0 开始 的整数数组 nums ，其中 nums[i] 表示第 i 名学生的分数。另给你一个整数 k 。
 * 从数组中选出任意 k 名学生的分数，使这 k 个分数间 最高分 和 最低分 的 差值 达到 最小化 。
 * 返回可能的 最小差值 。
 */
public class MinimumDifference_1984 {

    /*
        排序 + 滑动窗口
     */
    public static int minimumDifference(int[] nums, int k){
        // 排序
        Arrays.sort(nums);
        // 将结果置为最大值
        int ans = Integer.MAX_VALUE;
        for (int i = 0; i <= nums.length; i++) {
            if (i >= k){
                // 排序后的窗口内的最大值和最小值即是窗口内的第一个元素和最后一个元素
                ans = Math.min(ans, nums[i - 1] - nums[i - k]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {9,1,4,7};
        System.out.println(minimumDifference(nums, 2));
    }

}
