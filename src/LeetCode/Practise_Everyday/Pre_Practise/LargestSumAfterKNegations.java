package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 9:38 2021/12/3
 * @Description: 给你一个整数数组 nums 和一个整数 k ，按以下方法修改该数组：
 * <p>
 * 选择某个下标 i 并将 nums[i] 替换为 -nums[i] 。
 * 重复这个过程恰好 k 次。可以多次选择同一个下标 i 。
 * <p>
 * 以这种方式修改数组后，返回数组 可能的最大和 。
 */
public class LargestSumAfterKNegations {

    /**
     * 想法：循环遍历拿到数组的最小值并进行取反。感觉效率较低！！！
     *
     * @param nums
     * @param k
     * @return
     */
    public static int largestSumAfterKNegations(int[] nums, int k) {
        int len = nums.length;
        if (nums == null || len == 0) return 0;
        // 定义数组最小值、数组和、最小值下标
        int min = nums[0], sum = 0, index = 0;
        for (int i = 0; i < k; i++) {
            for (int j = 0; j < len; j++) {
                if (min > nums[j]) {
                    // 寻找最小值，并记录最小值的下标
                    index = j;
                    min = nums[j];
                }
            }
            // 将最小值取反并将最小值取反
            nums[index] = -nums[index];
            min = -min;
        }
        for (int i = 0; i < len; i++) {
            sum += nums[i];
        }
        return sum;
    }

    /**
     * 贪心 + 排序
     *
     * @param nums
     * @param k
     * @return
     */
    public static int largestSumAfterKNegations_Sort(int[] nums, int k) {
        int len = nums.length;
        int sum = 0;
        if (nums == null || len == 0) return 0;
        // 将数组排序，将负数都排在前面
        Arrays.sort(nums);
        for (int i = 0; i < len; i++) {
            // 把数组中的负数取反
            if (nums[i] < 0 && k > 0) {
                nums[i] = -nums[i];
                k--;
            }
            sum += nums[i];
        }
        Arrays.sort(nums);
        //  如果k没剩，那说明能转的负数都转正了，已经是最大和，返回sum
        //  如果k有剩，说明负数已经全部转正，所以如果k还剩偶数个就自己抵消掉，不用删减，如果k还剩奇数个就减掉2倍最小正数。
        return k % 2 == 0 ? sum - 0 : sum - 2 * nums[0];
    }

    public static void main(String[] args) {
        int[] nums = {2, -3, -1, 5, -4};
        System.out.println("sum:" + largestSumAfterKNegations(nums, 2));
        System.out.println("sum:" + largestSumAfterKNegations_Sort(nums, 2));
    }

}
