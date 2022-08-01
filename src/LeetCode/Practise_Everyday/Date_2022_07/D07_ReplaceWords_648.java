package LeetCode.Practise_Everyday.Date_2022_07;

import java.util.*;

/**
 * @Author: Lil_boat
 * @Date: 2022/7/7 9:31
 * @Description: 在英语中，我们有一个叫做 词根(root) 的概念，可以词根后面添加其他一些词组成另一个较长的单词——我们称这个词为 继承词(successor)。
 * 例如，词根an，跟随着单词 other(其他)，可以形成新的单词 another(另一个)。
 * 现在，给定一个由许多词根组成的词典 dictionary 和一个用空格分隔单词形成的句子 sentence。
 * 你需要将句子中的所有继承词用词根替换掉。如果继承词有许多可以形成它的词根，则用最短的词根替换它。
 * 你需要输出替换之后的句子。
 * <p>
 * 链接：https://leetcode.cn/problems/replace-words
 */
public class D07_ReplaceWords_648 {

    public String replaceWords(List<String> dictionary, String sentence) {
        // 将句子用空格分隔开来
        String[] sen = sentence.split(" ");
        // 用set集合存储词根
        Set<String> set = new HashSet<>();
        for (String s : dictionary) {
            set.add(s);
        }
        for (int i = 0; i < sen.length; i++) {
            String word = sen[i];
            for (int j = 0; j < word.length(); j++) {
                // 当前单词必须大于等于词根的长度
                // 词根是否在前面
                if (set.contains(word.substring(0, 1 + j))) {
                    sen[i] = word.substring(0, 1 + j);
                    break;
                }
            }
        }
        return String.join(" ", sen);
    }


    /**
     * 字典树数据结构
     */
    class Node {
        boolean isEnd;
        Node[] tire = new Node[26];
    }

    Node root = new Node();

    void add(String s) {
        Node p = root;
        for (int i = 0; i < s.length(); i++) {
            // 获取字母 - 'a' 的索引位置
            int idx = s.charAt(i) - 'a';
            // 判断是否有字母
            if (p.tire[idx] == null) {
                // 没有则创建
                p.tire[idx] = new Node();
            }
            // 指向这个字母
            p = p.tire[idx];
        }
        p.isEnd = true;
    }

    String query(String s) {
        Node p = root;
        for (int i = 0; i < s.length(); i++) {
            int idx = s.charAt(i) - 'a';
            // 如果不存在词根，则直接跳出循环
            if (p.tire[idx] == null) {
                break;
            }
            // 判断当前是否已经是词根的结尾
            if (p.tire[idx].isEnd) {
                return s.substring(0, i + 1);
            }
            p = p.tire[idx];
        }
        return s;
    }

    public String replaceWords_Tire(List<String> dictionary, String sentence) {
        // 将词根添加进字典树
        for (String str : dictionary) {
            add(str);
        }
        StringBuilder sb = new StringBuilder();
        for (String str : sentence.split(" ")) {
            sb.append(query(str)).append(" ");
        }
        return sb.substring(0, sb.length() - 1);
    }


    public static void main(String[] args) {
        List<String> dic = Arrays.asList("cat", "bat", "rat");

        D07_ReplaceWords_648 t = new D07_ReplaceWords_648();
        System.out.println(t.replaceWords(dic, "the cattle was rattled by the battery"));
        System.out.println(t.replaceWords_Tire(dic, "the cattle was rattled by the battery"));
    }

}
