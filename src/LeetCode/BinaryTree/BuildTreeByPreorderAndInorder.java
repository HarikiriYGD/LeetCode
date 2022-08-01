package LeetCode.BinaryTree;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
/**
 * @Auther: Lil_boat
 * @Date: 15:54 2021/12/13
 * @Description:
 * 给定一棵树的前序遍历 preorder 与中序遍历  inorder。请构造二叉树并返回其根节点。
 */
public class BuildTreeByPreorderAndInorder {

    int pre_index;
    int[] preorder;
    int[] inorder;
    Map<Integer, Integer> map = new HashMap<>();



    /**
     * 递归的方式
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTreeByPreorderAndInorder_Recursion(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        this.inorder = inorder;
        // 边界判断条件
        if (preorder.length == 0 || preorder == null) return null;
        // 初始化前序遍历的下标
        pre_index = 0;
        int index = 0;
        // 将中序遍历的结果存入HashMap中
        for (int val : inorder) {
            map.put(val, index++);
        }
        return helper(0, inorder.length - 1);
    }

    public TreeNode helper(int in_left, int in_right) {
        // 如果左下标大于右下标表示已经搜索结束
        if (in_left > in_right) return null;

        // 选择前序遍历的第一个元素作为根节点
        int root_val = preorder[pre_index];
        TreeNode root = new TreeNode(root_val);

        // 根据前序遍历的第一个结点作为根结点，将左右子树分开
        // 通过key拿到value
        int index = map.get(root_val);

        // 下标右移
        pre_index++;
        // 构造左子树
        root.left = helper(in_left, index - 1);
        // 构造右子树
        root.right = helper(index + 1, in_right);
        return root;

    }

    /**
     * 迭代的方式
     * @param preorder
     * @param inorder
     * @return
     */
    public TreeNode buildTreeByPreorderAndInorder_Iteration(int[] preorder, int[] inorder){
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
            }
        }
        return root;

    }

}
