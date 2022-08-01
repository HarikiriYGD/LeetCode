package LeetCode.String;

/**
 * @Auther: Lil_boat
 * @Date: 15:22 2021/12/27
 * @Description: 写一个函数 StrToInt，实现把字符串转换成整数这个功能。不能使用 atoi 或者其他类似的库函数。
 * 首先，该函数会根据需要丢弃无用的开头空格字符，直到寻找到第一个非空格的字符为止。
 * 当我们寻找到的第一个非空字符为正或者负号时，则将该符号与之后面尽可能多的连续数字组合起来，
 * 作为该整数的正负号；假如第一个非空字符是数字，则直接将其与之后连续的数字字符组合起来，形成整数。
 * 该字符串除了有效的整数部分之后也可能会存在多余的字符，这些字符可以被忽略，它们对于函数不应该造成影响。
 * 注意：假如该字符串中的第一个非空格字符不是一个有效整数字符、字符串为空或字符串仅包含空白字符时，则你的函数不需要进行转换。
 * 在任何情况下，若函数不能进行有效的转换时，请返回 0。
 * 说明：
 * 假设我们的环境只能存储 32 位大小的有符号整数，那么其数值范围为 [−231,  231 − 1]。如果数值超过这个范围，请返回  INT_MAX (231 − 1) 或 INT_MIN (−231) 。
 */
public class StrToInt_67 {
    /**
     * 解题思路：
     * 根据题意，有以下四种字符需要考虑：
     * <p>
     * 首部空格： 删除之即可；
     * 符号位： 三种情况，即 '+' , '−' , '无符号' ；新建一个变量保存符号位，返回前判断正负即可；
     * 非数字字符： 遇到首个非数字的字符时，应立即返回；
     * 数字字符：
     * 字符转数字： “此数字的 ASCII 码” 与 “ 0 的 ASCII 码” 相减即可；
     * 数字拼接： 若从左向右遍历数字，设当前位字符为 c ，当前位数字为 x ，数字结果为 res ，则数字拼接公式为：
     * res= 10 × res + x =ascii(c) − ascii(′0′)
     * 数字越界处理：题目要求返回的数值范围应在 [-2^{31}, 2^{31} - 1]，
     * 因此需要考虑数字越界问题。而由于题目指出 环境只能存储 32 位大小的有符号整数 ，
     * 因此判断数字越界时，要始终保持 res 在 int 类型的取值范围内。
     * 在每轮数字拼接前，判断 res在此轮拼接后是否超过 2147483647，若超过则加上符号位直接返回。
     * 设数字拼接边界 bndry = 2147483647 / 10 = 214748364，则以下两种情况越界：
     * <p>
     * res  > bndry 情况一：执行拼接10 × res ≥ 2147483650越界
     * res = bndry, x > 7 情况二：拼接后是2147483648或2147483649越界
     *
     * @param str
     * @return
     */
    public static int strToInt(String str) {
        // 去掉空格之后转换为char数组
        char[] chars = str.trim().toCharArray();
        if (chars.length == 0) return 0;
        int ans = 0, bound = Integer.MAX_VALUE / 10;
        int i = 1, sign = -1;
        if (chars[0] == '-') sign = -1;
        else if (chars[0] != '+') i = 0;
        for (int j = i; j < chars.length; j++) {
            if (chars[j] < '0' || chars[j] > '9') break;
            if (ans > bound || ans == bound && chars[j] > '7') return sign == 1 ? Integer.MIN_VALUE : Integer.MIN_VALUE;
            ans = ans * 10 + (chars[j] - '0');
        }
        return sign * ans;
    }

    public static void main(String[] args) {
        String s = "-5464564444455232141";
        System.out.println(strToInt(s));
    }
}
