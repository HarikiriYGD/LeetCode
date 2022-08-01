package LeetCode.Practise_Everyday.Date_2022_05;

import java.util.*;

/**
 * @Auther: Lil_boat
 * @Date: 9:34 2022/5/7
 * @Tile: 最小基因变化
 * @Description: 基因序列可以表示为一条由 8 个字符组成的字符串，其中每个字符都是 'A'、'C'、'G' 和 'T' 之一。
 * 假设我们需要调查从基因序列 start 变为 end 所发生的基因变化。一次基因变化就意味着这个基因序列中的一个字符发生了变化。
 * 例如，"AACCGGTT" --> "AACCGGTA" 就是一次基因变化。
 * 另有一个基因库 bank 记录了所有有效的基因变化，只有基因库中的基因才是有效的基因序列。
 * 给你两个基因序列 start 和 end ，以及一个基因库 bank ，请你找出并返回能够使 start 变化为 end 所需的最少变化次数。如果无法完成此基因变化，返回 -1 。
 * 注意：起始基因序列 start 默认是有效的，但是它并不一定会出现在基因库中。
 * <p>
 * 链接：https://leetcode.cn/problems/minimum-genetic-mutation
 */
public class D07_MinMutation_433 {
    static char[] items = {'A', 'C', 'T', 'G'};

    public int minMutation(String start, String end, String[] bank) {
        // 存储bank中的元素
        Set<String> set = new HashSet<>();
        for (String s : bank) set.add(s);
        Deque<String> d = new ArrayDeque<>();
        Map<String, Integer> map = new HashMap<>();
        d.addLast(start);
        map.put(start, 0);
        while (!d.isEmpty()) {
            int size = d.size();
            while (size-- > 0) {
                String s = d.pollFirst();
                char[] cs = s.toCharArray();
                int step = map.get(s);
                // 八个字符  每个字符有四种变化
                for (int i = 0; i < 8; i++) {
                    for (char c : items) {
                        if (cs[i] == c) continue;
                        char[] clone = cs.clone();
                        clone[i] = c;
                        String sub = String.valueOf(clone);
                        // 如果不在bank里面 直接continue
                        if (!set.contains(sub)) continue;
                        // 已经存在也直接continue
                        if (map.containsKey(sub)) continue;
                        // 如果已经和end相同 直接返回 step + 1
                        if (sub.equals(end)) return step + 1;
                        // 存入哈希表 步数 + 1
                        map.put(sub, step + 1);
                        d.addLast(sub);
                    }
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
        D07_MinMutation_433 t = new D07_MinMutation_433();
        String start = "AAAAACCC";
        String end = "AACCCCCC";
        String[] bank = {"AAAACCCC", "AAACCCCC", "AACCCCCC"};
        System.out.println(t.minMutation(start, end, bank));
    }
}
