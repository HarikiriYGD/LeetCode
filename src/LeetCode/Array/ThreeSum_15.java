package LeetCode.Array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: Lil_boat
 * @Date: 11:39 2022/4/1
 * @Description: 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 */
public class ThreeSum_15 {

    public static List<List<Integer>> threeSum(int[] nums) {
        // 数组的长度
        int n = nums.length;
        // 如果是空数组或者是数组长度小于3直接返回空的list
        if (nums == null || n <= 2) return new ArrayList<>();
        // 排序
        Arrays.sort(nums);
        // 构建结果集
        List<List<Integer>> res = new ArrayList<>();
        // 遍历数组
        for (int i = 0; i < n; i++) {
            // 如果当前元素已经大于0 则后面不可能会出现三数之和为0的情况 直接进行剪枝
            if (nums[i] > 0) break;
            // 去重操作
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            int l = i + 1, r = n - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    // 添加进结果集
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    // 去重操作
                    while (l < r && nums[l] == nums[l + 1]) l++;
                    while (l < r && nums[r] == nums[r - 1]) r--;
                    l++;
                    r--;
                } else if (sum > 0) r--;
                else if (sum < 0) l++;

            }
        }
        return res;
    }

    public static List<List<Integer>> threeSum_method2(int[] nums) {
        Arrays.sort(nums);
        // 如果为空集 或者是长度小于等于2 直接返回空结果集
        if (nums == null || nums.length <= 2) return new ArrayList<>();
        int n = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < nums.length - 2; i++) {
            // 去重
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            // 双指针
            int l = i + 1, r = n - 1;
            int num = nums[i];
            while (l < r) {
                if (num + nums[l] + nums[r] == 0) {
                    res.add(Arrays.asList(num, nums[l], nums[r]));
                    int tmp = nums[l];
                    while (l < r && tmp == nums[l]) l++;
                    tmp = nums[r];
                    while (l < r && tmp == nums[r]) r--;
                } else if (num + nums[l] + nums[r] > 0) r--;
                else l++;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threeSum(nums);
        List<List<Integer>> list = threeSum_method2(nums);
        System.out.println(Arrays.toString(lists.toArray()));
        System.out.println(Arrays.toString(list.toArray()));
    }

}
