package LeetCode.Practise_Everyday.Pre_Practise;

/**
 * @Auther: Lil_boat
 * @Date: 14:08 2022/1/9
 * @Description: LeetCode 设计了一款新式键盘，正在测试其可用性。测试人员将会点击一系列键（总计 n 个），每次一个。
 * <p>
 * 给你一个长度为 n 的字符串 keysPressed ，其中 keysPressed[i] 表示测试序列中第 i 个被按下的键。
 * releaseTimes 是一个升序排列的列表，其中 releaseTimes[i] 表示松开第 i 个键的时间。
 * 字符串和数组的 下标都从 0 开始 。第 0 个键在时间为 0 时被按下，接下来每个键都 恰好 在前一个键松开时被按下。
 * 测试人员想要找出按键 持续时间最长 的键。第 i 次按键的持续时间为 releaseTimes[i] - releaseTimes[i - 1] ，第 0 次按键的持续时间为 releaseTimes[0] 。
 * 注意，测试期间，同一个键可以在不同时刻被多次按下，而每次的持续时间都可能不同。
 * 请返回按键 持续时间最长 的键，如果有多个这样的键，则返回 按字母顺序排列最大 的那个键。
 */
public class SlowestKey_1629 {
    public static char slowestKey(int[] releaseTimes, String keysPressed) {
        int max = releaseTimes[0];
        char ans = ' ';
        for (int i = 1; i < releaseTimes.length; i++) {
            max = Math.max(max, releaseTimes[i] - releaseTimes[i - 1]);
        }
        for (int i = 1; i < releaseTimes.length; i++) {
            if (releaseTimes[i - 1] == max&& i-1==0) ans = keysPressed.charAt(i - 1);
            if (releaseTimes[i] - releaseTimes[i - 1] == max && keysPressed.charAt(i) > ans) {
                ans = keysPressed.charAt(i);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] releaseTimes = {5, 11, 16, 26, 38, 61, 87, 98};
        String keyPressed = "htshfndz";
        System.out.println(slowestKey(releaseTimes, keyPressed));
    }
}
