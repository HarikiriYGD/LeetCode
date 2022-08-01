package LeetCode.Practise_Everyday.Date_2022_05;

import java.util.ArrayDeque;
import java.util.Deque;
/**
 * @Auther: Lil_boat
 * @Date: 10:22 2022/5/2
 * @Tile:
 * @Description: 给定一个表示代码片段的字符串，你需要实现一个验证器来解析这段代码，并返回它是否合法。合法的代码片段需要遵守以下的所有规则：
 *
 * 代码必须被合法的闭合标签包围。否则，代码是无效的。
 * 闭合标签（不一定合法）要严格符合格式：<TAG_NAME>TAG_CONTENT</TAG_NAME>。
 * 其中，<TAG_NAME>是起始标签，</TAG_NAME>是结束标签。起始和结束标签中的 TAG_NAME 应当相同。
 * 当且仅当 TAG_NAME 和 TAG_CONTENT 都是合法的，闭合标签才是合法的。
 * 合法的 TAG_NAME 仅含有大写字母，长度在范围 [1,9] 之间。否则，该 TAG_NAME 是不合法的。
 * 合法的 TAG_CONTENT 可以包含其他合法的闭合标签，cdata （请参考规则7）和任意字符（注意参考规则1）除了不匹配的<、
 * 不匹配的起始和结束标签、不匹配的或带有不合法 TAG_NAME 的闭合标签。否则，TAG_CONTENT 是不合法的。
 * 一个起始标签，如果没有具有相同 TAG_NAME 的结束标签与之匹配，是不合法的。反之亦然。不过，你也需要考虑标签嵌套的问题。
 * 一个<，如果你找不到一个后续的>与之匹配，是不合法的。并且当你找到一个<或</时，所有直到下一个>的前的字符，都应当被解析为 TAG_NAME（不一定合法）。
 * cdata 有如下格式：<![CDATA[CDATA_CONTENT]]>。CDATA_CONTENT 的范围被定义成 <![CDATA[ 和后续的第一个 ]]>之间的字符。
 * CDATA_CONTENT 可以包含任意字符。cdata 的功能是阻止验证器解析CDATA_CONTENT，所以即使其中有一些字符可以被解析为标签（无论合法还是不合法），
 * 也应该将它们视为常规字符。
 *
 * 链接：https://leetcode-cn.com/problems/tag-validator
 */
public class D02_IsValid_591 {
    /*
        字符串大模拟，假设字符串 s 长度为 n，当前处理到的位置为 i，根据以下优先级进行检查：
            1. 优先尝试检查以 i 为开始的连续段是否为 CDATA，若能匹配到开头，则尝试匹配到 CDATA 的结尾处，并更新 i，若无法找到结尾，返回 False；
            2. 尝试匹配 s[i] 是否为 <，若满足，则根据 s[i + 1] 是否为 / 来判断当前 TAG_NAME 是处于右边还是左边，然后将 TAG_NAME 取出，记为 tag，
                判断 tag 长度是否合法，不合法返回 False，合法则根据是左边还是右边的 TAG_NAME 分情况讨论：
                    位于左边的 TAG_NAME：将其加入栈中，等待右边的 TAG_NAME 与其匹配；
                    位于右边的 TAG_NAME：将其与当前栈顶的元素进行匹配，若栈为空或匹配不上，
                    返回 False（注意：由于整个 s 应当被一对 TAG_NAME 所包裹，因此如果取出后栈顶元素匹配后，栈为空，
                    同时又还没处理完整个 s，此时 s 也不合法，返回 False）；
            3. 其余情况则为普通字符。
            最后由于整个 s 应当被一对 TAG_NAME 所包裹，因此当 i = 0 时，不能是情况 1 和情况 3，需要特判一下。

        链接：https://leetcode-cn.com/problems/tag-validator/solution/by-ac_oier-9l8z/
     */

    String CDATA1 = "<![CDATA[", CDATA2 = "]]>";

    public boolean isValid(String s) {
        Deque<String> d = new ArrayDeque<>();
        int n = s.length(), i = 0;
        while (i < n) {
            // 检查以i为开始的连续段是否为CDATA1
            if (i + 8 < n && s.substring(i, i + 9).equals(CDATA1)) {
                // 但是不能是i = 0的情况下
                if (i == 0) return false;
                int j = i + 9;
                boolean ok = false;
                while (j < n && !ok) {
                    // 判断能否找到CDATA2的字符串
                    if (j + 2 < n && s.substring(j, j + 3).equals(CDATA2)) {
                        j = j + 3;
                        ok = true;
                    } else {
                        j++;
                    }
                }
                // 若找不到直接返回false
                if (!ok) return false;
                i = j;
                // 寻找TAG_NAME
            } else if (s.charAt(i) == '<') {
                if (i == n - 1) return false;
                boolean isEnd = s.charAt(i + 1) == '/';
                int p = isEnd ? i + 2 : i + 1, j = p;
                while (j < n && s.charAt(j) != '>') {
                    if (!Character.isUpperCase(s.charAt(j))) return false;
                    j++;
                }
                if (j == n) return false;
                int len = j - p;
                // TAG_NAME长度范围在[1,9]
                if (len < 1 || len > 9) return false;
                String tag = s.substring(p, j);
                i = j + 1;
                if (!isEnd) {
                    d.addLast(tag);
                } else {
                    if (d.isEmpty() || !d.pollLast().equals(tag)) return false;
                    if (d.isEmpty() && i < n) return false;
                }
                // 其他字符
            } else {
                if (i == 0) return false;
                i++;
            }
        }
        return d.isEmpty();
    }

    public static void main(String[] args) {
        D02_IsValid_591 isValid_591 = new D02_IsValid_591();
        System.out.println(isValid_591.isValid("<DIV>This is the first line <![CDATA[<div>]]></DIV>"));
    }
}
