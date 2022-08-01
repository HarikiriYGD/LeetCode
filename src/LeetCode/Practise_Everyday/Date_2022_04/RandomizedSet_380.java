package LeetCode.Practise_Everyday.Date_2022_04;

import java.util.*;
/**
 * @Auther: Lil_boat
 * @Date: 9:36 2022/4/13
 * @Tile: O(1) 时间插入、删除和获取随机元素
 * @Description: 实现RandomizedSet 类：
 *
 * RandomizedSet() 初始化 RandomizedSet 对象
 * bool insert(int val) 当元素 val 不存在时，向集合中插入该项，并返回 true ；否则，返回 false 。
 * bool remove(int val) 当元素 val 存在时，从集合中移除该项，并返回 true ；否则，返回 false 。
 * int getRandom() 随机返回现有集合中的一项（测试用例保证调用此方法时集合中至少存在一个元素）。每个元素应该有 相同的概率 被返回。
 * 你必须实现类的所有函数，并满足每个函数的 平均 时间复杂度为 O(1) 。
 */
public class RandomizedSet_380 {

    List<Integer> list = new ArrayList<>();
    Map<Integer, Integer> map = new HashMap<>();
    Random random = new Random();

    public RandomizedSet_380() {

    }

    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        list.add(val);
        map.put(val, list.size() - 1);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        // 获取删除元素的下标
        int index = map.get(val);
        // 获取最后一个元素
        int last = list.get(list.size() - 1);
        // 将最后一个元素插入index的位置
        list.set(index, last);
        // 删除最后一个元素
        list.remove(list.size() - 1);
        // 重新放入map
        map.put(last, index);
        // 从map中删除
        map.remove(val);
        return true;
    }

    public int getRandom() {
        return list.get(random.nextInt(list.size()));
    }

}
