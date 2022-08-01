package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.ArrayList;
import java.util.List;
/**
 * @Auther: Lil_boat
 * @Date: 16:02 2022/2/10
 * @Description: 给你一个整数 n ，请你返回所有 0 到 1 之间（不包括 0 和 1）满足分母小于等于  n 的 最简 分数 。分数可以以 任意 顺序返回。
 */
public class SimplifiedFractions_1447 {
    public static List<String> simplifiedFractions(int n) {
        if (n <= 1) return new ArrayList<>();
        List<String> ans = new ArrayList<>();
        for (int i = 1; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                // 分子是1的全部放进ans
                if (i == 1) {
                    ans.add(i + "/" + j);
                } else {
                    // 判断是否互质
                    int gcd = gcd(j, i);
                    // 如果最小公约数为1则表明是最简分数
                    if (gcd == 1) {
                        ans.add(i + "/" + j);
                    }
                }
            }
        }
        return ans;
    }

    /*
        判断两数是否互质
     */
    public static int gcd(int a, int b) {
        if (a % b == 0) return b;
        return gcd(b, a % b);
    }

    public static void main(String[] args) {
        List<String> list = simplifiedFractions(6);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
