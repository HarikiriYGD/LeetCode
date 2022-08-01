package LeetCode.Practise_Everyday.Date_2022_06;

import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 10:25 2022/6/11
 * @Tile: 将字符串翻转到单调递增
 * @Description: 如果一个二进制字符串，是以一些 0（可能没有 0）后面跟着一些 1（也可能没有 1）的形式组成的，那么该字符串是 单调递增 的。
 * 给你一个二进制字符串 s，你可以将任何 0 翻转为 1 或者将 1 翻转为 0 。
 * 返回使 s 单调递增的最小翻转次数。
 * <p>
 * 链接：https://leetcode.cn/problems/flip-string-to-monotone-increasing
 */
public class D11_MinFlipsMonoIncr_926 {
    /**
     * 贪心策略
     *
     * @param s
     * @return
     */
    public int minFlipsMonoIncr(String s) {
        char[] cs = s.toCharArray();
        int n = cs.length, ans = 0;
        int[] g = new int[n + 10];
        Arrays.fill(g, n + 10);
        for (int i = 0; i < n; i++) {
            int t = s.charAt(i) - '0';
            int l = 1, r = i + 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (g[mid] > t) r = mid;
                else l = mid + 1;
            }
            g[r] = t;
            ans = Math.max(ans, r);
        }
        return n - ans;
    }

    /**
     * 动态规划
     *
     * @param s
     * @return
     */
    public int minFlipsMonoIncr_dp(String s) {
        int dp0 = 0, dp1 = 0;
        char[] chars = s.toCharArray();
        for (char ch : chars) {
            //先更新dp[i][1]，需要用到dp[i-1][0] 由于只有0和1，也不在使用三目运算
            dp1 = Math.min(dp0, dp1) + ('1' - ch);
            dp0 += ch - '0';
        }
        return Math.min(dp0, dp1);
    }


}
