package LeetCode.AimToOffer;

/**
 * @Auther: Lil_boat
 * @Date: 17:00 2021/12/27
 * @Description: 一只青蛙一次可以跳上1级台阶，也可以跳上2级台阶。求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * <p>
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 */

public class NumWays_10 {
    /**
     * 动态规划
     *
     * @param n
     * @return
     */
    public static int numWays(int n) {
        // 边界判断条件
        if (n == 0) return 1;
        int[] dp = new int[n + 1];
        // 初始化状态
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }
        return dp[n];
    }

    /**
     * 记忆化搜索 + 递归
     *
     * @param n
     * @return
     */
    public static int numWays_Recursion(int n) {
        int[] dp = new int[n + 1];
        return myWay(n, dp);
    }

    public static int myWay(int n, int[] dp) {
        if (n == 0) return 1;
        if (n == 1) return 1;
        if (dp[n] != 0) return dp[n] % 1000000007;
        dp[n] = myWay(n - 1, dp) + myWay(n - 2, dp);
        return dp[n]% 1000000007;
    }

    public static void main(String[] args) {
        System.out.println(numWays(100));
        System.out.println(numWays_Recursion(100));
    }
}
