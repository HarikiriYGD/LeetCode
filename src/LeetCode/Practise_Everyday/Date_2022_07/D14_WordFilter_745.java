package LeetCode.Practise_Everyday.Date_2022_07;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Lil_boat
 * @Date: 2022/7/14 15:43
 * @Title: 前缀和后缀搜索
 * @Description: 设计一个包含一些单词的特殊词典，并能够通过前缀和后缀来检索单词。
 * 实现 WordFilter 类：
 * WordFilter(string[] words) 使用词典中的单词 words 初始化对象。
 * f(string pref, string suff) 返回词典中具有前缀 prefix 和后缀 suff 的单词的下标。
 * 如果存在不止一个满足要求的下标，返回其中 最大的下标 。如果不存在这样的单词，返回 -1 。
 * <p>
 * 链接：https://leetcode.cn/problems/prefix-and-suffix-search
 */
public class D14_WordFilter_745 {

    class Node {
        // 存储单词的索引
        List<Integer> idxs;
        Node[] tr;

        public Node() {
            idxs = new ArrayList<>();
            tr = new Node[26];
        }
    }

    Node tr1 = new Node(), tr2 = new Node();

    void add(Node root, String s, int idx, boolean isTurn) {
        int n = s.length();
        // 存储当前单词在words数组中的索引位置
        root.idxs.add(idx);
        for (int i = isTurn ? n - 1 : 0; i >= 0 && i < n; i += isTurn ? -1 : 1) {
            int u = s.charAt(i) - 'a';
            if (root.tr[u] == null) {
                root.tr[u] = new Node();
            }
            root = root.tr[u];
            // 每一层的节点都要存储
            root.idxs.add(idx);
        }
    }

    int query(String a, String b) {
        int m = a.length(), n = b.length();
        Node p = tr1;
        for (int i = 0; i < m; i++) {
            int u = a.charAt(i) - 'a';
            if (p.tr[u] == null) return -1;
            p = p.tr[u];
        }
        List<Integer> l1 = p.idxs;
        p = tr2;
        for (int i = n - 1; i >= 0; i--) {
            int u = b.charAt(i) - 'a';
            if (p.tr[u] == null) return -1;
            p = p.tr[u];
        }
        List<Integer> l2 = p.idxs;
        m = l1.size();
        n = l2.size();
        for (int i = m - 1, j = n - 1; i >= 0 && j >= 0; ) {
            if (l1.get(i) > l2.get(j)) i--;
            else if (l1.get(i) < l2.get(j)) j--;
            else return l1.get(i);
        }
        return -1;
    }

    public D14_WordFilter_745(String[] words) {
        for (int i = 0; i < words.length; i++) {
            // 正序存储
            add(tr1, words[i], i, false);
            // 逆序存储
            add(tr2, words[i], i, true);
        }
    }

    public int f(String pref, String suff) {
        return query(pref,suff);
    }

}
