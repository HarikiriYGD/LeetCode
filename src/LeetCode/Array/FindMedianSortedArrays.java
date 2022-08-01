package LeetCode.Array;

/**
 * @Auther: Lil_boat
 * @Date: 16:25 2021/12/16
 * @Description: 给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 * 请你找出并返回这两个正序数组的 中位数 。
 * 算法的时间复杂度应该为 O(log (m+n)) 。
 */
public class FindMedianSortedArrays {

    /**
     * 给定两个有序数组，要求找到两个有序数组的中位数，最直观的思路有以下两种：
     * 一、使用归并的方式，合并两个有序数组，得到一个大的有序数组。大的有序数组的中间位置的元素，即为中位数。
     * 二、不需要合并两个有序数组，只要找到中位数的位置即可。由于两个数组的长度已知，
     * 因此中位数对应的两个数组的下标之和也是已知的。维护两个指针，初始时分别指向两个数组的下标 0的位置，
     * 每次将指向较小值的指针后移一位（如果一个指针已经到达数组末尾，则只需要移动另一个数组的指针），直到到达中位数的位置。
     * <p>
     * 假设两个有序数组的长度分别为 m和 n，上述两种思路的复杂度如何？
     * <p>
     * 第一种思路的时间复杂度是 O(m+n)，空间复杂度是 O(m+n)。
     * 第二种思路虽然可以将空间复杂度降到 O(1)，但是时间复杂度仍是O(m+n)。
     * <p>
     * 如何把时间复杂度降低到O(log(m+n))呢？如果对时间复杂度的要求有log，通常都需要用到二分查找，这道题也可以通过二分查找实现。
     * <p>
     * 根据中位数的定义，当 m+n是奇数时，中位数是两个有序数组中的第(m+n)/2 个元素，当 m+n是偶数时，
     * 中位数是两个有序数组中的第 (m+n)/2 个元素和第 (m+n)/2+1 个元素的平均值。因此，这道题可以转化成寻找两个有序数组中的第 k小的数，
     * 其中 k为 (m+n)/2或 (m+n)/2+1。
     * <p>
     * 假设两个有序数组分别是 A和 B。要找到第 k个元素，
     * 我们可以比较 A[k/2−1] 和 B[k/2−1]，其中 /表示整数除法。由于A[k/2−1] 和 B[k/2−1] 的前面分别有 A[0..k/2−2] 和 B[0..k/2−2]，
     * 即 k/2−1 个元素，对于A[k/2−1] 和 B[k/2−1] 中的较小值，最多只会有 (k/2-1)+(k/2-1)≤k−2 元素比它小，那么它就不能是第 k小的数了。
     * <p>
     * 因此我们可以归纳出三种情况：
     * <p>
     * 如果 A[k/2−1] < B[k/2−1]，则比 A[k/2−1]小的数最多只有 A的前 k/2−1 个数和 B的前k/2−1 个数，即比 A[k/2−1] 小的数最多只有 k−2 个，
     * 因此 A[k/2−1] 不可能是第 k个数，A[0] 到 A[k/2−1] 也都不可能是第 k个数，可以全部排除。
     * <p>
     * 如果 A[k/2−1] > B[k/2−1]，则可以排除B[0] 到 B[k/2−1]。
     * <p>
     * 如果A[k/2−1]=B[k/2−1]，则可以归入第一种情况处理。
     *
     * 可以看到，比较 A[k/2−1] 和 B[k/2−1] 之后，可以排除 k/2个不可能是第 k小的数，查找范围缩小了一半。
     * 同时，我们将在排除后的新数组上继续进行二分查找，并且根据我们排除数的个数，减少k的值，这是因为我们排除的数都不大于第 k小的数。
     *
     * 有以下三种情况需要特殊处理：
     *
     * 如果 A[k/2−1] 或者B[k/2−1] 越界，那么我们可以选取对应数组中的最后一个元素。
     * 在这种情况下，我们必须根据排除数的个数减少 k 的值，而不能直接将 k减去 k/2。
     * 如果一个数组为空，说明该数组中的所有元素都被排除，我们可以直接返回另一个数组中第 kk 小的元素。
     * 如果 k=1，我们只要返回两个数组首元素的最小值即可。
     *

     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        // 总长度
        int totalLength = m + n;
        if (totalLength % 2 == 1) {
            int midIndex = totalLength / 2;
            double median = getKElement(nums1, nums2, midIndex + 1);
            return median;
        } else {
            int midIndex1 = totalLength / 2, midIndex2 = totalLength / 2 - 1;
            double median = (getKElement(nums1, nums2, midIndex1 + 1) + getKElement(nums1, nums2, midIndex2 + 1)) / 2.0;
            return median;
        }
    }

    public static double getKElement(int[] nums1, int[] nums2, int k) {
        int m = nums1.length, n = nums2.length;
        int index1 = 0, index2 = 0;
        int kthElement = 0;
        while (true) {
            // 边界情况
            if (index1 == m) return nums2[index2 + k - 1];
            if (index2 == n) return nums1[index1 + k - 1];
            if (k == 1) {
                return Math.min(nums1[index1], nums2[index2]);
            }
            int half = k / 2;
            int newIndex1 = Math.min(index1 + half, m) - 1;
            int newIndex2 = Math.min(index2 + half, n) - 1;
            int pivot1 = nums1[newIndex1], pivot2 = nums2[newIndex2];
            if (pivot1 <= pivot2) {
                k -= (newIndex1 - index1 + 1);
                index1 = newIndex1 + 1;
            } else {
                k -= (newIndex2 - index2 + 1);
                index2 = newIndex2 + 1;
            }
        }
    }


    public static void main(String[] args) {
        int[] nums1 = {1, 2, 3};
        int[] nums2 = {4, 5, 6};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }

}
