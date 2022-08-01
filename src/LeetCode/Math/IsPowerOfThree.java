package LeetCode.Math;

/**
 * @Auther: Lil_boat
 * @Date: 17:34 2021/12/2
 * @Description: 给定一个整数，写一个函数来判断它是否是 3 的幂次方。如果是，返回 true ；否则，返回 false 。
 * 整数 n 是 3 的幂次方需满足：存在整数 x 使得 n == 3^x
 */
public class IsPowerOfThree {

    /**
     * 一直除以3判断最后是否等于1
     *
     * @param n
     * @return
     */
    public static boolean isPowerOfThree_Always(int n) {
        if (n > 1) {
            while (n % 3 == 0) {
                n /= 3;
            }
        }
        return n == 1;
    }

    /**
     * 递归的方式
     *
     * @param n
     * @return
     */
    public static boolean isPowerOfThree_Recursion(int n) {
        return n > 0 && (n == 1 || (n % 3 == 0 && isPowerOfThree_Recursion(n / 3)));
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfThree_Always(0));
        System.out.println(isPowerOfThree_Recursion(0));
    }

}
