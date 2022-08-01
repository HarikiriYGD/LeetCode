package LeetCode.Practise_Everyday.Date_2022_06;

import java.util.*;

/**
 * @Auther: Lil_boat
 * @Date: 11:45 2022/6/26
 * @Tile: 黑名单中的随机数
 * @Description: 给定一个整数 n 和一个 无重复 黑名单整数数组 blacklist 。
 * 设计一种算法，从 [0, n - 1] 范围内的任意整数中选取一个 未加入 黑名单 blacklist 的整数。
 * 任何在上述范围内且不在黑名单 blacklist 中的整数都应该有 同等的可能性 被返回。
 * <p>
 * 优化你的算法，使它最小化调用语言 内置 随机函数的次数。
 * <p>
 * 实现 Solution 类:
 * Solution(int n, int[] blacklist) 初始化整数 n 和被加入黑名单 blacklist 的整数
 * int pick() 返回一个范围为 [0, n - 1] 且不在黑名单 blacklist 中的随机整数
 * <p>
 * 链接：https://leetcode.cn/problems/random-pick-with-blacklist
 */
public class D26_Blacklist_710 {


    // 按官方给的思路，逻辑上构思一个0~n的整型数组，而我们把随机边界bound就设为(n - blacklist.length)
    // 也就是我们逻辑上认为0~n的所有整数中，刚好最后blacklist.length个数全是黑名单数
    // 这样我们只需要在0~bound上随机一次，就能得到一个可取随机白名单数
    // 那实际上不可能刚好黑名单数就全出现在最后
    // 所以我们要是可以把在前边出现的黑名单数，都一一映射到后边的非黑名单数的话，那就相当于实现了这样的效果了
    Random random;
    // 随机边界，即构思的0~n大整型数组，前边全白名单，后边全黑名单数的分界线
    int bound;
    // 存放前面出现黑名单，通过键值就能一一映射到后边非黑名单的map集合
    Map<Integer, Integer> map;

    public D26_Blacklist_710(int n, int[] blacklist) {
        this.bound = n - blacklist.length;
        this.random = new Random();
        this.map = new HashMap<>();

        HashSet<Integer> backBlack = new HashSet<>();
        for (int x : blacklist) {
            // 比如x拿到50，既表示它数本身是50，但同时也表示对应在0~n的大数组中，它的下标也是50
            // 故x > bound可以表示x的下标位置在边界后，即将它添加到边界后黑名单集合set中
            if (x >= bound)
                backBlack.add(x);
        }

        int w = bound;
        for (int x : blacklist) {
            // 去找边界后的非黑名单数
            // 从边界数往后依次判断，若此数是边界后黑名单数，那向后继续找
            if (x < bound) {
                while (backBlack.contains(x)) w++;
                map.put(x, w);
                w++;
            }
        }
    }

    public int pick() {
        //如果边界前随机的就是白名单数，直接返回就完了，否则返回这个边界前黑名单数对应的边界后映射

        int r = random.nextInt(bound);
        return map.getOrDefault(r, r);
    }

}
