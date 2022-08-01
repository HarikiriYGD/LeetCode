package LeetCode.Practise_Everyday.Date_2022_04;
/**
 * @Auther: Lil_boat
 * @Date: 10:37 2022/4/16
 * @Tile:  最大回文数乘积
 * @Description: 给定一个整数 n ，返回 可表示为两个 n 位整数乘积的 最大回文整数 。因为答案可能非常大，所以返回它对 1337 取余 。
 */
public class LargestPalindrome_479 {

    /*
        对于数位为 n 的两个数而言，其乘积的位数要么是 2 * n，要么是 2 * n - 1。
        当数位 n > 1 时，我们总能在数位为 2 * n 中找到答案。
        利用回文串的特性，我们只需枚举回文串的前半部分即可（后半部分唯一确定），
        我们只要在枚举前半部分时按照「从大到小」进行，即可确保找到的第一个合法值为最大数，对于一个数位为 n 的最大数为 10^n - 1。
        具体的，当枚举到回文串的前半部分 i 时，我们利用回文串特性构造出具实际的回文数值 nums，
        随后检查 nums 能否分解成数位为 n 的数对 (a, b)，利用乘法具有交换律，我们只需要枚举数对中的较大数即可。

     */
    public static int largestPalindrome(int n) {
        if (n == 1) return 9;
        // 枚举最大的数
        int max = (int) (Math.pow(10, n) - 1);
        for (int i = max; i >= 0; i--) {
            long num = i, t = i;
            // 构造回文串
            while (t != 0) {
                num = num * 10 + (t % 10);
                t /= 10;
            }
            // 判断能否被整除
            for (long j = max; j * j >= num; j--) {
                if (num % j == 0) return (int) (num % 1337);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(largestPalindrome(2));
    }

}
