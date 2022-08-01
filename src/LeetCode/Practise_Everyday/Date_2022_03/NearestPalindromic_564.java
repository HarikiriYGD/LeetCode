package LeetCode.Practise_Everyday.Date_2022_03;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: Lil_boat
 * @Date: 13:06 2022/3/2
 * @Description: 给定一个表示整数的字符串 n ，返回与它最近的回文整数（不包括自身）。如果不止一个，返回较小的那个。
 * “最近的”定义为两个整数差的绝对值最小。
 */
public class NearestPalindromic_564 {

    /*
        【上下界分析 + 边界处理】
            对于任意字符串数值 s 而言（令其长度为 n），先考虑如何找到「第一个比其大」和「第一个比其小」的回文串数值（上下界）。
            由于是找「最近」的数值，因此一个自然的想法是优先修改低位数字，但由于回文串本身的对称性质，每个「低位」的修改带来的副作用是需要同时修改对应的「高位」，
            因此一个真正可以修改的位置是（相对）中间的位置。
            举个例子，对于长度为奇数回文串数值 abcde，其中 de 的修改会导致 ab 同步修改，因此最近一个可以修改的低位是 c；
            对于长度为偶数的回文串数值 abcd而言，其中 d 的修改会导致 a 同步修改，因此最近一个可以修改的位置是 bc。
            当对目标位置进行修改后，其余位置的数值可由回文串性质唯一确定，因此只需要找到可修改位置相邻数值即可。

            例如对于 abcde 来说，最近的回文数值的前三位可能是 abc、abc+1 和 abc-1 三者之一，其他位置的数值随着前三位的确定而唯一确定。
            上述分析对于一般情况成立，而边界情况是指 abc + 1 和 abc - 1 导致整体长度发生变化，即 abc=999 和 abc=100 的情况，
            此时直接套用上述方法得到的值分别为 1000001 和 999，但真实最近的值为 100001 和 9999。
            展开来说就是：对于 abc = 999（对应原串为 999xx 的情况）而言，按照上述逻辑得到的是 1000，
            进行回文补充后得到 1000001（事实上应该是 100001 更优）；对于 abc = 100（对应原串为 100xx 的情况）而言，按照上述逻辑得到是 99，
            进行回文补充后得到 999（事实上应该是 9999 更优）。

            因此对于长度为 n 的数值，值考虑枚举前一半数的三种情况（前一半数不变，前一半数加一或减一）可能会过错最优解，
            我们需要将与长度相关的边界值也纳入考虑，即将「长度为 n - 1 的回文串最大值（99...99）」
            和「长度为 n + 1 的回文串最小值（10...01）」也纳入考虑，也就是对应的 10^{n - 1} - 1和 10^n + 1也纳入考虑。
            然后从上述 5 个值中选绝对差值最小的作为答案。

            一些细节：在枚举完前一半数后，我们需要根据原串长度的奇偶性来决定，如何生成后一半数，从而确保构建的回文串长度仍和原串长度相等。
            即对于原串长度 n 为奇数的 abcde 而言，在处理完 abc 之后，生成后一半数时，
            只需要根据前两位 ab 生成对应的 ba，而无须处理中间字符 c；而对于原串长度 n 为偶数的 abcd 而言，在处理完 ab 之后，
            生成另外一般数值需要根据整一个 ab 来生成 ba，没有中间字符需要被跳过。
     */
    public static String nearestPalindromic(String s) {
        int n = s.length();
        // 当前字符串所代表的数字
        long cur = Long.parseLong(s);
        Set<Long> set = new HashSet<>();
        // 直接构造 999…999 和 100…001 作为备选答案
        set.add((long) Math.pow(10, (n - 1)) - 1);
        set.add((long) Math.pow(10, n) + 1);
        // 因此对于长度为 n 的数值，值考虑枚举前一半数的三种情况（前一半数不变，前一半数加一或减一）
        long t = Long.parseLong(s.substring(0, (n + 1) / 2));
        for (long i = t - 1; i <= t + 1; i++) {
            long temp = getNum(i, n % 2 == 0);
            if (cur != temp) set.add(temp);
        }
        long ans = -1;
        for (Long i : set) {
            if (ans == -1) ans = i;
            // 比较哪个绝对值更小
            else if (Math.abs(i - cur) < Math.abs(ans - cur)) ans = i;
            else if (Math.abs(i - cur) == Math.abs(ans - cur) && i < ans) ans = i;
        }
        return String.valueOf(ans);
    }

    /**
     *
     * @param k 原数的前半部分替
     * @param isEven 长度是奇数还是偶数
     * @return
     */
    public static long getNum(long k, boolean isEven) {
        StringBuilder sb = new StringBuilder();
        sb.append(k);
        int n = sb.length(), idx = isEven ? n - 1 : n - 2;
        while (idx >= 0) sb.append(sb.charAt(idx--));
        return Long.parseLong(sb.toString());
    }

    public static void main(String[] args) {
        System.out.println(nearestPalindromic("999"));
    }

}
