package LeetCode.String;

/**
 * @Auther: Lil_boat
 * @Date: 17:18 2021/12/22
 * @Description: 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 */
public class ReplaceSpace_05 {
    public static String replaceSpace(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)==' ')sb.append("%20");
            else sb.append(s.charAt(i));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "we are happy";
        System.out.println(replaceSpace(s));
    }
}
