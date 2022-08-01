package LeetCode.Practise_Everyday.Date_2022_03;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auther: Lil_boat
 * @Date: 10:03 2022/3/18
 * @Description: 你的任务是为一个很受欢迎的银行设计一款程序，以自动化执行所有传入的交易（转账，存款和取款）。
 * 银行共有 n 个账户，编号从 1 到 n 。每个账号的初始余额存储在一个下标从 0 开始的整数数组 balance 中，其中第 (i + 1) 个账户的初始余额是 balance[i] 。
 * <p>
 * 请你执行所有 有效的 交易。如果满足下面全部条件，则交易 有效 ：
 * <p>
 * 指定的账户数量在 1 和 n 之间，且
 * 取款或者转账需要的钱的总数 小于或者等于 账户余额。
 * 实现 Bank 类：
 * <p>
 * Bank(long[] balance) 使用下标从 0 开始的整数数组 balance 初始化该对象。
 * boolean transfer(int account1, int account2, long money) 从编号为 account1 的账户向编号为 account2 的账户转帐 money 美元。
 * 如果交易成功，返回 true ，否则，返回 false 。
 * boolean deposit(int account, long money) 向编号为 account 的账户存款 money 美元。如果交易成功，返回 true ；否则，返回 false 。
 * boolean withdraw(int account, long money) 从编号为 account 的账户取款 money 美元。如果交易成功，返回 true ；否则，返回 false 。
 */
public class Bank_2043 {

    Map<Integer, Long> map = new HashMap<>();

    public Bank_2043(long[] balance) {
        for (int i = 0; i < balance.length; i++) {
            // 存储每个账户的账号和余额
            map.put(i + 1, balance[i]);
        }
    }

    // 转账
    public boolean transfer(int account1, int account2, long money) {
        // 用户不存在
        if (!map.containsKey(account1) || !map.containsKey(account2)) return false;
        long curMoney = map.get(account1);
        // 余额不足
        if (curMoney < money) return false;
        else {
            map.put(account1, curMoney - money);
            map.put(account2, map.get(account2) + money);
        }
        return true;
    }

    // 存款
    public boolean deposit(int account, long money) {
        if (!map.containsKey(account)) return false;
        map.put(account, map.get(account) + money);
        return true;
    }

    // 取款
    public boolean withdraw(int account, long money) {
        if (!map.containsKey(account)) return false;
        long curMoney = map.get(account);
        if (curMoney < money) return false;
        map.put(account, map.get(account) - money);
        return true;
    }

}
