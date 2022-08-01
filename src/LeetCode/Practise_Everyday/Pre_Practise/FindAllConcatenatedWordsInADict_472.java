package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: Lil_boat
 * @Date: 13:29 2021/12/28
 * @Description: 给你一个 不含重复 单词的字符串数组 words ，请你找出并返回 words 中的所有 连接词 。
 * 连接词 定义为：一个完全由给定数组中的至少两个较短单词组成的字符串。
 */
class Trie {
    /**
     * 定义字典树的数据结构
     */
    // Trie类型的数组
    Trie tree[];
    // 表示能否到达
    boolean canReach;

    public Trie() {
        canReach = false;
        // 有26个字母
        tree = new Trie[26];
    }

    // 如果当前字符不存在，则添入进去
    public void addWord(String s) {
        Trie t = this;
        for (int i = 0; i < s.length(); i++) {
            // 字符对应的下标
            int k = s.charAt(i) - 'a';
            // 判断是否有路径
            // 如果不存在则新建
            if (t.tree[k] == null) t.tree[k] = new Trie();
            // 如果存在，则让t指向它
            t = t.tree[k];
        }
        t.canReach = true;
    }
}

public class FindAllConcatenatedWordsInADict_472 {

    public static Trie trie;

    /**
     * 判断一个单词是不是连接词，需要判断这个单词是否完全由至少两个给定数组中的更短的非空单词（可以重复）组成。
     * 判断更短的单词是否在给定数组中，可以使用字典树实现。
     * <p>
     * 为了方便处理，首先将数组 words 按照字符串的长度递增的顺序排序，排序后可以确保当遍历到任意单词时，
     * 比该单词短的单词一定都已经遍历过，因此可以根据已经遍历过的全部单词判断当前单词是不是连接词。
     * 在将数组 words 排序之后，遍历数组，跳过空字符串，对于每个非空单词，判断该单词是不是连接词，
     * 如果是连接词则将该单词加入结果数组，如果不是连接词则将该单词加入字典树。
     * <p>
     * 判断一个单词是不是连接词的做法是在字典树中深度优先搜索。
     * 从该单词的第一个字符（即下标 0 处的字符）开始，在字典树中依次搜索每个字符对应的结点，可能有以下几种情况：
     * 如果一个字符对应的结点是单词的结尾，则找到了一个更短的单词，从该字符的后一个字符开始搜索下一个更短的单词；
     * 如果一个字符对应的结点在字典树中不存在，则当前的搜索结果失败，回到上一个单词的结尾继续搜索。
     * 如果找到一个更短的单词且这个更短的单词的最后一个字符是当前单词的最后一个字符，
     * 则当前单词是连接词。由于数组 words 中没有重复的单词，因此在判断一个单词是不是连接词时，
     * 该单词一定没有加入字典树，由此可以确保判断连接词的条件成立。
     * <p>
     * 由于一个连接词由多个更短的非空单词组成，如果存在一个较长的连接词的组成部分之一是一个较短的连接词，
     * 则一定可以将这个较短的连接词换成多个更短的非空单词，因此不需要将连接词加入字典树。
     *
     * @param words
     * @return
     */
    public static List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> ans = new ArrayList<>();
        // 根据字符串的长度对words进行排序
        Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));
        trie = new Trie();
        for (int i = 0; i < words.length; i++) {
            // 对每个字符串进行遍历
            if (canBeConcatenated(words[i], 0)) ans.add(words[i]);
            else trie.addWord(words[i]);
        }
        return ans;
    }

    /**
     * 判断是否是连接词的函数
     *
     * @param s     字符串
     * @param start 字符串开始索引
     * @return
     */
    public static boolean canBeConcatenated(String s, int start) {
        // 边界判断条件
        if (start > 0 && start == s.length()) return true;
        Trie t = trie;
        for (int i = start; i < s.length(); i++) {
            int k = s.charAt(i) - 'a';
            if (t.tree[k] == null) return false;
            t = t.tree[k];
            // 从头到尾一直判断能否到达
            if (t.canReach && canBeConcatenated(s, i + 1)) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        String[] words = {"cat", "cats", "catsdogcats", "dog", "dogcatsdog", "hippopotamuses", "rat", "ratcatdogcat"};
        List<String> res = findAllConcatenatedWordsInADict(words);
        for (String s : res) {
            System.out.println(s);
        }
    }
}
