package LeetCode.String;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1
 */
public class FirstUniqChar {
    /**
     * 两次遍历的方法
     * 先统计每个字母的次数
     * 寻找出现次数等于1的字母的下标
     *
     * @param s 传入的字符串
     * @return 出现次数等于1的字母所在的下标
     */
    public static int firstUniqChar(String s) {
        // 统计每个字母的个数
        int[] count = new int[26];
        char[] c = s.toCharArray();
        // 统计每个字母出现的次数
        for (int i = 0; i < c.length; i++) {
            count[c[i] - 'a']++;
        }

        for (int i = 0; i < c.length; i++) {
            if (count[c[i] - 'a'] == 1) return i;
        }
        return -1;
    }

    /**
     * 利用hashmap的方法
     *
     * @param s 传入的字符串
     * @return 出现次数等于1的字母所在的下标
     */
    public static int firstUniqChar_HashMap(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        // 先统计每个字符的数量
        for (char c : chars) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }

        for (int i = 0; i < s.length(); i++) {
            if (map.get(chars[i]) == 1) return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        String s = "leetcode";
        int res = firstUniqChar_HashMap(s);
        System.out.println(res);
    }
}
