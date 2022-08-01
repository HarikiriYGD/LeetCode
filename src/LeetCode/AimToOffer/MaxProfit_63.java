package LeetCode.AimToOffer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: Lil_boat
 * @Date: 11:44 2022/1/5
 * @Description: 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 */
public class MaxProfit_63 {

    /**
     * 运用双指针的思想
     * 找到价格最低的一个
     *
     * @param prices
     * @return
     */
    public static int maxProfit_Double_Poninter(int[] prices) {
        if (prices.length == 0 || prices == null) return 0;
        int min = prices[0];
        int pro = 0;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            pro = Math.max(prices[i] - min, pro);
        }
        return pro;
    }

    /*
    解题思路：
        设共有 n 天，第 a 天买，第 b 天卖，则需保证 a < b ；可推出交易方案数共有：(n−1)+(n−2)+⋯+2+1=n(n−1)/2
        因此，暴力法的时间复杂度为 O(n^2)。考虑使用动态规划降低时间复杂度。
    动态规划解析：
        状态定义： 设动态规划列表 dp ，dp[i] 代表以 prices[i] 为结尾的子数组的最大利润（以下简称为 前 i 日的最大利润 ）。
        转移方程： 由于题目限定 “买卖该股票一次” ，因此前 i 日最大利润 dp[i] 等于前 i - 1 日最大利润 dp[i-1] 和第 i 日卖出的最大利润中的最大值。
                    dp[i] = max(dp[i - 1], prices[i] - min(prices[0:i]))
                                            ↑
                    前 i 日最大利润 = max(前 (i-1) 日最大利润, 第 i 日价格 - 前 i 日最低价格)
        初始状态： dp[0] = 0 ，即首日利润为 0 ；
        返回值： dp[n - 1] ，其中 n 为 dp 列表长度。
     */
    public static int maxProfit_dp(int[] prices) {
        int cost = Integer.MAX_VALUE, profit = 0;
        for(int price : prices) {
            cost = Math.min(cost, price);
            profit = Math.max(profit, price - cost);
        }
        return profit;
    }


    public static void main(String[] args) {
        int[] prices = {7,1,5,4,6,3};
        System.out.println(maxProfit_Double_Poninter(prices));
        System.out.println(maxProfit_dp(prices));
    }
}
