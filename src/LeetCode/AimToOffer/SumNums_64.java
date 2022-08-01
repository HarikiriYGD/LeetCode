package LeetCode.AimToOffer;
/**
 * @Auther: Lil_boat
 * @Date: 14:47 2022/1/18
 * @Description: 求 1+2+...+n ，要求不能使用乘除法、for、while、if、else、switch、case等关键字及条件判断语句（A?B:C）。
 */
public class SumNums_64 {
    public int sumNums(int n) {
        boolean x = n > 1 && (n += sumNums(n - 1)) > 0;
        return n;
    }
}
