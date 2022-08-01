package LeetCode.MeiTuan;

import java.util.*;
/**
 * @Auther: Lil_boat
 * @Date: 11:33 2022/4/20
 * @Tile:  小美的跑腿代购
 * @Description: 小美的一个兼职是美团的一名跑腿代购员，她有 n 个订单可以接，订单编号是 1~n ，但是因为订单的时效性，
 * 他只能选择其中 m 个订单接取，精明的小美当然希望自己总的获利是最大的，已知，一份订单会提供以下信息，跑腿价格 v ，商品重量 w kg，
 * 商品每重 1kg ，代购费用要加 2 元，而一份订单可以赚到的钱是跑腿价格和重量加价之和。小美可是开兰博基尼送货的人，
 * 所以自然不会在意自己会累这种事情。请问小美应该选择哪些订单，使得自己获得的钱最多。
 * 请你按照选择的订单编号的从小到大顺序，如果存在多种方案，输出订单编号字典序较小的方案。
 */
public class Buying {

    static int n;
    static int m;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 有n个订单可以接
        n = sc.nextInt();
        // 只有接收m个订单
        m = sc.nextInt();
        // 记录每个商品的跑腿价格和重量
        int[][] orders = new int[n][2];
        for (int i = 0; i < n; i++) {
            // 每个订单的跑腿价格
            orders[i][0] = sc.nextInt();
            // 每个订单的重量
            orders[i][1] = sc.nextInt();
        }
        // 关闭输入流
        sc.close();

        // 获取结果集 并 打印输出
        List<Integer> list = countMaxValue(orders);
        for (Integer i : list) {
            System.out.println(i + " ");
        }

    }

    public static List<Integer> countMaxValue(int[][] orders) {
        // 优先队列
        // 根据总价值来排序 如果有相同的价值 则按字典序排列
        PriorityQueue<int[]> queue = new PriorityQueue<>(((o1, o2) -> {
            if (o2[0] + o2[1] * 2 == o1[0] + o1[1] * 2) return o1[2] - o2[2];
            else return o2[0] + o2[1] * 2 - o1[0] - o1[1] * 2;
        }));
        for (int i = 0; i < n; i++) {
            // 输入订单的跑腿价格    重量    订单编号
            queue.add(new int[]{orders[i][0], orders[i][1], i + 1});
        }
        List<Integer> ans = new ArrayList<>();
        while (ans.size() < m && !queue.isEmpty()) {
            ans.add(queue.poll()[2]);
        }
        ans.sort(Integer::compareTo);
        return ans;

    }


}
