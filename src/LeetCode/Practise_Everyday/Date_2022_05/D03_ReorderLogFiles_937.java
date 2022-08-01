package LeetCode.Practise_Everyday.Date_2022_05;

import java.util.Arrays;
/**
 * @Auther: Lil_boat
 * @Date: 11:52 2022/5/3
 * @Tile: 重新排列日志文件
 * @Description: 给你一个日志数组 logs。每条日志都是以空格分隔的字串，其第一个字为字母与数字混合的 标识符 。
 *
 * 有两种不同类型的日志：
 *
 * 字母日志：除标识符之外，所有字均由小写字母组成
 * 数字日志：除标识符之外，所有字均由数字组成
 * 请按下述规则将日志重新排序：
 *
 * 所有 字母日志 都排在 数字日志 之前。
 * 字母日志 在内容不同时，忽略标识符后，按内容字母顺序排序；在内容相同时，按标识符排序。
 * 数字日志 应该保留原来的相对顺序。
 * 返回日志的最终顺序。
 *
 * 链接：https://leetcode-cn.com/problems/reorder-data-in-log-files
 */
public class D03_ReorderLogFiles_937 {
    /*
        重写sort的方法
        自定义排序
     */
    public String[] reorderLogFiles(String[] logs) {
        Arrays.sort(logs, (log1, log2) -> {
            // 将log1 按分隔符“ ” ，分成2份，即把标识符分开来
            String[] split1 = log1.split(" ", 2);
            String[] split2 = log2.split(" ", 2);
            // 判断除标识符外的第一个字符是数字true，字母false
            boolean isDigit1 = Character.isDigit(split1[1].charAt(0));
            boolean isDigit2 = Character.isDigit(split2[1].charAt(0));
            if (!isDigit1 && !isDigit2) { // 如果两个都是字母日志
                int cmp = split1[1].compareTo(split2[1]);
                if (cmp != 0)return cmp;
                return split1[0].compareTo(split2[0]);
            }
            return isDigit1 ? (isDigit2 ? 0 : 1) : - 1;
        });
        return logs;
    }


    public static void main(String[] args) {
        String[] logs = {"dig1 8 1 5 1","let1 art can","dig2 3 6","let2 own kit dig","let3 art zero"};
        D03_ReorderLogFiles_937 r = new D03_ReorderLogFiles_937();
        r.reorderLogFiles(logs);
    }
}
