package LeetCode.QueueAndStack;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class OpenLock {
    /**
     * 打开转盘锁
     *
     * @param deadends 死亡数字
     * @param target   解锁的数字
     * @return
     */
    public static int openLock(String[] deadends, String target) {
        HashSet<String> dead_set = new HashSet<>(Arrays.asList(deadends)); // 死亡数字转换为哈希表
        if (dead_set.contains("0000")) return -1; // 如果死亡数字包含“0000”，直接返回-1
        Queue<String> queue = new LinkedList<>(); // 队列
        queue.add("0000"); // 加入开始节点
        int count = 0; // 记录解锁次数
        while (!queue.isEmpty()) { // 节点未访问完，队列内的节点不为空
            int size = queue.size(); // 每一步节点数
            while (size-- > 0) {
                String tmp = queue.remove(); // 弹出头节点
                if (target.equals(tmp)) return count; // 如果和解锁的数字相同，返回步数
                char[] c = tmp.toCharArray(); // 转为数组
                for (int j = 0; j < 4; j++) { // 修改四位数字
                    int i = c[j] - '0'; // 转为int型
                    c[j] = (char) ('0' + (i + 9) % 10); // 数字-1。余数运算可防止节点为0，9时出现-1和10的情况
                    String s = new String(c); // 得到新的字符串
                    if (!dead_set.contains(s)) { // 字符串不在死亡数字中
                        queue.add(s); // 添加到队列作为下一个开始遍历的节点
                        dead_set.add(s); // 加入死亡数字中
                    }

                    c[j] = (char) ('0' + (i + 11) % 10); // 数字+1。余数运算可防止节点为0，9时出现-1和10的情况
                    s = new String(c);
                    if (!dead_set.contains(s)) { // 字符串不在死亡数字中
                        queue.add(s); // 添加到队列作为下一个开始遍历的节点
                        dead_set.add(s); // 加入死亡数字中
                    }

                    c[j] = (char) ('0' + i); // 数字不变
                }
            }
            count++;
        }
        return -1;
    }

    public static void main(String[] args) {
        String[] deadends = {"0201", "0101", "0102", "1212", "2002"};
        int reslut = openLock(deadends, "0202");
        System.out.println(reslut);
    }

}
