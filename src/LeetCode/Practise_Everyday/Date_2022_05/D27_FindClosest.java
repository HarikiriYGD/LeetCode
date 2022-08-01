package LeetCode.Practise_Everyday.Date_2022_05;

/**
 * @Auther: Lil_boat
 * @Date: 9:57 2022/5/27
 * @Tile: 面试题 17.11. 单词距离
 * @Description: 有个内含单词的超大文本文件，给定任意两个不同的单词，
 * 找出在这个文件中这两个单词的最短距离(相隔单词数)。
 * 如果寻找过程在这个文件中会重复多次，而每次寻找的单词不同，你能对此优化吗?
 * <p>
 * 链接：https://leetcode.cn/problems/find-closest-lcci
 */
public class D27_FindClosest {

    public int findCloest(String[] words, String word1, String word2) {
        int n = words.length, ans = Integer.MAX_VALUE;
        for (int i = 0, p = -1, q = -1; i < n; i++) {
            String t = words[i];
            // 记录word1的索引位置
            if (t.equals(word1)) p = i;
            // 记录Word2的索引位置
            if (t.equals(word2)) q = i;
            // 两个单词都出现时
            if (p != -1 && q != -1) {
                // 如果距离为1时，则说明是最短距离了，则不需遍历
                if (ans == 1) return ans;
                ans = Math.min(ans, Math.abs(p - q));
            }
        }
        return ans;
    }

}
