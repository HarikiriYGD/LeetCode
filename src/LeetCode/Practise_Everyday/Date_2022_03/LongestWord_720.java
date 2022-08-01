package LeetCode.Practise_Everyday.Date_2022_03;

import java.util.Arrays;

public class LongestWord_720 {

    static int N = 100009;
    static int[][] tr = new int[N][26];
    static boolean[] isEnd = new boolean[N];
    static int index = 0;

    public static void add(String s) {
        int p = 0;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            if (tr[p][u] == 0) tr[p][u] = ++index;
            p = tr[p][u];
        }
        isEnd[p] = true;
    }

    public static boolean query(String s) {
        int p = 0;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            p = tr[p][u];
            if (!isEnd[p]) return false;
        }
        return true;
    }

    public static String longestWord(String[] words) {
        Arrays.fill(isEnd, false);
        for (int i = 0; i <= index; i++) Arrays.fill(tr[i], 0);
        index = 0;
        String ans = "";
        for (int i = 0; i < words.length; i++) {
            add(words[i]);
        }
        for (String word : words) {
            int n = word.length(), m = ans.length();
            if (n < m) continue;
            if (n == m && word.compareTo(ans) > 0) continue;
            if (query(word)) ans = word;
        }
        return ans;
    }


    public static void main(String[] args) {
        String[] words = {"a", "banana", "app", "appl", "ap", "apply", "apple"};
        System.out.println(longestWord(words));
    }
}
