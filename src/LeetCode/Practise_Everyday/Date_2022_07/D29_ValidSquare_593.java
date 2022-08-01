package LeetCode.Practise_Everyday.Date_2022_07;

/**
 * @Author: Lil_boat
 * @Date: 2022/7/29 15:08
 * @Title: 有效的正方形
 * @Description: 给定2D空间中四个点的坐标 p1, p2, p3 和 p4，如果这四个点构成一个正方形，则返回 true 。
 * 点的坐标 pi 表示为 [xi, yi] 。输入 不是 按任何顺序给出的。
 * 一个 有效的正方形 有四条等边和四个等角(90度角)。
 * <p>
 * 链接：https://leetcode.cn/problems/valid-square
 */
public class D29_ValidSquare_593 {

    /**
     * 定义边长
     */
    int len = -1;

    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        return cal(p1, p2, p3) && cal(p1, p2, p4) && cal(p2, p3, p4);
    }

    private boolean cal(int[] a, int[] b, int[] c) {
        // 计算三个点的边长
        int l1 = (a[0] - b[0]) * (a[0] - b[0]) + (a[1] - b[1]) * (a[1] - b[1]);
        int l2 = (a[0] - c[0]) * (a[0] - c[0]) + (a[1] - c[1]) * (a[1] - c[1]);
        int l3 = (b[0] - c[0]) * (b[0] - c[0]) + (b[1] - c[1]) * (b[1] - c[1]);
        // 如果有两条边相等且两边的平方和等于第三边，表明是等腰直角三角形
        boolean flag = (l1 == l2 && l1 + l2 == l3) || (l1 == l3 && l1 + l3 == l2) || (l2 == l3 && l2 + l3 == l1);
        if (!flag) return false;
        if (len == -1) len = Math.min(l1, l2);
        else if (len == 0 || len != Math.min(l1, l2)) return false;
        return true;
    }

}
