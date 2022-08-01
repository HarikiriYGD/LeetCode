package LeetCode.AimToOffer;

import java.util.Map;

/**
 * @Auther: Lil_boat
 * @Date: 10:38 2021/12/30
 * @Description: 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
 * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
 * 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 */

public class MaxValue_47 {
    /*
    解题思路：
        题目说明：从棋盘的左上角开始拿格子里的礼物，并每次 向右 或者 向下 移动一格、直到到达棋盘的右下角。
        根据题目说明，易得某单元格只可能从上边单元格或左边单元格到达。
        设 f(i, j) 为从棋盘左上角走至单元格 (i ,j) 的礼物最大累计价值，易得到以下递推关系：
        f(i,j) 等于 f(i,j-1) 和 f(i-1,j) 中的较大值加上当前单元格礼物价值 grid(i,j)。
                     f(i,j) = max[f(i,j-1), f(i-1,j)] + grid(i,j)
        因此，可用动态规划解决此问题，以上公式便为转移方程。
    动态规划解析：
        一、状态定义： 设动态规划矩阵 dp ，dp(i,j) 代表从棋盘的左上角开始，到达单元格 (i,j) 时能拿到礼物的最大累计价值。
        二、转移方程：
            I. 当 i = 0 且 j = 0 时，为起始元素；
            II. 当 i = 0 且 j != 0 时，为矩阵第一行元素，只可从左边到达；
            III. 当 i != 0 且 j = 0 时，为矩阵第一列元素，只可从上边到达；
            IV. 当 i != 0 且 j != 0 时，可从左边或上边到达；
                        grid(i,j)                           i = 0 && j = 0;
                        grid(i,j) + dp(i, j - 1)            i = 0 && j != 0;
            dp(i, j) =  grid(i,j) + dp(i - 1,j)             i != 0 && j = 0;
                        grid(i,j) + Math{dp(i,j-1), dp(i -1,j)} i != 0 && j != 0;

        三、初始状态： dp[0][0] = grid[0][0] ，即到达单元格 (0,0) 时能拿到礼物的最大累计价值为 grid[0][0] ；
        四、返回值： dp[m-1][n-1] ，m, n 分别为矩阵的行高和列宽，即返回 dp 矩阵右下角元素。
     空间复杂度降低：
        由于 dp[i][j] 只与 dp[i-1][j] , dp[i][j-1] , grid[i][j] 有关系，因此可以将原矩阵 grid 用作 dp 矩阵，即直接在 grid 上修改即可。
        应用此方法可省去 dp 矩阵使用的额外空间，因此空间复杂度从 O(MN) 降至 O(1) 。
     */
    public static int maxValue(int[][] grid) {
        int row = grid.length, col = grid[0].length;
        if (grid == null || row == 0) return 0;
        // 初始化第一行
        for (int j = 1; j < col; j++) {
            grid[0][j] += grid[0][j - 1];
        }
        // 初始化第一列
        for (int i = 1; i < row; i++) {
            grid[i][0] += grid[i - 1][0];
        }
        for (int i = 1; i < row; i++) {
            for (int j = 1; j < col; j++) {
                grid[i][j] += Math.max(grid[i][j - 1], grid[i - 1][j]);
            }
        }
        return grid[row - 1][col - 1];
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 3, 6}, {1, 6, 1}, {4, 2, 1}};
        System.out.println(maxValue(grid));
    }
}
