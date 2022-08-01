package LeetCode.String;

/**
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 * <p>
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 */
public class IsPalindrome {

    public static boolean isPalindrome(String s) {
        int left = 0; // 左指针
        int right = s.length() - 1; // 右指针
        while (left < right) {
            // 只考虑字母和数字，所以不是字母和数字的先过滤掉
            while (left < right && !Character.isLetterOrDigit(s.charAt(left)))
                left++;
            // 只考虑字母和数字，所以不是字母和数字的先过滤掉
            while (right > left && !Character.isLetterOrDigit(s.charAt(right)))
                right--;
            if (Character.toLowerCase(s.charAt(left)) != Character.toLowerCase(s.charAt(right))) return false;
            left++;
            right--;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = " ";
        boolean res = isPalindrome(s);
        System.out.println(res);
    }

}
