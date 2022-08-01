package LeetCode.Practise_Everyday.Date_2022_06;

import java.util.Random;

/**
 * @Auther: Lil_boat
 * @Date: 10:50 2022/6/5
 * @Tile: 在圆内随机生成点
 * @Description: 给定圆的半径和圆心的位置，实现函数 randPoint ，在圆中产生均匀随机点。
 * <p>
 * 实现 Solution 类:
 * Solution(double radius, double x_center, double y_center) 用圆的半径 radius 和圆心的位置 (x_center, y_center) 初始化对象
 * randPoint() 返回圆内的一个随机点。圆周上的一点被认为在圆内。答案作为数组返回 [x, y] 。
 * <p>
 * 链接：https://leetcode.cn/problems/generate-random-point-in-a-circle
 */
public class D05_Solution_478 {
    Random random = new Random();
    double r, x0, y0;

    public D05_Solution_478(double radius, double x_center, double y_center) {
        this.r = radius;
        this.x0 = x_center;
        this.y0 = y_center;
    }

    public double[] randPoint() {
        while (true) {
            // 控制x坐标在 [-r, r]
            double x = random.nextDouble() * (2 * r) - r;
            // 控制y坐标在 [-r, r]
            double y = random.nextDouble() * (2 * r) - r;
            if (x * x + y * y <= r * r) {
                return new double[]{x0 + x, y0 + y};
            }
        }
    }

    public static void main(String[] args) {
        D05_Solution_478 t = new D05_Solution_478(1.0, 0.0, 0.0);
        t.randPoint();
    }
}
