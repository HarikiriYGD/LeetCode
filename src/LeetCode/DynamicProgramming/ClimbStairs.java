package LeetCode.DynamicProgramming;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 */
public class ClimbStairs {

    /**
     * 动态规划的方法
     *
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        // 构建动态数组
        int[] dp = new int[n + 1];
        // 初始台阶为1和2的上台阶方法
        dp[0] = 1;
        dp[1] = 2;
        // 动态转移方程
        for (int i = 2; i <= n - 1; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n - 1];
    }

    /**
     * 递归的方法
     *
     * @param n
     * @return
     */
    public static int climbStairs_Recursion(int n) {
        return Fibonacci(n, 1, 1);
    }

    private static int Fibonacci(int n, int a, int b) {
        if (n <= 1) return b;
        return Fibonacci(n - 1, b, a + b);
    }

    public static void main(String[] args) {
        int res = climbStairs(40);
        System.out.println(res);
    }

}
