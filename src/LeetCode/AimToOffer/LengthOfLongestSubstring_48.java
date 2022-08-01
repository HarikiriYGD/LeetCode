package LeetCode.AimToOffer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Lil_boat
 * @Date: 11:37 2021/12/30
 * @Description: 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 */
public class LengthOfLongestSubstring_48 {
    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        if (n == 0 || s == null) return 0;
        // 构建记录上一次出现的索引的数组
        int[] last = new int[128];
        Arrays.fill(last, -1);
        int start = 0;
        int res = 0;
        for (int i = 0; i < n; i++) {
            // 开始的索引
            int index = s.charAt(i);
            // 这个符号的开始索引
            start = Math.max(last[index] + 1, start);
            res = Math.max(res, i - start + 1);
            last[index] = i;
        }
        return res;
    }

    /*
    解题思路：
        长度为 NN 的字符串共有 {(1+N)N}/2个子字符串(复杂度为 O(N^2))，判断长度为 N 的字符串是否有重复字符的复杂度为 O(N) ，
        因此本题使用暴力法解决的复杂度为 O(N^3)。考虑使用动态规划降低时间复杂度。
    动态规划解析：
        状态定义： 设动态规划列表 dp ，dp[j] 代表以字符 s[j] 为结尾的 “最长不重复子字符串” 的长度。
        转移方程： 固定右边界 j ，设字符 s[j] 左边距离最近的相同字符为 s[i] ，即 s[i] = s[j] 。
            1. 当 i < 0 ，即 s[j] 左边无相同字符，则 dp[j] = dp[j-1] + 1 ；
            2. 当 dp[j - 1] < j - i ，说明字符 s[i] 在子字符串 dp[j-1] 区间之外 ，则 dp[j] = dp[j - 1] + 1 ；
            3. 当 dp[j - 1] ≥ j − i ，说明字符 s[i] 在子字符串 dp[j-1] 区间之中 ，则 dp[j] 的左边界由 s[i] 决定，即 dp[j] = j - i ；
        当 i < 0 时，由于 dp[j - 1] ≤ j 恒成立，因而 dp[j - 1] < j - i 恒成立，因此分支 1. 和 2. 可被合并。
                                dp[j]=  dp[j−1] + 1,    dp[j−1] < j−i
                                        j−i,            dp[j−1] ≥ j−i
        返回值： max(dp) ，即全局的 “最长不重复子字符串” 的长度。
     */
    public static int lengthOfLongestSubstring_DynamicProgramming_HashMap(String s) {
        Map<Character, Integer> dic = new HashMap<>();
        int res = 0, tmp = 0, len = s.length();
        for (int j = 0; j < len; j++) {
            // 判断该元素之前出现过没有
            // 如果没有出现过 索引初始化为 -1
            int i = dic.getOrDefault(s.charAt(j), -1);
            // 存入dic 上一次出现的索引
            dic.put(s.charAt(j), j);
            // 判断当前不重复字符串的长度
            tmp = tmp < j - i ? tmp + 1 : j - i;
            // 取最大的长度
            res = Math.max(res,tmp);
        }
        return res;
    }

    public static void main(String[] args) {
        String s = "abcabcbbxbhjafgwiefgwfjdskfa";
        System.out.println(lengthOfLongestSubstring(s));
        System.out.println(lengthOfLongestSubstring_DynamicProgramming_HashMap(s));
    }
}
