package LeetCode.Practise_Everyday.Date_2022_07;

/**
 * @Author: Lil_boat
 * @Date: 2022/7/27 13:23
 * @Title: 分数加减运算
 * @Description: 给定一个表示分数加减运算的字符串 expression ，你需要返回一个字符串形式的计算结果。 
 * 这个结果应该是不可约分的分数，即最简分数。 如果最终结果是一个整数，例如 2，你需要将它转换成分数形式，其分母为 1。
 * 所以在上述例子中, 2 应该被转换为 2/1。
 * <p>
 * 链接：https://leetcode.cn/problems/fraction-addition-and-subtraction
 */
public class D27_FractionAddition_592 {
    public String fractionAddition(String s) {
        int n = s.length();
        char[] cs = s.toCharArray();
        String ans = "";
        for (int i = 0; i < n; ) {
            int j = i + 1;
            while (j < n && cs[j] != '+' && cs[j] != '-') j++;
            // 将分数分隔出来
            String num = s.substring(i, j);
            if (cs[i] != '+' && cs[i] != '-') {
                num = "+" + num;
            }
            ans = !ans.equals("") ? calc(num, ans) : num;
            i = j;
        }
        return ans.charAt(0) == '+' ? ans.substring(1) : ans;
    }

    private String calc(String a, String b) {
        boolean fa = a.charAt(0) == '+', fb = b.charAt(0) == '+';
        if (!fa && fb) return calc(b, a);
        long[] p = parse(a), q = parse(b);
        long p1 = p[0] * q[1], q1 = q[0] * p[1];
        if (fa && fb) {
            long r1 = p1 + q1, r2 = p[1] * q[1], c = gcd(r1, r2);
            return "+" + (r1 / c) + "/" + (r2 / c);
        } else if (!fa && !fb) {
            long r1 = p1 + q1, r2 = p[1] * q[1], c = gcd(r1, r2);
            return "-" + (r1 / c) + "/" + (r2 / c);
        } else {
            long r1 = p1 - q1, r2 = p[1] * q[1], c = gcd(Math.abs(r1), r2);
            String ans = (r1 / c) + "/" + (r2 / c);
            return p1 >= q1 ? "+" + ans : ans;
        }

    }

    long[] parse(String s) {
        int n = s.length(), idx = 1;
        while (idx < n && s.charAt(idx) != '/') idx++;
        long a = Long.parseLong(s.substring(1, idx)), b = Long.parseLong(s.substring(idx + 1));
        return new long[]{a, b};
    }

    long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }
}

