package LeetCode.Else;

/**
 * @Auther: Lil_boat
 * @Date: 15:55 2021/12/7
 * @Description: 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。
 * 提示：
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，
 * 并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。。
 */
public class HammingWeight {

    // you need to treat n as an unsigned value
    public static int hammingWeight(int n) {
        int count = 0;
        // 移位操作
        for (int i = 0; i < 32; i++) {
            if ((n >> (i) & 1) == 1) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(hammingWeight(00000000000000000000000000001011));
    }

}
