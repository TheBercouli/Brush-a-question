package ybs.shuati;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 双索引技术-滑动窗口
 */
public class Solution209and3and438and76 {

    /**
     * 给定一个含有n个正整数的数组和一个正整数 target 。
     * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组[numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
     *
     * 输入：target = 7, nums = [2,3,1,2,4,3]
     * 输出：2
     * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
     */
    public int minSubArrayLen209v1(int target, int[] nums) {
        int l = 0;
        int r = -1;
        int sum = 0;
        int res = nums.length + 1;
        while (l < nums.length) {
            // 调整窗口
            if (r + 1 < nums.length && sum < target)
                sum += nums[++r];
            else
                sum -= nums[l++];

            if (sum >= target)
                res = Math.min(res, (r - l + 1));
        }
        return res == nums.length + 1 ? 0 : res;
    }

    public int minSubArrayLen209v2(int target, int[] nums) {
        int l = 0;
        int r = 0;
        int sum = 0;
        int res = nums.length + 1;
        while (r < nums.length) {
            sum += nums[r++]; // 不断增大窗口
            while (sum >= target) {
                res = Math.min(res, r - l);
                sum -= nums[l++];
            }
        }
        return res == nums.length + 1 ? 0 : res;
    }
    /**
     * 无重复字符的最长子串: 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
     *
     * 输入: s = "abcabcbb"
     * 输出: 3
     * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
     */
    public int lengthOfLongestSubstring3(String s) {
        int[] freq = new int[256];
        int l = 0;
        int r = -1;
        int res = s.length() + 1;
        while (l < s.length()) {
            if (r + 1 < s.length() && 0 == freq[s.charAt(r + 1)])
                freq[s.charAt(++r)] ++;
            else
                freq[s.charAt(l++)] --;
            // 这里配合while循环把窗口开始位置直接缩小到 窗口继续可以向后移动（即排除了当前字串中[s.charAt(l),s.charAt(r+1)]之间的所有字母）

            res = Math.min(res, r - l + 1);
        }
        return res == s.length() + 1 ? 0 : res;
    }
    /**
     * 给定两个字符串s和 p，找到s中所有p的异位词的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
     *
     * 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
     输入: s = "cbaebabacd", p = "abc"
     输出: [0,6]
     解释:
     起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
     起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
     */
    public List<Integer> findAnagrams438(String s, String p) {
        Set<Character> set = new HashSet<>();
        for(int i = 0; i < p.length(); i++) {
            set.add(p.charAt(i));
        }
        int l = 0;
        int r = -1;
        List<Integer> res = new ArrayList<>();
        while (l < s.length()) {
            if (r + 1 < s.length() && (set.contains(s.charAt(r + 1))))
                r++;
            else if (r + 1 <= l)
                r++;
            else
                l++;
            res.add(l);
        }
        return res;
    }

}
