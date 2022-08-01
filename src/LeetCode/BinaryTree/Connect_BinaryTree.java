package LeetCode.BinaryTree;

/**
 * @Auther: Lil_boat
 * @Date: 16:46 2021/12/13
 * @Description: 给定一个二叉树
 * <p>
 * struct Node {
 * int val;
 * Node *left;
 * Node *right;
 * Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 * <p>
 * 初始状态下，所有 next 指针都被设置为 NULL。
 */
public class Connect_BinaryTree {

    public Node connect(Node root) {
        if (root == null) return root;
        // cur我们可以把它看做是每一层的链表
        Node cur = root;
        while (cur != null) {
            // 遍历当前层的时候，为了方便操作在下一
            // 层前面添加一个哑结点（注意这里是访问
            // 当前层的节点，然后把下一层的节点串起来）

            Node dummy = new Node(0);
            Node pre = dummy;
            // 开始遍历树
            while (cur != null) {
                // 如果当前节点的左子节点不为空，就让pre节点
                // 的next指向他，也就是把它串起来
                if (cur.left != null) {
                    pre.next = cur.left;
                    pre = pre.next;
                }
                // 如果当前节点的右子树不为空，就让pre节点
                // 的next指向他，串起来
                if (cur.right != null) {
                    pre.next = cur.right;
                    pre = pre.next;
                }
                cur = cur.next;
            }
            // 把下一层串联成一个链表之后，让他赋值给cur，
            // 后续继续循环，直到cur为空为止
            cur = dummy.next;
        }
        return root;
    }


}
