package LeetCode.AimToOffer;

/**
 * @Auther: Lil_boat
 * @Date: 15:06 2022/1/6
 * @Description: 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。
 * 一个机器人从坐标 [0, 0] 的格子开始移动，它每次可以向左、右、上、下移动一格（不能移动到方格外），
 * 也不能进入行坐标和列坐标的数位之和大于k的格子。
 * 例如，当k为18时，机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。
 * 但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 */
public class MovingCount_13 {
    // 初始化最多到达的格子数
    public static int cnt = 0;

    public static int movingCount(int m, int n, int k) {
        // 初始化访问矩阵
        int[][] board = new int[m][n];
        dfs(board, 0, 0, k);
        return cnt;
    }

    public static void dfs(int[][] board, int i, int j, int k) {
        // 位数之和
        int sum = i % 10 + i / 10 + j % 10 + j / 10;
        // 判断越界 或 访问过没有 或 位数之和是否大于 k
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == 1 || sum > k) return;
        // 访问过的格子 更改为 1
        board[i][j] = 1;
        // 格子数 + 1
        cnt++;
        // 上下左右遍历
        // 这里好像只用向左和向下搜索即可
        dfs(board, i + 1, j, k);
//        dfs(board, i - 1, j, k);
        dfs(board, i, j + 1, k);
//        dfs(board, i, j - 1, k);
    }

    public static void main(String[] args) {
        System.out.println(movingCount(20, 20, 6));
    }
}
