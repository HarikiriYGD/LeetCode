package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Lil_boat
 * @Date: 9:30 2022/1/10
 * @Description: 累加数 是一个字符串，组成它的数字可以形成累加序列。
 * 一个有效的 累加序列 必须 至少 包含 3 个数。除了最开始的两个数以外，字符串中的其他数都等于它之前两个数相加的和。
 * 给你一个只包含数字 '0'-'9' 的字符串，编写一个算法来判断给定输入是否是 累加数 。如果是，返回 true ；否则，返回 false 。
 * 说明：累加序列里的数 不会 以 0 开头，所以不会出现 1, 2, 03 或者 1, 02, 3 的情况。
 */
public class IsAdditiveNumber_306 {
    /**
     * 回溯的思想
     * @param num
     * @return
     */
    public static boolean isAdditiveNumber(String num) {
        return dfs(0, num, new ArrayList<>(num.length()));
    }

    public static boolean dfs(int index, String num, List<Long> pre) {
        // List用于记录当前累计的数字
        int preLength = pre.size();
        int numLength = num.length();
        // 如果preLength大于等于3，且前两个数之和不等于第三个数则返回false
        if (preLength >= 3 && pre.get(preLength - 1) != pre.get(preLength - 2) + pre.get(preLength - 3)) return false;
        // 遍历到结尾，且一直满足累加规则
        // 说明是一个累加数
        if (index == numLength && preLength >= 3) return true;
        for (int i = index; i < numLength; i++) {
            if (num.charAt(index) == '0' && i > index) break;
            pre.add(substringTransferToLong(num, index, i));
            if (dfs(i + 1, num, pre)) return true;
            // 回溯
            pre.remove(pre.size() - 1);
        }
        return false;
    }

    /**
     * 用于计算数值 即 开始到结束索引之间表示的数值
     * @param num 数字字符串
     * @param start 开始索引
     * @param end 结束索引
     * @return
     */
    public static Long substringTransferToLong(String num, int start, int end) {
        long res = 0;
        for (int i = start; i <= end; i++) {
            res += (num.charAt(i) - '0');
            if (i != end) {
                res *= 10;
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(isAdditiveNumber("112358"));
    }
}
