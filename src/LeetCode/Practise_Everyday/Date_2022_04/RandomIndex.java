package LeetCode.Practise_Everyday.Date_2022_04;

import java.util.*;

/**
 * @Auther: Lil_boat
 * @Date: 10:11 2022/4/25
 * @Tile: 随机数索引
 * @Description: 给定一个可能含有重复元素的整数数组，要求随机输出给定的数字的索引。 您可以假设给定的数字一定存在于数组中。
 * <p>
 * 注意：
 * 数组大小可能非常大。 使用太多额外空间的解决方案将不会通过测试。
 */
public class RandomIndex {
    HashMap<Integer, List<Integer>> map = new HashMap<>();
    Random r = new Random();
    int[] nums;

    /**
     * 蓄水池抽样原理 || 哈希表预处理
     *
     * @param nums
     */
    public RandomIndex(int[] nums) {
        /*for (int i = 0; i < nums.length; i++) {
            // 将相同的元素的索引放入 ArrayList中
            List<Integer> list = map.getOrDefault(nums[i], new ArrayList<>());
            list.add(i);
            map.put(nums[i], list);
        }*/
        this.nums = nums;
    }

    public int pick(int target) {
        /*List<Integer> list = map.get(target);
        return list.get(r.nextInt(list.size()));*/
        int cnt = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == target) {
                cnt++;
                if (r.nextInt(cnt) == 0) ans = i;
            }
        }
        return ans;
    }

}
