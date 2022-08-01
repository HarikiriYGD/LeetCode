package LeetCode.String;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
/**
 * @Auther: Lil_boat
 * @Date: 17:52 2022/4/14
 * @Tile: 单词长度的最大乘积
 * @Description: 给定一个字符串数组 words，请计算当两个字符串 words[i] 和 words[j] 不包含相同字符时，它们长度的乘积的最大值。
 * 假设字符串中只包含英语的小写字母。如果没有不包含相同字符的一对字符串，返回 0。
 */
public class MaxProduct_318 {

    public static int maxProduct(String[] words) {
        int n = words.length;
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int mask = 0;
            int wordLen = words[i].length();
            for (int j = 0; j < wordLen; j++) {
                // 对每个字母进行编码
                // 如a --- > 00000001
                // 如b --- > 00000010
                mask |= 1 << (words[i].charAt(j) - 'a');
            }
            if (wordLen > map.getOrDefault(mask, 0)) {
                map.put(mask, wordLen);
            }
        }
        int ans = 0;
        Set<Integer> set = map.keySet();
        for (int m1 : set) {
            // 获取单词的长度
            int len1 = map.get(m1);
            for (int m2 : set) {
                int len2 = map.get(m2);
                // 如果相与结果为0
                // 表示两个字符串没有相同的字符
                if ((m1 & m2) == 0) {
                    ans = Math.max(ans, len1 * len2);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] words = {"abcw","baz","foo","bar","fxyz","abcdef"};
        System.out.println(maxProduct(words));
    }

}
