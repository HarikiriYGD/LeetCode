package LeetCode.Array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;

/**
 * @Auther: Lil_boat
 * @Date: 9:58 2021/12/27
 * @Description: 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * <p>
 * 返回滑动窗口中的最大值。
 */
public class MaxSlidingWindow_239 {
    /**
     * 思路： 遍历数组 L R 为滑窗左右边界 只增不减
     * 双向队列保存当前窗口中最大的值的数组下标 双向队列中的数从大到小排序，
     * 新进来的数如果大于等于队列中的数 则将这些数弹出 再添加
     * 当R-L+1=k 时 滑窗大小确定 每次R前进一步L也前进一步 保证此时滑窗中最大值的
     * 数组下标在[L，R]中，并将当前最大值记录
     * 举例： nums[1，3，-1，-3，5，3，6，7] k=3
     * 1：L=0，R=0，队列【0】 R-L+1 < k
     * 队列代表值【1】
     * 2: L=0,R=1, 队列【1】 R-L+1 < k
     * 队列代表值【3】
     * 解释：当前数为3 队列中的数为【1】 要保证队列中的数从大到小 弹出1 加入3
     * 但队列中保存的是值对应的数组下标 所以队列为【1】 窗口长度为2 不添加记录
     * 3: L=0,R=2, 队列【1，2】 R-L+1 = k ,result={3}
     * 队列代表值【3，-1】
     * 解释：当前数为-1 队列中的数为【3】 比队列尾值小 直接加入 队列为【3，-1】
     * 窗口长度为3 添加记录记录为队首元素对应的值 result[0]=3
     * 4: L=1,R=3, 队列【1，2，3】 R-L+1 = k ,result={3，3}
     * 队列代表值【3，-1,-3】
     * 解释：当前数为-3 队列中的数为【3，-1】 比队列尾值小 直接加入 队列为【3，-1，-3】
     * 窗口长度为4 要保证窗口大小为3 L+1=1 此时队首元素下标为1 没有失效
     * 添加记录记录为队首元素对应的值 result[1]=3
     * 5: L=2,R=4, 队列【4】 R-L+1 = k ,result={3，3，5}
     * 队列代表值【5】
     * 解释：当前数为5 队列中的数为【3，-1，-3】 保证从大到小 依次弹出添加 队列为【5】
     * 窗口长度为4 要保证窗口大小为3 L+1=2 此时队首元素下标为4 没有失效
     * 添加记录记录为队首元素对应的值 result[2]=5
     * 依次类推 如果队首元素小于L说明此时值失效 需要弹出
     *
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow_LinkedList(int[] nums, int k) {
        if (nums == null || nums.length < 2) return nums;
        // 双向队列 保存当前窗口最大值的数组位置 保证队列中数组位置的数从大到小排序
        LinkedList<Integer> list = new LinkedList<>();
        // 存储k个数字中的最大值 即最多滑动次数
        int n = nums.length - k + 1;
        int[] res = new int[n];
        for (int i = 0; i < nums.length; i++) {
            // 保证从大到小 如果前面数小 弹出
            while (!list.isEmpty() && nums[list.peekLast()] <= nums[i]) {
                list.pollLast();
            }
            // 添加当前值对应的数组下标
            list.addLast(i);
            // 初始化窗口 等到窗口长度为k时 下次移动在删除过期数值
            if (list.peek() <= i - k) {
                list.poll();
            }
            // 窗口长度为k时，再保存当前窗口中最大值
            if (i - k + 1 >= 0) {
                res[i - k + 1] = nums[list.peek()];
            }
        }
        return res;
    }

    public static int[] maxSlidingWindow_PriorityQueue(int[] nums, int k) {
        int n = nums.length;
        PriorityQueue<int[]> queue = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
            }
        });

        for (int i = 0; i < k; i++) {
            queue.add(new int[]{nums[i], i});
        }
        int[] ans = new int[n - k + 1];
        ans[0] = queue.peek()[0];
        for (int i = k; i < n; i++) {
            queue.add(new int[]{nums[i], i});
            while (queue.peek()[1] <= i - k) {
                queue.poll();
            }
            ans[i - k + 1] = queue.peek()[0];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        System.out.println(Arrays.toString(maxSlidingWindow_LinkedList(nums, k)));
        System.out.println(Arrays.toString(maxSlidingWindow_PriorityQueue(nums, k)));
    }

}
