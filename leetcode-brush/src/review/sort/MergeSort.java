package review.sort;

import java.util.Arrays;

/**
 * @BelongsProject: Brush-a-question
 * @BelongsPackage: review.sort
 * @Author: alicebercouli_i
 * @Date: 2022/10/14 2:44 PM
 * @Description:
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] a = {39,33,10,8,66,23,67,9,15,100,70,22,3,6,54};
        System.out.println(Arrays.toString(a));
        //MergeSort mergeSort = new MergeSort();
        //mergeSort.mergeSort(a,0,a.length - 1);
        System.out.println(Arrays.toString(mergeSort(a)));
    }

    public void mergeSort(int[] a, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(a, left, middle);
            mergeSort(a,middle + 1, right);
            merge(a, left, middle, right);
        }
    }

    private static void merge(int[] a, int left, int middle, int right) {
        int[] tempArray = new int[a.length]; // 辅助数组
        int rightStart = middle + 1;
        int cursor = left;
        int temp = left;
        while (left <= middle && rightStart <= right) {
            if (a[left] <= a[rightStart]) {
                tempArray[cursor++] = a[left++];
            }else {
                tempArray[cursor++] = a[rightStart++];
            }
        }
        // 如果左侧小数组还有数据没有存入，说明这些数据已经比右侧小数组的所有数值都大，直接按顺序存入临时数组即可。下同
        while (left <= middle) {
            tempArray[cursor++] = a[left++];
        }
        while (rightStart <= right) {
            tempArray[cursor++] = a[rightStart++];
        }
        while (temp <= right) {
            a[temp] = tempArray[temp++];
        }
    }

    public static int[] mergeSort(int[] arr) {
        int n = arr.length;
        // 子数组的大小分别为1，2，4，8...
        // 刚开始合并的数组大小是1，接着是2，接着4....
        for (int i = 1; i < n; i += i) {
            //进行数组进行划分
            int left = 0;
            int mid = left + i - 1;
            int right = mid + i;
            //进行合并，对数组大小为 i 的数组进行两两合并
            while (right < n) {
                // 合并函数和递归式的合并函数一样
                merge(arr, left, mid, right);
                left = right + 1;
                mid = left + i - 1;
                right = mid + i;
            }
            // 还有一些被遗漏的数组没合并，千万别忘了
            // 因为不可能每个字数组的大小都刚好为 i
            if (left < n && mid < n) {
                merge(arr, left, mid, n - 1);
            }
        }
        return arr;
    }



}
