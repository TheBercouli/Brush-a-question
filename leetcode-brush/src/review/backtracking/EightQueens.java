package review.backtracking;

/**
 * @BelongsProject: Brush-a-question
 * @BelongsPackage: review.backtracking
 * @Author: alicebercouli_i
 * @Date: 2022/11/7 15:09
 * @Description: 回溯法-八皇后问题
 */
public class EightQueens {

    public static int num = 0;
    public static final int MAX_QUEEN = 8;
    public static int[] cols = new int[MAX_QUEEN]; // 定义cols数组，表示8列棋子摆放的位置，下标表示皇后所在的行，值代表皇后所在列

    /***
     * @author: alicebercouli_i
     * @description:
       * @Param n:  填第n+1列的皇后
       * @return: void
     */
    public void getCount(int n) {
        boolean[] rows = new boolean[MAX_QUEEN]; // 记录每个放个是否可以被放皇后
        for (int m = 0; m < n; m++) { // m 表示行
            rows[cols[m]] = true; // cols[m]存放的就是皇后存在的位置，因此rows[cols[m]]表示这个地方已经存在true皇后了，不能再存在了
            int d = n - m; // 斜对角
            // 正斜
            if (cols[m] - d >= 0) {
                rows[cols[m] - d] = true;
            }
            // 反斜
            if (cols[m] + d <= (MAX_QUEEN - 1)) {
                rows[cols[m] + d] = true;
            }
        }
        // 此时确定了哪些位置不能放皇后
        for (int i = 0; i < MAX_QUEEN; i++) {
            if (rows[i]) {
                // 不能放
                continue;
            }
            cols[n] = i;
            // 下面可能还有合发位置
            if (n < MAX_QUEEN - 1) {
                // 回溯
                getCount(n + 1);
            } else {
                // 找到完整的一套方案
                num++;
                printQueen();
            }
        }
    }

    private void printQueen() {
        System.out.println("第" + num + "种方案");
        for (int i = 0; i < MAX_QUEEN; i++) {
            for (int j = 0; j < MAX_QUEEN; j++) {
                if (i == cols[j]) {
                    System.out.print("0 ");
                }else {
                    System.out.print("+ ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        EightQueens eightQueens = new EightQueens();
        eightQueens.getCount(0);
    }

}
