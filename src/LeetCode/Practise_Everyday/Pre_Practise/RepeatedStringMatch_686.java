package LeetCode.Practise_Everyday.Pre_Practise;

/**
 * @Auther: Lil_boat
 * @Date: 9:29 2021/12/22
 * @Description: 给定两个字符串 a 和 b，寻找重复叠加字符串 a 的最小次数，
 * 使得字符串 b 成为叠加后的字符串 a 的子串，如果不存在则返回 -1。
 * 注意：字符串 "abc" 重复叠加 0 次是 ""，重复叠加 1 次是 "abc"，重复叠加 2 次是 "abcabc"。
 */
public class RepeatedStringMatch_686 {

    public static int repeatedStringMatch(String a, String b) {
        int aLen = a.length(), bLen = b.length();
        // 判断字符串b是否含有字符串a不含有的字母
        boolean[] set = new boolean[26];
        for (char c : a.toCharArray()) {
            set[c - 'a'] = true;
        }
        for (char c : b.toCharArray()) {
            if (!set[c - 'a']) return -1;
        }

        // 最多重复k+2次
        int k = bLen / aLen;

        StringBuilder sb = new StringBuilder();
        // 重复k次和字符串b的长度相等
        for (int i = 0; i < k; i++) {
            sb.append(a);
        }
        for (int i = 0; i < 3; i++) {
            if (sb.toString().contains(b)) return k + i;
            sb.append(a);

        }
        return -1;
    }

    public static void main(String[] args) {
        String a = "aa";
        String b = "a";
        System.out.println(repeatedStringMatch(a, b));
    }

}
