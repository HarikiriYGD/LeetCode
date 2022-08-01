package LeetCode.Practise_Everyday.Date_2022_07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Lil_boat
 * @Date: 9:36 2022/7/1
 * @Tile: 为运算表达式设计优先级
 * @Description: 给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，计算并返回所有可能组合的结果。
 * 你可以 按任意顺序 返回答案。
 * 生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 104 。
 * <p>
 * 链接：https://leetcode.cn/problems/different-ways-to-add-parentheses
 */
public class D01_DiffWaysToCompute_241 {

    /**
     * 记录已经计算过的字符串，避免重复计算
     */
    Map<String, List<Integer>> map = new HashMap<>();

    public List<Integer> diffWaysToCompute(String expression) {
        // 如果有计算过的 直接返回结果
        if (map.containsKey(expression)) {
            return map.get(expression);
        }
        // 构建结果集
        List<Integer> ans = new ArrayList<>();
        // 表达式长度
        int len = expression.length();
        for (int i = 0; i < len; i++) {
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {
                // 递归分解表达式
                List<Integer> left = diffWaysToCompute(expression.substring(0, i));
                List<Integer> right = diffWaysToCompute(expression.substring(i + 1, len));
                for (int l : left) {
                    for (int r : right) {
                        if (c == '+') {
                            ans.add(l + r);
                        } else if (c == '-') {
                            ans.add(l - r);
                        } else {
                            ans.add(l * r);
                        }
                    }
                }
            }
        }
        if (ans.size() == 0) {
            ans.add(Integer.valueOf(expression));
        }
        return ans;
    }

    public static void main(String[] args) {
        ReentrantLock r = new ReentrantLock();
        String expression = "2-1-1";
        D01_DiffWaysToCompute_241 diffWaysToCompute_241 = new D01_DiffWaysToCompute_241();
        diffWaysToCompute_241.diffWaysToCompute(expression).forEach(x -> System.out.println(x));
    }

}
