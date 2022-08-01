package LeetCode.Practise_Everyday.Date_2022_05;

import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 11:28 2022/5/20
 * @Tile: 寻找右区间
 * @Description: 给你一个区间数组 intervals ，其中 intervals[i] = [starti, endi] ，且每个 starti 都 不同 。
 * 区间 i 的 右侧区间 可以记作区间 j ，并满足 startj >= endi ，且 startj 最小化 。
 * 返回一个由每个区间 i 的 右侧区间 在 intervals 中对应下标组成的数组。如果某个区间 i 不存在对应的 右侧区间 ，则下标 i 处的值设为 -1 。
 * <p>
 * 链接：https://leetcode.cn/problems/find-right-interval
 */
public class D20_FindRightInterval_436 {
    /*
        为了方便，我们称 intervals 为 its。
        对于每个 its[i] 而言，我们需要在所有满足its[j][0] ⩾ its[i][1]」中找到 its[j][0] 值最小的下标 jj，并将其记为 ans[i]。
        对于一个特定的 its[i] 而言，其右端点固定，并且我们只关心目标位置的左端点。

        因此我们可以构造一个记录区间左端点的数组 clone，并将其进行排序，同时为了记录每个左端点来自于原序列中的那个下标，
        还需要额外记录原序列下标，即以 (start, idx)二元组的形式进行转存，并根据 start 排序。
        然后从前往后处理每个 its[i]its[i]，运用「二分」在 clone 中找到第一个满足左端点 starts 大于等于 its[i][1] 的成员 clone[j]，
        将其 clone[j][1] 即是 its[i] 的最右区间。

     */
    public int[] findRightInterval(int[][] intervals) {
        int n = intervals.length;
        int[] ans = new int[n];
        int[][] clone = new int[n][2];
        for (int i = 0; i < n; i++) {
            // 存储左端点和索引位置
            clone[i] = new int[]{intervals[i][0],i};
        }
        // 根据左端点排序
        Arrays.sort(clone, (a, b) -> a[0] - b[0]);
        for (int i = 0; i < n; i++) {
            int l = 0, r = n - 1;
            while (l < r) {
                int mid = l + r >> 1;
                if (clone[mid][0] >= intervals[i][1])r = mid;
                else  l = mid + 1;
            }
            ans[i] = clone[r][0] >= intervals[i][1] ? clone[r][1] : -1;
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] nums = {{3,4},{2,3},{1,2}};
        D20_FindRightInterval_436 t = new D20_FindRightInterval_436();
        int[] rightInterval = t.findRightInterval(nums);
        System.out.println(Arrays.toString(rightInterval));
    }
}
