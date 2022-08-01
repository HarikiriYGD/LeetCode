package LeetCode.Practise_Everyday.Date_2022_06;
/**
 * @Auther: Lil_boat
 * @Date: 9:12 2022/6/18
 * @Tile: 剑指 Offer II 029. 排序的循环链表
 * @Description: 给定循环单调非递减列表中的一个点，写一个函数向这个列表中插入一个新元素 insertVal ，使这个列表仍然是循环升序的。
 * 给定的可以是这个列表中任意一个顶点的指针，并不一定是这个列表中最小元素的指针。
 * 如果有多个满足条件的插入位置，可以选择任意一个位置插入新的值，插入后整个列表仍然保持有序。
 * 如果列表为空（给定的节点是 null），需要创建一个循环有序列表并返回这个节点。否则。请返回原先给定的节点
 *
 * 链接：https://leetcode.cn/problems/4ueAj6
 */
public class D18_Insert_Offer029 {

    public Node insert(Node head, int insertVal) {
        Node cur = null;
        Node next = null;
        Node realHead = null;
        // 空的
        if (head == null){
            head = new Node(insertVal);
            head.next = head;
            return head;
        }
        // 找到真正的头结点
        cur = head;
        next = head.next;
        // 如果不满足循环条件，则说明已经找到最小值了
        while (cur.val <= next.val){
            cur = cur.next;
            next = next.next;
            if (cur == head) break;
        }
        realHead = next;

        // 寻找插入位置
        while (next.val < insertVal){
            cur = next;
            next = next.next;
            if (next == realHead) break;
        }
        // 连接
        cur.next = new Node(insertVal);
        cur = cur.next;
        cur.next = next;
        return head;
    }

}
