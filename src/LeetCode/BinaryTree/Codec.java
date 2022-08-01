package LeetCode.BinaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @Auther: Lil_boat
 * @Date: 17:17 2021/12/14
 * @Description: 序列化是将一个数据结构或者对象转换为连续的比特位的操作，
 * 进而可以将转换后的数据存储在一个文件或者内存中，同时也可以通过网络传输到另一个计算机环境，采取相反方式重构得到原数据。
 * 请设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，
 * 你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。
 * <p>
 * 提示: 输入输出格式与 LeetCode 目前使用的方式一致，
 * 详情请参阅 LeetCode 序列化二叉树的格式。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。
 */
public class Codec {

    // Encodes a tree to a single string.
    public static String serialize(TreeNode root) {
        // 边界判断条件，如果是空就返回一个#
        if (root == null) return "#";
        StringBuilder sb = new StringBuilder();
        // 创建一个队列，存储入队的结点
        Queue<TreeNode> queue = new LinkedList<>();
        // 根结点入队
        queue.add(root);
        // 队里元素不为空时
        while (!queue.isEmpty()) {
            // 弹出队首元素
            TreeNode curr = queue.poll();
            // 是否为空
            if (curr == null) {
                sb.append("#,");
                continue;
            }
            sb.append(curr.val + ",");
            // 左结点入队
            queue.add(curr.left);
            // 右节点入队
            queue.add(curr.right);
        }
        return sb.toString();
    }

    // Decodes your encoded data to tree.
    public static TreeNode deserialize(String data) {
        if (data == "#") return null;
        Queue<TreeNode> queue = new LinkedList<>();
        // 以","将字符串分开
        String[] values = data.split(",");
        // 将第一个元素作为根结点赋值
        TreeNode root = new TreeNode(Integer.parseInt(values[0]));
        queue.add(root);
        for (int i = 1; i < values.length; i++) {
            // 队列中的元素出队
            TreeNode parent = queue.poll();
            // 因为在BFS中左右子节点是成对出现的，所以这里挨着的两个值一个是
            // 左子节点的值一个是右子节点的值，当前值如果是"#"就表示这个子节点
            // 是空的，如果不是"#"就表示不是空的

            if (!"#".equals(values[i])) {
                TreeNode left = new TreeNode(Integer.parseInt(values[i]));
                parent.left = left;
                queue.add(left);
            }
            // 上面如果不为空就是左子节点的值，这里是右子节点的值，注意这里有个i++，
            if (!"#".equals(values[++i])) {
                TreeNode right = new TreeNode(Integer.parseInt(values[i]));
                parent.right = right;
                queue.add(right);
            }
        }
        return root;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        TreeNode rl = new TreeNode(4);
        TreeNode rr = new TreeNode(5);
        root.left = left;
        root.right = right;
        left.left = null;
        left.right = null;
        right.right = rr;
        right.left = rl;
        String res = serialize(root);
        System.out.println(res);
        deserialize(res);

    }

    /*public void preOrder(TreeNode root, StringBuilder sb) {
        if (root == null) sb.append("#");
        sb.append(root.val + "");
        preOrder(root.left, sb);
        preOrder(root.right, sb);
        return;
    }

    public void inOrder(TreeNode root, StringBuilder sb) {
        if (root == null) sb.append("#");
        inOrder(root.left, sb);
        sb.append(root.val + "");
        inOrder(root.right, sb);
        return;
    }*/

}
