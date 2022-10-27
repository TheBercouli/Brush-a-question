package review.sort;

import java.util.ArrayList;
import java.util.Arrays;

import review.SimpleUtil;

/**
 * @BelongsProject: Brush-a-question
 * @BelongsPackage: review.sort
 * @Author: alicebercouli_i
 * @Date: 2022/10/19 11:06 AM
 * @Description: 基数排序-最大值（正数）
 *  根据每个元素的位来排序
 *
 */
public class BasicSort {

    public static void main(String[] args) {
        int[] a = {39,33,10,8,66,23,67,9,15,100,70,22,3,6,54};
        System.out.println(Arrays.toString(a));
        BasicSort basicSort = new BasicSort();
        basicSort.basicSort(a);
        System.out.println(Arrays.toString(a));
    }

    public void basicSort(int[] array) {
        int max = SimpleUtil.getMaxOrMinElements(array,true);
        // 获取最大值
        int times = 0;
        while (max > 0) {
            max = max / 10;
            times++;
        } // 获取最大值位数
        ArrayList<ArrayList<Integer>> queue = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ArrayList<Integer> q = new ArrayList<>();
            queue.add(q);
        }
        for (int i = 0; i < times; i++) {
            for (int a : array) {
                // 获取数的某个位上的值，即获取对应位的数值（i=0：个位；i=1；十位；i=2：百wei）
                // 分段解释：「array[a] % (int) Math.pow(10, i + 1)」 == 对原数值取余，拿到数值对应的后i+1位上的值作为结果
                // 「array[a] % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i)」 == 对上面结果取整数，拿到原数值对应的第i+1位上的值
                int x = a % (int) Math.pow(10, i + 1) / (int) Math.pow(10, i);
                ArrayList<Integer> q = queue.get(x);
                q.add(a);
                queue.set(x, q); // 原来的q已经更改，此行代码位刷新queue的值
            }
            // 收集: 第times次排序
            int count = 0;
            for (int j = 0; j < 10;j++) {
                ArrayList<Integer> q = queue.get(j); // 拿到每一个数组
                while (q.size() > 0) {
                    array[count] = q.get(0);
                    q.remove(0);
                    count++;
                }
            }
        }
    }
}
