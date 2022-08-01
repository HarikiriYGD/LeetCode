package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 10:02 2022/1/4
 * @Description: 给你一个下标从 0 开始的一维整数数组 original 和两个整数 m 和  n 。
 * 你需要使用 original 中 所有 元素创建一个 m 行 n 列的二维数组。
 * original 中下标从 0 到 n - 1 （都 包含 ）的元素构成二维数组的第一行，下标从 n 到 2 * n - 1 （都 包含 ）的元素构成二维数组的第二行，依此类推。
 * <p>
 * 请你根据上述过程返回一个 m x n 的二维数组。如果无法构成这样的二维数组，请你返回一个空的二维数组。
 */
public class Construct2DArray_2022 {
    /*
        注意original数组的下标索引和二维数组的关系
     */
    public static int[][] construct2DArray(int[] original, int m, int n) {
        if(original.length != m * n) return new int[][]{};
        int[][] ans = new int[m][n];
        int idx = 0;
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++){
                ans[i][j] = original[idx++];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] original = {1, 3, 5, 2};
        int[][] res = construct2DArray(original, 2, 2);
        System.out.println(Arrays.deepToString(res));
    }
}
