package LeetCode.QueueAndStack;

import java.util.*;

/**
 * @Auther: Lil_boat
 * @Date: 15:13 2021/12/27
 * @Description: 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 * <p>
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 */
public class MaxQueue_59 {

    // 单向队列存储数据
    Queue<Integer> queue;
    // 双向队列存储单调递减的最大值
    Deque<Integer> deque;

    public MaxQueue_59() {
        this.queue = new LinkedList<>();
        this.deque = new LinkedList<>();
    }

    public int max_value() {
        return deque.isEmpty() ? -1 : deque.peekFirst();
    }

    public void push_back(int value) {
        queue.offer(value);
        while (!deque.isEmpty() && deque.peekLast() < value) deque.pollLast();
        deque.offerLast(value);
    }

    public int pop_front() {
        if (queue.isEmpty()) return -1;
        if (queue.peek().equals(deque.peekFirst())) deque.removeFirst();
        return queue.poll();
    }
}
