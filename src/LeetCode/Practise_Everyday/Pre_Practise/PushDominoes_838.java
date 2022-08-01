package LeetCode.Practise_Everyday.Pre_Practise;

/**
 * @Auther: Lil_boat
 * @Date: 14:00 2022/2/21
 * @Description: n 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。在开始时，同时把一些多米诺骨牌向左或向右推。
 * <p>
 * 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
 * <p>
 * 如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。
 * <p>
 * 就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。
 * <p>
 * 给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：
 * <p>
 * dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
 * dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧，
 * dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。
 * 返回表示最终状态的字符串。
 */
public class PushDominoes_838 {

    /*
        双指针：
            可以使用「双指针」的方式寻找 "."左右两边距离最近的被推倒的牌，形成"X....Y"型的区间。
            在这两个被推倒了牌形成的区间里，根据左右两端的牌不同，有四种可能性：
                    'R......R' => 'RRRRRRRR'
                    'R......L' => 'RRRRLLLL' or 'RRRR.LLLL'
                    'L......R' => 'L......R'
                    'L......L' => 'LLLLLLLL'
            使用双指针算法：
                l指向区间的开始（指向 "L" 或者 "R"）；
                r跳过所有的 "."，指向区间的结束（也指向 "L" 或者 "R"）。
                此时区间的形状为 "X....Y"，判断这个区间左右端点的 "X"、 "Y"是什么，确定中间的 "."的状态。
            由于可能存在输入的dominoes的最左边和最右边都是 "."，那么形成不了"X....Y" 这样的区间。
            所以，下面的代码中，给dominoes最左边添加了一个 "L"，最右边添加了一个 "R"，
            添加的这两个虚拟的牌不会影响dominoes内部所有的牌的倒向，但是有助于我们形成区间，而且这两个添加的牌，也不会放到最终结果里。
     */
    public static String pushDominoes(String dominoes) {
        // 左右各放入虚拟的牌
        dominoes = "L" + dominoes + "R";
        int l = 0;
        StringBuilder res = new StringBuilder();
        for (int r = 1; r < dominoes.length(); r++) {
            if (dominoes.charAt(r) == '.') continue;
            // 虚拟的牌不放进结果集
            if (l != 0) res.append(dominoes.charAt(l));
            // 左右的长度
            int mid = r - l - 1;
            // 如果左右区间的端点的值都相同 则是以下情况
            // 'L......L' => 'LLLLLLLL'
            // 'R......R' => 'RRRRRRRR'
            if (dominoes.charAt(l) == dominoes.charAt(r)) {
                for (int i = 0; i < mid; i++) {
                    res.append(dominoes.charAt(l));
                }
                // 'L......R' => 'L......R'
            } else if (dominoes.charAt(l) == 'L' && dominoes.charAt(r) == 'R') {
                for (int i = 0; i < mid; i++) {
                    res.append('.');
                }
                // 'R......L' => 'RRRRLLLL' or 'RRRR.LLLL'
            } else {
                for (int i = 0; i < mid / 2; i++) {
                    res.append(dominoes.charAt(l));
                }
                // 如果长度是奇数 则说明受力平衡
                if (mid % 2 == 1) res.append('.');
                for (int i = 0; i < mid / 2; i++) {
                    res.append(dominoes.charAt(r));
                }
            }
            // 继续向后遍历

            l = r;
        }
        return res.toString();
    }

    public static void main(String[] args) {
        String dominoes = ".L.R...LR..L..";
        System.out.println(pushDominoes(dominoes));
    }

}
