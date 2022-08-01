package LeetCode.Practise_Everyday.Pre_Practise;

/**
 * @Auther: Lil_boat
 * @Date: 11:35 2022/2/20
 * @Description: 有两种特殊字符：
 *
 * 第一种字符可以用一比特 0 表示
 * 第二种字符可以用两比特（10 或 11）表示
 * 给你一个以 0 结尾的二进制数组 bits ，如果最后一个字符必须是一个一比特字符，则返回 true 。
 */
public class IsOneBitCharacter_722 {

    /*
        从起点开始遍历，当遇到1时，这个1一定会把下一个0或1吃掉，因此这时需要跳过下一个。如果能遍历到最后一个0，就说明成功了。
     */
    public static boolean isOneBitCharacter(int[] bits) {
        int n = bits.length, idx = 0;
        while (idx < n - 1) {
            if (bits[idx] == 0) idx++;
            else idx += 2;
        }

        return idx == n - 1;
    }

    public static void main(String[] args) {
        int[] bits = {1,0};
        System.out.println(isOneBitCharacter(bits));
    }

}
