package LeetCode.Practise_Everyday.Pre_Practise;

/**
 * @Auther: Lil_boat
 * @Date: 10:20 2022/1/27
 * @Description: 句子仅由小写字母（'a' 到 'z'）、数字（'0' 到 '9'）、连字符（'-'）、标点符号（'!'、'.' 和 ','）以及空格（' '）组成。
 * 每个句子可以根据空格分解成 一个或者多个 token ，这些 token 之间由一个或者多个空格 ' ' 分隔。
 * <p>
 * 如果一个 token 同时满足下述条件，则认为这个 token 是一个有效单词：
 * 仅由小写字母、连字符和/或标点（不含数字）。
 * 至多一个 连字符 '-' 。如果存在，连字符两侧应当都存在小写字母（"a-b" 是一个有效单词，但 "-ab" 和 "ab-" 不是有效单词）。
 * 至多一个 标点符号。如果存在，标点符号应当位于 token 的 末尾 。
 * 这里给出几个有效单词的例子："a-b."、"afad"、"ba-c"、"a!" 和 "!" 。
 * 给你一个字符串 sentence ，请你找出并返回 sentence 中 有效单词的数目 。
 */
public class CountValidWords_2047 {

    public static int countValidWords(String sentence) {
        int cnt = 0;
        String[] words = sentence.split(" ");
        for (String word : words) {
            if (check(word)) cnt++;
        }
        return cnt;
    }

    public static boolean check(String word) {
        int n = word.length();
        if (n == 0) return false;
        for (int i = 0, c1 = 0, c2 = 0; i < n; i++) {
            char c = word.charAt(i);
            if (Character.isDigit(c)) return false;
            if (c == ' ') return false;
            // c1用于统计 - 出现的次数
            // 如果多于一次直接返回false
            if (c == '-' && ++c1 >= 0) {
                // 如果 - 出现的位置在第一个或者是最后一个 直接返回false
                if (c1 > 1 || (i == 0 || i == n - 1)) return false;
                if (!Character.isLetter(word.charAt(i + 1)) || !Character.isLetter(word.charAt(i - 1))) return false;
            }
            // c2用于统计标点符号出现的次数
            // 如果多于一次直接返回false
            if (c == '!' || c == '.' || c == ',' && ++c2 >= 0) {
                if (c2 > 1 || (i != n - 1)) return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(countValidWords("he bought 2 pencils, 3 erasers, and 1  pencil-sharpener."));
    }
}
