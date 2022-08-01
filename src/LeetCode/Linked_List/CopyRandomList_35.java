package LeetCode.Linked_List;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Lil_boat
 * @Date: 16:13 2021/12/24
 * @Description: 其实就是深拷贝
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每
 * 个节点除了有一个 next 指针指向下一个节点，还有一个 random 指针指向链表中的任意节点或者 null。
 */
public class CopyRandomList_35 {

    /**
     * 利用哈希表的查询特点，考虑构建 原链表节点 和 新链表对应节点 的键值对映射关系，再遍历构建新链表各节点的 next 和 random 引用指向即可。
     * <p>
     * 算法流程：
     * 若头节点 head 为空节点，直接返回 null ；
     * 初始化： 哈希表 dic ， 节点 cur 指向头节点；
     * 复制链表：
     * 建立新节点，并向 dic 添加键值对 (原 cur 节点, 新 cur 节点） ；
     * cur 遍历至原链表下一节点；
     * 构建新链表的引用指向：
     * 构建新节点的 next 和 random 引用指向；
     * cur 遍历至原链表下一节点；
     * 返回值： 新链表的头节点 dic[cur]；
     * <p>
     * 复杂度分析：
     * 时间复杂度 O(N)： 两轮遍历链表，使用 O(N)时间。
     * 空间复杂度 O(N)： 哈希表 dic 使用线性大小的额外空间。
     *
     * @param head
     * @return
     */
    public Node copyRandomList_HashMap(Node head) {
        // 判断是否head为空节点
        if (head == null) return head;
        // 构建映射
        Map<Node, Node> map = new HashMap<>();
        Node cur = head;
        // 将原链表存储到HashMap中
        while (cur != null) {
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        cur = head;
        while (cur != null) {
            // 构建关系
            map.get(cur).next = map.get(cur.next);
            map.get(cur).random = map.get(cur.random);
            cur = cur.next;
        }
        return map.get(head);
    }

    public Node copyRandomList(Node head) {
        if (head == null) return head;
        Node cur = head;
        while (cur != null) {
            Node tmp = new Node(cur.val);
            tmp.next = cur.next;
            cur.next = tmp;
            cur = tmp.next;
        }
        cur = head;
        while (cur != null) {
            if (cur.random != null) cur.next.random = cur.random.next;
            cur = cur.next.next;
        }
        // 拆分链表
        cur = head.next;
        Node pre = head, res = head.next;
        while (cur.next != null) {
            pre.next = pre.next.next;
            cur.next = cur.next.next;
            pre = pre.next;
            cur = cur.next;
        }
        pre.next=null;
        return res;
    }

}
