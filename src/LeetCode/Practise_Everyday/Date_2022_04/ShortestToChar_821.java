package LeetCode.Practise_Everyday.Date_2022_04;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: Lil_boat
 * @Date: 9:54 2022/4/19
 * @Tile: 字符的最短距离
 * @Description: 给你一个字符串 s 和一个字符 c ，且 c 是 s 中出现过的字符。
 * <p>
 * 返回一个整数数组 answer ，其中 answer.length == s.length 且 answer[i] 是 s 中从下标 i 到离它 最近 的字符 c 的 距离 。
 * <p>
 * 两个下标 i 和 j 之间的 距离 为 abs(i - j) ，其中 abs 是绝对值函数。
 */
public class ShortestToChar_821 {

    /*
        两次遍历：
            第一次遍历找到右边最近的字符
            第二次遍历找到左边最近的字符
     */
    public static int[] shortestToChar(String s, char c) {
        int n = s.length();
        int[] ans = new int[n];
        Arrays.fill(ans, n + 1);
        for (int i = 0, j = -1; i < n; i++) {
            if (s.charAt(i) == c) j = i;
            if (j != -1) ans[i] = i - j;
        }
        for (int i = n - 1, j = -1; i >= 0; i--) {
            if (s.charAt(i) == c) j = i;
            if (j != -1) ans[i] = Math.min(ans[i], j - i);
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "loveleetcode";
        int[] ans = shortestToChar(s, 'e');
        System.out.println(Arrays.toString(ans));
    }

}
