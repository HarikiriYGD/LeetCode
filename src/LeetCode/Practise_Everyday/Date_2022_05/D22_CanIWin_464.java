package LeetCode.Practise_Everyday.Date_2022_05;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Lil_boat
 * @Date: 10:23 2022/5/22
 * @Tile: 我能赢吗
 * @Description: 在 "100 game" 这个游戏中，两名玩家轮流选择从 1 到 10 的任意整数，累计整数和，先使得累计整数和 达到或超过  100 的玩家，即为胜者。
 * 如果我们将游戏规则改为 “玩家 不能 重复使用整数” 呢？
 * 例如，两个玩家可以轮流从公共整数池中抽取从 1 到 15 的整数（不放回），直到累计整数和 >= 100。
 * 给定两个整数 maxChoosableInteger （整数池中可选择的最大数）和 desiredTotal（累计和），若先出手的玩家是否能稳赢则返回 true ，否则返回 false 。
 * 假设两位玩家游戏时都表现 最佳 。
 * <p>
 * 链接：https://leetcode.cn/problems/can-i-win
 */
public class D22_CanIWin_464 {

    Map<Integer, Boolean> memory = new HashMap<>();

    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        // 所有数之和都小于累加和 则没有人会赢
        if ((1 + maxChoosableInteger) * maxChoosableInteger * 0.5 < desiredTotal) return false;
        // 递归判断
        return dfs(maxChoosableInteger, 0, desiredTotal, 0);
    }

    private boolean dfs(int maxChoosableInteger, int usedNumber, int desiredTotal, int currentTotal) {
        if (!memory.containsKey(usedNumber)) {
            boolean res = false;
            // 如果遍历完所有的数都不能胜利 则返回false
            for (int i = 0; i < maxChoosableInteger; i++) {
                // 判断该数使用过没有
                if (((usedNumber >> i) & 1) == 0) {
                    // 如果选完能直接胜利，那么结果自然为true
                    if (i + 1 + currentTotal >= desiredTotal) {
                        res = true;
                        break;
                    }
                    if (!dfs(maxChoosableInteger, usedNumber | (1 << i), desiredTotal, currentTotal + i + 1)) {
                        res = true;
                        break;
                    }
                }
            }
            // 把结果放入记忆化
            memory.put(usedNumber, res);
        }
        return memory.get(usedNumber);
    }

    public static void main(String[] args) {
        D22_CanIWin_464 t = new D22_CanIWin_464();
        System.out.println(t.canIWin(10, 11));
    }

}
