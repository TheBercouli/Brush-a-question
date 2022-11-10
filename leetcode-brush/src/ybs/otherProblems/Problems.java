package ybs.otherProblems;

import java.math.BigInteger;

/**
 * @BelongsProject: Brush-a-question
 * @BelongsPackage: ybs.swordRefers2
 * @Author: alicebercouli_i
 * @Date: 2022/10/9 2:29 PM
 * @Description: 零碎题解
 */
public class Problems {

    /***
     * @author: alicebercouli_i
     * @date: 2022/10/10 2:46 PM
     * @description: 计算200以内正整数的阶乘【https://www.nowcoder.com/questionTerminal/155abf9586be4274a55455c9e721619e】
     * 单例解释：
     * 1    *    2    *    3
     * x    *    y
     * z = x * y = 1 * 2 = 2;
     * 此时让x保存z(已计算)的值，并且让y向后移动一位
     * x = z
     * y ++
     * 此时x = 2为2！的最终值，让它与下一轮的值3(y++)计算得到3！
     * 备注：数组下标移步法（独创）
     */
    public BigInteger solution(int n) {
        BigInteger x = BigInteger.valueOf(1);
        BigInteger y = BigInteger.valueOf(2);
        BigInteger z = BigInteger.ZERO;
        for(int i = 1; i < n ; i ++) {
            z = x.multiply(y);
            x = z;
            y = y.add(BigInteger.valueOf(1));
        }
        return x;
    }

    /***
     * @author: alicebercouli_i
     * @date: 2022/11/8 11:22
     * @description: 大数相乘
     */
    // 高位对调
    // 现实：456 * 123 个位是6和3，先从个位开始计算
    // 计算机中：存入字符数组是456不方便 "先从个位开始计算"，因此需要高位对调
    public void covertData(char data[], int len) {
        for (int i = 0; i < len / 2; i++) {
            data[i] += data[len - 1 - i];
            data[len - 1 - i] = (char) (data[i] - data[len - 1 - i]);
            data[i] = (char) (data[i] - data[len - 1 - i]);
        }
    }
    public void multiply(char a[], int aLen, char b[], int bLen) {
        int resultSize = aLen + bLen + 3; // 两个数字相乘位数不超过两个数的位数和+3
        int[] res = new int[resultSize]; // 乘积结果数组

        // 对其后逐位相乘
        for (int i = 0; i < bLen; i ++) {
            for (int j = 0; j < aLen; j ++) {
                res[i + j] += Integer.parseInt(String.valueOf(a[j])) * Integer.parseInt(String.valueOf(b[i]));
            }
        }
        // 进位处理
        int m = 0;
        for (m = 0; m < resultSize; m ++) {
            int carry = res[m] / 10;
            res[m] = res[m] % 10;
            if (carry > 0) {
                res[m + 1] += carry;
            }
        }
        // 找到最高位
        for (m = resultSize - 1; m >= 0;) {
            if (res[m] > 0)
                break;
            m--;
        }
        for (int n = 0; n <= m; n ++) {
            System.out.print(res[m - n]);
        }
        System.out.println();
    }





}
