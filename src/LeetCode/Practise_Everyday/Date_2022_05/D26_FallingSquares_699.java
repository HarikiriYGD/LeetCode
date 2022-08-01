package LeetCode.Practise_Everyday.Date_2022_05;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Lil_boat
 * @Date: 10:15 2022/5/26
 * @Tile: 掉落的方块
 * @Description: 在无限长的数轴（即 x 轴）上，我们根据给定的顺序放置对应的正方形方块。
 * 第 i 个掉落的方块（positions[i] = (left, side_length)）是正方形，
 * 其中 left 表示该方块最左边的点位置(positions[i][0])，
 * side_length 表示该方块的边长(positions[i][1])。
 * <p>
 * 每个方块的底部边缘平行于数轴（即 x 轴），并且从一个比目前所有的落地方块更高的高度掉落而下。
 * 在上一个方块结束掉落，并保持静止后，才开始掉落新方块。
 * 方块的底边具有非常大的粘性，并将保持固定在它们所接触的任何长度表面上（无论是数轴还是其他方块）。
 * 邻接掉落的边不会过早地粘合在一起，因为只有底边才具有粘性。
 * <p>
 * 返回一个堆叠高度列表 ans 。每一个堆叠高度 ans[i] 表示在通过 positions[0], positions[1], ..., positions[i] 表示的方块掉落结束后，
 * 目前所有已经落稳的方块堆叠的最高高度。
 * <p>
 * 链接：https://leetcode.cn/problems/falling-squares
 */
public class D26_FallingSquares_699 {

    int N = (int) 1e9;

    class Node {
        // ls和rs分别代表当前区间的左右节点
        Node ls, rs;
        // val 代表当前区间的最大高度，add为懒标记
        int val, add;
    }

    Node root = new Node();

    void update(Node node, int lc, int rc, int l, int r, int x) {
        if (l <= lc && rc <= r) {
            node.add = x;
            node.val = x;
            return;
        }
        pushDown(node);
        int mid = lc + rc >> 1;
        if (l <= mid) update(node.ls, lc, mid, l, r, x);
        if (r > mid) update(node.rs, mid + 1, rc, l, r, x);
        pushUp(node);
    }

    int query(Node node, int lc, int rc, int l, int r) {
        if (l <= lc && rc <= r) return node.val;
        pushDown(node);
        int mid = lc + rc >> 1, ans = 0;
        if (l <= mid) ans = query(node.ls, lc, mid, l, r);
        if (r > mid) ans = Math.max(ans, query(node.rs, mid + 1, rc, l, r));
        return ans;
    }


    private void pushDown(Node node) {
        if (node.ls == null) node.ls = new Node();
        if (node.rs == null) node.rs = new Node();
        if (node.add == 0) return;
        node.ls.add = node.add;
        node.rs.add = node.add;
        node.ls.val = node.add;
        node.rs.val = node.add;
        node.add = 0;
    }

    private void pushUp(Node node) {
        node.val = Math.max(node.ls.val, node.rs.val);
    }

    public List<Integer> fallingSquares(int[][] positions) {
        int n = positions.length;
        if (n == 0 || positions == null) return new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        for (int[] info : positions) {
            int x = info[0], height = info[1], cur = query(root, 0, N, x, x + height - 1);
            update(root, 0, N, x, x + height - 1, cur + height);
            ans.add(root.val);
        }
        return ans;
    }


}
