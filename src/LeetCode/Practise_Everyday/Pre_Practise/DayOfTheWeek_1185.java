package LeetCode.Practise_Everyday.Pre_Practise;

/**
 * @Auther: Lil_boat
 * @Date: 14:17 2022/1/4
 * @Description: 给你一个日期，请你设计一个算法来判断它是对应一周中的哪一天。
 * 输入为三个整数：day、month 和 year，分别表示日、月、年。
 * 您返回的结果必须是这几个值中的一个 {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"}。
 */
public class DayOfTheWeek_1185 {
    /*
    题目规定输入的日期一定是在 1971到 2100年之间的有效日期，即在 1971 年 1 月 1 日，到 2100年 12 月 31 日之间。
    通过查询日历可知，1970 年 12 月 31 日是星期四，我们只需要算出输入的日期距离 1970 年 12 月 31 日有几天，再加上 3 后对 7 求余，
    即可得到输入日期是一周中的第几天。

        求输入的日期距离 19701970 年 1212 月 3131 日的天数，可以分为三部分分别计算后求和：
            （1）输入年份之前的年份的天数贡献；
            （2）输入年份中，输入月份之前的月份的天数贡献；
            （3）输入月份中的天数贡献。
        例如，如果输入是 2100年 12 月 31 日，即可分为三部分分别计算后求和：
        （1）1971 年 1 月 1 到 2099 年 12 月 31 日之间所有的天数；
        （2）2100 年 1 月 1 日到 2100 年 11 月 31 日之间所有的天数；
        （3）2100 年 12 月 1 日到 2100 年 12 月 31 日之间所有的天数。
    其中（1）和（2）部分的计算需要考虑到闰年的影响。当年份是 400 的倍数或者是 4 的倍数且不是 100 的倍数时，该年会在二月份多出一天。
     */
    public static String dayOfTheWeek(int day, int month, int year) {
        String[] weeks = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30};
        // 输入年份看过了多少天
        int days = (year - 1971) * 365 + (year - 1969) / 4;
        // 输入月份之前的过了多少天
        for (int i = 0; i < month - 1; i++) {
            days += months[i];
        }
        // 判断今年是否是闰年
        if ((year % 400 == 0 || (year % 4 == 0 && year % 100 != 0)) && month >= 3) days += 1;
        // 看过了多少天
        days += day;
        return weeks[(days + 3) % 7];
    }

    /*
        蔡勒公式：
            蔡勒公式我想有不少同学都了解过，一般刚学编程时候会介绍这个公式，但应该没有人专门背下来。这里做个记录；
                            D=[ c/4 ]− 2c + y + [y/4] + [13(m+1)/5] + d − 1
                            W = D mod 7
            其中：
                c 是世纪数减一，也就是年份的前两位。
                y 是年份的后两位。
                m 是月份。m 的取值范围是 3 至 14，因为某年的 1、2 月要看作上一年的 13、14月，比如 2019 年的 1 月 1 日要看作 2018 年的 13 月 1 日来计算。
                d 是该月第几天。
                [] 代表对计算结果向下取整，只保留整数部分。
                W = D%7 是结果，代表一周中第几天, 0 为周日。
            由于蔡勒公式最后计算 D 可能为负数，需要处理一下。
            方法很多：这里由 D 计算式发现减的内容最大为 199 所以可以加上一个大于 199 且是 7 的倍数的数。
            我随便取一个 210 加上保证结果为正。
     */
    public static String dayOfTheWeek_caile(int day, int month, int year) {
        String[] weeks = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        if (month < 3) {
            month += 12;
            year--;
        }
        int c = year / 100;
        year = year % 100;
        int D = c / 4 - 2 * c + year + year / 4 + 13 * (month + 1) / 5 + day - 1 + 210;
        return weeks[D % 7];
    }

    public static void main(String[] args) {
        System.out.println(dayOfTheWeek(31, 8, 2019));
        System.out.println(dayOfTheWeek_caile(31, 8, 2019));
    }
}
