package LeetCode.AimToOffer;

import java.util.*;

/**
 * @Auther: Lil_boat
 * @Date: 17:23 2022/1/6
 * @Description: 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，
 * 第三行再按照从左到右的顺序打印，其他行以此类推。
 * 例如:
 * 给定二叉树: [3,9,20,null,null,15,7],
 * 3
 * / \
 * 9  20
 * /  \
 * 15   7
 * 返回其层次遍历结果：
 * [
 * [3],
 * [20,9],
 * [15,7]
 * ]
 */

public class LevelOrder_32_III {
    /*
    方法一：层序遍历 + 双端队列
        利用双端队列的两端皆可添加元素的特性，设打印列表（双端队列） tmp ，并规定：
            奇数层 则添加至 subList 尾部 ，
            偶数层 则添加至 subList 头部 。
        算法流程：
            1. 特例处理： 当树的根节点为空，则直接返回空列表 [] ；
            2. 初始化： 打印结果空列表 ans ，包含根节点的双端队列 queue ；
            3. BFS 循环： 当 queue 为空时跳出；
                A. 新建列表 subList ，用于临时存储当前层打印结果；
                B. 当前层打印循环： 循环次数为当前层节点数（即 queue 长度）；
                    出队： 队首元素出队，记为 cur；
                    打印： 若为奇数层，将 cur.val 添加至 subList 尾部；否则，添加至 subList 头部；
                    添加子节点： 若 cur 的左（右）子节点不为空，则加入 queue ；
                C. 将当前层结果 subList 转化为 list 并添加入 ans ；
            4. 返回值： 返回打印结果列表 ans 即可；
     */
    public static List<List<Integer>> levelOrder_DoubleQueue(TreeNode root) {
        if (root == null) return new ArrayList<List<Integer>>() {
        };
        // 构建结果List
        List<List<Integer>> ans = new ArrayList<>();
        // 层序遍历
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        // queue不为空
        while (!queue.isEmpty()) {
            // 双端队列
            LinkedList<Integer> subList = new LinkedList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode cur = queue.poll();
                // 偶数层
                if (ans.size() % 2 == 0) subList.addLast(cur.val);
                    // 奇数层
                else subList.addFirst(cur.val);
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            ans.add(subList);

        }
        return ans;
    }

    /*
    方法二：层序遍历 + 双端队列（奇偶层逻辑分离）
        方法一代码简短、容易实现；但需要判断每个节点的所在层奇偶性，即冗余了 N 次判断。
        通过将奇偶层逻辑拆分，可以消除冗余的判断。
    算法流程：
        与方法一对比，仅 BFS 循环不同。
        BFS 循环： 循环打印奇 / 偶数层，当 deque 为空时跳出；
        打印奇数层： 从左向右 打印，先左后右 加入下层节点；
        若 deque 为空，说明向下无偶数层，则跳出；
        打印偶数层： 从右向左 打印，先右后左 加入下层节点；

     */
    public static List<List<Integer>> levelOrder_OddOrEven(TreeNode root){
        if (root == null) return new ArrayList<List<Integer>>() {
        };
        // 构建结果List
        List<List<Integer>> ans = new ArrayList<>();
        // 层序遍历
        Deque<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()){
            // 打印奇数层
            List<Integer> subList = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
                // 从左向右打印
                TreeNode cur =  queue.removeFirst();
                subList.add(cur.val);
                if (cur.left != null) queue.addLast(cur.left);
                if (cur.right != null) queue.addLast(cur.right);
            }
            ans.add(subList);
            if (queue.isEmpty())break;
            subList = new ArrayList<>();
            // 打印偶数层
            for (int i = queue.size(); i > 0; i--) {
                // 从右向左打印
                TreeNode cur =  queue.removeLast();
                subList.add(cur.val);
                if (cur.right != null) queue.addFirst(cur.right);
                if (cur.left != null) queue.addFirst(cur.left);
            }
            ans.add(subList);
        }
        return ans;
    }

    /*
    方法三：层序遍历 + 倒序
        此方法的优点是只用列表即可，无需其他数据结构。
        偶数层倒序： 若 ans 的长度为 奇数 ，说明当前是偶数层，则对 subList 执行 倒序 操作。
     */
    public static List<List<Integer>> levelOrder_Reverse(TreeNode root) {
        if (root == null) return new ArrayList<List<Integer>>() {
        };
        // 构建存放每一层节点的队列
        Queue<TreeNode> queue = new LinkedList<>();
        // 构建结果集
        List<List<Integer>> ans = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            List<Integer> subList = new ArrayList<>();
            for (int i = queue.size(); i > 0; i--) {
                TreeNode cur = queue.poll();
                subList.add(cur.val);
                if (cur.left != null) queue.add(cur.left);
                if (cur.right != null) queue.add(cur.right);
            }
            // 奇数层就反转之后再添入结果集
            if (ans.size() % 2 == 1) Collections.reverse(subList);
            ans.add(subList);
        }
        return ans;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(9);
        TreeNode right = new TreeNode(20);
        TreeNode l = new TreeNode(15);
        TreeNode r = new TreeNode(7);
        root.left = left;
        root.right = right;
        right.left = l;
        right.right = r;
        List<List<Integer>> res = levelOrder_DoubleQueue(root);
        for (List<Integer> list : res) {
            for (Integer i : list) {
                System.out.println(i);
            }
        }
    }
}
