package ybs.otherProblems;

/**
 * @BelongsProject: Brush-a-question
 * @BelongsPackage: ybs.otherProblems
 * @Author: alicebercouli_i
 * @Date: 2022/11/8 11:44
 * @Description:
 */
public class Main {

    public static void main(String[] args) {
        Problems problems = new Problems();
        String str1 = "2345678";
        String str2 = "92356";
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();
        System.out.print(str1 + " * " + str2 + " = ");

        problems.covertData(s1, str1.length());
        problems.covertData(s2, str2.length());

        problems.multiply(s1,str1.length(), s2,str2.length());

    }


}
