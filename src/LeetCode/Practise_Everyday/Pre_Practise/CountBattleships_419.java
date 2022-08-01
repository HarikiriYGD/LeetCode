package LeetCode.Practise_Everyday.Pre_Practise;

/**
 * @Auther: Lil_boat
 * @Date: 13:51 2021/12/18
 * @Description: 给你一个大小为 m x n 的矩阵 board 表示甲板，
 * 其中，每个单元格可以是一艘战舰 'X' 或者是一个空位 '.' ，返回在甲板 board 上放置的 战舰 的数量。
 * <p>
 * 战舰只能水平或者垂直放置在 board 上。
 * 换句话说，战舰只能按 1 x k（1行，k列）或 k x 1（k行，1列）的形状建造，其中k可以是任意大小。
 * 两艘战舰之间至少有一个水平或垂直的空位分隔 （即没有相邻的战舰）。
 */
public class CountBattleships_419 {
    /**
     * 思想就是判断有几块 'X' 是连在一起的
     * @param board
     * @return k
     */
    public static int countBattleships(char[][] board) {
        // 行数和列数
        int m = board.length;
        int n = board[0].length;
        int k = 0;
        if (board == null || board.length == 0) return 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 遇到X战舰就进行深度遍历
                if (board[i][j] == 'X')
                    k++;
                dfs(board, i, j);
            }
        }
        return k;
    }

    /**
     * 深度遍历
     * @param board 矩阵
     * @param i 行数
     * @param j 列数
     */
    private static void dfs(char[][] board, int i, int j) {
        // 边界判断条件和判断是否是'X'
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '.') return;
        board[i][j] = '.';
        dfs(board, i - 1, j);
        dfs(board, i + 1, j);
        dfs(board, i, j - 1);
        dfs(board, i, j + 1);
    }

    /**
     * 题目进阶要求一次扫描算法，只使用 O(1) 额外空间，并且不修改甲板的值。
     * 因为题目中给定的两艘战舰之间至少有一个水平或垂直的空位分隔，任意两个战舰之间是不相邻的，
     * 因此我们可以通过枚举每个战舰的左上顶点即可统计战舰的个数。
     * 假设矩阵的行数为row，矩阵的列数col，
     * 矩阵中的位置 (i, j)为战舰的左上顶点，需满足以下条件：
     *
     * 满足当前位置所在的值 board[i][j]=’X’；
     * 满足当前位置的左则为空位，即board[i][j−1]=’.’；
     * 满足当前位置的上方为空位，即board[i−1][j]=’.’；
     * 我们统计出所有战舰的左上顶点的个数即为所有战舰的个数。
     *
     * @param board
     * @return
     */
    public static int countBattleships_lowTime(char[][] board) {
        int row = board.length;
        int col = board[0].length;
        int ans = 0;
        for (int i = 0; i < row; ++i) {
            for (int j = 0; j < col; ++j) {
                if (board[i][j] == 'X') {
                    if (i > 0 && board[i - 1][j] == 'X') {
                        continue;
                    }
                    if (j > 0 && board[i][j - 1] == 'X') {
                        continue;
                    }
                    ans++;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        char[][] board = {{'X', '.', '.', 'X'}, {'.', '.', '.', 'X'}, {'.', '.', '.', 'X'}, {'.', '.', '.', '.'}};
        char[][] board1 = {{'X', '.', '.', 'X'}, {'.', '.', '.', 'X'}, {'.', '.', '.', 'X'}, {'.', '.', '.', '.'}};
        char[][] c = {{'.'}};
        System.out.println(countBattleships(board));
        System.out.println(countBattleships_lowTime(board1));
//        System.out.println(countBattleships(c));
    }
}
