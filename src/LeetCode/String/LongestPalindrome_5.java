package LeetCode.String;

/**
 * @Auther: Lil_boat
 * @Date: 10:11 2021/12/21
 * @Description: 给你一个字符串 s，找到 s 中最长的回文子串。
 */
public class LongestPalindrome_5 {
    /**
     * 这种算法应该是最简洁和最优的解法了！
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (s == null || s.length() == 0) return null;
        // 保存回文串的起始位置
        int[] range = new int[2];
        char[] str = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            // 把回文看成中间的部分全是同一字符，左右部分相对称
            // 找到下一个与当前字符不同的字符
            i = findLongest(str, i, range);
        }
        return s.substring(range[0], range[1] + 1);
    }

    public static int findLongest(char[] str, int low, int[] range) {
        // 查找中间部分
        int high = low;
        // 从当前字符向右找，找到所有相同的字符作为一个整体，再把这个整体看作回文的中心，向左右扩展
        while (high < str.length - 1 && str[high + 1] == str[low]) {
            high++;
        }
        // 定位中间部分的最后一个字符
        int ans = high;
        // 从中间向左右扩散
        while (low > 0 && high < str.length - 1 && str[low - 1] == str[high + 1]) {
            low--;
            high++;
        }
        // 记录最大长度
        if (high - low > range[1] - range[0]) {
            range[1] = high;
            range[0] = low;
        }
        return ans;
    }

    /**
     * 中心扩散的思想
     * 分为奇数和偶数的时候
     *
     * @param s
     * @return
     */
    public static String longestPalindrome_Center_spread(String s) {
        // 边界判断条件
        if (s.length() < 2) return s;
        // start表示最长回文串开始的位置
        // maxLen表示回文串的长度
        int start = 0;
        int maxLen = 0;
        int length = s.length();
        for (int i = 0; i < length; ) {
            // 如果剩余子串长度小于目前查找到的最长回文子串的长度，终止循环
            if (length - i <= maxLen / 2) {
                break;
            }
            int left = i, right = i;
            // 过滤掉重复的
            while (right < length - 1 && s.charAt(right) == s.charAt(right + 1)) right++;

            // 下次在判断的时候从重复的下一个字符开始判断
            i = right + 1;
            // 往两边判断
            while (left > 0 && right < length - 1 && s.charAt(left - 1) == s.charAt(right + 1)) {
                left--;
                right++;
            }
            if (right - left + 1 > maxLen) {
                start = left;
                maxLen = right - left + 1;
            }
        }
        return s.substring(start, start + maxLen);
    }

