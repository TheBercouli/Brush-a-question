package ybs.shuati;

import java.util.*;

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
        // 本题通用解法：滑动窗口-动态调整窗口
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
        // 本题通用解法：滑动窗口-动态调整窗口
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
    // 本题与上题一样，关键在于如何唯一确定一个字串，利用字符字节出现的频率来唯一确定！！
    public List<Integer> findAnagrams438v1(String s, String p) {
        List<Integer> res = new ArrayList<>();
        int sLen = s.length();
        int pLen = p.length(); // 固定窗口大小（每次移动长度）

        if (sLen < pLen)
            throw new IllegalArgumentException();

        // 本题通用解法：滑动窗口-固定窗口
        // 明确谁是窗口
        int[] pFreq = new int[26];
        int[] window = new int[26];
        // 初始化窗口和频次数组:这里相当于一种情况进行了处理=>初始化s中前【窗口大小】个元素和窗口元素出现的频率
        for (int i = 0; i < pLen; i++) {
            pFreq[p.charAt(i) - 'a']++;
            window[s.charAt(i) - 'a']++;
        }
        // 如果s中前【窗口大小】和窗口中频率一致则为结果集的一种
        if (Arrays.equals(pFreq, window)) {
            res.add(0);
        }
        // 如果第一种情况没有，此时sFreq中最多存在两个目标字母的频率数据。
        // 此时开始滑动固定大小的窗口，
        for (int i = 0; i < sLen - pLen; i++) {
            // 滑动过程：
            window[s.charAt(i) - 'a']--;
            window[s.charAt(i + pLen) - 'a']++; // 如果添加了p串以外的字符，那么都不会是结果集的一种
            if (Arrays.equals(window, pFreq))
                res.add(i + 1);
        }
        return res;
    }

    /**
     * 任何题目如果没有思路其实都可以想一下暴力解法。这道题暴力解法思路简单：
     * 遍历任意i，j，使得i和j之间的子串长度，等于p串的长度。该子串称之为x。该步复杂度为O（n）。
     * 判断x是否与p是异位词。是的话，则把i加入答案中。该步复杂度为O（n）。
     * 暴力法的复杂度为O（n^2）。显然不高效。
     * 可以发现第二步其实做了很多不必要的操作，例如[i, j]和[i+1, j+1]两个子串在暴力法第二步中，需要各遍历一次，完全没必要。其实[i+1, j+1]完全可以在[i, j]的基础上做判断，也就是去掉头部的字符（i位置），加上尾部的字符（j+1位置）。这样第一步的复杂度可以降到O(1)。整体复杂度降到O(n)。已经得到信息不重复使用就浪费了，没必要重新搜集近乎相同的信息。这就是滑动窗口法。
     * 滑动窗口法的特点是，一连串元素的信息，可以用常数时间推断出，该串整体移位后，新串信息。
     * 所有滑动窗口问题，如果能从暴力法优化的角度思考，都不难想到。
     */
    public List<Integer> findAnagrams438v2(String s, String p) {
        // 本题通用解法：滑动窗口-蠕动收缩窗口，先增大，在缩小
        List<Integer> res = new ArrayList<>();
        int[] freq = new int[26];
        for (char c : p.toCharArray()) {
            freq[c - 'a']++;
        }
        int l = 0;
        int h = 0;
        while (h < s.length()) {
            if (freq[s.charAt(h) - 'a'] > 0) {
                freq[s.charAt(h++) - 'a']--;
                if (h - l == p.length()) res.add(l);
            } else
                freq[s.charAt(l++) - 'a']++;
        }
        return res;
    }

    /**
     * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     *
     * 输入：s = "ADOBECODEBANC", t = "ABC"
     * 输出："BANC"
     * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
     */
    public static String minWindow76v1(String s, String t) {
        char[] chars = s.toCharArray(), chart = t.toCharArray();
        int n = chars.length, m = chart.length;

        int[] hash = new int[128];
        for (char ch : chart) hash[ch]--;

        String res = "";
        for (int i = 0, j = 0, cnt = 0; i < n; i++) {
            hash[chars[i]]++;
            if (hash[chars[i]] <= 0) cnt++;
            while (cnt == m && hash[chars[j]] > 0) hash[chars[j++]]--;
            if (cnt == m)
                if (res.equals("") || res.length() > i - j + 1)
                    res = s.substring(j, i + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        String a = "ADOBECODEBANC";
        String b = "ABC";
        System.out.println(minWindow76v1(a, b));
    }

}
