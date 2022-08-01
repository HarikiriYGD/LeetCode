package LeetCode.Practise_Everyday.Pre_Practise;

/**
 * @Auther: Lil_boat
 * @Date: 9:35 2022/1/4
 * @Description: 对于一个 正整数，如果它和除了它自身以外的所有 正因子 之和相等，我们称它为 「完美数」。
 * <p>
 * 给定一个 整数 n， 如果是完美数，返回 true，否则返回 false
 */
public class CheckPerfectNumber_507 {
    /*
        简单的数学思想
     */
    public static boolean checkPerfectNumber(int num) {
        if (num <= 1) return false;
        int sum = 1;
        for (int i = 2; i <= num / i; i++) {
            if (num % i == 0) {
                sum += i;
                if (i * i != num) sum += num / i;
            }
        }
        if (num == sum) return true;
        return false;
    }

    public static void main(String[] args) {
        System.out.println(checkPerfectNumber(6));
    }
}
