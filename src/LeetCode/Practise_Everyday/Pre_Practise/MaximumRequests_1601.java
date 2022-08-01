package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Lil_boat
 * @Date: 11:03 2022/2/28
 * @Description: 我们有 n 栋楼，编号从 0 到 n - 1 。每栋楼有若干员工。由于现在是换楼的季节，部分员工想要换一栋楼居住。
 * <p>
 * 给你一个数组 requests ，其中 requests[i] = [fromi, toi] ，表示一个员工请求从编号为 fromi 的楼搬到编号为 toi 的楼。
 * <p>
 * 一开始 所有楼都是满的，所以从请求列表中选出的若干个请求是可行的需要满足 每栋楼员工净变化为 0 。
 * 意思是每栋楼 离开 的员工数目 等于 该楼 搬入 的员工数数目。比方说 n = 3 且两个员工要离开楼 0 ，
 * 一个员工要离开楼 1 ，一个员工要离开楼 2 ，如果该请求列表可行，应该要有两个员工搬入楼 0 ，一个员工搬入楼 1 ，一个员工搬入楼 2 。
 * <p>
 * 请你从原请求列表中选出若干个请求，使得它们是一个可行的请求列表，并返回所有可行列表中最大请求数目。
 */
public class MaximumRequests_1601 {

    public static int maximumRequests(int n, int[][] requests) {
        int[] cnt = new int[requests.length];
        Map<Integer, Long> map = new HashMap<>();
        return backTrack(cnt, requests, 0, 0);
    }

    public static int backTrack(int[] cnt, int[][] requests, int idx, int picked) {
        // 边界判断条件
        if (idx == requests.length) {
            for (int c : cnt) {
                if (c != 0) return 0;
                return picked;
            }
        }
        int ans = 0;
        // 离开的宿舍楼 空位 + 1
        cnt[requests[idx][0]]++;
        // 入住的宿舍楼 空位 - 1
        cnt[requests[idx][1]]--;
        ans = backTrack(cnt, requests, idx + 1, picked + 1);
        // 递归完恢复数据
        cnt[requests[idx][0]]--;
        cnt[requests[idx][1]]++;
        // 返回当前最大满足需求的最大值
        return Math.max(ans, Math.max(ans, backTrack(cnt, requests, idx + 1, picked)));
    }
}

