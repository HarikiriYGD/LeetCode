package LeetCode.Practise_Everyday.Date_2022_07;

import java.util.HashMap;

/**
 * @Author: Lil_boat
 * @Date: 2022/7/6 11:06
 * @Title: Lisp 语法解析
 * @Description: 给你一个类似 Lisp 语句的字符串表达式 expression，求出其计算结果。
 * 表达式语法如下所示:
 * 表达式可以为整数，let 表达式，add 表达式，mult 表达式，或赋值的变量。表达式的结果总是一个整数。
 * (整数可以是正整数、负整数、0)
 * let 表达式采用 "(let v1 e1 v2 e2 ... vn en expr)" 的形式，其中 let 总是以字符串 "let"来表示，接下来会跟随一对或多对交替的变量和表达式，也就是说，第一个变量 v1被分配为表达式 e1 的值，第二个变量 v2 被分配为表达式 e2 的值，依次类推；最终 let 表达式的值为 expr表达式的值。
 * add 表达式表示为 "(add e1 e2)" ，其中 add 总是以字符串 "add" 来表示，该表达式总是包含两个表达式 e1、e2 ，最终结果是 e1 表达式的值与 e2 表达式的值之 和 。
 * mult 表达式表示为 "(mult e1 e2)" ，其中 mult 总是以字符串 "mult" 表示，该表达式总是包含两个表达式 e1、e2，最终结果是 e1 表达式的值与 e2 表达式的值之 积 。
 * 在该题目中，变量名以小写字符开始，之后跟随 0 个或多个小写字符或数字。为了方便，"add" ，"let" ，"mult" 会被定义为 "关键字" ，不会用作变量名。
 * 最后，要说一下作用域的概念。计算变量名所对应的表达式时，在计算上下文中，首先检查最内层作用域（按括号计），然后按顺序依次检查外部作用域。测试用例中每一个表达式都是合法的。有关作用域的更多详细信息，请参阅示例。
 * <p>
 * 链接：https://leetcode.cn/problems/parse-lisp-expression
 */
public class D06_Evaluate_736 {

    public int evaluate(String expression) {
        return parse(expression, 0, expression.length(), new HashMap<>());
    }

    private int parse(String expression, int start, int end, HashMap<String, Integer> vars) {
        // 去掉首尾括号
        if (expression.charAt(start) == '(') {
            start++;
            end--;
        }
        // 寻找第一个空格
        int firstSpace = start;
        while (firstSpace < end) {
            if (expression.charAt(firstSpace) == ' ') {
                break;
            }
            firstSpace++;
        }
        // 解析当前表达式
        String type = expression.substring(start, firstSpace);
        if (type.equals("let")) {
            // let 表达式
            // 有 2 * n + 1 个子表达式
            // let v1 e1 v2 e2 ... vn en expr
            HashMap<String, Integer> subVars = new HashMap<>(vars);
            int pre = firstSpace + 1;
            for (int i = firstSpace + 1; i <= end; ) {
                // v 或者是 expr
                int braceCount = 0;
                while (i <= end) {
                    if (braceCount == 0 && (i == end || expression.charAt(i) == ' ')) {
                        break;
                    } else if (expression.charAt(i) == '(') {
                        braceCount++;
                    } else if (expression.charAt(i) == ')') {
                        braceCount--;
                    }
                    i++;
                }
                if (i == end) {
                    return parse(expression, pre, i, subVars);
                } else {
                    String v = expression.substring(pre, i);
                    pre = ++i;
                    braceCount = 0;
                    while (i < end) {
                        if (braceCount == 0 && (i == end || expression.charAt(i) == ' ')) {
                            break;
                        } else if (expression.charAt(i) == '(') {
                            braceCount++;
                        } else if (expression.charAt(i) == ')') {
                            braceCount--;
                        }
                        i++;
                    }
                    // 递归
                    int e = parse(expression, pre, i, subVars);
                    pre = ++i;
                    subVars.put(v, e);
                }
            }
            return 0;
        } else if (type.equals("mult") || type.equals("add")) {
            // add mult 表达式
            // 只有两个子表达式
            // add e1 e2
            // mult e1 e2

            int braceCount = 0;
            int secondSpace = firstSpace + 1;
            while (secondSpace < end) {
                char c = expression.charAt(secondSpace);
                if (c == ' ' && braceCount == 0) {
                    break;
                } else if (c == '(') {
                    braceCount++;
                } else if (c == ')') {
                    braceCount--;
                }
                secondSpace++;
            }
            // 递归
            int e1 = parse(expression, firstSpace + 1, secondSpace, vars);
            int e2 = parse(expression, secondSpace + 1, end, vars);
            return type.equals("add") ? e1 + e2 : e1 * e2;
        } else if (type.charAt(0) >= 'a' && type.charAt(0) <= 'z') {
            // 变量
            return vars.get(type);
        } else {
            // 整数
            return Integer.parseInt(type);
        }
    }


    public static void main(String[] args) {
        D06_Evaluate_736 t = new D06_Evaluate_736();
        System.out.println(t.evaluate("(let x 2 (mult x (let x 3 y 4 (add x y))))"));
    }

}
