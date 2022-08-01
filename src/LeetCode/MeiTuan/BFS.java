package LeetCode.MeiTuan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
/**
 * @Auther: Lil_boat
 * @Date: 13:38 2022/4/22
 * @Tile:  小团无路可逃
 * @Description: 小团惹小美生气了，小美要去找小团“讲道理”。
 * 小团望风而逃，他们住的地方可以抽象成一棵有n个结点的树，小美位于 x 位置，小团位于 y 位置。
 * 小团和小美每个单位时间内都可以选择不动或者向相邻的位置转移，很显然最终小团会无路可逃，
 * 只能延缓一下被“讲道理”的时间，请问最多经过多少个单位时间后，小团会被追上。
 */
public class BFS {

    private static List<List<Integer>> graph = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        // 获取输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] line = br.readLine().split(" ");
        // 结点的数量
        int n = Integer.parseInt(line[0]);
        // 小美的位置
        int x = Integer.parseInt(line[1]);
        // 小团的位置
        int y = Integer.parseInt(line[2]);
        // 构建图
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        // 建立图的关系
        for (int i = 0; i < n - 1; i++) {
            line = br.readLine().split(" ");
            graph.get(Integer.parseInt(line[0])).add(Integer.parseInt(line[1]));
            graph.get(Integer.parseInt(line[1])).add(Integer.parseInt(line[0]));
        }

        // 存储距离关系
        // key ---> 存储节点
        // value ---> 存储距离
        Map<Integer, Integer> distanceX = new HashMap<>();
        Map<Integer, Integer> distanceY = new HashMap<>();

        // bfs求出小美到每个点的距离
        bfs(distanceX, new boolean[n + 1], x);
        // bfs求出小团到每个点的距离
        bfs(distanceY, new boolean[n + 1], y);

        int maxDis = 0;
        for (Map.Entry<Integer, Integer> entry : distanceX.entrySet()) {
            int node = entry.getKey();
            int disX = entry.getValue();
            int disY = distanceY.get(node);
            if (disX > disY) maxDis = Math.max(maxDis, disX);
        }
        System.out.print(maxDis);
    }

    private static void bfs(Map<Integer, Integer> distance, boolean[] visited, int pos) {
        // 建立要访问的节点
        Queue<Integer> queue = new LinkedList<>();
        queue.add(pos);
        int dis = 0;
        while (!queue.isEmpty()) {
            for (int i = queue.size(); i > 0; i--) {
                int node = queue.poll();
                distance.put(node, dis);
                visited[node] = true;
                for (int adjNode : graph.get(node)) {
                    // 如果没有访问过，则加入访问队列
                    if (!visited[adjNode]) queue.offer(adjNode);
                }
            }
            // 距离 + 1
            dis++;
        }
    }

}
