package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * @Auther: Lil_boat
 * @Date: 11:37 2022/2/15
 * @Description: 给你一个 m * n 的矩阵，矩阵中的数字 各不相同 。请你按 任意 顺序返回矩阵中的所有幸运数。
 * 幸运数是指矩阵中满足同时下列两个条件的元素：
 * 在同一行的所有元素中最小
 * 在同一列的所有元素中最大
 */
public class LuckyNumbers_1380 {

    public static List<Integer> luckNumbers(int[][] matrix) {
        // 构建结果集
        List<Integer> ans = new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;
        for (int i = 0; i < m; i++) {
            // 将每一行的第一个元素作为最小值
            int rowMin = matrix[i][0];
            // 用于记录当前行最小值的索引
            int idx = 0;
            // 遍历每一个找到最小元素
            for (int j = 0; j < n; j++) {
                rowMin = Math.min(rowMin, matrix[i][j]);
                // 记录当前行最小值的所在列索引
                if (rowMin == matrix[i][j]) idx = j;
            }
            // 将每一列的第一个元素作为最大值
            int colMax = matrix[0][idx];
            // 找到每一行的最大值
            for (int j = 0; j < m; j++) {
                colMax = Math.max(colMax,matrix[j][idx]);
            }
            // 如果当前行的最小值与当前行的最大值相等则表明是幸运数
            if (colMax == rowMin)ans.add(colMax);
        }
        return ans;
    }

    /*
        预处理出每行的最小值数组 minRow 和每列的最大值数组 maxCol，其中 minRow[i] 表示第 i 行的最小值，maxCol[j] 表示第 j 列的最大值。
        遍历矩阵 matrix，如果 matrix[i][j] 同时满足 matrix[i][j]=minRow[i] 和 matrix[i][j]=maxCol[j]，
        那么 matrix[i][j] 是矩阵中的幸运数，加入返回结果。
     */
    public static List<Integer> luckyNumbers_preHandle (int[][] matrix) {
        int m = matrix.length, n = matrix[0].length;
        int[] minRow = new int[m];
        Arrays.fill(minRow, Integer.MAX_VALUE);
        int[] maxCol = new int[n];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // 找到每一列中的最小值
                minRow[i] = Math.min(minRow[i], matrix[i][j]);
                // 找到每一行中的最大值
                maxCol[j] = Math.max(maxCol[j], matrix[i][j]);
            }
        }
        List<Integer> ret = new ArrayList<Integer>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == minRow[i] && matrix[i][j] == maxCol[j]) {
                    ret.add(matrix[i][j]);
                }
            }
        }
        return ret;
    }


    public static void main(String[] args) {
        int[][] matrix = {{1,10,4,2},{9,3,8,7},{15,16,17,12}};
        List<Integer> list = luckNumbers(matrix);
        List<Integer> list1 = luckyNumbers_preHandle(matrix);
        for (Integer integer : list) {
            System.out.println(integer);
        }
        for (Integer integer : list1) {
            System.out.println(integer);
        }
    }

}
