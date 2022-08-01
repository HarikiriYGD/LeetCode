package LeetCode.Practise_Everyday.Date_2022_03;

import java.util.ArrayList;
import java.util.List;
/**
 * @Auther: Lil_boat
 * @Date: 12:54 2022/3/26
 * @Description: 你现在是一场采用特殊赛制棒球比赛的记录员。这场比赛由若干回合组成，过去几回合的得分可能会影响以后几回合的得分。
 *
 * 比赛开始时，记录是空白的。你会得到一个记录操作的字符串列表 ops，其中 ops[i] 是你需要记录的第 i 项操作，ops 遵循下述规则：
 *
 * 整数 x - 表示本回合新获得分数 x
 * "+" - 表示本回合新获得的得分是前两次得分的总和。题目数据保证记录此操作时前面总是存在两个有效的分数。
 * "D" - 表示本回合新获得的得分是前一次得分的两倍。题目数据保证记录此操作时前面总是存在一个有效的分数。
 * "C" - 表示前一次得分无效，将其从记录中移除。题目数据保证记录此操作时前面总是存在一个有效的分数。
 * 请你返回记录中所有得分的总和。
 *
 */
public class CalPoints_682 {

    public static int calPoints(String[] ops) {
        List<Integer> ans = new ArrayList<>();
        int n = ops.length;
        for (int i = 0; i < n; i++) {
            if (ops[i].equals("C")) ans.remove(ans.size() - 1);
            else if (ops[i].equals("D")) ans.add(ans.get(ans.size() - 1) * 2);
            else if (ops[i].equals("+")) ans.add(ans.get(ans.size() - 1) + ans.get(ans.size() - 2));
            else {
                ans.add(Integer.valueOf(ops[i]));
            }
        }
        int res = 0;
        for (Integer i : ans) {
            res += i;
        }
        return res;
    }

    public static void main(String[] args) {
        String[] ops = {"5","-2","4","C","D","9","+","+"};
        System.out.println(calPoints(ops));
    }

}
