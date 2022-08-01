package LeetCode.Practise_Everyday.Date_2022_04;

/**
 * @Auther: Lil_boat
 * @Date: 10:42 2022/4/22
 * @Tile: 旋转函数
 * @Description: 给定一个长度为 n 的整数数组 nums 。
 * <p>
 * 假设 arrk 是数组 nums 顺时针旋转 k 个位置后的数组，我们定义 nums 的 旋转函数  F 为：
 * <p>
 * F(k) = 0 * arrk[0] + 1 * arrk[1] + ... + (n - 1) * arrk[n - 1]
 * 返回 F(0), F(1), ..., F(n-1)中的最大值 。
 * <p>
 * 生成的测试用例让答案符合 32 位 整数。
 */
public class MaxRotateFunction_396 {

    /*
        F(0) = 0*A[0]+1*A[1]+2*A[2]+3*A[3]
        F(1) = 0*A[3]+1*A[0]+2*A[1]+3*A[2]
        F(2) = 0*A[2]+1*A[3]+2*A[0]+3*A[1]
        F(3) = 0*A[1]+1*A[2]+2*A[3]+3*A[0]

        F(1)-F(0) = A[0]+A[1]+A[2]-3*A[3]
        F(2)-F(1) = A[0]+A[1]+A[3]-3*A[2]
        F(3)-F(2) = A[0]+A[2]+A[3]-3*A[1]

        其中 我们定于sumA = A[0]+A[1]+A[2]+A[3],有意思了
        比如其中F(3)-F(2) = A[0]+A[2]+A[3]-3*A[1]= sumA - 4*A[1]

        延伸一下如果是一个长度为n+1的数组，
        F(n) = 0*A[0]+1*A[1]+...+(n)*A[n];
        F(n-1) = 0*A[1]+1*A[2]+2*A[3]+...+(n-1)*A[n]+n*A[0];

        F(n)-F(n-1)= sumA-nA[0] => F(i+1)-F(i)=sumA-n*A[n-i-1]

        是否如此呢？假设一个长度为n的数组
        F(k)   = 0*A[n-k]+...+(k-1)*A[n-1]+k*A[0]+(k+1)*A[1]+...+(n-1)*A[n-k-1]
        F(k+1) = 0*A[n-k-1]+...+(k)*A[n-1] +(k+1)*A[0]+(k+2)*A[1]+...+(n-1)*A[n-1-2]

        F(k+1)-F(k) = sumA - nA[n-k-1]
        证明结论成立。
     */
    public static int maxRotateFunction(int[] nums) {
        int n = nums.length, sum = 0;
        int F = 0;
        for (int i = 0; i < n; i++) {
            sum += nums[i];
            F += nums[i] * i;
        }
        int ans = F;
        for (int i = 1; i < n; i++) {
            F = F + sum - n * nums[n - i];
            ans = Math.max(ans, F);
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 6};
        System.out.println(maxRotateFunction(nums));
    }

}
