package LeetCode.Practise_Everyday.Date_2022_04;
/**
 * @Auther: Lil_boat
 * @Date: 15:16 2022/4/7
 * @Description: 给定两个字符串, s 和 goal。如果在若干次旋转操作之后，s 能变成 goal ，那么返回 true 。
 *
 * s 的 旋转操作 就是将 s 最左边的字符移动到最右边。 
 *
 * 例如, 若 s = 'abcde'，在旋转一次之后结果就是'bcdea' 。
 */
public class RotateString_796 {

    public boolean rotateString(String s, String goal) {
        // 自己的想法 首尾拼接 但是效率比较低
        /*for(int i = 1; i < s.length(); i++){
            if(goal.equals(s.substring(i,s.length()) + s.substring(0,i)))return true;
        }
        return false;*/
        // 思路：只需比较一下两个字符串的长度，然后判断A + A中是否存在B就ok，因为A + A中已经包含了所有可能的移动情况
        return s.length() == goal.length() && (s + s).contains(goal);
    }

}
