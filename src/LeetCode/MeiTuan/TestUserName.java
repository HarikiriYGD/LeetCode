package LeetCode.MeiTuan;

import java.util.Scanner;

/**
 * @Auther: Lil_boat
 * @Date: 11:22 2022/4/19
 * @Tile: 小美的用户名
 * @Description: 小美是美团的前端工程师，为了防止系统被恶意攻击，小美必须要在用户输入用户名之前做一个合法性检查，一个合法的用户名必须满足以下几个要求：
 * <p>
 * 用户名的首字符必须是大写或者小写字母。
 * 用户名只能包含大小写字母，数字。
 * 用户名需要包含至少一个字母和一个数字。
 * 如果用户名合法，请输出 "Accept"，反之输出 "Wrong"。
 */
public class TestUserName {

    public static String checkUserName(String username) {
        char[] cs = username.toCharArray();
        int[] cnt = new int[2];
        if (!Character.isLetter(cs[0])) return "Wrong";
        for (int i = 0; i < cs.length; i++) {
            if (!Character.isLetter(cs[i]) && !Character.isDigit(cs[i])) return "Wrong";
            if (Character.isDigit(cs[i])) cnt[0]++;
            else if (Character.isLetter(cs[i])) cnt[1]++;
        }
        if (cnt[0] >= 1 && cnt[1] >= 1) return "Accept";
        return "Wrong";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner (System.in);
        int t = sc.nextInt ();

        for (int i = 0; i < t; i++) {
            System.out.println(checkUserName(sc.next()));
        }
        sc.close ();

    }
}
