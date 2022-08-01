package LeetCode.Practise_Everyday.Date_2022_06;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Lil_boat
 * @Date: 10:42 2022/6/16
 * @Tile: 数组中的 k-diff 数对
 * @Description: 给你一个整数数组 nums 和一个整数 k，请你在数组中找出 不同的 k-diff 数对，并返回不同的 k-diff 数对 的数目。
 * k-diff 数对定义为一个整数对 (nums[i], nums[j]) ，并满足下述全部条件：
 * <p>
 * 0 <= i, j < nums.length
 * i != j
 * nums[i] - nums[j] == k
 * 注意，|val| 表示 val 的绝对值。
 * <p>
 * 链接：https://leetcode.cn/problems/k-diff-pairs-in-an-array
 */
public class D16_FindPairs_532 {

    public int findPairs(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        int ans = 0;
        for (int num : nums) {
            if (map.get(num) == 0) continue;
            if (k == 0) {
                if (map.get(num) > 1) ans++;
            } else {
                int a = num - k, b = num + k;
                if (map.getOrDefault(a, 0) > 0) ans++;
                if (map.getOrDefault(b, 0) > 0) ans++;
            }
            // 用过的num将数量清零
            map.put(num, 0);
        }
        return ans;
    }


    public int findPairs_Binary(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length, res = 0;
        for (int i = 0; i < n; i++) {
            // 去重操作
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            // 寻找比nums[i]大k的数是否存在
            int target = nums[i] + k;
            int left = i + 1, right = n - 1;
            int ans = -1;
            while (left <= right) {
                int mid = left + right >> 1;
                if (nums[mid] >= target) {
                    // 记录索引
                    ans = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
            // 判断是否找到
            if (ans != -1 && nums[ans] == target) res++;
        }
        return res;
    }

}
