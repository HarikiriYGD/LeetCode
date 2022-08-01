package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @Auther: Lil_boat
 * @Date: 14:45 2021/12/14
 * @Description: 这里有 n 门不同的在线课程，按从 1 到 n 编号。给你一个数组 courses ，
 * 其中 courses[i] = [durationi, lastDayi] 表示第 i 门课将会 持续 上 durationi 天课，
 * 并且必须在不晚于 lastDayi 的时候完成。
 * 你的学期从第 1 天开始。且不能同时修读两门及两门以上的课程。
 * 返回你最多可以修读的课程数目。
 */
public class ScheduleCourse {

    /**
     * 贪心 + 优先队列
     * @param courses
     * @return
     */
    public static int scheduleCourse(int[][] courses) {
        // 根据结束时间将courses升序排序
        Arrays.sort(courses, (a, b) -> a[1] - b[1]);
        System.out.println(Arrays.deepToString(courses));
        // 建立一个优先队列
        // 储存已选择的课程，按照持续时间排序
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<Integer>((a, b) -> b - a);

        int total = 0;
        for (int[] course : courses) {
            int ti = course[0], di = course[1];
            // 如果当前课程时间不冲突，将该课程加入队列
            // 这里的不冲突可以理解为，0~day+c[0]这段区间，我们还可以再插入当前一节课
            if (ti + total <= di) {
                total += ti;
                priorityQueue.offer(ti);
                // 课程时间冲突，且有选过其他课，这时我们找到最长时间的课程，用当前的短课替换了，余出了更多的空区间
                // 所以这里我们余出的时间其实就是两者的持续时间之差，课程变短了，day会前移，这样我们相当于变相给后面的课程增加了选择的区间
            } else if (!priorityQueue.isEmpty() && priorityQueue.peek() > ti) {
                total -= priorityQueue.poll() - ti;
                priorityQueue.offer(ti);
            }
        }
        return priorityQueue.size();
    }


    public static void main(String[] args) {
//        int[][] courses = {{7, 16}, {2, 3}, {3, 12}, {3, 14}, {10, 19}, {10, 16}, {6, 8}, {6, 11}, {3, 13}, {6, 16}};
//        int[][] courses1 = {{100, 2}};
       int[][] courses2 = {{5, 5}, {2, 6}, {4, 6}};
//        System.out.println(scheduleCourse(courses));
//        System.out.println(scheduleCourse(courses1));
        System.out.println(scheduleCourse(courses2));
    }

}
