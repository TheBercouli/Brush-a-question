package review.recursion;

/**
 * @BelongsProject: Brush-a-question
 * @BelongsPackage: review.recursion
 * @Author: alicebercouli_i
 * @Date: 2022/10/28 11:04
 * @Description: 汉诺塔问题 s
 */
public class HanNota {

    public void hanNota(int n, char from, char dependOn, char to) {
        if (n == 1) {
            move(1, from, to);
        } else { // 将n个盘子从f移到t 等同于以下三个步骤 （n指从上倒下的方向即最大的盘子是第n个盘子）
            hanNota(n - 1, from, to, dependOn); // 将 n - 1个盘子 从f借助t移到d
            move(n, from, to); // 将 第n个盘子 从f移到c
            hanNota(n - 1, dependOn, from, to); // 将 n - 1个盘子 从d借助f移到t
        }
    }

    private void move(int i, char from, char to) {
        System.out.println("第" + i + "个盘子从" + from + "====>" + to);
    }

    public static void main(String[] args) {
        HanNota hanNota = new HanNota();
        hanNota.hanNota(3,'F','D', 'T');
    }

}
