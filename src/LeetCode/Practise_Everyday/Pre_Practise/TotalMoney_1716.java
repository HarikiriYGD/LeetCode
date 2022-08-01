package LeetCode.Practise_Everyday.Pre_Practise;

/**
 * @Auther: Lil_boat
 * @Date: 20:35 2022/1/15
 * @Description: Hercy 想要为购买第一辆车存钱。他 每天 都往力扣银行里存钱。
 * 最开始，他在周一的时候存入 1 块钱。从周二到周日，他每天都比前一天多存入 1 块钱。在接下来每一个周一，他都会比 前一个周一 多存入 1 块钱。
 * 给你 n ，请你返回在第 n 天结束的时候他在力扣银行总共存了多少块钱。
 */
public class TotalMoney_1716 {

    public static int totalMoney(int n) {
        // 第一周的总金额
        int totalWeek = 28;
        // 整周数
        int weeks = n / 7;
        // 下一周剩余的天数
        int x = n % 7;
        // 存入银行的总金额
        int totalMoney = 0;
        // 记录下一周的钱
        int next = 0;
        // 记录每周相差多少个7
        // 比如第一周和第二周如果是全满的
        // 第一周：[1,2,3,4,5,6,7]
        // 第二周：[2,3,4,5,6,7,8]     第二周和第一周差了7 * 1
        // 第三周：[3,4,5,6,7,8,9]     第二周和第三周差了7 * 2
        // ....以此类推
        // 第n周和第一周差了 7 * (n - 1)
        // 等差公式求和
        int y = 0;
        for (int i = weeks + 1; i <= x + weeks; i++) next += i;
        for (int i = 1; i < weeks; i++) {
            y += i;
        }

        totalMoney = totalWeek * weeks + 7 * y + next;
        return totalMoney;

    }

    public static void main(String[] args) {
        System.out.println(totalMoney(26));
    }
}
