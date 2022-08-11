package LeetCode.Practise_Everyday.Date_2022_08;


import java.util.Arrays;

/**
 * @Author: Lil_boat
 * @Date: 2022/8/11 20:46
 * @Title: 重新格式化字符串
 * @Description: 给你一个混合了数字和字母的字符串 s，其中的字母均为小写英文字母。
 * 请你将该字符串重新格式化，使得任意两个相邻字符的类型都不同。也就是说，字母后面应该跟着数字，而数字后面应该跟着字母。
 * 请你返回 重新格式化后 的字符串；如果无法按要求重新格式化，则返回一个 空字符串 。
 * <p>
 * 链接：https://leetcode.cn/problems/reformat-the-string
 */
public class D11_Reformat_1417 {

    public String reformat(String s) {
        StringBuilder sb = new StringBuilder();
        char[] cs = s.toCharArray();
        Arrays.sort(cs);
        for (int i = 0, j = cs.length - 1; i < j; i++, j--) {
            if (cs[i] > '9' || cs[j] < 'a') {
                return "";
            }
            // 添加数字
            sb.append(cs[i]);
            // 添加字母
            sb.append(cs[j]);
        }
        if (cs.length % 2 == 1) {
            // 中间字符是字母。字母多，插到串首
            if (cs[cs.length / 2] > '9') {
                sb.insert(0, cs[cs.length / 2]);
            } else {
                sb.append(cs[cs.length / 2]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        D11_Reformat_1417 t = new D11_Reformat_1417();
        System.out.println(t.reformat("ab123"));
    }

}
