package LeetCode.Practise_Everyday.Date_2022_06;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Auther: Lil_boat
 * @Date: 10:56 2022/6/7
 * @Tile: 我的日程安排表 III
 * @Description: 当 k 个日程安排有一些时间上的交叉时（例如 k 个日程安排都在同一时间内），就会产生 k 次预订。
 * 给你一些日程安排 [start, end) ，请你在每个日程安排添加后，返回一个整数 k ，表示所有先前日程安排会产生的最大 k 次预订。
 * 实现一个 MyCalendarThree 类来存放你的日程安排，你可以一直添加新的日程安排。
 * <p>
 * MyCalendarThree() 初始化对象。
 * int book(int start, int end) 返回一个整数 k ，表示日历中存在的 k 次预订的最大值。
 * <p>
 * 链接：https://leetcode.cn/problems/my-calendar-iii
 */
public class D06_MyCalendarThree_732 {

    class Node {
        int ls, rs, add, max;
    }

    int N = (int) 1e9, M = 4 * 400 * 20, cnt = 1;
    Node[] tr = new Node[M];

    void update(int u, int lc, int rc, int l, int r, int v) {
        if (l <= lc && rc <= r) {
            tr[u].add += v;
            tr[u].max += v;
            return;
        }
        lazyCreate(u);
        pushdown(u);
        int mid = lc + rc >> 1;
        if (l <= mid) update(tr[u].ls, lc, mid, l, r, v);
        if (r > mid) update(tr[u].rs, mid + 1, rc, l, r, v);
        pushup(u);
    }

    int query(int u, int lc, int rc, int l, int r) {
        if (l <= lc && rc <= r) return tr[u].max;
        lazyCreate(u);
        pushdown(u);
        int mid = lc + rc >> 1, ans = 0;
        if (l <= mid) ans = query(tr[u].ls, lc, mid, l, r);
        if (r > mid) ans = Math.max(ans, query(tr[u].rs, mid + 1, rc, l, r));
        return ans;
    }

    void lazyCreate(int u) {
        if (tr[u] == null) tr[u] = new Node();
        if (tr[u].ls == 0) {
            tr[u].ls = ++cnt;
            tr[tr[u].ls] = new Node();
        }
        if (tr[u].rs == 0) {
            tr[u].rs = ++cnt;
            tr[tr[u].rs] = new Node();
        }
    }

    void pushdown(int u) {
        tr[tr[u].ls].add += tr[u].add;
        tr[tr[u].rs].add += tr[u].add;
        tr[tr[u].ls].max += tr[u].add;
        tr[tr[u].rs].max += tr[u].add;
        tr[u].add = 0;
    }

    void pushup(int u) {
        tr[u].max = Math.max(tr[tr[u].ls].max, tr[tr[u].rs].max);
    }

    public int book(int start, int end) {
        update(1, 1, N + 1, start + 1, end, 1);
        return query(1, 1, N + 1, 1, N + 1);
    }

}
