package LeetCode.Practise_Everyday.Date_2022_08;

/**
 * @Author: Lil_boat
 * @Date: 2022/8/15 20:25
 * @Title: 设计循环双端队列
 * @Description: 设计实现双端队列。
 *
 * 实现 MyCircularDeque 类:
 *
 * MyCircularDeque(int k) ：构造函数,双端队列最大为 k 。
 * boolean insertFront()：将一个元素添加到双端队列头部。 如果操作成功返回 true ，否则返回 false 。
 * boolean insertLast() ：将一个元素添加到双端队列尾部。如果操作成功返回 true ，否则返回 false 。
 * boolean deleteFront() ：从双端队列头部删除一个元素。 如果操作成功返回 true ，否则返回 false 。
 * boolean deleteLast() ：从双端队列尾部删除一个元素。如果操作成功返回 true ，否则返回 false 。
 * int getFront() )：从双端队列头部获得一个元素。如果双端队列为空，返回 -1 。
 * int getRear() ：获得双端队列的最后一个元素。 如果双端队列为空，返回 -1 。
 * boolean isEmpty() ：若双端队列为空，则返回 true ，否则返回 false  。
 * boolean isFull() ：若双端队列满了，则返回 true ，否则返回 false 。
 *
 * 链接：https://leetcode.cn/problems/design-circular-deque
 */
public class D15_MyCircularDeque_641 {

    int[] nums;
    int he, ta, cnt, k;
    public D15_MyCircularDeque_641(int _k) {
        k = _k;
        nums = new int[k];
    }
    public boolean insertFront(int value) {
        if (isFull()) return false;
        he = (he + k - 1) % k;
        nums[he] = value; cnt++;
        return true;
    }
    public boolean insertLast(int value) {
        if (isFull()) return false;
        nums[ta++] = value; cnt++;
        ta %= k;
        return true;
    }
    public boolean deleteFront() {
        if (isEmpty()) return false;
        he = (he + 1) % k; cnt--;
        return true;
    }
    public boolean deleteLast() {
        if (isEmpty()) return false;
        ta = (ta + k - 1) % k; cnt--;
        return true;
    }
    public int getFront() {
        return isEmpty() ? -1 : nums[he];
    }
    public int getRear() {
        return isEmpty() ? -1 : nums[(ta + k - 1) % k];
    }
    public boolean isEmpty() {
        return cnt == 0;
    }
    public boolean isFull() {
        return cnt == k;
    }

}
