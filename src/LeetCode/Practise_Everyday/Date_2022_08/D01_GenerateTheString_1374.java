package LeetCode.Practise_Everyday.Date_2022_08;

/**
 * @Author: Lil_boat
 * @Date: 2022/8/1 19:54
 * @Title: 生成每种字符都是奇数个的字符串
 * @Description: 给你一个整数 n，请你返回一个含 n 个字符的字符串，其中每种字符在该字符串中都恰好出现 奇数次 。
 * 返回的字符串必须只含小写英文字母。如果存在多个满足题目要求的字符串，则返回其中任意一个即可
 * <p>
 * 链接：https://leetcode.cn/problems/generate-a-string-with-characters-that-have-odd-counts
 */
public class D01_GenerateTheString_1374 {

    public String generateTheString(int n) {
        StringBuilder sb = new StringBuilder();
        if (n % 2 == 0 && --n >= 0) sb.append('a');
        while (n-- > 0) sb.append('b');
        return sb.toString();
    }
}
