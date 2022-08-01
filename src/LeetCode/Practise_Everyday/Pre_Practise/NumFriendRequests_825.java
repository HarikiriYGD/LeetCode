package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 12:54 2021/12/27
 * @Description: 在社交媒体网站上有 n 个用户。给你一个整数数组 ages ，其中 ages[i] 是第 i 个用户的年龄。
 * <p>
 * 如果下述任意一个条件为真，那么用户 x 将不会向用户 y（x != y）发送好友请求：
 * age[y] <= 0.5 * age[x] + 7
 * age[y] > age[x]
 * age[y] > 100 && age[x] < 100
 * 否则，x 将会向 y 发送一条好友请求。
 * <p>
 * 注意，如果 x 向 y 发送一条好友请求，y 不必也向 x 发送一条好友请求。另外，用户不会向自己发送好友请求。
 * <p>
 * 返回在该社交媒体网站上产生的好友请求总数。
 */
public class NumFriendRequests_825 {
    /**
     * 思路与算法
     * 观察题目中给定的三个条件：
     * ages[y]≤0.5×ages[x]+7; ages[y]>ages[x]; ages[y]>100∧ages[x]<100
     * <p>
     * 可以发现，条件 3 是蕴含在条件 2 中的，即如果满足条件 3 那么一定满足条件 2。
     * 因此，我们当条件 1 和 2 均不满足时，用户 x 就会向用户 y 发送好友请求，也就是用户 y 需要满足：
     * 0.5×ages[x] + 7<ages[y] ≤ ages[x]当 ages[x] ≤ 14 时，不存在满足要求的 ages[y]。
     * 因此我们只需要考虑 ages[x] ≥ 15 的情况，此时满足要求的 ages[y] 的范围为 (0.5×ages[x]+7, ages[x]]。
     * <p>
     * 当 ages[x] 增加时，上述区间的左右边界均单调递增，因此如果我们将数组 ages 进行升序排序，
     * 那么就可以在遍历 ages[x] 的同时，使用两个指针 left 和 right 维护满足要求的 ages[y] 的左右边界。
     * 当 x 向后移动一个位置时：
     * 如果左边界指针 left 指向的元素不满足 ages[left]>0.5×ages[x]+7，那么就将左边界向后移动一个位置；
     * 如果右边界指针 right 指向的下一个元素满足 ages[right+1]≤ages[x]，那么就将右边界向后移动一个位置。
     * 这样一来，[left,right] 就是满足年龄要求的 y 的下标。
     * 需要注意的是，x 本身一定在 [left,right] 区间内，因此 x 发送的好友请求数，即为 [left,right] 区间的长度减去 1。
     * 我们将每一个 x 对应的 [left,right] 区间长度减去 1 进行累加，就可以得到最终的答案。
     *
     * @param ages
     * @return
     */
    public static int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int sum = 0;
        int left = 0, right = 0;
        for (int age : ages) {
            if (age < 15) continue;
            while (ages[left] <= 0.5 * age + 7) left++;
            while (right + 1 < ages.length && ages[right + 1] <= age) right++;
            sum += right - left;
        }
        return sum;
    }

    /**
     * 计数排序 + 前缀和
     * 思路与算法：
     * 注意到题目中给定的年龄在 [1, 120] 的范围内，因此我们可以使用计数排序代替普通的排序。
     * 记 cnt[age] 表示年龄为 age 的用户数，那么每一个年龄为 age (age≥15) 的用户发送好友的请求数量即为：
     * ∑cnt[i] − 1，其中 i 从下限 ⌊0.5 × age + 8⌋ 到上限 age
     * 这里的 ⌊⋅⌋ 表示向下取整，−1 表示减去自身，与方法一相同。
     * 为了快速计算上式，我们可以使用数组 pre 存储数组 cnt 的前缀和，即：pre[age] = ∑ cnt[i] 其中 i 从下限 i = 1 到上限 age
     * 这样一来，上式就可以简化为：(pre[age] − pre[⌊0.5×age+7⌋]) − 1
     * 我们就可以在 O(1) 的时间内计算出一个年龄为 age 的用户发送好友的请求数量，
     * 将其乘以 cnt[age] 并累加就可以得到最终的答案。
     *
     * @param ages
     * @return
     */
    public static int numFriendRequests_Prefix_Sum(int[] ages) {
        int[] cnt = new int[121];
        for (int age : ages) {
            // 统计每个年龄有多少人
            cnt[age]++;
        }
        int[] pre = new int[121];
        for (int i = 1; i <= 120; i++) {
            // 前缀和
            pre[i] = pre[i - 1] + cnt[i];
        }
        int ans = 0;
        for (int i = 15; i <= 120; i++) {
            if (cnt[i] > 0) {
                int bound = (int) (i * 0.5 + 8);
                ans += cnt[i] * (pre[i] - pre[bound - 1] - 1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] ages = {16, 17,17, 18};
        System.out.println(numFriendRequests(ages));
        System.out.println(numFriendRequests_Prefix_Sum(ages));
    }
}
