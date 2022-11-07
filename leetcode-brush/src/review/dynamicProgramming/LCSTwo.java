package review.dynamicProgramming;

/**
 * @BelongsProject: Brush-a-question
 * @BelongsPackage: review.dynamicProgramming
 * @Author: alicebercouli_i
 * @Date: 2022/11/4 17:05
 * @Description: 工具类
 */
import java.util.Scanner;
public class LCSTwo {
    static int N=1002;
    static int [][]c = new int[N][N];
    static int [][]b = new int[N][N];//记录最优解来源数组
    static char []s1 = new char[N];
    static char []s2 = new char[N];
    static int len1, len2;//s1,s2的长度
    static void LCSL(){
        int i, j;
        for (i = 1;i <= len1; i++){//控制s1序列
            for (j = 1; j <= len2; j++){//控制s2序列
                if (s1[i - 1] == s2[j - 1]) {//下标从零开始，如果当前的字符相同，则公共子序列的长度为该字符前的最长公共序列
                    c[i][j] = c[i - 1][j - 1] + 1;
                    b[i][j] = 1;
                } else{
                    if (c[i][j - 1] >= c[i - 1][j]){//两者找最大值，并记录最优解来源
                        c[i][j] = c[i][j - 1];
                        b[i][j] = 2;
                    } else{
                        c[i][j] = c[i - 1][j];
                        b[i][j] = 3;
                    }
                }
            }
        }
    }
    /*
    我们在最长公共子序列长度c[i][j]的过程中，用b[i][j]记录了c[i][j]的来源，所以可以根据b[i][j]数组倒推最优解
     */
    static void print(int i, int j){//根据记录下来的信息构造最长公共子序列（从b[i][j]开始递推）
        if(i == 0 || j == 0)
            return;
        if (b[i][j] == 1){//说明s1[i-1]=s2[j-1],递归输出print（i-1，j-1）;然后输出s1[i-1]
            print(i - 1,j - 1);
            System.out.print(s1[i - 1]);
        }
        else if(b[i][j] == 2){//说明s1[i-1]≠s2[j-1]且最优解来源于c[i][j]=c[i][j-1],递归输出print（i,j-1）
            print(i,j - 1);
        }
        else//即b[i][j]=3,说明s1[i-1]≠s2[j-1]且最优解来源于c[i][j]=c[i-1][j],递归输出print（i-1,j）
            print(i - 1,j);
    }
    public static void main(String[] args){
        Scanner sc=new Scanner(System.in);
        int i, j;
        String s;
        System.out.println("请输入字符串s1:");
        s = sc.nextLine();
        len1 = s.length();
        for(i = 0; i < len1; i++){
            s1[i] = s.charAt(i);
        }
        System.out.println("请输入字符串s2:");
        s = sc.nextLine();
        len2 = s.length();
        for(i = 0; i < len2; i++){
            s2[i] = s.charAt(i);
        }
        for(i = 0; i <= len1; i++){
            c[i][0] = 0;//初始化第一列为0；
        }
        for(j=0; j <= len2; j++){
            c[0][j] = 0;//初始化第一行为0；
        }
        LCSL();
        System.out.println("s1和s2的最长公共子序列的长度是：" + c[len1][len2]);
        System.out.println("s1和s2的最长公共子序列是：");
        print(len1 ,len2);
        System.out.println();
    }
}

