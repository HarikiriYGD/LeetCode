package LeetCode.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenerateParenthesis_22 {

    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        dfs(res, "", 0, 0, n);
        return res;
    }

    /**
     * dfs + 剪枝
     *
     * @param res
     * @param str
     * @param l   代表左括号数
     * @param r   代表右括号数
     * @param n   左右括号数
     */
    public static void dfs(List<String> res, String str, int l, int r, int n) {
        // l
        if (l > n || r > n || r > l) return;
        // 如果左右括号数都等于 n 则加入结果集
        if (l == n && r == n) {
            res.add(str);
            return;
        }
        // 递归
        // 加入左括号
        dfs(res, str + "(", l + 1, r, n);
        // 加入右括号
        dfs(res, str + ")", l, r + 1, n);
        return;

    }

    public static void main(String[] args) {
        List<String> list = generateParenthesis(3);
        System.out.println(Arrays.toString(list.toArray()));
    }

}
