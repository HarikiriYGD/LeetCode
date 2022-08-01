package LeetCode.Practise_Everyday.Pre_Practise;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 13:43 2021/12/9
 * @Description: 给你一个字符串数组 board 表示井字游戏的棋盘。当且仅当在井字游戏过程中，棋盘有可能达到 board 所显示的状态时，才返回 true 。
 * 井字游戏的棋盘是一个 3 x 3 数组，由字符 ' '，'X' 和 'O' 组成。字符 ' ' 代表一个空位。
 * 以下是井字游戏的规则：
 * <p>
 * 1、玩家轮流将字符放入空位（' '）中。
 * 2、玩家 1 总是放字符 'X' ，而玩家 2 总是放字符 'O' 。
 * 3、'X' 和 'O' 只允许放置在空位中，不允许对已放有字符的位置进行填充。
 * 4、当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。
 * 5、当所有位置非空时，也算为游戏结束。
 * 6、如果游戏结束，玩家不允许再放置字符。
 */
public class ValidTicTacToe {

    /**
     * 要达到的状态
     *
     * @param board
     * @return
     */
    public static boolean validTicTacToe(String[] board) {
        // 统计X的数量
        int countX = 0;
        // 统计O的数量
        int countO = 0;
        // 新建一个二维的字符数组
        char[][] chars = new char[board.length][board.length];

        // 赋值操作
        char[][] c = StringToCharArray(chars, board);

        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < c[0].length; j++) {
                if (c[i][j] == 'X') countX++;
                if (c[i][j] == 'O') countO++;
            }
        }
        // 如果要想达到赢的状态，X和O的数量不会相差超过1
        // 而且X的数量一定大于等于O的数量
        if (countO > countX || countX - countO > 1) return false;

        else {
            // 如果都是赢家，不可能存在这种情况，则返回false
            if (isWinner(c, 'X') && isWinner(c, 'O')) return false;
            // 如果X是赢家
            if (isWinner(c, 'X')) {
                // X取胜的时候，X的数量一定比O的数量多1
                if (countX - countO == 1) return true;
                else return false;
            }
            // 如果O是赢家，则其数量必定与X数量相等
            if (isWinner(c, 'O')) {
                if (countO == countX) return true;
                else return false;
            }
        }
        return true;
    }

    /**
     * 将String[]数组转换成二维的char[][]
     * @param c
     * @param board
     * @return
     */
    public static char[][] StringToCharArray(char[][] c, String[] board) {
        // 赋值
        char[] chars = board[0].toCharArray();
        char[] chars1 = board[1].toCharArray();
        char[] chars2 = board[2].toCharArray();
        for (int i = 0; i < chars.length; i++) {
            c[0][i] = chars[i];
        }
        for (int i = 0; i < chars1.length; i++) {
            c[1][i] = chars1[i];
        }
        for (int i = 0; i < chars2.length; i++) {
            c[2][i] = chars2[i];
        }
        return c;
    }

    /**
     * 判断谁是赢家，一共8中情况判胜
     * @param chars
     * @param c 赢家
     * @return
     */
    public static boolean isWinner(char[][] chars, char c) {
        // 六条线
        for (int i = 0; i < chars.length; i++) {
            if (chars[0][i] == c && chars[1][i] == c && chars[2][i] == c) return true;
            if (chars[i][0] == c && chars[i][1] == c && chars[i][2] == c) return true;
        }
        // 两条对角线
        if (chars[0][0] == c && chars[1][1] == c && chars[2][2] == c) return true;
        if (chars[2][0] == c && chars[1][1] == c && chars[0][2] == c) return true;
        return false;
    }

    public static void main(String[] args) {
        String[] board = {"OXX", "XOX", "OXO"};
        System.out.println(validTicTacToe(board));
    }

}
