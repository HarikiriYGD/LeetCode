package LeetCode.Practise_Everyday.Date_2022_08;

import java.util.*;

/**
 * @Author: Lil_boat
 * @Date: 2022/8/6 20:56
 * @Title: 数组中的字符串匹配
 * @Description: 给你一个字符串数组 words ，数组中的每个字符串都可以看作是一个单词。请你按 任意 顺序返回 words 中是其他单词的子字符串的所有单词。
 *
 * 如果你可以删除 words[j] 最左侧和/或最右侧的若干字符得到 word[i] ，那么字符串 words[i] 就是 words[j] 的一个子字符串。
 *
 * 链接：https://leetcode.cn/problems/string-matching-in-an-array。
 */
public class D06_StringMatching_1408 {

    public List<String> stringMatching(String[] words) {
        if(words.length == 1){
            return new ArrayList<>();
        }
        List<String> ans = new ArrayList<>();
        Set<String> set = new HashSet<>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length ; j++) {
                if (i == j || words[i].length() < words[j].length()){
                    continue;
                }
                if (words[i].contains(words[j])){
                    set.add(words[j]);
                }
            }
        }
        for (String s : set) {
            ans.add(s);
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] words = {"mass","as","hero","superhero"};
        D06_StringMatching_1408 t = new D06_StringMatching_1408();
        t.stringMatching(words);
    }
}
