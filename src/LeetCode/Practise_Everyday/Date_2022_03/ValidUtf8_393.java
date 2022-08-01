package LeetCode.Practise_Everyday.Date_2022_03;
/**
 * @Auther: Lil_boat
 * @Date: 11:50 2022/3/13
 * @Description: 给定一个表示数据的整数数组 data ，返回它是否为有效的 UTF-8 编码。
 *
 * UTF-8 中的一个字符可能的长度为 1 到 4 字节，遵循以下的规则：
 *
 * 对于 1 字节 的字符，字节的第一位设为 0 ，后面 7 位为这个符号的 unicode 码。
 * 对于 n 字节 的字符 (n > 1)，第一个字节的前 n 位都设为1，第 n+1 位设为 0 ，后面字节的前两位一律设为 10 。
 * 剩下的没有提及的二进制位，全部为这个符号的 unicode 码。
 * 这是 UTF-8 编码的工作方式：
 *
 *    Char. number range  |        UTF-8 octet sequence
 *       (hexadecimal)    |              (binary)
 *    --------------------+---------------------------------------------
 *    0000 0000-0000 007F | 0xxxxxxx
 *    0000 0080-0000 07FF | 110xxxxx 10xxxxxx
 *    0000 0800-0000 FFFF | 1110xxxx 10xxxxxx 10xxxxxx
 *    0001 0000-0010 FFFF | 11110xxx 10xxxxxx 10xxxxxx 10xxxxxx
 * 注意：输入是整数数组。只有每个整数的 最低 8 个有效位 用来存储数据。这意味着每个整数只表示 1 字节的数据。
 */
public class ValidUtf8_393 {
    /*
        一字节码：第一字节<128；
        二字节码：192<=第一字节<224；
        三字节码：224<=第一字节<240；
        四字节码：248>第一字节>=240；
        其他大小的第一字节非法；
        大于二字节的码，后边的字节都大于等于128小于192
     */
    public static boolean validUtf8(int[] data) {
        for(int i = 0; i < data.length; i++){
            // 一字节码 0xxxxxxx
            if(data[i] < 128) continue;
            // 不符合情况 直接返回false
            if(data[i] < 192 || data[i] >= 248) return false;
                // 二字节码 11xxxxxx
            else if(data[i] < 224){
                if(i + 2 > data.length) return false;
                if(!(data[i + 1] >= 128 && data[i + 1] < 192))return false;
                i++;
            }
            // 三字节码 111xxxxx
            else if(data[i] < 240){
                if(i + 3 > data.length) return false;
                for(int j = 1; j <= 2; j++){
                    if(!(data[i + j] >= 128 && data[i + j] < 192))return false;
                }

                i+=2;
            }
            // 四字节码 1111xxxx
            else{
                if(i + 4 > data.length) return false;
                for(int j = 1; j <= 3; j++){
                    if(!(data[i + j] >= 128 && data[i + j] < 192))return false;
                }
                i+=3;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int[] data = {4};
        System.out.println(validUtf8(data));
    }
}
