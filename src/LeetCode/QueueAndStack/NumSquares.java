package LeetCode.QueueAndStack;


import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class NumSquares {
    /**
     * 完全平方和（动态规划法）
     *
     * @param n 传入的数值
     * @return 返回数组的最后一个值，即所需完全平方和的个数
     */
    public static int numSquares(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        for (int i = 1; i <= n; i++) {
            dp[i] = i;
            for (int j = 1; j * j <= i; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }

        return dp[n];
    }

    public static int numSquares1(int n) {
        HashSet<Integer> visited = new HashSet<>(); // 记录访问过的节点
        Queue<Integer> queue = new LinkedList<>(); // 队列
        visited.add(0);
        queue.add(0);
        int level = 0; // 记录树的第几层
        while (!queue.isEmpty()) { // 队列不为空
            int size = queue.size();
            System.out.println(size);
            level++;
            // 遍历所有层的节点
            for (int i = 0; i < size; i++) {
                // 节点的值
                int digit = queue.poll();
                // 访问当前节点的子节点，类比于二叉树的左右子节点
                for (int j = 1; j <= n; j++) {
                    // nodeValue始终是完全平方数的和，当他等于n的时候直接返回
                    int nodeValue = digit + j * j;
                    if (nodeValue == n) return level;
                    // nodeValue大于n的时候，跳出循环
                    if (nodeValue > n) break;
                    if (!visited.contains(nodeValue)) {
                        queue.add(nodeValue);
                        visited.add(nodeValue);
                    }
                }
            }
        }
        return level;
    }


    public static void main(String[] args) {

        int result = numSquares1(13);
        System.out.println(result);
    }

}
