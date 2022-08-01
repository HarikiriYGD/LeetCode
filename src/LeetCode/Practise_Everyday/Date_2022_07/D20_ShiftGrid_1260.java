package LeetCode.Practise_Everyday.Date_2022_07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: Lil_boat
 * @Date: 2022/7/20 8:07
 * @Title: 二维网格迁移
 * @Description: 给你一个 m 行 n 列的二维网格 grid 和一个整数 k。你需要将 grid 迁移 k 次。
 * <p>
 * 每次「迁移」操作将会引发下述活动：
 * <p>
 * 位于 grid[i][j] 的元素将会移动到 grid[i][j + 1]。
 * 位于 grid[i][n - 1] 的元素将会移动到 grid[i + 1][0]。
 * 位于 grid[m - 1][n - 1] 的元素将会移动到 grid[0][0]。
 * 请你返回 k 次迁移操作后最终得到的 二维网格。
 * <p>
 * 链接：https://leetcode.cn/problems/shift-2d-grid
 */
public class D20_ShiftGrid_1260 {

    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length, n = grid[0].length;
        List<List<Integer>> ans = new ArrayList<>();
        int[] tmp = new int[m * n];
        int idx = 0;
        // 二维转一维数组
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                tmp[idx++] = grid[i][j];
            }
        }
        // 实际迁移次数
        int ac = k % (m * n);
        idx = 0;
        int[] pre = Arrays.copyOfRange(tmp, m * n - ac, m * n);
        int[] suff = Arrays.copyOfRange(tmp, 0, m * n - ac);

        for (int i = 0; i < pre.length; i++) {
            tmp[idx++] = pre[i];
        }
        for (int i = 0; i < suff.length; i++) {
            tmp[idx++] = suff[i];
        }

        List<Integer> subList = new ArrayList<>();
        for (int i = 0; i < m * n; i++) {
            if (i % n == 0) {
                subList = new ArrayList<>();
                ans.add(subList);
            }
            subList.add(tmp[i]);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[][] grid = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
        D20_ShiftGrid_1260 t = new D20_ShiftGrid_1260();
        List<List<Integer>> lists = t.shiftGrid(grid, 6);
        System.out.println(lists.toString());
    }

}
