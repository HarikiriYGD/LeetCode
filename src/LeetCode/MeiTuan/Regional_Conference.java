package LeetCode.MeiTuan;

import java.io.*;
import java.util.*;

public class Regional_Conference {

    // n 表示区域的数量 k 表示不能超过的级别
    private static int n, k;
    // 构建图
    private static Map<Integer, List<Integer>> adj = new HashMap<>();
    // 存储每个区域的级别
    private static int[] level;
    // 记录访问过的节点
    private static boolean[] vis;
    private static int MOD = 1000000007;


    /*
    题目的难点在于「级别最高的和级别最低的相差不超过 k」：
        我们可以对『以每个节点开始的连通图』求和，并限定在DFS时，下一个节点的『level』必须大于起始节点，这样每次只需要比较『当前』和『起始』两个节点的level；
        此外，如果level相等，例如题目给定的示例，我们可以限定下一节点的『序号』必须大于起始节点，避免重复计算。

        在计算『ans』时，我们可以发现其递推公式为 ans = ans + ans * dfs(next, start)，具体编码时加上取模操作即可。
     */
    public static void main(String[] args) throws IOException {
        // 获取输入流
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             // 获取输出流
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            // 获取区域的数量和不能超过的级别
            String[] s = br.readLine().split(" ");
            n = Integer.parseInt(s[0]);
            k = Integer.parseInt(s[1]);
            // 构建图的结构
            for (int i = 0; i < n - 1; i++) {
                s = br.readLine().split(" ");
                int x = Integer.parseInt(s[0]);
                int y = Integer.parseInt(s[1]);
                adj.putIfAbsent(x, new ArrayList<>());
                adj.get(x).add(y);
                adj.putIfAbsent(y, new ArrayList<>());
                adj.get(y).add(x);
            }
            // 获取每一层的级别
            s = br.readLine().split(" ");
            // 给每一层级赋值
            level = new int[n + 1];
            vis = new boolean[n + 1];
            for (int i = 1; i <= n; i++) {
                level[i] = Integer.parseInt(s[i - 1]);
            }
            // 枚举从一个点开始的所有连通图
            long ans = 0;
            for (int i = 1; i <= n; i++) {
                ans = (ans + dfs(i, i)) % MOD;
            }
            bw.write(String.valueOf(ans));
        }
    }

    private static long dfs(int cur, int start) {
        vis[cur] = true;
        long ans = 1;
        for (int next : adj.get(cur)) {
            if (vis[next]) continue;
            // 只找level比自己大的或者level相等，但是序号比自己大的，避免重复计算
            if ((level[next] > level[start] && level[next] - level[start] <= k) || (level[next] == level[start] && next > start)) {
                ans = (ans + ans * (dfs(next, start))) % MOD;
            }
        }
        vis[cur] = false;
        return ans;
    }
}
