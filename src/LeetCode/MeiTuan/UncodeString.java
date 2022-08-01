package LeetCode.MeiTuan;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @Auther: Lil_boat
 * @Date: 11:13 2022/4/21
 * @Tile: 小团的神秘暗号
 * @Description: 小团深谙保密工作的重要性，因此在某些明文的传输中会使用一种加密策略，
 * 小团如果需要传输一个字符串 S ，则他会为这个字符串添加一个头部字符串和一个尾部字符串。
 * 头部字符串满足至少包含一个 “MT” 子序列，且以 T 结尾。
 * 尾部字符串需要满足至少包含一个 “MT” 子序列，且以 M 开头。
 * 例如 AAAMT 和 MAAAT 都是一个合法的头部字符串，而 MTAAA 就不是合法的头部字符串。
 * 很显然这样的头尾字符串并不一定是唯一的，因此我们还有一个约束，就是 S 是满足头尾字符串合法的情况下的最长的字符串。
 * 很显然这样的加密策略是支持解码的，给出一个加密后的字符串，请你找出中间被加密的字符串 S 。
 */
public class UncodeString {

    public static void main(String[] args) throws IOException {
        // 获取输入流
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 获取字符串的长度
        int n = Integer.parseInt(br.readLine());
        // 获取字符串
        String s = br.readLine();

        int l = 0, r = n - 1;
        while (s.charAt(l) != 'M') l++;
        while (s.charAt(l) != 'T') l++;
        while (s.charAt(r) != 'T') r--;
        while (s.charAt(r) != 'M') r--;

        System.out.print(s.substring(l + 1, r));

    }
}
