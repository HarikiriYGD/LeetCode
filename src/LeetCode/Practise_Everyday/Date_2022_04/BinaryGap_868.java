package LeetCode.Practise_Everyday.Date_2022_04;

/**
 * @Auther: Lil_boat
 * @Date: 9:41 2022/4/24
 * @Tile: 二进制间距
 * 给定一个正整数 n，找到并返回 n 的二进制表示中两个 相邻 1 之间的 最长距离 。如果不存在两个相邻的 1，返回 0 。
 * 如果只有 0 将两个 1 分隔开（可能不存在 0 ），则认为这两个 1 彼此 相邻 。
 * 两个 1 之间的距离是它们的二进制表示中位置的绝对差。例如，"1001" 中的两个 1 的距离为 3
 */
public class BinaryGap_868 {

    public static int binaryGap(int n) {
        int ans = 0;
        for (int i = 31, j = -1; i >= 0; i--) {
            // 左移 i 位，并与 1 相与 看看是否等于1
            if (((n >> i) & 1) == 1) {
                if (j != -1) ans = Math.max(ans, j - 1);
                j = i;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int n = 8;
        System.out.println(binaryGap(22));
    }

}
