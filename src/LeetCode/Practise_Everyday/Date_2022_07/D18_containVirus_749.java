package LeetCode.Practise_Everyday.Date_2022_07;

/**
 * @Author: Lil_boat
 * @Date: 2022/7/18 8:30
 * @Title: 隔离病毒
 * @Description: 病毒扩散得很快，现在你的任务是尽可能地通过安装防火墙来隔离病毒。
 * 假设世界由 m x n 的二维矩阵 isInfected 组成， isInfected[i][j] == 0 表示该区域未感染病毒，
 * 而  isInfected[i][j] == 1 表示该区域已感染病毒。可以在任意 2 个相邻单元之间的共享边界上安装一个防火墙（并且只有一个防火墙）。
 * <p>
 * 每天晚上，病毒会从被感染区域向相邻未感染区域扩散，除非被防火墙隔离。
 * 现由于资源有限，每天你只能安装一系列防火墙来隔离其中一个被病毒感染的区域（一个区域或连续的一片区域），
 * 且该感染区域对未感染区域的威胁最大且 保证唯一 。
 * <p>
 * 你需要努力使得最后有部分区域不被病毒感染，如果可以成功，那么返回需要使用的防火墙个数;
 * 如果无法实现，则返回在世界被病毒全部感染时已安装的防火墙个数。
 * <p>
 * 链接：https://leetcode.cn/problems/contain-virus
 */
public class D18_containVirus_749 {

    int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    int curWall = 0;
    int rows;
    int cols;

    public int containVirus(int[][] grid) {
        rows = grid.length;
        cols = grid[0].length;
        int result = 0;
        while (true) {
            int walls = getMaxAreaNeedWalls(grid);
            if (walls == 0) {
                break;
            }
            result += walls;
        }
        return result;
    }

    private int getMaxAreaNeedWalls(int[][] grid) {
        //maxArea代表最大的感染区,ans代表需要修的墙的数量,
        //targetX、targetY表示此区域的DFS开始的坐标
        //state表示当前区域要修建墙的状态,如果没有修过,需要给maxArea+1,修过的话，只加墙的数量,不加maxArea
        //并且每个区域的state都是不一样的，互相不能影响 DFS完给state-1
        int maxArea = 0, ans = 0, targetX = -1, targetY = -1, state = -3;
        //存放访问的状态
        int[][] visited = new int[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                //找到没有访问的病毒区域
                if (grid[i][j] == 1 && visited[i][j] == 0) {
                    //当前区域需要的防火墙数量
                    curWall = 0;
                    //当前区域能感染的数量
                    int curMaxArea = dfs(grid, visited, i, j, state);
                    if (curMaxArea > maxArea) {
                        maxArea = curMaxArea;
                        ans = curWall;
                        targetX = i;
                        targetY = j;
                    }
                    state--;
                }
            }
        }
        if (targetX == -1) {
            return 0;
        }
        //将活跃的病毒改为死亡状态
        modifyDead(grid, targetX, targetY);
        visited = new int[rows][cols];
        //病毒扩散
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1 && visited[i][j] == 0) {
                    spread(grid, visited, i, j);
                }
            }
        }
        return ans;
    }

    private void spread(int[][] grid, int[][] visited, int x, int y) {
        visited[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int newX = x + dirs[i][0];
            int newY = y + dirs[i][1];
            if (newX >= 0 && newX < rows && newY >= 0 && newY < cols
                    && visited[newX][newY] == 0) {
                //扩散区域
                if (grid[newX][newY] == 0) {
                    grid[newX][newY] = 1;
                    visited[newX][newY] = 1;
                } else if (grid[newX][newY] == 1) {
                    spread(grid, visited, newX, newY);
                }
            }
        }
    }

    private void modifyDead(int[][] grid, int x, int y) {
        grid[x][y] = -2;
        for (int i = 0; i < 4; i++) {
            int newX = x + dirs[i][0];
            int newY = y + dirs[i][1];
            if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && grid[newX][newY] == 1) {
                modifyDead(grid, newX, newY);
            }
        }
    }

    private int dfs(int[][] grid, int[][] visited, int x, int y, int state) {
        int curArea = 0;
        visited[x][y] = 1;
        for (int i = 0; i < 4; i++) {
            int newX = x + dirs[i][0];
            int newY = y + dirs[i][1];
            if (newX >= 0 && newX < rows && newY >= 0 && newY < cols && visited[newX][newY] != 1) {
                //不是病毒
                if (grid[newX][newY] == 0) {
                    curWall++;
                    //是否已经访问，是的话只加墙，不是需要加墙和区域（一个区域有四个墙）
                    if (visited[newX][newY] != state) {
                        curArea++;
                        visited[newX][newY] = state;
                    }
                } else if (grid[newX][newY] == 1) {
                    //是存活病毒
                    curArea += dfs(grid, visited, newX, newY, state);
                }
            }
        }
        return curArea;
    }

}
