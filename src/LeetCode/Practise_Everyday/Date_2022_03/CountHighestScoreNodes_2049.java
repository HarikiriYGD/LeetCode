package LeetCode.Practise_Everyday.Date_2022_03;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auther: Lil_boat
 * @Date: 12:40 2022/3/11
 * @Description: 给你一棵根节点为 0 的 二叉树 ，它总共有 n 个节点，节点编号为 0 到 n - 1 。
 * 同时给你一个下标从 0 开始的整数数组 parents 表示这棵树，其中 parents[i] 是节点 i 的父节点。由于节点 0 是根，所以 parents[0] == -1 。
 * <p>
 * 一个子树的 大小 为这个子树内节点的数目。每个节点都有一个与之关联的 分数 。
 * 求出某个节点分数的方法是，将这个节点和与它相连的边全部 删除 ，剩余部分是若干个 非空 子树，这个节点的 分数 为所有这些子树 大小的乘积 。
 * 请你返回有 最高得分 节点的 数目 。
 */
public class CountHighestScoreNodes_2049 {

    static int[] cnt;
    static List<Integer>[] children;

    public static int countHighestScoreNodes(int[] parents) {

        // 树的大小
        int n = parents.length;
        // 存储每个节点的孩子节点
        children = new List[n];
        cnt = new int[n];
        for (int i = 0; i < n; i++) {
            children[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            children[parents[i]].add(i);
        }
        dfs(0);
        long max = 1L;
        int ans = 0;
        for (int i = 0; i < n; i++) {
            long cur = Math.max(1L, 1L * (n - cnt[i]));
            // 计算分数
            for (Integer child : children[i]) {
                cur *= cnt[child];
            }
            if (cur > max){
                // 最大值重置
                max = cur;
                // 并且将此
                ans = 1;
            }else if (cur == max){
                ans++;
            }
        }
        return ans;
    }

    // 查询每个节点的边各有多少条 根节点的边数是树节点的个数
    public static void dfs(int i) {
        int ans = 1;
        for (Integer child : children[i]) {
            dfs(child);
            ans += cnt[child];
        }
        cnt[i] = ans;
    }

    public static void main(String[] args) {
        int[] parents = {-1, 2, 0, 2, 0};
        System.out.println(countHighestScoreNodes(parents));
    }

}
