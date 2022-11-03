package review.recursion;

/**
 * @BelongsProject: Brush-a-question
 * @BelongsPackage: review.recursion
 * @Author: alicebercouli_i
 * @Date: 2022/10/28 16:05
 * @Description: 欧几理德算法
 *
 *       （m>n）m和n的最大公约数= n和m%n的最大公约数
 */
public class Gcd {

    // gcd方法的含义：取n和m%n的最大公约数
    public static int gcd(int m ,int n) {
        if (n == 0) {
            return m;
        }else {
            return gcd(n, m%n);
        }
    }

    public static void main(String[] args) {
        System.out.println(gcd(36, 24));
    }
}
