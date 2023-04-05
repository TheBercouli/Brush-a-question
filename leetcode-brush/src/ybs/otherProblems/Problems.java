package ybs.otherProblems;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @BelongsProject: Brush-a-question
 * @BelongsPackage: ybs.swordRefers2
 * @Author: alicebercouli_i
 * @Date: 2022/10/9 2:29 PM
 * @Description: 零碎题解
 */
public class Problems {

    /***
     * @author: alicebercouli_i
     * @date: 2022/10/10 2:46 PM
     * @description: 计算200以内正整数的阶乘【https://www.nowcoder.com/questionTerminal/155abf9586be4274a55455c9e721619e】
     * 单例解释：
     * 1    *    2    *    3
     * x    *    y
     * z = x * y = 1 * 2 = 2;
     * 此时让x保存z(已计算)的值，并且让y向后移动一位
     * x = z
     * y ++
     * 此时x = 2为2！的最终值，让它与下一轮的值3(y++)计算得到3！
     * 备注：数组下标移步法（独创）
     */
    public BigInteger solution(int n) {
        BigInteger x = BigInteger.valueOf(1);
        BigInteger y = BigInteger.valueOf(2);
        BigInteger z = BigInteger.ZERO;
        for(int i = 1; i < n ; i ++) {
            z = x.multiply(y);
            x = z;
            y = y.add(BigInteger.valueOf(1));
        }
        return x;
    }

    /***
     * @author: alicebercouli_i
     * @date: 2022/11/8 11:22
     * @description: 大数相乘
     */
    // 高位对调
    // 现实：456 * 123 个位是6和3，先从个位开始计算
    // 计算机中：存入字符数组是456不方便 "先从个位开始计算"，因此需要高位对调
    public void covertData(char data[], int len) {
        for (int i = 0; i < len / 2; i++) {
            data[i] += data[len - 1 - i];
            data[len - 1 - i] = (char) (data[i] - data[len - 1 - i]);
            data[i] = (char) (data[i] - data[len - 1 - i]);
        }
    }
    public void multiply(char a[], int aLen, char b[], int bLen) {
        int resultSize = aLen + bLen + 3; // 两个数字相乘位数不超过两个数的位数和+3
        int[] res = new int[resultSize]; // 乘积结果数组

        // 对其后逐位相乘
        for (int i = 0; i < bLen; i ++) {
            for (int j = 0; j < aLen; j ++) {
                res[i + j] += Integer.parseInt(String.valueOf(a[j])) * Integer.parseInt(String.valueOf(b[i]));
            }
        }
        // 进位处理
        int m = 0;
        for (m = 0; m < resultSize; m ++) {
            int carry = res[m] / 10;
            res[m] = res[m] % 10;
            if (carry > 0) {
                res[m + 1] += carry;
            }
        }
        // 找到最高位
        for (m = resultSize - 1; m >= 0;) {
            if (res[m] > 0)
                break;
            m--;
        }
        for (int n = 0; n <= m; n ++) {
            System.out.print(res[m - n]);
        }
        System.out.println();
    }

    /**
     * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
     * 输出：[2]
     */
    public int[] intersection350(int[] nums1, int[] nums2) {
        Set<Integer> res = new HashSet<>();
        Set<Integer> set = new HashSet<>();
        for (int i : nums2) {
            set.add(i);
        }
        for (int i : nums1) {
            if (set.contains(i)) {
                res.add(i);
            }
        }
        List<Integer> collect = new ArrayList<>(res);
        int[] arr = new int[collect.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = collect.get(i);
        }
        return arr;
    }

