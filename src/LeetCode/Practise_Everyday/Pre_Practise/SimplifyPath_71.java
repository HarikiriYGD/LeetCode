package LeetCode.Practise_Everyday.Pre_Practise;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Stack;

/**
 * @Auther: Lil_boat
 * @Date: 9:37 2022/1/6
 * @Description: 给你一个字符串 path ，表示指向某一文件或目录的 Unix 风格 绝对路径 （以 '/' 开头），请你将其转化为更加简洁的规范路径。
 * 在 Unix 风格的文件系统中，一个点（.）表示当前目录本身；此外，两个点 （..） 表示将目录切换到上一级（指向父目录）；两者都可以是复杂相对路径的组成部分。
 * 任意多个连续的斜杠（即，'//'）都被视为单个斜杠 '/' 。 对于此问题，任何其他格式的点（例如，'...'）均被视为文件/目录名称。
 * <p>
 * 请注意，返回的 规范路径 必须遵循下述格式：
 * 始终以斜杠 '/' 开头。
 * 两个目录名之间必须只有一个斜杠 '/' 。
 * 最后一个目录名（如果存在）不能 以 '/' 结尾。
 * 此外，路径仅包含从根目录到目标文件或目录的路径上的目录（即，不含 '.' 或 '..'）。
 * 返回简化后得到的 规范路径 。
 */
public class SimplifyPath_71 {
    public static String simplifyPath(String path) {
        // 将path以split分隔开
        String[] paths = path.split("/");
        Stack<String> stack = new Stack<>();
        for (String s : paths) {
            // 如果是两个点 表示返回到上一级目录
            if (s.equals("..") && !stack.isEmpty()) stack.pop();
            // 如果不是 . 或者 .. 或者 空串 都压入栈
            if (!s.equals(".") && !s.equals("..") && !s.equals("")) stack.push(s);
        }
        StringBuilder sb = new StringBuilder();
        // 遍历栈
        while (!stack.isEmpty()) {
            // 因为压入的是顺序的 所以弹出的时候要反序
            sb.insert(0, stack.pop());
            sb.insert(0, "/");
        }
        // 如果栈里面没有元素 直接返回 /
        if (sb.length() == 0) sb.append("/");
        return sb.toString();

        // 使用库函数的方法
        // return Paths.get(path).normalize().toString();
    }

    public static void main(String[] args) {
        String path = "/home//foo/";
        System.out.println(simplifyPath(path));
    }

}
