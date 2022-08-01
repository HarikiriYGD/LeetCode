package LeetCode.Practise_Everyday.Pre_Practise;

/**
 * @param null
 * @Auther: Lil_boat
 * @Date: 16:37 2021/12/1
 * @Return
 * @Description: 给你一个字符串 s ，字符串的「能量」定义为：只包含一种字符的最长非空子字符串的长度。
 * 请你返回字符串的能量。
 */
public class MaxPower {

    /**
     * @param s
     * @return
     */
    public static int maxPower(String s) {
        if (s.length() == 0 || s == null) return 0;
        // 定义最大的能量
        int max = 0;
        // 存储当前最大的能量
        int temp = 0;
        char[] chars = s.toCharArray();
        char c = chars[0];
        for (int i = 0; i < chars.length; i++) {
            // 如果与下一个字符相等，则当前能量+1
            if (c == chars[i]) {
                temp++;
            } else {
                // 如果不与下一个字符相等，则让下一个字符作为开始比较的第一个字符
                c = chars[i];
                temp = 1;
            }
            // 选择最大的能量
            max = Math.max(max, temp);
        }
        return max;
    }

    /**
     * 思想与上述类似
     *
     * @param s
     * @return
     */
    public static int maxPower_One(String s) {
        int ans = 1, cnt = 1;
        for (int i = 1; i < s.length(); ++i) {
            if (s.charAt(i) == s.charAt(i - 1)) {
                ++cnt;
                ans = Math.max(ans, cnt);
            } else {
                cnt = 1;
            }
        }
        return ans;

    }

    public static void main(String[] args) {
        String s = "j";
        System.out.println(maxPower_One(s));
    }

}
