package LeetCode.Practise_Everyday.Date_2022_03;
/**
 * @Auther: Lil_boat
 * @Date: 10:23 2022/3/7
 * @Description: 给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。
 */
public class ConvertToBase7_504 {

    public static String convertToBase(int num) {
        StringBuilder sb = new StringBuilder();
        if(num == 0)return "0";
        if(num > 0){
            while(num > 0){
                int x = num % 7;
                num /= 7;
                sb.append(x);
            }
        }else{
            num = -num;
            while(num > 0){
                int x = num % 7;
                num /= 7;
                sb.append(x);
            }
            sb.append("-");
        }
        return sb.reverse().toString();
        // 内置函数
//        return Integer.toString(num, 7);
    }

    public static void main(String[] args) {
        System.out.println(convertToBase(100));
    }
}
