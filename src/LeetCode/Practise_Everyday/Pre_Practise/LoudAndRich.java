package LeetCode.Practise_Everyday.Pre_Practise;

import LeetCode.Linked_List.ListNode;

import java.util.*;

/**
 * @Auther: Lil_boat
 * @Date: 14:21 2021/12/15
 * @Description: 有一组 n 个人作为实验对象，从 0 到 n - 1 编号，
 * 其中每个人都有不同数目的钱，以及不同程度的安静值（quietness）。
 * 为了方便起见，我们将编号为 x 的人简称为 "person x "。
 * 给你一个数组 richer ，其中 richer[i] = [ai, bi] 表示 person ai 比 person bi 更有钱。
 * 另给你一个整数数组 quiet ，其中 quiet[i] 是 person i 的安静值。richer 中所给出的数据
 * 逻辑自恰（也就是说，在 person x 比 person y 更有钱的同时，不会出现 person y 比 person x 更有钱的情况 ）。
 * 现在，返回一个整数数组 answer 作为答案，其中 answer[x] = y 的前提是，
 * 在所有拥有的钱肯定不少于 person x 的人中，person y 是最安静的人（也就是安静值 quiet[y] 最小的人）。
 */
public class LoudAndRich {

    /**
     * dfs的方法
     * 深度优先搜索
     * 我们可以根据richer构建一张有向图：
     * 把人看成点，如果 ai比bi更有钱，那么就从bi向ai连一条有向边。
     * 由于题目保证 richer中所给出的数据逻辑自恰，我们得到的是一张有向无环图。
     * 因此我们从图上任意一点（设为 x）出发，沿着有向边所能访问到的点，都比 x 更有钱。
     * <p>
     * 题目需要计算拥有的钱肯定不少于 x的人中，
     * 最安静的人。我们可以分为拥有的钱肯定与 x 相等，
     * 以及拥有的钱肯定比 x多两种情况。对于前者，根据题目所给信息，
     * 我们只知道 x拥有的钱肯定与自己相等，
     * 无法知道是否有其他人拥有的钱肯定与 x相等；
     * 对于后者，我们可以先计算出 x的邻居的 answer值，再取这些 answer，
     * 这是因为从 x的邻居出发所能访问到的点，并上 x的邻居后所得到的点集，
     * 就是从 x出发所能访问到的点。总的来说，最安静的人要么是x自己，要么是 x的邻居的 answer中最安静的人。
     * 计算 x的每个邻居的answer 值是一个递归的过程，我们可以用深度优先搜索来实现。
     * 为避免重复运算，在已经计算出answer[x] 的情况下可以直接返回。
     *
     * @param richer
     * @param quiet
     * @return
     */
    public static int[] loudAndRich(int[][] richer, int[] quiet) {
        // 实验对象的人数
        int n = quiet.length;
        // 建立一个List类型的数组
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<Integer>();
        }
        // 构建图谱
        for (int[] r : richer) {
            g[r[1]].add(r[0]);
        }
        // 构建answer数组
        int[] ans = new int[n];
        Arrays.fill(ans, -1);
        for (int i = 0; i < n; i++) {
            // 寻找比personi有钱的人
            dfs(i, quiet, g, ans);
        }
        return ans;
    }

    /**
     * @param x
     * @param quiet
     * @param g
     * @param ans
     */
    private static void dfs(int x, int[] quiet, List<Integer>[] g, int[] ans) {
        if (ans[x] != -1) {
            return;
        }
        // 将personx的安静值赋值给ansx
        ans[x] = x;
        // 遍历g[x]的元素，其实就是比personx有钱的人
        for (int y : g[x]) {
            // 寻找比persony有钱的人
            dfs(y, quiet, g, ans);
            // 更新更有钱人中的最小安静值的人
            if (quiet[ans[y]] < quiet[ans[x]]) ans[x] = ans[y];
        }
    }

    /**
     * 拓扑排序
     * 我们可以将方法一中的图的边全部反向，
     * 即如果ai比bi更有钱，我们从ai向bi连一条有向边。
     * 这同样得到的是一张有向无环图，
     * 因此我们从图上任意一点（设为 x）出发，沿着有向边所能访问到的点，拥有的钱都比x少。
     * 这意味着我们可以在计算出 answer[x]后，用 answer[x]去更新 x所能访问到的点的answer 值。
     * 要实现这一算法，我们可以将每个answer[x]初始化为 x，然后对这张图执行一遍拓扑排序，
     * 并按照拓扑序去更新 x的邻居的 answer值。通过这一方式我们就能将 answer[x] 的值「传播」到x所能访问到的点上。
     *
     * @param richer
     * @param quiet
     * @return
     */
    public static int[] loudAndRich_Topological_sorting(int[][] richer, int[] quiet) {
        int n = quiet.length;
        List<Integer>[] g = new List[n];
        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }
        int[] inDeg = new int[n];
        for (int[] r : richer) {
            g[r[0]].add(r[1]);
            // 入度
            ++inDeg[r[1]];
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; ++i) {
            ans[i] = i;
        }
        Queue<Integer> q = new ArrayDeque<Integer>();
        for (int i = 0; i < n; ++i) {
            // 将入度为0的节点入队
            if (inDeg[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int x = q.poll();
            for (int y : g[x]) {
                if (quiet[ans[x]] < quiet[ans[y]]) {
                    ans[y] = ans[x]; // 更新 x 的邻居的答案
                }
                if (--inDeg[y] == 0) {
                    q.offer(y);
                }
            }
        }
        return ans;

    }

    /**
     * 类似于暴力的方法
     * 但是会超时
     * <p>
     * //     * @param richer
     * //     * @param quiet
     *
     * @return
     */
//    public static int[] loudAndRich(int[][] richer, int[] quiet) {
//        if (richer == null) return new int[1];
//        // 实验对象人数
//        int n = quiet.length;
//        int[] answer = new int[n];
//        // 存储每一个安静值对应的person
//        Map<Integer, Integer> map = new HashMap<>();
//        int count = 0, index = 0;
//        int minQ = 0;
//        for (int x : quiet) {
//            map.put(x, count++);
//        }
//        for (int i = 0; i < n; i++) {
//            Map<Integer, Integer> tmp = new HashMap<>();
//            find(richer, i, tmp, 0);
//            minQ = quiet[i];
//            for (Integer person : tmp.keySet()) {
//                if (quiet[person] < minQ)minQ = quiet[person];
//            }
//
//            answer[index++] = map.get(minQ);
//        }
//        return answer;
//
//    }
//
//    public static void find(int[][] richer, int index, Map<Integer, Integer> tmp, int c) {
//        for (int i = 0; i < richer.length; i++) {
//            if (richer[i][1] == index) {
//                tmp.put(richer[i][0], richer[i][0]);
//                find(richer, richer[i][0], tmp, c);
//            }
//        }
//    }
    public static void main(String[] args) {
        int[][] richer = {{0, 2}, {0, 3}, {1, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 4}, {3, 4}};
        int[] quiet = {2, 1, 4, 3, 0};
        System.out.println("第一组数据:" + Arrays.toString(loudAndRich(richer, quiet)));
        System.out.println("第二组数据:" + Arrays.toString(loudAndRich_Topological_sorting(richer, quiet)));
    }

}
