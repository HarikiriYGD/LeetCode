package LeetCode.Practise_Everyday.Date_2022_08;

/**
 * @Author: Lil_boat
 * @Date: 2022/8/26 12:01
 * @Title: 数组中两元素的最大乘积
 * @Description: 给你一个整数数组 nums，请你选择数组的两个不同下标 i 和 j，使 (nums[i]-1)*(nums[j]-1) 取得最大值。
 * 请你计算并返回该式的最大值。
 *
 * 链接：https://leetcode.cn/problems/maximum-product-of-two-elements-in-an-array
 */
public class D26_MaxProduct_1464 {

    public int maxProduct(int[] nums){
        int max = Integer.MIN_VALUE, subMax = Integer.MIN_VALUE;
        for (int num : nums) {
            if (max < num){
                // 寻找数组中最大的元素
                subMax = max;
                max = num;
            } else if (subMax < num){
                // 寻找数组中第二大的元素
                subMax = num;
            }
        }
        return (subMax - 1) * (max - 1);
    }
}
