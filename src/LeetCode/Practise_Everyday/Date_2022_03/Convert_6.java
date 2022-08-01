package LeetCode.Practise_Everyday.Date_2022_03;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Lil_boat
 * @Date: 12:54 2022/3/1
 * @Description: 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * <p>
 * 请你实现这个将字符串进行指定行数变换的函数：
 * string convert(string s, int numRows);
 */
public class Convert_6 {

    public static String convert(String s, int numRows) {
        int n = s.length();
        if (numRows == 1 || n <= numRows) return s;
        List<StringBuilder> list = new ArrayList<>();
        // 构建一个numRows行的结果集
        for (int i = 0; i < numRows; i++) {
            list.add(new StringBuilder());
        }
        // i表示对第几行进行元素的添加 flag表示方向的改变
        int i = 0, flag = -1;
        for (char c : s.toCharArray()) {
            list.get(i).append(c);
            // 如果是在第一行或者是最后一行 意味着方向要开始改变
            if (i == 0 || i == numRows - 1) flag = -flag;
            // 更新当前字符 c 对应的行索引；
            i += flag;
        }
        StringBuilder sb = new StringBuilder();
        for (StringBuilder s1 : list) {
            sb.append(s1);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        String test = "PAHNAPLSIIGYIR";
        String res = convert(s, 3);
        System.out.println(res.equals(test));
    }

}
