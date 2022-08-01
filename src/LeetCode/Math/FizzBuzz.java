package LeetCode.Math;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Lil_boat
 * @Date: 15:28 2021/12/2
 * @Description: 给你一个整数 n ，找出从 1 到 n 各个整数的 Fizz Buzz 表示，并用字符串数组 answer（下标从 1 开始）返回结果，其中：
 * answer[i] == "FizzBuzz" 如果 i 同时是 3 和 5 的倍数。
 * answer[i] == "Fizz" 如果 i 是 3 的倍数。
 * answer[i] == "Buzz" 如果 i 是 5 的倍数。
 * answer[i] == i 如果上述条件全不满足。
 */
public class FizzBuzz {

    public static List<String> fizzBuzz(int n) {
        List<String> list = new ArrayList<>();
        String[] ss = new String[]{"Fizz", "Buzz", "FizzBuzz"};
        for (int i = 1; i <= n; i++) {
            if (i % 3 == 0 && i % 5 != 0) {
                list.add(ss[0]);
            } else if (i % 5 == 0 && i % 3 != 0) {
                list.add(ss[1]);
            } else if (i % 3 == 0 && i % 5 == 0) {
                list.add(ss[2]);
            } else {
                list.add(String.valueOf(i));
            }
        }
        return list;
    }

    public static void main(String[] args) {
        List<String> list = fizzBuzz(15);
        for (String s : list) {
            System.out.println(s);
        }
    }

}
