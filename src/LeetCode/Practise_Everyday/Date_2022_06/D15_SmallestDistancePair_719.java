package LeetCode.Practise_Everyday.Date_2022_06;

import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 10:22 2022/6/15
 * @Tile:
 * @Description:
 */
public class D15_SmallestDistancePair_719 {
            /*
            先将数组 nums 从小到大进行排序。因为第 k 小的数对距离必然在区间 [0, max(nums) − min(nums)] 内，
            令 left = 0，right = max(nums) − min(nums)，我们在区间 [left,right] 上进行二分。

            对于当前搜索的距离 mid，计算所有距离小于等于mid 的数对数目 cnt，如果cnt ≥ k，那么right = mid − 1，
            否则 left = mid + 1。当 left > right 时，终止搜索，那么第 kk 小的数对距离为 left。

            给定距离 mid，计算所有距离小于等于 mid 的数对数目 cnt 可以使用二分查找：枚举所有数对的右端点 j，
            二分查找大于等于 nums[j] − mid 的最小值的下标 i，那么右端点为 j 且距离小于等于 mid 的数对数目为 j - i，计算这些数目之和。
             */
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        // left为最小的数对距离， right为最大的数对距离
        int n = nums.length, left = 0, right = nums[n - 1] - nums[0];
        while (left <= right) {
            int mid = left + right >> 1;
            int cnt = 0;
            for (int i = 0,j = 0; j < n; j++) {
                while (nums[j] - nums[i] > mid) {
                    i++;
                }
                cnt += j - i;
            }
            if (cnt >= k)right = mid - 1;
            else left = mid + 1;
        }
        return left;
    }

    public int binarySearch(int[] nums, int end, int target) {
        int left = 0, right = end;
        while (left < right) {
            int mid = (left + right) / 2;
            if (nums[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

}
