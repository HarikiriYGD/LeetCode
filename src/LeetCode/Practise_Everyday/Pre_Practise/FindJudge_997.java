package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: Lil_boat
 * @Date: 14:05 2021/12/19
 * @Description: 小镇里有 n 个人，按从 1 到 n 的顺序编号。传言称，这些人中有一个暗地里是小镇法官。
 * 如果小镇法官真的存在，那么：
 * 小镇法官不会信任任何人。
 * 每个人（除了小镇法官）都信任这位小镇法官。
 * 只有一个人同时满足属性 1 和属性 2 。
 * 给你一个数组 trust ，其中 trust[i] = [ai, bi] 表示编号为 ai 的人信任编号为 bi 的人。
 * <p>
 * 如果小镇法官存在并且可以确定他的身份，请返回该法官的编号；否则，返回 -1 。
 */
public class FindJudge_997 {

    public static int findJudge(int n, int[][] trust) {
        // 记录索引
        int index = 0;
        // 入度数组
        int[] inDegrees = new int[n];
        // 出度数组
        int[] outDegrees = new int[n];
        // 遍历
        for (int[] t : trust) {
            // 入度 + 1
            inDegrees[t[1] - 1]++;
            // 出度 + 1
            outDegrees[t[0] - 1]++;
        }
        for (int i = 0; i < n; i++) {
            // 判断有没有入度为 n-1 出度为 0的点
            if (inDegrees[i] == n - 1 && outDegrees[i] == 0) {
                index = i;
                return index + 1;
            }
        }

        return index - 1;
    }

    public static void main(String[] args) {
        int[][] trust = {{1, 3}, {1, 4}, {2, 3}, {2, 4}, {4, 3}};
        System.out.println(findJudge(4, trust));
    }
}
