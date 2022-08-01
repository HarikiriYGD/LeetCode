package LeetCode.String;

/**
 * @Auther: Lil_boat
 * @Date: 15:43 2021/12/16
 * @Description: 给定一个字符串 s请你找出其中不含有重复字符的最长子串 的长度。
 */

import java.util.Arrays;

public class LengthOfLongestSubstring {

    public static int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) return 0;
        // 记录字符上一次出现的位置
        int[] last = new int[128];
        Arrays.fill(last, -1);
        // 字符串的长度
        int n = s.length();
        int res = 0;
        int start = 0;
        for (int i = 0; i < n; i++) {
            // 对应字符的ASCII码
            int index = s.charAt(i);
            start = Math.max(start, last[index] + 1);
            res = Math.max(res, i - start + 1);
            // 将字符出现的位置进行更新
            last[index] = i;
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "~~~1ab@c1~@~~abcbb";
        String s1 = "";
        System.out.println(lengthOfLongestSubstring(s));
        System.out.println(lengthOfLongestSubstring(s1));
    }

}
