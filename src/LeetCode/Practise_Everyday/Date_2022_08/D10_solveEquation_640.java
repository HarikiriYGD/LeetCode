package LeetCode.Practise_Everyday.Date_2022_08;


/**
 * @Author: Lil_boat
 * @Date: 2022/8/10 13:07
 * @Title: 求解方程
 * @Description: 求解一个给定的方程，将x以字符串 "x=#value" 的形式返回。该方程仅包含 '+' ， '-' 操作，变量 x 和其对应系数。
 * 如果方程没有解，请返回 "No solution" 。如果方程有无限解，则返回 “Infinite solutions” 。
 * 题目保证，如果方程中只有一个解，则 'x' 的值是一个整数。
 * <p>
 * 链接：https://leetcode.cn/problems/solve-the-equation
 */
public class D10_solveEquation_640 {
    public String solveEquation(String equation) {
        // 把x移到左边，把其他移到右边
        String[] arr1 = equation.split("=");
        int left = 0;
        int right = 0;

        String[] arr2 = arr1[0].replace("-", "+-").split("\\+");
        String[] arr3 = arr1[1].replace("-", "+-").split("\\+");

        // 等式左边的处理
        for (String s : arr2) {
            if (s.equals("x")) {
                left += 1;
            } else if (s.equals("-x")) {
                left += -1;
            } else if (s.contains("x")) {
                left += Integer.parseInt(s.substring(0, s.length() - 1));
            } else if (!s.equals("")) {
                right -= Integer.parseInt(s);
            }
        }

        // 等式右边的处理
        for (String s : arr3) {
            if (s.equals("x")) {
                left -= 1;
            } else if (s.equals("-x")) {
                left -= -1;
            } else if (s.contains("x")) {
                left -= Integer.parseInt(s.substring(0, s.length() - 1));
            } else if (!s.equals("")) {
                right += Integer.parseInt(s);
            }
        }

        if (left == 0) {
            if (right == 0) {
                return "Infinite solutions";
            } else {
                return "No solution";
            }
        } else {
            return "x=" + right / left;
        }
    }

    public static void main(String[] args) {
        String s = "x+5-3+x=6+x-2";
        D10_solveEquation_640 t = new D10_solveEquation_640();
        System.out.println(t.solveEquation(s));
    }

}
