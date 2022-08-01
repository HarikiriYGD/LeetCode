package LeetCode.Practise_Everyday.Date_2022_05;

import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 9:56 2022/5/14
 * @Tile: 贴纸拼词
 * @Description: 我们有 n 种不同的贴纸。每个贴纸上都有一个小写的英文单词。
 * 您想要拼写出给定的字符串 target ，方法是从收集的贴纸中切割单个字母并重新排列它们。如果你愿意，你可以多次使用每个贴纸，每个贴纸的数量是无限的。
 * 返回你需要拼出 target 的最小贴纸数量。如果任务不可能，则返回 -1 。
 * 注意：在所有的测试用例中，所有的单词都是从 1000 个最常见的美国英语单词中随机选择的，并且 target 被选择为两个随机单词的连接。
 * <p>
 * 链接：https://leetcode.cn/problems/stickers-to-spell-word
 */
public class D14_MinStickers_691 {

    int N = 20, M = 1 << 20, INF = 50;
    int[] f = new int[M];
    String[] ss;
    String t;

    int dfs(int state) {
        int n = t.length();
        if (state == ((1 << n) - 1)) return 0;
        if (f[state] != -1) return f[state];
        int ans = INF;
        for (String s : ss) {
            int nstate = state;
            out:
            for (char c : s.toCharArray()) {
                for (int i = 0; i < n; i++) {
                    if (t.charAt(i) == c && ((nstate >> i) & 1) == 0) {
                        nstate |= (1 << i);
                        continue out;
                    }
                }
            }
            if (nstate != state) ans = Math.min(ans, dfs(nstate) + 1);
        }
        return f[state] = ans;
    }

    public int minStickers(String[] stickers, String target) {
        ss = stickers;
        t = target;
        Arrays.fill(f, -1);
        int ans = dfs(0);
        return ans == INF ? -1 : ans;
    }
}


