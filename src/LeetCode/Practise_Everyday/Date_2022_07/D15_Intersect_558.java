package LeetCode.Practise_Everyday.Date_2022_07;

/**
 * @Author: Lil_boat
 * @Date: 2022/7/15 15:44
 * @Title: 四叉树交集
 * @Description: 二进制矩阵中的所有元素不是 0 就是 1 。
 * 给你两个四叉树，quadTree1 和 quadTree2。其中 quadTree1 表示一个 n * n 二进制矩阵，而 quadTree2 表示另一个 n * n 二进制矩阵。
 * 请你返回一个表示 n * n 二进制矩阵的四叉树，它是 quadTree1 和 quadTree2 所表示的两个二进制矩阵进行 按位逻辑或运算 的结果。
 * 注意，当 isLeaf 为 False 时，你可以把 True 或者 False 赋值给节点，两种值都会被判题机制 接受 。
 * <p>
 * 四叉树数据结构中，每个内部节点只有四个子节点。此外，每个节点都有两个属性：
 * val：储存叶子结点所代表的区域的值。1 对应 True，0 对应 False；
 * isLeaf: 当这个节点是一个叶子结点时为 True，如果它有 4 个子节点则为 False 。
 * <p>
 * 我们可以按以下步骤为二维区域构建四叉树：
 * 如果当前网格的值相同（即，全为 0 或者全为 1），将 isLeaf 设为 True ，将 val 设为网格相应的值，并将四个子节点都设为 Null 然后停止。
 * 如果当前网格的值不同，将 isLeaf 设为 False， 将 val 设为任意值，然后如下图所示，将当前网格划分为四个子网格。
 * 使用适当的子网格递归每个子节点。
 * <p>
 * 四叉树格式：
 * <p>
 * 输出为使用层序遍历后四叉树的序列化形式，其中 null 表示路径终止符，其下面不存在节点。
 * 它与二叉树的序列化非常相似。唯一的区别是节点以列表形式表示 [isLeaf, val] 。
 * 如果 isLeaf 或者 val 的值为 True ，则表示它在列表 [isLeaf, val] 中的值为 1 ；
 * 如果 isLeaf 或者 val 的值为 False ，则表示值为 0 。
 * <p>
 * 链接：https://leetcode.cn/problems/logical-or-of-two-binary-grids-represented-as-quad-trees
 */
public class D15_Intersect_558 {
    /**
     * 四叉树的定义
     */
    class Node {
        public boolean val;
        public boolean isLeaf;
        public Node topLeft;
        public Node topRight;
        public Node bottomLeft;
        public Node bottomRight;

        public Node() {
        }

        public Node(boolean _val, boolean _isLeaf, Node _topLeft, Node _topRight, Node _bottomLeft, Node _bottomRight) {
            val = _val;
            isLeaf = _isLeaf;
            topLeft = _topLeft;
            topRight = _topRight;
            bottomLeft = _bottomLeft;
            bottomRight = _bottomRight;
        }
    }

    public Node intersect(Node t1, Node t2) {
        if (t1.isLeaf && t2.isLeaf) {
            if (t1.val) return t1;
            else if (t2.val) return t2;
            else return t1;
        }
        Node ans = new Node();
        ans.topLeft = intersect(t1.isLeaf ? t1 : t1.topLeft, t2.isLeaf ? t2 : t2.topLeft);
        ans.topRight = intersect(t1.isLeaf ? t1 : t1.topRight, t2.isLeaf ? t2 : t2.topRight);
        ans.bottomLeft = intersect(t1.isLeaf ? t1 : t1.bottomLeft, t2.isLeaf ? t2 : t2.bottomLeft);
        ans.bottomRight = intersect(t1.isLeaf ? t1 : t1.bottomRight, t2.isLeaf ? t2 : t2.bottomRight);
        // 判断是否是叶子节点
        boolean a = ans.topLeft.isLeaf && ans.topRight.isLeaf && ans.bottomLeft.isLeaf && ans.bottomRight.isLeaf;
        boolean b = ans.topLeft.val && ans.topRight.val && ans.bottomLeft.val && ans.bottomRight.val;
        boolean c = ans.topLeft.val || ans.topRight.val || ans.bottomLeft.val || ans.bottomRight.val;
        ans.isLeaf = a && (b || !c);
        ans.val = ans.topLeft.val;
        if (ans.isLeaf) ans.topLeft = ans.topRight = ans.bottomLeft = ans.bottomRight = null;
        return ans;
    }
}
