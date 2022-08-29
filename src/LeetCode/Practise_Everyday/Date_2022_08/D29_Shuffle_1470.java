package LeetCode.Practise_Everyday.Date_2022_08;

/**
 * @Author: Lil_boat
 * @Date: 2022/8/29 17:14
 * @Title: 重新排列数组
 * @Description: 给你一个数组 nums ，数组中有 2n 个元素，按 [x1,x2,...,xn,y1,y2,...,yn] 的格式排列。
 * 请你将数组按 [x1,y1,x2,y2,...,xn,yn] 格式重新排列，返回重排后的数组。
 * <p>
 * 链接：https://leetcode.cn/problems/shuffle-the-array
 */
public class D29_Shuffle_1470 {

    public int[] shuffle(int[] nums, int n) {
        int[] ans = new int[2 * n];
        int l = 0, r = n;
        int idx = 0;
        while (idx < 2 * n) {
            if (idx % 2 == 0) {
                ans[idx++] = nums[l++];
            } else {
                ans[idx++] = nums[r++];
            }
        }
        return ans;
    }

}
