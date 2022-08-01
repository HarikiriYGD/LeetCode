package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Lil_boat
 * @Date: 15:00 2021/12/2
 * @Description: 给你一个长度为 n 的整数数组 score ，其中 score[i] 是第 i 位运动员在比赛中的得分。所有得分都 互不相同 。
 * <p>
 * 运动员将根据得分 决定名次 ，其中名次第 1 的运动员得分最高，名次第 2 的运动员得分第 2 高，依此类推。运动员的名次决定了他们的获奖情况：
 * <p>
 * 名次第 1 的运动员获金牌 "Gold Medal" 。
 * 名次第 2 的运动员获银牌 "Silver Medal" 。
 * 名次第 3 的运动员获铜牌 "Bronze Medal" 。
 * 从名次第 4 到第 n 的运动员，只能获得他们的名次编号（即，名次第 x 的运动员获得编号 "x"）。
 * 使用长度为 n 的数组 answer 返回获奖，其中 answer[i] 是第 i 位运动员的获奖情况。
 */
public class FindRelativeRanks {

    /**
     * 这种方式时间复杂度是O(n * n)
     *
     * @param score
     * @return
     */
    public static String[] findRelativeRanks(int[] score) {
        // 边界判断条件
        if (score.length == 0 || score == null) return null;
        // 数组长度
        int len = score.length;
        // 排名字符串数组
        String[] s = new String[len];
        // 复制数组并排序
        int[] clone = score.clone();
        Arrays.sort(clone);
        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j < len; j++) {
                if (clone[i] == score[j] && i == len - 1) {
                    s[j] = "Gold Medal";
                    break;
                } else if (clone[i] == score[j] && i == len - 2) {
                    s[j] = "Silver Medal";
                    break;
                } else if (clone[i] == score[j] && i == len - 3) {
                    s[j] = "Bronze Medal";
                    break;
                } else if (clone[i] == score[j]) {
                    // 索引关系
                    s[j] = String.valueOf(len - i);
                }
            }
        }
        return s;
    }


    /**
     * 这种方式的时间复杂度是O(n * logn)
     *
     * @param score
     * @return
     */
    public static String[] findRelativeRanks_Hash(int[] score) {
        String[] ss = new String[]{"Gold Medal", "Silver Medal", "Bronze Medal"};
        int n = score.length;
        String[] ans = new String[n];
        int[] clone = score.clone();
        Arrays.sort(clone);
        Map<Integer, Integer> map = new HashMap<>();
        // 将排序后的分数放入map
        for (int i = n - 1; i >= 0; i--) {
            map.put(clone[i], n - 1 - i);
        }
        for (int i = 0; i < n; i++) {
            int rank = map.get(score[i]);
            ans[i] = rank < 3 ? ss[rank] : String.valueOf(rank + 1);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {10, 3, 8, 9, 4};
        String[] res = findRelativeRanks(nums);
        for (String s : res) {
            System.out.println(s);
        }
    }

}
