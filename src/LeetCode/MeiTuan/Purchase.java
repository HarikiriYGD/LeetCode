package LeetCode.MeiTuan;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Purchase {
    private static final long MOD = 998244353;
    private static List<Integer> list = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String[] line = br.readLine().split(" ");
        // 每个装饰品的价格不超过 n 元
        int n = Integer.parseInt(line[0]);
        // 共有 m 个空位放置装饰品
        int m = Integer.parseInt(line[1]);
        br.close();
        // 构建动规数组
        long[][] dp = new long[m][n + 1];
        for (int i = 1; i <= n; i++) dp[0][i] = 1L;
        for (int i = 0; i < m - 1; i++) {
            for (int j = 1; j <= n; j++) {
                for (int k = 1; j * k <= n; k++) {
                    dp[i + 1][j * k] = (dp[i + 1][j * k] + dp[i][j]) % MOD;
                }
            }
        }
        long ans = 0L;
        for (int i = 1; i <= n; i++) ans = (ans + dp[m - 1][i]) % MOD;
        bw.write(String.valueOf(ans));
        bw.close();
    }


}
