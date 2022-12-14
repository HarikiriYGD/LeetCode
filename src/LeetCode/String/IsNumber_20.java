package LeetCode.String;

import javax.xml.stream.events.Characters;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Lil_boat
 * @Date: 16:26 2021/12/23
 * @Description: 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * <p>
 * 数值（按顺序）可以分成以下几个部分：
 * 1、若干空格
 * 2、一个 小数 或者 整数
 * 3、（可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * 4、若干空格
 * <p>
 * 小数（按顺序）可以分成以下几个部分：
 * 1、（可选）一个符号字符（'+' 或 '-'）
 * 2、下述格式之一：
 * 至少一位数字，后面跟着一个点 '.'
 * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 一个点 '.' ，后面跟着至少一位数字
 * <p>
 * 整数（按顺序）可以分成以下几个部分：
 * 1、（可选）一个符号字符（'+' 或 '-'）
 * 2、至少一位数字
 * <p>
 * 部分数值列举如下：
 * ["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
 * 部分非数值列举如下：
 * ["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
 *  
 */
public class IsNumber_20 {
    /**
     * 解题思路：
     * 本题使用有限状态自动机。根据字符类型和合法数值的特点，先定义状态，再画出状态转移图，最后编写代码即可。
     *
     * 字符类型：
     * 空格 「 」、数字「 0—9 」 、正负号 「 +, - 」 、小数点 「 . 」 、幂符号 「 e, E 」.
     * 状态定义：按照字符串从左到右的顺序，定义以下 9 种状态。
     *
     * 0、开始的空格
     * 1、幂符号前的正负号
     * 2、小数点前的数字
     * 3、小数点、小数点后的数字
     * 4、当小数点前为空格时，小数点、小数点后的数字
     * 5、幂符号
     * 6、幂符号后的正负号
     * 7、幂符号后的数字
     * 8、结尾的空格
     * 结束状态：合法的结束状态有 2, 3, 7, 8 。
     *
     * 初始化：
     *
     * 状态转移表 states ： 设 states[i] ，其中 i 为所处状态， states[i] 使用哈希表存储可转移至的状态。
     * 键值对 (key, value) 含义：输入字符 key ，则从状态 i 转移至状态 value 。
     * 当前状态 p ： 起始状态初始化为 p = 0 。
     * 状态转移循环： 遍历字符串 s 的每个字符 c 。
     *
     * 记录字符类型 t ： 分为四种情况。
     * 当 c 为正负号时，执行 t = 's' ;
     * 当 c 为数字时，执行 t = 'd' ;
     * 当 c 为 e 或 E 时，执行 t = 'e' ;
     * 当 c 为 . 或 空格 时，执行 t = c （即用字符本身表示字符类型）;
     * 否则，执行 t = '?' ，代表为不属于判断范围的非法字符，后续直接返回 false 。
     * 终止条件： 若字符类型 t 不在哈希表 states[p] 中，说明无法转移至下一状态，因此直接返回 false 。
     * 状态转移： 状态 p 转移至 states[p][t] 。
     * 返回值： 跳出循环后，若状态 p ∈ 2,3,7,8 ，说明结尾合法，返回 true，否则返回 false。
     *
     * @param s
     * @return
     */
    public static boolean isNumber(String s) {
        Map[] states = {
                new HashMap() {{ put(' ', 0); put('s', 1); put('d', 2); put('.', 4); }}, // 0.
                new HashMap() {{  put('d',2); put('.',4); }},                                 // 1.
                new HashMap() {{ put('d', 2); put('.', 3); put('e', 5); put(' ', 8); }}, // 2.
                new HashMap() {{ put('d', 3); put('e', 5); put(' ', 8); }},              // 3.
                new HashMap() {{ put('d', 3); }},                                        // 4.
                new HashMap() {{ put('s', 6); put('d', 7); }},                           // 5.
                new HashMap() {{ put('d', 7); }},                                        // 6.
                new HashMap() {{ put('d', 7); put(' ', 8); }},                           // 7.
                new HashMap() {{ put(' ', 8); }}                                         // 8.

        };
        int p = 0;
        char t;
        for (char c : s.toCharArray()) {
            if (c>='0'&&c<='9') t='d';
            else if (c == '+' || c == '-') t = 's';
            else if (c == 'e' || c == 'E') t = 'e';
            else if (c == '.' || c== ' ') t = c;
            else t = '?';
            //
            if (!states[p].containsKey(t))return false;
            p = (int) states[p].get(t);
        }
        return p==2 || p==3 || p==7 || p==8;
    }

    public static void main(String[] args) {
        String s = "-5+";
        System.out.println(isNumber(s));
    }
}
