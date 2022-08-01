package LeetCode.Practise_Everyday.Pre_Practise;
/**
 * @Auther: Lil_boat
 * @Date: 11:14 2022/2/18
 * @Description: 有一个无向的 星型 图，由 n 个编号从 1 到 n 的节点组成。星型图有一个 中心 节点，
 * 并且恰有 n - 1 条边将中心节点与其他每个节点连接起来。
 * 给你一个二维整数数组 edges ，其中 edges[i] = [ui, vi] 表示在节点 ui 和 vi 之间存在一条边。请你找出并返回 edges 所表示星型图的中心节点。
 */
public class FindCenter_1791 {
    /*
        中心节点一定是重复元素 所以判断前两个一维数组中的元素哪个相等 则返回哪个
     */
    public static int findCenter(int[][] edges) {
        return edges[0][0] == edges[1][0] || edges[0][0] == edges[1][1] ? edges[0][0] : edges[0][1];
    }

    public static void main(String[] args) {
        int[][] edges = {{1,2},{2,3},{4,2}};
        System.out.println(findCenter(edges));
    }
}
