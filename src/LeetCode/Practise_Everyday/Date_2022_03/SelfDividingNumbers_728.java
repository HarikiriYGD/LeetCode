package LeetCode.Practise_Everyday.Date_2022_03;

import java.util.ArrayList;
import java.util.List;
/**
 * @Auther: Lil_boat
 * @Date: 9:40 2022/3/31
 * @Description: 自除数 是指可以被它包含的每一位数整除的数。
 * 例如，128 是一个 自除数 ，因为 128 % 1 == 0，128 % 2 == 0，128 % 8 == 0。
 * 自除数 不允许包含 0 。
 * 给定两个整数 left 和 right ，返回一个列表，列表的元素是范围 [left, right] 内所有的 自除数 。
 *
 */
public class SelfDividingNumbers_728 {

    public static List<Integer> selfDividingNumbers(int left, int right) {
        if (left > right) return new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        for (int i = left; i <= right; i++) {
            int x = i;
            while (x > 0) {
                // 取每一位数
                int tmp = x % 10;
                // 除数不为0
                if (tmp == 0) break;
                // 判断能否整除
                if (i % tmp == 0) {
                    if (1 <= x && x <= 9 && String.valueOf(i).charAt(0) == String.valueOf(x).charAt(0)) ans.add(i);
                } else break;
                x /= 10;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        List<Integer> list = selfDividingNumbers(1, 22);
        for (Integer integer : list) {
            System.out.println(integer);
        }
    }

}
