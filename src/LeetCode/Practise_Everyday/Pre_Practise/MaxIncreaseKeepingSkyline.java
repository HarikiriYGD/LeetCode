package LeetCode.Practise_Everyday.Pre_Practise;

/**
 * @Auther: Lil_boat
 * @Date: 14:45 2021/12/13
 * @Description: 给你一座由 n x n 个街区组成的城市，每个街区都包含一座立方体建筑。给你一个下标从 0 开始的 n x n 整数矩阵 grid ，
 * 其中 grid[r][c] 表示坐落于 r 行 c 列的建筑物的 高度 。
 * <p>
 * 城市的天际线是从远处观察城市时，所有建筑物形成的外部轮廓。从东、南、西、北四个主要方向观测到的天际线可能不同。
 * 我们被允许为 任意数量的建筑物 的高度增加 任意增量（不同建筑物的增量可能不同）。
 * 高度为0的建筑物的高度也可以增加。然而，增加的建筑物高度不能影响 从任何主要方向观察城市得到的天际线 。
 * 在不改变从任何主要方向观测到的城市天际线的前提下，返回建筑物可以增加的最大高度增量总和 。
 */
public class MaxIncreaseKeepingSkyline {

    /**
     * 可以简化一下代码，但是时间效率好像要高一些
     * @param grid
     * @return
     */
    public static int maxIncreaseKeepingSkyline(int[][] grid) {
        // 记录grid的行数和列数
        int row = grid.length;
        int col = grid[0].length;
        // 记录每一行的最大值
        int[] rowMax = new int[row];
        // 记录每一列的最大值
        int[] colMax = new int[col];
        int max = 0, sum = 0, sumChange = 0;
        // 寻找每一行最大的数
        for (int i = 0; i < row; i++) {
            // 令每一行的第一个元素是最大值
            max = grid[i][0];
            for (int j = 0; j < col; j++) {
                if (max < grid[i][j]) max = grid[i][j];
                sum += grid[i][j];
            }
            // 找出每一行的最大值后赋值给rowMax数组
            rowMax[i] = max;
        }
        // 寻找每一列的最大的数
        for (int j = 0; j < col; j++) {
            // 令每一列的第一个元素是最大值
            max = grid[0][j];
            for (int i = 0; i < row; i++) {
                if (max < grid[i][j]) max = grid[i][j];
            }
            // 找出每一列的最大值后赋值给colMax数组
            colMax[j] = max;
        }
        // 更新数组，先将每一行都更新为colMax中所求每一行的最大值
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                grid[i][j] = rowMax[i];
            }
        }
        for (int j = 0; j < col; j++) {
            for (int i = 0; i < row; i++) {
                // 判断每一列的元素的最大值是否大于所求colMax中所求每一列的最大值
                // 如果大于则替换为colMax中对应每一列中的最大值
                if (grid[i][j] > colMax[j]) grid[i][j] = colMax[j];
                sumChange += grid[i][j];
            }
        }

        // 变化之后的和 - 未变化之前的和
        return sumChange - sum;
    }

    /**
     * 简化代码后
     * @param grid
     * @return
     */
    public static int maxIncreaseKeepingSkyline_simplify(int[][] grid) {
        int n = grid.length;
        int[] rowMax = new int[n];
        int[] colMax = new int[n];
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                rowMax[i] = Math.max(rowMax[i], grid[i][j]);
                colMax[j] = Math.max(colMax[j], grid[i][j]);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                ans += Math.min(rowMax[i], colMax[j]) - grid[i][j];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] grid = {{3, 0, 8, 4}, {2, 4, 5, 7}, {9, 2, 6, 3}, {0, 3, 1, 0}};
        int[][] grid1 = {{3, 0, 8, 4}, {2, 4, 5, 7}, {9, 2, 6, 3}, {0, 3, 1, 0}};
        System.out.println(maxIncreaseKeepingSkyline(grid));
        System.out.println(maxIncreaseKeepingSkyline_simplify(grid1));
    }

}
