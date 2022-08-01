package LeetCode.BinaryTree;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * @Auther: Lil_boat
 * @Date: 15:31 2021/12/13
 * @Description:
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 * 注意:
 * 你可以假设树中没有重复的元素。
 */
public class BuildTreeByInorderAndPostorder {
    int[] postorder;
    int[] inorder;
    int post_index;
    Map<Integer, Integer> map = new HashMap<>();

    /**
     * 递归的方式
     * @param inorder 中序遍历
     * @param postorder 后序遍历
     * @return
     */
    public TreeNode buildTreeByInorderAndPostorder_Recursion(int[] inorder, int[] postorder) {
        this.inorder = inorder;
        this.postorder = postorder;
        post_index = postorder.length - 1;
        int index = 0;
        for (int val : inorder) {
            map.put(val, index++);
        }
        return helper(0, inorder.length - 1);
    }

    public TreeNode helper(int in_left, int in_right) {
        // 边界判断条件，如果左下标大于右下标
        if (in_left > in_right) return null;

        // 选择后序遍历的最后一个元素作为根节点
        int root_val = postorder[post_index];
        TreeNode root = new TreeNode(root_val);

        // 根据根节点所在的位置，分为左右子树
        int index = map.get(root_val);

        // 下标减一
        post_index--;
        // 构造右子树
        root.right = helper(index + 1, in_right);
        // 构造左子树
        root.left = helper(in_left, index - 1);
        return root;

    }

    public TreeNode buildTreeByInorderAndPostorder_Iteration(int[] inorder, int[] postorder){
        if (postorder == null || postorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        int inorderIndex = inorder.length - 1;
        for (int i = postorder.length - 2; i >= 0; i--) {
            int postorderVal = postorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.right = new TreeNode(postorderVal);
                stack.push(node.right);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex--;
                }
                node.left = new TreeNode(postorderVal);
                stack.push(node.left);
            }
        }
        return root;
    }

}

