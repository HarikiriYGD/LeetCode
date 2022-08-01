package LeetCode.Practise_Everyday.Date_2022_04;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @Auther: Lil_boat
 * @Date: 10:05 2022/4/8
 * @Description: 给定一个 N 叉树，返回其节点值的层序遍历。（即从左到右，逐层遍历）。
 * <p>
 * 树的序列化输入是用层序遍历，每组子节点都由 null 值分隔（参见示例）。
 */
public class LevelOrder_429 {

    public List<List<Integer>> levelOrder(Node root) {
        // 如果是空树 直接返回空结果
        if (root == null) return new ArrayList<>();
        // 构建结果集
        List<List<Integer>> ans = new ArrayList<>();
        // 建立双端队列 存放每一层的节点
        Queue<Node> queue = new LinkedList<>();
        // 根节点入队
        queue.offer(root);
        while (!queue.isEmpty()) {
            // 每一层的结果集
            List<Integer> subList = new ArrayList<>();
            // 每一层的数目
            int size = queue.size();
            // 添加进每一层的结果集
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                // 如果有子节点 加入队列
                if (cur.children != null) {
                    List<Node> childrens = cur.children;
                    for (Node children : childrens) {
                        queue.offer(children);
                    }
                }
                subList.add(cur.val);
            }

            ans.add(subList);
        }
        return ans;
    }

}
