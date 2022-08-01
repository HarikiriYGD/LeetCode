package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.PriorityQueue;

/**
 * @Auther: Lil_boat
 * @Date: 13:08 2022/2/7
 * @Description: 如果字符串中不含有任何 'aaa'，'bbb' 或 'ccc' 这样的字符串作为子串，那么该字符串就是一个「快乐字符串」。
 * <p>
 * 给你三个整数 a，b ，c，请你返回 任意一个 满足下列全部条件的字符串 s：
 * <p>
 * s 是一个尽可能长的快乐字符串。
 * s 中 最多 有a 个字母 'a'、b 个字母 'b'、c 个字母 'c' 。
 * s 中只含有 'a'、'b' 、'c' 三种字母。
 * 如果不存在这样的字符串 s ，请返回一个空字符串 ""。
 */
public class LongestDiverseString_1405 {

    /*
    题目要求找到最长的快乐字符串，且快乐字符串中不含有三个连续相同的字母。为了找到最长的字符串，我们可以使用如下贪心策略：
    尽可能优先使用当前数量最多的字母，因为最后同一种字母剩余的越多，越容易出现字母连续相同的情况。
    如果构建完成最长的快乐字符串后还存在剩余未选择的字母，则剩余的字母一定为同一种字母且该字母的总数量最多。
    依次从当前数量最多的字母开始尝试，如果发现加入当前字母会导致出现三个连续相同字母，则跳过当前字母，
    直到我们找到可以添加的字母为止。实际上每次只会在数量最多和次多的字母中选择一个。
    如果尝试所有的字母都无法添加，则直接退出，此时构成的字符串即为最长的快乐字符串。
     */
    public static String longestDiverseString(int a, int b, int c) {
        // 构建优先队列 并且按照数组的第二个元素的大小进行排列
        PriorityQueue<int[]> q = new PriorityQueue<>((x, y) -> y[1] - x[1]);
        // 将 a b c从大到小依次入队
        if (a > 0) q.add(new int[]{0, a});
        if (b > 0) q.add(new int[]{1, b});
        if (c > 0) q.add(new int[]{2, c});
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            // 第一个元素出队 即字母最多的个数
            int[] curr = q.poll();
            int n = sb.length();
            // 判断是否第三个添加的字符是否都等于前面已经添加的字符
            // 如果是则找到第二多的字符
            if (n >= 2 && sb.charAt(n - 1) - 'a' == curr[0] && sb.charAt(n - 2) - 'a' == curr[0]) {
                // 如果队列为空则直接跳出循环
                if (q.isEmpty()) break;
                // 第二个元素出队 即字母个数第二多的个数
                int[] next = q.poll();
                // 添加到sb
                sb.append((char) (next[0] + 'a'));
                // 如果个数不为0则继续添加到优先队列中
                if (--next[1] != 0) q.add(next);
                // 再把最多的字符个数添加到队列中
                q.add(curr);
            } else {
                // 当前最多的字符个数
                sb.append((char) (curr[0] + 'a'));
                // 如果添加之后不为0 则继续添加到优先队列中
                if (--curr[1] != 0) q.add(curr);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(longestDiverseString(7, 1, 0));
    }

}
