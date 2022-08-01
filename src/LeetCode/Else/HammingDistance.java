package LeetCode.Else;

/**
 * @Auther: Lil_boat
 * @Date: 16:10 2021/12/7
 * @Description: 两个整数之间的 汉明距离 指的是这两个数字对应二进制位不同的位置的数目。
 * <p>
 * 给你两个整数 x 和 y，计算并返回它们之间的汉明距离。
 */
public class HammingDistance {

    public static int hammingDistance(int x, int y) {
        // 异或运算
        int tmp = x ^ y;
        int distance = 0;
        // 统计1的个数
        for (int i = 0; i < 32; i++) {
            if ((tmp >> (i) & 1) == 1) distance++;
        }
        return distance;
    }

    public static void main(String[] args) {
        System.out.println(hammingDistance(1, 4));
    }

}
