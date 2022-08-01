package LeetCode.Practise_Everyday.Pre_Practise;

/**
 * @Auther: Lil_boat
 * @Date: 11:03 2022/2/4
 * @Description: 给你一个数组 rectangles ，其中 rectangles[i] = [li, wi] 表示第 i 个矩形的长度为 li 、宽度为 wi 。
 * <p>
 * 如果存在 k 同时满足 k <= li 和 k <= wi ，就可以将第 i 个矩形切成边长为 k 的正方形。例如，矩形 [4,6] 可以切成边长最大为 4 的正方形。
 * <p>
 * 设 maxLen 为可以从矩形数组 rectangles 切分得到的 最大正方形 的边长。
 * <p>
 * 请你统计有多少个矩形能够切出边长为 maxLen 的正方形，并返回矩形 数目 。
 */
public class CountGodRectangles_1725 {
    public static int countGoodRectangles(int[][] rectangles) {
        int n = rectangles.length;
        int[] res = new int[n];
        int index = 0;
        for (int[] m : rectangles) {
            res[index++] = Math.min(m[0], m[1]);
        }
        // 定义最大正方形的边长
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (res[i] > max) max = res[i];
        }
        // 寻找最大正方形边长的个数
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (res[i] == max) count++;
        }
        return count;
    }

    public static void main(String[] args) {

    }
}

