package ybs.swordRefers2;

import java.math.BigInteger;

/**
 * @BelongsProject: Brush-a-question
 * @BelongsPackage: ybs.swordRefers2
 * @Author: alicebercouli_i
 * @Date: 2022/10/9 2:29 PM
 * @Description: 零碎题解
 */
public class Main {

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
    public static BigInteger solution(int n) {
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

}
