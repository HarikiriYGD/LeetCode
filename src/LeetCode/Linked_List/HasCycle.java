package LeetCode.Linked_List;

import java.util.HashSet;
import java.util.Set;

public class HasCycle {

    /**
     * Definition for singly-linked list.
     */
    class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    /**
     * 判断链表是否有环应该是老生常谈的一个话题了，最简单的一种方式就是快慢指针，
     * 慢指针针每次走一步，快指针每次走两步，如果相遇就说明有环，如果有一个为空说明没有环。
     *
     * @param head
     * @return
     */
    public boolean hasCycle_fast_slow(ListNode head) {
        // 如果为空则返回不是环
        if (head == null) return false;
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            // 如果相遇则表明是环
            if (fast == slow) return true;
        }
        // 否则不是环
        return false;
    }

    /**
     * 这题还可以把节点存放到集合set中，每次存放的时候判断当前节点是否存在，
     * 如果存在，说明有环，直接返回true，
     * <p>
     * 效率较低
     *
     * @param head
     * @return
     */
    public boolean hasCycle_Set(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }

    /**
     * 一个链表从头节点开始一个个删除，所谓删除就是让他的next指针指向他自己。
     * 如果没有环，从头结点一个个删除，最后肯定会删完
     *
     * @param head
     * @return
     */
    public boolean hasCycle_Delete(ListNode head) {
        // 如果head为空，或者head.next为空，直接返回false
        if (head == null || head.next == null) return false;
        if (head.next == head) return true;
        // 保存下一个节点的值
        ListNode nextNode = head.next;
        // 自己指向自己就是把自己删除了
        head.next = head;
        //然后递归，查看下一个节点
        return hasCycle_Delete(nextNode);
    }

}
