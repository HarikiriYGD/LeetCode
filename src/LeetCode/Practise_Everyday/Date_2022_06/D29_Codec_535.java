package LeetCode.Practise_Everyday.Date_2022_06;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @Auther: Lil_boat
 * @Date: 9:27 2022/6/29
 * @Tile: TinyURL 的加密与解密
 * @Description: TinyURL 是一种 URL 简化服务，
 * 比如：当你输入一个 URL https://leetcode.com/problems/design-tinyurl 时，
 * 它将返回一个简化的URL http://tinyurl.com/4e9iAk 。
 * 请你设计一个类来加密与解密 TinyURL 。
 * 加密和解密算法如何设计和运作是没有限制的，你只需要保证一个 URL 可以被加密成一个 TinyURL ，并且这个 TinyURL 可以用解密方法恢复成原本的 URL 。
 * <p>
 * 实现 Solution 类：
 * Solution() 初始化 TinyURL 系统对象。
 * String encode(String longUrl) 返回 longUrl 对应的 TinyURL 。
 * String decode(String shortUrl) 返回 shortUrl 原本的 URL 。题目数据保证给定的 shortUrl 是由同一个系统对象加密的。
 *  
 * 链接：https://leetcode.cn/problems/encode-and-decode-tinyurl
 */
public class D29_Codec_535 {

    /*
         tinyurl 格式： http://tinyurl.com/ + 6位随机码(4e9iAK) 使用哈希表map,
         加密的时候生成随机 tinyurl, 若 tinyurl 在 map中不存在, 则以 tinyurl 作为 key, url 作为value;
         解密时, 通过 tinyurl 作为 key, 即可找到作为 value 的 url.
     */

    HashMap<String, String> map = new HashMap<>();

    static final String INDEX = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final String TINYURL_PREFIX = "http://tinyurl.com/";

    // Encodes a URL to a shortened URL.
    public String encode(String longUrl) {
        char[] cs = new char[6];
        for (; ; ) {
            for (int i = 0; i < 6; i++) {
                cs[i] = INDEX.charAt((int) Math.random() * 62);
            }
            String shortUrl = TINYURL_PREFIX + new String(cs);
            if (!map.containsKey(shortUrl)) {
                map.put(shortUrl, longUrl);
                return shortUrl;
            }
        }
    }

    // Decodes a shortened URL to its original URL.
    public String decode(String shortUrl) {
        return map.get(shortUrl);
    }


    public static void main(String[] args) {
        D29_Codec_535 t = new D29_Codec_535();
        t.encode("https://leetcode.com/problems/design-tinyurl");
    }

}
