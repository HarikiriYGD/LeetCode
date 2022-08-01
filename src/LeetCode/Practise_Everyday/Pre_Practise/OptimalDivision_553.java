package LeetCode.Practise_Everyday.Pre_Practise;

/**
 * @Auther: Lil_boat
 * @Date: 13:30 2022/2/27
 * @Description: 给定一组正整数，相邻的整数之间将会进行浮点除法操作。例如， [2,3,4] -> 2 / 3 / 4 。
 * 但是，你可以在任意位置添加任意数目的括号，来改变算数的优先级。你需要找出怎么添加括号，
 * 才能得到最大的结果，并且返回相应的字符串格式的表达式。你的表达式不应该含有冗余的括号。
 */
public class OptimalDivision_553 {

    /*
        本以为考的是算法，实际上考的是字符处理...用脑筋急转弯包装了一层而已，
        如果想要结果最大，那么实际就是要第二个数要最小，那么第一个数除以最小的第二个数，
        才会获得最大结果，第二个数一直往后除那就是最小值
     */
    public static String optimalDivision(int[] nums) {
        int n = nums.length;
        if (n == 1) return String.valueOf(nums[0]);
        String[] arr = new String[n];
        for (int i = 0; i < n; i++) {
            arr[i] = String.valueOf(nums[i]);
        }
        if (n == 2) return String.join("/", arr);
        arr[1] = "(" + arr[1];
        arr[n - 1] = arr[n - 1] + ")";
        return String.join("/", arr);
    }

    public static void main(String[] args) {
        int[] nums = {1000,100,10,2};
        System.out.println(optimalDivision(nums));
    }

}
