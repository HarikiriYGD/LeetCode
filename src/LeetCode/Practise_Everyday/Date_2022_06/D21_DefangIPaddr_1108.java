package LeetCode.Practise_Everyday.Date_2022_06;
/**
 * @Auther: Lil_boat
 * @Date: 9:05 2022/6/21
 * @Tile: IP 地址无效化
 * @Description: 给你一个有效的 IPv4 地址 address，返回这个 IP 地址的无效化版本。
 * 所谓无效化 IP 地址，其实就是用 "[.]" 代替了每个 "."。
 */
public class D21_DefangIPaddr_1108 {

    public String defangIPaddr(String address){
        return address.replace(".","[.]");
    }

    public static void main(String[] args) {
        D21_DefangIPaddr_1108 t = new D21_DefangIPaddr_1108();
        System.out.println(t.defangIPaddr("1.1.1.1"));
    }

}
