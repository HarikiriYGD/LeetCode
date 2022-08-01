package LeetCode.Practise_Everyday.Date_2022_05;

/**
 * @Auther: Lil_boat
 * @Date: 10:23 2022/5/12
 * @Tile: 删列造序
 * @Description: 给你由 n 个小写字母字符串组成的数组 strs，其中每个字符串长度相等。
 * <p>
 * 这些字符串可以每个一行，排成一个网格。例如，strs = ["abc", "bce", "cae"] 可以排列为：
 * abc
 * bce
 * cae
 * 你需要找出并删除 不是按字典序升序排列的 列。在上面的例子（下标从 0 开始）中，列 0（'a', 'b', 'c'）
 * 和列 2（'c', 'e', 'e'）都是按升序排列的，
 * 而列 1（'b', 'c', 'a'）不是，所以要删除列 1 。
 * <p>
 * 返回你需要删除的列数。
 * <p>
 * 链接：https://leetcode.cn/problems/delete-columns-to-make-sorted。
 */
public class D12_MinDeletionSize_944 {

    public int minDeletionSize(String[] strs) {
        int m = strs.length, n = strs[0].length();
        int ans = 0;
        // 根据列升序比较
        for (int j = 0; j < n; j++) {
            // 控制每一个单词
            for (int i = 0; i < m - 1; i++) {
                // 如果有反序的，则表示需要删除 结果 + 1
                if (strs[i].charAt(j) > strs[i + 1].charAt(j)) {
                    ans++;
                    break;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        String[] strs = {"cba","daf","ghi"};
        D12_MinDeletionSize_944 t = new D12_MinDeletionSize_944();
        System.out.println(t.minDeletionSize(strs));
    }

}
