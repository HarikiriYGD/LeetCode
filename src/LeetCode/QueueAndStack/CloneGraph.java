package LeetCode.QueueAndStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CloneGraph {

    // 定义邻接表结构体
    static class Node {
        public int val; // 节点值
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    /**
     * @param node
     * @return
     */
    public static Node cloneGraph(Node node) {
        return clone(node, new HashMap<>());
    }

    /**
     * @param node
     * @param visitedMap
     * @return
     */
    public static Node clone(Node node, HashMap<Integer, Node> visitedMap) {
        // 判断边界条件
        if (node == null) return null;
        // 如果当前节点已经创建，直接返回
        if (visitedMap.containsKey(node.val)) {
            return visitedMap.get(node.val);
        }

        // 否则创建节点
        Node newNode = new Node(node.val, new ArrayList<>());
        // 把创建好的节点存放在map中
        visitedMap.put(newNode.val, newNode);
        //创建当前节点的邻居节点
        for (Node neighbor : node.neighbors)
            newNode.neighbors.add(clone(neighbor, visitedMap));
        return newNode;

    }

    public static void main(String[] args) {

    }
}
