package review.dynamicProgramming;

import review.SimpleUtil;

/**
 * @BelongsProject: Brush-a-question
 * @BelongsPackage: review.dynamicProgramming
 * @Author: alicebercouli_i
 * @Date: 2022/11/4 15:36
 * @Description: 动态规划-Longest Common Subsequence 最长公共子序列（注意不是字串）
 */
public class LCS {

    public int findLCS(String A, String B) {
        int n = A.length();
        int m = B.length();
        char[] a = A.toCharArray();
        char[] b = B.toCharArray();
        int[][] dp = new int[n][m];
        StringBuilder rs = new StringBuilder();
        for (int i = 0; i < n; i++) {
            if (a[i] == b[0]) {
                dp[i][0] = 1;
                for (int j = i + 1; j < n; j++) {
                    dp[j][0] = 1;
                }
                break; // 到此首列计算完毕
            }
        }
        for (int i = 0; i < m; i++) {
            if (a[0] == b[i]) {
                dp[0][i] = 1;
                rs.append(b[i]); // 拼接子序列
                for (int j = i + 1; j < m; j++) {
                    dp[0][j] = 1;
                }
                break; // 到此首行计算完毕
            }
        }
        int temp = -1;
        for (int i = 1; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (a[i] == b[j]) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    // 拼接子序列
                    if (dp[i][j] > temp) {
                        rs.append(b[j]);
                    }
                    temp = dp[i][j]; // 记录临时值用作拼接条件判断
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        SimpleUtil.PrintTwoDimensionalArray(dp);
        System.out.println(rs);
        return  dp[n - 1][m - 1];
    }

    public static void main(String[] args) {
        LCS lcs = new LCS();
        int findLcs = lcs.findLCS("android", "random");
        System.out.println(findLcs);
    }
}
