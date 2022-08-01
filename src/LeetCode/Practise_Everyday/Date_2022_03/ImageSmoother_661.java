package LeetCode.Practise_Everyday.Date_2022_03;

import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 10:41 2022/3/24
 * @Description: 图像平滑器 是大小为 3 x 3 的过滤器，用于对图像的每个单元格平滑处理，平滑处理后单元格的值为该单元格的平均灰度。
 * 每个单元格的  平均灰度 定义为：该单元格自身及其周围的 8 个单元格的平均值，结果需向下取整。（即，需要计算蓝色平滑器中 9 个单元格的平均值）。
 * 如果一个单元格周围存在单元格缺失的情况，则计算平均灰度时不考虑缺失的单元格（即，需要计算红色平滑器中 4 个单元格的平均值）。
 */
public class ImageSmoother_661 {

    /*
        按照题目的要求，我们直接依次计算每一个位置平滑处理后的结果即可。
        具体地，对于位置 (i,j)(i,j)，我们枚举其周围的九个单元是否存在，对于存在的单元格，
        我们统计其数量 num 与总和 sum，那么该位置平滑处理后的结果即为 ⌊ tol / cnt ⌋。
     */
    public static int[][] imageSmoother(int[][] img) {
        int m = img.length, n = img[0].length;
        int[][] res = new int[m][n];
        int[][] dirs = new int[][]{{0, 0}, {0, 1}, {1, 0}, {0, -1}, {-1, 0}, {1, 1}, {1, -1}, {-1, 1}, {-1, -1}};
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                int tol = 0, cnt = 0;
                for (int[] dir : dirs) {
                    int nx = i + dir[0], ny = j + dir[1];
                    if (nx < 0 || nx >= m || ny < 0 || ny >= n) continue;
                    tol += img[nx][ny];
                    cnt++;
                }
                res[i][j] = tol / cnt;
            }
        return res;
    }

    public static void main(String[] args) {
        int[][] img = new int[][]{{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
        int[][] ints = imageSmoother(img);
        System.out.println(Arrays.deepToString(ints));
    }

}
