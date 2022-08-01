package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Lil_boat
 * @Date: 10:26 2022/1/19
 * @Description: 给你一个整数数组 nums 和一个整数 k ，判断数组中是否存在两个 不同的索引 i 和 j ，
 * 满足 nums[i] == nums[j] 且 abs(i - j) <= k 。如果存在，返回 true ；否则，返回 false 。
 */
public class ContainsNearbyDuplicate_219 {

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        // 存放数组的值和索引
        Map<Integer, Integer> map = new HashMap<>();
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            // 如果出现过重复元素且索引的差值小于k直接返回true
            // 否则返回false
            if (map.containsKey(nums[i])&&Math.abs(i - map.get(nums[i])) <= k) return true;
            map.put(nums[i], index++);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3, 4, 0, 0, 7, 8, 9, 10, 11, 12, 0};
        System.out.println(containsNearbyDuplicate(nums, 1));
    }
}
