package LeetCode.Practise_Everyday.Date_2022_04;

/**
 * @Auther: Lil_boat
 * @Date: 12:44 2022/4/4
 * @Description: 给你一个数组 nums ，请你完成两类查询。
 * <p>
 * 其中一类查询要求 更新 数组 nums 下标对应的值
 * 另一类查询要求返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 ，其中 left <= right
 * 实现 NumArray 类：
 * <p>
 * NumArray(int[] nums) 用整数数组 nums 初始化对象
 * void update(int index, int val) 将 nums[index] 的值 更新 为 val
 * int sumRange(int left, int right) 返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 
 * （即，nums[left] + nums[left + 1], ..., nums[right]）
 */
public class NumArray_307 {
    // 树状数组
    int[] tree;

    // 找到x的二进制数的最后一个1所表示的二进制
    private int lowBit(int x) {
        return x & -x;
    }
    // 在树状数组index位置中增加val值
    // 更新树状数组使用 x+=lowBit()来寻找被影响的数组下标
    private void insert(int index, int val) {
        int x = index + 1;
        while (x < tree.length) {
            tree[x] += val;
            x += lowBit(x);
        }
    }
    // 查询树状数组的前缀和
    // 查询树状数组使用 x-= lowBit(x)来寻找小于x的下一个区间
    private int query(int x) {
        int ans = 0;
        while (x > 0) {
            ans += tree[x];
            x -= lowBit(x);
        }
        return ans;
    }

    int[] nums;

    public NumArray_307(int[] nums) {
        this.nums = nums;
        this.tree = new int[nums.length + 1];
        for (int i = 0; i < nums.length; i++) {
            // 初始化数据
            insert(i, nums[i]);
        }
    }

    public void update(int index, int val) {
        insert(index, val - nums[index]);
        nums[index] = val;
    }

    public int sumRange(int left, int right) {
        return query(right + 1) - query(left);
    }

}
