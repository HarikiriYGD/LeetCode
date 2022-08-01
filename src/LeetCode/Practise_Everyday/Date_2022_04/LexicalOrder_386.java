package LeetCode.Practise_Everyday.Date_2022_04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: Lil_boat
 * @Date: 10:37 2022/4/18
 * @Tile: 字典序排数
 * @Description: 给你一个整数 n ，按字典序返回范围 [1, n] 内所有整数。
 * <p>
 * 你必须设计一个时间复杂度为 O(n) 且使用 O(1) 额外空间的算法。
 */
public class LexicalOrder_386 {

    /**
     * 递归的方式实现，但是空间复杂度不是严格的O(1)
     * 首先容易想到使用「递归」来实现 DFS。
     *
     * 将 [1, n] 的数按照字典序添加到答案，本质上是对一颗节点数量为 n，形态类似字典树的多阶树进行遍历，
     * 根节点为 0，需要被跳过，因此我们可以从树的第二层开始搜索。
     * 树中每个节点的值为其搜索路径所代表的数字，且每个节点有 [0, 9] 共 10 个子节点。
     *
     * @param n
     * @return
     */
    public static List<Integer> lexicalOrder(int n) {
        if (n == 1) return new ArrayList<>();
        ArrayList<Integer> ans = new ArrayList<>();
        for (int i = 1; i <= 9; i++) {
            dfs(ans, i, n);
        }
        return ans;
    }

    public static void dfs(List<Integer> ans, int cur, int limit) {
        if (cur > limit) return;
        ans.add(cur);
        for (int i = 0; i <= 9; i++) dfs(ans, cur * 10 + i, limit);
    }

    public static List<Integer> lexicalOrder_iterator(int n) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 0, j = 1; i < n; i++) {
            ans.add(j);
            if (j * 10 <= n) {
                j *= 10;
            } else {
                while (j % 10 == 9 || j + 1 > n) j /= 10;
                j++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        List<Integer> list = lexicalOrder(13);
        List<Integer> list1 = lexicalOrder_iterator(13);
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(Arrays.toString(list1.toArray()));
    }

}
