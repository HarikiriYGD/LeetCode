package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 10:34 2022/2/24
 * @Description: 用一个大小为 m x n 的二维网格 grid 表示一个箱子。你有 n 颗球。箱子的顶部和底部都是开着的。
 * 箱子中的每个单元格都有一个对角线挡板，跨过单元格的两个角，可以将球导向左侧或者右侧。
 * 将球导向右侧的挡板跨过左上角和右下角，在网格中用 1 表示。
 * 将球导向左侧的挡板跨过右上角和左下角，在网格中用 -1 表示。
 * 在箱子每一列的顶端各放一颗球。每颗球都可能卡在箱子里或从底部掉出来。如果球恰好卡在两块挡板之间的 "V" 形图案，
 * 或者被一块挡导向到箱子的任意一侧边上，就会卡住。
 * 返回一个大小为 n 的数组 answer ，其中 answer[i] 是球放在顶部的第 i 列后从底部掉出来的那一列对应的下标，如果球卡在盒子里，则返回 -1 。
 */
public class FindBall_1706 {

    /*
         1.基本判断规则
                如果是1，就要看右边是不是1
                如果是-1，就要看左边是不是-1
                如果需要查看另一边超出边界，判断卡住
         2.移动规则
                如果满足判断规则，如果原来为1，判断下一行右边一格
                如果原来为-1，判断下一行左边一格
     */
    public static int[] findBall(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[] res = new int[n];
        // 表示小球最开始出发的列数
        for (int i = 0; i < n; i++) {
            res[i] = i;
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                int x = res[j];
                if (x == -1) continue;
                else {
                    // 如果斜率为1 则表示向右移动一格
                    if (grid[i][x] == 1 && x + 1 < n && grid[i][x + 1] == 1) res[j]++;
                    // 如果斜率为-1 则表示向左移动一格
                    else if (grid[i][x] == -1 && x - 1 >= 0 && grid[i][x - 1] == -1) res[j]--;
                    // 如果都不是 则表明形成了V字形 小球不再下落
                    else res[j] = -1;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 1, 1, -1, -1}, {1, 1, 1, -1, -1}, {-1, -1, -1, 1, 1}, {1, 1, 1, 1, -1}, {-1, -1, -1, -1, 1}};
        int[] ball = findBall(grid);
        System.out.println(Arrays.toString(ball));
    }

}
