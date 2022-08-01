package LeetCode.Practise_Everyday.Date_2022_06;


/**
 * @Auther: Lil_boat
 * @Date: 9:13 2022/6/27
 * @Tile: 最长特殊序列 II
 * @Description: 给定字符串列表 strs ，返回其中 最长的特殊序列 。如果最长特殊序列不存在，返回 -1 。
 * 特殊序列 定义如下：该序列为某字符串 独有的子序列（即不能是其他字符串的子序列）。
 *  s 的 子序列可以通过删去字符串 s 中的某些字符实现。
 * 例如，"abc" 是 "aebdc" 的子序列，因为您可以删除"aebdc"中的下划线字符来得到 "abc" 。"aebdc"的子序列还包括"aebdc"、 "aeb" 和 "" (空字符串)。
 * <p>
 * 链接：https://leetcode.cn/problems/longest-uncommon-subsequence-ii
 */
public class D27_FindLUSlength_522 {

    public int findLUSlength(String[] strs) {
        int n = strs.length, ans = -1;
        for (int i = 0; i < n; i++) {
            boolean check = true;
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                // 判断strs[i]是否是strs[j]的子序列
                if (isSubSequence(strs[i], strs[j])) {
                    check = false;
                    break;
                }
            }
            // 根据标志位，判断是否将ans进行更新
            if (check) ans = Math.max(ans, strs[i].length());
        }
        return ans;
    }

    private boolean isSubSequence(String str1, String str2) {
        int n = str1.length(), m = str2.length();
        if (n > m) return false;
        int i = 0, j = 0;
        while (i < n && j < m) {
            if (str1.charAt(i) == str2.charAt(j)) {
                i++;
                j++;
            } else {
                j++;
            }
        }
        return i == str1.length();
    }

}
