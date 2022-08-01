package LeetCode.Practise_Everyday.Date_2022_07;

/**
 * @Author: Lil_boat
 * @Date: 2022/7/12 11:04
 * @Title: 奇数值单元格的数目oddCells
 * @Description: 给你一个 m x n 的矩阵，最开始的时候，每个单元格中的值都是 0。
 * 另有一个二维索引数组 indices，indices[i] = [ri, ci] 指向矩阵中的某个位置，其中 ri 和 ci 分别表示指定的行和列（从 0 开始编号）。
 * 对 indices[i] 所指向的每个位置，应同时执行下述增量操作：
 * <p>
 * ri 行上的所有单元格，加 1 。
 * ci 列上的所有单元格，加 1 。
 * 给你 m、n 和 indices 。请你在执行完所有 indices 指定的增量操作后，返回矩阵中 奇数值单元格 的数目。
 * <p>
 * 链接：https://leetcode.cn/problems/cells-with-odd-values-in-a-matrix
 */
public class D12_OddCells_1252 {

    public int oddCells(int m, int n, int[][] indices) {
        int[][] cnt = new int[m][n];
        for (int[] idx : indices) {
            int row = idx[0];
            int col = idx[1];
            for (int i = 0; i < n; i++) {
                cnt[row][i]++;
            }
            for (int i = 0; i < m; i++) {
                cnt[i][col]++;
            }
        }
        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (cnt[i][j] % 2 == 1) {
                    ans++;
                }
            }
        }
        return ans;
    }

}
