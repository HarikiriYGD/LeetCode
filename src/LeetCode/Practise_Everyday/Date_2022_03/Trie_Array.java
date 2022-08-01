package LeetCode.Practise_Everyday.Date_2022_03;

import java.util.Arrays;

public class Trie_Array {
    /**
     * 使用二维数组 trie[][] 来存储我们所有的单词字符。
     * 使用 index 来自增记录我们到底用了多少个格子（相当于给被用到格子进行编号）。
     * 使用 count[] 数组记录某个格子被「被标记为结尾的次数」（当 idx 编号的格子被标记了 n 次，则有 count[idx]++）。
     */
    static int N = 100009;
    static int[][] trie = new int[N][26];
    static int[] count = new int[N];
    static int index = 0;

    // 在构造方法中完成重置 static 成员数组的操作
    // 这样做的目的是为减少 new 操作（无论有多少测试数据，上述 static 成员只会被 new 一次）
    public Trie_Array() {
        for (int row = index; row >= 0; row--) {
            Arrays.fill(trie[row], 0);
        }
        Arrays.fill(count, 0);
        index = 0;
    }

    public void insert(String s) {
        int p = 0;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            if (trie[p][u] == 0) trie[p][u] = ++index;
            p = trie[p][u];
        }
        count[p]++;
    }

    public boolean search(String s) {
        int p = 0;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            if (trie[p][u] == 0) return false;
            p = trie[p][u];
        }
        return count[p] != 0;
    }

    public boolean startWith(String s) {
        int p = 0;
        for (int i = 0; i < s.length(); i++) {
            int u = s.charAt(i) - 'a';
            if (trie[p][u] == 0) return false;
            p = trie[p][u];
        }
        return true;
    }

}
