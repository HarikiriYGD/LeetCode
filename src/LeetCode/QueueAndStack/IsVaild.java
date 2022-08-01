package LeetCode.QueueAndStack;

import java.util.Stack;

public class IsVaild {
    /**
     * 括号匹配
     *
     * @param s 传入所要匹配的括号
     * @return 返回是否匹配的布尔值
     */
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>(); // 构造栈
        char[] chars = s.toCharArray(); // 将字符串转换为字符数组
        for (char c : chars) { // 遍历字符数组
            if (c == '(') { // 将右括号压入栈
                stack.push(')');
            } else if (c == '[') {
                stack.push(']');
            } else if (c == '{') {
                stack.push('}');
            } else if (stack.isEmpty() || stack.pop() != c) { // 匹配右括号
                return false;
            }
        }
        return stack.isEmpty(); // 栈为空则匹配成功，反之，失败
    }


    public static void main(String[] args) {
        boolean valid = isValid("[{{{}}}]");
        System.out.println(valid);
    }
}
