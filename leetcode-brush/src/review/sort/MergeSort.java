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
        MergeSort mergeSort = new MergeSort();
        mergeSort.mergeSort(a,0,a.length - 1);
        System.out.println(Arrays.toString(a));
    }

    public void mergeSort(int[] a, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            mergeSort(a, left, middle);
            mergeSort(a,middle + 1, right);
            merge(a, left, middle, right);
        }
    }

    private void merge(int[] a, int left, int middle, int right) {
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



}
