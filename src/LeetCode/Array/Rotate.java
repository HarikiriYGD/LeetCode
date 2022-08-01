package LeetCode.Array;

import java.util.Arrays;

public class Rotate {

    // 方法一

    /**
     * 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数。
     *
     * @param nums 需要旋转的数组
     * @param k    右移的长度
     */
    public static void rotate(int[] nums, int k) {
        int length = nums.length; // 数组长度
        int[] tmp = new int[length]; // 构造一个存储数组
        for (int i = 0; i < length; i++) {
            tmp[(i + k) % length] = nums[i];
        }
        for (int i = 0; i < length; i++) {
            nums[i] = tmp[i];
        }
        String res = Arrays.toString(nums);
        System.out.println(res);
    }

    // 方法二

    /**
     * 通过反转的方法实现旋转数组
     *
     * @param nums
     * @param k
     */
    public static void rotate_reverse(int[] nums, int k) {
        int length = nums.length;
        k %= length;
        reverse(nums, 0, length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, length - 1);
        String res = Arrays.toString(nums);
        System.out.println(res);
    }

    /**
     * 反转函数
     *
     * @param nums
     * @param start 起始下标
     * @param end   终止下标
     */
    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int tmp = nums[start];
            nums[start++] = nums[end];
            nums[end--] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6};
        rotate_reverse(nums, 9);
    }
}
