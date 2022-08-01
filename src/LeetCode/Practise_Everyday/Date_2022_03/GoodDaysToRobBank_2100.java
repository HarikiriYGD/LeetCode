package LeetCode.Practise_Everyday.Date_2022_03;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Lil_boat
 * @Date: 9:35 2022/3/6
 * @Description: 你和一群强盗准备打劫银行。给你一个下标从 0 开始的整数数组 security ，其中 security[i] 是第 i 天执勤警卫的数量。
 * 日子从 0 开始编号。同时给你一个整数 time 。
 * <p>
 * 如果第 i 天满足以下所有条件，我们称它为一个适合打劫银行的日子：
 * 第 i 天前和后都分别至少有 time 天。
 * 第 i 天前连续 time 天警卫数目都是非递增的。
 * 第 i 天后连续 time 天警卫数目都是非递减的。
 * 更正式的，第 i 天是一个合适打劫银行的日子当且仅当：
 * security[i - time] >= security[i - time + 1] >= ... >= security[i] <= ... <= security[i + time - 1] <= security[i + time].
 * <p>
 * 请你返回一个数组，包含 所有 适合打劫银行的日子（下标从 0 开始）。返回的日子可以 任意 顺序排列。
 */
public class GoodDaysToRobBank_2100 {
    /*
        为了方便，我们令 len 为 security 长度。
        根据题目对「适合打劫银行的日子」的定义，首先我们可以确定答案落在 [time, n - time) 范围内，
        另外规定了「适合打劫银行的日子」左右侧需要满足「非递增」和「非递减」的性质。
        首先我们可以预处理 g 数组，g[i] 代表当前时间 security[i] 与前一时间 security[i - 1] 的大小关系，
            当 security[i] > security[i - 1] 时有 g[i] = 1，
            当 security[i] < security[i - 1] 时有 g[i] = -1，
            否则 g[i] = 0，另外我们特别的有 g[0] = 0 的边界情况。
        然后我们对 g 应用「前缀和」思想：使用 a 数组记录前缀 1 的数量，使用 b 数组记录前缀 -1 的数量。
        最终，如果 i 为「适合打劫银行的日子」，那么满足 time <= i < n - time，
        且满足 (i - time, i] 范围前缀 1 数量为 0，(i, i + time] 范围前缀 -1 数量为 0。
     */
    public static List<Integer> goodDaysToRobBank(int[] security, int time) {
        int len = security.length;
        int[] g = new int[len];
        for (int i = 1; i < len; i++) {
            if (security[i] == security[i - 1]) continue;
            g[i] = security[i] > security[i - 1] ? 1 : -1;
        }
        int[] a = new int[len + 1], b = new int[len + 1];
        for (int i = 1; i <= len; i++) {
            a[i] = a[i - 1] + (g[i - 1] == 1 ? 1 : 0);
        }
        for (int i = 1; i <= len; i++) {
            b[i] = b[i - 1] + (g[i - 1] == -1 ? 1 : 0);
        }
        List<Integer> ans = new ArrayList<>();
        for (int i = time; i < len - time; i++) {
            int c1 = a[i + 1] - a[i + 1 - time], c2 = b[i + 1 + time] - b[i + 1];
            if (c1 == 0 && c2 == 0) ans.add(i);
        }
        StringBuilder sb = new StringBuilder();
        return ans;
    }

    /*
        双指针的方式
     */
    public static List<Integer> goodDaysToRobBank_Double_Pointer(int[] security, int time) {
        int n = security.length;
        List<Integer> ans = new ArrayList<>();
        // 如果time为0 说明任何一天都适合打劫
        if (time == 0) {
            for (int i = 0; i < n; i++) {
                ans.add(i);
            }
        }
        int left = 0;
        int right = 0;
        for (int i = 1; i < n - time; i++) {
            // 如果满足非递增的情况下，left++
            if (security[i] <= security[i - 1]) left++;
                // 否则 left = 0
            else left = 0;
            // 判断右边界的情况
            if (security[i + time] >= security[i + time - 1]) right++;
            else right = 0;
            // 判断前后两天是否都是大于time
            if (left >= time && right >= time)ans.add(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] security = {5,3,3,3,5,6,2};
        List<Integer> list = goodDaysToRobBank(security, 2);
        List<Integer> list1 = goodDaysToRobBank_Double_Pointer(security, 2);
        System.out.println(list.equals(list1));
    }
}
