package LeetCode.AimToOffer;

/**
 * @Auther: Lil_boat
 * @Date: 15:41 2022/1/6
 * @Description: 输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)
 * B是A的子结构， 即 A中有出现和B相同的结构和节点值。
 * 例如:
 * 给定的树 A:
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 * 给定的树 B：
 *    4 
 *   /
 *  1
 * 返回 true，因为 B 与 A 的一个子树拥有相同的结构和节点值。
 */
public class IsSubStructure_26 {
    /*
    解题思路：
        若树 B 是树 A 的子结构，则子结构的根节点可能为树 A 的任意一个节点。因此，判断树 B 是否是树 A 的子结构，需完成以下两步工作：
            先序遍历树 A 中的每个节点 n_A；（对应函数 isSubStructure(A, B)）
            判断树 A 中 以 n_A为根节点的子树 是否包含树 B 。（对应函数 preOrder(A, B)）
     算法流程：
        本文名词规定：树 A 的根节点记作 节点 A ，树 B 的根节点称为 节点 B 。
        preOrder(A, B) 函数：
            一、终止条件：
                当节点 B 为空：说明树 B 已匹配完成（越过叶子节点），因此返回 true ；
                当节点 A 为空：说明已经越过树 A 的叶节点，即匹配失败，返回 false ；
                当节点 A 和 B 的值不同：说明匹配失败，返回 false；
            二、返回值：
                判断 A 和 B 的 左子节点 是否相等，即 preOrder(A.left, B.left) ；
                判断 A 和 B 的 右子节点 是否相等，即 preOrder(A.right, B.right) ；
        isSubStructure(A, B) 函数：
            一、特例处理： 当 树 A 为空 或 树 B 为空 时，直接返回 false ；
            二、返回值： 若树 B 是树 A 的子结构，则必满足以下三种情况之一，因此用或 || 连接；
                1. 以 节点 A 为根节点的子树 包含树 B ，对应 recur(A, B)；
                2. 树 B 是 树 A 左子树 的子结构，对应 isSubStructure(A.left, B)；
                3. 树 B 是 树 A 右子树 的子结构，对应 isSubStructure(A.right, B)；
                以上 2. 3. 实质上是在对树 A 做 先序遍历 。
     */
    public static boolean isSubStructure(TreeNode A, TreeNode B) {
        /*// 如果有空树则返回false
        if (A == null || B == null) return false;
        boolean res = false;
        // 判断当前A树和B树是否是子结构
        res = res || preOrder(A, B);
        // 前序遍历,并判断当前位置是否找到子结构。（且找到子结构就立即返回 -> 等价于下面的写法)
        res = res || isSubStructure(A.left, B) || isSubStructure(A.right, B);
        return res;*/
        // 简化代码
        return (A != null && B != null) || (preOrder(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    public static boolean preOrder(TreeNode A, TreeNode B) {
        // 根为A和根为B的树的相同方式的遍历是否一致的问题。
        // 如果B树全部访问完 则返回true
        // 注意：约定空树不是任意一个树的子结构，而这里的含义是B树递归遍历完成。
        if (B == null) return true;
        // 如果A树全部访问完成，而B树还没有访问完 则返回false
        if (A == null && B != null) return false;
        // 只要两个的值不相等 直接返回
        if (A.val != B.val) return false;
        // 下一步递归。 注意：完全匹配，故使用&&
        return preOrder(A.left, B.left) && preOrder(A.right, B.right);
    }
}
