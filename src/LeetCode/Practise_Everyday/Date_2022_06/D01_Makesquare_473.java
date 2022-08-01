package LeetCode.Practise_Everyday.Date_2022_06;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 10:47 2022/6/1
 * @Tile: 火柴拼正方形
 * @Description: 你将得到一个整数数组 matchsticks ，其中 matchsticks[i] 是第 i 个火柴棒的长度。
 * 你要用 所有的火柴棍 拼成一个正方形。你 不能折断 任何一根火柴棒，但你可以把它们连在一起，而且每根火柴棒必须 使用一次 。
 * <p>
 * 如果你能使这个正方形，则返回 true ，否则返回 false 。
 * <p>
 * 链接：https://leetcode.cn/problems/matchsticks-to-square
 */
public class D01_Makesquare_473 {

    public boolean makeSquare(int[] matchsticks) {
        // 对整数数组求和
        int sum = 0;
        for (int x : matchsticks) {
            sum += x;
        }
        // 如果边长的总长度不是4的倍数，则肯定不能构成正方形
        if (sum % 4 != 0) return false;
        // 每条边的长度
        int len = sum / 4;
        Arrays.sort(matchsticks);
        // 将数组的边长从大到小排序
        for (int i = 0, j = matchsticks.length - 1; i < j; i++, j--) {
            int temp = matchsticks[i];
            matchsticks[i] = matchsticks[j];
            matchsticks[j] = temp;
        }
        // 存储边长数组
        int[] edges = new int[4];
        // 回溯
        return dfs(0, matchsticks, edges, len);
    }

    private boolean dfs(int index, int[] matchsticks, int[] edges, int len) {
        // 边界判断条件
        if (index == matchsticks.length) return true;
        for (int i = 0; i < edges.length; i++) {
            // 每条边 + matchsticks[index]
            edges[i] += matchsticks[index];
            // 判断是否大于最大边长
            if (edges[i] <= len && dfs(index + 1, matchsticks, edges, len)) return true;
            // 回溯
            edges[i] -= matchsticks[index];
        }
        return false;
    }

}
