package review.exhaustion;

/**
 * @BelongsProject: Brush-a-question
 * @BelongsPackage: review.exhaustion
 * @Author: alicebercouli_i
 * @Date: 2022/11/3 11:08
 * @Description: 穷举法-泊松分酒
 */
public class PoissonShareWine {
    private int b1 = 12;
    private int b2 = 8;
    private int b3 = 5;
    private int m =6; // 目标

    // 假设一开始12 0 0
    public void backBottle(int bb1, int bb2, int bb3) { // bb1 bb2 bb3 表示当前杯中留有酒量
        System.out.println("当前酒量:bb1=" + bb1 + ", bb2=" + bb2 + ", bb3=" + bb3);
        if (bb1 == m || bb2 == m || bb3 == m) {
            System.out.println("find the bottle!");
            return;
        }
        if (bb2 != 0 && bb3 != b3) { // 瓶子2≠0 说明bb2有酒，瓶子3≠b3，说明瓶子3还没有满
            if (bb2 + bb3 <= b3) { // 将瓶子2里的酒全部倒入瓶子3仍然没有倒满或者刚好倒满
                backBottle(bb1, 0, bb2 + bb3);
            }else { // 将瓶子2里的酒倒入瓶子3时瓶子3溢出，瓶子2仍有剩余
                backBottle(bb1, bb2 - (b3 - bb3), b3); // 换个角度理解 如果第一次发生倒酒，那么bb3 = 0, bb3 + 倒出 = b3, bb2中剩余酒为bb2 - (b3 -bb3)
            }
        }else if (bb3 == b3) {
            // 瓶子3满了向瓶子1倒
            if (bb3 + bb1 <= b1) { // 将瓶子3倒入瓶子1中后，瓶子1没满
                backBottle(bb1 + bb3, bb2, 0);
            }else { // 瓶子3倒入瓶子1后，瓶子1满溢，瓶子3仍有剩余
                backBottle(b1, bb2, bb3 - (b1 - bb1));
            }
        }else if (bb2 == 0) {
            // 从瓶子1往瓶子2倒酒
            if (bb1 >= b2) {
                backBottle(bb1 - b2, b2, bb3);
            }else {
                backBottle(0, bb1, bb3);
            }
        }
    }

    public static void main(String[] args) {
        PoissonShareWine poissonShareWine = new PoissonShareWine();
        poissonShareWine.backBottle(12, 0, 0);
    }
}
