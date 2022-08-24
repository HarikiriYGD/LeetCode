package LeetCode.Practise_Everyday.Date_2022_08;

import java.util.Arrays;

/**
 * @Author: Lil_boat
 * @Date: 2022/8/24 23:52
 * @Title: 通过翻转子数组使两个数组相等
 * @Description: 给你两个长度相同的整数数组 target 和 arr 。每一步中，你可以选择 arr 的任意 非空子数组 并将它翻转。你可以执行此过程任意次。
 * 如果你能让 arr 变得与 target 相同，返回 True；否则，返回 False 。
 *
 * 链接：https://leetcode.cn/problems/make-two-arrays-equal-by-reversing-sub-arrays
 */
public class D24_CanBeEqual_1460 {


    public boolean canBeEqual(int[] target, int[] arr) {
        Arrays.sort(target);
        Arrays.sort(arr);
        return Arrays.equals(target,arr);
    }

}
