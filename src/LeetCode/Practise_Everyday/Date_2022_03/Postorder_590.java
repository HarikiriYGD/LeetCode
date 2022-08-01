package LeetCode.Practise_Everyday.Date_2022_03;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: Lil_boat
 * @Date: 14:19 2022/3/12
 * @Description: 给定一个 n 叉树的根节点 root ，返回 其节点值的 后序遍历 。
 *
 * n 叉树 在输入中按层序遍历进行序列化表示，每组子节点由空值 null 分隔（请参见示例）。
 */
public class Postorder_590 {

    List<Integer> ans = new ArrayList<>();
    public List<Integer> postorder(Node root) {
        if(root == null) return new ArrayList<>();
        post(root);
        return ans;
    }

    public void post(Node root){
        if(root== null) return;
        List<Node> childs = root.children;
        for(Node child : childs){
            post(child);
        }
        ans.add(root.val);
    }
}
