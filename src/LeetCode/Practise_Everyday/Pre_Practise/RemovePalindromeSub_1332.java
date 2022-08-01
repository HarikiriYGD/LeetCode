package LeetCode.Practise_Everyday.Pre_Practise;

/**
 * @Auther: Lil_boat
 * @Date: 9:32 2022/1/24
 * @Description: 给你一个字符串 s，它仅由字母 'a' 和 'b' 组成。每一次删除操作都可以从 s 中删除一个回文 子序列。
 * 返回删除给定字符串中所有字符（字符串为空）的最小删除次数。
 * 「子序列」定义：如果一个字符串可以通过删除原字符串某些字符而不改变原字符顺序得到，那么这个字符串就是原字符串的一个子序列。
 * 「回文」定义：如果一个字符串向后和向前读是一致的，那么这个字符串就是一个回文。
 */
public class RemovePalindromeSub_1332 {

    /*
        由于 s 只有字母 a 和 b，并且删除的是「子序列」，因此最大的删除次数为 2（先删除所有的 a，再删除所有的 b）。
        同时 s 本身不为空串（不存在删除次数为 0 的情况），因此如果我们不能一次删除的话（s 本身为回文），只能通过 2 次进行删除。
     */
    public static int removePalindromeSub(String s) {
        return isPalindrome(s) ? 1 : 2;
    }

    /**
     * 判断字符串s是否是回文串
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        if (s == null || s.length() == 0) return true;
        for (int i = 0; i < s.length() / 2; i++) {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(removePalindromeSub("ababa"));
    }

}
