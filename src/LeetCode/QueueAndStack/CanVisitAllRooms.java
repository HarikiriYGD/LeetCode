package LeetCode.QueueAndStack;

import java.util.ArrayList;
import java.util.List;

public class CanVisitAllRooms {
    private static int count = 0; // 记录访问过的房间数量
    private static boolean[] visited;

    /**
     * 能否访问完所有的房间
     *
     * @param rooms 房间数
     * @return 返回是否能访问所有房间
     */
    public static boolean canVisitAllRooms(List<List<Integer>> rooms) {
        int N = rooms.size(); // 房间数
        visited = new boolean[N];
        dfs(rooms, 0);
        return count == N; // 判断是否已经去过所有房间,如果和房间数相等则返回true
    }

    /**
     * 深度遍历搜索
     *
     * @param rooms 房间数和里面的钥匙
     * @param i     当前访问的房间
     */
    private static void dfs(List<List<Integer>> rooms, int i) {
        visited[i] = true; // 将访问过的房间置为true
        count++; // 房间数+1
        for (int j : rooms.get(i)) {
            if (!visited[j]) {
                dfs(rooms, j);
            }
        }
    }


    public static void main(String[] args) {
        List<List<Integer>> rooms = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            List<Integer> b = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                b.add(j);
            }
            rooms.add(b);
        }

        boolean res = canVisitAllRooms(rooms);
        System.out.println(res);
        System.out.println(rooms);
    }
}
