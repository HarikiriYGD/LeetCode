package LeetCode.QueueAndStack;

public class MyCircularQueue {

    private int front;
    private int rear;
    private int size;
    private int data[];

    public MyCircularQueue(int k) {
        this.size = k;
        data = new int[k + 1];
    }

    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        rear = (rear + 1) % data.length;
        data[rear] = value;
        return true;
    }

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

    public boolean isEmpty() {
        if (rear == front) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isFull() {
        if ((rear + 1) % data.length == front) {
            return true;
        }
        return false;
    }

}
