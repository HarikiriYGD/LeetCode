package LeetCode.Every_weeks_competition.WeiLai;

import java.util.Arrays;

/**
 * @Author: Lil_boat
 * @Date: 13:43 2022/7/4
 * @Tile: 网格图中递增路径的数目
 * @Description: 给你一个 m x n 的整数网格图 grid ，你可以从一个格子移动到 4 个方向相邻的任意一个格子。
 * 请你返回在网格图中从 任意 格子出发，达到 任意 格子，且路径中的数字是 严格递增 的路径数目。由于答案可能会很大，请将结果对 10^9 + 7 取余 后返回。
 * 如果两条路径中访问过的格子不是完全相同的，那么它们视为两条不同的路径。
 * <p>
 * https://leetcode.cn/problems/number-of-increasing-paths-in-a-grid/
 */
public class CountPaths {

    private static final int MOD = (int) 1e9 + 7;
    private long ans = 0;
    private int[][] grid;

    public int countPaths(int[][] grid) {
        this.grid = grid;
        int m = grid.length, n = grid[0].length;
        long[][] dp = new long[m][n];
        Arrays.fill(dp, 0);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                dfs(i, j, dp, 0);
            }
        }
        return (int) ans % MOD;
    }

    private long dfs(int i, int j, long[][] dp, long last_value) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] <= last_value) {
            return 0;
        }
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        dp[i][j] = 1;
        dp[i][j] += dfs(i + 1, j, dp, grid[i][j]) % MOD;
        dp[i][j] += dfs(i - 1, j, dp, grid[i][j]) % MOD;
        dp[i][j] += dfs(i, j + 1, dp, grid[i][j]) % MOD;
        dp[i][j] += dfs(i, j - 1, dp, grid[i][j]) % MOD;
        ans = ans % MOD + dp[i][j] % MOD;
        return dp[i][j];
    }

}
