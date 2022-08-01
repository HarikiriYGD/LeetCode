package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.Stack;

/**
 * @Auther: Lil_boat
 * @Date: 13:39 2021/12/13
 * @Description: 给你一个字符串 s ，将该字符串中的大写字母转换成相同的小写字母，返回新的字符串。
 */
public class ToLowerCase {

    public static String toLowerCase(String s) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= 'A' && chars[i] <= 'Z') {
                char c = (char) (chars[i] + ' ');
                sb.append(c);
            } else {

                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }

    public static String toLowerCase_Or(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c >= 'A' && c <= 'Z') {
                // 或方法
                int i = Character.valueOf(c) | 32;
                char c1 = (char) i;
                sb.append(c1);
            }else sb.append(c);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "HelloSFDSGS";
        System.out.println(toLowerCase(s));
        System.out.println(toLowerCase_Or(s));
    }

}
