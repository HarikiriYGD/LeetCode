package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Lil_boat
 * @Date: 9:34 2021/12/30
 * @Description: Alice 手中有一把牌，她想要重新排列这些牌，分成若干组，使每一组的牌数都是 groupSize ，并且由 groupSize 张连续的牌组成。
 * <p>
 * 给你一个整数数组 hand 其中 hand[i] 是写在第 i 张牌，和一个整数 groupSize 。如果她可能重新排列这些牌，返回 true ；否则，返回 false 。
 */
public class IsNStraightHand_846 {
    public static boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if (n == 0 || groupSize == 0 || n % groupSize != 0) return false;
        Arrays.sort(hand);
        // 被分成了多少个牌组
        int cnt = (n + 1) / groupSize;
        Map<Integer, Integer> map = new HashMap<>();
        // 统计hand数组中每个数字有多少个
        for (int num : hand) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
//        for (int num : hand) {
//            // 判断当前数值的value是否大于0
//            if (map.get(num) > 0) {
//                // 继续判断num连续的数字是否有足够的数量 num; num + 1; num + 2;....
//                for (int i = 0; i < groupSize; i++) {
//                    if (map.getOrDefault(num + i, 0) > 0) map.put(num + i, map.get(num + i) - 1);
//                    else return false;
//                }
//            }
//        }
        for (int num : hand) {
            // 是否包含num这个key
            if (!map.containsKey(num)) continue;
            for (int i = 0; i < groupSize; i++) {
                // 如果连续的不存在 直接返回false
                if (!map.containsKey(num + i)) return false;
                // 如果存在 数量 - 1
                map.put(num + i, map.get(num + i) - 1);
                // 如果value为0 则将其删除
                if (map.get(num+i) == 0) map.remove(num+i);
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] hand = {1, 2, 3, 6, 2, 3, 4, 7, 8};
        int groupSize = 3;
        System.out.println(isNStraightHand(hand, groupSize));
    }
}
