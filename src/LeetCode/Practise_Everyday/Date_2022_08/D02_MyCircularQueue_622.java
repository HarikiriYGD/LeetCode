package LeetCode.Practise_Everyday.Date_2022_08;

/**
 * @Author: Lil_boat
 * @Date: 2022/8/2 18:29
 * @Title: 设计循环队列
 * @Description: 设计你的循环队列实现。
 * 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 * 循环队列的一个好处是我们可以利用这个队列之前用过的空间。在一个普通队列里，一旦一个队列满了，
 * 我们就不能插入下一个元素，即使在队列前面仍有空间。但是使用循环队列，我们能使用这些空间去存储新的值。
 * <p>
 * 你的实现应该支持如下操作：
 * <p>
 * MyCircularQueue(k): 构造器，设置队列长度为 k 。
 * Front: 从队首获取元素。如果队列为空，返回 -1 。
 * Rear: 获取队尾元素。如果队列为空，返回 -1 。
 * enQueue(value): 向循环队列插入一个元素。如果成功插入则返回真。
 * deQueue(): 从循环队列中删除一个元素。如果成功删除则返回真。
 * isEmpty(): 检查循环队列是否为空。
 * isFull(): 检查循环队列是否已满。
 * <p>
 * 链接：https://leetcode.cn/problems/design-circular-queue
 */
public class D02_MyCircularQueue_622 {

    /**
     * front: 队首指针
     * rear: 队尾指针
     * size: 循环队列的大小
     * data[]: 存储数据
     */
    private int front;
    private int rear;
    private int size;
    private int[] data;

    public D02_MyCircularQueue_622(int k) {
        this.size = k;
        data = new int[k + 1];
    }

    /**
     * 入队元素
     *
     * @param value
     * @return
     */
    public boolean enQueue(int value) {
        // 判断队列是否满
        if (isFull()) return false;
        // 队尾指针 + 1
        rear = (rear + 1) % data.length;
        data[rear] = value;
        return true;
    }

    /**
     * 出队元素
     *
     * @return
     */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        front = (front + 1) % data.length;
        return true;
    }


    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        int temp = (front + 1) % data.length;
        return data[temp];
    }

    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        return data[rear];
    }

    /**
     * 判断循环队列是否为空
     *
     * @return
     */
    public boolean isEmpty() {
        if (front == rear) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断队列是否满
     *
     * @return
     */
    public boolean isFull() {
        // 如果队尾指针 + 1 % 队列长度 = 队首指针 则表明已经满
        if ((rear + 1) % data.length == front) {
            return true;
        }
        return false;
    }


}
