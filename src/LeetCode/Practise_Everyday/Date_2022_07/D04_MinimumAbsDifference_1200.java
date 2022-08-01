package LeetCode.Practise_Everyday.Date_2022_07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Lil_boat
 * @Date: 10:20 2022/7/4
 * @Tile: 最小绝对差
 * @Description: 给你个整数数组 arr，其中每个元素都 不相同。
 * 请你找到所有具有最小绝对差的元素对，并且按升序的顺序返回。
 *
 * 来源：https://leetcode.cn/problems/minimum-absolute-difference/
 */
public class D04_MinimumAbsDifference_1200 {

    public List<List<Integer>> minimumAbsDifference(int[] arr){
        List<List<Integer>> ans = new ArrayList<>();
        int min = Integer.MAX_VALUE;
        Arrays.sort(arr);
        // 寻找最小绝对差值
        for (int i = 0; i < arr.length - 1; i++){
            min = Math.min(min, arr[i + 1] - arr[i]);
        }
        for (int i = 0; i < arr.length - 1; i++){
            if (arr[i + 1] - arr[i] == min){
                ans.add(Arrays.asList(arr[i],arr[i + 1]));
            }
        }
        return ans;
    }

}
