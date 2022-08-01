package LeetCode.Practise_Everyday.Date_2022_07;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Lil_boat
 * @Date: 10:19 2022/7/3
 * @Tile: 下一个更大元素 III
 * @Description: 给你一个正整数 n ，请你找出符合条件的最小整数，其由重新排列 n 中存在的每位数字组成，并且其值大于 n 。
 * 如果不存在这样的正整数，则返回 -1 。
 * 注意 ，返回的整数应当是一个 32 位整数 ，如果存在满足题意的答案，但不是 32 位整数 ，同样返回 -1 。
 * <p>
 * 链接：https://leetcode.cn/problems/next-greater-element-iii
 */
public class D03_NextGreaterElement_556 {

    public int nextGreaterElement(int n) {
        List<Integer> nums = new ArrayList<>();
        // 将nums中的每一位数字存储到nums中
        // 从高位往低位存储
        while (n != 0) {
            nums.add(n % 10);
            n /= 10;
        }
        int len = nums.size(), idx = -1;
        for (int i = 0; i < len - 1 && idx == -1; i++) {
            // 寻找后一位比前一位大的数字
            if (nums.get(i + 1) < nums.get(i)) {
                idx = i + 1;
            }
        }
        // 如果没有找到，表明已经是最小的数字了
        if (idx == -1) {
            return -1;
        }
        // 交换前面的数字
        for (int i = 0; i < idx; i++) {
            if (nums.get(i) > nums.get(idx)) {
                swap(nums, i, idx);
                break;
            }
        }
        for (int l = 0, r = idx - 1; l < r; l++, r--) {
            swap(nums, l, r);
        }
        long ans = 0;
        for (int i = len - 1; i >= 0; i--) {
            ans = ans * 10 + nums.get(i);
        }
        return ans > Integer.MAX_VALUE ? -1 : (int) ans;
    }

    /**
     * 交换元素
     *
     * @param nums
     * @param a    交换的索引
     * @param b    交换的索引
     */
    private void swap(List<Integer> nums, int a, int b) {
        int tmp = nums.get(a);
        nums.set(a, nums.get(b));
        nums.set(b, tmp);
    }

    public static void main(String[] args) {
        D03_NextGreaterElement_556 t = new D03_NextGreaterElement_556();
        t.nextGreaterElement(1242);
    }

}
