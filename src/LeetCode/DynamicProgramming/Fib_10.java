package LeetCode.DynamicProgramming;

/**
 * @Auther: Lil_boat
 * @Date: 16:34 2021/12/27
 * @Description: 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 * <p>
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 */
public class Fib_10 {
    /**
     * 暴力递归
     * 这个会超时
     *
     * @param n
     * @return
     */
    public static int fib_Violence(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fib_Violence(n - 1) + fib_Violence(n - 2);
    }

    /**
     * 记忆化搜索
     *
     * @param n
     * @return
     */
    public static int fib_Memory(int n) {
        int[] dp = new int[n + 1];
        return dyMethod(n, dp) % 1000000007;
    }

    public static int dyMethod(int n, int[] dp) {
        if (n == 0) return dp[0] = 0;
        if (n == 1) return dp[1] = 1;
        // 判断是否计算过，如果计算过直接返回
        if (dp[n] != 0) return dp[n] % 1000000007;
        dp[n] = dyMethod(n - 1, dp) + dyMethod(n - 2, dp);
        return dp[n] % 1000000007;
    }

    /**
     * 动态规划的方式
     *
     * @param n
     * @return
     */
    public static int fib(int n) {
        if (n == 0) return 0;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2]) % 1000000007;
        }
        return dp[n];
    }

    public static void main(String[] args) {
        System.out.println(fib_Violence(5));
        System.out.println(fib_Memory(50));
        System.out.println(fib(50));
    }
}
