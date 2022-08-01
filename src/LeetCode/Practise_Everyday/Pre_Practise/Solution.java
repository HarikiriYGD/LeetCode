package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.ArrayList;
import java.util.Random;
/**
 * @Auther: Lil_boat
 * @Date: 12:46 2022/1/17
 * @Description: 给你一个单链表，随机选择链表的一个节点，并返回相应的节点值。每个节点 被选中的概率一样 。
 * 实现 Solution 类：
 * Solution(ListNode head) 使用整数数组初始化对象。
 * int getRandom() 从链表中随机选择一个节点并返回该节点的值。链表中所有节点被选中的概率相等。
 */
public class Solution {
    /*
        我们可以在初始化时，用一个数组记录链表中的所有元素，这样随机选择链表的一个节点，就变成在数组中随机选择一个元素。
     */
    Random r = new Random();
    ArrayList<Integer> list;
    public Solution(ListNode head) {
        list = new ArrayList<>();
        while(head!=null){
            list.add(head.val);
            head = head.next;
        }
        Random r = new Random();
    }

    public int getRandom() {
        return list.get(r.nextInt(list.size()));
    }
}
