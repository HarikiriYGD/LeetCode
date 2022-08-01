package LeetCode.BinaryTree;

/**
 * @Auther: Lil_boat
 * @Date: 16:45 2021/12/13
 * @Description:
 * 给定一个 完美二叉树 ，其所有叶子节点都在同一层，每个父节点都有两个子节点。二叉树定义如下：
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * 填充它的每个 next 指针，让这个指针指向其下一个右侧节点。如果找不到下一个右侧节点，则将 next 指针设置为 NULL。
 *
 * 初始状态下，所有 next 指针都被设置为 NULL。
 *
 */
public class Connect_PerfectBinaryTree {

    /**
     * 递归的方式
     *
     * @param root
     * @return
     */
    public Node connect(Node root) {
        dfs(root, null);
        return root;
    }

    public void dfs(Node curr, Node next) {
        if (curr == null) return;
        curr.next = next;
        dfs(curr.left, curr.right);
        dfs(curr.right, curr.next == null ? null : curr.next.left);
    }

    public Node connect_BFS(Node root) {
        if (root == null) return root;
        //cur我们可以把它看做是每一层的链表
        Node cur = root;
        while (cur != null) {
            // 遍历当前层的时候，为了方便操作在下一
            // 层前面添加一个哑结点（注意这里是访问
            // 当前层的节点，然后把下一层的节点串起来）
            Node dummy = new Node(0);
            Node pre = dummy;

            // 然后开始遍历当前层的链表
            // 因为是完美二叉树，如果有左子节点就一定有右子节点
            while (cur != null && cur.left != null) {
                // 让pre节点的next指向当前节点的左子节点，也就是把它串起来
                pre.next = cur.left;

                // 然后再更新pre
                pre = pre.next;

                // pre节点的next指向当前节点的右子节点
                pre.next = cur.right;
                pre = pre.next;
                // 继续访问这一行的下一个节点
                cur = cur.next;
            }
            // 把下一层串联成一个链表之后，让他赋值给cur，
            // 后续继续循环，直到cur为空为止
            cur = dummy.next;
        }
        return root;
    }

}
