package LeetCode.Math;

/**
 * @Auther: Lil_boat
 * @Date: 10:43 2022/4/1
 * @Description: 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。 27 写做  XXVII, 即为 XX + V + II 。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。
 * 数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。
 * 同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。 
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * 给你一个整数，将其转为罗马数字。
 */
public class IntToRoman_12 {

    // 分别代表1000,900,500,400,100,90,50,40,10,9,5,4,3,2,1
    static final String[] roman = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "III", "II", "I"};

    public static String intToRoman(int num) {
        StringBuilder sb = new StringBuilder();
        while (num != 0) {
            if (num >= 1000) {
                sb.append(roman[0]);
                num -= 1000;
                continue;
            }
            if (num >= 900) {
                sb.append(roman[1]);
                num -= 900;
                continue;
            }
            if (num >= 500) {
                sb.append(roman[2]);
                num -= 500;
                continue;
            }
            if (num >= 400) {
                sb.append(roman[3]);
                num -= 400;
                continue;
            }
            if (num >= 100) {
                sb.append(roman[4]);
                num -= 100;
                continue;
            }
            if (num >= 90) {
                sb.append(roman[5]);
                num -= 90;
                continue;
            }
            if (num >= 50) {
                sb.append(roman[6]);
                num -= 50;
                continue;
            }
            if (num >= 40) {
                sb.append(roman[7]);
                num -= 40;
                continue;
            }
            if (num >= 10) {
                sb.append(roman[8]);
                num -= 10;
                continue;
            }
            if (num >= 9) {
                sb.append(roman[9]);
                num -= 9;
                continue;
            }
            if (num >= 5) {
                sb.append(roman[10]);
                num -= 5;
                continue;
            }
            if (num >= 4) {
                sb.append(roman[11]);
                num -= 4;
                continue;
            }
            if (num >= 3) {
                sb.append(roman[12]);
                num -= 3;
                continue;
            }
            if (num >= 2) {
                sb.append(roman[13]);
                num -= 2;
                continue;
            }
            if (num >= 1) {
                sb.append(roman[14]);
                num -= 1;
                continue;
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(3999));
    }

}
