package LeetCode.Practise_Everyday.Pre_Practise;

/**
 * @Auther: Lil_boat
 * @Date: 11:35 2022/2/5
 * @Description: 你要开发一座金矿，地质勘测学家已经探明了这座金矿中的资源分布，并用大小为 m * n 的网格 grid 进行了标注。
 * 每个单元格中的整数就表示这一单元格中的黄金数量；如果该单元格是空的，那么就是 0。
 * 为了使收益最大化，矿工需要按以下规则来开采黄金：
 * <p>
 * 每当矿工进入一个单元，就会收集该单元格中的所有黄金。
 * 矿工每次可以从当前位置向上下左右四个方向走。
 * 每个单元格只能被开采（进入）一次。
 * 不得开采（进入）黄金数目为 0 的单元格。
 * 矿工可以从网格中 任意一个 有黄金的单元格出发或者是停止。
 */
public class GetMaximumGold_1219 {
    int ans = 0;
    int row = 0;
    int col = 0;
    int[][] grid;

    public int getMaximumGold(int[][] grid) {
        this.grid = grid;
        // 定义行和列的数目
        int row = grid.length;
        int col = grid[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                // 从每一个大于0的矿洞开始DFS
                if (grid[i][j] > 0) {
                    dfs(i, j, 0);
                }
            }
        }
        return ans;
    }

    public void dfs(int i, int j, int gold) {
        if (i < 0 || i >= row || j < 0 || j >= col || grid[i][j] == 0) return;
        int g = grid[i][j];
        gold += g;
        grid[i][j] = 0;
        ans = Math.max(ans, gold);
        dfs(i + 1, j, gold);
        dfs(i - 1, j, gold);
        dfs(i, j + 1, gold);
        dfs(i, j - 1, gold);
        gold -= g;
        grid[i][j] = g;
    }
}
