package LeetCode.Practise_Everyday.Date_2022_03;

import java.util.HashSet;
import java.util.Set;
/**
 * @Auther: Lil_boat
 * @Date: 11:03 2022/3/21
 * @Description: 给定一个二叉搜索树 root 和一个目标结果 k，如果 BST 中存在两个元素且它们的和等于给定的目标结果，则返回 true。
 */
public class FindTarget_653 {

    Set<Integer> set = new HashSet<Integer>();

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        if (set.contains(k - root.val)) {
            return true;
        }
        set.add(root.val);
        return findTarget(root.left, k) || findTarget(root.right, k);
    }

}
