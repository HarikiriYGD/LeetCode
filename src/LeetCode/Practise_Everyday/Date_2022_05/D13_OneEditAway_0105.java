package LeetCode.Practise_Everyday.Date_2022_05;

/**
 * @Auther: Lil_boat
 * @Date: 10:19 2022/5/13
 * @Tile: 一次编辑
 * @Description: 字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
 * <p>
 * https://leetcode.cn/problems/one-away-lcci/
 */
public class D13_OneEditAway_0105 {

    public boolean oneEditAway(String first, String second) {
        // 分别记录first和second的字符串长度
        int m = first.length(), n = second.length();
        // 记录变化的次数
        int cnt = 0;
        // 记录是否插入或删除的字符在末尾
        boolean flag = true;
        // 长度距离超过1直接返回false
        if (Math.abs(m - n) > 1) return false;
        if (m > n) return oneEditAway(second, first);
            // 需要替换的情况
        else if (m == n) {
            for (int i = 0; i < m; i++) {
                if (first.charAt(i) != second.charAt(i)) cnt++;
            }
            // 编辑次数为1 或者 两个字符串相同
            if (cnt == 1 || first.equals(second)) return true;
            else return false;
        }
        // 需要插入的情况
        else {
            for (int i = 0, j = 0; i < m && j < n; ) {
                if (first.charAt(i) != second.charAt(j)) {
                    cnt++;
                    j++;
                    flag = false;
                    continue;
                }
                i++;
                j++;
            }
            if (cnt == 0 && flag) return true;
            else if (cnt == 1) return true;
            else return false;
        }
    }
}
