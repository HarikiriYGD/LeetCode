package LeetCode.Practise_Everyday.Date_2022_07;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * @Author: Lil_boat
 * @Date: 2022/7/23 12:07
 * @Title: 重建序列
 * @Description: 给定一个长度为 n 的整数数组 nums ，其中 nums 是范围为 [1，n] 的整数的排列。
 * 还提供了一个 2D 整数数组 sequences ，其中 sequences[i] 是 nums 的子序列。
 * 检查 nums 是否是唯一的最短 超序列 。最短 超序列 是 长度最短 的序列，并且所有序列 sequences[i] 都是它的子序列。
 * 对于给定的数组 sequences ，可能存在多个有效的 超序列 。
 * <p>
 * 例如，对于 sequences = [[1,2],[1,3]] ，有两个最短的 超序列 ，[1,2,3] 和 [1,3,2] 。
 * 而对于 sequences = [[1,2],[1,3],[1,2,3]] ，唯一可能的最短 超序列 是 [1,2,3] 。[1,2,3,4] 是可能的超序列，但不是最短的。
 * 如果 nums 是序列的唯一最短 超序列 ，则返回 true ，否则返回 false 。
 * 子序列 是一个可以通过从另一个序列中删除一些元素或不删除任何元素，而不改变其余元素的顺序的序列。
 * <p>
 * 链接：https://leetcode.cn/problems/ur2n8P
 */
public class D23_SequenceReconstruction_444 {

    public boolean sequenceReconstruction(int[] nums, int[][] sequences) {
        int n = nums.length;
        Deque<Integer> deque = new ArrayDeque<>();
        // 每个节点的入度
        int[] v = new int[n + 1];
        List<Integer>[] grap = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            grap[i] = new ArrayList<>();
        }
        // 存图
        for (int[] sequence : sequences) {
            for (int i = 0; i < sequence.length - 1; i++) {
                // 存储入度
                v[sequence[i + 1]]++;
                grap[sequence[i]].add(sequence[i + 1]);
            }
        }
        int ptr = 0;
        for (int i = 1; i <= n; i++) {
            // 将所有入度为0的节点入队
            if (v[i] == 0) {
                deque.offer(i);
            }
        }
        while (!deque.isEmpty()) {
            // 如果存在两个或以上的节点的入度为0，则不满足超序列的定义
            if (deque.size() > 1) return false;
            int cur = deque.poll();
            // 是否和nums的顺序一致
            if (nums[ptr++] != cur) return false;
            for (int next : grap[cur]) {
                v[next]--;
                if (v[next] == 0) deque.offer(next);
            }
        }
        return ptr == n;
    }

}
