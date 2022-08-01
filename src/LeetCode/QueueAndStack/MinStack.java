package LeetCode.QueueAndStack;


import java.util.Stack;

/**
 *
 */
public class MinStack {

    Stack<Integer> stack1 = new Stack<>(); // 初始化栈
    Stack<Integer> stack2 = new Stack<>(); // 辅助栈存储最小值


    public MinStack() {
        stack2.push(Integer.MAX_VALUE);// 先加入整型最大值在栈底，避免判断辅助栈是否为空
    }

    /**
     * 将元素 x 推入栈中
     *
     * @param val
     */
    public void push(int val) {
        stack1.push(val);
        if (stack2.peek() >= val) stack2.push(val);
    }

    /**
     * 删除栈顶的元素。
     */
    public void pop() {
        int tmp = stack1.pop();
        if (tmp == stack2.peek()) stack2.pop(); // 弹出栈的元素值如果和辅助栈顶元素值相等，也在辅助栈弹出它


    }

    /**
     * 获取栈顶元素
     *
     * @return 返回栈顶元素
     */
    public int top() {
        if (!stack1.empty()) {
            return stack1.peek();
        }
        return -1;
    }

    /**
     * 检索栈中的最小元素
     *
     * @return 返回栈中最小元素
     */
    public int getMin() {
        return stack2.peek();
    }
}
