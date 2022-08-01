package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 15:22 2021/12/7
 * @Description: 给你一个大小为 m x n 的整数矩阵 grid ，表示一个网格。另给你三个整数 row、col 和 color 。
 * 网格中的每个值表示该位置处的网格块的颜色。
 * 当两个网格块的颜色相同，而且在四个方向中任意一个方向上相邻时，它们属于同一 连通分量 。
 * 连通分量的边界 是指连通分量中的所有与不在分量中的网格块相邻（四个方向上）的所有网格块，或者在网格的边界上（第一行/列或最后一行/列）的所有网格块。
 * 请你使用指定颜色 color 为所有包含网格块 grid[row][col] 的 连通分量的边界 进行着色，并返回最终的网格 grid 。
 */
public class ColorBorder {

    public static int[][] colorBorder(int[][] grid, int row, int col, int color) {
        if (grid[row][col] == color) return grid;
        int pre = grid[row][col];
        int[][] mask = new int[grid.length][grid[0].length];
        dfs(grid, row, col, pre, color, mask);
        for (int i = 1; i < grid.length - 1; i++) {
            for (int j = 1; j < grid[0].length - 1; j++) {
                // 判断是否是边界元素
                if (mask[i][j] == 1 && mask[i - 1][j] == 1 && mask[i + 1][j] == 1 && mask[i][j - 1] == 1 && mask[i][j + 1] == 1) {
                    grid[i][j] = pre;
                }
            }
        }
        return grid;
    }

    /**
     * dfs深度遍历
     *
     * @param grid  数组
     * @param x     当前横坐标
     * @param y     当前纵坐标
     * @param pre   前一个颜色
     * @param color 要上的颜色
     * @param mask  记录变化的数组
     */
    public static void dfs(int[][] grid, int x, int y, int pre, int color, int[][] mask) {
        // 边界判断条件
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] != pre) return;
        // 将其变色
        grid[x][y] = color;
        // 记录变色的数组的索引
        mask[x][y] = 1;
        dfs(grid, x - 1, y, pre, color, mask);
        dfs(grid, x + 1, y, pre, color, mask);
        dfs(grid, x, y - 1, pre, color, mask);
        dfs(grid, x, y + 1, pre, color, mask);
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}, {1, 1, 1, 1}};
        int[][] colorBorder = colorBorder(grid, 1, 2, 3);
        System.out.println(Arrays.deepToString(colorBorder));
    }

}
