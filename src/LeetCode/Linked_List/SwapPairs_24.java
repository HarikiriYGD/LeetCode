package LeetCode.Linked_List;

/**
 * @Auther: Lil_boat
 * @Date: 15:13 2022/4/8
 * @Description: 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
 * 你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 */
public class SwapPairs_24 {

    /**
     * 迭代的方式
     *
     * @param head
     * @return
     */
    public ListNode swapPairs_iteration(ListNode head) {
        ListNode pre = new ListNode(0), next;
        pre.next = head;
        ListNode temp = pre;
        while (pre.next != null && pre.next.next != null) {
            next = head.next;
            pre.next = next;
            head.next = next.next;
            next.next = head;
            pre = head;
            head = head.next;
        }
        return temp.next;
    }

    /**
     * 递归的方式
     *
     * @param head
     * @return 可以通过递归的方式实现两两交换链表中的节点。
     * <p>
     * 递归的终止条件是链表中没有节点，或者链表中只有一个节点，此时无法进行交换。
     * 如果链表中至少有两个节点，则在两两交换链表中的节点之后，原始链表的头节点变成新的链表的第二个节点，
     * 原始链表的第二个节点变成新的链表的头节点。链表中的其余节点的两两交换可以递归地实现。
     * 在对链表中的其余节点递归地两两交换之后，更新节点之间的指针关系，即可完成整个链表的两两交换。
     * 用 head 表示原始链表的头节点，新的链表的第二个节点，用 newHead 表示新的链表的头节点，
     * 原始链表的第二个节点，则原始链表中的其余节点的头节点是 newHead.next。
     * 令 head.next = swapPairs(newHead.next)，表示将其余节点进行两两交换，
     * 交换后的新的头节点为 head 的下一个节点。然后令 newHead.next = head，即完成了所有节点的交换。
     * 最后返回新的链表的头节点 newHead。
     */
    public ListNode swapPairs_Recursion(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode newHead = head.next;
        head.next = swapPairs_Recursion(newHead.next);
        newHead.next = head;
        return newHead;
    }

}
