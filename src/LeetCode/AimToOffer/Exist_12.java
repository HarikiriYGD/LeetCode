package LeetCode.AimToOffer;

/**
 * @Auther: Lil_boat
 * @Date: 15:03 2022/1/5
 * @Description: 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。同一个单元格内的字母不允许被重复使用。
 */
public class Exist_12 {
    /*
    解题思路：
        本问题是典型的矩阵搜索问题，可使用 深度优先搜索（DFS）+ 剪枝 解决。
            深度优先搜索： 可以理解为暴力法遍历矩阵中所有字符串可能性。DFS 通过递归，先朝一个方向搜到底，再回溯至上个节点，沿另一个方向搜索，以此类推。
            剪枝： 在搜索中，遇到 这条路不可能和目标字符串匹配成功 的情况（例如：此矩阵元素和目标字符不同、此元素已被访问），则应立即返回，称之为 可行性剪枝 。
        DFS 解析：
            递归参数： 当前元素在矩阵 board 中的行列索引 i 和 j ，当前目标字符在 word 中的索引 k 。
            终止条件：
                返回 false ： (1) 行或列索引越界 或 (2) 当前矩阵元素与目标字符不同 或 (3) 当前矩阵元素已访问过 （ (3) 可合并至 (2) ） 。
                返回 true ： k = len(word) - 1 ，即字符串 word 已全部匹配。
            递推工作：
                标记当前矩阵元素： 将 board[i][j] 修改为 空字符 '' ，代表此元素已访问过，防止之后搜索时重复访问。
                搜索下一单元格： 朝当前元素的 上、下、左、右 四个方向开启下层递归，
                使用 或 连接 （代表只需找到一条可行路径就直接返回，不再做后续 DFS ），并记录结果至 res 。
                还原当前矩阵元素： 将 board[i][j] 元素还原至初始值，即 word[k] 。
            返回值： 返回布尔量 res ，代表是否搜索到目标字符串。
            使用空字符（Python: '' , Java/C++: '\0' ）做标记是为了防止标记字符与矩阵原有字符重复。
            当存在重复时，此算法会将矩阵原有字符认作标记字符，从而出现错误。
     */
    public static boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board, words, i, j, 0)) return true;
            }
        }
        return false;
    }

    public static boolean dfs(char[][] board, char[] words, int i, int j, int k) {
        // 边界情况 即剪枝操作
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != words[k]) return false;
        // 如果k的值与words的长度一致 则说明矩阵中有该单词
        if (k == words.length - 1) return true;
        // 更改访问过的点
        board[i][j] = ' ';
        // 访问上下左右四个方向的点
        boolean res = dfs(board, words, i - 1, j, k + 1) || dfs(board, words, i + 1, j, k + 1) ||
                dfs(board, words, i, j - 1, k + 1) || dfs(board, words, i, j + 1, k + 1);
        // 访问完成之后再将该点改回
        board[i][j] = words[k];
        return res;
    }

    public static void main(String[] args) {
        char[][] board = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        String word = "ABCCED";
        System.out.println(exist(board, word));

    }
}
