package LeetCode.Practise_Everyday.Date_2022_03;

import java.util.Arrays;
import java.util.Stack;

/**
 * @Auther: Lil_boat
 * @Date: 9:57 2022/3/3
 * @Description: 给定一个非负整数 num，反复将各个位上的数字相加，直到结果为一位数。返回这个结果。
 */
public class AddDigits_258 {
    public static int addDigits(int num) {
        int ans = 0;
        while (num > 0) {
            int temp = num % 10;
            num /= 10;
            num = temp + num;
            ans = num;
            if (1 <= ans && ans < 10) break;
        }
        String[] words = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
        Arrays.sort(words);
        for (String word : words) {
            System.out.println(word);
        }
        return ans;
        /*
            这题还是道对 同余定理 应用的题目。
            对于任意一个正整数而言，我们最终是要求得 a_0 + a_1 + a_2 + ... + a_{n - 1}对 9 取模的数。
            而利用「同余式相加」性质，等价于每个数分别对 9 取模之和，再集合任意的 10^n 对 9 取模恒为 1，
            可得最终答案为原数对 9 取模，剩下只需要对相加结果为 9 的边界情况进行处理即可。

         */
//        return (num - 1) % 9 + 1;
    }

    public static void main(String[] args) {
        System.out.println(addDigits(100));
    }
}
