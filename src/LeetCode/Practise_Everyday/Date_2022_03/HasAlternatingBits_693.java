package LeetCode.Practise_Everyday.Date_2022_03;
/**
 * @Auther: Lil_boat
 * @Date: 13:20 2022/3/28
 * @Description: 给定一个正整数，检查它的二进制表示是否总是 0、1 交替出现：换句话说，就是二进制表示中相邻两位的数字永不相同。
 */
public class HasAlternatingBits_693 {

    public static boolean hasAlternatingBits(int n) {
        String s = Integer.toString(n, 2);
        char[] c = s.toCharArray();
        for(int i = 0; i < c.length - 1; i++){
            if(c[i] == c[i + 1])return false;
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(hasAlternatingBits(5));
    }

}
