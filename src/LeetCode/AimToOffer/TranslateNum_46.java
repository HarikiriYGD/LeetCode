package LeetCode.AimToOffer;

/**
 * @Auther: Lil_boat
 * @Date: 17:11 2021/12/28
 * @Description: 给定一个数字，我们按照如下规则把它翻译为字符串：
 * 0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 * 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 */
public class TranslateNum_46 {

    /*
    方法一：字符串遍历
        为方便获取数字的各位 x_i，考虑先将数字 num 转化为字符串 s ，通过遍历 s 实现动态规划。
        通过字符串切片 s[i - 2:i] 获取数字组合 10 x_{i-1} + x_i，通过对比字符串 ASCII 码判断字符串对应的数字区间。
        空间使用优化： 由于 dp[i] 只与 dp[i - 1] 有关，因此可使用两个变量 a, b 分别记录 dp[i] , dp[i - 1] ，两变量交替前进即可。
        此方法可省去 dp 列表使用的 O(N) 的额外空间。
     复杂度分析：
        时间复杂度 O(N) ： N 为字符串 s 的长度（即数字 num 的位数 log(num)，其决定了循环次数。
         空间复杂度 O(N) ： 字符串 s 使用 O(N) 大小的额外空间。
     */
    public static int translateNum_String(int num) {
        String s = String.valueOf(num);
        int a = 1, b = 1;
        for (int i = 2; i <= s.length(); i++) {
            String tmp = s.substring(i - 2, i);
            int c = tmp.compareTo("10") >= 0 && tmp.compareTo("25") <= 0 ? a + b : a;
            b = a;
            a = c;
        }
        return a;
    }

    /*
    方法二：数字求余
        上述方法虽然已经节省了 dp 列表的空间占用，但字符串 s 仍使用了 O(N) 大小的额外空间。
        空间复杂度优化：
            利用求余运算 num % 10 和求整运算 num / 10 ，可获取数字 num 的各位数字（获取顺序为个位、十位、百位…）。
            运用 求余 和 求整 运算实现，可实现 从右向左 的动态规划计算。而根据上述动态规划 “对称性” ，可知从右向左计算是正确的。
            自此，字符串 s 的空间占用也被省去，空间复杂度从 O(N) 降至 O(1) 。
        复杂度分析：
            时间复杂度 O(N) ： N 为字符串 s 的长度，即数字 num 的位数 log(num) ，其决定了循环次数。
            空间复杂度 O(1) ： 几个变量使用常数大小的额外空间。
     */
    public static int translateNum_MOD(int num) {
        int a = 1, b = 1, x, y = num % 10;
        while (num > 9) {
            num /= 10;
            x = num % 10;
            int tmp = x * 10 + y;
            int c = (tmp >= 0 && tmp <= 25) ? a + b : a;
            b = a;
            a = c;
            y = x;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println(translateNum_String(12258));
        System.out.println(translateNum_MOD(12258));
    }
}
