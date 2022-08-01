package LeetCode.AimToOffer;

/**
 * @Auther: Lil_boat
 * @Date: 15:33 2022/3/11
 * @Description:
 */
public class ReversePairs_51 {

    int[] tmp;

    public int reversePairs(int[] nums) {
        this.tmp = new int[nums.length];
        return mergeSort(nums, 0, nums.length - 1);
    }

    public int mergeSort(int[] nums, int left, int right) {
        // 边界判断条件
        // 区间归并
        if (left >= right) return 0;
        int mid = (left + right) / 2;
        int res = mergeSort(nums, left, mid) + mergeSort(nums, mid + 1, right);
        // 合并阶段
        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) tmp[k] = nums[k];

        for (int k = left; k <= right; k++) {
            if (j == mid + 1) nums[k] = tmp[j + 1];
            else if (j == right + 1) nums[k] = tmp[j++];
            else if (tmp[i] <= tmp[j]) nums[k] = tmp[i++];
            else {
                nums[k] = tmp[j++];
                res += mid - i + 1; // 统计逆序对
            }
        }
        return res;
    }

}
