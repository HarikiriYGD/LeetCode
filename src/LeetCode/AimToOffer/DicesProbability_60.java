package LeetCode.AimToOffer;

import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 17:11 2022/1/4
 * @Description: 把 n 个骰子扔在地上，所有骰子朝上一面的点数之和为s。输入n，打印出s的所有可能的值出现的概率。
 * 你需要用一个浮点数数组返回答案，其中第 i 个元素代表这 n 个骰子所能掷出的点数集合中第 i 小的那个的概率。
 */
public class DicesProbability_60 {
    /*
        动态规划:
            设输入 n 个骰子的解（即概率列表）为 f(n) ，其中「点数和」 x 的概率为 f(n, x) 。
            假设已知 n - 1 个骰子的解 f(n - 1) ，此时添加一枚骰子，求 n 个骰子的点数和为 x 的概率 f(n, x) 。
            当添加骰子的点数为 1 时，前 n - 1 个骰子的点数和应为 x - 1 ，方可组成点数和 x ；
            同理，当此骰子为 2 时，前 n - 1 个骰子应为 x - 2 ；以此类推，直至此骰子点数为 6 。将这 6 种情况的概率相加，即可得到概率 f(n, x) 。
            递推公式如下所示：
                                    f(n,x)= ∑ f(n−1,x−i) × 1/6 , i = {1,2,..,6}
            根据以上分析，得知通过子问题的解 f(n - 1) 可递推计算出 f(n) ，而输入一个骰子的解 f(1) 已知，因此可通过解 f(1) 依次递推出任意解 f(n) 。
            观察发现，以上递推公式虽然可行，但 f(n - 1, x - i) 中的 x - i 会有越界问题。
            例如，若希望递推计算 f(2, 2) ，由于一个骰子的点数和范围为 [1, 6]，因此只应求和 f(1, 1) ，即 f(1, 0) , f(1, -1) , ... , f(1, -4) 皆无意义。
            此越界问题导致代码编写的难度提升。
            以上递推公式是 “逆向” 的，即为了计算 f(n, x)，将所有与之有关的情况求和；而倘若改换为 “正向” 的递推公式，便可解决越界问题。
            具体来看，由于新增骰子的点数只可能为 1 至 6 ，因此概率 f(n - 1, x) 仅与 f(n, x + 1) , f(n, x + 2), ... , f(n, x + 6) 相关。
            因而，遍历 f(n - 1) 中各点数和的概率，并将其相加至 f(n) 中所有相关项，即可完成 f(n - 1) 至 f(n) 的递推。
     */
    public static double[] dicesProbability(int n) {
        double[] dp = new double[6];
        Arrays.fill(dp, 1.0 / 6.0);
        // 如果骰子数大于等于2
        // 我们就要增加骰子数
        for (int i = 2; i <= n; i++) {
            double[] temp = new double[i * 5 + 1];
            for (int j = 0; j < dp.length; j++) {
                for (int k = 0; k < 6; k++) {
                    temp[j + k] += dp[j] / 6.0;
                }
            }
            dp = temp;
        }
        return dp;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(dicesProbability(2)));
    }
}
