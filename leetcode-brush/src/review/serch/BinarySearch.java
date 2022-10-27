package review.serch;

import review.sort.BasicSort;

/**
 * @BelongsProject: Brush-a-question
 * @BelongsPackage: review.serch
 * @Author: alicebercouli_i
 * @Date: 2022/10/26 5:07 PM
 * @Description:
 */
public class BinarySearch {

    public static int binarySearchRecursion(int element, int[] array, int start, int end) {
        if (start > end) {
            return -1;
        }
        int middle = (start + end) >> 1;
        if (array[middle] == element) {
            return middle;
        }
        if (array[middle] < element) {
            // 向右查找
            return binarySearchRecursion(element, array, middle + 1, end);
        }
        if (array[middle] > element) {
            // 向左查找
            return binarySearchRecursion(element,array,start,middle - 1);
        }
        return -1;
    }

    public static int binarySearchNotRecursion(int element, int[] array) {
        int start = 0;
        int end = array.length - 1;
        while (start < end) {
            int middle = (start + end) >> 1;
            if (array[middle] < element) {
                start = middle + 1;
            }else if (array[middle] > element) {
                end = middle - 1;
            }else {
                return middle;
            }
        }
        return -1;
    }





    public static void main(String[] args) {
        int[] a = {39,33,10,8,66,23,67,9,15,100,70,22,3 ,6,54};
        BasicSort basicSort = new BasicSort();
        basicSort.basicSort(a);
        int result = BinarySearch.binarySearchRecursion(6,a,0, a.length - 1);
        System.out.println(result);
        System.out.println(BinarySearch.binarySearchNotRecursion(6,a));

    }


}
