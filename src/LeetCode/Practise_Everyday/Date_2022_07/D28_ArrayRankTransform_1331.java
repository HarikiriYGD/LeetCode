package LeetCode.Practise_Everyday.Date_2022_07;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Lil_boat
 * @Date: 2022/7/28 13:34
 * @Title: 数组序号转换
 * @Description: 给你一个整数数组 arr ，请你将数组中的每个元素替换为它们排序后的序号。
 * 序号代表了一个元素有多大。序号编号的规则如下：
 * 序号从 1 开始编号。
 * 一个元素越大，那么序号越大。如果两个元素相等，那么它们的序号相同。
 * 每个数字的序号都应该尽可能地小。
 * <p>
 * 链接：https://leetcode.cn/problems/rank-transform-of-an-array
 */
public class D28_ArrayRankTransform_1331 {

    public int[] arrayRankTransform(int[] arr) {
        if (arr.length == 0 || arr == null) {
            return new int[]{};
        }
        int[] clone = arr.clone();
        Arrays.sort(clone);
        Map<Integer, Integer> map = new HashMap<>();
        int idx = 1;
        for (int i = 0; i < clone.length; i++) {
            // 如果存在则直接跳过
            if (map.containsKey(clone[i])) {
                continue;
            }
            map.put(clone[i], idx);
            idx++;
        }
        for (int i = 0; i < arr.length; i++) {
            clone[i] = map.get(arr[i]);
        }
        return clone;

    }

}
