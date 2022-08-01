package LeetCode.QueueAndStack;

import java.util.Stack;

public class DecodeString_394 {

    /**
     * 通过栈的数据结构实现对字符串的解码
     *
     * @param s 所需解码的字符串
     * @return 解码的字符串
     */
    public static String decodeString(String s) {

        // 初始化数据结构
        Stack<StringBuilder> str = new Stack<>();
        // 存储字符串中的数字
        Stack<Integer> nums = new Stack<>();
        // 最后拼接的结果
        StringBuilder res = new StringBuilder();
        char[] chars = s.toCharArray(); // 转换为字符数组
        // 将数字率先置为0
        int num = 0;
        for (char c : chars) {
            if (c == '[') {
                str.push(res);
                nums.push(num);
                /**
                 * 如果不刷新num和res会影响后面的结果
                 */
                num = 0;
                res = new StringBuilder();
            } else if (c == ']') { // 如果是右括号，通过数字的大小，将括号内的字符串不断拼接到res中
                StringBuilder tmp = new StringBuilder();
                for (int i = nums.pop(); i > 0; i--) {
                    tmp.append(res);
                }
                res = str.pop().append(tmp);
            } else if (c >= '0' && c <= '9') {
                // 将字符转换为数字
                num = num * 10 + (c - '0');
            } else {
                // 如果都不是上面的情况 直接将字符串拼接到res中
                res.append(c);
            }
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String s = "3[a2[c]]";
        String res = decodeString(s);
        System.out.println(res);
    }

}
