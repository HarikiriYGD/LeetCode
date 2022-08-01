package LeetCode.Every_weeks_competition.WeiLai;

import java.util.Arrays;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

/**
 * @Author: Lil_boat
 * @Date: 13:42 2022/7/4
 * @Tile:
 * @Description: 给你两个整数：m 和 n ，表示矩阵的维数。
 * 另给你一个整数链表的头节点 head 。
 * 请你生成一个大小为 m x n 的螺旋矩阵，矩阵包含链表中的所有整数。
 * 链表中的整数从矩阵 左上角 开始、顺时针 按 螺旋 顺序填充。如果还存在剩余的空格，则用 -1 填充。
 * 返回生成的矩阵。
 *
 * 链接：https://leetcode.cn/problems/spiral-matrix-iv
 */
public class SpiralMatrix {

    public int[][] spiralMatrix(int m, int n, ListNode head) {
        // 如果链表为空，直接返回空数组
        if (head == null) {
            return new int[][]{};
        }
        // 构建结果集
        int[][] ans = new int[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(ans[i], -1);
        }
        ListNode cur = head;
        // 四个方向
        int[][] dir = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        // idx为前进方向，x与y是遍历指针
        int idx = 0, x = 0, y = -1;
        while (cur != null) {
            int newX = x + dir[idx][0], newY = y + dir[idx][1];
            if (newX < 0 || newX >= m || newY < 0 || newY >= n || ans[newX][newY] != -1) {
                idx = (idx + 1) % 4;
            }
            x += dir[idx][0];
            y += dir[idx][1];
            ans[x][y] = cur.val;
            cur = cur.next;
        }
        return ans;
    }

    public static void main(String[] args) {
        SpiralMatrix t = new SpiralMatrix();
        ListNode head = new ListNode(3);
        ListNode head1 = new ListNode(0);
        ListNode head2 = new ListNode(2);
        ListNode head3 = new ListNode(6);
        ListNode head4 = new ListNode(8);
        ListNode head5 = new ListNode(1);
        ListNode head6 = new ListNode(7);
        ListNode head7 = new ListNode(9);
        ListNode head8 = new ListNode(4);
        ListNode head9 = new ListNode(2);
        ListNode head10 = new ListNode(5);
        ListNode head11 = new ListNode(5);
        ListNode head12 = new ListNode(0);

        head.next = head1;
        head1.next = head2;
        head2.next = head3;
        head3.next = head4;
        head4.next = head5;
        head5.next = head6;
        head6.next = head7;
        head7.next = head8;
        head8.next = head9;
        head9.next = head10;
        head10.next = head11;
        head11.next = head12;
        int[][] res = t.spiralMatrix(3, 5, head);
        System.out.println(Arrays.deepToString(res));
    }

}
