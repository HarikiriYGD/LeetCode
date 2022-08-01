package LeetCode.Practise_Everyday.Pre_Practise;

/***
 * @Auther: Lil_boat
 * @Date: 9:29 2022/1/12
 * @Description: 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 * 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
 *
 */
public class IncreasingTriplet_334 {

    /*
    贪心：
        可以使用贪心的方法将空间复杂度降到 O(1)。从左到右遍历数组 nums，遍历过程中维护两个变量 first 和 second，
        分别表示递增的三元子序列中的第一个数和第二个数，任何时候都有 first < second。
        初始时，first=nums[0]，second=+∞。对于 1 ≤ i < n，当遍历到下标 i 时，令 num=nums[i]，进行如下操作：
            1. 如果 num > second，则找到了一个递增的三元子序列，返回 true；
            2. 否则，如果 num > first，则将 second 的值更新为 num；
            3. 否则，将 first 的值更新为 num。
        如果遍历结束时没有找到递增的三元子序列，返回 false。
        上述做法的贪心思想是：为了找到递增的三元子序列，first 和 second 应该尽可能地小，此时找到递增的三元子序列的可能性更大。
        假设 (first,second,num) 是一个递增的三元子序列，如果存在 second’ 满足 first < second’ < second
        且 second’ 的下标位于 first 的下标和 num 的下标之间，则 (first,second’,num) 也是一个递增的三元子序列。
        但是当 (first,second’,num) 是递增的三元子序列时，由于 num 不一定大于 second，因此 (first,second,num) 未必是递增的三元子序列。
        由此可见，为了使找到递增的三元子序列的可能性更大，三元子序列的第二个数应该尽可能地小，
        将 second’ 作为三元子序列的第二个数优于将 second 作为三元子序列的第二个数。
        同理可得，三元子序列的第一个数也应该尽可能地小。
        如果遍历过程中遇到的所有元素都大于 first，则当遇到 num > second 时，first 一定出现在 second 的前面，
        second 一定出现在 num 的前面，(first,second,num) 即为递增的三元子序列。
        如果遍历过程中遇到小于 first 的元素，则会用该元素更新 first，虽然更新后的 first 出现在 second 的后面，
        但是在 second 的前面一定存在一个元素 first’ 小于 second，因此当遇到 num>second 时，(first’,second,num) 即为递增的三元子序列。
        根据上述分析可知，当遇到 num > second 时，一定存在一个递增的三元子序列，
        该三元子序列的第二个数和第三个数分别是 second 和 num，因此返回 true。
     */
    public static boolean increasingTriplet_other(int[] nums){
        if (nums.length < 3) return false;
        int min = Integer.MAX_VALUE;
        int sMin = Integer.MAX_VALUE;
        for (int num : nums) {
            // 找到数组的最小值
            if (num<=min)min=num;
            // 找到数组的次小值
            else if (num<=sMin)sMin=num;
            else return true;
        }
        return false;
    }

    /**
     * 暴力的方式(会超时)
     * 时间复杂度：O(n^3)
     * @param nums
     * @return
     */
    public static boolean increasingTriplet(int[] nums) {
        if (nums.length < 3) return false;
        for (int i = 0; i < nums.length - 2; i++) {
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] < nums[j] && nums[j] < nums[k]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 5, 0, 4, 6};
        System.out.println(increasingTriplet(nums));
        System.out.println(increasingTriplet_other(nums));
    }
}
