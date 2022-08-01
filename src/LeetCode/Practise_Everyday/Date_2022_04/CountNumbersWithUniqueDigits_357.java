package LeetCode.Practise_Everyday.Date_2022_04;
/**
 * @Auther: Lil_boat
 * @Date: 10:01 2022/4/11
 * @Description: 给你一个整数 n ，统计并返回各位数字都不同的数字 x 的个数，其中 0 <= x < 10^n 。
 */
public class CountNumbersWithUniqueDigits_357 {

    /*
        考虑首位不为0，第一位能选 1~9，9个，
        第二位能选 0~9 中处了第一位以外的 9 个，
        第三位 8 个。。
        以此类推
        考虑首位为 0，相当于 n-1 的情况，在前面已经计算过了
        所以最终结果就是两者相加
    */
    public static int countNumbersWithUniqueDigits(int n) {
        int res = 1;
        int product = 9;
        for (int i = 1; i <= n && i < 10; i++) {
            res = product + res;
            product *= (10 - i);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(countNumbersWithUniqueDigits(2));
    }

}
