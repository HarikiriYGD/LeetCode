package LeetCode.AimToOffer;

import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 16:26 2022/1/4
 * @Description: 我们把只包含质因子 2、3 和 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
 */
public class NthUglyNumber_49 {
    public static int nthUglyNumber(int n) {
        // 构建动规数组
        int[] dp = new int[n];
        // 初始状态
        dp[0] = 1;
        // 质因子 2 3 5
        int index = 0, i = 0, j = 0, k = 0;
        while (index++ < n - 1) {
            // 每次取最小的乘数来存储
            int temp = Math.min(dp[i] * 2, Math.min(dp[j] * 3, dp[k] * 5));
            dp[index] = temp;
            // 如果有相等的 则将其下标+1
            if (temp == dp[i] * 2) i++;
            if (temp == dp[j] * 3) j++;
            if (temp == dp[k] * 5) k++;
        }
        // 返回第n个丑数
        return dp[n - 1];
    }

    /**
     * 质因子只含有 2 3 5 7的第n个数
     * @param n
     * @return
     */
    public static int nthUglyNumber_Four(int n) {
        // 构建动规数组
        int[] dp = new int[n];
        dp[0] = 1;
        int index = 0, i = 0, j = 0, k = 0, m = 0;
        while (index++ < n - 1) {
            int temp = Math.min(Math.min(dp[i] * 2, dp[j] * 3), Math.min(dp[k] * 5, dp[m] * 7));
            dp[index] = temp;
            if (temp == dp[i] * 2) i++;
            if (temp == dp[j] * 3) j++;
            if (temp == dp[k] * 5) k++;
            if (temp == dp[m] * 7) m++;
        }
        System.out.println(Arrays.toString(dp));
        return dp[n - 1];
    }

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(1650));
        System.out.println(nthUglyNumber_Four(21));
    }
}
