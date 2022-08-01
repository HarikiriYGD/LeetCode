package LeetCode.String;

/**
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * <p>
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 */
public class IsAnagram {

    public static boolean isAnagram(String s, String t) {
        // 如果两个字符串长度不等，则肯定返回false
        if (s.length() != t.length()) return false;
        // 统计字符数组
        int[] letterCount = new int[26];
        char[] c1 = s.toCharArray();
        char[] c2 = t.toCharArray();
        // 将出现的字符+1
        for (int i = 0; i < c1.length; i++) {
            letterCount[c1[i] - 'a']++;
        }
        // 遍历另外一个数组，遇见相同的字母则对应-1
        for (int j = 0; j < c2.length; j++) {
            letterCount[c2[j] - 'a']--;
        }
        // 遍历统计数组，如果有不为0的数据，则返回false
        for (int i = 0; i < letterCount.length; i++) {
            if (letterCount[i] != 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = "anagram";
        String t = "nagaram";
        boolean res = isAnagram(s, t);
        System.out.println(res);
    }

}
