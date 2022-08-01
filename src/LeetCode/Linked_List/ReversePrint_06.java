package LeetCode.Linked_List;

import java.util.Stack;

/**
 * @Auther: Lil_boat
 * @Date: 17:31 2021/12/22
 * @Description: 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 */
public class ReversePrint_06 {
    /**
     * 辅助栈的方法
     *
     * 执行用时：1 ms, 在所有 Java 提交中击败了69.08%的用户
     * 内存消耗：39 MB, 在所有 Java 提交中击败了52.18%的用户
     * @param head
     * @return
     */
    public int[] reversePrint(ListNode head) {
        // 统计节点个数
        int count = 0;
        Stack<ListNode> s = new Stack<>();
        while (head != null) {
            // 入栈
            s.push(head);
            head = head.next;
            // 计数+1
            count++;
        }
        int[] res = new int[count];
        // 赋值
        for (int i = 0; i < count; i++) {
            res[i] = s.pop().val;
        }
        return res;
    }

    /**
     * 效率很高
     * 执行用时：0 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：38.9 MB, 在所有 Java 提交中击败了57.85%的用户
     * @param head
     * @return
     */
    public int[] reversePrint_Optimization(ListNode head) {
        // 统计个数
        int len = 0;
        // 建立临时指针
        ListNode tmp = head;
        while (tmp != null) {
            tmp = tmp.next;
            len++;
        }
        int[] res = new int[len];
        // 从尾部开始赋值
        while (head != null) {
            res[len - 1] = head.val;
            head = head.next;
        }
        return res;
    }
}
