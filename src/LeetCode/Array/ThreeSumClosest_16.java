package LeetCode.Array;

import java.util.Arrays;

public class ThreeSumClosest_16 {

    public static int threeSumClosest(int[] nums, int target) {
        // 排序
        Arrays.sort(nums);
        // 寻找最接近target的值
        int n = nums.length;
        int closestNum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int l = i + 1, r = nums.length - 1;
            while (l < r){
                int threeSum = nums[l] + nums[r] + nums[i];
                if (Math.abs(threeSum - target) < Math.abs(closestNum - target)) {
                    closestNum = threeSum;
                }
                if (threeSum > target) {
                    r--;
                } else if (threeSum < target) {
                    l++;
                } else {
                    // 如果已经等于target的话, 肯定是最接近的
                    return target;
                }

            }

        }
        return closestNum;
    }


    public static void main(String[] args) {
        int[] nums = {1,1,1,0};
        System.out.println(threeSumClosest(nums,-100));
    }

}
