package LeetCode.Practise_Everyday.Date_2022_04;

import java.util.List;

/**
 * @Auther: Lil_boat
 * @Date: 10:13 2022/4/15
 * @Tile: 迷你语法分析器
 * @Description: 给定一个字符串 s 表示一个整数嵌套列表，实现一个解析它的语法分析器并返回解析的结果 NestedInteger 。
 * <p>
 * 列表中的每个元素只可能是整数或整数嵌套列表
 */
public class Deserialize_385 {

    public NestedInteger deserialize(String s) {
        // 如果是空串 直接返回
        if (s.isEmpty()) return new NestedInteger();
        // s不为空，首字符为'['，如果不是，说明s为一个整数，直接返回结果
        if (s.charAt(0) != '[') return new NestedInteger(Integer.parseInt(s));
        // s的首字符为'[',且s的长度小于2，说明没有内容，直接返回结果
        if (s.length() <= 2) return new NestedInteger();
        NestedInteger res = new NestedInteger();
        /*
        如果s长度大于2，我们从i=1开始遍历，我们需要一个变量start来记录某一层的真实位置，
        用cnt来记录跟真实位置是否为同一深度，cnt=0表示同一深度，由于中间每段都是由逗号隔开，
        所以当我们判断当cnt为0，且当前字符是逗号或者已经到字符串末尾了，
        我们把start到当前位置之间的字符串取出来递归调用函数，
        把返回结果加入res中，然后start更新为i+1。
        如果遇到’[’，计数器cnt自增1，若遇到’]’，计数器cnt自减1。
         */
        int start = 1, cnt = 0;
        for (int i = 1; i < s.length(); i++) {
            if (cnt == 0 && (s.charAt(i) == ',' || i == s.length() - 1)) {
                res.add(deserialize(s.substring(start, i)));
                start = i + 1;
            } else if (s.charAt(i) == '[') cnt++;
            else if (s.charAt(i) == ']') cnt--;
        }
        return res;
    }


}
