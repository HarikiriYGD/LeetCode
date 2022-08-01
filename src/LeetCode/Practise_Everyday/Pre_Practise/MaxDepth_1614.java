package LeetCode.Practise_Everyday.Pre_Practise;

/**
 * @Auther: Lil_boat
 * @Date: 9:46 2022/1/7
 * @Description: 如果字符串满足以下条件之一，则可以称之为 有效括号字符串（valid parentheses string，可以简写为 VPS）：
 * <p>
 * 字符串是一个空字符串 ""，或者是一个不为 "(" 或 ")" 的单字符。
 * 字符串可以写为 AB（A 与 B 字符串连接），其中 A 和 B 都是 有效括号字符串 。
 * 字符串可以写为 (A)，其中 A 是一个 有效括号字符串 。
 * 类似地，可以定义任何有效括号字符串 S 的 嵌套深度 depth(S)：
 * <p>
 * depth("") = 0
 * depth(C) = 0，其中 C 是单个字符的字符串，且该字符不是 "(" 或者 ")"
 * depth(A + B) = max(depth(A), depth(B))，其中 A 和 B 都是 有效括号字符串
 * depth("(" + A + ")") = 1 + depth(A)，其中 A 是一个 有效括号字符串
 * 例如：""、"()()"、"()(()())" 都是 有效括号字符串（嵌套深度分别为 0、1、2），而 ")(" 、"(()" 都不是 有效括号字符串 。
 * <p>
 * 给你一个 有效括号字符串 s，返回该字符串的 s 嵌套深度 。
 */

public class MaxDepth_1614 {

    /*
        根据题意，其实就是求最大的连续左括号的数量（跳过普通字符，且与 ) 抵消后），只需要边遍历边统计即可。
     */
    public static int maxDepth(String s) {
        if (s == null || s.length() == 0) return 0;
        int sum = 0;
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                sum++;
                max = max > sum ? max : sum;
            }
            if (s.charAt(i) == ')') sum--;
        }
        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxDepth(" "));
    }

}
