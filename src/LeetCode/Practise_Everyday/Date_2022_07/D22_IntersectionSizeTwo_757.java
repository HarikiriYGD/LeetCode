package LeetCode.Practise_Everyday.Date_2022_07;


import java.util.Arrays;

/**
 * @Author: Lil_boat
 * @Date: 2022/7/22 8:45
 * @Title: 设置交集大小至少为2
 * @Description: 一个整数区间 [a, b]  ( a < b ) 代表着从 a 到 b 的所有连续整数，包括 a 和 b。
 * 给你一组整数区间intervals，请找到一个最小的集合 S，使得 S 里的元素与区间intervals中的每一个整数区间都至少有2个元素相交。
 * 输出这个最小集合S的大小。
 * <p>
 * 链接：https://leetcode.cn/problems/set-intersection-size-at-least-two
 */
public class D22_IntersectionSizeTwo_757 {

    public int intersectionSizeTwo(int[][] intervals) {
        // 根据右端点来排序，如果右端点一致，选择左端点来排序
        Arrays.sort(intervals, (a, b) -> a[1] > b[1] ? 1 : (a[1] == b[1]) ? (Integer.compare(a[0], b[0])) : -1);
        int reslut = 2;
        // 选择左边最大的数
        int v1 = intervals[0][1] - 1;
        int v2 = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            // 如果已经加入集合的S的最大元素都小于下一个区间的左端点，则要添加两个元素
            if (v2 < intervals[i][0]) {
                v1 = intervals[i][1] - 1;
                v2 = intervals[i][1];
                reslut += 2;
            } else if (v1 < intervals[i][0] && v2 >= intervals[i][0]) {
                // 例如：S = {2,3} 下一个区间为[3,5] 则要加入一个元素
                // 如果小于右边界，即上面的情况 则要改变v1和v2的值
                if (v2 < intervals[i][1]) {
                    v1 = v2;
                    v2 = intervals[i][1];
                    // 如果大于等于右边界，eg: S = {2,5} 下一个区间为[3,5]
                    // 将v1赋值为4即可
                } else {
                    v1 = intervals[i][1] - 1;
                }
                reslut++;
            }
        }
        return reslut;
    }

}
