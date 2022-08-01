package LeetCode.Practise_Everyday.Date_2022_04;

import java.util.*;

/**
 * @Auther: Lil_boat
 * @Date: 9:56 2022/4/17
 * @Tile: 最常见的单词
 * @Description: 给定一个段落 (paragraph) 和一个禁用单词列表 (banned)。返回出现次数最多，同时不在禁用列表中的单词。
 * <p>
 * 题目保证至少有一个词不在禁用列表中，而且答案唯一。
 * <p>
 * 禁用列表中的单词用小写字母表示，不含标点符号。段落中的单词不区分大小写。答案都是小写字母。
 */
public class MostCommonWord_819 {

    /*
        方法一：作替换
            将其他符号的都变为 空格' '所代替
            再将String以空格分隔开split(' ')
     */
    public static String mostCommonWord(String paragraph, String[] banned) {
        paragraph = paragraph.toLowerCase();
        paragraph = paragraph.replace(',', ' ').
                replace('?', ' ').
                replace('\'', ' ').
                replace(':', ' ').
                replace('.', ' ').
                replace(';', ' ').
                replace('!',' ').
                trim();
        String[] s = paragraph.split(" ");
        Map<String,Integer> map1=new HashMap<>();
        Map<String,Integer> map2=new HashMap<>();
        //被禁用的单词放入map1
        for(String str:banned){
            map1.put(str,1);
        }
        //把没有禁用的单词放入map2
        for(String str : s){
            if(!map1.containsKey(str)&&!str.equals("")){
                map2.put(str,map2.getOrDefault(str,0)+1);
            }
        }
        //在map2找出出现最多的单词
        int max=-1;
        String res=null;
        for(String str:map2.keySet()){
            if(map2.get(str)>max){
                max=map2.get(str);
                res=str;
            }
        }
        return res;
    }

    public static String mostCommonWord_method2(String paragraph, String[] banned) {
        Set<String> set = new HashSet<>();
        for (String s : banned) {
            set.add(s);
        }
        char[] cs = paragraph.toCharArray();
        int n = cs.length;
        String ans = null;
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; ) {
            // 判断是否是字母，如果不是直接跳过
            if (!Character.isLetter(cs[i]) && ++i >= 0) continue;
            int j = i;
            // 找到下一个不为字母的字符
            while (j < n && Character.isLetter(cs[j])) j++;
            // 这就是一个单词
            String sub = paragraph.substring(i, j).toLowerCase();
            i = j + 1;
            // 存入set中
            if (set.contains(sub)) continue;
            map.put(sub, map.getOrDefault(sub, 0) + 1);
            // 更新结果集
            if (ans == null || map.get(sub) > map.get(ans)) ans = sub;
        }
        return ans;
    }


    public static void main(String[] args) {
        String paragraph = "a.";
        String[] banned = {};
        System.out.println(mostCommonWord(paragraph, banned));
        System.out.println(mostCommonWord_method2(paragraph, banned));
    }


}
