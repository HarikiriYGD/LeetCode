package LeetCode.Practise_Everyday.Date_2022_03;

import java.util.*;

/**
 * @Auther: Lil_boat
 * @Date: 10:06 2022/3/20
 * @Description: 给你一个有 n 个服务器的计算机网络，服务器编号为 0 到 n - 1 。
 * 同时给你一个二维整数数组 edges ，其中 edges[i] = [ui, vi] 表示服务器 ui 和 vi 之间有一条信息线路，
 * 在 一秒 内它们之间可以传输 任意 数目的信息。再给你一个长度为 n 且下标从 0 开始的整数数组 patience 。
 * <p>
 * 题目保证所有服务器都是 相通 的，也就是说一个信息从任意服务器出发，都可以通过这些信息线路直接或间接地到达任何其他服务器。
 * 编号为 0 的服务器是 主 服务器，其他服务器为 数据 服务器。每个数据服务器都要向主服务器发送信息，并等待回复。
 * 信息在服务器之间按 最优 线路传输，也就是说每个信息都会以 最少时间 到达主服务器。
 * 主服务器会处理 所有 新到达的信息并 立即 按照每条信息来时的路线 反方向 发送回复信息。
 * <p>
 * 在 0 秒的开始，所有数据服务器都会发送各自需要处理的信息。
 * 从第 1 秒开始，每 一秒最 开始 时，每个数据服务器都会检查它是否收到了主服务器的回复信息（包括新发出信息的回复信息）：
 * <p>
 * 如果还没收到任何回复信息，那么该服务器会周期性 重发 信息。数据服务器 i 每 patience[i] 秒都会重发一条信息，
 * 也就是说，数据服务器 i 在上一次发送信息给主服务器后的 patience[i] 秒 后 会重发一条信息给主服务器。
 * 否则，该数据服务器 不会重发 信息。
 * 当没有任何信息在线路上传输或者到达某服务器时，该计算机网络变为 空闲 状态。
 * <p>
 * 请返回计算机网络变为 空闲 状态的 最早秒数 。
 *
 * https://leetcode.cn/problems/the-time-when-the-network-becomes-idle/
 */
public class NetworkBecomesIdle_2039 {

    static int N = 100010, M = 2 * N, INF = 0X3F3F3F3F;
    // 邻接表的方式存储图
    static int[] he = new int[N], e = new int[M], ne = new int[M];
    // 距离数组
    static int[] dist = new int[N];
    static int idx;

    /*
        根据题目可知这是一个边权为 1 的无向连通图，我们可以采用「邻接表建图 + BFS」的方式预处理出 dist 数组，
        dist[i] 含义为节点 i 到 0 号点的最短距离。

        一个数据服务器 i 往主服务器发送消息所消耗的时间为两节点之间的最短路径 dist[i]，
        而从发送消息到收到回复所需的时间为 di = 2 * dist[i]。

        同时每个数据服务器还存在时间间隔为 t = patience[i] 的重发动作，并且动作只有在第一次收到主服务的回复后才会停止。
        因此如果 di <= t，那么数据服务器不会发生重发动作，该节点活动停止时间点为 di；当 di > t，数据服务器将会发生重发动作，
        且最后一个数据包的发送时间为 (di - 1) / t * t，只有当最后一个数据包收到回复，该节点的所有活动才算停止，
        停止时间点为 (di - 1) / t * t + di。所有节点的活动停止时间点的最大值即是答案。
    */
    public static int networkBecomesIdle(int[][] edges, int[] patience) {
        // 存储头结点全部置为 -1
        Arrays.fill(he, -1);
        // 距离全部置为 INF
        Arrays.fill(dist, INF);
        int n = patience.length;
        // 存图结构
        for (int[] edge : edges) {
            add(edge[0], edge[1]);
            add(edge[1], edge[0]);
        }
        Deque<Integer> deque = new ArrayDeque<>();
        deque.addLast(0);
        dist[0] = 0;
        while (!deque.isEmpty()) {
            Integer t = deque.pollFirst();
            for (int i = he[t]; i != -1; i = ne[i]) {
                int j = e[i];
                if (dist[j] != INF) continue;
                // 距离 + 1
                dist[j] = dist[t] + 1;
                deque.addLast(j);
            }
        }
        int ans = 0;
        for (int i = 1; i < n; i++) {
            // 发送消息到收到回复所需的时间为 di = 2 * dist[i]
            int di = dist[i] * 2, t = patience[i];
            int cur = di <= t ? di : (di - 1) / t * t + di;
            if (cur > ans) ans = cur;
        }
        return ans + 1;
    }

    public static void add(int a, int b) {
        e[idx] = b;
        ne[idx] = he[a];
        he[a] = idx++;
    }

    public static void main(String[] args) {
        int[][] edges = {{0,1},{1,2}};
        int[] patience = {0,2,1};
        System.out.println(networkBecomesIdle(edges, patience));
    }

}
