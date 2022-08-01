package LeetCode.Array;

public class RemoveDuplicates {

    /**
     * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
     * <p>
     * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
     *
     * @param nums
     * @return 返回不同数字的个数
     */
    public static int removeDuplicates(int[] nums) {
        int count = 0; //计算重复的数字
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1]) {
                count++;
            } else {
                // 寻找下一个不同的值
                nums[i - count] = nums[i];
            }
        }

        return nums.length - count;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2, 2, 3, 4, 4};
        int res = removeDuplicates(nums);
        System.out.println(res);
    }
}
