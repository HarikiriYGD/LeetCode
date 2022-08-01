package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: Lil_boat
 * @Date: 11:30 2022/1/18
 * @Description: 给定一个 24 小时制（小时:分钟 "HH:MM"）的时间列表，找出列表中任意两个时间的最小时间差并以分钟数表示。
 */
public class FindMinDifference_539 {
    /*
    将 timePoints 排序后，最小时间差必然出现在 timePoints 的两个相邻时间，或者 timePoints 的两个首尾时间中。
    因此排序后遍历一遍 timePoints 即可得到最小时间差。
     */
    public static int findMinDifference(List<String> timePoints) {
        // 最多有1440个时间，如果超过则说明有相同的时间，直接返回0即可。
        if (timePoints.size()>1440)return 0;
        // 记录最小时间差
        int min = 0;
        // 记录索引
        int index = 0;
        // 存储时间的数组
        int[] tmp = new int[timePoints.size()];
        for (String s : timePoints) {
            // 转换成分钟数
            char[] times = s.toCharArray();
            int hour = (times[0] - '0') * 10 + (times[1] - '0');
            int minute = (times[3] - '0') * 10 + (times[4] - '0');
            int minutes = hour * 60 + minute;
            tmp[index++] = minutes;
        }
        // 排序
        Arrays.sort(tmp);
        // 记录相隔的最小时间差
        int m = tmp[1] - tmp[0];
        // 遍历排序后的数组，寻找最小的相邻时间差
        for (int i = 1; i < tmp.length - 1; i++) {
            if (tmp[i + 1] - tmp[i] < m) m = tmp[i + 1] - tmp[i];
        }
        // 计算最后一个时间和第一个时间的差值，首尾时间差
        m = Math.min(m, tmp[0] + 1440 - tmp[index - 1]);
        return m;
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        String s1 = "23:59";
        String s2 = "00:00";
        list.add(s1);
        list.add(s2);
        System.out.println(findMinDifference(list));
    }
}
