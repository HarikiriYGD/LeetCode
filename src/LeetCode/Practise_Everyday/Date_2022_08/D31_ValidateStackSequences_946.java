package LeetCode.Practise_Everyday.Date_2022_08;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author: Lil_boat
 * @Date: 2022/8/31 16:00
 * @Title: 验证栈序列
 * @Description: 给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，
 * 只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false 。
 * <p>
 * 链接：https://leetcode.cn/problems/validate-stack-sequences
 */
public class D31_ValidateStackSequences_946 {

    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> deque = new ArrayDeque<>();
        // popped索引
        int j = 0;
        for (int i = 0; i < pushed.length; i++) {
            deque.push(pushed[i]);
            while (!deque.isEmpty() && deque.peek() == popped[j]) {
                j++;
                deque.pop();
            }
        }
        return deque.isEmpty();
    }

}
