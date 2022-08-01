package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * @Auther: Lil_boat
 * @Date: 9:45 2021/12/24
 * @Description: 有一棵特殊的苹果树，一连 n 天，每天都可以长出若干个苹果。
 * 在第 i 天，树上会长出 apples[i] 个苹果，这些苹果将会在 days[i] 天后（也就是说，第 i + days[i] 天时）腐烂，变得无法食用。
 * 也可能有那么几天，树上不会长出新的苹果，此时用 apples[i] == 0 且 days[i] == 0 表示。
 * 你打算每天 最多 吃一个苹果来保证营养均衡。注意，你可以在这 n 天之后继续吃苹果。
 * 给你两个长度为 n 的整数数组 days 和 apples ，返回你可以吃掉的苹果的最大数目。
 */
public class EatenApples_1705 {
    /**
     * 贪心 + 优先队列
     * @param apples
     * @param days
     * @return
     */
    public static int eatenApples(int[] apples, int[] days) {
        int i = 0;
        int eaten = 0;
        int n = apples.length;
        Queue<int[]> q = new PriorityQueue<>((a, b) -> Integer.compare(a[1], b[1]));
        // 新建新的apples数组
        // 下标为0为该组的苹果数量
        // 下标为1为该组苹果的过期时间
        // 将这些苹果放入优先队列，按过期时间从早到晚排列
        while (i < n) {
            // 新生的苹果入队
            if (apples[i] > 0) {
                int[] newApple = {apples[i], i + days[i]};
                q.offer(newApple);
            }

            while (!q.isEmpty() && q.peek()[1] <= i) {
                // 清除已经品尝过的苹果
                q.poll();
            }

            if (!q.isEmpty()) {
                // 优先吃掉容易坏的苹果
                eaten += 1;
                if (--q.peek()[0] == 0) {
                    q.poll();
                }
            }
            ++i;
        }
        while (!q.isEmpty()) {
            while (!q.isEmpty() && q.peek()[1] <= i) {
                q.poll();
            }
            if (!q.isEmpty()) {
                int amount = Math.min(q.peek()[0], q.peek()[1] - i);
                eaten += amount;
                i += amount;
                q.poll();
            }
        }
        return eaten;
    }

    /**
     * 这题是一道经典的结合优先队列（堆）的贪心题，与结合排序的贪心题一样，属于最为常见的贪心题型。
     * 直觉上，我们会觉得「优先吃掉最快过期的苹果」会是最优，而这个维护苹果过期的过程，可以使用「小根堆」来实现。
     *苹果数量很大，但产生苹果的天数最多为 2 * 10^4，因此我们以二元组 (最后食用日期, 当日产生苹果数量) 的形式存入「小根堆」进行维护。
     *
     * 具体的，我们可以按照如下逻辑进行模拟（令 n 为数组长度，time 为当前时间，ans 为吃到的苹果数量）：
     * 首先，如果「time < n」或者「堆不为空」，说明「还有苹果未被生成」或者「未必吃掉」，继续模拟；
     * 在当日模拟中，如果「time < n」，说明当天有苹果生成，先将苹果 以二元组 (time + days[time] - 1, apples[time])形式加入小根堆中；
     * 其中二元组表示 (最后食用日期, 当日产生苹果数量)(最后食用日期,当日产生苹果数量)，同时需要过滤 apples[time] = 0的情况。
     * 然后尝试从堆中取出「最后食用日期」最早「可食用」的苹果 cur，如果堆顶元素已过期，则抛弃；
     * 如果吃掉 cur 一个苹果后，仍有剩余，并且最后时间大于当前时间（尚未过期），将 cur 重新入堆；
     * 循环上述逻辑，直到所有苹果出堆。
     *
     * @param apples
     * @param days
     * @return
     */
    public static int eatenApples_Simplify(int[] apples, int[] days) {
        Queue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        int n = apples.length, time = 0, ans = 0;
        while (time < n || !q.isEmpty()) {
            if (time < n && apples[time] > 0) q.add(new int[]{time + days[time] - 1, apples[time]});
            while (!q.isEmpty() && q.peek()[0] < time) q.poll();
            if (!q.isEmpty()) {
                int[] cur = q.poll();
                if (--cur[1] > 0 && cur[0] > time) q.add(cur);
                ans++;
            }
            time++;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] apples = {2, 1, 10};
        int[] days = {2, 10, 1};
        System.out.println(eatenApples(apples, days));
        System.out.println(eatenApples_Simplify(apples, days));
    }
}
