package LeetCode.QueueAndStack;

import java.util.Stack;

/**
 * @Auther: Lil_boat
 * @Date: 13:42 2021/12/23
 * @Description: 用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 */
public class CQueue_09 {

    Stack<Integer> s1 = new Stack<>();// 只会有一个元素
    Stack<Integer> s2 = new Stack<>();

    public CQueue_09() {
        this.s1 = s1;
        this.s2 = s2;
    }

    public void appendTail(int value) {
        s2.push(value);
    }

    public int deleteHead() {
        while (!s2.isEmpty()) {
            s1.push(s2.pop());
        }
        int res = s1.isEmpty() ? -1 : s1.pop();
        while (!s1.isEmpty()) {
            s2.push(s1.pop());
        }
        return res;
    }

    /**
     * Your CQueue object will be instantiated and called as such:
     * CQueue obj = new CQueue();
     * obj.appendTail(value);
     * int param_2 = obj.deleteHead();
     */

}
