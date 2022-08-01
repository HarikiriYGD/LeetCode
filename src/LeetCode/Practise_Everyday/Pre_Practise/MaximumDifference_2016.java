package LeetCode.Practise_Everyday.Pre_Practise;
/**
 * @Auther: Lil_boat
 * @Date: 13:12 2022/2/26
 * @Description: 给你一个下标从 0 开始的整数数组 nums ，该数组的大小为 n ，请你计算 nums[j] - nums[i] 能求得的 最大差值 ，
 * 其中 0 <= i < j < n 且 nums[i] < nums[j] 。
 * 返回 最大差值 。如果不存在满足要求的 i 和 j ，返回 -1 。
 */
public class MaximumDifference_2016 {

    public static int maximumDifference(int[] nums) {
        int n = nums.length;
        // 初始化没有找到的情况下的结果
        int max = -1;
        // 进行遍历 ，并且设置初始位置的最小nums[i] 为第一个元素
        for (int i = 0, min = nums[0]; i < n; i++) {
            // 如果满足 当前元素的值 大于了 当前所处位置的最小nums[i] 则进行更新我们的最大差值
            if (nums[i] > min) max = Math.max(max, nums[i] - min);
            // 更新我们 当前位置的最小nums[i]
            min = Math.min(min, nums[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {999, 997, 980, 976, 948, 940, 938, 928, 924, 917, 907, 907, 881, 878, 864, 862, 859, 857, 848, 840, 824, 824, 824, 805, 802, 798, 788, 777, 775, 766, 755, 748, 735, 732, 727, 705, 700, 697, 693, 679, 676, 644, 634, 624, 599, 596, 588, 583, 562, 558, 553, 539, 537, 536, 509, 491, 485, 483, 454, 449, 438, 425, 403, 368, 345, 327, 287, 285, 270, 263, 255, 248, 235, 234, 224, 221, 201, 189, 187, 183, 179, 168, 155, 153, 150, 144, 107, 102, 102, 87, 80, 57, 55, 49, 48, 45, 26, 26, 23, 15};
        System.out.println(maximumDifference(nums));
    }

}
