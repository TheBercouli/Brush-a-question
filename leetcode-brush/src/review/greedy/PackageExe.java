package review.greedy;

import java.util.Arrays;

import review.SimpleUtil;

/**
 * @BelongsProject: Brush-a-question
 * @BelongsPackage: review.greedy
 * @Author: alicebercouli_i
 * @Date: 2022/11/3 14:40
 * @Description: 贪心-背包：尽量取最大值
 */
public class PackageExe {

    private int  MAX_WEIGHT = 150;
    private int[] weights = new int[]{35,30,60,50,40,10,25};
    private int[] values = new int[]{10,40,30,50,35,40,30};

    private void packageGreedy(int capacity, int[] weights, int[] values) {
        int n = weights.length;
        double[] r = new double[n]; // 性价比数组
        int[] index = new int[n]; // 按性价比排序物品的下表
        for (int i = 0; i < n; i++) {
            r[i] = (double) values[i] / weights[i];
            index[i] = i;
        }
        // 处理好性价比数组并将 每种物品对应的下标根据性价比顺序 排好序（现在是降序）
        double temp = 0;
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (r[i] < r[j]) {
                    temp = r[i];
                    r[i] = r[j];
                    r[j] = temp;
                    // 用冒泡排序性价比数组后，将性价比物品下表与排序也更新
                    SimpleUtil.swapElements(index,i,j);
                }
            }
        }
        // 排序好的重量和价值分别存到数组
        int[] w = new int[n];
        int[] v = new int[n];
        for (int i = 0; i < n; i++) {
            w[i] = weights[index[i]];
            v[i] = values[index[i]];
        }
        // 装入背包
        int[] p = new int[n];
        int currentValue = 0;
        int currentWeight = 0;
        for (int i = 0; i < n; i++) {
            if (w[i] <= capacity) {
                p[i] = 1; // p下标表示在排好序的性价比数组中的物品是否被装入
                currentValue += v[i];
                currentWeight += w[i];
                System.out.println("重为" + w[i] + "价值为" + v[i] + "的"+ index[i] + "号物品被装入背包");
                capacity -= w[i];
            }
        }
        System.out.println("总共放下物品的数量: " + Arrays.toString(p));
        System.out.println("背包最大重量: " + currentWeight);
        System.out.println("背包最大价值: " + currentValue);
    }

    public static void main(String[] args) {
        PackageExe packageExe = new PackageExe();
        packageExe.packageGreedy(packageExe.MAX_WEIGHT, packageExe.weights, packageExe.values);
    }

}
