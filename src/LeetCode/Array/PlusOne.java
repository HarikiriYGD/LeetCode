package LeetCode.Array;

import java.util.Arrays;

public class PlusOne {

    /**
     * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
     * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
     * 你可以假设除了整数 0 之外，这个整数不会以零开头。
     *
     * @param digits 数组
     * @return 返回加1之后的数组
     */
    public static int[] plusOne(int[] digits) {
        int length = digits.length;
        for (int i = length - 1; i >= 0; i--) {
            if (digits[i] != 9) {
                // 如果数组当前元素不等于9，直接加1
                // 然后直接返回
                digits[i]++;
                return digits;
            } else {
                // 如果数组当前元素等于9，那么加1之后
                // 肯定会变为0，我们先让他变为0
                digits[i] = 0;
            }
        }

        // 除非数组中的元素都是9，否则不会走到这一步，
        // 如果数组的元素都是9，我们只需要把数组的长度
        // 增加1，并且把数组的第一个元素置为1即可
        int[] temp = new int[length + 1];
        temp[0] = 1;
        return temp;
    }

    public static void main(String[] args) {
        int[] digits = {9, 9, 9, 9};
        int[] res = plusOne(digits);
        String s = Arrays.toString(res);
        System.out.println(s);
    }

}
