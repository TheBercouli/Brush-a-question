package ybs.shuati;

/**
 * 双索引技术-对撞指针系列
 */
public class Solution167and125and344and11 {
    /**
     * 给你一个下标从 1 开始的整数数组numbers ，该数组已按 非递减顺序排列 ，请你从数组中找出满足相加之和等于目标数 target 的两个数。如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
     * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
     * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
     * 你所设计的解决方案必须只使用常量级的额外空间。
     *
     * 输入：numbers = [2,7,11,15], target = 9
     * 输出：[1,2]
     * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
     */
    public int[] twoSum167v1(int[] numbers, int target) {
        // 二分查找完成 o(nlogn)超时
        int end = numbers.length;
        int[] res = new int[2];
        for (int i = 0; i < end; i++) {
            int e1 = numbers[i];
            res[0] = i + 1;
            int l = i + 1;
            int r = end - 1;
            while (l <= r) {
                int mid = (l + r) / 2;
                if (e1 + numbers[mid] == target) {
                    res[1] = mid + 1;
                }else if(e1 + numbers[mid] < target) {
                    l = mid + 1;
                }else {
                    r = mid - 1;
                }
            }
        }
        return res;
    }
    public int[] twoSum167v2(int[] numbers, int target) {
        // 对撞指针
        int i = 0;
        int j = numbers.length - 1;
        while (i < j) {
            int sum = numbers[i] + numbers[j];
            if (sum == target) {
                return new int[]{i + 1, j + 1};
            }else if (sum > target) {
                j--;
            }else {
                i++;
            }
        }
        throw new IllegalArgumentException();
    }

    /**
     * 如果在将所有大写字符转换为小写字符、并移除所有非字母数字字符之后，短语正着读和反着读都一样。则可以认为该短语是一个 回文串 。
     * 字母和数字都属于字母数字字符。
     * 给你一个字符串 s，如果它是 回文串 ，返回 true ；否则，返回 false 。
     *
     输入: s = "A man, a plan, a canal: Panama"
     输出：true
     解释："amanaplanacanalpanama" 是回文串。
     */
    public boolean isPalindrome125(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i ++) {
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c)) {
                sb.append(Character.toLowerCase(c));
            }
        }
        String str = sb.toString();
        int n = str.length();
        int l = 0;
        int r = n - 1;
        while (l <= r) {
            if (str.charAt(l) == str.charAt(r)) {
                l++;
                r--;
            }else {
                return false;
            }
        }
        return true;
    }
    /**
     * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
     * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
     *
     * 输入：s = ["h","e","l","l","o"]
     * 输出：["o","l","l","e","h"]
     */
    public void reverseString344(char[] s) {
        int l = 0;
        int r = s.length - 1;
        while (l <= r) {
            char temp = s[l];
            s[l] = s[r];
            s[r] = temp;
            l++;
            r--;
        }
    }
    /**
     * 给定一个长度为 n 的整数数组height。有n条垂线，第 i 条线的两个端点是(i, 0)和(i, height[i])。
     * 找出其中的两条线，使得它们与x轴共同构成的容器可以容纳最多的水。
     * 返回容器可以储存的最大水量。
     * 说明：你不能倾斜容器。
     *
     输入：[1,8,6,2,5,4,8,3,7]
     输出：49
     解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
     */
    public int maxArea11(int[] height) {
        // 对撞指针如何移动是难点
        int l = 0;
        int r = height.length - 1;
        int res = 0;
        while (l < r) {
            res = height[l] < height[r] ?
                    Math.max(res, (r - l) * height[l++]):
                    Math.max(res, (r - l) * height[r--]);
        }
        return res;
    }
    public int maxArea11v2(int[] height) {
        // 暴力，超时
        int length = height.length;
        int max = 0;
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                int x = j - i;
                int y = Math.min(height[i], height[j]);
                int res = x * y;
                if (res > max) {
                    max = res;
                }
            }
        }
        return max;
    }
}
