package LeetCode.QueueAndStack;

public class NumIsIslands {
    /**
     * 记录岛屿的数量
     *
     * @param grid
     * @return
     * @author Guodan Yang
     */
    public static int numIslands(char[][] grid) {

        if (grid == null || grid.length == 0) return 0;
        int result = 0; // 岛屿数量
        int m = grid.length; // 矩阵的行
        int n = grid[0].length; // 矩阵的列
        for (int i = 0; i < m; i++) { // 遍历所有的点
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j, m, n);
                    result++; // 记录岛屿数量
                }
            }
        }
        return result;
    }

    /**
     * dfs
     *
     * @param grid 传入数组
     * @param i    当前行
     * @param j    当前列
     * @param m    矩阵的行数
     * @param n    矩阵的列数
     */
    private static void dfs(char[][] grid, int i, int j, int m, int n) {
        if (i < 0 || j < 0 || i >= m || j >= n || grid[i][j] == '0') return;
        grid[i][j] = '0';
        dfs(grid, i + 1, j, m, n);
        dfs(grid, i, j + 1, m, n);
        dfs(grid, i - 1, j, m, n);
        dfs(grid, i, j - 1, m, n);
    }


    public static void main(String[] args) {
        char[][] test = {{'1', '1'}, {'1', '0'}};
        int result = numIslands(test);
        System.out.println(result);

    }
}


