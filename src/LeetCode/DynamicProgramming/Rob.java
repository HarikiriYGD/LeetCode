package LeetCode.DynamicProgramming;


public class Rob {

    /**
     * 这里可以定义一个二维数组dp[length][2]，其中dp[i][0]表示第i+1（因为数组下标是从0开始的，所以这里是i+1）家偷了的最大总金额，dp[i][1]表示的是第i+1家没偷的最大总金额。那么我们找出递推公式
     * <p>
     * 1、dp[i][0]=max(dp[i-1][0],dp[i-1][1])
     * <p>
     * 他表示如果第i+1家没偷，那么第i家有没有偷都是可以的，我们取最大值即可。
     * <p>
     * 2、dp[i][1]=dp[i-1][0]+nums[i]
     * <p>
     * 他表示的是如果第i+1家偷了，那么第i家必须没偷，这里nums[i]表示的是第i+1家偷的金额。
     * <p>
     * 递推公式找出来之后我们再来看下边界条件，第一家可以选择偷，也可以选择不偷，所以
     * <p>
     * dp[0][0]=0，第一家没偷
     * <p>
     * dp[0][1]=nums[0]，第一家偷了
     *
     * @param nums
     * @return
     */

    public static int rob(int[] nums) {
        // 边界条件判断
        if (nums == null || nums.length == 0) return 0;
        int length = nums.length;
        int[][] dp = new int[length][2];
        // 第一家没偷
        dp[0][0] = 0;
        // 第一家偷了
        dp[0][1] = nums[0];
        for (int i = 1; i < length; i++) {
            // 表示第i家没偷，选择第i-1家最大的金额（可以是偷了或者没有偷）
            dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]);
            // 表示第i家偷了，则那么第i-1家肯定没有偷然后加上本身的金额
            dp[i][1] = dp[i - 1][0] + nums[i];
        }
        // 最后取最大值即可
        return Math.max(dp[length - 1][0], dp[length - 1][1]);
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 9, 3, 1};
        System.out.println(rob(nums));
    }

}
