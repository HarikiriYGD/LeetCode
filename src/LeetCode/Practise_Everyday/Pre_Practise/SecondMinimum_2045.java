package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.zip.Inflater;

/**
 * @Auther: Lil_boat
 * @Date: 13:36 2022/1/24
 * @Description: 城市用一个 双向连通 图表示，图中有 n 个节点，从 1 到 n 编号（包含 1 和 n）。
 * 图中的边用一个二维整数数组 edges 表示，其中每个 edges[i] = [ui, vi] 表示一条节点 ui 和节点 vi 之间的双向连通边。
 * 每组节点对由 最多一条 边连通，顶点不存在连接到自身的边。穿过任意一条边的时间是 time 分钟。
 * 每个节点都有一个交通信号灯，每 change 分钟改变一次，从绿色变成红色，再由红色变成绿色，循环往复。
 * 所有信号灯都 同时 改变。你可以在 任何时候 进入某个节点，但是 只能 在节点 信号灯是绿色时 才能离开。
 * 如果信号灯是  绿色 ，你 不能 在节点等待，必须离开。
 * <p>
 * 第二小的值 是 严格大于 最小值的所有值中最小的值。
 * 例如，[2, 3, 4] 中第二小的值是 3 ，而 [2, 2, 4] 中第二小的值是 4 。
 * 给你 n、edges、time 和 change ，返回从节点 1 到节点 n 需要的 第二短时间 。
 * <p>
 * 注意：
 * 你可以 任意次 穿过任意顶点，包括 1 和 n 。
 * 你可以假设在 启程时 ，所有信号灯刚刚变成 绿色 。
 */
public class SecondMinimum_2045 {
    static int N = 10040, M = 4 * N, INF = 0x3f3f3f3f, idx = 0;
    static int[] he = new int[N], e = new int[M], ne = new int[M];
    // 最短路线 和 次最短路线
    static int[] dist1 = new int[N], dist2 = new int[N];

    /**
     * 首先 idx 是用来对边进行编号的，然后对存图用到的几个数组作简单解释：
     * he 数组：存储是某个节点所对应的边的集合（链表）的头结点；
     * e  数组：由于访问某一条边指向的节点；
     * ne 数组：由于是以链表的形式进行存边，该数组就是用于找到下一条边；
     * w  数组：用于记录某条边的权重为多少。
     *
     * @param a
     * @param b
     */
    public static void add(int a, int b) {
        e[idx] = b;
        ne[idx] = he[a];
        he[a] = idx;
        idx++;
    }

    /*
    整体题意：在一张正权无向图上求严格次短路，该图无重边与自环。
    同时根据提示 edges.length<=min(2 ∗ 10^4,n∗(n−1)/2) 可知，该图为「稀疏图」，容易想到「堆优化 Dijkstra」做法。
    回到本题，与常规的「求最短路」不同，本题需要求得「严格次短路」，我们可以在原来的最短路算法基础上（dist1[] 数组用于记录最短路）
    多引入一个 dist2[] 数组，dist2[x]用于记录从节点 1 到节点 x 的严格次短路。

    维护次短路是容易的，基本思路为：
        * 若当前距离 dist 小于 dist1[x]，原本的最短路 dist1[x] 沦为次短路 dist2[x]，即先用 dist1[x] 更新 dist2[x] 后，
          再用 dist 更新 dist1[x]；
        * 若当前距离 dist 等于 dist1[x]，不符合「严格次短路」，忽略；
        * 若当前距离 dist 大于 dist1[x]，且 dist 小于 dist2[x]，则使用 dist 更新 dist2[x]。
    同时，由于处理「严格次短路包含重复边」的情况，我们无须使用 vis[] 数组记录处理过的点，而要确保每次「最短路」或者「次短路」被更新时，都进行入堆操作。
    然后考虑「红绿灯」切换问题，这本质是引入动态边权，假设我们当前经过 step 步到达节点 i，根据其与 change 的关系分情况讨论即可：
        * ⌊step / change⌋ 为偶数：当前处于绿灯，动态边权为 0；
        * ⌊step / change⌋ 为奇数：当前处于红灯，需要增加动态边权（等待时间），增加的动态边权为 change - (step % change)。
    最后，为了避免每个样例都 new 大数组，我们可以使用 static 修饰需要用到的数组，并在执行逻辑前进行重置工作。
     */
    public static int secondMinimum(int n, int[][] edge, int time, int change) {
        Arrays.fill(dist1, INF);
        Arrays.fill(dist2, INF);
        Arrays.fill(he, -1);
        idx = 0;
        // 存储图的边
        for (int[] e : edge) {
            int u = e[0], v = e[1];
            add(u, v);
            add(v, u);
        }
        // 优先队列 根据时间进行排序
        // int[] 存储的数据为：第几个节点和花费多少时间
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        q.add(new int[]{1, 0});
        dist1[1] = 0;
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            int u = poll[0], step = poll[1];
            // he 数组：存储是某个节点所对应的边的集合（链表）的头结点；
            // ne 数组：由于是以链表的形式进行存边，该数组就是用于找到下一条边；
            // e  数组：由于访问某一条边指向的节点；
            for (int i = he[u]; i != -1; i = ne[i]) {
                int j = e[i];
                // ⌊step / change⌋ 为偶数：当前处于绿灯，动态边权为 0；
                // ⌊step / change⌋ 为奇数：当前处于红灯，需要增加动态边权（等待时间），增加的动态边权为 change - (step % change)。
                int a = step / change, b = step % change;
                int wait = a % 2 == 0 ? 0 : change - b;
                int dist = wait + time + wait;
                if (dist1[j] > dist) {
                    dist2[j] = dist1[j];
                    dist1[j] = dist;
                    q.add(new int[]{j, dist1[j]});
                    q.add(new int[]{j, dist2[j]});
                } else if (dist1[j] < dist && dist < dist2[j]) {
                    dist2[j] = dist;
                    q.add(new int[]{j, dist2[j]});
                }
            }
        }
        return dist2[n];
    }
}
