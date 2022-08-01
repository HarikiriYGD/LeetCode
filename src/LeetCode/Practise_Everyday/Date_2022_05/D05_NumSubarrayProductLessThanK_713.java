package LeetCode.Practise_Everyday.Date_2022_05;

/**
 * @Auther: Lil_boat
 * @Date: 10:12 2022/5/5
 * @Tile: 乘积小于 K 的子数组
 * @Description: 给你一个整数数组 nums 和一个整数 k ，请你返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目。
 * <p>
 * 链接：https://leetcode-cn.com/problems/subarray-product-less-than-k
 */
public class D05_NumSubarrayProductLessThanK_713 {

    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k <= 1) return 0;
        int left = 0, right = 0;
        // 创建一个变量记录路上的乘积
        // 记录连续数组的组合个数
        int mul = 1, ans = 0;
        while (right < nums.length) {
            // 记录乘积
            mul *= nums[right];
            // 当大于等于k，左指针右移并把之前左指针的数除掉
            while (mul >= k) {
                mul /= nums[left];
                left++;
            }
            // 每次右指针位移到一个新位置，应该加上 x 种数组组合：
            //  nums[right]
            //  nums[right-1], nums[right]
            //  nums[right-2], nums[right-1], nums[right]
            //  nums[left], ......, nums[right-2], nums[right-1], nums[right]
            // 共有 right - left + 1 种
            ans += right - left + 1;
            right++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 10, 4, 3, 8, 3, 3, 6, 2, 10, 10, 9, 3};
        D05_NumSubarrayProductLessThanK_713 t = new D05_NumSubarrayProductLessThanK_713();
        System.out.println(t.numSubarrayProductLessThanK(nums, 19));
    }

}
