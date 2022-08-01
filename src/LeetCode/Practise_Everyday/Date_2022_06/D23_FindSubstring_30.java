package LeetCode.Practise_Everyday.Date_2022_06;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Auther: Lil_boat
 * @Date: 10:16 2022/6/23
 * @Tile: 串联所有单词的子串
 * @Description: 给定一个字符串 s 和一些 长度相同 的单词 words 。
 * 找出 s 中恰好可以由 words 中所有单词串联形成的子串的起始位置。
 * 注意子串要与 words 中的单词完全匹配，中间不能有其他字符 ，但不需要考虑 words 中单词串联的顺序。
 * <p>
 * 链接：https://leetcode.cn/problems/substring-with-concatenation-of-all-words
 */
public class D23_FindSubstring_30 {

    public List<Integer> findSubstring(String s, String[] words) {
        if (s.length() < words.length) {
            return new ArrayList<>();
        }
        List<Integer> ans = new ArrayList<>();
        HashMap<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        // 子串的长度
        int len = words.length * words[0].length();
        for (int i = 0; i < s.length() - len + 1; i++) {
            String substring = s.substring(i, i + len);
            HashMap<String, Integer> tmp_map = new HashMap<>();
            for (int j = 0; j < len; j += words[0].length()) {
                String w = substring.substring(j, j + words[0].length());
                tmp_map.put(w, tmp_map.getOrDefault(w, 0) + 1);
            }
            if (map.equals(tmp_map)) {
                ans.add(i);
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        String s = "barfoofoobarthefoobarman";
        String[] words = {"bar", "foo", "the"};
        D23_FindSubstring_30 t = new D23_FindSubstring_30();
        List<Integer> ans = t.findSubstring(s, words);
        ans.forEach(System.out::println);
    }

}
