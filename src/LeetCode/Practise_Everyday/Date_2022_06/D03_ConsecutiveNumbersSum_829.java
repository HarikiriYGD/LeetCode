package LeetCode.Practise_Everyday.Date_2022_06;

/**
 * @Auther: Lil_boat
 * @Date: 9:58 2022/6/3
 * @Tile: 连续整数求和
 * @Description: 给定一个正整数 n，返回 连续正整数满足所有数字之和为 n 的组数 。
 * https://leetcode.cn/problems/consecutive-numbers-sum/
 */
public class D03_ConsecutiveNumbersSum_829 {
    /*
        假设我们存在某个连续段之和为 n，假定该连续段首项为 a，项数为 k，根据「等差数列求和」可知：
                                    (a + a + k − 1) × k= 2n
        简单变形可得：
                                (2a + k − 1) × k = 2n ⇔ 2a = 2n / k − k + 1

        根据首项 a 和 k 均为正整数，可得：
                                2a = 2n / k − k + 1 ≥ 2
        进一步可得：
                                        2n / k ≥ k + 1 ⇔ 2n / k > k
        综上，根据 (2a + k − 1) × k = 2n 和 2n / k > k 可知，k 必然是 2n 的约数，并且为「较小」的约数。

        因此我们可以在 [1,sqrt[2n]) 范围内枚举 k，如果 k 为 2n 约数，并且结合 (2a + k − 1) × k = 2n 可验证 a 合法，
        说明找到了一组合法的 (a, k)，对答案进行累加。

     */
    public int consecutiveNumbersSum(int n) {
        int ans = 0;
        for (int k = 1; k * k < 2 * n; k++) {
            if (2 * n % k == 0 && (2 * n / k - k + 1) % 2 == 0) ans++;
        }
        return ans;
    }
}
