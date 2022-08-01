package LeetCode.String;

/**
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 */
public class LongestCommonPrefix {

    public static String longestCommonPrefix(String[] strs) {
        if (strs.length == 0 || strs == null) return "";
        // 默认第一个元素是最长匹配前缀
        String pre = strs[0];
        int i = 1;
        while (i < strs.length) {
            // 不断的截取
            /**
             * public int indexOf(String str)返回指定子字符串在此字符串中第一次出现处的索引。返回的整数是
             *  this.startsWith(str, k)
             *  为 true 的最小 k 值。
             *
             * 参数：
             * str - 任意字符串。
             * 返回：
             * 如果字符串参数作为一个子字符串在此对象中出现，则返回第一个这种子字符串的第一个字符的索引；如果它不作为一个子字符串出现，则返回 -1。
             */
            while (strs[i].indexOf(pre) != 0) {
                System.out.println(strs[i].indexOf(pre));
                pre = pre.substring(0, pre.length() - 1);
            }
            i++;
        }
        return pre;
    }

    public static void main(String[] args) {
        String[] strs = {"flower", "flow", "flight"};
        String res = longestCommonPrefix(strs);
        System.out.println(res);
    }

}
