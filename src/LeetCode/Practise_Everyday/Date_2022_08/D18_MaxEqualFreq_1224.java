package LeetCode.Practise_Everyday.Date_2022_08;

import java.util.Arrays;

/**
 * @Author: Lil_boat
 * @Date: 2022/8/18 20:58
 * @Title: 最大相等频率
 * @Description: 给你一个正整数数组 nums，请你帮忙从该数组中找出能满足下面要求的 最长 前缀，并返回该前缀的长度：
 * <p>
 * 从前缀中 恰好删除一个 元素后，剩下每个数字的出现次数都相同。
 * 如果删除这个元素后没有剩余元素存在，仍可认为每个数字都具有相同的出现次数（也就是 0 次）。
 * <p>
 * 链接：https://leetcode.cn/problems/maximum-equal-frequency
 */
public class D18_MaxEqualFreq_1224 {
    /**
     * cnt表示每个数出现的次数
     * sum表示共有多少个数字出现了相同的次数
     */
    static int[] cnt = new int[100010], sum = new int[100010];

    public int maxEqualFreq(int[] nums) {
        // ans表示结果集
        // max表示出现的最大次数
        int ans = 0, max = 0;
        Arrays.fill(cnt, 0);
        Arrays.fill(sum, 0);
        for (int i = 0; i < nums.length; i++) {
            int t = nums[i], cur = ++cnt[t], len = i + 1;
            sum[cur]++;
            sum[cur - 1]--;
            max = Math.max(max, cur);
            if (max == 1) {
                ans = len;
            }
            if (max * sum[max] + 1 == len) {
                ans = len;
            }
            if ((max - 1) * (sum[max - 1] + 1) + 1 == len) {
                ans = len;
            }
        }
        return ans;
    }

}