    /**
     * 输入: s = "anagram", t = "nagaram"
     * 输出: true
     */
    public boolean isAnagram242(String s, String t) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        boolean res = true;
        for (char c : t.toCharArray()) {
            if (!map.containsKey(c))
                res = false;
            else {
                map.put(c, map.get(c) - 1);
                if (map.get(c) <= 0)
                    res = false;
            }
        }
        return res;

    }
    public boolean isHappy202(int n) {
        int slow = n, fast = n;
        // 如果同时从起点出发，slow每次移动1格，fast每次移动2格，如果是一个环状格子，那么格子的个数就是fast第一次追上slow时，双方需要移动的次数
        do {
            slow = sumBitSquare(slow);
            fast = sumBitSquare(sumBitSquare(fast));
        }while (slow != fast);

        //slow = n;
        //fast = sumBitSquare(n);
        //// 这里是从第一次移动后的位置开始出发
        //while (n != 1 && slow != fast) {
        //    slow = sumBitSquare(slow);
        //    fast = sumBitSquare(sumBitSquare(fast));
        //}

        return slow == 1;

    }
    private int sumBitSquare(int n) {
        int sum = 0;
        while (n > 0) {
            int bit = n % 10;
            sum += bit * bit;
            n = n / 10;
        }
        return sum;
    }

    public boolean wordPattern290(String pattern, String str) {
        String[] words = str.split(" ");
        if (words.length != pattern.length()) {
            return false;
        }
        Map<Object, Integer> map = new HashMap<>();
        for (Integer i = 0; i < words.length; i++) {
            if (map.put(pattern.charAt(i), i) != map.put(words[i], i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 输入：s = "egg", t = "add"
     * 输出：true
     */
    public boolean isIsomorphic205(String s, String t) {
        if (s.length() != t.length())
            return false;
        Map<Character, Character> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (!map.containsKey(s.charAt(i))) {
                if (map.containsValue(t.charAt(i)))
                    return false;
                map.put(s.charAt(i), t.charAt(i));
            }else if(!map.get(s.charAt(i)).equals(t.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * 451根据字符出现的频率排序
     * 输入: s = "tree"
     * 输出: "eert"
     * 解释: 'e'出现两次，'r'和't'都只出现一次。
     * 因此'e'必须出现在'r'和't'之前。此外，"eetr"也是一个有效的答案。
     */
    public String frequencySort451(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : s.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        List<Character> keyList = new ArrayList<>(map.keySet());
        List<Character> list = keyList.stream().sorted((a,b) -> (map.get(b) - map.get(a))).collect(Collectors.toList());
        StringBuilder res = new StringBuilder();
        for (Character character : list) {
            for (int i = 0; i < map.get(character); i++) {
                res.append(character);
            }
        }
        return res.toString();
    }



    public int[] twoSum1(int[] nums, int target) {
        Map<Integer, Integer> record = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int temp = target - nums[i];
            if (record.containsKey(temp)) {
                return new int[]{i, record.get(temp)};
            }
            record.put(nums[i], i);
        }
        throw new IllegalArgumentException();
    }


    /**
     * 15 三数之和
     * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
     * 你返回所有和为 0 且不重复的三元组
     * 。
     * 输入：nums = [-1,0,1,2,-1,-4] -4 -1 -1 0 1 2
     * 输出：[[-1,-1,2],[-1,0,1]]
     * 解释：
     * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
     * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
     * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
     * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
     * 注意，输出的顺序和三元组的顺序并不重要。
     */
    /*
    本题思路：明确题目要求=数组内三个数之和为0，不能有重复的三元组，但三元组内的元素是可以重复的！
    首先输出的不是数组下标，这点很关键。其次是重复问题
    因此我们的解答：1.对数组排序 2. 选定标定数即三数中的一个数 3. 利用对撞指针进行求和
                4. 注意过程中的重复问题
     */
    public static List<List<Integer>> threeSum15(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            // 此时数组有序，三数和中标定数已经大于0那么后续无需判断肯定没法sum=0
            if (nums[i] > 0) {
                return res;
            }
            // 去重过程的重点：不能有重复的三元组，但三元组内的元素是可以重复的！
            // 此步骤为去重的第一步：由于不能有重复的三元组
            // 即，如果说本次开始元素和上次开始元素相同，应该在不影响后续操作的基础上去重，因此不可以使用nums[i] = nums[i + 1]否则这样会导致[-1, -1 , 2]这个可行解被排除
            // 跟通用的理解是：因为数组有序，相同元素值紧邻排列。如果此时第一个元素与第二个元素值相同
            // 此时使用第一个元素值作为标定数，那么后续指针将会从第二个元素开始逻辑选择操作
            // 当操作完毕后，以标定数为核心的可行解已经完全获取，如果再从第二个元素开始(元素值与第一个元素也就是标定元素值相同)则会导致造成相同的可行解
            // 此时若第一个元素获取可行解为集合T,若用第二个元素获取可行解为集合R，则有关系T的一个真子集是R，因此，我们使用过第一个相同元素后，他之后的相同元素不应该再被使用
            // 例子：-4 -1 -1 0 1 2    T={[-1,-1,2],[-1,0,1]} R={[-1,0,1]}
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int l = i + 1;
            int h = n - 1;
            while (l < h) {
                int sum = nums[l] + nums[h] + nums[i];
                if (sum == 0) {
                    res.add(Arrays.asList(nums[i], nums[l], nums[h]));
                    // 再次对剩余元素去重 {0,-2,-2,-2,-1,-1,1,1,1,-2,-2,-2}针对这种情况的处理
                    while (l < h && nums[l] == nums[l + 1]) l++;
                    while (l < h && nums[h] == nums[h - 1]) h--;
                    // 去重过后，因为一个元组已经确定，对撞指针同时移动，寻找下一个可行解
                    l++;h--;
                }else if (sum > 0) {
                    h --;
                }else {
                    l ++;
                }
            }
        }
        return res;
    }


    /**
     * 16:最接近的三数之和
     * 给你一个长度为 n 的整数数组nums和 一个目标值target。请你从 nums 中选出三个整数，使它们的和与target 最接近。
     * 返回这三个数的和。
     * 假定每组输入只存在恰好一个解。
     *
     * 输入：nums = [-1,2,1,-4], target = 1   -4 -1 1 2
     * 输出：2
     * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。

     */
    public int threeSumClosest16v1(int[] nums, int target) {
        Arrays.sort(nums);
        int resSum = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue;
            int l = i + 1;
            int h = nums.length - 1;
            while (l < h) {
                int sum = nums[i] + nums[l] + nums[h];
                if (sum == target)  // 一般情况这如果不是最接近 这里已经拿到了全部数据
                    return sum;
                // 获取最优解
                if (Math.abs(sum - target) < Math.abs(resSum - target)) {
                    resSum = sum;
                }
                // 和15一样的去重操作，然后移动对撞指针，移动方式也略有不同
                if (sum > target) {
                    while (l < h && nums[h] == nums[h - 1]) h--;
                    h--;
                }else {
                    while (l < h && nums[l] == nums[l + 1]) l++;
                    l++;
                }
            }
        }
        return resSum;

    }

    /**
     * 49：字母异位词分组
     * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
     * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。
     *
     * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
     * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
     */
    public List<List<String>> groupAnagrams49(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] chars = str.toCharArray();
            Arrays.sort(chars); // 拍了需就相当于唯一确定了一种异位词
            String key = new String(chars);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str); // 将这种异位词代表的数组中字符串放入该异位词对应的映射集合中。
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    //347前K个出现频率




}
