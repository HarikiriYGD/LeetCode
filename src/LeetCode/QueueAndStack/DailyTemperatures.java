package LeetCode.QueueAndStack;

import java.util.Arrays;
import java.util.Stack;


public class DailyTemperatures {
    /**
     * 每日温度
     *
     * @param temperatures
     * @return 未来几天会出现高温
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        Stack<Integer> stack = new Stack<>(); // 定义栈
        int[] res = new int[temperatures.length]; // 构造数组
        for (int i = 0; i < temperatures.length; i++) { // 遍历温度数组
            while (!stack.isEmpty() && temperatures[i] > temperatures[stack.peek()]) { // 如果栈不为空且温度大于栈顶元素
                // System.out.println(i + "+" +stack.peek());
                int index = stack.pop(); // 弹出栈顶元素
                res[index] = i - index;
            }
            stack.push(i);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] t = {70, 74, 75, 71, 69, 72, 76, 73};
        int[] result = dailyTemperatures(t);
        String s = Arrays.toString(result);
        System.out.println(s);
    }
}
