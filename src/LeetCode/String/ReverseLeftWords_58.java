package LeetCode.String;

public class ReverseLeftWords_58 {
    /**
     * 如果面试中不允许使用语法糖，则使用下面的方法
     *
     * @param s
     * @param n
     * @return
     */
    public static String reverseLeftWords(String s, int n) {
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = n; i < chars.length; i++) {
            sb.append(chars[i]);
        }
        for (int i = 0; i < n; i++) {
            sb.append(chars[i]);
        }
        return sb.toString();
    }

    /**
     * 简化写法
     * @param s
     * @param n
     * @return
     */
    public static String reverseLeftWords_Simplify(String s, int n) {
        return s.substring(n, s.length()) + s.substring(0, n);
    }

    public static void main(String[] args) {
        String s = "abcdefg";
        System.out.println(reverseLeftWords(s, 2));
        System.out.println(reverseLeftWords_Simplify(s, 2));
    }
}
