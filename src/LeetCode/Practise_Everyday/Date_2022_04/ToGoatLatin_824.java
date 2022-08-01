package LeetCode.Practise_Everyday.Date_2022_04;

import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 9:41 2022/4/21
 * @Tile: 山羊拉丁文
 * @Description: 给你一个由若干单词组成的句子 sentence ，单词间由空格分隔。每个单词仅由大写和小写英文字母组成。
 * 请你将句子转换为 “山羊拉丁文（Goat Latin）”（一种类似于 猪拉丁文 - Pig Latin 的虚构语言）。山羊拉丁文的规则如下：
 * 如果单词以元音开头（'a', 'e', 'i', 'o', 'u'），在单词后添加"ma"。
 * 例如，单词 "apple" 变为 "applema" 。
 * <p>
 * 如果单词以辅音字母开头（即，非元音字母），移除第一个字符并将它放到末尾，之后再添加"ma"。
 * 例如，单词 "goat" 变为 "oatgma" 。
 * <p>
 * 根据单词在句子中的索引，在单词最后添加与索引相同数量的字母'a'，索引从 1 开始。
 * 例如，在第一个单词后添加 "a" ，在第二个单词后添加 "aa" ，以此类推。
 * <p>
 * 返回将 sentence 转换为山羊拉丁文后的句子。
 */
public class ToGoatLatin_824 {

    public static String toGoatLatin(String sentence) {
        // 以空格分隔
        String[] s = sentence.split(" ");
        int n = s.length;
        for (int i = 0; i < n; i++) {
            // 统一成小写字母
            char c = s[i].toLowerCase().charAt(0);
            // 如果是元音字母开始
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') {
                s[i] = s[i] + "ma";
                for (int j = 0; j <= i; j++) {
                    s[i] += "a";
                }
                // 不是以元音字母开始
            } else {
                int length = s[i].length();
                s[i] = s[i].substring(1, length) + s[i].substring(0, 1) + "ma";
                for (int j = 0; j <= i; j++) {
                    s[i] += "a";
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length; i++) {
            if (i == s.length - 1) sb.append(s[i]);
            else sb.append(s[i] + " ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String sentence = "Each word consists of lowercase and uppercase letters only";
        System.out.println(toGoatLatin(sentence));
    }

}
