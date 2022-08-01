package LeetCode.Practise_Everyday.Pre_Practise;

/**
 * @Auther: Lil_boat
 * @Date: 12:38 2021/12/17
 * @Description: 小区便利店正在促销，用 numExchange个空酒瓶可以兑换一瓶新酒。你购入了numBottles瓶酒。
 * 如果喝掉了酒瓶中的酒，那么酒瓶就会变成空的。
 * 请你计算 最多 能喝到多少瓶酒
 */
public class NumWaterBottles_1518 {
    static int sum = 0;

    /**
     * 感觉想复杂了
     * 递归的方式
     *
     * @param numBottles
     * @param numExchange
     * @return
     */
    public static int numWaterBottles_Recursion(int numBottles, int numExchange) {
        // 第一次能换多少瓶酒
        int ex = numBottles / numExchange;
        // 剩下的多少瓶酒
        int left = numBottles % numExchange;
        sum += numBottles - left;
        if (ex + left >= numExchange) numWaterBottles_Recursion(ex + left, numExchange);
        else sum += ex + left;
        return sum;
    }

    /**
     * 简化之后
     *
     * @param numBottles
     * @param numExchange
     * @return
     */
    public static int numWaterBottles_Simplify(int numBottles, int numExchange) {
        return numBottles + (numBottles - 1) / (numExchange - 1);
    }

    /**
     * 模拟的方式
     * 首先我们一定可以喝到 b瓶酒，剩下 b个空瓶。接下来我们可以拿瓶子换酒，
     * 每次拿出 e个瓶子换一瓶酒，然后再喝完这瓶酒，得到一个空瓶。以此类推，我们可以统计得到答案。
     *
     * @param numBottles
     * @param numExchange
     * @return
     */
    public static int numWaterBottles_Modify(int numBottles, int numExchange) {
        int bottle = numBottles, ans = numBottles;
        while (bottle >= numExchange) {
            // 兑换的空瓶
            bottle -= numExchange;
            // 喝的酒 + 1
            ++ans;
            // 瓶数 ++
            ++bottle;
        }
        return ans;
    }

    /**
     *第一步，首先我们一定可以喝到 b瓶酒，剩下 b个空瓶。
     *
     * 第二步，接下来我们来考虑空瓶换酒，换完再喝，喝完再换的过程——每次换到一瓶酒就意味着多一个空瓶，
     * 所以每次损失的瓶子的数量为 e - 1，我们要知道这个过程能得到多少瓶酒，
     * 即希望知道第一个打破下面这个条件的 n是多少：
     *
     * b − n(e−1) ≥ e
     *
     * 即我们要找到最小的 n使得：
     *
     * b - n(e - 1) < e
     *
     * 我们得到求得n的最小值 min{n=|(e−1/b−e)+1|}。
     * 当然我们要特别注意这里的前提条件是b ≥ e，试想如果b < e，没有足够的瓶子再换酒了，就不能进行第二步了。
     *
     * @param numBottles
     * @param numExchange
     * @return
     */
    public static int numWaterBottles_Math(int numBottles, int numExchange){
        return numBottles >= numExchange ? (numBottles - numExchange) / (numExchange - 1) + 1 + numBottles : numBottles;

    }


    public static void main(String[] args) {
        System.out.println(numWaterBottles_Recursion(25, 4));
        System.out.println(numWaterBottles_Simplify(25, 4));
    }
}
