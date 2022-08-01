package LeetCode.Practise_Everyday.Date_2022_05;
/**
 * @Auther: Lil_boat
 * @Date: 11:18 2022/5/17
 * @Tile: 验证外星语词典
 * @Description: 某种外星语也使用英文小写字母，但可能顺序 order 不同。字母表的顺序（order）是一些小写字母的排列。
 * 给定一组用外星语书写的单词 words，以及其字母表的顺序 order，只有当给定的单词在这种外星语中按字典序排列时，返回 true；否则，返回 false。
 *
 * 链接：https://leetcode.cn/problems/verifying-an-alien-dictionary
 */
public class D17_IsAlienSorted_953 {

    int[] alphabet = new int[26];
    public boolean isAlienSorted(String[] words, String order) {
        // 给定的order字母表
        // 字典序的顺序
        for(int i = 0; i < 26; i++){
            alphabet[order.charAt(i) - 'a'] = i;
        }
        // 判断相邻两个的单词是否按照字典序排序
        for(int i = 0; i < words.length - 1; i++){
            if(compare(words[i],words[i + 1]) > 0) return false;
        }
        return true;
    }

    public int compare(String s1, String s2){
        int n1 = s1.length();
        int n2 = s2.length();
        int min = Math.min(n1, n2);
        for(int i = 0; i < min; i++){
            char c1 = s1.charAt(i);
            char c2 = s2.charAt(i);
            // 如果当前字符不相等，则判断字典序的顺序
            if(c1 != c2) return alphabet[c1 - 'a'] - alphabet[c2 - 'a'];
        }
        // 比较单词长度
        // 如果为正数则表明不是按照字典序排序
        // 如果为负数则表明是按照字典序排序
        return n1 - n2;
    }

}
