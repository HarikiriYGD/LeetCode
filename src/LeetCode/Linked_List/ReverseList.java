package LeetCode.Linked_List;

import java.util.Stack;

/**
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 */
public class ReverseList {

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
     * 最简单的一种方式就是使用栈，因为栈是先进后出的。
     * 实现原理就是把链表节点一个个入栈，当全部入栈完之后再一个个出栈，出栈的时候在把出栈的结点串成一个新的链表。
     *
     * @param head
     * @return
     */
    public ListNode reverseList_Stack(ListNode head) {
        // 构建空栈
        Stack<ListNode> stack = new Stack<>();
        // 将所有的元素压入栈内
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        if (stack.isEmpty()) return null;
        ListNode node = stack.pop();
        ListNode dummy = node;
        // 将栈中的所有元素出栈
        while (!stack.isEmpty()) {
            ListNode tempNode = stack.pop();
            node.next = tempNode;
            node = node.next;
        }
        // 最后一个结点就是反转前的头结点，一定要让他的next
        // 等于空，否则会构成环
        node.next = null;
        return dummy;
    }

    /**
     * 双链表求解是把原链表的结点一个个摘掉，每次摘掉的链表都让他成为新的链表的头结点，然后更新新链表。
     *
     * @param head
     * @return
     */
    public ListNode reverseList_TwoLinkedList(ListNode head) {
        // 构建一个新的链表
        ListNode newHead = null;
        while (head != null) {
            // 先保存访问的节点的下一个节点，保存起来
            // 留着下一步访问的
            ListNode temp = head.next;
            // 每次访问的原链表节点都会成为新链表的头结点，
            // 其实就是把新链表挂到访问的原链表节点的
            // 后面就行了
            head.next = newHead;
            newHead = head;
            head = temp;
        }
        return newHead;
    }

    /**
     * @param head
     * @return
     */
    public ListNode reverseList_Recursion(ListNode head) {
        // 终止条件
        if (head == null || head.next == null) return head;
        // 保存当前节点的下一个节点
        ListNode temp = head.next;
        // 从当前节点的下一个结点开始递归调用
        ListNode reverse = reverseList_Recursion(temp);
        // reverse是反转之后的链表，因为函数reverseList
        // 表示的是对链表的反转，所以反转完之后next肯定
        // 是链表reverse的尾结点，然后我们再把当前节点
        // head挂到next节点的后面就完成了链表的反转。
        temp.next = head;
        head.next = null;
        return reverse;
    }

}
