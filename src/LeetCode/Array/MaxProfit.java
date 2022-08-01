package LeetCode.Array;

public class MaxProfit {

    /**
     * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
     * <p>
     * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
     * <p>
     * 作者：力扣 (LeetCode)
     *
     * @param prices 股票每日价格
     * @return 返回收益值
     */
    public static int maxProfit(int[] prices) {
        int profit = 0; // 定义利润
        if (prices.length == 0) return 0;
        for (int i = 0; i < prices.length - 1; i++) {
            int tmp = prices[i];
            if (tmp < prices[i + 1]) {
                profit += prices[i + 1] - prices[i];
            }
        }
        return profit;
    }

    /**
     * 贪心算法实现
     *
     * @param prices 股票每日价格
     * @return 返回收益值
     */
    public static int maxProfit_GreedyAlgorithms(int[] prices) {
        if (prices.length == 0 || prices == null) return 0;
        int ans = 0;
        for (int i = 1; i < prices.length; i++) {
            ans += Math.max(0, prices[i] - prices[i - 1]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] prices = {6, 5, 4, 3, 2, 1};
        int res = maxProfit_GreedyAlgorithms(prices);
        System.out.println(res);
    }
}
