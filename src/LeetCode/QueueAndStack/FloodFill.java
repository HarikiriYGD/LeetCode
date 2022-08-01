package LeetCode.QueueAndStack;

import java.util.Arrays;
import java.util.Stack;

public class FloodFill {
    public static Stack<Integer> visited = new Stack<>();

    /**
     * 图像渲染
     *
     * @param image    像素数组
     * @param sr       横坐标
     * @param sc       纵坐标
     * @param newColor 新的像素值
     * @return
     */
    public static int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        // 获取旧的像素值
        int n = image[sr][sc];
        // 深度搜索遍历
        dfs(image, sr, sc, newColor, n);
        return image;
    }

    /**
     * 深度搜索遍历
     *
     * @param image    像素数组
     * @param i        横坐标
     * @param j        纵坐标
     * @param newColor 新的像素值
     * @param n        旧的像素值
     */
    public static void dfs(int[][] image, int i, int j, int newColor, int n) {
        if (i >= image.length || i < 0 || j >= image[0].length || j < 0 || image[i][j] != n || visited.contains(image[i][j]))
            return;
        // 将旧的像素值改变成为新的像素值
        image[i][j] = newColor;
        // 将访问过的节点 压栈
        visited.push(image[i][j]);
        dfs(image, i + 1, j, newColor, n);
        dfs(image, i - 1, j, newColor, n);
        dfs(image, i, j + 1, newColor, n);
        dfs(image, i, j - 1, newColor, n);
    }

    public static void main(String[] args) {
        int[][] image = {{1, 1, 1}, {1, 1, 0}, {1, 0, 1}};
        int[][] res = floodFill(image, 1, 1, 2);
        String s = Arrays.deepToString(res);
        System.out.println(s);
    }

}
