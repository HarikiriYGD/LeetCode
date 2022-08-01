package LeetCode.Practise_Everyday.Date_2022_07;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Lil_boat
 * @Date: 2022/7/11 11:23
 * @Title: 实现一个魔法字典
 * @Description: 设计一个使用单词列表进行初始化的数据结构，单词列表中的单词 互不相同 。
 * 如果给出一个单词，请判定能否只将这个单词中一个字母换成另一个字母，使得所形成的新单词存在于你构建的字典中。
 * <p>
 * 实现 MagicDictionary 类：
 * MagicDictionary() 初始化对象
 * void buildDict(String[] dictionary) 使用字符串数组 dictionary 设定该数据结构，dictionary 中的字符串互不相同
 * bool search(String searchWord) 给定一个字符串 searchWord ，判定能否只将字符串中 一个 字母换成另一个字母，
 * 使得所形成的新字符串能够与字典中的任一字符串匹配。如果可以，返回 true ；否则，返回 false 。
 * <p>
 * 链接：https://leetcode.cn/problems/implement-magic-dictionary
 */
public class D11_MagicDictionary_676 {

    class Trie{
        boolean isEnd;
        Trie[] tr;

        public Trie(){
            isEnd = false;
            tr = new Trie[26];
        }
    }

    Trie tr = new Trie();

    void add(String s){
        Trie p = tr;
        for(int i = 0; i < s.length(); i++){
            int idx = s.charAt(i) - 'a';
            if(p.tr[idx] == null){
                p.tr[idx] = new Trie();
            }
            p = p.tr[idx];
        }
        p.isEnd = true;
    }

    boolean query(String s, int idx, Trie p, int limit){
        if(limit < 0)return false;
        if(idx == s.length()){
            return p.isEnd && limit == 0;
        }
        int u = s.charAt(idx) - 'a';
        for(int i = 0; i < 26; i++){
            if(p.tr[i] == null) continue;
            if(query(s, idx + 1, p.tr[i], i == u ? limit : limit - 1)) return true;
        }
        return false;
    }


    public void buildDict(String[] dictionary) {
        for(String s : dictionary){
            add(s);
            list.add(s);
        }
    }

    public boolean search(String searchWord) {
        return query(searchWord, 0, tr, 1);
    }

    List<String> list = new ArrayList<>();


    public D11_MagicDictionary_676() {

    }


    public boolean search_Tire(String searchWord) {
       return query(searchWord, 0, tr, 1);
    }

    public boolean search_one(String searchWord) {
        int n = searchWord.length();
        for (String s : list) {
            // 如果两个字符串长度都不相等，则直接返回
            if (s.length() != n) {
                continue;
            } else {
                int i = 0, cnt = 0;
                while (i < n) {
                    // 统计有多少个不同的字符
                    if (s.charAt(i) != searchWord.charAt(i)) {
                        cnt++;
                        i++;
                    } else {
                        i++;
                    }
                }
                if (cnt == 1) {
                    return true;
                }
            }

        }
        return false;
    }


    public static void main(String[] args) {
        String[] dic = {"hello", "hallo", "leetcode", "judge", "judgg"};
        String ser = "heelo";
        D11_MagicDictionary_676 t = new D11_MagicDictionary_676();
        t.buildDict(dic);
        System.out.println(t.search_one(ser));
        System.out.println(t.search_Tire(ser));
    }

}
