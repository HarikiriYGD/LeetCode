package LeetCode.Practise_Everyday.Date_2022_08;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * @Author: Lil_boat
 * @Date: 2022/8/25 10:42
 * @Title: 找到 K 个最接近的元素
 * @Description: 给定一个 排序好 的数组 arr ，两个整数 k 和 x ，从数组中找到最靠近 x（两数之差最小）的 k 个数。返回的结果必须要是按升序排好的。
 * 整数 a 比整数 b 更接近 x 需要满足：
 * <p>
 * |a - x| < |b - x| 或者
 * |a - x| == |b - x| 且 a < b
 * <p>
 * 链接：https://leetcode.cn/problems/find-k-closest-eislements
 */
public class D25_FindClosestElements_658 {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> ans = new ArrayList<>();
        // 记录目前有多少个
//        int cnt = 0;
//        PriorityQueue<int[]> queue = new PriorityQueue<>((a, b) -> {
//            return a[1] == b[1] ? a[0] - b[0] : a[1] - b[1];
//        });
//        for (int i = 0; i < arr.length; i++) {
//            queue.add(new int[]{arr[i], Math.abs(arr[i] - x)});
//        }
//        while (k > 0) {
//            ans.add(queue.poll()[0]);
//            k--;
//        }
//        Collections.sort(ans);
        int l = 0, r = arr.length - 1;
        for (int i = 0; i < arr.length - k; i++) {
            if (Math.abs(arr[l] - x) <= Math.abs(arr[r] - x)) {
                r--;
            } else l++;
        }
        for (int i = l; i <= r; i++) ans.add(arr[i]);
        return ans;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5};
        D25_FindClosestElements_658 t = new D25_FindClosestElements_658();
        t.findClosestElements(arr, 4, 3).forEach(System.out::println);
    }

}
