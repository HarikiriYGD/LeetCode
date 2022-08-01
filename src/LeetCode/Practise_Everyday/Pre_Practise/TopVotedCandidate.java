package LeetCode.Practise_Everyday.Pre_Practise;

/**
 * @Auther: Lil_boat
 * @Date: 12:31 2021/12/13
 * @Description:
 * 给你两个整数数组 persons 和 times 。在选举中，第 i 张票是在时刻为 times[i] 时投给候选人 persons[i] 的。
 * 对于发生在时刻 t 的每个查询，需要找出在 t 时刻在选举中领先的候选人的编号。
 * 在 t 时刻投出的选票也将被计入我们的查询之中。在平局的情况下，最近获得投票的候选人将会获胜。
 *
 * 实现 TopVotedCandidate 类：
 * TopVotedCandidate(int[] persons, int[] times) 使用 persons 和 times 数组初始化对象。
 * int q(int t) 根据前面描述的规则，返回在时刻 t 在选举中领先的候选人的编号。
 *  
 */
public class TopVotedCandidate {
    // 记录每一时刻的最大票选人
    int[] res;
    // 记录最大时刻
    int maxTime;

    public TopVotedCandidate(int[] persons, int[] times) {
        // 记录投票
        int[] vote = new int[5000];
        maxTime = times[0];
        // 寻找最大时间
        for (int i = 0; i < times.length; i++) {
            if (maxTime < times[i]) maxTime = times[i];
        }
        res = new int[maxTime + 1];
        // persons[i]和times[i]都从下标0开始
        int cur = 0;
        // 初始化最大票数为persons[0]
        int top = persons[0];
        for (int i = 0; i <= maxTime; i++) {
            // 如果i时刻和times[cur]的元素相等
            if (i == times[cur]) {
                // persons[cur]的票选人的票数+1
                vote[persons[cur]]++;
                if (vote[persons[cur]] >= vote[top]) {
                    top = persons[cur];
                }
                // 下标后移
                cur++;
            }
            // 记录最大票数的persons[cur]
            res[i] = top;
        }
    }

    public int q(int t) {
        // 如果已经超过最大时间，那必是res数组的最后一个元素
        if (t > maxTime) return res[res.length - 1];
        // 其他的返回即可
        else return res[t];
    }


}
