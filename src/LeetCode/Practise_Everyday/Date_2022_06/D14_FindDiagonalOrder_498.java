package LeetCode.Practise_Everyday.Date_2022_06;
/**
 * @Auther: Lil_boat
 * @Date: 10:27 2022/6/14
 * @Tile: 对角线遍历
 * @Description: 给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
 */
public class D14_FindDiagonalOrder_498 {

    public int[] findDiagonalOrder(int[][] mat) {
        int n = mat.length, m = mat[0].length, cnt = m * n;
        int[] ans = new int[cnt];
        // dir 表示 访问对角线的方向
        int x = 0, y = 0, dir = 1, idx = 0;
        while (idx != cnt) {
            ans[idx++] = mat[x][y];
            int nx = x, ny = y;
            if (dir == 1) {
                nx = x - 1;
                ny = y + 1;
            } else {
                nx = x + 1;
                ny = y - 1;
            }
            if (idx < cnt && (nx < 0 || nx >= n || ny < 0 || ny >= m)) {
                if (dir == 1) {
                    nx = y + 1 < m ? x : x + 1;
                    ny = y + 1 < m ? y + 1 : y;
                } else {
                    nx = x + 1 < n ? x + 1 : x;
                    ny = x + 1 < n ? y : y + 1;
                }
                dir *= -1;
            }
            x = nx; y = ny;
        }
        return ans;
    }

}
