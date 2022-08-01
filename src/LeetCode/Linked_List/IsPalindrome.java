package LeetCode.Linked_List;

import java.util.Stack;

/**
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 */
public class IsPalindrome {

    /**
     * Definition for singly-linked list.
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    /**
     * 用栈的方式来判断 先让所有元素入栈 再依次弹出元素和原来的链表元素比较
     *
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        ListNode temp = head;
        Stack<Integer> stack = new Stack();
        //把链表节点的值存放到栈中
        while (temp != null) {
            stack.push(temp.val);
            temp = temp.next;
        }

        //然后再出栈
        while (head != null) {
            if (head.val != stack.pop()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 这题是让判断链表是否是回文链表，所谓的回文链表就是以链表中间为中心点两边对称。
     * 我们常见的有判断一个字符串是否是回文字符串，这个比较简单，
     * 可以使用两个指针，一个最左边一个最右边，两个指针同时往中间靠，判断所指的字符是否相等。
     *
     * @param head
     * @return
     */
    public boolean isPalindrome_reverseLinkedList(ListNode head) {
        ListNode fast = head, slow = head;
        // 通过快慢指针找到中点
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        // 如果fast不为空，说明链表的个数为奇数个
        if (fast != null) {
            slow = slow.next;
        }
        // 反转链表
        slow = reverse(slow);

        fast = head;
        while (slow != null) {
            if (fast.val != slow.val) return false;
            fast = fast.next;
            slow = slow.next;
        }
        return true;
    }

    /**
     * 反转链表
     *
     * @param head
     * @return
     */
    public ListNode reverse(ListNode head) {
        ListNode pre = null;
        while (head != null) {
            ListNode next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }


    public static void main(String[] args) {

    }
}
