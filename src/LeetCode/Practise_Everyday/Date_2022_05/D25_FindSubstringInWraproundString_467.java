package LeetCode.Practise_Everyday.Date_2022_05;

/**
 * @Auther: Lil_boat
 * @Date: 10:02 2022/5/25
 * @Tile: 环绕字符串中唯一的子字符串
 * @Description: 把字符串 s 看作是 “abcdefghijklmnopqrstuvwxyz” 的无限环绕字符串，所以 s 看起来是这样的：
 * "...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd...." . 
 * 现在给定另一个字符串 p 。返回 s 中 唯一 的 p 的 非空子串 的数量 。 
 * <p>
 * 链接：https://leetcode.cn/problems/unique-substrings-in-wraparound-string
 */
public class D25_FindSubstringInWraproundString_467 {

    /*
        统计以每个字符作为结尾的最长连续序列(可以覆盖掉重复的短序列的情况), 他们的和即为所求
        例如:abcdbcd, 对于以d结尾的有abcd, bcd, cd和d, 而bcd产生的序列都会被abcd所覆盖
        总和即以a、b、c和d结尾的所有连续最长序列1 + 2 + 3 + 4 = 10
     */
    public int findSubstringInWraproundString(String p) {
        int ans = 0, curMaxLen = 0;
        int n = p.length();
        if (n == 0) return 0;
        // 统计以这个字符为结尾的最长子串
        int[] cnt = new int[26];
        char[] str = p.toCharArray();
        for (int i = 0; i < n; i++) {
            // 如果字符之间相差 1 或者 是 'z' 和 'a'这种情况
            // 最长连续序列 + 1
            if (i > 0 && (str[i] - str[i - 1] == 1 || str[i - 1] - str[i] == 25)) curMaxLen++;
            // 如果不连续，则为1
            else curMaxLen = 1;
            // 更新以当前字符为结尾的最长连续序列的长度
            cnt[str[i] - 'a'] = Math.max(cnt[str[i] - 'a'], curMaxLen);
        }
        for (int x : cnt) {
            ans += x;
        }
        return ans;
    }

}
