package LeetCode.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: Lil_boat
 * @Date: 10:25 2022/4/8
 * @Description: 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。
 * 请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] （若两个四元组元素一一对应，则认为两个四元组重复）：
 * <p>
 * 0 <= a, b, c, d < n
 * a、b、c 和 d 互不相同
 * nums[a] + nums[b] + nums[c] + nums[d] == target
 * 你可以按 任意顺序 返回答案 。
 */
public class FourSum_18 {

    /*
        设计一个n数之和的API
    */

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        return nSum(nums, 4, 0, target);

    }

    // n数之和API
    public static List<List<Integer>> nSum(int[] nums, int n, int start, int target) {
        int size = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        if (n < 2 || size < n) return res;
        // 求两数之和，采用双指针法
        if (n == 2) {
            int left = start;
            int right = size - 1;
            while (left < right) {
                int leftE = nums[left];
                int rightE = nums[right];
                int sum = leftE + rightE;
                if (sum < target) {
                    // 左指针去重
                    while (left < right && nums[left] == leftE) left++;
                } else if (sum > target) {
                    // 右指针去重
                    while (left < right && nums[right] == rightE) right--;
                } else {
                    // 加入一组元素
                    res.add(Arrays.asList(leftE, rightE));
                    // 去重
                    while (left < right && nums[left] == leftE) left++;
                    while (left < right && nums[right] == rightE) right--;
                }
            }
        } else {
            // 大于2个数，采用递归计算
            for (int i = start; i < size; i++) {
                // 递归
                List<List<Integer>> subRes = nSum(nums, n - 1, i + 1, target - nums[i]);
                // 加入当前元素
                for (List<Integer> list : subRes) {
                    ArrayList<Integer> arrayList = new ArrayList<>();
                    arrayList.add(nums[i]);
                    arrayList.addAll(list);
                    res.add(arrayList);
                }
                // 去重
                while (i < size - 1 && nums[i + 1] == nums[i]) i++;
            }
        }
        return res;
    }

}
