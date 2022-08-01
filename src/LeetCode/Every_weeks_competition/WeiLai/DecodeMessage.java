package LeetCode.Every_weeks_competition.WeiLai;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DecodeMessage {

    private char[] apl = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    public String decodeMessage(String key, String message) {
        Map<Character, Integer> map = new HashMap<>();
        int cnt = 0;
        for (char c : key.toCharArray()) {
            if (c == ' ') {
                continue;
            }
            if (!map.containsKey(c)){
                map.put(c, cnt);
                cnt++;
            }

        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char c = message.charAt(i);
            if (c == ' ') {
                sb.append(" ");
            } else {
                sb.append(apl[map.get(c)]);
            }
        }
        return sb.toString();
    }


    public static void main(String[] args) {
        DecodeMessage t = new DecodeMessage();
        System.out.println(t.apl.length);
        System.out.println(t.decodeMessage("the quick brown fox jumps over the lazy dog", "vkbs bs t suepuv"));
    }
}
