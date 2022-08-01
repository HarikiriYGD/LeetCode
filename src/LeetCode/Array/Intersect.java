package LeetCode.Array;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Intersect {

    /**
     * 给定两个数组，编写一个函数来计算它们的交集。
     *
     * @param nums1
     * @param nums2
     * @return 返回合并之后的数组
     */
    public static int[] intersect(int[] nums1, int[] nums2) {
        // 对两个数组进行排序
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        // 初始化合并数组的大小
        int[] res = new int[Math.min(nums1.length, nums2.length)];
        int index = 0, index1 = 0, index2 = 0;
        while (index1 < nums1.length && index2 < nums2.length) {
            if (nums1[index1] < nums2[index2]) {
                index1++;
            } else if (nums1[index1] > nums2[index2]) {
                index2++;
            } else {
                res[index] = nums1[index1];
                index++;
                index1++;
                index2++;
            }
        }
        // 将后面的多余的0截去，从下标from开始 到to结束
        return Arrays.copyOfRange(res, 0, index);
    }

    /**
     * 通过HashMap的方式来进行合并数组
     *
     * @param nums1
     * @param nums2
     * @return 返回合并之后的数组
     */
    public static int[] intersect_HashMap(int[] nums1, int[] nums2) {
        /**
         * 比较数组的大小，利用较小的数组来构造合并数组的大小
         * 尽量使用较小的存储空间
         */
        if (nums1.length > nums2.length) {
            return intersect_HashMap(nums2, nums1);
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums1) {
            int count = map.getOrDefault(num, 0) + 1;
            map.put(num, count);
        }

        int[] res = new int[nums1.length];
        int index = 0;
        for (int num : nums2) {
            int count = map.getOrDefault(num, 0);
            if (count > 0) {
                res[index++] = num;
                count--;
                if (count > 0) {
                    map.put(num, count);
                } else {
                    map.remove(num);
                }
            }
        }
        return Arrays.copyOfRange(res, 0, index);
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 2, 2};
        int[] nums2 = {2, 2, 1};
        int[] res = intersect_HashMap(nums1, nums2);
        String s = Arrays.toString(res);
        System.out.println(s);
    }

}
