package LeetCode.Practise_Everyday.Date_2022_04;

import java.util.Arrays;
/**
 * @Auther: Lil_boat
 * @Date: 9:59 2022/4/12
 * @Tile: 写字符串需要的行数
 * @Description: 我们要把给定的字符串 S 从左到右写到每一行上，每一行的最大宽度为100个单位，
 * 如果我们在写某个字母的时候会使这行超过了100 个单位，
 * 那么我们应该把这个字母写到下一行。我们给定了一个数组 widths ，
 * 这个数组 widths[0] 代表 'a' 需要的单位， widths[1] 代表 'b' 需要的单位，...， widths[25] 代表 'z' 需要的单位。
 *
 * 现在回答两个问题：至少多少行能放下S，以及最后一行使用的宽度是多少个单位？将你的答案作为长度为2的整数列表返回。
 */
public class NumberOfLines_806 {

    public static int[] numberOfLines(int[] widths, String s) {
        int[] ans = new int[2];
        ans[0] = 1;
        for (char arr : s.toCharArray()) {
            // 统计需要多少个单位
            ans[1] += widths[arr - 'a'];
            // 如果超过了100个单位 另起一行
            if (ans[1] > 100) {
                ans[1] = widths[arr - 'a'];
                // 行数 + 1
                ans[0]++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] withds = {4, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10};
        String s = "dddbbbcccaaa";
        System.out.println(Arrays.toString(numberOfLines(withds, s)));
    }

}
