package LeetCode.Practise_Everyday.Date_2022_05;

/**
 * @Auther: Lil_boat
 * @Date: 9:59 2022/5/15
 * @Tile: 最大三角形面积
 * @Description: 给定包含多个点的集合，从其中取三个点组成三角形，返回能组成的最大三角形的面积。
 */
public class D15_LargestTriangleArea_812 {

    public double largestTriangleArea(int[][] points) {
        double ans = 0.0;
        int n = points.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                for (int k = j + 1; k < n; k++) {
                    int cur = cross(points[j][0] - points[i][0], points[j][1] - points[i][1], points[k][0] - points[i][0], points[k][1] - points[k][1]);
                    ans = Math.max(ans, Math.abs(cur / 2.0));
                }
            }
        }
        return ans;
    }

    private int cross(int a, int b, int c, int d) {
        return a * d - b * c;
    }

}
