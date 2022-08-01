package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: Lil_boat
 * @Date: 11:29 2021/12/27
 * @Description: 给出第一个词 first 和第二个词 second，考虑在某些文本 text 中可能以 "first second third" 形式出现的情况，
 * 其中 second 紧随 first 出现，third 紧随 second 出现。
 * 对于每种这样的情况，将第三个词 "third" 添加到答案中，并返回答案。
 */
public class FindOcurrences_1078 {

    public static String[] findOcurrences(String text, String first, String second) {
        // 建立结果队列
        List<String> list = new ArrayList<>();
        // 以空格分开
        String[] s = text.split(" ");
        for (int i = 0; i < s.length - 2; i++) {
            if (s[i].equals(first) &&  second.equals(s[i+1])) list.add(s[i + 2]);

        }
        return list.toArray(new String[0]);
    }

    public static void main(String[] args) {
        String text = "we will we will rock you";
        String first = "we";
        String second = "will";
        System.out.println(Arrays.toString(findOcurrences(text, first, second)));
    }
}
