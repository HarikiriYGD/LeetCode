package LeetCode.Practise_Everyday.Date_2022_06;

import java.util.Arrays;

/***
 * @Auther: Lil_boat
 * @Date: 9:36 2022/6/28
 * @Tile: 摆动排序 II
 * @Description: 给你一个整数数组 nums，将它重新排列成 nums[0] < nums[1] > nums[2] < nums[3]... 的顺序。
 * 你可以假设所有输入数组都可以得到满足题目要求的结果。
 *
 * 链接：https://leetcode.cn/problems/wiggle-sort-ii
 */
public class D28_WiggleSort_324 {

    public void wiggleSort(int[] nums) {
        int n = nums.length;
        int[] cp = Arrays.copyOfRange(nums, 0, n);
        for (int i = 0; i < n; i++) {
            cp[i] = nums[i];
        }
        Arrays.sort(cp);
        int l = n % 2 == 0 ? n / 2 - 1 : n / 2, r = n - 1;
        int index = 0;
        while (index < n) {
            nums[index] = cp[(index & 1) == 0 ? l-- : r--];
            index++;
        }

    }

    public void wiggleSort_Bucket(int[] nums){
        //5001个桶
        int[] bucket = new int[5001];
        for (int num : nums) {
            bucket[num]++;
        }
        int j = 5000;
        //插空放 较大元素
        for (int i = 1; i < nums.length; i += 2) {
            while (bucket[j] == 0) {
                j--;
            }
            nums[i] = j;
            bucket[j]--;
        }
        //插空放 较小元素
        for (int i = 0; i < nums.length; i += 2) {
            while (bucket[j] == 0) {
                j--;
            }
            nums[i] = j;
            bucket[j]--;
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,5,1,1,6,4};
        D28_WiggleSort_324 t= new D28_WiggleSort_324();
        t.wiggleSort_Bucket(nums);
    }

}
