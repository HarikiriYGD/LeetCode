package LeetCode.Practise_Everyday.Date_2022_08;

import java.util.Map;

/**
 * @Author: Lil_boat
 * @Date: 2022/8/14 22:01
 * @Title: 分割字符串的最大得分
 * @Description: 给你一个由若干 0 和 1 组成的字符串 s ，请你计算并返回将该字符串分割成两个 非空 子字符串（即 左 子字符串和 右 子字符串）所能获得的最大得分。
 * 「分割字符串的得分」为 左 子字符串中 0 的数量加上 右 子字符串中 1 的数量。
 * <p>
 * 链接：https://leetcode.cn/problems/maximum-score-after-splitting-a-string
 */
public class D14_MaxScore_1422 {

    public int maxScore(String s) {
        int zero = 0, one = 0, ans = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '0') {
                zero++;
            } else {
                one++;
            }
        }
        int cnt0 = 0, cnt1 = one;
        for (int i = 0; i < s.length() - 1; i++) {
            if (s.charAt(i) == '0') {
                cnt0++;
            }else {
                cnt1--;
            }
            ans = Math.max(ans, cnt0 + cnt1);
        }
        return ans;
    }

    public static void main(String[] args) {
        D14_MaxScore_1422 t = new D14_MaxScore_1422();
        System.out.println(t.maxScore("0111101"));
    }

}
