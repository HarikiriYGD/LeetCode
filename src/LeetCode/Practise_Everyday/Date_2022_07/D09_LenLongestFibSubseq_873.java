package LeetCode.Practise_Everyday.Date_2022_07;

import java.util.HashMap;

/**
 * @Author: Lil_boat
 * @Date: 2022/7/9 11:43
 * @Title: 最长的斐波那契子序列的长度
 * @Description: 如果序列 X_1, X_2, ..., X_n 满足下列条件，就说它是 斐波那契式 的：
 * n >= 3
 * 对于所有 i + 2 <= n，都有 X_i + X_{i+1} = X_{i+2}
 * 给定一个严格递增的正整数数组形成序列 arr ，找到 arr 中最长的斐波那契式的子序列的长度。如果一个不存在，返回  0 。
 * <p>
 * （回想一下，子序列是从原序列 arr 中派生出来的，它从 arr 中删掉任意数量的元素（也可以不删），而不改变其余元素的顺序。
 * 例如， [3, 5, 8] 是 [3, 4, 5, 6, 7, 8] 的一个子序列）
 * <p>
 * 链接：https://leetcode.cn/problems/length-of-longest-fibonacci-subsequence
 */
public class D09_LenLongestFibSubseq_873 {

    public int lenLongestFibSubseq(int[] arr) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int n = arr.length, ans = 0;
        // 构建动态规划数组
        // 其中dp[i][j]表示以arr[i][j]为倒数两个数
        int[][] dp = new int[n][n];
        // 将数组的元素及其对应的索引放入map中
        for (int i = 0; i < n; i++) {
            map.put(arr[i], i);
        }
        // 从数组的第三个数开始
        for (int j = 2; j < n; j++) {
            // 判断前两个数是否构成斐波那契数
            for (int i = j - 1; i > 0 && arr[i] * 2 > arr[j]; i--) {
                // 判断是否存在
                int a = map.getOrDefault(arr[j] - arr[i], j);
                if (a < i) {
                    dp[i][j] = dp[a][i] + 1;
                    ans = Math.max(ans, dp[i][j]);
                }
            }
        }
        return ans == 0 ? 0 : ans + 2;
    }

}
