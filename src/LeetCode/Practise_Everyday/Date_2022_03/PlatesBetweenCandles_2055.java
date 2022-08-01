package LeetCode.Practise_Everyday.Date_2022_03;

import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 10:22 2022/3/8
 * @Description: 给你一个长桌子，桌子上盘子和蜡烛排成一列。给你一个下标从 0 开始的字符串 s ，
 * 它只包含字符 '*' 和 '|' ，其中 '*' 表示一个 盘子 ，'|' 表示一支 蜡烛 。
 * 同时给你一个下标从 0 开始的二维整数数组 queries ，
 * 其中 queries[i] = [lefti, righti] 表示 子字符串 s[lefti...righti] （包含左右端点的字符）。
 * 对于每个查询，你需要找到 子字符串中 在 两支蜡烛之间 的盘子的 数目 。
 * 如果一个盘子在 子字符串中 左边和右边 都 至少有一支蜡烛，那么这个盘子满足在 两支蜡烛之间 。
 * <p>
 * 比方说，s = "||**||**|*" ，查询 [3, 8] ，表示的是子字符串 "*||**|" 。子字符串中在两支蜡烛之间的盘子数目为 2 ，
 * 子字符串中右边两个盘子在它们左边和右边 都 至少有一支蜡烛。
 * 请你返回一个整数数组 answer ，其中 answer[i] 是第 i 个查询的答案。
 */
public class PlatesBetweenCandles_2055 {
    /*
        前缀和的思想
     */
    public static int[] platesBetweenCandles(String s, int[][] queries) {
        int n = s.length();
        int[] ans = new int[queries.length];
        // 记录蜡烛出现的位置
        int[] next = new int[n];
        int[] prev = new int[n];
        int[] sum = new int[n];
        int idx = n;
        // 记录左边的蜡烛的位置
        for (int i = n - 1; i >= 0; i--) {
            if (s.charAt(i) == '|') {
                idx = i;
            }
            next[i] = idx;
        }
        idx = -1;
        // 记录右边蜡烛的位置
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '|') {
                idx = i;
            }
            prev[i] = idx;
        }
        // 前缀和
        // 如果第一个是盘子则是1
        sum[0] = s.charAt(0) == '*' ? 1 : 0;
        // 进行累加盘子数
        for (int i = 1; i < n; i++) {
            sum[i] = sum[i - 1] + (s.charAt(i) == '*' ? 1 : 0);
        }
        int index = 0;
        for (int[] tmp : queries) {
            int left = next[tmp[0]];
            int right = prev[tmp[1]];
            if (left < right && left < n && right >= 0) {
                // 两个蜡烛之间的盘子数
                ans[index] = sum[right] - sum[left];
            }
            index++;
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "**|*******************|**********************************************|************|*********|*****|*********************************************************************************************|***";
        int[][] queries = new int[][]{{31,96},{14,192},{0,180},{13,193},{12,192},{11,183},{12,189},{65,116},{55,160},{100,164},{6,183}};
        int[] ints = platesBetweenCandles(s, queries);
        System.out.println(Arrays.toString(ints));
    }
}
