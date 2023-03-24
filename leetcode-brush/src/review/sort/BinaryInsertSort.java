package review.sort;

import java.util.Arrays;

public class BinaryInsertSort {


    public static void main(String[] args) {
        int[] a = {39,33,10,8,66,23,67,9,15,100,70,22,3,6,54};
        System.out.println(Arrays.toString(a));
        //QuickSort quickSort = new QuickSort();
        //quickSort.quick(a);
        binaryInsertSort(a);
        System.out.println(Arrays.toString(a));
    }

    public static void binaryInsertSort(int[] arr) {
        for(int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            int l = 0;
            int r = i - 1;
            int mid = 0;
            while (l <= r) {
                mid = (l + r) / 2;
                if (temp > arr[mid]) {
                    l = mid + 1;
                }else if (temp < arr[mid]) {
                    r = mid - 1;
                }
            }
            // 腾出位置
            for (int j = i - 1; j >= l; j--) {
                arr[j + 1] = arr[j];
            }
            // 待插入元素大于所有有序区元素
            if (l != i) {
                arr[l] = temp;
            }
        }
    }
}
