package LeetCode.Practise_Everyday.Date_2022_08;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Lil_boat
 * @Date: 2022/8/16 21:05
 * @Title: 设计有序流
 * @Description: 有 n 个 (id, value) 对，其中 id 是 1 到 n 之间的一个整数，value 是一个字符串。不存在 id 相同的两个 (id, value) 对。
 *
 * 设计一个流，以 任意 顺序获取 n 个 (id, value) 对，并在多次调用时 按 id 递增的顺序 返回一些值。
 *
 * 实现 OrderedStream 类：
 *
 * OrderedStream(int n) 构造一个能接收 n 个值的流，并将当前指针 ptr 设为 1 。
 * String[] insert(int id, String value) 向流中存储新的 (id, value) 对。存储后：
 * 如果流存储有 id = ptr 的 (id, value) 对，则找出从 id = ptr 开始的 最长 id 连续递增序列 ，并 按顺序 返回与这些 id 关联的值的列表。然后，将 ptr 更新为最后那个  id + 1 。
 * 否则，返回一个空列表。
 *
 * 链接：https://leetcode.cn/problems/design-an-ordered-stream
 */
public class D16_OrderedStream_1656 {

    String[] tmp;
    int ptr = 1;
    public D16_OrderedStream_1656(int n) {
        tmp = new String[n + 1];
    }

    public List<String> insert(int idKey, String value) {
        tmp[idKey] = value;
        List<String> ans = new ArrayList<>();
        while(ptr < tmp.length){
            if (tmp[ptr] == null){
                break;
            }else{
                ans.add(tmp[ptr]);
                ptr++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {

    }

}
