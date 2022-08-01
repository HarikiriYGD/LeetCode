package LeetCode.Practise_Everyday.Pre_Practise;
/**
 * @Auther: Lil_boat
 * @Date: 9:41 2022/2/12
 * @Description: 给你一个大小为 m x n 的二进制矩阵 grid ，其中 0 表示一个海洋单元格、1 表示一个陆地单元格。
 * 一次 移动 是指从一个陆地单元格走到另一个相邻（上、下、左、右）的陆地单元格或跨过 grid 的边界。
 * 返回网格中 无法 在任意次数的移动中离开网格边界的陆地单元格的数量。
 */
public class NumEnclaves_1020 {

    /*
        从地图四边界上的陆地往地图里面进行深搜,最后再遍历一遍地图，此时还存在的陆地就是无法到达的陆地。
     */
    public static int numEnclaves(int[][] grid) {
        int cnt = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                // 从边缘是陆地的开始深度搜索
                if (grid[i][j] == 1 && (i == 0 || j == 0 || i == grid.length - 1 || j == grid[0].length - 1)) {
                    dfs(grid, i, j);
                }
            }
        }
        // 统计内部为1的陆地数量
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 1) cnt++;

            }
        }
        return cnt;
    }

    public static void dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 0) return;
        grid[i][j] = 0;
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 0, 0, 1, 1, 1, 0, 1, 0, 0}, {1, 1, 0, 0, 0, 1, 0, 1, 1, 1}, {0, 0, 0, 1, 1, 1, 0, 1, 0, 0}, {0, 1, 1, 0, 0, 0, 1, 0, 1, 0}, {0, 1, 1, 1, 1, 1, 0, 0, 1, 0},
                {0, 0, 1, 0, 1, 1, 1, 1, 0, 1}, {0, 1, 1, 0, 0, 0, 1, 1, 1, 1}, {0, 0, 1, 0, 0, 1, 0, 1, 0, 1}, {1, 0, 1, 0, 1, 1, 0, 0, 0, 0}, {0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 1}};
        System.out.println(numEnclaves(grid));
    }
}
