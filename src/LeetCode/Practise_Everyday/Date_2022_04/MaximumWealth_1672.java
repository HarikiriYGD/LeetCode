package LeetCode.Practise_Everyday.Date_2022_04;

/**
 * @Auther: Lil_boat
 * @Date: 9:39 2022/4/14
 * @Tile: 最富有客户的资产总量
 * @Description: 给你一个 m x n 的整数网格 accounts ，其中 accounts[i][j] 是第 i​位客户在第 j 家银行托管的资产数量。
 * 返回最富有客户所拥有的 资产总量 。
 * <p>
 * 客户的 资产总量 就是他们在各家银行托管的资产数量之和。最富有客户就是 资产总量 最大的客户。
 */
public class MaximumWealth_1672 {

    public int maximumWealth(int[][] accounts) {
        int m = accounts.length;
        int n = accounts[0].length;
        int ans = 0;
        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                sum += accounts[i][j];
            }
            ans = Math.max(sum, ans);
        }
        return ans;
    }

}
