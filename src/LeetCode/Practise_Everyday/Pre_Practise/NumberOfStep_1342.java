package LeetCode.Practise_Everyday.Pre_Practise;

/**
 * @Auther: Lil_boat
 * @Date: 22:48 2022/1/31
 * @Description: 给你一个非负整数 num ，请你返回将它变成 0 所需要的步数。 如果当前数字是偶数，你需要把它除以 2 ；否则，减去 1 。
 */
public class NumberOfStep_1342 {

    public static int numberOfStep(int num) {
        int cnt = 0;
        while (num > 0) {
            if (num % 2 == 0) {
                num /= 2;
                cnt++;
            } else {
                num -= 1;
                cnt++;
            }
        }
        return cnt;
    }


    public static void main(String[] args) {
        System.out.println(numberOfStep(14));
    }

}
