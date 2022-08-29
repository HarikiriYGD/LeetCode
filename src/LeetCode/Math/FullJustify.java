package LeetCode.Math;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author: Lil_boat
 * @Date: 2022/8/27 12:05
 * @Title: 文本左右对齐
 * @Description: 给定一个单词数组 words 和一个长度 maxWidth ，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 * 你应该使用 “贪心算法” 来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 * <p>
 * 注意:
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 * <p>
 * 链接：https://leetcode.cn/problems/text-justification
 */
public class FullJustify {


    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> ans = new ArrayList<>();
        int n = words.length;
        // 用于装载每行的单词数
        List<String> list = new ArrayList<>();
        for (int i = 0; i < n; ) {
            // 清除之前
            list.clear();
            list.add(words[i]);
            // 获取当前单词的长度
            int cur = words[i++].length();
            // 如果当前单词长度 + 空格长度没有大于当前行的长度，则继续往list中添加单词
            while (i < n && cur + 1 + words[i].length() <= maxWidth) {
                cur += 1 + words[i].length();
                list.add(words[i++]);
            }

            // 当前行为最后一行，特殊处理为左对齐
            if (i == n) {
                // 构造
                StringBuilder sb = new StringBuilder(list.get(0));
                // 继续添加剩余单词
                for (int j = 1; j < list.size(); j++) {
                    sb.append(" ").append(list.get(j));
                }
                while (sb.length() < maxWidth) {
                    sb.append(" ");
                }
                ans.add(sb.toString());
                break;
            }

            int cnt = list.size();
            // 如果只有一个单词
            if (cnt == 1) {
                String s = list.get(0);
                while (s.length() != maxWidth) {
                    s += " ";
                }
                ans.add(s);
                continue;
            }

            // 一般情况
            // wordwidth: 当前行单词总长度
            // spaceWidth: 当前行空格总长度
            // spaceItem: 往下取整后的单位空格长度
            int wordWidth = cur - (cnt - 1);
            int spaceWidth = maxWidth - wordWidth;
            int spaceItemWidth = spaceWidth / (cnt - 1);
            String spaceItem = "";
            for (int j = 0; j < spaceItemWidth; j++) {
                spaceItem += " ";
            }
            StringBuilder sb = new StringBuilder();
            for (int j = 0, sum = 0; j < cnt; j++) {
                // 获取当前单词
                String item = list.get(j);
                sb.append(item);
                if (j == cnt - 1) break;
                // 每个单词后跟的空格数
                sb.append(spaceItem);
                // 统计已经添入的空格数
                sum += spaceItemWidth;
                // 剩余的间隙数量
                int remain = cnt - j - 1 - 1;
                // 剩余间隙数量 * 最小单位空格长度 + 当前空格长度 < 单词总长度，则在当前间隙多补充一个空格
                if (remain * spaceItemWidth + sum < spaceWidth){
                    sb.append(" ");
                    sum++;
                }
            }
            ans.add(sb.toString());
        }
        return ans;
    }

}
