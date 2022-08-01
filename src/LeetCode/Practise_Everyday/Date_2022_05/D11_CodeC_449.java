package LeetCode.Practise_Everyday.Date_2022_05;

/**
 * @Auther: Lil_boat
 * @Date: 9:48 2022/5/11
 * @Tile: 序列化和反序列化二叉搜索树
 * @Description: 序列化是将数据结构或对象转换为一系列位的过程，以便它可以存储在文件或内存缓冲区中，
 * 或通过网络连接链路传输，以便稍后在同一个或另一个计算机环境中重建。
 * <p>
 * 设计一个算法来序列化和反序列化 二叉搜索树 。 对序列化/反序列化算法的工作方式没有限制。
 * 您只需确保二叉搜索树可以序列化为字符串，并且可以将该字符串反序列化为最初的二叉搜索树。
 * 编码的字符串应尽可能紧凑。
 * <p>
 * 链接：https://leetcode.cn/problems/serialize-and-deserialize-bst
 */
public class D11_CodeC_449 {

    public String serialize(TreeNode root) {
        if (root == null) return "";
        StringBuilder sb = new StringBuilder();
        inorder(root, sb);
        return sb.toString();
    }

    /**
     * 前序遍历得到String
     * @param root
     * @param sb
     */
    public void inorder(TreeNode root, StringBuilder sb) {
        if (root == null) return;
        sb.append("#" + root.val);
        inorder(root.left, sb);
        inorder(root.right, sb);
    }

    public TreeNode deserialize(String data) {
        if (data.length() == 0 || data == null) return null;
        // 以#作为分隔符得到每一个节点的值
        String[] s = data.split("#");
        int[] node = new int[s.length - 1];
        for (int i = 0; i < node.length; i++) {
            node[i] = Integer.parseInt(s[i + 1]);
        }
        return reForBST(node, 0, node.length - 1);
    }

    public TreeNode reForBST(int[] node, int l, int r) {
        TreeNode t = new TreeNode(node[l]);
        if (l < r) {
            if (node[l + 1] > node[l]) t.right = reForBST(node, l + 1, r);
            else if (node[r] < node[l]) t.left = reForBST(node, l + 1, r);
            else {
                int left = l + 1, right = r;
                while (left < right) {
                    int mid = (left + right) / 2;
                    if (node[mid] < node[l]) left = mid + 1;
                    else right = mid;
                    if (left == right - 1) {
                        if (node[left] > node[l]) right = left;
                        break;
                    }
                }
                t.left = reForBST(node, l + 1, right - 1);
                t.right = reForBST(node, right, r);
            }
        }
        return t;
    }

}
