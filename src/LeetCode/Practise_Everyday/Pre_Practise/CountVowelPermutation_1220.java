package LeetCode.Practise_Everyday.Pre_Practise;

/**
 * @Auther: Lil_boat
 * @Date: 13:36 2022/1/18
 * @Description: 给你一个整数 n，请你帮忙统计一下我们可以按下述规则形成多少个长度为 n 的字符串：
 * 字符串中的每个字符都应当是小写元音字母（'a', 'e', 'i', 'o', 'u'）
 * 每个元音 'a' 后面都只能跟着 'e'
 * 每个元音 'e' 后面只能跟着 'a' 或者是 'i'
 * 每个元音 'i' 后面 不能 再跟着另一个 'i'
 * 每个元音 'o' 后面只能跟着 'i' 或者是 'u'
 * 每个元音 'u' 后面只能跟着 'a'
 * 由于答案可能会很大，所以请你返回 模 10^9 + 7 之后的结果。
 */
public class CountVowelPermutation_1220 {

    /*
    方法一：动态规划
        思路:
            题目中给定的字符的下一个字符的规则如下：
                字符串中的每个字符都应当是小写元音字母 ('a','e','i','o','u');
                    每个元音 'a' 后面都只能跟着 'e'；
                    每个元音 'e' 后面只能跟着 'a' 或者是 'i'；
                    每个元音 'i' 后面不能再跟着另一个 'i'；
                    每个元音 'o' 后面只能跟着 ‘i’ 或者是 'u'；
                    每个元音 'u' 后面只能跟着 'a'；
                以上等价于每个字符的前一个字符的规则如下：
                    元音字母 ‘a’ 前面只能跟着 ‘e’,‘i’,‘u’；
                    元音字母 ‘e’ 前面只能跟着 ‘a’,‘i’；
                    每个元音 ‘i’ 前面只能跟着 , ‘e’,‘o’；
                    每个元音 ‘o’ 前面只能跟着 ‘i’；
                    每个元音 ‘u’ 后面只能跟着 ‘o’,‘i’；
            我们设 dp[i][j] 代表当前长度为 i 且以字符 j 为结尾的字符串的数目，其中在此 j = {0,1,2,3,4}
            分别代表元音字母 ‘a’,‘e’,‘i’,‘o’,‘u’，通过以上的字符规则，我们可以得到动态规划递推公式如下： 
                            dp[i][0]=dp[i−1][1]+dp[i−1][2]+dp[i−1][4]
                            dp[i][1]=dp[i−1][0]+dp[i−1][2]
                            dp[i][2]=dp[i−1][1]+dp[i−1][3]
                            dp[i][3]=dp[i−1][2]
                            dp[i][4]=dp[i−1][2]+dp[i−1][3] 
​           按照题目规则最终可以形成长度为 n 的字符串的数目为：∑i=04dp[n][i]
            实际计算过程中只需要保留前一个状态即可推导出后一个状态，计算长度为 i 的状态只需要长度为 i-1 的中间变量即可，
            i-1 之前的状态全部都可以丢弃掉。计算过程中，答案需要取模 1e9+7。
     */
    private static int mod = (int) 1e9 + 7;

    public static int countVowelPermutation(int n) {
        long a = 1, e = 1, i = 1, o = 1, u = 1;
        for (int k = 2; k <= n; k++) {
            long aa = e % mod;
            long ee = (a + i) % mod;
            long ii = (a + e + u + o) % mod;
            long oo = (i+u)%mod;
            long uu = a %mod;
            a=aa;e=ee;i=ii;o=oo;u=uu;
        }
        return (int) ((a+e+i+o+u)%mod);
    }

    /*
    方法二：矩阵快速幂
        思路:已经知道上述的递推公式，可以转将其转化为矩阵乘法，设 f(n) 表示长度为 n 的字符串且以不同元音字母为结尾的组合数矩阵，构造矩阵的递推关系如下：
                   0 1 1 0 1
                   1 0 1 0 0
          f(n)=    0 1 0 1 0    ×f(n−1)
                   0 0 1 0 0
                   0 0 1 1 0
        因此我们可以推出:
                   0 1 1 0 1  ^ (n-1)
                   1 0 1 0 0
          f(n)=    0 1 0 1 0    ×f(1)
                   0 0 1 0 0
                   0 0 1 1 0
         令：f(1) = [1 1 1 1 1]
                    0 1 1 0 1
                    1 0 1 0 0
              M  =  0 1 0 1 0
                    0 0 1 0 0
                    0 0 1 1 0
        因此只要我们能快速计算矩阵 M 的 n 次幂，就可以得到 f(n) 的值。如果直接求取 M^n，时间复杂度是 O(n)，可以定义矩阵乘法，然后用快速幂算法来加速 M^n的求取。计算过程中，答案需要取模 1e9+7。
     */

    public static int countVowelPermutation_Matrix(int n) {
        long mod = 1_000_000_007;
        long[][] factor =
                {
                        {0, 1, 0, 0, 0},
                        {1, 0, 1, 0, 0},
                        {1, 1, 0, 1, 1},
                        {0, 0, 1, 0, 1},
                        {1, 0, 0, 0, 0}
                };

        long[][] res = fastPow(factor, n - 1, mod);
        long ans = 0;
        for (int i = 0; i < 5; ++i) {
            for (int j = 0; j < 5; ++j) {
                ans = (ans + res[i][j]) % mod;
            }
        }
        return (int)ans;
    }

    /**
     * 快速幂乘法
     * @param matrix
     * @param n
     * @param mod
     * @return
     */
    public static long[][] fastPow(long[][] matrix, int n, long mod) {
        int m = matrix.length;
        long[][] res = new long[m][m];
        long[][] curr = matrix;

        for (int i = 0; i < m; ++i) {
            res[i][i] = 1;
        }
        for (int i = n; i != 0; i >>= 1) {
            if ((i % 2) == 1) {
                res = multiply(curr, res, mod);
            }
            curr = multiply(curr, curr, mod);
        }
        return res;
    }

    /**
     * 矩阵的乘法
     * @param matrixA
     * @param matrixB
     * @param mod
     * @return
     */
    public static long[][] multiply(long[][] matrixA, long[][] matrixB, long mod) {
        int m = matrixA.length;
        int n = matrixB[0].length;
        long[][] res = new long[m][n];

        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                res[i][j] = 0;
                for (int k = 0; k < matrixA[i].length; ++k) {
                    res[i][j] = (res[i][j] + matrixA[i][k] * matrixB[k][j]) % mod;
                }
            }
        }
        return res;
    }


    public static void main(String[] args) {
        System.out.println(countVowelPermutation(2));
    }

}
