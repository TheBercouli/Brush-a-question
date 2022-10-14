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


}
