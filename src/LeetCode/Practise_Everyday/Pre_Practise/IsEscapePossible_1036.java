package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @Auther: Lil_boat
 * @Date: 10:57 2022/1/11
 * @Description: 在一个 10^6 x 10^6 的网格中，每个网格上方格的坐标为 (x, y) 。
 * 现在从源方格 source = [sx, sy] 开始出发，意图赶往目标方格 target = [tx, ty] 。
 * 数组 blocked 是封锁的方格列表，其中每个 blocked[i] = [xi, yi] 表示坐标为 (xi, yi) 的方格是禁止通行的。
 * 每次移动，都可以走到网格中在四个方向上相邻的方格，只要该方格 不 在给出的封锁列表 blocked 上。同时，不允许走出网格。
 * 只有在可以通过一系列的移动从源方格 source 到达目标方格 target 时才返回 true。否则，返回 false。
 */
public class IsEscapePossible_1036 {
    /**
     * 核心思想：因为网格很大，但是blocked的包围圈很小，所以访问到一定的上限次数之后必定不可能包围target
     * 这时就可以提前跳出！！！
     */
    /*
    方法二：离散化 + 广度优先搜索
        思路与算法:
            我们也可以借助离散化技巧将网格「压缩」成一个规模较小的但等价的新网格，并在新网格上进行常规的广度优先搜索。
            以网格的每一行为例，可以发现，不同的行坐标只有：
                1. 障碍所在的行，最多有 n 个；
                2. source 和 target 所在的行，最多有 2 个。
                3. 网格的上下边界（即 -1 和 10^6），有 2 个。
            因此不同的行坐标最多只有 n+4 个，我们可以对行坐标进行离散化，具体的规则如下：
                我们将行坐标进行升序排序；将上边界离散化为 -1。上边界是排序后的第 0 个行坐标；
                如果排序后的第 i 个行坐标与第 i-1 个行坐标相同，那么它们离散化之后的值也相同；
                如果排序后的第 i 个行坐标与第 i-1 个行坐标相差 1，那么它们离散化之后的值也相差 1;
                如果排序后的第 i 个行坐标与第 i-1 个行坐标相差超过 1，那么它们离散化之后的值相差 2。
            这样的正确性在于：在离散化前，如果两个行坐标本身相邻，那么在离散化之后它们也必须相邻。
            如果它们不相邻，可以把它们之间间隔的若干行直接「压缩」成一行，即行坐标相差 2。
            对于列坐标的离散化方法也是如此。在离散化完成之后，新的网格的规模不会超过 2(n+4) × 2(n+4)，进行广度优先搜索需要的时间是可接受的。
     */
    static private boolean[][] cameBefore = new boolean[650][650];
    static private int r = 0;
    static private int k = 0;

    public static boolean isEscapePossible(int[][] blocked, int[] source, int[] target) {
        int max = (int) 1e6;
        TreeSet<Integer> row = new TreeSet<>();
        TreeSet<Integer> col = new TreeSet<>();
        row.add(source[0]);
        row.add(target[0]);
        col.add(source[1]);
        col.add(target[1]);
        for (int i = 0; i < blocked.length; i++) {
            row.add(blocked[i][0]);
            if (blocked[i][0] > 0) {
                row.add(blocked[i][0] - 1);
            }
            if (blocked[i][0] < max - 1) {
                row.add(blocked[i][0] + 1);
            }
            col.add(blocked[i][1]);
            if (blocked[i][1] > 0) {
                col.add(blocked[i][1] - 1);
            }
            if (blocked[i][1] < max - 1) {
                col.add(blocked[i][1] + 1);
            }
        }
        Map<Integer, Integer> map1 = new HashMap<>();//行映射
        Map<Integer, Integer> map2 = new HashMap<>();//列映射
        for (Integer a : row) {
            map1.put(a, r);
            r++;
        }
        for (Integer a : col) {
            map2.put(a, k);
            k++;
        }
        for (int i = 0; i < blocked.length; i++) {
            cameBefore[map1.get(blocked[i][0])][map2.get(blocked[i][1])] = true;
        }
        findPath(map1.get(source[0]), map2.get(source[1]));
        return cameBefore[map1.get(target[0])][map2.get(target[1])];
    }

    public static void findPath(int x, int y) {
        if (x < 0 || y < 0 || x == r || y == k || cameBefore[x][y]) {
            return;
        }
        cameBefore[x][y] = true;
        findPath(x + 1, y);
        findPath(x - 1, y);
        findPath(x, y + 1);
        findPath(x, y - 1);
    }

    public static boolean dfs(int[][] blocked, int row, int col, int i, int j, int tx, int ty, int[][] visited) {
        for (int[] b : blocked) {
            if (b[0] == i && b[1] == j) return false;
        }
        // 边界条件
        if (i < 0 || i >= row || j < 0 || j >= col || visited[i][j] == 1) return false;
        visited[i][j] = 1;
        if (i == tx && j == ty) return true;
        return dfs(blocked, row, col, i + 1, j, tx, ty, visited) ||
                dfs(blocked, row, col, i - 1, j, tx, ty, visited) ||
                dfs(blocked, row, col, i, j + 1, tx, ty, visited) ||
                dfs(blocked, row, col, i, j - 1, tx, ty, visited);
    }

    public static void main(String[] args) {
        int[][] blocked = {{0, 1}, {1, 0}};
        int[] source = {0, 0};
        int[] target = {0, 2};
        System.out.println(isEscapePossible(blocked, source, target));
    }


}
