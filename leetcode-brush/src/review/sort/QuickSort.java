package review.sort;

import java.util.Arrays;
import java.util.Random;

import review.SimpleUtil;

/**
 * @BelongsProject: Brush-a-question
 * @BelongsPackage: review.sort
 * @Author: alicebercouli_i
 * @Date: 2022/10/13 11:41 AM
 * @Description: 快速排序-双向扫描
 *  1. 时间复杂度: 最佳-O(nlogn) 极端-O(n^2)  空间复杂度：不需要额外辅助空间
 *  2. 破坏稳定性：在某些时刻不同位置的元素值相同
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] a = {39,33,10,8,66,23,67,9,15,100,70,22,3,6,54};
        System.out.println(Arrays.toString(a));
        QuickSort quickSort = new QuickSort();
        quickSort.quick(a);
        System.out.println(Arrays.toString(a));

    }
    // 入口
    public void quick(int[] a) {
        if (a.length <= 0) {
            return;
        }
        // 随机生成基准元素（默认0为基准元素）
        int i = new Random().nextInt(a.length);
        SimpleUtil.swapElements(a, 0,i);
        // 排序
        quickSort(a, 0, a.length - 1);
    }

    // 迭代：此方法的含义是找到某个数在排序后的正确位置
    private void quickSort(int[] a, int low, int high) {
        if (low < high) {
            // 通过基数分段
            int correctPosition = getCorrectPosition(a, low, high);
            // 迭代
            quickSort(a,low,correctPosition - 1);
            quickSort(a,correctPosition + 1, high);
        }
    }

    // 获取正确位置
    private int getCorrectPosition(int[] a, int low, int high) {
        int temp = a[low]; // 基准元素
        while (low < high) {
            while (low < high && a[high] >= temp) {
                high --;
            }
            a[low] = a[high];
            while (low < high && a[low] <= temp) {
                low ++;
            }
            a[high] = a[low];
        }
        a[low] = temp; // 此时这个元素已经插入到排序后的正确位置
        return low;
    }
}
