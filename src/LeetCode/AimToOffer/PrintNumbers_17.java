package LeetCode.AimToOffer;

import java.util.Arrays;

public class PrintNumbers_17 {
    /*
    大数打印解法：
        实际上，本题的主要考点是大数越界情况下的打印。需要解决以下三个问题：
            1. 表示大数的变量类型：无论是 short / int / long ... 任意变量类型，数字的取值范围都是有限的。因此，大数的表示应用字符串 String 类型。
            2. 生成数字的字符串集：使用 int 类型时，每轮可通过 +1 生成下个数字，而此方法无法应用至 String 类型。
               并且， String 类型的数字的进位操作效率较低，例如 "9999" 至 "10000" 需要从个位到千位循环判断，进位 4 次。
               观察可知，生成的列表实际上是 n 位 0 - 9 的 全排列 ，因此可避开进位操作，通过递归生成数字的 String 列表。
            3. 递归生成全排列：基于分治算法的思想，先固定高位，向低位递归，当个位已被固定时，添加数字的字符串。
               例如当 n = 2 时（数字范围 1 - 99 ），固定十位为 0 - 9 ，按顺序依次开启递归，固定个位 0 - 9 ，终止递归并添加数字字符串。
         在此方法下，各数字字符串被逗号隔开，共同组成长字符串。返回的数字集字符串如下所示：
            输入：n = 1
            输出："0,1,2,3,4,5,6,7,8,9"

            输入：n = 2
            输出："00,01,02,...,10,11,12,...,97,98,99"

            输入：n = 3
            输出："000,001,002,...,100,101,102,...,997,998,999"
        观察可知，当前的生成方法仍有以下问题：诸如 00, 01, 02, ,⋯ 应显示为 0, 1, 2, ⋯ ，即应 删除高位多余的 0 ;
        此方法从 0 开始生成，而题目要求 列表从 1 开始 ；
        以上两个问题的解决方法如下：
            1. 删除高位多余的 0 ：
                1. 字符串左边界定义： 声明变量 start 规定字符串的左边界，以保证添加的数字字符串 num[start:] 中无高位多余的 0 。
                    例如当 n = 2 时， 1 - 9 时 start = 1 ， 10 - 99 时 start = 0 。
                2. 左边界 start 变化规律： 观察可知，当输出数字的所有位都是 9 时，则下个数字需要向更高位进 1 ，
                    此时左边界 start 需要减 1 （即高位多余的 0 减少一个）。例如当 n = 3 （数字范围 1 - 999 ）时，左边界 start 需要减 1 的情况有：
                    "009" 进位至 "010" ，  。设数字各位中 9 的数量为 nine ，所有位都为 9 的判断条件可用以下公式表示：n - start = nine
                3. 统计 nine 的方法： 固定第 x 位时，当 i = 9 则执行 nine = nine + 1 ，并在回溯前恢复 nine = nine - 1 。
            2. 列表从 1 开始：在以上方法的基础上，添加数字字符串前判断其是否为 "0" ，若为 "0" 则直接跳过。
     */
    private static int[] res;
    private static int nine = 0, count = 0, start, n;
    private static char[] nums, loop = {'0','1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static int[] printNumbers(int num) {
        n = num;
        res = new int[(int) Math.pow(10, num) - 1];
        nums = new char[num];
        start = n - 1;
        dfs(0);
        return res;
    }

    public static void dfs(int x) {
        if (x == n) {
            String s = String.valueOf(nums).substring(start);
            if (!s.equals("0")) res[count++] = Integer.parseInt(s);
            if (n - start == nine) start--;
            return;
        }
        for (char i : loop) {
            if (i == '9') nine++;
            nums[x] = i;
            dfs(x + 1);
        }
        nine--;
    }

    public static void main(String[] args) {
        int[] res = printNumbers(2);
        String s = Arrays.toString(res);
        System.out.println(s);
    }

}
