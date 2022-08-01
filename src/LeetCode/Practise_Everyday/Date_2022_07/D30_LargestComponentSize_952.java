package LeetCode.Practise_Everyday.Date_2022_07;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Lil_boat
 * @Date: 2022/7/30 15:31
 * @Title: 按公因数计算最大组件大小
 * @Description: 给定一个由不同正整数的组成的非空数组 nums ，考虑下面的图：
 * 有 nums.length 个节点，按从 nums[0] 到 nums[nums.length - 1] 标记；
 * 只有当 nums[i] 和 nums[j] 共用一个大于 1 的公因数时，nums[i] 和 nums[j]之间才有一条边。
 * 返回 图中最大连通组件的大小
 * <p>
 * 链接：https://leetcode.cn/problems/largest-component-size-by-common-factor
 */
public class D30_LargestComponentSize_952 {

    static int N = 20010;
    static int[] p = new int[N], sz = new int[N];
    int ans = 1;

    public int find(int x) {
        if (p[x] != x) {
            p[x] = find(p[x]);
        }
        return p[x];
    }

    public void union(int a, int b) {
        if (find(a) == find(b)) {
            return;
        }
        sz[find(a)] += sz[find(b)];
        p[find(b)] = p[find(a)];
        ans = Math.max(ans, sz[find(a)]);
    }

    /**
     * 枚举质因数 + 并查集
     *
     * @param nums
     * @return
     */
    public int largestComponentSize(int[] nums) {
        int n = nums.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int cur = nums[i];
            for (int j = 2; j <= cur / j; j++) {
                if (cur % j == 0) {
                    add(map, j, i);
                }
                while (cur % j == 0) {
                    cur /= j;
                }
            }
            if (cur > 1) add(map, cur, i);
        }
        for (int i = 0; i <= n; i++) {
            p[i] = i;
            sz[i]=1;
        }
        for (int key : map.keySet()) {
            List<Integer> list = map.get(key);
            for (int i = 1; i < list.size(); i++) {
                union(list.get(0),list.get(i));
            }
        }
        return ans;
    }

    private void add(Map<Integer, List<Integer>> map, int key, int value) {
        List<Integer> list = map.getOrDefault(key, new ArrayList<>());
        list.add(value);
        map.put(key,list);
    }

}
