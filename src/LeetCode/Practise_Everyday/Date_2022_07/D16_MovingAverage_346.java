package LeetCode.Practise_Everyday.Date_2022_07;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @Author: Lil_boat
 * @Date: 2022/7/16 11:28
 * @Title: 滑动窗口的平均值
 * @Description: 给定一个整数数据流和一个窗口大小，根据该滑动窗口的大小，计算滑动窗口里所有数字的平均值。
 * 实现 MovingAverage 类：
 * MovingAverage(int size) 用窗口大小 size 初始化对象。
 * double next(int val) 成员函数 next 每次调用的时候都会往滑动窗口增加一个整数，
 * 请计算并返回数据流中最后 size 个值的移动平均值，即滑动窗口里所有数字的平均值。
 * <p>
 * 链接：https://leetcode.cn/problems/qIsx9U
 */
public class D16_MovingAverage_346 {

    int sum = 0, size;
    Deque<Integer> deque = new ArrayDeque<>();

    D16_MovingAverage_346(int size) {
        this.size = size;
    }

    public double next(int val) {
        // 入队
        deque.addLast(val);
        if (deque.size() <= size) {
            sum += val;
            return (double) sum / deque.size();
        }
        int cur = deque.pollFirst();
        sum -= cur - val;
        return (double) sum / deque.size();
    }

    public static void main(String[] args) {
        D16_MovingAverage_346 t = new D16_MovingAverage_346(3);
        System.out.println(t.next(1));
        System.out.println(t.next(10));
        System.out.println(t.next(3));
        System.out.println(t.next(5));
    }

}
