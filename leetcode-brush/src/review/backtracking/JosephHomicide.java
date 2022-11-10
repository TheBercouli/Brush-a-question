package review.backtracking;

/**
 * @BelongsProject: Brush-a-question
 * @BelongsPackage: review.backtracking
 * @Author: alicebercouli_i
 * @Date: 2022/11/7 17:21
 * @Description: 回溯-约瑟夫杀人法
 */
public class JosephHomicide {

    public static int N = 20;

    public static int M = 5; // 数到M就咔嚓一个人

    class Node{
        int val;
        Node next;
        public Node(int val){
            this.val = val;
        }
    }

    public void killNode() {
        Node header = new Node(1);
        Node x = header; // x当前节点
        for (int i = 2; i <= N; i++) {
            x = (x.next = new Node(i));
        }
        x.next = header; // 头尾相接
        System.out.println("被kill的顺序:");
        while (x != x.next) {
            // 至少还有两人，仍然继续报数
            for (int i = 1; i < M; i++) {
                x = x.next;
            }
            System.out.println(x.next.val + "被kill");
            x.next = x.next.next;
        }
        System.out.println("最终存活的是:" + x.val);
    }

    public static void main(String[] args) {
        JosephHomicide josephHomicide = new JosephHomicide();
        josephHomicide.killNode();
    }


}
