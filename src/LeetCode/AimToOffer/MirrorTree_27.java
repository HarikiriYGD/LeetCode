package LeetCode.AimToOffer;

import java.util.Stack;

/**
 * @Auther: Lil_boat
 * @Date: 16:13 2022/1/6
 * @Description: 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 * <p>
 * 例如输入：
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 * 镜像输出：
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 */
public class MirrorTree_27 {
    /**
     * 递归的方式实现
     *
     * @param root
     * @return
     */
    public TreeNode mirrorTree_Recursion(TreeNode root) {
        // 如果是空树 直接返回root
        if (root == null) return root;
        // 交换左右子树
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = right;
        root.right = left;
        // root的左子树和右子树再互换
        mirrorTree_Recursion(root.left);
        mirrorTree_Recursion(root.right);
        return root;
    }

    /*
      方法二：辅助栈（或队列）
            利用栈（或队列）遍历树的所有节点 node ，并交换每个 node 的左 / 右子节点。
      算法流程：
            1. 特例处理： 当 root 为空时，直接返回 nul ；
            2. 初始化： 栈（或队列），本文用栈，并加入根节点 root 。
            3. 循环交换： 当栈 stack 为空时跳出；
                出栈： 记为 node ；
                添加子节点： 将 node左和右子节点入栈；
                交换： 交换 node 的左 / 右子节点。
            4. 返回值： 返回根节点 root 。
     */
    public TreeNode mirrorTree_Stack(TreeNode root) {
        if (root == null) return root;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            if (cur.left != null) stack.add(cur.left);
            if (cur.right != null) stack.add(cur.right);
            TreeNode tmp = cur.left;
            cur.left = cur.right;
            cur.right = tmp;
        }
        return root;
    }
}
