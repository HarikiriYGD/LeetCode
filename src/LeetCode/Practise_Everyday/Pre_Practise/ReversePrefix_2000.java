package LeetCode.Practise_Everyday.Pre_Practise;


/**
 * @Auther: Lil_boat
 * @Date: 9:37 2022/2/2
 * @Description: 给你一个下标从 0 开始的字符串 word 和一个字符 ch 。找出 ch 第一次出现的下标 i ，
 * 反转 word 中从下标 0 开始、直到下标 i 结束（含下标 i ）的那段字符。如果 word 中不存在字符 ch ，则无需进行任何操作。
 * <p>
 * 例如，如果 word = "abcdefd" 且 ch = "d" ，那么你应该 反转 从下标 0 开始、直到下标 3 结束（含下标 3 ）。结果字符串将会是 "dcbaefd" 。
 * 返回 结果字符串 。
 */
public class ReversePrefix_2000 {

    public static String reversePrefix(String word, char ch) {
        // 构建字符串容器
        StringBuilder sb = new StringBuilder();
        // 找到ch出现的索引位置
        int index = word.indexOf(ch);
        if (index < 0) return word;
        // 找到前面的子字符串 添加进sb 并反转
        sb.append(word.substring(0, index + 1)).reverse();
        // 再把后面的字符串添加进sb
        sb.append(word.substring(index + 1));
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(reversePrefix("abcdefd", 'd'));
    }

}
