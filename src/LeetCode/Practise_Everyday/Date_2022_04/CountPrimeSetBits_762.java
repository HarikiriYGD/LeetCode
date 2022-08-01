package LeetCode.Practise_Everyday.Date_2022_04;
/**
 * @Auther: Lil_boat
 * @Date: 23:07 2022/4/5
 * @Description: 给你两个整数 left 和 right ，在闭区间 [left, right] 范围内，统计并返回 计算置位位数为质数 的整数个数。
 * 计算置位位数 就是二进制表示中 1 的个数。
 * 例如， 21 的二进制表示 10101 有 3 个计算置位。
 */
public class CountPrimeSetBits_762 {

    public static int countPrimeSetBits(int left, int right) {
        int ans = 0;
        for(int i = left; i <= right; i++){
            String s = Integer.toString(i,2);
            char[] c = s.toCharArray();
            int cnt = 0;
            for(int j = 0; j < c.length; j++){
                if(c[j]=='1')cnt++;
            }
            if(isPrimeNum(cnt))ans++;
        }
        return ans;
    }
    private static boolean isPrimeNum(int i) {
        for (int j = i == 2 ? 3 : 2; j < Math.sqrt(i); j++) {
            if (i % j == 0) return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(countPrimeSetBits(6, 10));
    }

}
