package LeetCode.Design_problem;


import java.util.Stack;

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

/**
 * @Auther: Lil_boat
 * @Date: 15:25 2021/12/24
 * @Description: 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。
 */
public class MinStack {
    // 栈1存放需要压栈的值
    Stack<Integer> stack1 = new Stack<>();
    // 栈2存放最小的值
    Stack<Integer> stack2 = new Stack<>();

    public MinStack() {
        stack2.push(Integer.MAX_VALUE);
    }

    public void push(int val) {
        stack1.push(val);
        if (stack2.isEmpty() || val <= getMin()) {
            stack2.push(val);
        }
    }

    public void pop() {
        //如果出栈的值等于最小值，说明栈中的最小值
        //已经出栈了，因为stack2中的栈顶元素存放的
        //就是最小值，所以stack2栈顶元素也要出栈
        if (stack1.pop() == getMin())
            stack2.pop();
    }

    public int top() {
        return stack1.peek();
    }

    public int getMin() {
        return stack2.peek();
    }

}
