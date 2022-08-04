package LeetCode.Practise_Everyday.Date_2022_08;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Lil_boat
 * @Date: 2022/8/4 20:24
 * @Title: 非递增顺序的最小子序列
 * @Description: 给你一个数组 nums，请你从中抽取一个子序列，满足该子序列的元素之和 严格 大于未包含在该子序列中的各元素之和。
 * 如果存在多个解决方案，只需返回 长度最小 的子序列。如果仍然有多个解决方案，则返回 元素之和最大 的子序列。
 * 与子数组不同的地方在于，「数组的子序列」不强调元素在原数组中的连续性，也就是说，它可以通过从数组中分离一些（也可能不分离）元素得到。
 * 注意，题目数据保证满足所有约束条件的解决方案是 唯一 的。同时，返回的答案应当按 非递增顺序 排列。
 * <p>
 * 链接：https://leetcode.cn/problems/minimum-subsequence-in-non-increasing-order
 */
public class D04_MinSubsequence_1403 {

    public List<Integer> minSubsequence(int[] nums) {
        if (nums.length == 1) {
            return Arrays.asList(nums[0]);
        }
        Arrays.sort(nums);
        List<Integer> ans = new ArrayList<>();
        int sum = 0, tmp = 0;
        for (int num : nums) {
            sum += num;
        }
        for (int i = nums.length - 1; i >= 0; i--) {
            if (tmp > sum) {
                break;
            } else {
                sum -= nums[i];
                tmp += nums[i];
                ans.add(nums[i]);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        D04_MinSubsequence_1403 t = new D04_MinSubsequence_1403();
        int[] nums = {4, 4, 7, 6, 7};
        List<Integer> list = t.minSubsequence(nums);
        list.forEach(System.out::println);
    }

}
