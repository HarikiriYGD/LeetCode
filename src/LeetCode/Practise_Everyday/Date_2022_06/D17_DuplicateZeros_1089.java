package LeetCode.Practise_Everyday.Date_2022_06;
/**
 * @Auther: Lil_boat
 * @Date: 9:52 2022/6/17
 * @Tile: 复写零
 * @Description: 给你一个长度固定的整数数组 arr，请你将该数组中出现的每个零都复写一遍，并将其余的元素向右平移。
 * 注意：请不要在超过该数组长度的位置写入元素。
 * 要求：请对输入的数组 就地 进行上述修改，不要从函数返回任何东西。
 *
 * 链接：https://leetcode.cn/problems/duplicate-zeros
 */
public class D17_DuplicateZeros_1089 {

    public void duplicateZeros(int[] arr){
        /**
         * 双指针的方式
         */
        /*int n = arr.length, i = 0, j = 0;
        while (j <n){
            if (arr[i] == 0)j++;
            i++;j++;
        }
        i--;j--;
        while (i >= 0){
            if (j < n)arr[j] = arr[i];
            if (arr[i] == 0 && --j >= 0)arr[j] = 0;
            i--;j--;
        }*/
        /**
         * 暴力的方式
         */
        for (int i = 0; i < arr.length; i++) {
          if (arr[i] == 0){
              for (int j = arr.length - 1; j > i; j--){
                  arr[j] = arr[j -1];
              }
              i++;
          }
        }
    }


    public static void main(String[] args) {
        D17_DuplicateZeros_1089 t = new D17_DuplicateZeros_1089();
        t.duplicateZeros(new int[]{1,0,2,3,0,4,5,0});
    }

}
