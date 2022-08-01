package LeetCode.Practise_Everyday.Date_2022_06;


/**
 * @Auther: Lil_boat
 * @Date: 9:47 2022/6/2
 * @Tile: 删除二叉搜索树中的节点
 * @Description: 给定一个二叉搜索树的根节点 root 和一个值 key，删除二叉搜索树中的 key 对应的节点，
 * 并保证二叉搜索树的性质不变。返回二叉搜索树（有可能被更新）的根节点的引用。
 * <p>
 * 一般来说，删除节点可分为两个步骤：
 * 首先找到需要删除的节点；
 * 如果找到了，删除它。
 * <p>
 * 链接：https://leetcode.cn/problems/delete-node-in-a-bst
 */
public class D02_DeleteNode_450 {

    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null) return null;
        // 待删除的key在左子树
        if (key < root.val) {
            root.left = deleteNode(root.left, key);
            return root;
            // 待删除的key在右子树
        } else if (key > root.val) {
            root.right = deleteNode(root.right, key);
            return root;
            // 待删除的key是root节点
        } else {
            // 如果待删除的key的左子树为空，则返回右子树作为根节点
            if (root.left == null) return root.right;
                // 如果待删除的key的右子树为空，则返回左子树作为根节点
            else if (root.right == null) return root.left;
                // 如果左右子树都存在，返回后继节点作为根节点
            else {
                // 寻找key的后继节点
                TreeNode successor = min(root.right);
                successor.right = deleteMin(root.right);
                successor.left = root.left;
                return successor;
            }
        }

    }

    /**
     * 寻找key的后继节点
     *
     * @param node
     * @return
     */
    private TreeNode min(TreeNode node) {
        if (node.left == null) return node;
        return min(node.left);
    }


    private TreeNode deleteMin(TreeNode node) {
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        return node;
    }

}
