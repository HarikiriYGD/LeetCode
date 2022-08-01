package LeetCode.Practise_Everyday.Pre_Practise;
/**
 * @Auther: Lil_boat
 * @Date: 10:07 2022/2/23
 * @Description: 给你一个字符串 s ，根据下述规则反转字符串：
 *
 * 所有非英文字母保留在原有位置。
 * 所有英文字母（小写或大写）位置反转。
 * 返回反转后的 s 。
 */
public class ReverseOnlyLetters_917 {

    public static String reverseOnlyLetters(String s) {
        // 头尾指针
        int left = 0, right = s.length() - 1;
        char[] c = s.toCharArray();
        while (left < right) {
            // 如果头尾都是字母 则交换 并且指针自加和自减
            if (Character.isLetter(s.charAt(left)) && Character.isLetter(s.charAt(right))) {
                char c1 = c[left];
                c[left] = c[right];
                c[right] = c1;
                left++;
                right--;
                // 如果遇到左指针不是字母而右指针是字母 则left++
            } else if (!Character.isLetter(s.charAt(left)) && Character.isLetter(s.charAt(right))) {
                left++;
                // 如果遇到右指针不是字母而右指针是字母 则right++
            } else if (Character.isLetter(s.charAt(left)) && !Character.isLetter(s.charAt(right))) {
                right--;
                // 如果都不是字母则都改变
            } else {
                left++;
                right--;
            }
        }
        return String.valueOf(c);
    }

    public static void main(String[] args) {
        String s = "a-bC-dEf-ghIj";
        System.out.println(reverseOnlyLetters(s));
    }

}
