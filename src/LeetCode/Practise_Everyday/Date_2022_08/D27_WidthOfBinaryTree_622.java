package LeetCode.Practise_Everyday.Date_2022_08;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @Author: Lil_boat
 * @Date: 2022/8/28 22:07
 * @Description:
 */
public class D27_WidthOfBinaryTree_622 {

    public int widthOfBinaryTree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        HashMap<TreeNode, Integer> map = new HashMap<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        map.put(root, 1);
        long ans = 0;
        long min = Long.MAX_VALUE, max = Long.MIN_VALUE;
        while (!queue.isEmpty()) {
            min = Long.MAX_VALUE;
            max = Long.MIN_VALUE;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = queue.poll();
                if (i == 0) {
                    min = Math.min(map.get(cur), min);
                }
                if (i == size - 1) {
                    max = Math.max(map.get(cur), max);
                }
                if (cur.left != null) {
                    queue.add(cur.left);
                    map.put(cur.left, map.get(cur) * 2);
                }
                if (cur.right != null) {
                    queue.add(cur.right);
                    map.put(cur.right, map.get(cur) * 2 + 1);
                }
            }
            ans = Math.max(max - min + 1, ans);
        }

        return ans > Integer.MAX_VALUE ? (int) (ans - Integer.MAX_VALUE) : (int) ans;
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
    }

}
