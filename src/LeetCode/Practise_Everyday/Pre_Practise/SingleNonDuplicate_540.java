package LeetCode.Practise_Everyday.Pre_Practise;
/**
 * @Auther: Lil_boat
 * @Date: 10:47 2022/2/14
 * @Description: 给你一个仅由整数组成的有序数组，其中每个元素都会出现两次，唯有一个数只会出现一次。
 * 请你找出并返回只出现一次的那个数。
 * 你设计的解决方案必须满足 O(log n) 时间复杂度和 O(1) 空间复杂度。
 */
public class SingleNonDuplicate_540 {

    /*
        思路和算法:
            假设只出现一次的元素位于下标 x，由于其余每个元素都出现两次，因此下标 x 的左边和右边都有偶数个元素，数组的长度是奇数。
            由于数组是有序的，因此数组中相同的元素一定相邻。对于下标 x 左边的下标 y，如果 nums[y] = nums[y+1]，则 y 一定是偶数；
            对于下标 x 右边的下标 z，如果 nums[z] = nums[z+1]，则 z 一定是奇数。
            由于下标 x 是相同元素的开始下标的奇偶性的分界，因此可以使用二分查找的方法寻找下标 x。
            初始时，二分查找的左边界是 0，右边界是数组的最大下标。每次取左右边界的平均值 mid 作为待判断的下标，
            根据 mid 的奇偶性决定和左边或右边的相邻元素比较：
                1. 如果 mid 是偶数，则比较 nums[mid] 和 nums[mid+1] 是否相等；
                2. 如果 mid 是奇数，则比较 nums[mid−1] 和 nums[mid] 是否相等。
            如果上述比较相邻元素的结果是相等，则 mid < x，调整左边界，否则 mid ≥ x，调整右边界。调整边界之后继续二分查找，直到确定下标 x 的值。
            得到下标 x 的值之后，nums[x] 即为只出现一次的元素。
     */
    public static int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1, mid;
        while (left < right) {
            // 中间索引
            mid = left + (right - left) / 2;
            // 如果中间索引是奇数 变为偶数
            if (mid % 2 == 1) mid--;
            if (nums[mid] == nums[mid + 1]) {
                left = mid + 2;
            } else right = mid;
        }
        return nums[left];
    }

    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 3, 3, 4, 4,8,8};
        System.out.println(singleNonDuplicate(nums));
    }
}
