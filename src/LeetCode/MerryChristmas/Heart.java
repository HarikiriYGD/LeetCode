package LeetCode.MerryChristmas;

public class Heart {

    public static void main(String[] args) {
        double x, y, a;
        for (y = 1.5; y > -1.5; y -= 0.1) {
            for (x = -1.5; x < 1.5; x += 0.035) {
                a = x * x + y * y - 1;
                System.out.print("\033[31m");
                System.out.print(a * a * a - x * x * y * y * y <= 0.0 ? '*' : ' ');
            }
            System.out.println();
        }
    }

}
