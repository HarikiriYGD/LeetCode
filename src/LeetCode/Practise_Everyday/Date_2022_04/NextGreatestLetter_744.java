package LeetCode.Practise_Everyday.Date_2022_04;
/**
 * @Auther: Lil_boat
 * @Date: 22:49 2022/4/3
 * @Description:给你一个排序后的字符列表 letters ，列表中只包含小写英文字母。另给出一个目标字母 target，请你寻找在这一有序列表里比目标字母大的最小字母。
 *
 * 在比较时，字母是依序循环出现的。举个例子：
 *
 * 如果目标字母 target = 'z' 并且字符列表为 letters = ['a', 'b']，则答案返回 'a'
 */
public class NextGreatestLetter_744 {

    public static char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        // 寻找比target字母大的第一个字母即是最小字母
        for(int i = 0; i < n; i++){
            // 找到了就返回
            if(letters[i] > target)return letters[i];
        }
        // 没找到就返回第一个
        // 因为这是一个有序的数组
        return letters[0];
    }

    public static void main(String[] args) {
        char[] letters = {'a','d','f'};
        System.out.println(nextGreatestLetter(letters, 'g'));
    }

}
