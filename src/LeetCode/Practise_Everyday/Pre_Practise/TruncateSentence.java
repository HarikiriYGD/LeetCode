package LeetCode.Practise_Everyday.Pre_Practise;

/**
 * @Auther: Lil_boat
 * @Date: 16:44 2021/12/6
 * @Description: 句子 是一个单词列表，列表中的单词之间用单个空格隔开，且不存在前导或尾随空格。每个单词仅由大小写英文字母组成（不含标点符号）。
 * <p>
 * 例如，"Hello World"、"HELLO" 和 "hello world hello world" 都是句子。
 * 给你一个句子 s和一个整数 k，请你将 s截断,使截断后的句子仅含前 k个单词。返回截断s后得到的句子。
 */
public class TruncateSentence {

    /**
     * 建立StringBuilder将前面的单词放入之中
     *
     * @param s s 仅由大小写英文字母和空格组成，s中的单词之间由单个空格隔开
     * @param k 单词的个数
     * @return
     */
    public static String truncateSentence(String s, int k) {
        StringBuilder builder = new StringBuilder();
        // 以空格断开
        String[] tmp = s.split(" ");
        int index = 0;
        while (index < k) {
            if (index != k - 1) {
                // 如果不是最后一个，则放入单词和在后面加空格
                builder.append(tmp[index]);
                builder.append(" ");
                index++;
            } else {
                // 最后一个单词则后面不用加空格
                builder.append(tmp[index]);
                index++;
            }
        }
        return builder.toString();
    }

    /**
     * 利用subString()将单词后面是空格的单词加入到s中，其余都截断
     *
     * @param s s 仅由大小写英文字母和空格组成，s中的单词之间由单个空格隔开
     * @param k 单词的个数
     * @return
     */
    public static String truncateSentence_subString(String s, int k) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ' ' && --k == 0) {
                return s.substring(0, i);
            }
        }
        return s;
    }

    public static void main(String[] args) {
        String s = "Hello how are you Contestant";
        System.out.println(truncateSentence(s, 4));
        System.out.println(truncateSentence_subString(s, 4));
    }

}
