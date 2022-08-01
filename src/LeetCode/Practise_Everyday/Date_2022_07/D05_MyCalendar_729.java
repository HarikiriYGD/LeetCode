package LeetCode.Practise_Everyday.Date_2022_07;

import LeetCode.Practise_Everyday.Date_2022_06.D05_Solution_478;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: Lil_boat
 * @Date: 13:24 2022/7/5
 * @Tile: 我的日程安排表 I
 * @Description: 实现一个 MyCalendar 类来存放你的日程安排。如果要添加的日程安排不会造成 重复预订 ，则可以存储这个新的日程安排。
 * 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生 重复预订 。
 * 日程可以用一对整数 start 和 end 表示，这里的时间是半开区间，即 [start, end), 实数 x 的范围为，  start <= x < end 。
 * <p>
 * 实现 MyCalendar 类：
 * MyCalendar() 初始化日历对象。
 * boolean book(int start, int end) 如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true 。
 * 否则，返回 false 并且不要将该日程安排添加到日历中。
 * <p>
 * 链接：https://leetcode.cn/problems/my-calendar-i
 */


public class D05_MyCalendar_729 {

    class Node {
        // 左右孩子节点
        Node left, right;
        // 当前节点值和懒标记
        int val, add;
    }

    private int N = (int) 1e9;
    Node root = new Node();


    public D05_MyCalendar_729() {

    }

    public boolean book(int start, int end) {
        // 如果不存在重复预订 查询返回的结果值应该为0
        if (query(root, 0, N, start, end - 1) != 0) {
            return false;
        }
        update(root, 0, N, start, end - 1, 1);
        return true;
    }

    public void update(Node node, int start, int end, int l, int r, int val) {
        // 找到满足要求的区间
        if (l <= start && r >= end) {
            node.val += val;
            node.add += val;
            return;
        }
        pushDown(node);
        int mid = (start + end) >> 1;
        if (l <= mid) {
            update(node.left, start, mid, l, r, val);
        }
        if (r > mid) {
            update(node.right, mid + 1, end, l, r, val);
        }
        pushUp(node);

    }

    public int query(Node node, int start, int end, int l, int r) {
        if (l <= start && end <= r) {
            return node.val;
        }
        pushDown(node);
        int mid = start + end >> 1, ans=0;
        if (l <= mid) {
            ans = query(node.left, start, mid, l, r);
        }
        if (r > mid) {
            ans = Math.max(ans, query(node.right, mid + 1, end, l, r));
        }
        return ans;
    }

    /**
     * 向上更新
     *
     * @param node
     */
    private void pushUp(Node node) {
        // 每个节点存的是当前区间的最大值
        node.val = Math.max(node.left.val, node.right.val);
    }

    /**
     * 下推懒标记的函数
     *
     * @param node
     */
    private void pushDown(Node node) {
        if (node.left == null) {
            node.left = new Node();
        }
        if (node.right == null) {
            node.right = new Node();
        }
        // 如果懒标记为0，表示没有标记
        if (node.add == 0) {
            return;
        }
        // 当前节点的值
        node.left.val += node.add;
        node.right.val += node.add;
        // 把标记下推给孩子节点
        // 对区间进行「加减」的更新操作，下推懒惰标记时需要累加起来，不能直接覆盖
        node.left.add += node.add;
        node.right.add += node.add;
        // 取消当前节点标记
        node.add = 0;
    }


    /*private List<int[]> list = new ArrayList<>();

    /**
     * 模拟的方法
     *
     * @param start
     * @param end
     * @return

    public boolean book(int start, int end) {
        end--;
        for (int[] pre : list) {
            // 存在重复预订就两种情况
            // 要么 start的时间比上一个预订的结束时间晚
            // 要么 end的时间比上一个预订的开始时间晚
            if (start > pre[1] || end < pre[0]) {
                continue;
            }
            return false;
        }
        list.add(new int[]{start, end});
        return true;
    }*/

    public static void main(String[] args) {
        D05_MyCalendar_729 t = new D05_MyCalendar_729();
        t.book(10, 20);
        t.book(15, 25);
        t.book(20, 30);
    }

}
