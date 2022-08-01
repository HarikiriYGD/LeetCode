package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 10:59 2021/12/8
 * @Description: 给你一个整数数组 nums 和一个整数 k ，找出三个长度为 k 、互不重叠、且 3 * k 项的和最大的子数组，并返回这三个子数组。
 * <p>
 * 以下标的数组形式返回结果，数组中的每一项分别指示每个子数组的起始位置（下标从 0 开始）。如果有多个结果，返回字典序最小的一个。
 */
public class MaxSumOfThreeSubarrays {

    /**
     * 设sum1为大小为 k的窗口的元素和，当窗口从 [i-k+1,i]向右滑动 1个元素后，
     * sum1增加了 nums[i+1]，减少了 nums[i−k+1]。据此我们可以 O(1)地计算出向右滑动 1个元素后的窗口的元素和。
     * 我们从 [0,k-1]开始，不断地向右滑动窗口，直至窗口右端点到达数组末尾时停止。
     * 统计这一过程中的sum1​的最大值（记作maxSum1）及其对应位置。
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSumOfOneSubarrays(int[] nums, int k) {
        int[] ans = new int[1];
        int sum = 0, maxSum = 0;
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            if (i >= k - 1) {
                if (sum > maxSum) {
                    maxSum = sum;
                    ans[0] = i - k + 1;
                }
                sum -= nums[i - k + 1];
            }
        }
        return ans;
    }

    /**
     * 我们使用两个大小为 k的滑动窗口。
     * 设 sum1为第一个滑动窗口的元素和，该滑动窗口从 [0,k-1]开始;
     * sum2为第二个滑动窗口的元素和，该滑动窗口从 [k,2k-1]开始。
     * 我们同时向右滑动这两个窗口，并维护 sum1的最大值maxSum1
     * 及其对应位置。每次滑动时，计算当前maxSum1与 sum2之和。统计这一过程中的 maxSum1+sum2
     * 的最大值（记作 maxSum12）及其对应位置。
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSumOfTwoSubarrays(int[] nums, int k) {
        int[] ans = new int[2];
        int sum1 = 0, maxSum1 = 0, maxSumIndex = 0;
        int sum2 = 0, maxSum12 = 0;
        for (int i = k; i < nums.length; i++) {
            sum1 += nums[i - k];
            sum2 += nums[i];
            if (i >= 2 * k - 1) {
                if (sum1 > maxSum1) {
                    maxSum1 = sum1;
                    maxSumIndex = i - 2 * k + 1;
                }
                if (maxSum1 + sum2 > maxSum12) {
                    maxSum12 = maxSum1 + sum2;
                    ans[0] = maxSumIndex;
                    ans[1] = i - k + 1;
                }
                sum1 -= nums[i - 2 * k + 1];
                sum2 -= nums[i - k + 1];
            }
        }
        return ans;
    }

    /**
     * 回到本题，我们使用三个大小为 kk 的滑动窗口。
     * 设sum1为第一个滑动窗口的元素和，该滑动窗口从 [0,k-1]开始;
     * sum2为第二个滑动窗口的元素和，该滑动窗口从 [k,2k-1]开始；
     * sum3为第三个滑动窗口的元素和，该滑动窗口从 [2k,3k-1]开始。
     * 我们同时向右滑动这三个窗口，按照前言二的方法并维护maxSum12及其对应位置。
     * 每次滑动时，计算当前maxSum12与 sum3之和。统计这一过程中的maxSum12+sum3的最大值及其对应位置。
     * <p>
     * 对于题目要求的最小字典序，由于我们是从左向右遍历的，
     * 并且仅当元素和超过最大元素和时才修改最大元素和，从而保证求出来的下标列表是字典序最小的。
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        if (k > nums.length / 3) return null;
        int[] ans = new int[3];
        int sum1 = 0, maxSum1 = 0, maxSumIndex = 0;
        int sum2 = 0, maxSum2 = 0, maxSumIndex1 = 0, maxSumIndex2 = 0;
        int sum3 = 0, maxSum123 = 0;
        for (int i = 2 * k; i < nums.length; i++) {
            sum1 += nums[i - 2 * k];
            sum2 += nums[i - k];
            sum3 += nums[i];
            if (i >= 3 * k - 1) {
                if (sum1 > maxSum1) {
                    maxSum1 = sum1;
                    maxSumIndex = i - 3 * k + 1;
                }
                if (sum2 + maxSum1 > maxSum2) {
                    maxSum2 = sum2 + maxSum1;
                    maxSumIndex1 = maxSumIndex;
                    maxSumIndex2 = i - 2 * k + 1;
                }
                if (maxSum2 + sum3 > maxSum123) {
                    maxSum123 = maxSum2 + sum3;
                    ans[0] = maxSumIndex1;
                    ans[1] = maxSumIndex2;
                    ans[2] = i - k + 1;
                }
                sum1 -= nums[i - 3 * k + 1];
                sum2 -= nums[i - 2 * k + 1];
                sum3 -= nums[i - k + 1];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 1, 2, 6, 7, 5, 1};
        System.out.println(Arrays.toString(maxSumOfOneSubarrays(nums, 2)));
        System.out.println(Arrays.toString(maxSumOfTwoSubarrays(nums, 2)));
        System.out.println(Arrays.toString(maxSumOfThreeSubarrays(nums, 2)));
    }

}
