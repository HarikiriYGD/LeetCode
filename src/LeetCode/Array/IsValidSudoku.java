package LeetCode.Array;

/**
 * 请你判断一个 9x9 的数独是否有效。只需要 根据以下规则 ，验证已经填入的数字是否有效即可。
 * <p>
 * 数字 1-9 在每一行只能出现一次。
 * 数字 1-9 在每一列只能出现一次。
 * 数字 1-9 在每一个以粗实线分隔的 3x3 宫内只能出现一次。（请参考示例图）
 * 数独部分空格内已填入了数字，空白格用 '.' 表示。
 */
public class IsValidSudoku {

    public static boolean isValidSudoku(char[][] board) {
        int length = board.length; // 行数
        int rows[][] = new int[length][length]; // 用来存储行上的数字是否有相同的数字
        int columns[][] = new int[length][length]; // 用来存储列上的数字是否有相同的数字
        int tmp[][][] = new int[3][3][length]; // 用来存储3×3的矩阵内有无重复元素
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (board[i][j] != '.') {
                    char c = board[i][j];
                    int index = c - '0' - 1;
                    rows[i][index]++;
                    columns[j][index]++;
                    tmp[i / 3][j / 3][index]++;
                    if (rows[i][index] > 1 || columns[j][index] > 1 || tmp[i / 3][j / 3][index] > 1) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }

}
