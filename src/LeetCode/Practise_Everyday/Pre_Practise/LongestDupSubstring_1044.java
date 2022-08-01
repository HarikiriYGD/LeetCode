package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * @Auther: Lil_boat
 * @Date: 14:34 2021/12/23
 * @Description: 给你一个字符串 s ，考虑其所有重复子串 ：
 * 即，s 的连续子串，在 s 中出现 2 次或更多次。
 * 这些出现之间可能存在重叠。
 * 返回任意一个 可能具有最长长度的重复子串。如果 s 不含重复子串，那么答案为 "" 。
 */
public class LongestDupSubstring_1044 {

    /**
     * 记 s的长度为 n。这个问题可以分为两步：
     * 从 n - 1到 1由大至小遍历选取长度 L，判断 s中是否有长度为 L的重复子串。
     * 从大至小遍历的时候，第一次遇到的符合条件的 L，即为最大的符合条件的 L，记为 L1，重复的子串为 s1。并
     * 且对于任意满足 L0 ≤ L1的 L0也均符合条件，因为 s1的所有子串也是 s 的重复子串。
     * 而对于任意满足 L2 > L1的 L2，则均不符合条件。因此，我们可以用二分查找的方法，来查找 L1。
     * <p>
     * 剩下的任务便是如何高效判断 s 中是否有长度为 L 的重复子串。
     * 我们可以使用 Rabin-Karp 算法对固定长度的字符串进行编码。
     * 当两个字符串的编码相同时，则这两个字符串也相同。在 s 中 n−L+1 个长度为 L 的子串中，
     * 有两个子串的编码相同时，则说明存在长度为 L 的重复子串。具体步骤如下：
     * <p>
     * 首先，我们需要对 s 的每个字符进行编码，得到一个数组 arr。因为本题中 s 仅包含小写字母，
     * 我们可按照 arr[i] = (int)s.charAt(i) - (int)‘a’，将所有字母编码为 0-25 之间的数字。
     * 比如字符串 “abcde" 可以编码为数组 [0, 1, 2, 3, 4]。
     * 我们将子串看成一个 26 进制的数，它对应的 10 进制数就是它的编码。
     * 假设此时我们需要求长度为 3 的子串的编码。
     * 那么第一个子串 “abc” 的编码就是 h0=0×26^2+1×26^1+2×26^0=28。
     * 更一般地，设 ci为 s 的第 i 个字符编码后的数字，a(a≥26) 为编码的进制，
     * 那么有 h0=c0a^(L−1)+c1a^(L−2)+...+cL−1a^1。
     * 上一步我们只求了第一个子串 “abc” 的编码。
     * 当我们要求第二个子串 “bcd” 的编码时，也可以按照上一步的方法求：h1=1×26^2+2×26^1+3×26^0=731，但是这样时间复杂度是 O(L)。
     * 我们可以在 h0的基础上，更快地求出它的编码：h1=(h0−0×262)×26+3×260=731。
     * 更一般的表达式是：h1=(h0×a−c0×a^L)+c(L+1)。
     * 这样，我们只需要在常数时间内就可以根据上一个子串的编码求出下一个子串的编码。
     * <p>
     * 我们用一个哈希表 seen 来存储子串的编码。
     * 在求子串的编码时，如果某个子串的编码出现过，则表示存在长度为 L 的重复子串，
     * 否则，我们将当前的编码放入 seen 中。如果所有编码都不重复，则说明不存在长度为 L 的重复子串。
     * 还有一点需要考虑的是，本题中 a^L会非常大。
     * 一般的做法是需要对编码进行取模来防止溢出，模一般选取编码的信息量的平方的数量级。
     * 而取模则会带来哈希碰撞。本题中为了避免碰撞，我们使用双哈希，即用两套进制和模的组合，来对字符串进行编码。
     * 只有两种编码都相同时，我们才认为字符串相同。
     * 本题要求返回最长重复子串而不是最长重复子串长度。
     * 因此，当存在长度为 L 的子串时，我们的判断函数可以返回重复子串的起点。而当不存在时，可以返回 -1用做区分。
     *
     * @param s
     * @return
     */
    public static String longestDupSubstring(String s) {
        Random random = new Random();
        // 生成两个进制
        int a1 = random.nextInt(75) + 26;
        int a2 = random.nextInt(75) + 26;
        // 生成两个模
        int mod1 = random.nextInt(Integer.MAX_VALUE - 1000000007 + 1) + 1000000007;
        int mod2 = random.nextInt(Integer.MAX_VALUE - 1000000007 + 1) + 1000000007;
        // 对所有的字符进行编码
        int n = s.length();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = s.charAt(i) - 'a';
        }
        // 二分查找的范围是[1, n-1]
        int left = 1, right = n - 1;
        int length = 0, start = -1;
        while (left <= right) {
            int mid = (right - left + 1) / 2 + left;
            int index = check(arr, mid, a1, a2, mod1, mod2);
            if (index != -1) {
                // 有重复子串，移动左边界
                left = mid + 1;
                length = mid;
                start = index;
            } else {
                // 无重复子串，移动右边界
                right = mid - 1;
            }
        }
        return start != -1 ? s.substring(start, start + length) : "";
    }

    public static int check(int[] arr, int mid, int a1, int a2, int mod1, int mod2) {
        int n = arr.length;
        long aL1 = pow(a1, mid, mod1);
        long aL2 = pow(a2, mid, mod2);
        long h1 = 0, h2 = 0;
        for (int i = 0; i < mid; i++) {
            h1 = (h1 * a1 % mod1 + arr[i]) % mod1;
            h2 = (h2 * a2 % mod2 + arr[i]) % mod2;
            if (h1 < 0) h1 += mod1;
            if (h2 < 0) h2 += mod2;
        }
        // 存储一个编码组合是否出现过
        Set<Long> seen = new HashSet<>();
        seen.add(h1 * mod2 + h2);
        for (int start = 1; start <= n - mid; ++start) {
            h1 = (h1 * a1 % mod1 - arr[start - 1] * aL1 % mod1 + arr[start + mid - 1]) % mod1;
            h2 = (h2 * a2 % mod2 - arr[start - 1] * aL2 % mod2 + arr[start + mid - 1]) % mod2;
            if (h1 < 0) {
                h1 += mod1;
            }
            if (h2 < 0) {
                h2 += mod2;
            }

            long num = h1 * mod2 + h2;
            // 如果重复，则返回重复串的起点
            if (!seen.add(num)) {
                return start;
            }
        }
        // 没有重复，则返回-1
        return -1;
    }

    public static long pow(int a, int mid, int mod) {
        long ans = 1;
        long contribute = a;
        while (mid > 0) {
            if (mid % 2 == 1) {
                ans = ans * contribute % mod;
                if (ans < 0) {
                    ans += mod;
                }
            }
            contribute = contribute * contribute % mod;
            if (contribute < 0) contribute += mod;
            mid /= 2;
        }
        return ans;
    }

    public static long[] h, p;

    /**
     * 题目要求得「能取得最大长度的任一方案」，首先以「最大长度」为分割点的数轴具有「二段性」：
     * <p>
     * 小于等于最大长度方案均存在（考虑在最大长度方案上做删减）；
     * 大于最大长度的方案不存在。
     * 二分范围为 [0, n]，关键在于如何 check 函数，即实现「检查某个长度 len 作为最大长度，是否存在合法方案」。
     * <p>
     * 对于常规做法而言，可枚举每个位置作为起点，得到长度为 len 的子串，
     * 同时使用 Set<String> 容器记录已被处理过子串有哪些，当容器中出现过当前子串，说明存在合法方案。
     * 但是该做法实现的 check 并非线性，子串的生成和存入容器的时执行的哈希函数执行均和子串长度相关，复杂度是 O(n * len)。
     * 我们可以通过「字符串哈希」进行优化。
     * 具体的，在二分前先通过 O(n) 的复杂度预处理出哈希数组，
     * 从而确保能够在 check 时能够 O(1) 得到某个子串的哈希值，最终将 check 的复杂度降为 O(n)。
     *
     * @param s
     * @return
     */
    public static String longestDupSubstring_StringHash_Binary(String s) {
        int P = 131313, n = s.length();
        h = new long[n + 10];
        p = new long[n + 10];
        p[0] = 1;
        for (int i = 0; i < n; i++) {
            p[i + 1] = p[i] * P;
            h[i + 1] = h[i] * P + s.charAt(i);
        }
        String ans = "";
        int left = 0, right = n;
        // 二分查找
        while (left < right) {
            int mid = (right - left + 1) / 2 + left;
            String t = check_String(s, mid);
            if (t.length() != 0) left = mid;
            else right = mid - 1;
            ans = t.length() > ans.length() ? t : ans;
        }
        return ans;
    }

    public static String check_String(String s, int len) {
        int n = s.length();
        Set<Long> set = new HashSet<>();
        for (int i = 1; i + len - 1 <= n; i++) {
            int j = i + len - 1;
            // 前缀和
            long hash = h[j] - h[i - 1] * p[j - i + 1];
            if (set.contains(hash)) return s.substring(i - 1, j);
            set.add(hash);
        }
        return "";
    }

    public static void main(String[] args) {
        String s = "acba";
        System.out.println(longestDupSubstring(s));
        System.out.println(longestDupSubstring_StringHash_Binary(s));
    }
}
