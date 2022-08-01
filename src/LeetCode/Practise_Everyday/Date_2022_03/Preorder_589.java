package LeetCode.Practise_Everyday.Date_2022_03;

import java.util.*;

/**
 * @Auther: Lil_boat
 * @Date: 9:40 2022/3/10
 * @Description: 给定一个 n 叉树的根节点  root ，返回 其节点值的 前序遍历 。
 * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 */
public class Preorder_589 {
    /*
        递归的方式
     */
    List<Integer> ans = new ArrayList<>();

    public List<Integer> preorder(Node root) {
        if (root == null) return new ArrayList<>();
        ans.add(root.val);
        List<Node> children = root.children;
        for (Node child : children) {
            preorder(child);
        }
        return ans;
    }

    /*
        迭代的方式
     */
    public List<Integer> preorder_iterator(Node root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return new ArrayList<>();
        Stack<Node> stack =new Stack<>();
        stack.add(root);

        while (!stack.isEmpty()){
            Node cur = stack.pop();
            res.add(cur.val);
            List<Node> children = cur.children;
            for (int i = children.size() - 1; i >= 0; i--){
                stack.add(children.get(i));
            }
        }
        return res;
    }

}
