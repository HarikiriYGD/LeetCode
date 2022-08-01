package LeetCode.Practise_Everyday.Date_2022_05;

/**
 * @Auther: Lil_boat
 * @Date: 11:08 2022/5/18
 * @Tile: 乘法表中第k小的数
 * @Description: 几乎每一个人都用 乘法表。但是你能在乘法表中快速找到第k小的数字吗？
 * 给定高度m 、宽度n 的一张 m * n的乘法表，以及正整数k，你需要返回表中第k 小的数字。
 * <p>
 * 链接：https://leetcode.cn/problems/kth-smallest-number-in-multiplication-table
 */
public class D18_FindKthNumber_668 {

    /*
        二分查找
     */
    public int findKthNumber(int m, int n, int k) {
        int left = 1;
        int right = m * n;
        while (left < right) {
            int mid = left + right >> 1;
            if (count(m, n, mid) >= k) right = mid;
            else left = mid + 1;
        }
        return left;
    }

    private int count(int m, int n, int k) {
        int res = 0;
        for (int i = 1; i <= m; i++) {
            res += Math.min(k / i, n);
        }
        return res;
    }

}
