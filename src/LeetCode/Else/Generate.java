package LeetCode.Else;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Lil_boat
 * @Date: 17:22 2021/12/7
 * @Description: 给定一个非负整数 numRows，生成「杨辉三角」的前 numRows 行。
 * <p>
 * 在「杨辉三角」中，每个数是它左上方和右上方的数的和。
 */
public class Generate {

    /**
     * 运用ArrayList
     *
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generate(int numRows) {
        //结果值
        List<List<Integer>> res = new ArrayList<>();
        //每一行的元素
        ArrayList<Integer> row = new ArrayList<>();
        for (int i = 0; i < numRows; i++) {
            //下面一行都会比上面一行多一个元素，我们在第一个位置给他加个1
            row.add(0, 1);
            //遍历每一行的结果，遍历的时候跳过第一个和最后一个，
            //每个格子的值都是他正上面和左上角元素的和
            for (int j = 1; j < row.size() - 1; j++)
                row.set(j, row.get(j) + row.get(j + 1));
            //把结果存放到res中
            res.add(new ArrayList<>(row));
        }
        return res;

    }

    /**
     * 帕斯卡三角形
     * 1
     * 1 1
     * 1 2 1
     * 1 3 3 1
     * 1 4 6 4 1
     * 1 5 10 10 5 1
     *
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generate_ArrayList(int numRows) {
        //结果值
        List<List<Integer>> res = new ArrayList<>();
        // 构建数组
        int[][] arr = new int[numRows][numRows];
        for (int i = 0; i < numRows; i++) {
            List<Integer> subList = new ArrayList<>();
            // 判断是否是边界或是对角线上的元素
            // 如果是，则赋值为1
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) {
                    arr[i][j] = 1;
                } else {
                    arr[i][j] = arr[i - 1][j - 1] + arr[i - 1][j];
                }
                // 将数组元素赋值到subList中
                subList.add(arr[i][j]);
            }
            res.add(subList);
        }
        return res;
    }


    public static void main(String[] args) {
        List<List<Integer>> res = generate(5);
        System.out.println(res.toString());
        System.out.println(generate_ArrayList(5).toString());
    }
}
