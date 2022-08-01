package LeetCode.Practise_Everyday.Date_2022_06;

/**
 * @Auther: Lil_boat
 * @Date: 10:07 2022/6/8
 * @Tile: 有效的回旋镖
 * @Description: 给定一个数组 points ，其中 points[i] = [xi, yi] 表示 X-Y 平面上的一个点，如果这些点构成一个 回旋镖 则返回 true 。
 * 回旋镖 定义为一组三个点，这些点 各不相同 且 不在一条直线上 。
 * <p>
 * 链接：https://leetcode.cn/problems/valid-boomerang
 */
public class D08_IsBoomerang_1037 {

    public boolean isBoomerang(int[][] points) {
        int x1 = points[1][0] - points[0][0], y1 = points[1][1] - points[0][1];
        int x2 = points[2][0] - points[0][0], y2 = points[2][1] - points[0][1];
        return x1 * y2 - x2 * y1 != 0;
    }

    public static void main(String[] args) {
        D08_IsBoomerang_1037 t = new D08_IsBoomerang_1037();
        int[][] points = {{1, 1}, {2, 2}, {3, 3}};
        System.out.println(t.isBoomerang(points));
    }

}
