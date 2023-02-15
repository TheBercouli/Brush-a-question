package review.graph;

/**
 * @BelongsProject: Brush-a-question
 * @BelongsPackage: review.graph
 * @Author: alicebercouli_i
 * @Date: 2022/11/10 16:07
 * @Description:
 */
public class Graph {

    private int vertexSize;//顶点数量

    private int [] vertexs;//顶点数组

    private int[][]  matrix; // 邻接矩阵

    private static final int MAX_WEIGHT = 1000;

    private boolean [] isVisited;

    public int getVertexSize() {
        return vertexSize;
    }

    public void setVertexSize(int vertexSize) {
        this.vertexSize = vertexSize;
    }

    public int[][] getMatrix() {
        return matrix;
    }

    public void setMatrix(int[][] matrix) {
        this.matrix = matrix;
    }

    public Graph(int vertexSize){
        this.vertexSize = vertexSize;
        matrix = new int[vertexSize][vertexSize];
        vertexs = new int[vertexSize];
        for(int i = 0;i<vertexSize;i++){
            vertexs[i] = i;
        }
        isVisited = new boolean[vertexSize];
    }

    /**
     * 创建图的过程
     */
    public void createGraph(){
        int [] a1 = new int[]{0,1,5,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT};
        int [] a2 = new int[]{1,0,3,7,5,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT};
        int [] a3 = new int[]{5,3,0,MAX_WEIGHT,1,7,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT};
        int [] a4 = new int[]{MAX_WEIGHT,7,MAX_WEIGHT,0,2,MAX_WEIGHT,3,MAX_WEIGHT,MAX_WEIGHT};
        int [] a5 = new int[]{MAX_WEIGHT,5,1,2,0,3,6,9,MAX_WEIGHT};
        int [] a6 = new int[]{MAX_WEIGHT,MAX_WEIGHT,7,MAX_WEIGHT,3,0,MAX_WEIGHT,5,MAX_WEIGHT};
        int [] a7 = new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,3,6,MAX_WEIGHT,0,2,7};
        int [] a8 = new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,9,5,2,0,4};
        int [] a9 = new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,7,4,0};

        matrix[0] = a1;
        matrix[1] = a2;
        matrix[2] = a3;
        matrix[3] = a4;
        matrix[4] = a5;
        matrix[5] = a6;
        matrix[6] = a7;
        matrix[7] = a8;
        matrix[8] = a9;
    }

    /**
     * @author: alicebercouli_i
     * @description: 获取某个顶点的第一个邻接点
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexSize; j++) {
            if (matrix[index][j] > 0 && matrix[index][j] < MAX_WEIGHT) {
                // 找到第一个
                return j;
            }
        }
        // 没有临界点
        return -1;
    }

    /**
     * @author: alicebercouli_i
     * @description: 根据前一个邻接点的下标来获取下一个邻接点
     *  如果v = 3 index = 4，方法的含义为：找到 点3 以 点4 为标准 开始的下一个邻接点
    */
    public int getNextNeighbor(int v, int index) {
        for (int i = index + 1; i < vertexSize; i++) {
            if (matrix[v][i] > 0 && matrix[v][i] < MAX_WEIGHT) {
                return i;
            }
        }
        return -1;
    }

    /***
     * @author: alicebercouli_i
     * @description: 图的深度优先遍历DFS核心
     */
    private void dfs(int i){
        isVisited[i] = true;
        int w = getFirstNeighbor(i);
        while (w != -1) { // 有临接点
            if (!isVisited[w]) {
                // 需要遍历该节点
                System.out.println("访问到了：" + w + "顶点");
                dfs(w);
            }
            w = getNextNeighbor(i, w); // 第一个相对于w的临接节点

        }
    }
    /***
     * @author: alicebercouli_i
     * @description: 调用的深度优先遍历
     */
    public void depthFirstSearch() {
        isVisited = new boolean[vertexSize];
        for (int i = 0; i < vertexSize; i++) {
            if (!isVisited[i]) {
                dfs(i);
            }
        }
        isVisited = new boolean[vertexSize];
    }

















    public static void main(String [] args){
        Graph graph = new Graph(9);

        int [] a1 = new int[]{0,10,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,11,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT};
        int [] a2 = new int[]{10,0,18,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,16,MAX_WEIGHT,12};
        int [] a3 = new int[]{MAX_WEIGHT,MAX_WEIGHT,0,22,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,8};
        int [] a4 = new int[]{MAX_WEIGHT,MAX_WEIGHT,22,0,20,MAX_WEIGHT,MAX_WEIGHT,16,21};
        int [] a5 = new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,20,0,26,MAX_WEIGHT,7,MAX_WEIGHT};
        int [] a6 = new int[]{11,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,26,0,17,MAX_WEIGHT,MAX_WEIGHT};
        int [] a7 = new int[]{MAX_WEIGHT,16,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,17,0,19,MAX_WEIGHT};
        int [] a8 = new int[]{MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,16,7,MAX_WEIGHT,19,0,MAX_WEIGHT};
        int [] a9 = new int[]{MAX_WEIGHT,12,8,21,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,MAX_WEIGHT,0};

        graph.matrix[0] = a1;
        graph.matrix[1] = a2;
        graph.matrix[2] = a3;
        graph.matrix[3] = a4;
        graph.matrix[4] = a5;
        graph.matrix[5] = a6;
        graph.matrix[6] = a7;
        graph.matrix[7] = a8;
        graph.matrix[8] = a9;

//		int degree = graph.getOutDegree(4);
//		System.out.println("vo的出度:"+degree);
//		System.out.println("权值："+graph.getWeight(2,3));
		graph.depthFirstSearch();
//		graph.broadFirstSearch();
//        graph.prim();
    }


}
