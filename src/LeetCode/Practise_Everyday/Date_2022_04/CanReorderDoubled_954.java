package LeetCode.Practise_Everyday.Date_2022_04;

import java.util.*;

/**
 * @Auther: Lil_boat
 * @Date: 10:09 2022/4/1
 * @Description: 给定一个长度为偶数的整数数组 arr，
 * 只有对 arr 进行重组后可以满足 “对于每个 0 <= i < len(arr) / 2，
 * 都有 arr[2 * i + 1] = 2 * arr[2 * i]” 时，返回 true；否则，返回 false。
 */
public class CanReorderDoubled_954 {

    public static boolean canReorderDoubled(int[] arr) {
        // 统计成倍数的个数
        int cnt = 0;
        // 排序
        Arrays.sort(arr);
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        // 统计0的成对的个数
        if (map.containsKey(0)) {
            if ((map.get(0) & 1) == 1) return false;
            cnt += map.get(0) / 2;
            map.remove(0);
        }
        for (int i : arr) {
            int d = 2 * i;
            // 观察是否有其元素的两倍 且是否存在 且不能是该元素不能是0（因为0之前已经被remove）
            if (map.containsKey(d) && map.get(d) > 0 && map.get(i) > 0) {
                cnt++;
                map.put(d, map.get(d) - 1);
                map.put(i, map.get(i) - 1);
            }
        }
        return cnt * 2 == arr.length;
    }


    public static void main(String[] args) {
        int[] arr = {2, 4, 0, 0, 8, 1};
        System.out.println(canReorderDoubled(arr));
    }

}
