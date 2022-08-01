package LeetCode.Array;

public class SingleNumber {

    /**
     * 使用异或运算，将所有值进行异或
     * 异或运算，相异为真，相同为假，所以 a^a = 0 ; 0^a = a
     * 因为异或运算 满足交换律 a^b^a = a^a^b = b 所以数组经过异或运算，单独的值就剩下了
     *
     * @param nums 非空数组
     * @return 返回只出现一次的数字
     */
    public static int singleNumber(int[] nums) {
        int s = 0;
        for (int num : nums) {
            // 异或运算
            s = s ^ num;
        }
        return s;
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 2, 1, 3};
        int res = singleNumber(nums);
        System.out.println(res);
    }

}
