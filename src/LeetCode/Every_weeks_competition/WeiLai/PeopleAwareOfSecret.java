package LeetCode.Every_weeks_competition.WeiLai;

/**
 * @Author: Lil_boat
 * @Date: 2022/7/8 11:22
 * @Description: 在第 1 天，有一个人发现了一个秘密。
 * 给你一个整数 delay ，表示每个人会在发现秘密后的 delay 天之后，每天 给一个新的人 分享 秘密。
 * 同时给你一个整数 forget ，表示每个人在发现秘密 forget 天之后会 忘记 这个秘密。一个人 不能 在忘记秘密那一天及之后的日子里分享秘密。
 * 给你一个整数 n ，请你返回在第 n 天结束时，知道秘密的人数。由于答案可能会很大，请你将结果对 109 + 7 取余 后返回。
 * <p>
 * 链接：https://leetcode.cn/problems/number-of-people-aware-of-a-secret
 */
public class PeopleAwareOfSecret {

    /**
     * 定义dp记录第i天所有知道秘密的人数
     * 那么第i - forget天就是第i天忘记的人数
     * i - delay就是第i天可以分享的人数
     * 那么第i天可以分享秘密的人数 = 可分享人数 - 今天忘记人数。第i天 = 第i - 1天的总人数 + 分享人数。
     * 注意:第i天还包括前面忘记的人数，我们只是让他们不再分享。
     * 所以最后答案还需减去总共忘记的人数，即dp[n - forget]。复杂度O(n)
     */
    private final int MOD = (int) 1e9 + 7;

    public int peopleAwareOfSecret(int n, int delay, int forget) {
        long[] dp = new long[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            long t = i >= delay ? dp[i - delay] : 0;
            t -= i >= forget ? dp[i - forget] : 0;
            dp[i] = (dp[i - 1] + t + MOD) % MOD;
        }
        return (int) (dp[n] - dp[n - forget] + MOD) % MOD;
    }

}
