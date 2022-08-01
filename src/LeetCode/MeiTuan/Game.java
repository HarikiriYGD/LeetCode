package LeetCode.MeiTuan;

import java.io.*;
/**
 * @Auther: Lil_boat
 * @Date: 11:42 2022/4/25
 * @Tile: 小团的默契游戏
 * @Description: 小团从某不知名论坛上突然得到了一个测试默契度的游戏，想和小美玩一次来检验两人的默契程度。
 * 游戏规则十分简单，首先给出一个长度为 n 的序列，最大值不超过 m 。
 * 小团和小美各自选择一个 [1,m] 之间的整数，设小美选择的是 l ，小团选择的是 r ，我们认为两个人是默契的需要满足以下条件:
 *
 * l 小于等于 r 。
 * 对于序列中的元素 x ，如果 0<x<l ，或 r<x<m+1 ,则 x 按其顺序保留下来，要求保留下来的子序列单调不下降。
 * 小团为了表现出与小美最大的默契，因此事先做了功课，他想知道能够使得两人默契的二元组 <l,r> 一共有多少种。
 * 我们称一个序列 A 为单调不下降的，当且仅当对于任意的 i>j ，满足 A[i]>=A[j] 。
 */
public class Game {

    /*
        二分查找
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] line = br.readLine().split(" ");
        int m = Integer.parseInt(line[0]);
        int n = Integer.parseInt(line[1]);
        // 获取输入的序列
        line = br.readLine().split(" ");
        br.close();
        int[] seq = new int[n];
        for (int i = 0; i < n; i++) {
            seq[i] = Integer.parseInt(line[i]);
        }
        int res = 0;
        // 二分查找
        for (int i = 1; i <= m; i++) {
            int left = i, right = m + 1;
            while (left < right) {
                int mid = (right - left) / 2 + left;
                if (check(seq, i, mid)) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            // 个数
            res += m - left + 1;
            if (left == m + 1) {
                break;
            }
        }
        bw.write(String.valueOf(res));
        bw.close();
    }

    private static boolean check(int[] arr, int left, int right) {
        int pre = -1;
        for (int x : arr) {
            if (left > x || right < x) {
                // 如果是非递增的则返回false 表明不是一个非递增的序列
                if (x < pre) {
                    return false;
                }
                // 记录前一个添加进序列的元素
                pre = x;
            }
        }
        return true;

    }

}
