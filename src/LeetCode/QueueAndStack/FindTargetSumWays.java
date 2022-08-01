package LeetCode.QueueAndStack;

public class FindTargetSumWays {

    /**
     * 所需运算次数
     */
    private static int count = 0;

    /**
     * 通过加减运算得到目标和
     *
     * @param nums   运算的数组
     * @param target 目标和
     * @return 返回所需运算的次数 count
     */
    public static int findTargetSumWays(int[] nums, int target) {
        // 调用dfs
        dfs(nums, 0, target, 0);
        return count;
    }

    /**
     * 动态规划
     *
     * @param nums    运算的数组
     * @param index   当前下标
     * @param target  目标和
     * @param current 当前目标和
     */
    public static void dfs(int[] nums, int index, int target, int current) {
        // 如果当前索引值等于数组长度，表明数组的全部数据被遍历，再进行判断与目标和是否相同
        if (index == nums.length) {
            // 如果目标和与当前目标和相同，次数+1
            if (target == current) {
                count++;
            }
            return;
        }

        // 索引值+1，再次调用dfs，进行加减运算
        dfs(nums, index + 1, target, current + nums[index]);
        dfs(nums, index + 1, target, current - nums[index]);
    }

    public static void main(String[] args) {
        int[] test = {1};
        int res = findTargetSumWays(test, 1);
        System.out.println(res);
    }

}
