package LeetCode.Practise_Everyday.Pre_Practise;

import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 9:44 2021/12/21
 * @Description: 给你一个字符串 date ，按 YYYY-MM-DD 格式表示一个 现行公元纪年法 日期。
 * 请你计算并返回该日期是当年的第几天。
 * <p>
 * 通常情况下，我们认为 1 月 1 日是每年的第 1 天，1 月 2 日是每年的第 2 天，
 * 依此类推。每个月的天数与现行公元纪年法（格里高利历）一致。
 */
public class DayOfYear_1154 {
    public static int dayOfYear(String date) {
        int day = 0;
        int[] dates = new int[3];
        int[] sum = new int[]{0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30};
        String[] strings = date.split("-");
        for (int i = 0; i < strings.length; i++) {
            dates[i] = Integer.parseInt(strings[i]);
        }
        System.out.println(Arrays.toString(dates));
        // 判断是否为闰年
        if ((dates[0] % 4 == 0 && dates[0] % 100 != 0) || dates[0] % 400 == 0) {
            for (int i = 0; i < dates[1]; i++) {
                day += sum[i];
            }
            if (dates[1] > 2) day += dates[2] + 1;
            else day += dates[2];
        } else {
            for (int i = 0; i < dates[1]; i++) {
                day += sum[i];
            }

            day += dates[2];
        }

        return day;
    }

    public static void main(String[] args) {
        String date = "1900-03-09";
        System.out.println(dayOfYear(date));
    }
}
