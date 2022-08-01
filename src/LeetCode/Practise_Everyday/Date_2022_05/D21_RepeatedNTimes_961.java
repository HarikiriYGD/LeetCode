package LeetCode.Practise_Everyday.Date_2022_05;

/**
 * @Auther: Lil_boat
 * @Date: 10:52 2022/5/21
 * @Tile: 在长度 2N 的数组中找出重复 N 次的元素
 * @Description: 给你一个整数数组 nums ，该数组具有以下属性：
 * * nums.length == 2 * n.
 * * nums 包含 n + 1 个 不同的 元素
 * * nums 中恰有一个元素重复 n 次
 * 找出并返回重复了 n 次的那个元素。
 * <p>
 * 链接：https://leetcode.cn/problems/n-repeated-element-in-size-2n-array
 */
public class D21_RepeatedNTimes_961 {

    /*
        假设重复出现的数字是 x，数字 x 重复了 n 次，要将这 n 个相同的 x 间隔开，需要 n - 1 个额外数字，
        而实际上我们除 x 以外还有 n 个额外数字（数字总数为 n + 1 个），
        因此在我们所能构造出的所有排列方式中，最多使相邻 x 之间间隔了 2 个额外数字。
        对于每个 nums[i] 而言，我们检查往前的三个位置（若有），如果有重复相等情况，说明当前的 nums[i] 即是答案。
     */
    public int repeatedNTimes(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            int t = nums[i];
            if (i - 1 >= 0 && nums[i - 1] == t) return t;
            if (i - 2 >= 0 && nums[i - 2] == t) return t;
            if (i - 3 >= 0 && nums[i - 3] == t) return t;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 2, 3, 1, 5};
        D21_RepeatedNTimes_961 T = new D21_RepeatedNTimes_961();
        System.out.println(T.repeatedNTimes(nums));
    }

}
