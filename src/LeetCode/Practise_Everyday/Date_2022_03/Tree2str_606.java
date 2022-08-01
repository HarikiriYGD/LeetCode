package LeetCode.Practise_Everyday.Date_2022_03;


/**
 * @Auther: Lil_boat
 * @Date: 10:09 2022/3/19
 * @Description: 你需要采用前序遍历的方式，将一个二叉树转换成一个由括号和整数组成的字符串。
 * <p>
 * 空节点则用一对空括号 "()" 表示。而且你需要省略所有不影响字符串与原始二叉树之间的一对一映射关系的空括号对。
 */
public class Tree2str_606 {

    /*
        生成字符串的规则其实就是在「前序遍历」输出节点值的同时，在每颗子树的左右添加一对 ()（根节点除外），同时需要忽略掉一些不必要的 () 。
        所谓的不必要就是指当以某个节点 x 为根时，其只「有左子树」而「没有右子树」时，右子树的 () 可被忽略，
        或者「左右子树都没有」时，两者的 () 可被忽略。
        或者反过来说，如果对于每个非空节点才添加 () 的话，
        那么当「有右子树」同时「没有左子树」时，左子树的 () 不能被忽略，需要额外添加，
        从而确保生成出来的字符串能够与「有左子树」同时「没有右子树」的情况区分开来，而不会产生二义性。

     */
    public  String tree2str(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        pre(root, sb);
        return sb.toString();
    }

    public  void pre(TreeNode root, StringBuilder sb) {
        if (root != null) {
            sb.append(root.val);
            if (root.left != null || root.right != null) {
                sb.append('(');
                pre(root.left, sb);
                if (root != null) {
                    sb.append('(');
                    pre(root.right, sb);
                    sb.append(')');
                }
            }
        }
    }


}
