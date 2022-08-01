package LeetCode.Array;

import java.util.Arrays;

public class MoveZeroes {

    /**
     * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
     *
     * @param nums
     */
    public static void moveZeroes(int[] nums) {
        int length = nums.length;
        if (nums == null || length == 0) return;
        int index = 0;
        for (int i = 0; i < length; i++) {
            // 将非零元素前移
            if (nums[i] != 0) nums[index++] = nums[i];
        }

        // 在数组最后补零
        while (index < length) {
            nums[index++] = 0;
        }
        String s = Arrays.toString(nums);
        System.out.println(s);
    }

    /**
     * 利用双指针的思想
     *
     * @param nums
     */
    public static void moveZeroes_pointer(int[] nums) {
        if (nums == null || nums.length == 0) return;
        int count = 0; // 记录零的个数
        for (int i = 0; i < nums.length; i++) {
            // 如果当前数字是0就不操作，并将count+1
            if (nums[i] == 0) count++;
            else if (count != 0) {
                // 否则，把当前数字放到最前面那个0的位置，然后再把
                // 当前位置设为0
                nums[i - count] = nums[i];
                nums[i] = 0;
            }
        }
        String res = Arrays.toString(nums);
        System.out.println(res);
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 0, 2, 0, 2, 3, 5};
        moveZeroes_pointer(nums);
    }

}
