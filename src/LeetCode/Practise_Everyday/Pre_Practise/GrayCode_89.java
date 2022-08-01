package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Lil_boat
 * @Date: 13:56 2022/1/8
 * @Description: n 位格雷码序列 是一个由 2^n 个整数组成的序列，其中：
 * 每个整数都在范围 [0, 2^n - 1] 内（含 0 和 2^n - 1）
 * *    第一个整数是 0
 * *    一个整数在序列中出现 不超过一次
 * *    每对 相邻 整数的二进制表示 恰好一位不同 ，且
 * *    第一个 和 最后一个 整数的二进制表示 恰好一位不同
 * 给你一个整数 n ，返回任一有效的 n 位格雷码序列 。
 */
public class GrayCode_89 {

    /*
        关键是搞清楚格雷编码的生成过程, G(i) = i ^ (i/2);
        如 n = 3:
        G(0) = 000,
        G(1) = 1 ^ 0 = 001 ^ 000 = 001
        G(2) = 2 ^ 1 = 010 ^ 001 = 011
        G(3) = 3 ^ 1 = 011 ^ 001 = 010
        G(4) = 4 ^ 2 = 100 ^ 010 = 110
        G(5) = 5 ^ 2 = 101 ^ 010 = 111
        G(6) = 6 ^ 3 = 110 ^ 011 = 101
        G(7) = 7 ^ 3 = 111 ^ 011 = 100
     */
    public static List<Integer> grayCode(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < 1 << n; i++) {
            ans.add(i ^ i >>1);
        }
        return ans;
    }

    /**
     * 这种方法利用循环的思想 生成n位的格雷码之后，再将每个结果计算出添入进ans列表
     * 效率比较低
     * @param n
     * @return
     */
    public static List<Integer> grayCode_Mine(int n) {
        List<Integer> ans = new ArrayList<>();
        StringBuilder[] recursion = recursion(n);
        for (StringBuilder s : recursion) {
            int sum = 0;
            for (int j = 0; j < s.length(); j++) {
                sum += (int) ((s.charAt(j) - '0') * Math.pow(2, s.length() - 1 - j));
            }
            ans.add(sum);
        }
        return ans;
    }

    public static StringBuilder[] recursion(int n) {
        int grayNum = (int) Math.pow(2, n);
        StringBuilder[] grayCode = new StringBuilder[grayNum];
        if (n == 1) {
            grayCode[0] = new StringBuilder("0");
            grayCode[1] = new StringBuilder("1");
        } else {
            StringBuilder[] tmp = recursion(n - 1);
            for (int i = 0; i < grayNum; i++) {
                if (i < grayNum / 2) {
                    grayCode[i] = new StringBuilder(tmp[i]);
                    grayCode[i].insert(0, "0");
                } else {
                    grayCode[i] = new StringBuilder(tmp[grayNum - i - 1]);
                    grayCode[i].insert(0, "1");
                }
            }
        }
        return grayCode;
    }

    public static void main(String[] args) {
        List<Integer> list = grayCode_Mine(16);
        grayCode(3);
    }
}
