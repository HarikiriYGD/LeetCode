package LeetCode.Practise_Everyday.Date_2022_07;

import java.util.PriorityQueue;

/**
 * @Author: Lil_boat
 * @Date: 18:43 2022/7/2
 * @Tile: 最低加油次数
 * @Description: 汽车从起点出发驶向目的地，该目的地位于出发位置东面 target 英里处。
 * 沿途有加油站，每个 station[i] 代表一个加油站，它位于出发位置东面 station[i][0] 英里处，并且有 station[i][1] 升汽油。
 * 假设汽车油箱的容量是无限的，其中最初有 startFuel 升燃料。它每行驶 1 英里就会用掉 1 升汽油。
 * 当汽车到达加油站时，它可能停下来加油，将所有汽油从加油站转移到汽车中。
 * 为了到达目的地，汽车所必要的最低加油次数是多少？如果无法到达目的地，则返回 -1 。
 * 注意：如果汽车到达加油站时剩余燃料为 0，它仍然可以在那里加油。如果汽车到达目的地时剩余燃料为 0，仍然认为它已经到达目的地。
 * <p>
 * 链接：https://leetcode.cn/problems/minimum-number-of-refueling-stops
 */
public class D02_MinRefuelStops_871 {

    /*
        贪心 + 优先队列
        贪心：每次选取优先队列中加油站油最多的那一家，如果选取完所有的油量都不能抵达终点，则返回-1.
     */
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int ans = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>((a, b) -> b - a);
        for (int i = 0; i <= stations.length; i++) {
            int x = 0, y = 0;
            if (i == stations.length) {
                x = target;
            }else {
                x = stations[i][0];
                y = stations[i][1];
            }
            while (x > startFuel){
                ans++;
                Integer k = queue.poll();
                if (k == null) {
                    return -1;
                }
                startFuel+=k;
            }
            queue.add(y);
        }
        return ans;
    }

}
