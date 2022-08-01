package LeetCode.AimToOffer;

import java.util.HashSet;
import java.util.Set;

/**
 * @Auther: Lil_boat
 * @Date: 9:56 2022/1/19
 * @Description: 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，xn）。不得使用库函数，同时不需要考虑大数问题。
 */
public class MyPow_16 {
    /*
        1. 当 x = 0.0 时：直接返回 0.0 ，以避免后续 1 除以 0 操作报错。
           分析： 数字 0 的正数次幂恒为 0 ； 0 的 0 次幂和负数次幂没有意义，因此直接返回 0.0 即可。
        2. 初始化 res = 1 。
        3. 当 n < 0 时：把问题转化至 n ≥ 0 的范围内，即执行 x = 1/x ，n = - n 。
        4. 循环计算：当 n = 0 时跳出。
            1. 当 n & 1 = 1 时：将当前 x 乘入 res （即 res *= x ）。
            2. 执行 x *= x 。
            3. 执行 n 右移一位（即 n >>= 1）。
        5. 返回 res 。
     */
    public static double myPow(double x, int n) {
        Set<Integer> s = new HashSet<>();
        if(x==0.0f)return 0.0d;
        double res = 1.0;
        long b = n;
        if(b<0){
            x=1/x;
            b=-b;
        }
        while(b > 0){
            if((b&1)==1)res*=x;
            x*=x;
            b=b>>1;
        }
        return res;
    }

    public static void main(String[] args) {
        myPow(2.0,10);
    }
}
