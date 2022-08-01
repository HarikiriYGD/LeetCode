package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Auther: Lil_boat
 * @Date: 14:37 2021/12/16
 * @Description: 给你一个点数组 points 和一个表示角度的整数 angle ，
 * 你的位置是 location，其中 location = [posx, posy]且points[i] = [xi, yi]都表示 X-Y平面上的整数坐标。
 * 最开始，你面向东方进行观测。你 不能 进行移动改变位置，
 * 但可以通过自转调整观测角度。换句话说，posx 和 posy 不能改变。
 * 你的视野范围的角度用 angle 表示， 这决定了你观测任意方向时可以多宽。
 * 设 d 为你逆时针自转旋转的度数，那么你的视野就是角度范围 [d - angle/2, d + angle/2] 所指示的那片区域。
 */
public class VisiblePoints {

    public static int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        // 和观察者所在位置相同的点
        int samePointSum = 0;
        List<Double> degrees = new ArrayList<>();
        // 观察者的位置坐标
        int x = location.get(0), y = location.get(1);
        for (List<Integer> curLocation : points) {
            int curX = curLocation.get(0), curY = curLocation.get(1);
            if (curX - x == 0 && curY - y == 0) samePointSum++;
            else {
                // 弧度转为角度 1 rad = 180 / PI
                degrees.add(Math.atan2(curY - y, curX - x) / Math.PI * 180);
            }
        }
        // 排序
        Collections.sort(degrees);
        int n = degrees.size();
        // 将所有的角度统一到[0,360]内
        for (int i = 0; i < n && degrees.get(i) + 180 <= angle; i++) {
            degrees.add(degrees.get(i) + 360);
        }

        // 滑动窗口
        int res = 0;
        int left = 0, right = 0;
        while (right < degrees.size()) {
            right++;
            // 判断右端的角度 - 左端的角度是否大于 angle
            // 如果大于则不能同时观察到
            while (left < right && degrees.get(right - 1) - degrees.get(left) > angle) {
                // 左端右移
                left++;
            }
            res = Math.max(res, right - left);
        }
        return res + samePointSum;
    }

    public static void main(String[] args) {
        List<List<Integer>> points = new ArrayList<>();
        List<Integer> points1 = new ArrayList<>();
        List<Integer> points2 = new ArrayList<>();
        List<Integer> points3 = new ArrayList<>();
        List<Integer> points4 = new ArrayList<>();
        points1.add(2);
        points1.add(1);
        points.add(points1);
        points2.add(2);
        points2.add(2);
        points.add(points2);
        points3.add(3);
        points3.add(3);
        points.add(points3);
        points4.add(2);
        points4.add(0);
        points.add(points4);
        List<Integer> location = new ArrayList<>();
        location.add(1);
        location.add(1);
        System.out.println(visiblePoints(points, 90, location));
    }


}
