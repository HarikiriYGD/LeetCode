package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.HashMap;

/**
 * @Auther: Lil_boat
 * @Date: 10:34 2022/2/17
 * @Description: 在一个 n x n 的国际象棋棋盘上，一个骑士从单元格 (row, column) 开始，
 * 并尝试进行 k 次移动。行和列是 从 0 开始 的，所以左上单元格是 (0,0) ，右下单元格是 (n - 1, n - 1) 。
 * 象棋骑士有8种可能的走法，如下图所示。每次移动在基本方向上是两个单元格，然后在正交方向上是一个单元格。
 */
public class KnightProbability_688 {
    // 定义八种移动的方向
    static private final int[][] dirs = new int[][]{{-1, 2}, {1, 2}, {-1, -2}, {1, -2}, {2, 1}, {2, -1}, {-2, 1}, {-2, -1}};

    /*
        定义 f[i][j][p] 为从位置 (i, j) 出发，使用步数不超过 p 步，最后仍在棋盘内的概率。
        不失一般性考虑 f[i][j][p] 该如何转移，根据题意，移动规则为「八连通」，对下一步的落点 (nx, ny) 进行分情况讨论即可：
            * 由于计算的是仍在棋盘内的概率，因此对于 (nx, ny) 在棋盘外的情况，无须考虑；
            * 若下一步的落点 (nx, ny) 在棋盘内，其剩余可用步数为 p - 1，则最后仍在棋盘的概率为 f[nx][ny][p - 1]，
            * 则落点 (nx, ny) 对 f[i][j][p] 的贡献为 f[nx][ny][p - 1] × 1/8，其中 1/8为事件「从 (i, j) 走到 (nx, ny)」的概率（八连通移动等概率发生），
            * 该事件与「到达 (nx, ny)(nx,ny) 后进行后续移动并留在棋盘」为相互独立事件。

        最终的 f[i][j][p]f[i][j][p] 为「八连通」落点的概率之和，即有：
                            f[i][j][p] = ∑f[nx][ny][p−1] × 1/8
     */
    public static double knightProbability(int n, int k, int row, int column) {
        double[][][] f = new double[n][n][k + 1];
        // 初始化f 因为最开始即第0步时骑士都是在棋盘上 所以其概率为1
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                f[i][j][0] = 1;
            }
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,map.getOrDefault(0,0) + 1);
        for (Integer integer : map.keySet()) {

        }
        // 动态规划
        for (int p = 1; p <= k; p++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    for (int[] d : dirs) {
                        int nx = i + d[0], ny = j + d[1];
                        if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
                        f[i][j][p] += f[nx][ny][p - 1] / 8;
                    }
                }
            }
        }
        return f[row][column][k];
    }

    public static void main(String[] args) {
        System.out.println(knightProbability(3, 2, 0, 0));
    }

}
