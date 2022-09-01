package LeetCode.LRU;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Lil_boat
 * @Date: 2022/9/1 14:10
 * @Description: 自己定义数据结构来实现LRU
 */
public class LRUCacheByMyself {

    class Node<K, V> {
        K key;
        V value;
        Node<K, V> prev;
        Node<K, V> next;

        // 无参构造
        public Node() {
            this.prev = this.next = null;
        }

        // 有参构造
        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = this.prev = null;
        }
    }

    class DoubleLinkedList<K, V> {
        Node<K, V> head;
        Node<K, V> tail;

        // 无参构造
        public DoubleLinkedList() {
            head = new Node<>();
            tail = new Node<>();
            head.next = tail;
            tail.prev = head;
        }

        // 添加到头结点
        public void addHead(Node<K, V> node){
            node.next = head.next;
            node.prev = head;
            head.next.prev = node;
            head.next = node;
        }

        public void remove(Node<K, V> node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
            node.prev = null;
            node.next = null;
        }

        public Node getLast() {
            return tail.prev;
        }
    }

    private int cacheSize;

    Map<Integer, Node<Integer, Integer>> map;
    DoubleLinkedList<Integer, Integer> doubleLinkedList;

    public LRUCacheByMyself(int capacity) {
        this.cacheSize = capacity;
        map = new HashMap<>();
        doubleLinkedList = new DoubleLinkedList<>();
    }

    // 获取key
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node<Integer, Integer> node = map.get(key);
        // 先删除
        doubleLinkedList.remove(node);
        // 插入到头结点
        doubleLinkedList.addHead(node);
        return node.value;
    }

    // 放入结点
    public void put(int key, int value) {
        if (map.containsKey(key)) {
            Node<Integer, Integer> node = map.get(key);
            node.value = value;
            map.put(key, node);
            doubleLinkedList.remove(node);
            doubleLinkedList.addHead(node);
        } else {
            if (map.size() == cacheSize){
                Node lastNode = doubleLinkedList.getLast();
                map.remove(lastNode.key);
                doubleLinkedList.remove(lastNode);
            }
            // 新增
            Node<Integer, Integer> newNode = new Node<>(key, value);
            map.put(key, newNode);
            doubleLinkedList.addHead(newNode);
        }
    }

}
