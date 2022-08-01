package LeetCode.Practise_Everyday.Date_2022_04;

/**
 * @Auther: Lil_boat
 * @Date: 10:06 2022/4/11
 * @Description: 给定四个整数 sx , sy ，tx 和 ty，如果通过一系列的转换可以从起点 (sx, sy) 到达终点 (tx, ty)，则返回 true，否则返回 false。
 * <p>
 * 从点 (x, y) 可以转换到 (x, x+y)  或者 (x+y, y)。
 */
public class ReachingPoints_780 {

    /*
        思路分析：这道题确实非常妙！大部分的人一般都会惯性思维死劲想着怎么从(sx, sy)推到(tx, ty)，
        但是由于可以变换的情况非常多，特别是当起点与终点的差距比较大的时候。如果我们逆向思考呢，
        从(tx, ty)推到(sx, sy)，则时只能有一种操作，就是将tx、ty中较大值减去较小值（因为顺推的时候是(x, y)
        可以转换到 (x, x+y) 或者 (x+y, y)，则逆推的时候只能将较大者减去较小者），这样思维方式确实很妙！
     */
    public static boolean reachingPoints(int sx, int sy, int tx, int ty) {
        // 逆向推导
        while (tx > 0 && ty > 0) {
            // 如果目标点和起始点相同则返回true
            if (tx == sx && ty == sy) return true;
            if (tx > ty) {// 此时只能有tx减去ty
                // tx - sx是目标与起始值在x的差距，我们需要一次减去n * ty达到快速逼近sx的目的
                tx -= Math.max((tx - sx) / ty, 1) * ty;
            } else {// 此时只能有ty减去tx
                // ty - sy是目标与起始值在y的差距，我们需要一次减去n * tx达到快速逼近sy的目的
                ty -= Math.max((ty - sy) / sx, 1) * tx;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(reachingPoints(1, 1, 2, 2));
    }

}
