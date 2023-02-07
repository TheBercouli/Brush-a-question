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
     * @description: 获取某个定点的第一个邻接点
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexSize; j++) {
            if (matrix[index][j] > 0 && matrix[index][j] < MAX_WEIGHT) {
                return j;
            }
        }
        return -1;
    }
    /**
     * @author: alicebercouli_i
     * @description: 根据前一个邻接点的下标来获取下一个邻接点
     *  如果v1 = 3 v2 = 4，方法的含义为：找到 点3 以 点4 为标准 开始的下一个邻接点
    */
    public int getNextNeighbor(int v1, int v2) {
        return 0;
    }


}
