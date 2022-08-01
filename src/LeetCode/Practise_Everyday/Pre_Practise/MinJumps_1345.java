package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.*;

/**
 * @Auther: Lil_boat
 * @Date: 13:03 2022/1/21
 * @Description: 给你一个整数数组 arr ，你一开始在数组的第一个元素处（下标为 0）。
 * <p>
 * 每一步，你可以从下标 i 跳到下标：
 * i + 1 满足：i + 1 < arr.length
 * i - 1 满足：i - 1 >= 0
 * j 满足：arr[i] == arr[j] 且 i != j
 * 请你返回到达数组最后一个元素的下标处所需的 最少操作次数 。
 * 注意：任何时候你都不能跳到数组外面。
 */
public class MinJumps_1345 {
    public static int minJumps(int[] arr) {
        // 总体思路：广度优先搜索：首次到达的末尾点的元素，必然是最小步数
        Map<Integer, List<Integer>> map = new HashMap<>();
        int n = arr.length;
        // 将相等的元素加入hashMap
        for (int i = n - 1; i >= 0; i--) {
            List<Integer> list = map.getOrDefault(arr[i], new ArrayList<>());
            list.add(i);
            map.put(arr[i], list);
        }
        // 队列用于存 此节点 在哪里(索引：index) 以及 来时走了多少路(步数：steps)
        Deque<int[]> queue = new ArrayDeque<>();
        queue.offerLast(new int[]{0, 0});
        // 用于记录当前位置是否来过
        boolean[] hasBeenTo = new boolean[n];
        hasBeenTo[0] = true;
        // while循环
        while (!queue.isEmpty()) {
            // 起始点出队
            int[] poll = queue.pollFirst();
            int idx = poll[0], steps = poll[1];
            // 判断是否走到了最后位置 如果是 直接返回
            if (idx == n - 1) return steps;
            // 广搜的具体操作 向前 向后 等值跳
            steps++;
            // 向后
            if (idx - 1 >= 0 && hasBeenTo[idx - 1] == false) {
                hasBeenTo[idx - 1] = true;
                queue.offerLast(new int[]{idx - 1, steps});
            }
            // 向前
            if (idx + 1 <= n - 1 && hasBeenTo[idx + 1] == false) {
                hasBeenTo[idx + 1] = true;
                queue.offerLast(new int[]{idx + 1, steps});
            }
            // 向元素相同的值地方
            if (map.get(arr[idx]) != null) {
                for (int i : map.get(arr[idx])) {
                    if (hasBeenTo[i] == false) {
                        if (i == n - 1) return steps; //  如果某个元素相同的位置在最后一位，那就直接结束。
                        hasBeenTo[i] = true;
                        queue.offerLast(new int[]{i, steps});
                    }
                }
                // 访问过某个节点后，即会将所有其他与其元素相同的节点都放入队列。经过这个过程之后直接将该元素移出哈希表避免重复访问
                map.remove(arr[idx]);
            }
        }
        return -1;
    }



    public static void main(String[] args) {
        int[] arr = {100,-23,-23,404,100,23,23,23,3,404};
        System.out.println(minJumps(arr));
    }
}
