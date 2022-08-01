package LeetCode.Practise_Everyday.Date_2022_03;

import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 9:48 2022/3/27
 * @Description: 现有一份 n + m 次投掷单个 六面 骰子的观测数据，骰子的每个面从 1 到 6 编号。
 * 观测数据中缺失了 n 份，你手上只拿到剩余 m 次投掷的数据。幸好你有之前计算过的这 n + m 次投掷数据的 平均值 。
 * <p>
 * 给你一个长度为 m 的整数数组 rolls ，其中 rolls[i] 是第 i 次观测的值。同时给你两个整数 mean 和 n 。
 * <p>
 * 返回一个长度为 n 的数组，包含所有缺失的观测数据，且满足这 n + m 次投掷的 平均值 是 mean 。
 * 如果存在多组符合要求的答案，只需要返回其中任意一组即可。如果不存在答案，返回一个空数组。
 * <p>
 * k 个数字的 平均值 为这些数字求和后再除以 k 。
 * 注意 mean 是一个整数，所以 n + m 次投掷的总和需要被 n + m 整除。
 */
public class MissingRolls_2028 {
    public static int[] missingRolls(int[] rolls, int mean, int n) {
        int m = rolls.length, cnt = m + n;
        int t = cnt * mean;
        for (int i : rolls) t -= i;
        // 如果缺失的平均值大于6 或者是 小于1 都是不可能发生的情况
        if (t > 6 * n || t < n) return new int[]{};
        // 缺失数组的集合
        int[] ans = new int[n];
        // 先全部分配平均数
        Arrays.fill(ans, t / n);
        // 与t之间的差值 idx索引
        int d = t - (t / n * n), idx = 0;
        // 分配个每个数组
        while (d-- > 0) ans[idx++]++;
        return ans;

    }

    public static void main(String[] args) {
        int[] rolls = {1, 2, 3, 4};
        int[] ints = missingRolls(rolls, 6, 4);
        for (int anInt : ints) {
            System.out.println(anInt);
        }
    }
}
