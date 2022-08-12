package LeetCode.Practise_Everyday.Date_2022_08;

import java.util.*;


/**
 * @Author: Lil_boat
 * @Date: 2022/8/12 22:14
 * @Title: 用户分组
 * @Description: 有 n 个人被分成数量未知的组。每个人都被标记为一个从 0 到 n - 1 的唯一ID 。
 * 给定一个整数数组 groupSizes ，其中 groupSizes[i] 是第 i 个人所在的组的大小。例如，如果 groupSizes[1] = 3 ，则第 1 个人必须位于大小为 3 的组中。
 * 返回一个组列表，使每个人 i 都在一个大小为 groupSizes[i] 的组中。
 * 每个人应该 恰好只 出现在 一个组 中，并且每个人必须在一个组中。如果有多个答案，返回其中 任何 一个。可以 保证 给定输入 至少有一个 有效的解。
 * <p>
 * 链接：https://leetcode.cn/problems/group-the-people-given-the-group-size-they-belong-to
 */
public class D12_GroupThePeople_1282 {

    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        List<List<Integer>> ans = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        // 将所有分组大小的数字归到一组
        for (int i = 0; i < groupSizes.length; i++) {
            List<Integer> list = map.computeIfAbsent(groupSizes[i], l -> new ArrayList<>());
            list.add(i);
        }
        for (Map.Entry<Integer, List<Integer>> entry : map.entrySet()) {
            int groupLength = entry.getKey();
            List<Integer> mem = entry.getValue();
            // 一共有多少个组
            int count = mem.size() / groupLength;
            for (int i = 0, idx = 0; i < count; i++){
                List<Integer> subList = new ArrayList<>();
                for (int j = 0; j < groupLength; j++) {
                    subList.add(mem.get(idx));
                    idx++;
                }
                ans.add(subList);
            }
        }
        return ans;
    }

}
