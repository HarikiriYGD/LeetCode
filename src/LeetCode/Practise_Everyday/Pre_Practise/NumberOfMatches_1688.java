package LeetCode.Practise_Everyday.Pre_Practise;

/**
 * @Auther: Lil_boat
 * @Date: 9:47 2022/1/25
 * @Description: 给你一个整数 n ，表示比赛中的队伍数。比赛遵循一种独特的赛制：
 * <p>
 * 如果当前队伍数是 偶数 ，那么每支队伍都会与另一支队伍配对。总共进行 n / 2 场比赛，且产生 n / 2 支队伍进入下一轮。
 * 如果当前队伍数为 奇数 ，那么将会随机轮空并晋级一支队伍，其余的队伍配对。总共进行 (n - 1) / 2 场比赛，且产生 (n - 1) / 2 + 1 支队伍进入下一轮。
 * 返回在比赛中进行的配对次数，直到决出获胜队伍为止。
 */
public class NumberOfMatches_1688 {

    public static int numberOfMatches(int n) {
        // 定义匹配次数
        int cnt = 0;
        while (n > 1) {
            // 匹配次数累加
            cnt += n / 2;
            // 如果是偶数则 n = n / 2；奇数则 n = n / 2 + 1
            n = n % 2 == 0 ? n / 2 : (n / 2) + 1;
        }
        return cnt;
    }

    public static void main(String[] args) {
        System.out.println(numberOfMatches(7));
    }

}
