package LeetCode.Practise_Everyday.Date_2022_08;

/**
 * @Author: Lil_boat
 * @Date: 2022/8/28 20:37
 * @Title: 阶乘函数后 K 个零
 * @Description:  f(x) 是 x! 末尾是 0 的数量。回想一下 x! = 1 * 2 * 3 * ... * x，且 0! = 1 。
 * <p>
 * 例如， f(3) = 0 ，因为 3! = 6 的末尾没有 0 ；而 f(11) = 2 ，因为 11!= 39916800 末端有 2 个 0 。
 * 给定 k，找出返回能满足 f(x) = k 的非负整数 x 的数量。
 * <p>
 * 链接：https://leetcode.cn/problems/preimage-size-of-factorial-zeroes-function
 */
public class D28_PreimageSizeFZF_793 {

    public int preimageSizeFZF(int k) {
        if (k <= 1) {
            return 5;
        }
        return f(k) - f(k - 1);
    }

    private int f(int x) {
        long l = 0, r = (long) 10e9;
        while (l < r) {
            long mid = l + r + 1 >> 1;
            if (getCnt(mid) <=x) l = mid;
            else r = mid - 1;
        }
        return (int) r;
    }

    private int getCnt(long x) {
        int ans = 0;
        while ( x != 0){
            ans += x/ 5;
            x/=5;
        }
        return ans;
    }

    public static void main(String[] args) {
        D28_PreimageSizeFZF_793 t = new D28_PreimageSizeFZF_793();
        System.out.println(t.preimageSizeFZF(7));
    }

}
