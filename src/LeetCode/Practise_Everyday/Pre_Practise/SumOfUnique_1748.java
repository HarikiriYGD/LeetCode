package LeetCode.Practise_Everyday.Pre_Practise;
/**
 * @Auther: Lil_boat
 * @Date: 11:47 2022/2/6
 * @Description: 给你一个整数数组 nums 。数组中唯一元素是那些只出现 恰好一次 的元素。
 * 请你返回 nums 中唯一元素的 和 。
 */
public class SumOfUnique_1748 {

    public static int sumOfUnique(int[] nums) {
        int ans = 0;
        // 元素个数很少 构建统计每个数的数量
        int[] count = new int[100];
        for (int num : nums) {
            count[num - 1]++;
        }
        // 将元素个数为1的全部加起来
        for (int i = 0; i < count.length; i++) {
            if (count[i] == 1) {
                ans += i + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,2,4};
        System.out.println(sumOfUnique(nums));
    }

}
