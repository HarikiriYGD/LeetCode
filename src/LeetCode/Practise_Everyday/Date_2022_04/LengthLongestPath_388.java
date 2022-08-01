package LeetCode.Practise_Everyday.Date_2022_04;

import java.util.Arrays;

/**
 * @Auther: Lil_boat
 * @Date: 9:59 2022/4/20
 * @Tile: 文件的最长绝对路径
 * @Description: 在文本格式中，如下所示(⟶表示制表符)：
 * <p>
 * dir
 * ⟶ subdir1
 * ⟶ ⟶ file1.ext
 * ⟶ ⟶ subsubdir1
 * ⟶ subdir2
 * ⟶ ⟶ subsubdir2
 * ⟶ ⟶ ⟶ file2.ext
 * 如果是代码表示，上面的文件系统可以写为 "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext" 。
 * '\n' 和 '\t' 分别是换行符和制表符。
 * <p>
 * 文件系统中的每个文件和文件夹都有一个唯一的 绝对路径 ，即必须打开才能到达文件/目录所在位置的目录顺序，
 * 所有路径用 '/' 连接。上面例子中，指向 file2.ext 的 绝对路径 是 "dir/subdir2/subsubdir2/file2.ext" 。
 * 每个目录名由字母、数字和/或空格组成，每个文件名遵循 name.extension 的格式，其中 name 和 extension由字母、数字和/或空格组成。
 * <p>
 * 给定一个以上述格式表示文件系统的字符串 input ，返回文件系统中 指向 文件 的 最长绝对路径 的长度 。 如果系统中没有文件，返回 0。
 */
public class LengthLongestPath_388 {

    /*
        步骤1：利用String的split方法分割元素。
        步骤2：在第一步所得的元素基础上从左向右遍历，用一个pahLens数组记录所遍历的元素的路径长度。
               其中pahLens[ i ]表示level为i的元素的路径长度（存在多个同level元素时，取最后面的元素）。这就是动态规划的思想。时间O（n）。
     */
    public static int lengthLongestPath(String input) {
        int ans = 0;
        String[] words = input.split("\n");
        int[] pathLens = new int[words.length + 1]; // pathLens[i]存放地i层次的最后面的元素的路径长度
        pathLens[0] = -1; // 层次最少是1，为了统一dp操作（具体看下面循环体），取pathLens[0]=-1
        //自左向右，dp
        for (String word : words) {
            int level = word.lastIndexOf('\t') + 1 + 1; // 层次计算
            int nameLen = word.length() - (level - 1); // 计算名字长度
            // word的父文件夹必定目前是level-1层次的最后一个，因此pathLens[level-1]就是父文件夹路径长度
            // 这个word必然是目前本层次的最后一个，因此需要刷新pathLens[level],+1是因为要加一个'\'
            pathLens[level] = pathLens[level - 1] + 1 + nameLen;
            // 如果是文件，还需要用路径长度刷新ans
            if (word.contains(".")) ans = Math.max(ans, pathLens[level]);
        }
        return ans;
    }

    public static void main(String[] args) {
        String input = "dir\n\tsubdir1\n\t\tfile1.ext\n\t\tsubsubdir1\n\tsubdir2\n\t\tsubsubdir2\n\t\t\tfile2.ext";
        System.out.println(lengthLongestPath(input));
    }

}
