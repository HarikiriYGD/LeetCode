package LeetCode.Linked_List;

/**
 * @Auther: Lil_boat
 * @Date: 17:43 2021/12/14
 * @Description: 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照逆序的方式存储的，
 * 并且每个节点只能存储 位数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头
 */
public class AddTwoNumbers {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 新建一个头指针
        ListNode res = new ListNode(0);
        // 记录当前节点
        ListNode curr = res;
        // 进位
        int flag = 0;
        // l1或l2或进位不为零时一直向后遍历
        while (l1 != null || l2 != null || flag != 0) {
            // 当l1或l2不为空时 取值
            int l1val = l1 != null ? l1.val : 0;
            int l2val = l2 != null ? l2.val : 0;
            // 值的和
            int sum = l1val + l2val + flag;
            // 判断有无进位
            flag = sum / 10;
            curr.next = new ListNode(sum % 10);
            curr = curr.next;
            // 向后遍历
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        return res.next;
    }

}