    /**
     * 动态规划的方式
     * 定义二维数组dp[length][length]，
     * 如果dp[left][right]为true，则表示字符串从left到right是回文子串，
     * 如果dp[left][right]为false，则表示字符串从left到right不是回文子串。
     * 如果dp[left+1][right-1]为true，我们判断s.charAt(left)和s.charAt(right)是否相等，
     * 如果相等，那么dp[left][right]肯定也是回文子串，否则dp[left][right]一定不是回文子串。
     * <p>
     * 所以我们可以找出递推公式 dp[left][right]=s.charAt(left)==s.charAt(right)&&dp[left+1][right-1]
     * <p>
     * 有了递推公式，还要确定边界条件：
     * 如果s.charAt(left)！=s.charAt(right)，那么字符串从left到right是不可能构成子串的，直接跳过即可。
     * 如果s.charAt(left)==s.charAt(right)，字符串从left到right能不能构成回文子串还需要进一步判断
     * 如果left==right，也就是说只有一个字符，我们认为他是回文子串。即dp[left][right]=true（left==right）
     * 如果right-left<=2，类似于"aa"，或者"aba"，我们认为他是回文子串。即dp[left][right]=true（right-left<=2）
     * 如果right-left>2，我们只需要判断dp[left+1][right-1]是否是回文子串，才能确定dp[left][right]是否为true还是false。
     * 即dp[left][right]=dp[left+1][right-1]
     *
     * @param s
     * @return
     */
    public static String longestPalindrome_Dynamic_Programming(String s) {
        // 边界判断条件
        if (s.length() < 2) return s;
        //start表示最长回文串开始的位置，
        //maxLen表示最长回文串的长度
        int start = 0, maxLen = 1;
        int length = s.length();
        boolean[][] dp = new boolean[length][length];
        for (int right = 1; right < length; right++) {
            for (int left = 0; left < right; left++) {
                // 如果两种字符不相同，肯定不能构成回文子串
                if (s.charAt(left) != s.charAt(right)) continue;
                // 下面是s.charAt(left)和s.charAt(right)两个
                // 字符相同情况下的判断
                // 如果只有一个字符，肯定是回文子串
                if (right == left) {
                    dp[left][right] = true;
                } else if (right - left <= 2) {
                    dp[left][right] = true;
                } else {
                    // 类似于"a******a"，要判断他是否是回文子串，只需要
                    // 判断"******"是否是回文子串即可
                    dp[left][right] = dp[left + 1][right - 1];
                }
                // 如果字符串从left到right是回文子串，只需要保存最长的即可
                if (dp[left][right] && right - left + 1 > maxLen) {
                    maxLen = right - left + 1;
                    start = left;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }

    /**
     * Manacher(马拉车)算法
     *
     * 我们先看一下回文串，回文串有两种形式，一种是奇数的比如"aba"，一种是偶数的比如"abba"。
     * 这里使用Manacher算法的时候，会在每个字符之间都会插入一个特殊字符，
     * 并且两边也会插入，这个特殊字符要保证不能是原字符串中的字符，
     * 这样无论原来字符串长度是奇数还是偶数，添加之后长度都会变成奇数。
     * 例如 "aba"-->"#a#b#a#"（长度是7） "abba"-->"#a#b#b#a#"（长度是9）
     * <p>
     * 这里再来引用一个变量叫回文半径，通过添加特殊字符，
     * 原来字符串长度无论是奇数还是偶数最终都会变为奇数，
     * 因为特殊字符的引用，改变之后的字符串的所有回文子串长度一定都是奇数。
     * 并且回文子串的第一个和最后一个字符一定是你添加的那个特殊字符。其实很好证明
     * <p>
     * 如果原来回文子串的长度是奇数，通过中间插入特殊字符，
     * 特殊字符的个数必定是偶数，在加上两边的特殊字符，长度必然是奇数
     * <p>
     * 如果原来回文子串的长度是偶数，通过中间插入特殊字符，
     * 特殊字符的个数必定是奇数，在加上两边的特殊字符，长度必然是奇数
     * <p>
     * 因为添加特殊字符之后所有回文子串的长度都是奇数，
     * 我们定义回文子串最中间的那个字符到回文子串最左边的长度叫回文半径
     *
     * @param s
     * @return
     */
    public static String longestPalindrome_Manacher(String s) {
        int charLen = s.length();//源字符串的长度
        int length = charLen * 2 + 1;//添加特殊字符之后的长度
        char[] chars = s.toCharArray();//源字符串的字符数组
        char[] res = new char[length];//添加特殊字符的字符数组
        int index = 0;
        //添加特殊字符
        for (int i = 0; i < res.length; i++) {
            res[i] = (i % 2) == 0 ? '#' : chars[index++];
        }

        //新建p数组 ，p[i]表示以res[i]为中心的回文串半径
        int[] p = new int[length];
        //maxRight(某个回文串延伸到的最右边下标)
        //maxCenter(maxRight所属回文串中心下标),
        //resCenter（记录遍历过的最大回文串中心下标）
        //resLen（记录遍历过的最大回文半径）
        int maxRight = 0, maxCenter = 0, resCenter = 0, resLen = 0;
        //遍历字符数组res
        for (int i = 0; i < length; i++) {
            if (i < maxRight) {
                //情况一，i没有超出范围[left,maxRight]
                //2 * maxCenter - i其实就是j的位置，实际上是判断p[j]<maxRight - i
                if (p[2 * maxCenter - i] < maxRight - i) {
                    //j的回文半径没有超出范围[left,maxRight]，直接让p[i]=p[j]即可
                    p[i] = p[2 * maxCenter - i];
                } else {
                    //情况二，j的回文半径已经超出了范围[left,maxRight]，我们可以确定p[i]的最小值
                    //是maxRight - i，至于到底有多大，后面还需要在计算
                    p[i] = maxRight - i;
                    while (i - p[i] >= 0 && i + p[i] < length && res[i - p[i]] == res[i + p[i]])
                        p[i]++;
                }
            } else {
                //情况三，i超出了范围[left,maxRight]，就没法利用之前的已知数据，而是要一个个判断了
                p[i] = 1;
                while (i - p[i] >= 0 && i + p[i] < length && res[i - p[i]] == res[i + p[i]])
                    p[i]++;
            }
            //匹配完之后，如果右边界i + p[i]超过maxRight，那么就更新maxRight和maxCenter
            if (i + p[i] > maxRight) {
                maxRight = i + p[i];
                maxCenter = i;
            }
            //记录最长回文串的半径和中心位置
            if (p[i] > resLen) {
                resLen = p[i];
                resCenter = i;
            }
        }
        //计算最长回文串的长度和开始的位置
        resLen = resLen - 1;
        int start = (resCenter - resLen) >> 1;
        //截取最长回文子串
        return s.substring(start, start + resLen);
    }

    public static void main(String[] args) {
        String s = "aaabcbaaa";
        System.out.println("最优的解法：" + longestPalindrome(s));
        System.out.println("中心扩散的方法：" + longestPalindrome_Center_spread(s));
        System.out.println("动态规划的方法：" + longestPalindrome_Dynamic_Programming(s));
    }

}
