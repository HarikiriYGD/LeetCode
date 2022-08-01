package LeetCode.QueueAndStack;


import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class EvalRPN {

    /**
     * 根据 逆波兰表示法，求表达式的值。
     * 有效的算符包括 +、-、*、/ 。每个运算对象可以是整数，也可以是另一个逆波兰表达式。
     *
     * @param tokens 所需求解的表达式字符串数组
     * @return 返回运算结果
     */
    public static int evalRPN(String[] tokens) {
        Stack<Integer> stack = new Stack<>();
        for (String token : tokens) { // 遍历字符串数组
            // 判断是否是运算符号
            if (token.equals("+")) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num2 + num1);
            } else if (token.equals("*")) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num2 * num1);
            } else if (token.equals("-")) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num2 - num1);
            } else if (token.equals("/")) {
                int num1 = stack.pop();
                int num2 = stack.pop();
                stack.push(num2 / num1);
            } else {
                stack.push(Integer.parseInt(token));
            }
        }
        return stack.pop();
    }

    public static int anotherEvalRPN(String[] tokens) {
        // 枚举所有的运算符
        List<String> list = Arrays.asList("+", "-", "*", "/");
        Stack<Integer> s = new Stack<>();
        for (int i = 0; i < tokens.length; i++) {
            if (!list.contains(tokens[i])) {
                s.push(Integer.valueOf(tokens[i]));
            } else {
                // 遍历到操作符时，拿出栈顶的两个数字按照操作符操作，并将结果入栈
                // 注意先pop的是第二个操作数，后pop的才是第一个操作数
                int o2 = s.pop();
                int o1 = s.pop();
                if (tokens[i].equals("+")) {
                    s.push(o1 + o2);
                } else if (tokens[i].equals("-")) {
                    s.push(o1 - o2);
                } else if (tokens[i].equals("*")) {
                    s.push(o1 * o2);
                } else if (tokens[i].equals("/")) {
                    s.push(o1 / o2);
                }
            }
        }
        if (!s.isEmpty()) {
            return s.pop();
        }
        return 0;
    }


    public static void main(String[] args) {
        String[] s = {"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"};
        int result = evalRPN(s);
        System.out.println(result);
    }
}
