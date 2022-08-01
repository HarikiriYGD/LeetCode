package LeetCode.Array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ContainsDuplicate {

    /**
     * 给定一个整数数组，判断是否存在重复元素。
     * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
     *
     * @param nums 传入的数组
     * @return 是否有重复元素的布尔值
     */
    public static boolean containsDuplicate(int[] nums) {
        int length = nums.length;
        // 对数组进行排序
        Arrays.sort(nums);
        // 相同的元素一定是相邻的，如果与下一个相同则返回
        for (int i = 0; i < length - 1; i++) {
            if (nums[i] == nums[i + 1]) return true;
        }
        return false;
    }

    /**
     * 我们知道set集合中的元素是不能有重复的，在添加的时候如果有重复的，会把之前的值给覆盖，并且返回false。
     *
     * @param nums
     * @return
     */
    public static boolean containsDuplicate_hashSet(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if (!set.add(num)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 5};
        boolean res = containsDuplicate_hashSet(nums);
        System.out.println(res);
    }
}
