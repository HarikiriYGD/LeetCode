package LeetCode.Practise_Everyday.Date_2022_08;

import java.util.Arrays;

/**
 * @Author: Lil_boat
 * @Date: 2022/8/3 19:40
 * @Title: 有序队列
 * @Description: 给定一个字符串 s 和一个整数 k 。你可以从 s 的前 k 个字母中选择一个，并把它加到字符串的末尾。
 * 返回 在应用上述步骤的任意数量的移动后，字典上最小的字符串 。
 * <p>
 * 链接：https://leetcode.cn/problems/orderly-queue
 */
public class D03_OrderlyQueue_899 {

    /**
     * 如果k == 1那么就轮换一整轮，找到字典序最小的。
     * 如果k > 1那么就能做到完美升序。
     * 原理是，
     * <p>
     * 只要k > 1就能实现「交换相邻两个字母」。
     * 能实现交换相邻两个字母，就能实现冒泡排序。
     *
     * @param s
     * @param k
     * @return
     */
    public String orderlyQueue(String s, int k) {
        if (k == 1) {
            String ans = s;
            StringBuilder sb = new StringBuilder(s);
            int n = s.length();
            for (int i = 1; i < n; i++) {
                char c = sb.charAt(0);
                sb.deleteCharAt(0);
                sb.append(c);
                if (sb.toString().compareTo(ans) < 0){
                    ans = sb.toString();
                }
            }
            return ans;
        } else {
            char[] arr = s.toCharArray();
            Arrays.sort(arr);
            return new String(arr);
        }
    }


    public static void main(String[] args) {
        String s = "cba";
        D03_OrderlyQueue_899 t = new D03_OrderlyQueue_899();
        System.out.println(t.orderlyQueue(s, 1));
    }

}
