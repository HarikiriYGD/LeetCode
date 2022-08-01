package LeetCode.String;

/**
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 * <p>
 * 函数 myAtoi(string s) 的算法如下：
 * <p>
 * 读入字符串并丢弃无用的前导空格
 * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。 如果两者都不存在，则假定结果为正。
 * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 * 如果整数数超过 32 位有符号整数范围 [−2^31,  2^31 − 1] ，需要截断这个整数，使其保持在这个范围内。具体来说，小于 −2^31 的整数应该被固定为 −2^31 ，大于 2^31 − 1 的整数应该被固定为 231 − 1 。
 * 返回整数作为最终结果。
 */

public class MyAtoi {

    /**
     * 判断各种符号
     *
     * @param s 所需转换的字符串
     * @return 数字
     */
    public static int myAtoi(String s) {
        // 数组索引
        int index = 0;
        // 字符串长度
        int length = s.length();
        // 如果字符串长度是0 则返回数字0
        if (length == 0) return 0;
        // 最后的结果
        int res = 0;
        // 索引小于字符串长度时且不是字母时
        while (index < length && !Character.isLetter(s.charAt(index)) && s.charAt(index) != '.') {
            while (Character.isDigit(s.charAt(index)) || s.charAt(index) == '+' || s.charAt(index) == '-' || s.charAt(index) == ' ') {
                // 如果是数字的情况
                if (Character.isDigit(s.charAt(index))) {
                    while (index < length && Character.isDigit(s.charAt(index))) {
                        int tmp = s.charAt(index) - '0';
                        if (tmp < 0 || tmp > 9) break;
                        int temp = res;
                        res = res * 10 + tmp;
                        index++;
                        if (res / 10 != temp) {
                            return Integer.MAX_VALUE;
                        }

                    }
                    return res;
                }
                // 是加号的情况
                if (s.charAt(index) == '+') {
                    index++;
                    while (index < length && Character.isDigit(s.charAt(index))) {
                        int tmp = s.charAt(index) - '0';
                        if (tmp < 0 || tmp > 9) break;
                        int temp = res;
                        res = res * 10 + tmp;
                        if (res / 10 != temp) {
                            return Integer.MAX_VALUE;
                        }
                        index++;
                    }
                    return res;
                }
                // 是减号的情况
                if (s.charAt(index) == '-') {
                    index++;
                    while (index < length && Character.isDigit(s.charAt(index))) {
                        int tmp = s.charAt(index) - '0';
                        if (tmp < 0 || tmp > 9) break;
                        int temp = res;
                        res = res * 10 + tmp;

                        if (res / 10 != temp) {
                            return Integer.MIN_VALUE;
                        }
                        index++;
                    }
                    return res * (-1);
                }
                if (s.charAt(index) == ' ' && index + 1 > length - 1) return 0;
                // 以上三种情况什么都不是则索引++
                index++;
            }
        }
        return 0;
    }

    public static int myAtoi_else(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        int index = 0;
        // 先去除空格
        while (index < length && chars[index] == ' ') {
            index++;
        }
        // 极端情况 "  " 和""
        if (index >= length) {
            return 0;
        }
        // 再判断符号
        int sign = 1;
        if (chars[index] == '-' || chars[index] == '+') {
            if (chars[index] == '-') {
                sign = -1;
            }
            index++;
        }
        int result = 0;
        int temp = 0;
        while (index < length) {
            int num = chars[index] - '0';
            if (num > 9 || num < 0) {
                break;
            }
            temp = result;
            result = result * 10 + num;
            // 越界后，数值和期望数值发生变化，取余再除10获取原始值，比对判断
            if (result / 10 != temp) {
                if (sign > 0) {
                    return Integer.MAX_VALUE;
                } else {
                    return Integer.MIN_VALUE;
                }
            }
            index++;
        }
        return result * sign;

    }

    public static void main(String[] args) {
        String s = " ";
        int res = myAtoi(s);
        System.out.println(res);
    }

}
