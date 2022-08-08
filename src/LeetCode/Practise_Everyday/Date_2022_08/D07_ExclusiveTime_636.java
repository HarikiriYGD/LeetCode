package LeetCode.Practise_Everyday.Date_2022_08;

import java.util.*;

/**
 * @Author: Lil_boat
 * @Date: 2022/8/7 21:19
 * @Title: 函数的独占时间
 * @Description: 有一个 单线程 CPU 正在运行一个含有 n 道函数的程序。每道函数都有一个位于  0 和 n-1 之间的唯一标识符。
 * 函数调用 存储在一个 调用栈 上 ：当一个函数调用开始时，它的标识符将会推入栈中。而当一个函数调用结束时，它的标识符将会从栈中弹出。
 * 标识符位于栈顶的函数是 当前正在执行的函数 。每当一个函数开始或者结束时，将会记录一条日志，包括函数标识符、是开始还是结束、以及相应的时间戳。
 * <p>
 * 给你一个由日志组成的列表 logs ，其中 logs[i] 表示第 i 条日志消息，该消息是一个按 "{function_id}:{"start" | "end"}:{timestamp}" 进行格式化的字符串。
 * 例如，"0:start:3" 意味着标识符为 0 的函数调用在时间戳 3 的 起始开始执行 ；而 "1:end:2" 意味着标识符为 1 的函数调用在时间戳 2 的 末尾结束执行。
 * 注意，函数可以 调用多次，可能存在递归调用 。
 * <p>
 * 函数的 独占时间 定义是在这个函数在程序所有函数调用中执行时间的总和，调用其他函数花费的时间不算该函数的独占时间。
 * 例如，如果一个函数被调用两次，一次调用执行 2 单位时间，另一次调用执行 1 单位时间，那么该函数的 独占时间 为 2 + 1 = 3 。
 * <p>
 * 以数组形式返回每个函数的 独占时间 ，其中第 i 个下标对应的值表示标识符 i 的函数的独占时间。
 * <p>
 * 链接：https://leetcode.cn/problems/exclusive-time-of-functions
 */
public class D07_ExclusiveTime_636 {

    public int[] exclusiveTime(int n, List<String> logs) {
        // 构建结果集
        int[] ans = new int[n];
        int cur = -1;
        Deque<Integer> deque = new ArrayDeque<>();
        for (String log : logs) {
            String[] split = log.split(":");
            // 获取函数的编号 和 开始或结束的时间
            int functionId = Integer.parseInt(split[0]), ts = Integer.parseInt(split[2]);
            if (split[1].equals("start")) {
                if (!deque.isEmpty()) {
                    // 开始累加时间
                    ans[deque.peekLast()] += ts - cur;
                }
                // 入栈
                deque.addLast(functionId);
                // 变更时间
                cur = ts;
            } else {
                // 获取函数的编号
                int idx = deque.pollLast();
                // 计算结束的时间 并进行累加
                ans[idx] += ts - cur + 1;
                // 时间后移
                cur = ts + 1;
            }
        }
        return ans;
    }

}
