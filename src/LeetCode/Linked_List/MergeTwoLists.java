package LeetCode.Linked_List;

/**
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class MergeTwoLists {

    /**
     * 双指针的方式遍历两个链表
     *
     * @param l1
     * @param l2
     * @return 合并之后的序列
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode p1 = l1;
        ListNode p2 = l2;
        ListNode res = new ListNode();
        // 头指针
        ListNode temp = res;
        while (p1 != null && p2 != null) {
            if (p1.val <= p2.val) {
                temp.next = new ListNode(p1.val);
                p1 = p1.next;
            } else {
                temp.next = new ListNode(p2.val);
                p2 = p2.next;
            }
            temp = temp.next;
        }
        // 如果剩下的元素全部添入进入res链表中
        if (p1 == null) {
            temp.next = p2;
        } else {
            temp.next = p1;
        }
        return res.next;
    }

    /**
     * 递归的方式解决合并方案
     *
     * @param l1
     * @param l2
     * @return
     */
    public ListNode mergeTwoLists_recursion(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val <= l2.val) {
            l1.next = mergeTwoLists_recursion(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeTwoLists_recursion(l1, l2.next);
            return l2;
        }
    }

    public static void main(String[] args) {

    }

}
