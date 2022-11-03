package review;

/**
 * @BelongsProject: Brush-a-question
 * @BelongsPackage: PACKAGE_NAME
 * @Author: alicebercouli_i
 * @Date: 2022/10/13 11:10 AM
 * @Description:
 */
public class SimpleUtil {

    // 交换数组中下表为x与y位置的值
    public static void swapElements(int[] a, int x, int y) {
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }
    // true为max false为min
    public static int getMaxOrMinElements(int[] a, boolean flag) {
        int max = 0;
        int min = 0;
        for (int i = 0; i < a.length; i++) {
            if (max < a[i])
                max = a[i];
            if (min > a[i])
                min = a[i];
        }
        return flag ? max : min;
    }
}
