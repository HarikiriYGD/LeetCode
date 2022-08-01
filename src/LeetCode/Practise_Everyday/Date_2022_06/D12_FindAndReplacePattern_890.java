package LeetCode.Practise_Everyday.Date_2022_06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: Lil_boat
 * @Date: 9:54 2022/6/12
 * @Tile: 查找和替换模式
 * @Description: 有一个单词列表 words 和一个模式  pattern，你想知道 words 中的哪些单词与模式匹配。
 * 如果存在字母的排列 p ，使得将模式中的每个字母 x 替换为 p(x) 之后，我们就得到了所需的单词，那么单词与模式是匹配的。
 * （回想一下，字母的排列是从字母到字母的双射：每个字母映射到另一个字母，没有两个字母映射到同一个字母。）
 * 返回 words 中与给定模式匹配的单词列表。
 * 你可以按任何顺序返回答案。
 * <p>
 * 链接：https://leetcode.cn/problems/find-and-replace-pattern
 */
public class D12_FindAndReplacePattern_890 {

    public List<String> findAndReplacePattern(String[] words, String pattern) {
        List<String> ans = new ArrayList<>();
        // 构建映射数组
        int[] map = new int[26], vis = new int[26];
        for (String word : words) {
            Arrays.fill(map, -1);
            Arrays.fill(vis, 0);
            boolean flag = true;
            for (int i = 0; i < word.length() && flag; i++) {
                // 记录word.charAt(i) - 'a 的值
                int a = word.charAt(i) - 'a', b = pattern.charAt(i) - 'a';
                // 如果没有更改过 就去更改map数组
                if (map[a] == -1 && vis[b] == 0){
                    map[a] = b;
                    vis[b] = 1;
                    // 如果更改过 判断是否相同
                }else if (map[a] != b) flag = false;
            }
            if (flag)ans.add(word);
        }
        return ans;
    }

}
