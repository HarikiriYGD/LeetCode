package LeetCode.SortAndSearch;

import java.util.Arrays;

public class Merge {
    /**
     * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     * <p>
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     * <p>
     * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
     *
     * @param nums1
     * @param m     数组长度
     * @param nums2
     * @param n     数组长度
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // 构建一个m+n的数组
        int[] res = new int[m + n];
        // nums1的下标
        int i = 0;
        // nums2的下标
        int j = 0;
        // 新数组的索引
        int index = 0;
        while (i < m && j < n) {
            if (nums1[i] <= nums2[j]) {
                res[index++] = nums1[i++];
            } else {
                res[index++] = nums2[j++];
            }
        }
        // 将nums1剩下的元素赋值到res中
        for (; i < m; ) {
            res[index++] = nums1[i++];
        }
        // 将nums2剩下的元素赋值到res中
        for (; j < n; ) {
            res[index++] = nums2[j++];
        }
        for (int k = 0; k < m + n; k++) {
            nums1[k] = res[k];
        }

        // System.out.println(Arrays.toString(nums1));
    }

    /**
     * 直接将nums2数组的元素赋值在nums1的尾部
     *
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge_sort(int[] nums1, int m, int[] nums2, int n) {
        int index = m;
        for (int i = 0; i < n; i++) {
            nums1[index++] = nums2[i];
        }
        Arrays.sort(nums1);
        System.out.println(Arrays.toString(nums1));
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int[] nums2 = {2, 5, 6};
        merge_sort(nums1, 3, nums2, 3);
    }
}
