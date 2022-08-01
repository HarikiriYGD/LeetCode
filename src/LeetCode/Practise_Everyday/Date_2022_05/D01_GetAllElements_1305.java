package LeetCode.Practise_Everyday.Date_2022_05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Auther: Lil_boat
 * @Date: 9:57 2022/5/1
 * @Tile: 两棵二叉搜索树中的所有元素
 * @Description: 给你 root1 和 root2 这两棵二叉搜索树。请你返回一个列表，其中包含 两棵树 中的所有整数并按 升序 排序。.
 */

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class D01_GetAllElements_1305 {


    List<Integer> ans = new ArrayList<>();

    public List<Integer> getAllElements(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return new ArrayList<>();
        // 两棵树的都遍历
        inorder(root1);
        inorder(root2);
        // 暴力排序
        Collections.sort(ans);
        return ans;
    }

    /**
     * 中序遍历
     * @param root
     */
    public void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        ans.add(root.val);
        inorder(root.right);
    }

}
