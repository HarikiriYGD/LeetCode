package LeetCode.Practise_Everyday.Date_2022_04;
/**
 * @Auther: Lil_boat
 * @Date: 10:04 2022/4/26
 * @Tile: 三维形体投影面积
 * @Description: 在 n x n 的网格 grid 中，我们放置了一些与 x，y，z 三轴对齐的 1 x 1 x 1 立方体。
 *
 * 每个值 v = grid[i][j] 表示 v 个正方体叠放在单元格 (i, j) 上。
 *
 * 现在，我们查看这些立方体在 xy 、yz 和 zx 平面上的投影。
 * 投影 就像影子，将 三维 形体映射到一个 二维 平面上。从顶部、前面和侧面看立方体时，我们会看到“影子”。
 * 返回 所有三个投影的总面积 。
 */
public class ProjectionArea {

    public static int projectionArea(int[][] grid) {
        int ans = 0, n = grid.length;
        for (int i = 0; i < n; i++) {
            int xz = grid[i][0], yz = grid[0][i];
            for (int j = 0; j < n; j++) {
                // 求xy投影面的面积
                if (grid[i][j] != 0) ans++;
                // 求每一行的最大值
                xz = Math.max(xz, grid[i][j]);
                // 求每一列的最大值
                yz = Math.max(yz, grid[j][i]);
            }
            ans += xz + yz;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1,2},{3,4}};
        System.out.println(projectionArea(grid));
    }

}
