package ybs.shuati;

import review.SimpleUtil;

import java.util.Arrays;
import java.util.Random;

public class Solution75and88and215 {

    /**
     * 给定一个包含红色、白色和蓝色、共n 个元素的数组nums，原地对它们进行排序，使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
     * 我们使用整数 0、1 和 2 分别表示红色、白色和蓝色。
     * 必须在不使用库内置的 sort 函数的情况下解决这个问题。
     * 输入：nums = [2,0,2,1,1,0]
     * 输出：[0,0,1,1,2,2]
     */
    public void sortColors75v1(int[] nums) {
        // 计数排序
        int[] sort = new int[3];
        for (int i : nums) {
            sort[i]++;
        }
        int index = 0;
        for (int i = 0; i < sort.length; i++) {
            for (int j = sort[i]; j>0; j--) {
                nums[index++] = i;
            }
        }
    }
    public void sortColors75v2(int[] nums) {
        // 简单三路快排
        int zero = -1;
        int two = nums.length;
        for (int i = 0; i < two; ) {
            if (nums[i] == 1)
                i++;
            else if (nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[two - 1];
                nums[--two] = temp;
            }else {
                assert nums[i] == 0;
                int temp = nums[i];
                nums[i++] = nums[zero + 1];
                nums[++zero] = temp;
            }
        }
    }

    /**
     * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
     * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
     * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
     输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
     输出：[1,2,2,3,5,6]
     解释：需要合并 [1,2,3] 和 [2,5,6] 。
     合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
     */
    public void merge88v1(int[] nums1, int m, int[] nums2, int n) {
        int l = 0;
        int r = 0;
        int index = 0; // 临时数组下标
        int cursor = 0; // 临时数组最终复制给原数组用
        int[] temp = new int[m + n];
        while (l < m && r < n) {
            if (nums1[l] <= nums2[r]) {
                temp[index++] = nums1[l++];
            }else {
                temp[index++] = nums2[r++];
            }
        }
        while (l < m) {
            temp[index++] = nums1[l++];
        }
        while (r < n) {
            temp[index++] = nums2[r++];
        }
        while (cursor < (m + n)) {
            nums1[cursor] = temp[cursor++];
        }
    }
    // 不需要辅助数组，思路是从后往前方
    public  void merge88v2(int[] nums1, int m, int[] nums2, int n) {
        int p = m - 1;
        int q = n - 1;
        int index = m + n - 1;
        while (p >= 0 && q >= 0) {
            nums1[index--] = nums1[p] < nums2[q] ? nums2[q--] : nums1[p--];
        }
        while (q >= 0) {
            nums1[index--] = nums2[q--]; //?
        }
    }

    //public static void main(String[] args) {
    //    int[] nums1 = {4,5,7,0,0,0};
    //    int m = 3;
    //    int[] nums2 = {2,5,6};
    //    int n = 3;
    //    merge88v2(nums1,m,nums2,n);
    //    Arrays.toString(nums1);
    //
    //}


    /**
     * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
     * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
     * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
     输入: [3,2,3,1,2,4,5,5,6], k = 4
     输出: 4
     */
    public int findKthLargest(int[] nums, int k) {
        int low = 0;
        int high = nums.length - 1;
        int t = nums.length - k;
        int pivot = getPosition(nums, low, high);
        while (pivot != t) {
            if (pivot < t)
                low = pivot + 1;
            else
                high = pivot - 1;
            pivot = getPosition(nums, low, high);
        }
        return nums[pivot];
    }

    private int getPosition(int[] nums, int low, int high) {
        int i = new Random().nextInt(high - low + 1) + low;
        swapElements(nums, low, i);
        int temp = nums[low];
        while (low < high) {
            while (low < high && nums[high] >= temp) {
                high--;
            }
            nums[low] = nums[high];
            while (low < high && nums[low] <= temp) {
                low++;
            }
            nums[high] = nums[low];
        }
        nums[low] = temp;
        return low;
    }

    public static void swapElements(int[] a, int x, int y) {
        int temp = a[x];
        a[x] = a[y];
        a[y] = temp;
    }
}
