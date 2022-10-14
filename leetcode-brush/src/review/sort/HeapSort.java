package review.sort;

import java.util.Arrays;

import review.SimpleUtil;

/**
 * @BelongsProject: Brush-a-question
 * @BelongsPackage: review.sort
 * @Author: alicebercouli_i
 * @Date: 2022/10/13 9:53 AM
 * @Description: 堆排序
 */
public class HeapSort {

    public static void main(String[] args) {

        int[] a = {39,33,10,8,66,23,67,9,15,100,70,22,3,6,54};
        System.out.println(Arrays.toString(a));
        HeapSort heapSort = new HeapSort();
        heapSort.heapSort(a);
        System.out.println(Arrays.toString(a));

    }


    public void heapSort(int[] a) {
        if (a.length <= 1 || a == null)
            return;
        // 创建大堆
        buildMaxHeap(a);
        // 大堆创建后已经有一个最大的元素在堆顶即位置0
        for (int i = a.length - 1; i >= 1; i--)  {
            SimpleUtil.swapElements(a,0,i); // 每次交换一次就沉淀一个最大的元素放在数组最后即此处i的位置
             maxHeap(a,i,0);
        }
    }

    private void buildMaxHeap(int[] a) {
         int half = (a.length - 1) / 2;
         for (int i = half;  i >= 0; i--) {
             // 只需遍历总长度的一般就可以获取到所有元素，根：i，左：2i+1，右：2i+2
             maxHeap(a, a.length -1, i);
         }
    }
    // length 表示用于构造大堆的数组长度元素的个数；i表示当前根节点
    private void maxHeap(int[] a, int length, int i) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i; // 最大值暂时设置为i
        if (left < length && a[left] > a[i]) {
            largest = left;
        }
        if (right < length && a[right] > a[largest]) {
            largest = right;
        }
        if (i != largest) {
            SimpleUtil.swapElements(a, i, largest);
            maxHeap(a, length, largest);
        }
    }
}
