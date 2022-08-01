package LeetCode.Practise_Everyday.Date_2022_03;

import java.util.*;

public class BusiestServers_1606 {

    static int N = 100010;
    static int[] cnt = new int[N];

    /*
        题目要统计处理任务数最多的机器，首先容易想到使用「哈希表」统计每个机台处理的任务数，利用机台数量 kk 最多不超过 10^5，
        我们可以开一个静态数组 cnts 来充当哈希表，同时维护一个当前处理的最大任务数量 max，最终所有满足 cnst[i] = max 的机台集合即是答案。

        再根据「每个任务有对应的开始时间和持续时间」以及「任务分配规则」，容易想到使用优先队列（堆）和有序集合（红黑树）来进行维护。
        具体的，利用「每个任务有对应的开始时间和持续时间」，我们使用优先队列（堆）维护二元组 (idx, endTime)，
        其中 idx 为机器编号，endTime 为当前机台所处理任务的结束时间（也就是该机台最早能够接受新任务的时刻），
        对于每个 arrival[i] 而言（新任务），我们先从优先队列中取出所有 endTime < arrival[i] 的机台 idx，
        加入「空闲池」，然后再按照「任务分配规则」从空闲池子中取机台，若取不到，则丢弃该任务。

        由于「任务分配规则」是优先取大于等于 i % k 的最小值，若取不到，再取大于等于 0 的最小值。
        因此我们的「空闲池」最好是支持「二分」的有序集合，容易想到基于「红黑树」的 TreeSet 结构。

     */
    public static List<Integer> busiestServers(int k, int[] arrival, int[] load) {
        Arrays.fill(cnt, 0);
        // 请求的长度
        int n = arrival.length, max = 0;
        Map<Integer, Integer> map = new HashMap<>();
        // 优先队列 根据服务完成的时间来出队   升序排列
        PriorityQueue<int[]> busy = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        // 利用红黑树记录空闲池 存储当前任务开始时刻，所有空闲的服务器索引
        TreeSet<Integer> free = new TreeSet<>();
        // 初始所有服务器均空闲，全部加入到free
        for (int i = 0; i < k; i++) free.add(i);
        for (int i = 0; i < n; i++) {
            // 每个请求开始的时间 结束的时间
            int start = arrival[i], end = load[i] + start;
            // 找出所有当前忙碌的服务器的任务结束时刻早于待分配任务的开始时刻的，都加入到free集合中等待筛选
            while (!busy.isEmpty() && busy.peek()[1] <= start) free.add(busy.poll()[0]);
            // 返回等于或大于 i % k的最小元素
            Integer u = free.ceiling(i % k);
            // 如果不存在 从0开始找
            if (u == null) u = free.ceiling(0);
            // 若依然取不到，则放弃该任务
            if (u == null) continue;
            // 取到了,将该服务器索引从free中移除
            free.remove(u);
            // 与此同时，将该服务器加入到忙碌队列中，存储一个长度为2的数组，[0]:索引 [1]:任务结束时刻
            busy.add(new int[]{u, end});
            // 更新当前最大的处理任务数目
            max = Math.max(max, ++cnt[u]);
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            if (cnt[i] == max) ans.add(i);
        }
        return ans;
    }

}
