package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Lil_boat
 * @Date: 13:39 2021/12/29
 * @Description: 给你一个 下标从 0 开始 的整数数组 nums ，返回满足下述条件的 不同 四元组 (a, b, c, d) 的 数目 ：
 * <p>
 * nums[a] + nums[b] + nums[c] == nums[d] ，且
 * a < b < c < d
 */
public class CountQuadruplets_1995 {
    /*
        暴力的方式：
            因为数据的范围比较小，直接枚举可行
     */
    public static int countQuadruplets(int[] nums) {
        int len = nums.length;
        if (nums.length < 4) return 0;
        int count = 0;
        for (int i = 0; i < len - 3; i++) {
            for (int j = i + 1; j < len - 2; j++) {
                for (int k = j + 1; k < len - 1; k++) {
                    for (int m = k + 1; m < len; m++) {
                        if (nums[i] + nums[j] + nums[k] == nums[m]) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }

    /*
    使用哈希表存储 nums[d] − nums[c]
        思路与算法:
            我们将等式左侧的 nums[c] 移动到右侧，变为：nums[a]+nums[b]=nums[d]−nums[c]
            如果我们已经枚举了前两个下标 a, b，那么就已经知道了等式左侧 nums[a]+nums[b] 的值，即为 nums[d]−nums[c] 的值。
            对于下标 c, d 而言，它的取值范围是 b < c < d < n，
            那么我们可以使用哈希表统计满足上述要求的每一种 nums[d]−nums[c] 出现的次数。
            这样一来，我们就可以直接从哈希表中获得满足等式的 c, d 的个数，而不需要在 [b+1, n-1] 的范围内进行枚举了。
            细节在枚举前两个下标 a, b 时，我们可以先逆序枚举 b。
            在 b 减小的过程中，c 的取值范围是逐渐增大的：即从 b+1 减小到 b 时，c 的取值范围中多了 b+1 这一项，而其余的项不变。
            因此我们只需要将所有满足 c=b+1 且 d>c 的 c, d 对应的 nums[d]−nums[c] 加入哈希表即可。
            在这之后，我们就可以枚举 a 并使用哈希表计算答案了。
     */
    public static int countQuadruplets_Hash(int[] nums) {
        int n = nums.length;
        int ans = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int b = n - 3; b >= 1; --b) {
            for (int d = b + 2; d < n; ++d) {
                map.put(nums[d] - nums[b + 1], map.getOrDefault(nums[d] - nums[b + 1], 0) + 1);
            }
            for (int a = 0; a < b; ++a) {
                ans += map.getOrDefault(nums[a] + nums[b], 0);
            }
        }
        return ans;
    }

    /*
    多维背包：
        利用等式关系 nums[a] + nums[b] + nums[c] = nums[d]，具有明确的「数值」和「个数」关系，可将问题抽象为组合优化问题求方案数。
        限制组合个数的维度有两个，均为「恰好」限制，转换为「二维费用背包问题求方案数」问题。
        定义 f[i][j][k] 为考虑前 i 个物品（下标从 1 开始），凑成数值恰好 j，使用个数恰好为 k 的方案数。
        最终答案为 sum_{i = 3}∑{n - 1}(f[i][nums[i]][3]) ，
        起始状态 f[0][0][0] = 1 代表不考虑任何物品时，所用个数为 0，凑成数值为 0 的方案数为 1。
        不失一般性考虑 f[i][j][k] 该如何转移，根据 nums[i - 1] 是否参与组合进行分情况讨论：
            nums[i - 1]nums[i−1] 不参与组成，此时有：f[i - 1][j][k]f[i−1][j][k];
            nums[i - 1]nums[i−1] 参与组成，此时有：f[i - 1][j - t][k - 1]f[i−1][j−t][k−1];
        最终 f[i][j][k] 为上述两种情况之和，最终统计 sum_{i = 3}∑{n - 1}(f[i][nums[i]][3]) 即是答案。
        利用 f[i][j][k] 仅依赖于 f[i - 1][j][k] 和 j k 维度值更小的 f[i - 1][X][X]，可进行维度优化，并在转移过程中统计答案。

     */
    public static int countQuadruplets_Bags(int[] nums) {
        int n = nums.length;
        int[][][] f = new int[n + 1][110][4];
        f[0][0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int t = nums[i - 1];
            for (int j = 0; j < 110; j++) {
                for (int k = 0; k < 4; k++) {
                    f[i][j][k] += f[i - 1][j][k];
                    if (j - t >= 0 && k - 1 >= 0) f[i][j][k] += f[i - 1][j - t][k - 1];
                }
            }
        }
        int ans = 0;
        for (int i = 0; i < n; i++) {
            ans += f[i][nums[i]][3];
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {28, 8, 49, 85, 37, 90, 20, 8};
        System.out.println(countQuadruplets(nums));
        System.out.println(countQuadruplets_Hash(nums));
        System.out.println(countQuadruplets_Bags(nums));
    }
}
