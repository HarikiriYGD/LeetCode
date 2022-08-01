package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 10:19 2022/2/13
 * @Description: 给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。
 * 字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。
 */
public class MaxNumberOfBalloons_1189 {
    public static int maxNumberOfBalloons(String text) {
        int[] cnt = new int[5];
        char[] c = text.toCharArray();
        for (int i = 0; i < c.length; i++) {
            // a
            if (c[i] - 'a' == 0) cnt[0]++;
                // b
            else if (c[i] - 'a' == 1) cnt[1]++;
                // l
            else if (c[i] - 'a' == 11) cnt[2]++;
                // n
            else if (c[i] - 'a' == 13) cnt[3]++;
                // o
            else if (c[i] - 'a' == 14) cnt[4]++;
        }
        cnt[2] /= 2;
        cnt[4] /= 2;
        Arrays.sort(cnt);
        return cnt[0];
    }

    public static void main(String[] args) {
        String text = "loonbalxballpoon";
        System.out.println(maxNumberOfBalloons(text));
    }
}
