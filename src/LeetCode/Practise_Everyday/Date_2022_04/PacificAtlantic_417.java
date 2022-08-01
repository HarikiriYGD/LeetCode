package LeetCode.Practise_Everyday.Date_2022_04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @Auther: Lil_boat
 * @Date: 10:33 2022/4/27
 * @Tile: 太平洋大西洋水流问题
 * @Description: 有一个 m × n 的矩形岛屿，与 太平洋 和 大西洋 相邻。 “太平洋” 处于大陆的左边界和上边界，而 “大西洋” 处于大陆的右边界和下边界。
 *
 * 这个岛被分割成一个由若干方形单元格组成的网格。给定一个 m x n 的整数矩阵 heights ， heights[r][c] 表示坐标 (r, c) 上单元格 高于海平面的高度 。
 *
 * 岛上雨水较多，如果相邻单元格的高度 小于或等于 当前单元格的高度，雨水可以直接向北、南、东、西流向相邻单元格。水可以从海洋附近的任何单元格流入海洋。
 *
 * 返回 网格坐标 result 的 2D列表 ，其中 result[i] = [ri, ci] 表示雨水可以从单元格 (ri, ci) 流向 太平洋和大西洋 。
 */
public class PacificAtlantic_417 {


    public static List<List<Integer>> pacificAtlantic(int[][] heights) {
        int m = heights.length;
        int n = heights[0].length;
        List<List<Integer>> ans = new ArrayList<>();
        // 记录可以流向太平洋的单元格
        boolean[][] pacific = new boolean[m][n];
        // 记录可以流向大西洋的单元格
        boolean[][] atlantic = new boolean[m][n];
        // 从左右边界开始访问
        for (int i = 0; i < m; i++) {
            dfs(heights, heights[i][0], i, 0, pacific);
            dfs(heights, heights[i][n - 1], i, n - 1, atlantic);
        }
        // 从上下边界开始访问
        for (int i = 0; i < n; i++) {
            dfs(heights, heights[0][i], 0, i, pacific);
            dfs(heights, heights[m - 1][i], m - 1, i, atlantic);
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (pacific[i][j] && atlantic[i][j]) {
                    ans.add(Arrays.asList(i, j));
                }
            }
        }
        return ans;
    }

    private static void dfs(int[][] heights, int pre, int i, int j, boolean[][] visited) {
        // 边界结束条件
        // 如果已经访问过的单元格 直接返回
        // 如果当前单元格 小于 前一个访问的单元格 则说明不能流向
        if (i >= heights.length || i < 0 || j >= heights[0].length || j < 0 || visited[i][j] || heights[i][j] < pre)
            return;
        visited[i][j] = true;
        dfs(heights, heights[i][j], i + 1, j, visited);
        dfs(heights, heights[i][j], i - 1, j, visited);
        dfs(heights, heights[i][j], i, j + 1, visited);
        dfs(heights, heights[i][j], i, j - 1, visited);
    }

    public static void main(String[] args) {
        int[][] heights = {{1, 2, 2, 3, 5}, {3, 2, 3, 4, 4}, {2, 4, 5, 3, 1}, {6, 7, 1, 4, 5}, {5, 1, 1, 2, 4}};
        List<List<Integer>> res = pacificAtlantic(heights);
        System.out.println(Arrays.deepToString(res.toArray()));
    }

}
