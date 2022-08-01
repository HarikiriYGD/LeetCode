package LeetCode.Practise_Everyday.Date_2022_05;

import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 9:40 2022/5/9
 * @Tile: 增减字符串匹配
 * @Description: 由范围 [0,n] 内所有整数组成的 n + 1 个整数的排列序列可以表示为长度为 n 的字符串 s ，其中:
 * <p>
 * 如果 perm[i] < perm[i + 1] ，那么 s[i] == 'I' 
 * 如果 perm[i] > perm[i + 1] ，那么 s[i] == 'D' 
 * 给定一个字符串 s ，重构排列 perm 并返回它。如果有多个有效排列perm，则返回其中 任何一个 。
 * <p>
 * 链接：https://leetcode.cn/problems/di-string-match
 */
public class D09_DiStringMatch_942 {

    public int[] diStringMatch(String s) {
        int n = s.length();
        int[] ans = new int[n + 1];
        int min = 0, max = n;
        char[] c = s.toCharArray();
        for (int i = 0; i < n; i++) {
            if (c[i] == 'I') ans[i] = min++;
            else if (c[i] == 'D') ans[i] = max--;
        }
        ans[n] = (min + max) >> 1;
        return ans;
    }

    public static void main(String[] args) {
        D09_DiStringMatch_942 t = new D09_DiStringMatch_942();
        int[] res = t.diStringMatch("III");
        System.out.println(Arrays.toString(res));
    }

}
