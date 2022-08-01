package LeetCode.Practise_Everyday.Date_2022_04;

import java.util.Arrays;
/**
 * @Auther: Lil_boat
 * @Date: 9:47 2022/4/28
 * @Tile: 按奇偶排序数组
 * @Description: 给你一个整数数组 nums，将 nums 中的的所有偶数元素移动到数组的前面，后跟所有奇数元素。
 * 返回满足此条件的 任一数组 作为答案。
 */

public class SortArrayByParity_905 {

    public static int[] sortArrayByParity(int[] nums){
        int left = 0;
        int right = nums.length - 1;
        while(left < right){
            if(nums[left] % 2 == 0)left++;
            else if(nums[right] % 2!=0 )right--;
            else{
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
                left++;
                right--;
            }
        }
        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {3,1,2,4};
        System.out.println(Arrays.toString(sortArrayByParity(nums)));
    }

}
