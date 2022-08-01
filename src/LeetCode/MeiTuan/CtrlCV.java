package LeetCode.MeiTuan;

import java.io.BufferedReader;
import java.io.InputStreamReader;
/**
 * @Auther: Lil_boat
 * @Date: 10:18 2022/4/21
 * @Tile: 小团的复制粘贴
 * @Description: 小团是一个莫得感情的 CtrlCV 大师，他有一个下标从 1 开始的序列 A 和一个初始全部为 -1 序列 B ，两个序列的长度都是 n 。
 * 他会进行若干次操作，每一次操作，他都会选择 A 序列中一段连续区间，将其粘贴到 B 序列中的某一个连续的位置，在这个过程中他也会查询 B 序列中某一个位置上的值。
 * 我们用如下的方式表示他的粘贴操作和查询操作：
 *
 * 粘贴操作：1 k x y，表示把 A 序列中从下标 x 位置开始的连续 k 个元素粘贴到 B 序列中从下标 y 开始的连续 k 个位置上。原始序列中的元素被覆盖。
 * （注意：输入数据可能会出现粘贴后 k 个元素超出 B 序列原有长度的情况，超出部分可忽略）
 * 查询操作：2 x，表示询问B序列下标 x 处的值是多少。
 */
class SegNode {
    public int l, r;
    public int val, ai;

    SegNode() {
    }

    SegNode(int l_, int r_, int val_, int ai_) {
        l = l_;
        r = r_;
        val = val_;
        ai = ai_;
    }
}

public class CtrlCV {

    public static int N = 20010;
    public static int[] A = new int[N];
    public static SegNode[] tree = new SegNode[N * 4];

    public static void build(int root, int l, int r) {

        tree[root] = new SegNode(l, r, -1, 0);
        if (l == r) return;
        int mid = (l + r) / 2;
        build(root << 1, l, mid);
        build(root << 1 | 1, mid + 1, r);
    }

    public static void push_down(int root) {
        if (tree[root].ai > 0) {
            int mid = (tree[root].l + tree[root].r) / 2;
            tree[root << 1].val = A[tree[root].ai];
            tree[root << 1].ai = tree[root].ai;
            tree[root << 1 | 1].val = A[tree[root].ai + mid + 1 - tree[root].l];
            tree[root << 1 | 1].ai = tree[root].ai + mid + 1 - tree[root].l;
            tree[root].ai = 0;
        }
    }

    public static void update(int root, int ul, int ur, int ai) {
        if (ul <= tree[root].l && tree[root].r <= ur) {
            tree[root].val = A[ai + tree[root].l - ul];
            tree[root].ai = ai + tree[root].l - ul;
            return;
        }
        push_down(root);
        int mid = (tree[root].l + tree[root].r) / 2;
        if (ul <= mid)
            update(root << 1, ul, ur, ai);
        if (mid + 1 <= ur)
            update(root << 1 | 1, ul, ur, ai);
        // push_up(root);
    }

    public static int query(int root, int ID) {
        if (ID == tree[root].l && tree[root].r == ID) {
            return tree[root].val;
        }

        push_down(root);
        int mid = (tree[root].l + tree[root].r) / 2;
        if (ID <= mid)
            return query(root << 1, ID);
        return query(root << 1 | 1, ID);
        // push_up(root);
    }


    public static void main(String[] args) throws Exception {
        // 获取输入流
        BufferedReader BR = new BufferedReader(new InputStreamReader(System.in));
        // 获取序列的长度
        int n = Integer.parseInt(BR.readLine());
        String [] line1 = BR.readLine().split(" ");
        // 给数组A赋值
        for (int i = 1; i < n + 1; i ++)
            A[i] = Integer.parseInt(line1[i - 1]);
        // 获取操作数的个数
        int m = Integer.parseInt(BR.readLine());
        // 构建线段树
        build(1, 1, n);
        // 获取每一行的操作数
        for (int ee = 0; ee < m; ee ++)
        {
            String [] line2 = BR.readLine().split(" ");
            if (line2[0].equals("1"))
            {
                int len_ = Integer.parseInt(line2[1]);
                int ai = Integer.parseInt(line2[2]);
                int bl = Integer.parseInt(line2[3]);
                int br = bl + len_ - 1;
                update(1, bl, br, ai);
            }
            else
            {
                int bj = Integer.parseInt(line2[1]);
                System.out.println(query(1, bj));
            }
        }
    }


}
