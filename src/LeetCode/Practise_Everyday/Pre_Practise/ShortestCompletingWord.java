package LeetCode.Practise_Everyday.Pre_Practise;

/**
 * @Auther: Lil_boat
 * @Date: 12:43 2021/12/10
 * @Description: 给你一个字符串 licensePlate 和一个字符串数组 words ，请你找出并返回 words 中的 最短补全词 。
 * 补全词 是一个包含 licensePlate 中所有的字母的单词。在所有补全词中，最短的那个就是 最短补全词 。
 * 在匹配 licensePlate 中的字母时：
 * 忽略 licensePlate 中的 数字和空格 。
 * 不区分大小写。
 * 如果某个字母在 licensePlate 中出现不止一次，那么该字母在补全词中的出现次数应当一致或者更多。
 */
public class ShortestCompletingWord {

    public static String shortestCompletingWord(String licensePlate, String[] words) {
        // 判断words数组是否为空
        if (words.length == 0) return null;
        // 记录licensePlate字符串中的字数个数
        int[] count = new int[26];
        // 克隆count数组
        int[] clone = new int[26];
        // 记录最小匹配的单词的下标
        int best = -1;
        char[] chars = licensePlate.toLowerCase().toCharArray();
        for (char c : chars) {
            if (c >= 'a' && c <= 'z') {
                count[c - 'a']++;
            }
        }
        // 遍历words数组
        for (int i = 0; i < words.length; i++) {
            clone = count.clone();
            boolean flag = true;
            // 将clone数组中对应字母的个数减1
            for (int k = 0; k < words[i].length(); k++) {
                clone[words[i].charAt(k) - 'a']--;
            }
            for (int j = 0; j < 26; j++) {
                // 如果clone数组中有大于0的元素，则表明该word[i]不能表示licensePlate
                if (clone[j] > 0) {
                    flag = false;
                    break;
                }
            }
            if (flag) {
                if (best < 0 || (words[best].length() > words[i].length())) best = i;
            }
        }
        return words[best];
    }


    public static String shortestCompletingWord_Another(String licensePlate, String[] words) {
        int[] cnt = getCnt(licensePlate);
        String ans = null;
        for (String s : words) {
            int[] cur = getCnt(s);
            boolean ok = true;
            for (int i = 0; i < 26 && ok; i++) {
                if (cnt[i] > cur[i]) ok = false;
            }
            if (ok && (ans == null || ans.length() > s.length())) ans = s;
        }
        return ans;
    }
    public  static int[] getCnt(String s) {
        int[] cnt = new int[26];
        for (char c : s.toCharArray()) {
            if (Character.isLetter(c)) cnt[Character.toLowerCase(c) - 'a']++;
        }
        return cnt;
    }


    public static void main(String[] args) {
        String licensePlate = "1s3 PSt";
        String[] words = {"step", "steps", "stripe", "stepple"};
        System.out.println(shortestCompletingWord(licensePlate, words));
        System.out.println(shortestCompletingWord_Another(licensePlate, words));
    }

}
