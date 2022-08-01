package LeetCode.Practise_Everyday.Date_2022_07;

/**
 * @Author: Lil_boat
 * @Date: 2022/7/24 12:33
 * @Title: 公交站间的距离
 * @Description: 环形公交路线上有 n 个站，按次序从 0 到 n - 1 进行编号。
 * 我们已知每一对相邻公交站之间的距离，distance[i] 表示编号为 i 的车站和编号为 (i + 1) % n 的车站之间的距离。
 * 环线上的公交车都可以按顺时针和逆时针的方向行驶。
 * <p>
 * 返回乘客从出发点 start 到目的地 destination 之间的最短距离。
 * <p>
 * 链接：https://leetcode.cn/problems/distance-between-bus-stops
 */
public class D24_DistanceBetweenBusStops_1184 {
    public int distanceBetweenBusStops(int[] distance, int start, int destination) {
        // 记录距离
        int d1 = 0, d2 = 0;
        // 比较start和destination的大小
        int l = Math.min(start, destination);
        int r = Math.max(start, destination);
        for (int i = 0; i < distance.length; i++) {
            if (i >= l && i < r) {
                d1 += distance[i];
            } else {
                d2 += distance[i];
            }
        }
        return d1 < d2 ? d1 : d2;
    }
}
