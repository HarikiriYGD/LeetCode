package LeetCode.Practise_Everyday.Date_2022_05;
/**
 * @Auther: Lil_boat
 * @Date: 12:38 2022/5/25
 * @Tile: 后继者
 * @Description: 设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
 *
 * 如果指定节点没有对应的“下一个”节点，则返回null。
 */
public class D16_InorderSuccessor_04_06 {

    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null) return null;
        if(root.val > p.val){
            TreeNode temp = inorderSuccessor(root.left, p);
            return temp == null ? root : temp;
        }
        return inorderSuccessor(root.right, p);
    }

}
