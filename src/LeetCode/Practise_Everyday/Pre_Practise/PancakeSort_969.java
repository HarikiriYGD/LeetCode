package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.ArrayList;
import java.util.List;
/**
 * @Auther: Lil_boat
 * @Date: 11:35 2022/2/19
 * @Description: 给你一个整数数组 arr ，请使用 煎饼翻转 完成对数组的排序。
 * 一次煎饼翻转的执行过程如下：
 * 选择一个整数 k ，1 <= k <= arr.length
 * 反转子数组 arr[0...k-1]（下标从 0 开始）
 * 例如，arr = [3,2,1,4] ，选择 k = 3 进行一次煎饼翻转，反转子数组 [3,2,1] ，得到 arr = [1,2,3,4] 。
 *
 * 以数组形式返回能使 arr 有序的煎饼翻转操作所对应的 k 值序列。任何将数组排序且翻转次数在 10 * arr.length 范围内的有效答案都将被判断为正确。
 */
public class PancakeSort_969 {
    /*
        由于每次我们都对「某段前缀」进行整体翻转，并且规定了翻转次数在一定范围内的方案均为合法翻转方案，同时 arr 又是 1 到 n 的排列。
        我们可以很自然想到「冒泡排序」：每次确定未排序部分最右端的元素（最大值）。

        具体的，假设下标 [k + 1, n - 1] 部分已有序，如果我们希望当前值 t 出现在某个位置 k 上，可以进行的操作为：
            * 如果当前值 t 已在 k 上，无须进行操作；
            * 如果当前值不在 k 上，根据当前值是否在数组头部（下标为 0）进行分情况讨论：
                当前值在数组头部（下标为 0），直接将 [0, k] 部分进行翻转（将 k + 1 加入答案中），即可将当前值 t 放到位置 k 上；
                当前值不在数组头部（下标为 0），而是在 idx位置上，需要先将 [0, idx] 部分进行翻转（将 idx + 1 加入答案中），
                这样使得当前值 t 出现数组头部（下标为 0），然后再将 [0, k] 部分进行翻转（将 k + 1 加入答案中），即可将当前值 t 放到位置 k 上。
                而翻转某个前缀的操作可使用「双指针」实现，复杂度为 O(n)。
     */
    public static List<Integer> pancakeSort(int[] arr) {
        int n = arr.length;
        int[] idxs = new int[n + 10];
        // 记录arr中每个元素的第一次出现的索引
        for (int i = 0; i < n; i++) {
            idxs[arr[i]] = i;
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = n; i >= 1; i--) {
            int idx = idxs[i];
            // 如果索引和最后isOneBitCharacter排序好的索引值相同 则不变
            if (idx == i - 1) continue;
            if (idx != 0) {
                ans.add(idx + 1);
                reverse(arr, 0, idx, idxs);
            }
            ans.add(i);
            reverse(arr, 0, i - 1, idxs);
        }
        return ans;
    }

    public static void reverse(int[] arr, int i, int j, int[] idxs) {
        while (i < j) {
            // 索引交换
            idxs[arr[i]] = j;
            idxs[arr[j]] = i;
            // 值交换
            int c = arr[i];
            arr[i++] = arr[j];
            arr[j--] = c;
        }
    }

    public static void main(String[] args) {
        int[] arr = {4,1,3,2};
        pancakeSort(arr);
    }
}
