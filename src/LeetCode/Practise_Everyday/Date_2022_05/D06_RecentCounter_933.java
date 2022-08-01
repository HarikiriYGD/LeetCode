package LeetCode.Practise_Everyday.Date_2022_05;

import java.util.ArrayDeque;
import java.util.Deque;
/**
 * @Auther: Lil_boat
 * @Date: 11:10 2022/5/6
 * @Tile: 最近的请求次数
 * @Description: 写一个 RecentCounter 类来计算特定时间范围内最近的请求。
 *
 * 请你实现 RecentCounter 类：
 *
 * RecentCounter() 初始化计数器，请求数为 0 。
 * int ping(int t) 在时间 t 添加一个新请求，其中 t 表示以毫秒为单位的某个时间，
 * 并返回过去 3000 毫秒内发生的所有请求数（包括新请求）。确切地说，返回在 [t-3000, t] 内发生的请求数。
 * 保证 每次对 ping 的调用都使用比之前更大的 t 值。
 *
 * 链接：https://leetcode-cn.com/problems/number-of-recent-calls
 */
public class D06_RecentCounter_933 {


    Deque<Integer> deque ;

    public D06_RecentCounter_933() {
     deque = new ArrayDeque<>();
    }

    public int ping(int t) {
        deque.addLast(t);
        while (!deque.isEmpty()) {
            int pre = deque.peek();
            if (pre < t - 3000)deque.pollFirst();
            else break;
        }
        return deque.size();
    }

    public static void main(String[] args) {
        D06_RecentCounter_933 test = new D06_RecentCounter_933();
        System.out.println(test.ping(1));
        System.out.println(test.ping(100));
        System.out.println(test.ping(3001));
        System.out.println(test.ping(3002));
    }

}
