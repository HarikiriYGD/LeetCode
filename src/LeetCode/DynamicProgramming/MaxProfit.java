package LeetCode.DynamicProgramming;

import java.util.Arrays;

/**
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * <p>
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * <p>
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 */
public class MaxProfit {

    /**
     * 双指针的方法
     *
     * @param prices
     * @return
     */
    public static int maxProfit_DoubleLink(int[] prices) {
        if (prices.length == 0 || prices == null) return 0;
        int profit = 0; // 定义最大利润
        int min = prices[0]; // 记录访问过的最小股票值
        for (int i = 1; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            profit = Math.max(prices[i] - min, profit);
        }
        return profit;
    }

    /**
     * 动态规划的方法
     * <p>
     * dp[i][0]：规定了今天不持股，有以下两种情况：
     * 昨天不持股，今天什么都不做；
     * 昨天持股，今天卖出股票（现金数增加），
     * dp[i][1]：规定了今天持股，有以下两种情况：
     * 昨天持股，今天什么都不做（现金数与昨天一样）；
     * 昨天不持股，今天买入股票（注意：只允许交易一次，因此手上的现金数就是当天的股价的相反数）。
     *
     * @param prices
     * @return
     */
    public static int maxProfit_DP(int[] prices) {
        if (prices.length == 0 || prices == null) return 0;
        int length = prices.length;
        int[][] dp = new int[length][2];
        // 初始化dp
        // dp[i][0] 下标为 i 这天结束的时候，不持股，手上拥有的现金数
        // dp[i][1] 下标为 i 这天结束的时候，持股，手上拥有的现金数

        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        // 从第 2 天开始遍历
        for (int i = 1; i < length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], -prices[i]);
            System.out.println(Arrays.deepToString(dp));
        }
        return dp[length - 1][0];

    }


    public static void main(String[] args) {
        int[] prices = {6, 5, 4, 3, 2, 1};
        int res = maxProfit_DP(prices);
        System.out.println(res);
    }
}
