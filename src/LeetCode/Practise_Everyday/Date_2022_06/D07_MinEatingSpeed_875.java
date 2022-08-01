package LeetCode.Practise_Everyday.Date_2022_06;

/**
 * @Auther: Lil_boat
 * @Date: 9:44 2022/6/7
 * @Tile: 爱吃香蕉的珂珂
 * @Description: 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
 * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。
 * 如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
 * <p>
 * 链接：https://leetcode.cn/problems/koko-eating-bananas
 */
public class D07_MinEatingSpeed_875 {

    public int minEatingSpeed(int[] piles, int h) {
        // 吃香蕉的最大速度不超过每堆数量的最大值
        int maxVal = 1;
        for (int pile : piles) {
            maxVal = Math.max(maxVal, pile);
        }
        int left = 1;
        int right = maxVal;
        // 二分查找
        while (left < right) {
            int mid = left + right >> 1;
            // 如果吃香蕉的速度大于规定的h 则说明速度慢了
            if (calculateSum(piles, mid) > h) left = mid + 1;
            else right = mid;
        }
        return left;
    }

    /**
     * 计算按照speed的速度吃香蕉所需的时间
     * @param piles
     * @param speed
     * @return
     */
    private int calculateSum(int[] piles, int speed) {
        int sum = 0;
        for (int pile : piles) {
            sum += (pile + speed - 1) / speed;
        }
        return sum;
    }

}
