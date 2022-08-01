package LeetCode.Practise_Everyday.Date_2022_03;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: Lil_boat
 * @Date: 9:46 2022/3/16
 * @Description: 请你设计一个用于存储字符串计数的数据结构，并能够返回计数最小和最大的字符串。
 * <p>
 * 实现 AllOne 类：
 * <p>
 * AllOne() 初始化数据结构的对象。
 * inc(String key) 字符串 key 的计数增加 1 。如果数据结构中尚不存在 key ，那么插入计数为 1 的 key 。
 * dec(String key) 字符串 key 的计数减少 1 。如果 key 的计数在减少后为 0 ，那么需要将这个 key 从数据结构中删除。
 * 测试用例保证：在减少计数前，key 存在于数据结构中。
 * getMaxKey() 返回任意一个计数最大的字符串。如果没有元素存在，返回一个空字符串 "" 。
 * getMinKey() 返回任意一个计数最小的字符串。如果没有元素存在，返回一个空字符串 "" 。
 */
public class AllOne_432 {
    /**
     * 双向链表的数据结构
     */
    class Node {
        int cnt;
        Set<String> set = new HashSet<>();
        Node left, right;

        Node(int _cnt) {
            cnt = _cnt;
        }
    }

    Node head, tail;
    Map<String, Node> map = new HashMap<>();

    public AllOne_432() {
        head = new Node(-1000);
        tail = new Node(-1000);
        head.right = tail;
        tail.left = head;
    }

    public void clear(Node node) {
        if (node.set.size() == 0) {
            node.left.right = node.right;
            node.right.left = node.left;
        }
    }

    public void inc(String key) {
        // 如果map中已经有这个key
        /*
            若存在：根据其所属的 Node 的计数 cnt 为多少，并结合当前是「增加计数」还是「减少计数」来决定是找 Node 的「右节点」还是「左节点」，
            同时检查相邻节点的计数值 cnt 是否为目标值，对应要检查数值是 cnt + 1 和 cnt - 1：
                若相邻节点的 cnt 为目标值：即目标节点存在，将 key 从原 Node 的 set 集合中移除，并添加到目标节点的集合中，更新哈希表；
                若相邻节点的 cnt 不是目标值：则需要创建相应的目标节点，并构建双向链表关系，把 key 存入新创建的目标节点，更新哈希表。
         */
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.set.remove(key);
            // 获取str的个数
            int cnt = node.cnt;
            Node next = null;
            if (node.right.cnt == cnt + 1) {
                next = node.right;
            } else {
                next = new Node(cnt + 1);
                next.right = node.right;
                next.left = node;
                node.right.left = next;
                node.right = next;
            }
            next.set.add(key);
            map.put(key, next);
            clear(node);
            // 不存在key 将其插入其中
            /*
                若不存在（只能是 inc 操作）：查找是否存在 cnt = 1 的节点（也就是检查 head.right 节点的计数值）：
                    如果存在 cnt = 1 的目标节点：将 key 添加到目标节点的 set 集合中，更新哈希表；
                    若不存在 cnt = 1 的目标节点：创建相应的节点，并构建双向关系，并构建双向链表关系，把 key 存入新创建的目标节点，更新哈希表。
             */
        } else {
            Node node = null;
            if (head.right.cnt == 1) {
                node = head.right;
            } else {
                node = new Node(1);
                node.right = head.right;
                node.left = head;
                head.right.left = node;
                head.right = node;
            }
            node.set.add(key);
            map.put(key, node);
        }
    }

    // 原理同inc
    public void dec(String key) {
        Node node = map.get(key);
        node.set.remove(key);
        int cnt = node.cnt;
        if (cnt == 1) {
            map.remove(key);
        } else {
            Node prev = null;
            if (node.left.cnt == cnt - 1) {
                prev = node.left;
            } else {
                prev = new Node(cnt - 1);
                prev.right = node;
                prev.left = node.left;
                node.left.right = prev;
                node.left = prev;
            }
            prev.set.add(key);
            map.put(key, prev);
        }
        clear(node);
    }

    // getMaxKey/getMinKey 操作：分别从 tail.left 和 head.right 中尝试查找，如果存在非哨兵节点，则从节点的 set 集合中取任意元素进行返回，否则返回空串。

    public String getMaxKey() {
        Node node = tail.left;
        for (String s : node.set) {
            return s;
        }
        return "";
    }

    public String getMinKey() {
        Node node = head.right;
        for (String s : node.set) {
            return s;
        }
        return "";
    }
}

