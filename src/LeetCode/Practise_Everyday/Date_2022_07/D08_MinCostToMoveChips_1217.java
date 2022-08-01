package LeetCode.Practise_Everyday.Date_2022_07;

/**
 * @Author: Lil_boat
 * @Date: 2022/7/8 11:14
 * @Title: 玩筹码
 * @Description: 有 n 个筹码。第 i 个筹码的位置是 position[i] 。
 * 我们需要把所有筹码移到同一个位置。在一步中，我们可以将第 i 个筹码的位置从 position[i] 改变为:
 * position[i] + 2 或 position[i] - 2 ，此时 cost = 0
 * position[i] + 1 或 position[i] - 1 ，此时 cost = 1
 * 返回将所有筹码移动到同一位置上所需要的 最小代价 。
 * <p>
 * 链接：https://leetcode.cn/problems/minimum-cost-to-move-chips-to-the-same-position
 */
public class D08_MinCostToMoveChips_1217 {

    public int minCostToMoveChips(int[] position) {
        // x 代表奇数位置上的筹码
        // y 代表偶数位置上的筹码
        int x = 0, y = 0;
        // 统计个数
        for (int chip : position) {
            if (chip % 2 == 0) {
                x++;
            } else {
                y++;
            }
        }
        // 奇数位上的筹码少则移动的代价就小
        // 偶数位上的筹码少则移动的代价就小
        return x > y ? y : x;
    }

}
