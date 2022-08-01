package LeetCode.Practise_Everyday.Pre_Practise;

/**
 * @Auther: Lil_boat
 * @Date: 9:25 2022/1/13
 * @Description: 给你一个整数数组 nums ，其中总是存在 唯一的 一个最大整数 。
 * 请你找出数组中的最大元素并检查它是否 至少是数组中每个其他数字的两倍 。如果是，则返回 最大元素的下标 ，否则返回 -1 。
 */
public class DominantIndex_747 {
    public static int dominantIndex(int[] nums) {
        // 记录数组中的最大值
        int max = 0;
        // 记录最大值的索引
        int index = -1;
        // 记录数组中第二大的值
        int subM = 1;
        // 遍历数组
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= max) {
                // 将上一个最大值赋值给subM
                subM = max;
                // 记录现在数组中的最大值
                max = nums[i];
                index = i;
                // 如果当前的数组中比最大值小但比第二大的值大，则更新subM
            } else if (nums[i] >= subM) subM = nums[i];
        }
        if (max >= 2 * subM) return index;
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {3, 6, 1, 0};
        System.out.println(dominantIndex(nums));
    }
}
