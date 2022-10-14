package ybs.swordRefers2;

/**
 * @BelongsProject: Brush-a-question
 * @BelongsPackage: ybs.swordRefers2
 * @Author: alicebercouli_i
 * @Date: 2022/10/14 11:28 AM
 * @Description:
 */
public class Sr03to12 {

    /***
     * @author: alicebercouli_i
     * @date: 2022/10/14 11:31 AM
     * @description:
     * 序号：03 == 找出数组中重复的数字 ： 数组中某些数字是重复的，但不知道有几个数字重复了，也不知道每个数字重复了几次
     *  输入：
     *  [2, 3, 1, 0, 2, 5, 3]
     *  输出：2 或 3
     */
    public int findRepeatNumber(int[] nums) {
        for (int i = 0;i<nums.length;i++){
            while(i!=nums[i]){
                if(nums[i] == nums[nums[i]])
                    return nums[i];
                int k = nums[nums[i]];
                nums[nums[i]] = nums[i];
                nums[i]=k;
            }
        }
        return -1;
    }
    /**
     * @author: alicebercouli_i
     * @date: 2022/10/14 11:33 AM
     * @description:
     * 序号：04 == 特殊规律的二维数组中的查找 ：
     *  在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。请完成一个高效
     *  的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
     *
            [1,   4,  7, 11, 15],
            [2,   5,  8, 12, 19],
            [3,   6,  9, 16, 22],
            [10, 13, 14, 17, 24],
            [18, 21, 23, 26, 30]
     */
        public boolean findNumberIn2DArray(int[][] matrix, int target) {
            if(matrix == null || matrix.length <= 0 || matrix[0].length <=0 )
                return false;
            int cols = matrix.length; // 行
            int rows = matrix[0].length; // 列
            int col = cols - 1; // 左下角元素的行
            int row = 0; // 左下角元素的列

            while(col >= 0 && row < rows ) {
                if (matrix[col][row] > target)
                    col --;
                else if (matrix[col][row] < target)
                    row ++;
                else if (matrix[col][row] == target)
                    return true;
            }
            return false;
        }

}
