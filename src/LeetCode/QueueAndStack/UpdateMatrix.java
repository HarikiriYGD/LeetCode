package LeetCode.QueueAndStack;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class UpdateMatrix {
    public static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    /**
     * 给定一个由 0 和 1 组成的矩阵 mat ，
     * 请输出一个大小相同的矩阵，
     * 其中每一个格子是 mat 中对应位置元素到最近的 0 的距离。
     * 两个相邻元素间的距离为 1
     *
     * @param mat 0-1矩阵
     * @return
     */
    // 方法一：广度优先搜索
    public static int[][] updateMatrix(int[][] mat) {
        int m = mat.length; // 行
        int n = mat[0].length; // 列
        int[][] dist = new int[m][n]; // 初始化距离数组
        boolean[][] seen = new boolean[m][n];
        Queue<int[]> queue = new LinkedList<>();
        // 将所有的0添加进初始队列
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                    seen[i][j] = true;
                }
            }
        }

        // 广度优先搜索
        while (!queue.isEmpty()) {
            int[] cell = queue.poll();
            int i = cell[0];
            int j = cell[1];
            for (int d = 0; d < 4; d++) {
                int ni = i + dirs[d][0];
                int nj = j + dirs[d][1];
                if (ni >= 0 && ni < m && nj >= 0 && nj < n && !seen[ni][nj]) {
                    dist[ni][nj] = dist[i][j] + 1;
                    queue.offer(new int[]{ni, nj});
                    seen[ni][nj] = true;
                }
            }
        }
        return dist;
    }

    /**
     * 动态规划的方法实现
     *
     * @param mat 0-1矩阵
     * @return
     */
    public static int[][] updateMatrix1(int[][] mat) {
        int m = mat.length; // 行
        int n = mat[0].length; // 列
        // 初始化动态规划的数组，所有的距离都初始化为较大的一个数值
        int[][] dist = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
        }
        // 如果mat的元素为0，则将其距离修改为0
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    dist[i][j] = 0;
                }
            }
        }
        // 只有 水平向左移动 和 竖直向上运动，注意动态规划的顺序
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i - 1 >= 0) {
                    dist[i][j] = Math.min(dist[i][j], dist[i - 1][j] + 1);
                }
                if (j - 1 >= 0) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][j - 1] + 1);
                }
            }
        }

        // 只有 水平向左移动 和 竖直向下运动，注意动态规划的顺序
        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                if (i + 1 < m) {
                    dist[i][j] = Math.min(dist[i][j], dist[i + 1][j] + 1);
                }
                if (j - 1 >= 0) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][j - 1] + 1);
                }
            }
        }

        // 只有水平向左移动和竖直向下运动，注意动态规划的顺序
        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (i - 1 >= 0) {
                    dist[i][j] = Math.min(dist[i][j], dist[i - 1][j] + 1);
                }
                if (j + 1 < n) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][j + 1] + 1);
                }
            }
        }

        // 只有水平向右移动和竖直向下运动，注意动态规划的顺序
        for (int i = m - 1; i >= 0; i--) {
            for (int j = n - 1; j >= 0; j--) {
                if (i + 1 < m) {
                    dist[i][j] = Math.min(dist[i][j], dist[i + 1][j] + 1);
                }
                if (j + 1 < n) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][j + 1] + 1);
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) {
        int[][] mat = {{0, 0, 0, 0}, {0, 1, 0, 1}, {0, 1, 1, 1},
                {0, 1, 1, 1}};
        int[][] res = updateMatrix1(mat);
        String s = Arrays.deepToString(res);
        System.out.println(s);
    }
}
