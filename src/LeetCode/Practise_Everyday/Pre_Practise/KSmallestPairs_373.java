package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.*;

/**
 * @Auther: Lil_boat
 * @Date: 13:08 2022/1/14
 * @Description: 给定两个以升序排列的整数数组 nums1 和 nums2 , 以及一个整数 k 。
 * 定义一对值 (u,v)，其中第一个元素来自 nums1，第二个元素来自 nums2 。
 * 请找到和最小的 k 个数对 (u1,v1),  (u2,v2)  ...  (uk,vk) 。
 */
public class KSmallestPairs_373 {

    /*
        实际求解时可以不用求出所有的数对，只需从所有符合待选的数对中选出最小的即可，
        假设当前已选的前 n 小数对的索引分别为 (a_1,b_1),(a_2,b_2),(a_3,b_3),...,(a_n,b_n)，
        由于两个数组都是按照升序进行排序的，则可以推出第 n+1 小的数对的索引选择范围为 (a_1+1,b_1),(a_1,b_1+1),(a_2+1,b_2),(a_2,b_2+1),
        (a_3+1,b_3),(a_3,b_3+1),...,(a_n+1,b_n),(a_n,b_n+1)，假设我们利用堆的特性可以求出待选范围中最小数对的索引为 (a_{i},b_{i})，
        同时将新的待选的数对 (a_{i}+1,b_{i}),(a_{i},b_{i}+1) 加入到堆中，直到我们选出 k 个数对即可。
     */
    static boolean flag = true;

    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        if (nums1.length > nums2.length && flag) return kSmallestPairs(nums2, nums1, k);
        List<List<Integer>> ans = new ArrayList<>();
        // 优先队列
        // 因为两个数组是有序的数组，所以两个数组前面相加的都是算是最小的对
        // 排序规则是以和的升序排序
        Queue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(nums1[a[0]] + nums2[a[1]], nums1[b[0]] + nums2[b[1]]));
        // 初始化优先队列中的元素
        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            q.add(new int[]{i, 0});
        }
        // 添入k个最小对元素
        while (k > 0 && !q.isEmpty()) {
            int[] index = q.poll();
            // 用的方法
//            ans.add(List.of(nums1[index[0]], nums2[index[1]]));

            // 如果index[1] + 1 没有超过nums2的长度，再加元素加入到优先队列之中
            if (index[1] + 1 < nums2.length) {
                q.add(new int[]{index[0], index[1] + 1});
            }
            k--;
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] nums1 = {1, 2};
        int[] nums2 = {3};
        kSmallestPairs(nums1, nums2, 3);
    }
}
