package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.HashMap;
import java.util.Map;
/**
 * @Auther: Lil_boat
 * @Date: 19:34 2022/1/30
 * @Description: 句子 是一串由空格分隔的单词。每个 单词 仅由小写字母组成。
 * 如果某个单词在其中一个句子中恰好出现一次，在另一个句子中却 没有出现 ，那么这个单词就是 不常见的 。
 * 给你两个 句子 s1 和 s2 ，返回所有 不常用单词 的列表。返回列表中单词可以按 任意顺序 组织。
 */
public class UncommonFromSentences_844 {

    public static String[] uncommonFromSentences(String s1, String s2) {
        int count = 0;
        int index = 0;
        Map<String, Integer> map = new HashMap<>();
        String[] words1 = s1.split(" ");
        String[] words2 = s2.split(" ");
        for (String s : words1) {
            map.put(s, map.getOrDefault(s,0) + 1);
        }
        for (String s : words2) {
            map.put(s, map.getOrDefault(s,0) + 1);
        }
        for (String s : map.keySet()) {
            if (map.get(s) == 1) {
                count++;
            }
        }
        String[] ans = new String[count];
        for (String s : map.keySet()) {
            if (map.get(s) == 1) {
                ans[index++] = s;
            }

        }
        return ans;
    }

    public static void main(String[] args) {
        String s1 = "this apple is sweet";
        String s2 = "this apple is sour";
        String[] strings = uncommonFromSentences(s1, s2);
        for (String string : strings) {
            System.out.println(string);
        }
    }
}
