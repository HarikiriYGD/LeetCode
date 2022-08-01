package LeetCode.Else;

import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 14:33 2021/12/8
 * @Description: 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 */
public class MissingNumber {

    /**
     * 排序后查找缺失元素
     *
     * @param nums
     * @return
     */
    public static int missingNumber(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == i) continue;
            else return i;
        }
        return nums.length;
    }

    /**
     * 位运算
     * 如果我们把这个数组添加从0~n的n+1个元素，就变成了数组中只有一个数出现了一次，
     * 其他数字都出现了2次，让我们求这个只出现一次的数字。
     *
     * @param nums
     * @return
     */
    public static int missingNumber_xor(int[] nums) {
        int xor = 0;
        for (int i = 0; i < nums.length; i++)
            xor ^= nums[i] ^ (i + 1);
        return xor;

    }

    public static void main(String[] args) {
        int[] nums = {0};
        System.out.println(missingNumber(nums));
        System.out.println(missingNumber_xor(nums));
    }

}
