package LeetCode.String;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: Lil_boat
 * @Date: 11:35 2022/4/2
 * @Description: 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
public class LetterCombinations_17 {
    static final String[][] table = {
            {"a", "b", "c"}, // 2
            {"d", "e", "f"}, // 3
            {"g", "h", "i"}, // 4
            {"j", "k", "l"}, // 5
            {"m", "n", "o"}, // 6
            {"p", "q", "r", "s"}, // 7
            {"t", "u", "v"}, // 8
            {"w", "x", "y", "z"}}; // 9
    static List<String> res = new ArrayList<>();

    /*
        回溯过程中维护一个字符串，表示已有的字母排列（如果未遍历完电话号码的所有数字，则已有的字母排列是不完整的）。
        该字符串初始为空。每次取电话号码的一位数字，从哈希表中获得该数字对应的所有可能的字母，
        并将其中的一个字母插入到已有的字母排列后面，然后继续处理电话号码的后一位数字，直到处理完电话号码中的所有数字，
        即得到一个完整的字母排列。然后进行回退操作，遍历其余的字母排列。
     */
    public static List<String> letterCombinations(String digits) {
        int n = digits.length();
        // 电话号码为空 则直接返回空的列表
        if (n == 0) return new ArrayList<>();
        // 回溯
        backTrack(digits, 0, new StringBuffer());
        return res;
    }

    /**
     * 回溯
     * @param digits 电话号码
     * @param index 当前索引位置
     * @param sb 当前所代表的字母组合
     */
    public static void backTrack(String digits, int index, StringBuffer sb) {
        // 如果当前长度等于电话号码长度 直接加入结果集
        if (index == digits.length()) res.add(sb.toString());
        else {
            // 获取数字
            char digit = digits.charAt(index);
            // 获取电话号码对应的字母
            int i = Integer.valueOf(digit - '0') - 2;
            // 当前电话号码代表了几个字母
            int len = table[i].length;
            for (int j = 0; j < len; j++) {
                // 依次添加进sb
                sb.append(table[i][j]);
                // 回溯
                backTrack(digits, index + 1, sb);
                // 删除添加的字母
                sb.deleteCharAt(index);
            }
        }

    }

    public static void main(String[] args) {
        String digits = "23";
        List<String> list = letterCombinations(digits);
        System.out.println(Arrays.toString(list.toArray()));
    }
}
