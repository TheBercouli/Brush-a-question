package review.divideConquer;

import java.util.Arrays;

import review.SimpleUtil;

/**
 * @BelongsProject: Brush-a-question
 * @BelongsPackage: review.divideConquer
 * @Author: alicebercouli_i
 * @Date: 2022/11/3 16:31
 * @Description: 分治-比赛日程安排 一个矩阵 行为天数 列为队名
 */
public class SportSchedule {
    public static void scheduleTable(int[][] table, int n) {
        if (n == 1) {
            table[0][0] = 1;
        }else {
            // 填充左上区域
            int m = n / 2;
            scheduleTable(table, m);
            // 填充右上区域
            for (int i = 0; i < m; i++) {
                for (int j = m; j < n; j++) {
                    table[i][j] = table[i][j - m] + m;
                }
            }
            // 填充左下区域
            for (int i = m; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    table[i][j] = table[i - m][j] + m;
                }
            }
            // 填充右下区域
            for (int i = m; i < n; i++) {
                for (int j = m; j < n; j++) {
                    table[i][j] = table[i - m][j - m];
                }
            }
        }
    }

    public static void main(String[] args) {
        int[][] table = new int[8][8];
        int n = 8;
        SportSchedule.scheduleTable(table, n);
        SimpleUtil.PrintTwoDimensionalArray(table);
    }
}
