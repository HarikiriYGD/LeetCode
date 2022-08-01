package LeetCode.AimToOffer;

import java.util.Stack;

/**
 * @Auther: Lil_boat
 * @Date: 14:17 2022/1/19
 * @Description: 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。
 * 假设输入的数组的任意两个数字都互不相同。
 */
public class VerifyPostorder_33 {

    /*
        方法一：
            递归分治的方式
                后序遍历定义： [ 左子树 | 右子树 | 根节点 ] ，即遍历顺序为 “左、右、根” 。
                二叉搜索树定义： 左子树中所有节点的值 << 根节点的值；右子树中所有节点的值 >> 根节点的值；其左、右子树也分别为二叉搜索树。
            根据二叉搜索树的定义，可以通过递归，判断所有子树的 正确性 （即其后序遍历是否满足二叉搜索树的定义） ，
            若所有子树都正确，则此序列为二叉搜索树的后序遍历。

            递归解析：
                1. 终止条件： 当 i ≥ j ，说明此子树节点数量 1≤1 ，无需判别正确性，因此直接返回 true ；
                2. 递推工作：
                    1. 划分左右子树： 遍历后序遍历的 [i, j] 区间元素，寻找 第一个大于根节点 的节点，索引记为 m 。
                       此时，可划分出左子树区间 [i,m−1] 、右子树区间 [m, j - 1] 、根节点索引 j 。
                    2. 判断是否为二叉搜索树：
                        * 左子树区间 [i, m - 1] 内的所有节点都应 < postorder[j] 。而第 1.划分左右子树 步骤已经保证左子树区间的正确性，
                          因此只需要判断右子树区间即可。
                        * 右子树区间 [m, j-1] 内的所有节点都应 > postorder[j] 。实现方式为遍历，当遇到 ≤postorder[j] 的节点则跳出；
                        * 则可通过 p = j 判断是否为二叉搜索树。
                    3. 返回值： 所有子树都需正确才可判定正确，因此使用 与逻辑符 && 连接。
                        * p = j ： 判断 此树 是否正确。
                        * recur(i, m - 1) ： 判断 此树的左子树 是否正确。
                        * recur(m, j - 1) ： 判断 此树的右子树 是否正确。

     */
    public static boolean verifyPostorder_Recursion(int[] postorder) {
        return verify(postorder, 0, postorder.length - 1);
    }

    public static boolean verify(int[] postorder, int start, int end) {
        if (start >= end) return true;
        int k = start;
        while (postorder[k] < postorder[end]) k++;
        int mid = k;
        while (postorder[k] > postorder[end]) k++;
        return k == end && verify(postorder, start, mid - 1) && verify(postorder, mid, end - 1);
    }

    /*
    方法二：辅助单调栈
        后序遍历倒序： [ 根节点 | 右子树 | 左子树 ] 。类似 先序遍历的镜像 ，即先序遍历为 “根、左、右” 的顺序，而后序遍历的倒序为 “根、右、左” 顺序。
        设后序遍历倒序列表为 [r_{n}, r_{n-1},...,r_1]，遍历此列表，设索引为 ii ，若为 二叉搜索树 ，则有：
            * 当节点值 r_i > r_{i+1}时： 节点 r_i一定是节点 r_{i+1}的右子节点。
            * 当节点值 r_i < r_{i+1}时： 节点 r_i一定是某节点 root 的左子节点，
              且 root 为节点 r_{i+1}, r_{i+2},..., r_{n}中值大于且最接近 r_i的节点(∵ root 直接连接 左子节点 r_i)。
        当遍历时遇到递减节点 r_i < r_{i+1}，若为二叉搜索树，则对于后序遍历中节点 r_i右边的任意节点 r_x ∈ [r_{i-1}, r_{i-2}, ..., r_1] ，
        必有节点值 r_x < root 。节点 r_x只可能为以下两种情况：
            ① r_x为 r_i的左、右子树的各节点；
            ② r_x为 root 的父节点或更高层父节点的左子树的各节点。在二叉搜索树中，以上节点都应小于 root 。
        遍历 “后序遍历的倒序” 会多次遇到递减节点 r_i，若所有的递减节点 r_i对应的父节点 root 都满足以上条件，
        则可判定为二叉搜索树。根据以上特点，考虑借助 单调栈 实现：
            1. 借助一个单调栈 stack 存储值递增的节点；
            2. 每当遇到值递减的节点 r_i，则通过出栈来更新节点 r_i的父节点 root ；
            3. 每轮判断 r_i和 root 的值关系：
                * 若 r_i > root 则说明不满足二叉搜索树定义，直接返回 false 。
                * 若 r_i < root 则说明满足二叉搜索树定义，则继续遍历。
        算法流程：
            1. 初始化： 单调栈 stack ，父节点值 root = +∞ （初始值为正无穷大，可把树的根节点看为此无穷大节点的左孩子）；
            2. 倒序遍历 postorder ：记每个节点为 r_i；
                * 判断： 若 r_i > root ，说明此后序遍历序列不满足二叉搜索树定义，直接返回 false ；
                * 更新父节点 root： 当栈不为空 且 r_i < stack.peek()时，循环执行出栈，并将出栈节点赋给 root 。
                * 入栈： 将当前节点 r_i入栈；
            3. 若遍历完成，则说明后序遍历满足二叉搜索树定义，返回 true 。

     */
    public static boolean verifyPostorder_SingleStack(int[] postorder) {
        Stack<Integer> stack = new Stack<>();
        int root = Integer.MAX_VALUE;
        for (int i = postorder.length - 1; i >= 0; i--) {
            if (postorder[i]>root)return false;
            while (!stack.isEmpty()&&stack.peek()>postorder[i])root = stack.pop();
            stack.add(postorder[i]);
        }
        return true;
    }

    public static void main(String[] args) {
        int[] nums = {4, 8, 6, 12, 16, 14, 10};
        System.out.println(verifyPostorder_Recursion(nums));
        System.out.println(verifyPostorder_SingleStack(nums));
    }
}
