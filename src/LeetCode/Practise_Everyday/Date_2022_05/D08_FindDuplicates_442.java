package LeetCode.Practise_Everyday.Date_2022_05;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: Lil_boat
 * @Date: 23:34 2022/5/8
 * @Tile: 数组中重复的数据
 * @Description: 给你一个长度为 n 的整数数组 nums ，其中 nums 的所有整数都在范围 [1, n] 内，
 * 且每个整数出现 一次 或 两次 。请你找出所有出现 两次 的整数，并以数组形式返回。
 * <p>
 * 你必须设计并实现一个时间复杂度为 O(n) 且仅使用常量额外空间的算法解决此问题。
 * <p>
 * 链接：https://leetcode-cn.com/problems/find-all-duplicates-in-an-array
 */
public class D08_FindDuplicates_442 {

    public List<Integer> findDuplicates(int[] nums) {
        // 构建结果集
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            // 记录当前的元素值
            int t = nums[i];
            // 如果是负数表明已经添加进结果集
            // 如果该元素已在该在的位置 直接跳过
            if (t < 0 || t - 1 == i) continue;
            // 如果该元素的前一个元素仍是该元素 则表明出现了两次
            if (nums[t - 1] == t) {
                ans.add(t);
                // 置为负数
                nums[t - 1] *= -1;
            } else {
                // 置换操作
                int temp = nums[t - 1];
                nums[t - 1] = t;
                nums[i--] = temp;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        D08_FindDuplicates_442 t = new D08_FindDuplicates_442();
        int[] nums = {4, 3, 2, 7, 8, 2, 3, 1};
        List<Integer> ans = t.findDuplicates(nums);
        System.out.println(Arrays.toString(ans.toArray()));
    }

}
