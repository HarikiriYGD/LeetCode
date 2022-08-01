package LeetCode.Practise_Everyday.Date_2022_07;

import java.util.Arrays;

/**
 * @Author: Lil_boat
 * @Date: 2022/7/10 13:39
 * @Title: 摘樱桃
 * @Description: 一个N x N的网格(grid) 代表了一块樱桃地，每个格子由以下三种数字的一种来表示：
 * <p>
 * 0 表示这个格子是空的，所以你可以穿过它。
 * 1 表示这个格子里装着一个樱桃，你可以摘到樱桃然后穿过它。
 * -1 表示这个格子里有荆棘，挡着你的路。
 * 你的任务是在遵守下列规则的情况下，尽可能的摘到最多樱桃：
 * <p>
 * 从位置 (0, 0) 出发，最后到达 (N-1, N-1) ，只能向下或向右走，并且只能穿越有效的格子（即只可以穿过值为0或者1的格子）；
 * 当到达 (N-1, N-1) 后，你要继续走，直到返回到 (0, 0) ，只能向上或向左走，并且只能穿越有效的格子；
 * 当你经过一个格子且这个格子包含一个樱桃时，你将摘到樱桃并且这个格子会变成空的（值变为0）；
 * 如果在 (0, 0) 和 (N-1, N-1) 之间不存在一条可经过的路径，则没有任何一个樱桃能被摘到。
 * <p>
 * 链接：https://leetcode.cn/problems/cherry-pickup
 */
public class D10_CherryPickup_741 {

    private int[][] grid;
    private int ans;
    private static int m, n;
    private boolean flag = false;

    public int cherryPickup(int[][] grid) {
        this.grid = grid;
        // 获取矩阵的长度和宽度
        m = grid.length;
        n = grid[0].length;
        dfs(0, 0, m - 1, n - 1);
        flag = false;
        dfs(m - 1, n - 1, 0, 0);
        return flag ? ans : 0;
    }

    private void dfs(int x, int y, int destX, int destY) {
        // 边界判断条件
        if (x < 0 || x >= m || y < 0 || y >= n || flag || grid[x][y] == -1) {
            return;
        }
        if (grid[x][y] == 1) {
            ans++;
            grid[x][y] = 0;
        }
        if (x == destX && y == destY) {
            flag = true;
            return;
        }
        if (destX == m - 1 && destY == n - 1) {
            // 向下寻找
            dfs(x + 1, y, destX, destY);
            // 向右寻找
            dfs(x, y + 1, destX, destY);
        } else {
            dfs(x - 1, y, destX, destY);
            dfs(x, y - 1, destX, destY);
        }


    }

    public static void main(String[] args) {
        D10_CherryPickup_741 t = new D10_CherryPickup_741();
        int[][] grid = {{1, 1, 1, 1, 0, 0, 0},
                        {0, 0, 0, 1, 0, 0, 0},
                        {0, 0, 0, 1, 0, 0, 1},
                        {1, 0, 0, 1, 0, 0, 0},
                        {0, 0, 0, 1, 0, 0, 0},
                        {0, 0, 0, 1, 0, 0, 0},
                        {0, 0, 0, 1, 1, 1, 1}};
        System.out.println(t.cherryPickup(grid));
        System.out.println(Arrays.deepToString(grid));
    }

}
