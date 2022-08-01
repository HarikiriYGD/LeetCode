package LeetCode.Practise_Everyday.Date_2022_04;

/**
 * @Auther: Lil_boat
 * @Date: 9:23 2022/4/2
 * @Description: 如果一个密码满足下述所有条件，则认为这个密码是强密码：
 * * 由至少 6 个，至多 20 个字符组成。
 * * 至少包含 一个小写 字母，一个大写 字母，和 一个数字 。
 * * 同一字符 不能 连续出现三次 (比如 "...aaa..." 是不允许的, 但是 "...aa...a..." 如果满足其他条件也可以算是强密码)。
 * 给你一个字符串 password ，返回 将 password 修改到满足强密码条件需要的最少修改步数。如果 password 已经是强密码，则返回 0 。
 * <p>
 * 在一步修改操作中，你可以：
 * <p>
 * 插入一个字符到 password ，
 * 从 password 中删除一个字符，或
 * 用另一个字符来替换 password 中的某个字符。
 */
public class StrongPasswordChecker_420 {

    public static int strongPasswordChecker(String password) {
        // 密码的长度
        int n = password.length();
        char[] chars = password.toCharArray();
        // 统计字符的种类个数
        int A = 0, B = 0, C = 0;
        for (char c : chars) {
            if (c >= 'a' && c <= 'z') A = 1;
            else if (c >= '0' && c <= '9') B = 1;
            else C = 1;
        }
        int m = A + B + C;
        /**
         * n < 6：长度过短，不满足要求，任何一次「删除」操作都需要额外搭配一个「增加」操作，
         * 而这两步操作可以使用「替换」来代替，结果不会变差；
         * 同时为了满足长度要求，我们必然要使用到「增加」操作。
         * 因此需要用到「增加」和「替换」操作，枚举所有的情况发现，最少操作次数最终可以归纳到 max(6 - n, 3 - m)；
         */
        if (n < 6) return Math.max(6 - n, 3 - m);
        /**
         * 6 ⩽ n ⩽ 20：任何的有效的「增加」操作目的只能是为了「破坏连续段长度不低于 3」或者「增加字符种类数量」，
         * 这两个目的都可以使用「替换」来做到；
         * 而任何有效的「删除」操作只能是为了「破坏连续段长度不低于 3」，这一目的也可以使用「替换」来做到。
         * 因此只需要用到「替换」操作，结过不会变差。对于某个 g[i] 而言，
         * 我们需要使用 ⌊g[i]/3⌋ 次「替换」操作来满足「连续段长度不能不低于 3」的要求，
         * 在此基础上再考虑字符种类的问题，最少操作次数最终可以归纳到 max(∑⌊g[i]/3⌋ , 3−m)；
         */
        else if (n <= 20) {
            int tot = 0;
            for (int i = 0; i < n; ) {
                int j = i;
                while (j < n && chars[j] == chars[i]) j++;
                int cnt = j - i;
                if (cnt >= 3) tot += cnt / 3;
                i = j;
            }
            return Math.max(tot, 3 - m);
            /**
             * n > 20：长度过长，不满足要求，任何一次「增加」操作都需要额外搭配一个「删除」操作，
             * 只需要用到「替换」和「删除」操作，为了满足长度要求，必然用到的「删除」操作可能会影响到最终的「替换」操作，
             * 直觉上，应当优先删除那些「连续段长度不低于 3」的字符。由于连续段长度 g[i] 与其消耗的「替换」次数的关系为 ⌊g[i]/3⌋，
             * 在不考虑余数的情况下，每删除 3 个字符，能够连带的减少一次「替换」操作。
             */
        } else {
            int tot = 0;
            int[] remain = new int[3];
            for (int i = 0; i < n; ) {
                int j = i;
                // 检验密码中连续的长度
                while (j < n && chars[j] == chars[i]) j++;
                int cnt = j - i;
                if (cnt >= 3) {
                    // 相当于一次替换操作
                    tot += cnt / 3;
                    remain[cnt % 3]++;
                }
                i = j;
            }
            // base删除次数
            int base = n - 20, cur = base;
            for (int i = 0; i < 3; i++) {
                if (i == 2) remain[i] = tot;
                if (remain[i] != 0 && cur > 0) {
                    int t = Math.min(remain[i] * (i + 1), cur);
                    cur -= t;
                    tot -= t / (i + 1);
                }
            }
            return base + Math.max(tot, 3 - m);
        }
    }

    public static boolean isThreeSame(String password) {
        int n = password.length();
        char[] chars = password.toCharArray();
        // 判断是否有三个连续一样的
        for (int i = 1; i < n - 1; i++) {
            if (chars[i - 1] == chars[i] && chars[i] == chars[i + 1]) return false;
        }
        return true;
    }

    public static boolean isValid(String password) {
        int n = password.length();
        // 统计数字 大写字母 小写字母的个数
        int numCnt = 0, upperCnt = 0, lowerCnt = 0;
        for (int i = 0; i < n; i++) {
            if (password.charAt(i) >= '0' && password.charAt(i) <= '9') numCnt++;
            else if (password.charAt(i) >= 'a' && password.charAt(i) <= 'z') lowerCnt++;
            else if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') upperCnt++;
        }
        return numCnt >= 1 && lowerCnt >= 1 && upperCnt >= 1;
    }

    public static void main(String[] args) {
        String password = "a1A533r";
        System.out.println(strongPasswordChecker(password));
    }

}
