package LeetCode.Practise_Everyday.Date_2022_03;

/**
 * @Auther: Lil_boat
 * @Date: 10:43 2022/3/15
 * @Description: 给你一个整数数组 nums ，请你找出 nums 子集 按位或 可能得到的 最大值 ，并返回按位或能得到最大值的 不同非空子集的数目 。
 *
 * 如果数组 a 可以由数组 b 删除一些元素（或不删除）得到，则认为数组 a 是数组 b 的一个 子集 。如果选中的元素下标位置不一样，则认为两个子集 不同 。
 *
 * 对数组 a 执行 按位或 ，结果等于 a[0] OR a[1] OR ... OR a[a.length - 1]（下标从 0 开始）。
 *
 */
public class CountMaxOrSubsets_2044 {

    static int count = 0;
    static int max = 0;

    public static int countMaxOrSubsets(int[] nums) {
        int n = nums.length;
        // 获取当前最大的或值
        for (int i = 0; i < n; i++) {
            max |= nums[i];
        }
        dfs(nums, 0, 0);
        return count;
    }

    /**
     * 回溯
     * @param nums 操作数组
     * @param curIdx 当前索引
     * @param result 当前元素的或值
     */
    public static void dfs(int[] nums, int curIdx, int result) {
        if (curIdx == nums.length){
            if (result == max)count++;
            return;
        }
        // 索引 + 1 不与任何元素进行或
        dfs(nums, curIdx + 1, result);
        // 索引 + 1 与当前元素进行或
        dfs(nums, curIdx + 1, result | nums[curIdx]);
    }

    public static void main(String[] args) {
        int[] nums = {3,2,1,5};
        System.out.println(countMaxOrSubsets(nums));


    }

}
