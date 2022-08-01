package LeetCode.Practise_Everyday.Date_2022_03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Lil_boat
 * @Date: 11:52 2022/3/14
 * @Description: 假设 Andy 和 Doris 想在晚餐时选择一家餐厅，并且他们都有一个表示最喜爱餐厅的列表，每个餐厅的名字用字符串表示。
 *
 * 你需要帮助他们用最少的索引和找出他们共同喜爱的餐厅。 如果答案不止一个，则输出所有答案并且不考虑顺序。 你可以假设答案总是存在。
 */
public class FindRestaurant_599 {
    public String[] findRestaurant(String[] list1, String[] list2) {
        // 存储list1中每个餐厅的下标
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i);
        }
        List<String> ans = new ArrayList<>();
        // 最小值索引和
        int min = Integer.MAX_VALUE;

        for (int i = 0; i < list2.length; i++) {
            // 如果list2中存在和list1中的餐厅相同
            if (map.containsKey(list2[i])) {
                // 获取list1中的下标
                int j = map.get(list2[i]);
                // 判断是否是最小的下标和
                if (i + j < min) {
                    // 清除ans
                    ans.clear();
                    min = i + j;
                    ans.add(list2[i]);
                    // 如果最小值的索引和相等 则将其加入结果集
                } else if (i + j == min) ans.add(list2[i]);
            }
        }
        return ans.toArray(new String[ans.size()]);
    }
}
