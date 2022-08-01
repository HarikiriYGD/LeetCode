package LeetCode.Linked_List;

/**
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * <p>
 * 进阶：你能尝试使用一趟扫描实现吗？
 */
public class RemoveNthFromEnd {
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

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode pre = head;
        int len = length(head) - n;
        // 如果len等于0表示删除的是头结点
        if (len == 0) return head.next;
        // 找到删除元素的前一个节点
        for (int i = 0; i < len - 1; i++) {
            head = head.next;
        }
        head.next = head.next.next;
        // 返回头结点
        return pre;

    }

    /**
     * 求链表的长度
     *
     * @param head 头指针
     * @return 返回链表的长度
     */
    private static int length(ListNode head) {
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }
        return len;
    }
}
