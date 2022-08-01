package LeetCode.Linked_List;

import java.util.Stack;

/**
 * @Auther: Lil_boat
 * @Date: 17:13 2021/12/23
 * @Description: 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 */
public class ReverseList_20 {

    /**
     *
     * @param head
     * @return
     */
    public ListNode reverseList_Iteration(ListNode head) {
        ListNode h = null;
        ListNode pre = null;
        while (head != null) {
            // 记录现在的节点
            pre = head;
            // 向后移动一个节点
            head = head.next;
            // 现在的前一个节点是h
            pre.next = h;
            // h的后面一个节点是pre
            h = pre;
        }
        return h;
    }

    /**
     * 递归的方式
     * @param head
     * @return
     */
    public ListNode reverseList_Recursion(ListNode head){
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverseList_Recursion(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;

    }

    /**
     * 运用栈的方式去解决
     * 注意的是：用栈的方法，遍历到最后一个元素就截止！！！
     *
     * @param head
     * @return
     */
    public ListNode reverseList_Stack(ListNode head) {
        Stack<ListNode> s = new Stack<>();
        while (head != null && head.next != null) {
            s.push(head);
            head = head.next;
        }
        ListNode h = head;
        while (!s.isEmpty()) {
            ListNode l = new ListNode(s.pop().val);
            head.next = l;
            head = head.next;
        }
        return h;
    }

}
