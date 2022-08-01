package LeetCode.MeiTuan;

import java.util.*;

/**
 * @Auther: Lil_boat
 * @Date: 11:51 2022/4/19
 * @Tile: 小美的仓库整理
 * @Description: 小美是美团仓库的管理员，她会根据单据的要求按顺序取出仓库中的货物，
 * 每取出一件货物后会把剩余货物重新堆放，使得自己方便查找。
 * 已知货物入库的时候是按顺序堆放在一起的。如果小美取出其中一件货物，则会把货物所在的一堆物品以取出的货物为界分成两堆，这样可以保证货物局部的顺序不变。
 * 已知货物最初是按 1~n 的顺序堆放的，每件货物的重量为 w[i] ,小美会根据单据依次不放回的取出货物。
 * 请问根据上述操作，小美每取出一件货物之后，重量和最大的一堆货物重量是多少？
 */
public class StoreManagement {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        int[] weights = new int[n];
        int[] orders = new int[n];
        // 获取货物的重量
        for (int i = 0; i < n; i++) {
            weights[i] = sc.nextInt();
        }
        // 获取货物取出的顺序
        for (int i = 0; i < n; i++) {
            orders[i] = sc.nextInt();
        }
        sc.close();
        // 构建前缀和数组 方便快速查询
        int[] prefixSum = new int[n + 1];
        for (int i = 0; i < n; i++) {
            prefixSum[i + 1] = prefixSum[i] + weights[i];
        }
        // 构建结果集
        int[] ans = new int[n];
        //
        TreeSet<Integer> set = new TreeSet<>();
        set.add(0);
        set.add(n + 1);

        // key存储前缀和 value存储个数
        TreeMap<Integer, Integer> sumMap = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            // 当前要取出的货物索引
            int pos = orders[i];
            // 返回严格小于给定键值的最大键值
            int left = set.lower(pos);
            // 返回严格大于给定键值的最小键值
            int right = set.higher(pos);
            // 添加分割点
            set.add(pos);

            // 片段和
            int segSum = prefixSum[right - 1] - prefixSum[left];
            Integer count = sumMap.get(segSum);
            // 如果是空的，则不管
            if (count != null) {
                // 如果数量是1代表要删除了
                if (count == 1) {
                    sumMap.remove(segSum);
                } else {
                    sumMap.put(segSum, count - 1);
                }
            }
            int leftSum = prefixSum[pos - 1] - prefixSum[left];
            int rightSum = prefixSum[right - 1] - prefixSum[pos];
            sumMap.put(leftSum, sumMap.getOrDefault(leftSum, 0) + 1);
            sumMap.put(rightSum, sumMap.getOrDefault(rightSum, 0) + 1);
            // treeMap中的最后一个元素就是最大的值
            ans[i] = sumMap.lastKey();
        }

        for (int i = 0; i < n; i++) {
            System.out.println(ans[i]);
        }
    }

}
