package LeetCode.AimToOffer;

/**
 * @Auther: Lil_boat
 * @Date: 17:30 2022/1/10
 * @Description: 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。
 */
public class TreeToDoublyLis_36 {

    /*
    解题思路：
        本文解法基于性质：二叉搜索树的中序遍历为 递增序列 。
        将二叉搜索树 转换成一个 “排序的循环双向链表” ，其中包含三个要素：
            1. 排序链表： 节点应从小到大排序，因此应使用 中序遍历 “从小到大”访问树的节点。
            2. 双向链表： 在构建相邻节点的引用关系时，设前驱节点 pre 和当前节点 cur ，不仅应构建 pre.right = cur ，也应构建 cur.left = pre 。
            3. 循环链表： 设链表头节点 head 和尾节点 tail ，则应构建 head.left = tail 和 tail.right = head
        根据以上分析，考虑使用中序遍历访问树的各节点 cur ；
        并在访问每个节点时构建 cur 和前驱节点 pre 的引用指向；
        中序遍历完成后，最后构建头节点和尾节点的引用指向即可。
    算法流程：
        dfs(cur): 递归法中序遍历；
            1. 终止条件： 当节点 cur 为空，代表越过叶节点，直接返回；
            2. 递归左子树，即 dfs(cur.left) ；
            3. 构建链表：
                1. 当 pre 为空时： 代表正在访问链表头节点，记为 head ；
                2. 当 pre 不为空时： 修改双向节点引用，即 pre.right = cur ， cur.left = pre ；
                3. 保存 cur ： 更新 pre = cur ，即节点 cur 是后继节点的 pre ；
            4. 递归右子树，即 dfs(cur.right) ；
      treeToDoublyList(root)：
        1. 特例处理： 若节点 root 为空，则直接返回；
        2. 初始化： 空节点 pre ；
        3. 转化为双向链表： 调用 dfs(root) ；
        4. 构建循环链表： 中序遍历完成后，head 指向头节点， pre 指向尾节点，因此修改 head 和 pre 的双向节点引用即可；
        5. 返回值： 返回链表的头节点 head 即可；
     */
    Node pre = null;
    Node head = null;

    public Node treeToDoublyList(Node root) {
        if (root == null) return root;
        dfs(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    // 类似于中序遍历
    public void dfs(Node root) {
        // 判断当前节点是否为空
        if (root == null) return;
        dfs(root.left);
        if (head==null) head=root;
        else pre.right=root;

        root.left=pre;
        pre=root;
        dfs(root.right);
    }
}
