package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 15:57 2021/12/20
 * @Description: 冬季已经来临。 你的任务是设计一个有固定加热半径的供暖器向所有房屋供暖。
 * 在加热器的加热半径范围内的每个房屋都可以获得供暖。
 * 现在，给出位于一条水平线上的房屋 houses 和供暖器 heaters 的位置，请你找出并返回可以覆盖所有房屋的最小加热半径。
 * 说明：所有供暖器都遵循你的半径标准，加热的半径也一样。
 */
public class FindRadiu_475 {
    /**
     * 房屋左右侧的热水器，取距离小的那个，最终取的是所有房屋所需最大的那个半径。
     *
     * @param houses
     * @param heaters
     * @return
     */
    public static int findRadius(int[] houses, int[] heaters) {
        // 先进行升序排序
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int radius = 0;
        int i = 0;
        for (int house : houses) {
            while (i < heaters.length && heaters[i] < house) {
                // 一直找到处于房屋右侧的热水器
                i++;
            }
            if (i == 0)
                radius = Math.max(radius, heaters[i] - house);
            else if (i == heaters.length)
                // 最后一个热水器
                return Math.max(radius, houses[houses.length - 1] - heaters[heaters.length - 1]);
            else
                // 房屋右侧的热水器和房屋左侧的热水器，取小的那个
                radius = Math.max(radius, Math.min(heaters[i] - house, house - heaters[i - 1]));
        }
        return radius;
    }

    /**
     * 为了使供暖器可以覆盖所有房屋且供暖器的加热半径最小，
     * 对于每个房屋，应该选择离该房屋最近的供暖器覆盖该房屋，
     * 最近的供暖器和房屋的距离即为该房屋需要的供暖器的最小加热半径。
     * 所有房屋需要的供暖器的最小加热半径中的最大值即为可以覆盖所有房屋的最小加热半径。
     *
     * 为了得到距离每个房屋最近的供暖器，可以将供暖器数组heaters 排序，然后通过二分查找得到距离最近的供暖器。
     *
     * 具体而言，对于每个房屋house，需要在有序数组 heaters 中找到最大的下标 i，使得 heaters[i]≤house，
     * 特别地，当 heaters[0]>house 时，i=−1。
     * 在得到下标 i之后，令 j=i+1，则 j是满足 heaters[j]>house 的最小下标。
     * 特别地，当 heaters[n−1]≤house 时，j = n，其中 n 是数组heaters 的长度。
     *
     * 得到下标 i和 j之后，离房屋 house 最近的供暖器为 heaters[i] 或 heaters[j]，分别计算这两个供暖器和房屋 house 的距离，
     * 其中的最小值即为该房屋需要的供暖器的最小加热半径。对于 i = -1 或 j = n的下标越界情况，只要将对应的距离设为 +∞ 即可。
     *
     * @param houses
     * @param heaters
     * @return
     */
    public static int findRadius_Sort_Binary(int[] houses, int[] heaters) {
        int ans = 0;
        Arrays.sort(houses);
        for (int house : houses) {
            // 遍历每一个房子
            // 寻找heaters中最大的下标 i， 使得heaters[i] <= house
            int i = binarySearch(heaters, house);
            int j = i + 1;
            int leftDistance = i < 0 ? Integer.MAX_VALUE : house - heaters[i];
            int rightDistance = j >= heaters.length ? Integer.MAX_VALUE : heaters[j] - house;
            int curDistance = Math.min(leftDistance, rightDistance);
            ans = Math.max(ans, curDistance);
        }
        return ans;
    }

    /***
     * 二分查找
     *
     * @param heaters
     * @param target
     * @return
     */
    public static int binarySearch(int[] heaters, int target) {
        int left = 0, right = heaters.length - 1;
        if (heaters[left] > target) {
            return -1;
        }
        while (left < right) {
            int mid = (right - left + 1) / 2 + left;
            if (heaters[mid] > target) right = mid - 1;
            else left = mid;
        }
        return left;


    }

    public static void main(String[] args) {
        int[] houses = {1,2,3};
        int[] heaters = {2};
        System.out.println(findRadius(houses, heaters));
        System.out.println(findRadius_Sort_Binary(houses, heaters));
    }
}
