package LeetCode.Practise_Everyday.Date_2022_03;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Lil_boat
 * @Date: 11:16 2022/3/25
 * @Description: 给定一个整数 n ，返回 n! 结果中尾随零的数量。
 *
 * 提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
 */
public class TrailingZeroes_172 {

    /*
        对于任意一个 n! 而言，其尾随零的个数取决于展开式中 10 的个数，而 10 可由质因数 2 * 5 而来，
        因此 n! 的尾随零个数为展开式中各项分解质因数后「2 的数量」和「5 的数量」中的较小值。
        即问题转换为对 [1, n] 中的各项进行分解质因数，能够分解出来的 2 的个数和 5 的个数分别为多少。

        为了更具一般性，我们分析对 [1, n] 中各数进行分解质因数，能够分解出质因数 p 的个数为多少。
        根据每项能够分解出 p 的个数进行分情况讨论：
            * 能够分解出至少一个 p 的数为 p 的倍数，在 [1, n] 范围内此类数的个数为 c_1 = ⌊ n / p ⌋
            * 能够分解出至少两个 p 个数为 p^2的倍数，在 [1, n] 范围内此类数的个数为 c_2 = ⌊ n / p ^ 2⌋
            * ...
            * 能够分解出至少 k 个 p 的数为 p^k的倍数，在 [1, n] 范围内此类数的个数为 c_k = ⌊ n / p ^ k ⌋
        我们定义一个合法的 k 需要满足 p^k ⩽ n，上述的每一类数均是前一类数的「子集」（一个数如果是 p^k的倍数，必然是 p^{k-1}的倍数），
        因此如果一个数是 p^k的倍数，其出现在的集合数量为 k，与其最终贡献的 p 的数量相等。

        回到本题，n! 中质因数 2 的数量为 :
                    ∑⌊ n / 2^i ⌋ = ⌊ n / 2 ⌋ + ⌊ n / 2 ^ 2 ⌋ + ... + ⌊ n / 2 ^ k1 ⌋  i = 1 ~ k1
        n! 中质因数 5 的数量为 :
                    ∑⌊ n / 5^i ⌋ = ⌊ n / 5 ⌋ + ⌊ n / 5 ^ 2 ⌋ + ... + ⌊ n / 5 ^ k2 ⌋  i = 1 ~ k2
        由 2 < 5，可知 k2 ⩽ k1，同时 i 相同的每一项满足 ⌊ n / 5^i⌋ ⩽ ⌊ n / 2^i⌋，可知最终  ∑⌊ n / 5^i ⌋ ⩽ ∑⌊ n / 2^i ⌋，
        即质因数 5 的个数必然不会超过质因数 2 的个数。 我们只需要统计质因数 5 的个数即可。

     */
    public static int trailingZeroes(int n) {
        int count = 0;
        while(n >= 5) {
            count += n / 5;
            n /= 5;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(trailingZeroes(2));
    }

}
