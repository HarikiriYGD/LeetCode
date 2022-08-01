package LeetCode.Practise_Everyday.Date_2022_03;

/**
 * @Auther: Lil_boat
 * @Date: 9:25 2022/3/29
 * @Description: 一位老师正在出一场由 n 道判断题构成的考试，每道题的答案为 true （用 'T' 表示）或者 false （用 'F' 表示）。
 * 老师想增加学生对自己做出答案的不确定性，方法是 最大化 有 连续相同 结果的题数。（也就是连续出现 true 或者连续出现 false）。
 * <p>
 * 给你一个字符串 answerKey ，其中 answerKey[i] 是第 i 个问题的正确结果。除此以外，还给你一个整数 k ，表示你能进行以下操作的最多次数：
 * 每次操作中，将问题的正确答案改为 'T' 或者 'F' （也就是将 answerKey[i] 改为 'T' 或者 'F' ）。
 * 请你返回在不超过 k 次操作的情况下，最大 连续 'T' 或者 'F' 的数目。
 */
public class MaxConsecutiveAnswers_2024 {

    /**
     * 滑动窗口的思想
     *
     * @param answerKey
     * @param k
     * @return
     */
    public static int maxConsecutiveAnswers(String answerKey, int k) {
        char[] ans = answerKey.toCharArray();
        return Math.max(get(ans, 'T', k), get(ans, 'F', k));
    }

    public static int get(char[] ans, char aim, int k) {
        // count记录连续但非相同的字符串
        // 双指针的左右指针
        int count = 0, r = 0, l = 0;
        while (r < ans.length) {
            if (ans[r++] != aim) count++;
            if (count > k) {
                if (ans[l++] != aim) count--;
            }
        }
        return r - l;
    }

    public static void main(String[] args) {
        String answerKey = "TFFT";
        System.out.println(maxConsecutiveAnswers(answerKey, 2));
    }

}
