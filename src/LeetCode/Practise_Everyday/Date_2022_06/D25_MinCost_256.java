package LeetCode.Practise_Everyday.Date_2022_06;

/**
 * @Auther: Lil_boat
 * @Date: 11:36 2022/6/25
 * @Tile: 剑指 Offer II 091. 粉刷房子
 * @Description: 假如有一排房子，共 n 个，每个房子可以被粉刷成红色、蓝色或者绿色这三种颜色中的一种，
 * 你需要粉刷所有的房子并且使其相邻的两个房子颜色不能相同。
 * 当然，因为市场上不同颜色油漆的价格不同，所以房子粉刷成不同颜色的花费成本也是不同的。
 * 每个房子粉刷成不同颜色的花费是以一个 n x 3 的正整数矩阵 costs 来表示的。
 * <p>
 * 例如，costs[0][0] 表示第 0 号房子粉刷成红色的成本花费；costs[1][2] 表示第 1 号房子粉刷成绿色的花费，以此类推。
 * 请计算出粉刷完所有房子最少的花费成本。
 * <p>
 * 链接：https://leetcode.cn/problems/JEj789
 */
public class D25_MinCost_256 {

    /**
     * 动态规划
     *
     * @param costs
     * @return
     */
    public int minCost(int[][] costs) {
        int n = costs.length;
        int[][] dp = new int[n + 1][3];
        for (int i = 0; i < n; i++) {
            dp[i + 1][0] = costs[i][0] + Math.min(dp[i][1], dp[i][2]);
            dp[i + 1][1] = costs[i][1] + Math.min(dp[i][0], dp[i][2]);
            dp[i + 1][2] = costs[i][2] + Math.min(dp[i][0], dp[i][1]);
        }
        return Math.min(dp[n][0], Math.min(dp[n][1], dp[n][2]));
    }


    public static void main(String[] args) {
        D25_MinCost_256 t = new D25_MinCost_256();
        int[][] costs = new int[][]{{17,2,17},{16,16,5},{14,3,19}};
        System.out.println(t.minCost(costs));
    }

}
