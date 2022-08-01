package LeetCode.Practise_Everyday.Date_2022_07;

/**
 * @Author: Lil_boat
 * @Date: 2022/7/19 17:39
 * @Title: 我的日程安排表 II
 * @Description: 实现一个 MyCalendar 类来存放你的日程安排。如果要添加的时间内不会导致三重预订时，则可以存储这个新的日程安排。
 * MyCalendar 有一个 book(int start, int end)方法。它意味着在 start 到 end 时间内增加一个日程安排，
 * 注意，这里的时间是半开区间，即 [start, end), 实数 x 的范围为，  start <= x < end。
 * <p>
 * 当三个日程安排有一些时间上的交叉时（例如三个日程安排都在同一时间内），就会产生三重预订。
 * 每次调用 MyCalendar.book方法时，如果可以将日程安排成功添加到日历中而不会导致三重预订，返回 true。
 * 否则，返回 false 并且不要将该日程安排添加到日历中。
 * 请按照以下步骤调用MyCalendar 类: MyCalendar cal = new MyCalendar(); MyCalendar.book(start, end)
 * <p>
 * 链接：https://leetcode.cn/problems/my-calendar-ii
 */
public class D19_MyCalendarTwo_731 {

    class Node {
        // 左右孩子节点
        Node left, right;
        // 节点值
        int val;
        //懒标记
        int add;
    }

    private int N = (int) 10e9;
    Node root = new Node();

    /**
     * 插入节点操作
     *
     * @param node
     * @param start 最左起点
     * @param end   最右起点
     * @param l     插入左区间点
     * @param r     插入右区间点
     * @param val   插入节点值
     */
    void update(Node node, int start, int end, int l, int r, int val) {
        if (l <= start && end <= r) {
            node.val += val;
            node.add += val;
            return;
        }
        pushDown(node);
        // 二分查找并更新
        int mid = (start + end) >> 1;
        if (l <= mid) {
            update(node.left, start, mid, l, r, val);
        }
        if (r > mid) {
            update(node.right, mid + 1, end, l, r, val);
        }
        pushUp(node);
    }

    int query(Node node, int start, int end, int l, int r) {
        if (l <= start && end <= r) {
            return node.val;
        }
        pushDown(node);
        int mid = (start + end) >> 1, ans = 0;
        if (l <= mid) {
            ans = query(node.left, start, mid, l, r);
        }
        if (r > mid) {
            ans = Math.max(ans, query(node.right, mid + 1, end, l, r));
        }
        return ans;
    }

    /**
     * 动态开点
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
        if (node.add == 0) return;
        node.left.val += node.add;
        node.right.val += node.add;
        node.left.add += node.add;
        node.right.add += node.add;
        node.add = 0;
    }

    private void pushUp(Node node) {
        node.val = Math.max(node.left.val, node.right.val);
    }


    public D19_MyCalendarTwo_731() {

    }

    public boolean book(int start, int end) {
        // 如果已经存在两次预订表明不能再次预订
        if (query(root, 0, N, start, end - 1) == 2) {
            return false;
        }
        // 更新节点值
        update(root, 0, N, start, end - 1, 1);
        return true;
    }

    public static void main(String[] args) {
        D19_MyCalendarTwo_731 t = new D19_MyCalendarTwo_731();
        System.out.println(t.book(10, 20));
        System.out.println(t.book(50, 60));
        System.out.println(t.book(10, 40));
        System.out.println(t.book(5, 15));
        System.out.println(t.book(5, 10));
        System.out.println(t.book(25, 55));
    }

}
