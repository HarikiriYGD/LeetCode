package LeetCode.Practise_Everyday.Date_2022_06;

/**
 * @Auther: Lil_boat
 * @Date: 9:44 2022/6/30
 * @Tile: 质数排列
 * @Description: 请你帮忙给从 1 到 n 的数设计排列方案，使得所有的「质数」都应该被放在「质数索引」（索引从 1 开始）上；你需要返回可能的方案总数。
 * 让我们一起来回顾一下「质数」：质数一定是大于 1 的，并且不能用两个小于它的正整数的乘积来表示。
 * 由于答案可能会很大，所以请你返回答案 模 mod 10^9 + 7 之后的结果即可。
 * <p>
 * 链接：https://leetcode.cn/problems/prime-arrangements
 */
public class D30_NumPrimeArrangements_1175 {
    private final int MOD = (int) 1e9 + 7;

    public int numPrimeArrangements(int n) {
        if (n == 1) {
            return 1;
        }
        // 构建结果集
        long ans = 1;
        // 计算质数的个数
        int count = 0;

        // 计算n以内有多少个质数
        for (int i = 2; i <= n; i++) {
            boolean isPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    isPrime = false;
                    break;
                }
            }
            if (isPrime) {
                count++;
            }
        }
        // 质数的全排列
        for (int i = count; i > 0; i--) {
            ans = (i * ans) % MOD;
        }
        // 合数的全排列
        for (int i = 1; i <= n - count; i++) {
            ans = (ans * i) % MOD;
        }
        return (int) ans % MOD;
    }


    public static void main(String[] args) {
        D30_NumPrimeArrangements_1175 t = new D30_NumPrimeArrangements_1175();
        System.out.println(t.numPrimeArrangements(100));
    }
}


