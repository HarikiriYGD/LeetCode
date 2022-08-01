package LeetCode.Practise_Everyday.Pre_Practise;

/**
 * @Auther: Lil_boat
 * @Date: 9:34 2022/1/5
 * @Description: 给你一个仅包含小写英文字母和 '?' 字符的字符串 s，请你将所有的 '?' 转换为若干小写字母，使最终的字符串不包含任何 连续重复 的字符。
 * 注意：你 不能 修改非 '?' 字符。
 * 题目测试用例保证 除 '?' 字符 之外，不存在连续重复的字符。
 * 在完成所有转换（可能无需转换）后返回最终的字符串。如果有多个解决方案，请返回其中任何一个。可以证明，在给定的约束条件下，答案总是存在的。
 */
public class ModifyString_1576 {
    public static String modifyString(String s) {
        char[] ch = s.toCharArray();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            // 判断是否是第一个元素，如果是则pre是空
            char pre = i == 0 ? ' ' : ch[i - 1];
            // 判断是否是最后一个元素，如果是则next是空
            char next = i == n - 1 ? ' ' : ch[i + 1];
            if (ch[i] == '?') {
                ch[i] = 'a';
                // 如果当前元素和前后两个元素相等，则变成不相同的为止
                while (ch[i] == pre || ch[i] == next) {
                    ch[i]++;
                }
            }
        }
        return new String(ch);
    }

    public static void main(String[] args) {
        System.out.println(modifyString("a?r"));
    }
}
