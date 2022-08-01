package LeetCode.Practise_Everyday.Date_2022_07;

import java.util.*;

/**
 * @Author: Lil_boat
 * @Date: 2022/7/13 9:24
 * @Title: 行星碰撞
 * @Description: 给定一个整数数组 asteroids，表示在同一行的行星。
 * 对于数组中的每一个元素，其绝对值表示行星的大小，正负表示行星的移动方向（正表示向右移动，负表示向左移动）。每一颗行星以相同的速度移动。
 * 找出碰撞后剩下的所有行星。碰撞规则：两个行星相互碰撞，较小的行星会爆炸。如果两颗行星大小相同，则两颗行星都会爆炸。
 * 两颗移动方向相同的行星，永远不会发生碰撞。
 * <p>
 * 链接：https://leetcode.cn/problems/asteroid-collision
 */
public class D13_AsteroidCollision_735 {

    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> deque = new ArrayDeque<>();
        for (int x : asteroids) {
            boolean flag = true;
            // 只有栈顶元素向正向且入栈元素向负向才会抵消
            while (flag && !deque.isEmpty() && deque.peekLast() > 0 && x < 0) {
                int a = Math.abs(deque.peekLast()), b = Math.abs(x);
                // 如果入栈的质量比准备入栈的质量小，则弹出
                if (a <= b) deque.pollLast();
                // 如果入栈的质量大于等于准备入栈的行星，则将状态位改变
                if (a >= b) flag = false;
            }
            if (flag) deque.addLast(x);
        }
        // 将栈中的元素全部加入到队列中
        int len = deque.size() - 1;
        int[] ans = new int[deque.size()];
        while (!deque.isEmpty()) {
            ans[len--] = deque.pollLast();
        }
        return ans;
    }


    public static void main(String[] args) {
        int[] a = {-2, 2, 1, -2};
        D13_AsteroidCollision_735 t = new D13_AsteroidCollision_735();
        int[] res = t.asteroidCollision(a);
        System.out.println(Arrays.toString(res));
    }

}
