package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 10:59 2022/1/28
 * @Description: 你正在参加一个多角色游戏，每个角色都有两个主要属性：攻击 和 防御 。
 * 给你一个二维整数数组 properties ，其中 properties[i] = [attack_i, defense_i] 表示游戏中第 i 个角色的属性。
 * 如果存在一个其他角色的攻击和防御等级 都严格高于 该角色的攻击和防御等级，则认为该角色为 弱角色 。
 * 更正式地，如果认为角色 i 弱于 存在的另一个角色 j ，那么 attack_j > attack_i 且 defense_j > defense_i 。
 * <p>
 * 返回 弱角色 的数量。
 */
public class NumberOfWeakCharacters_1996 {
    public static int numberOfWeakCharacters(int[][] properties) {
        // 第一排序根据攻击力降序排列
        // 第二排序根据 如果攻击力相同则根据防御力升序排序
        Arrays.sort(properties, (o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o2[0] - o1[0]);
        int cnt = 0;
        int max = -1;
        for (int i = 0; i < properties.length; i++) {
            if (max > properties[i][1]) cnt++;
            max = Math.max(max, properties[i][1]);
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[][] properties = {{5, 5}, {4, 6}, {6, 3}};
        System.out.println(numberOfWeakCharacters(properties));
    }
}
