public class GraphMatrix implements Graph {
    private int numOfNodes;
    private boolean directed;
    private boolean weighted;
    private int[][] matrix;

    private boolean[][] isSetMatrix;

    static class ResultSet {
        // will store the vertex(parent) from which the current vertex will reached
        int parent;
        // will store the weight for printing the MST weight
        int weight;
    }

    public GraphMatrix(int numOfNodes) { // for undirected and weighted graphs

        this.directed = false;
        this.weighted = true;
        this.numOfNodes = numOfNodes;

        // Simply initializes our matrix to the appropriate size
        matrix = new int[numOfNodes][numOfNodes];
        isSetMatrix = new boolean[numOfNodes][numOfNodes];
    }

    public GraphMatrix(int numOfNodes, boolean directed, boolean weighted) {

        this.directed = directed;
        this.weighted = weighted;
        this.numOfNodes = numOfNodes;

        // Simply initializes our adjacency matrix to the appropriate size
        matrix = new int[numOfNodes][numOfNodes];
        isSetMatrix = new boolean[numOfNodes][numOfNodes];
    }

    @Override
    public void addEdge(int src, int dest, int weight) {

        int valueToAdd = weight;


        if (!weighted) {
            valueToAdd = 1;
        }

        matrix[src][dest] = valueToAdd;
        isSetMatrix[src][dest] = true;

        if (!directed) {
            matrix[dest][src] = valueToAdd;
            isSetMatrix[dest][src] = true;
        }
    }

    public void addEdge(int src, int dest) {

        int valueToAdd = 1;


        if (weighted) {
            valueToAdd = 0;
        }

        matrix[src][dest] = valueToAdd;
        isSetMatrix[src][dest] = true;

        if (!directed) {
            matrix[dest][src] = valueToAdd;
            isSetMatrix[dest][src] = true;
        }
    }

    public void printMatrix() {
        for (int i = 0; i < numOfNodes; i++) {
            for (int j = 0; j < numOfNodes; j++) {
                // We only want to print the values of those positions that have been marked as set
                if (isSetMatrix[i][j])
                    System.out.format("%8s", String.valueOf(matrix[i][j]));
                else System.out.format("%8s", "/  ");
            }
            System.out.println();
        }
    }

    @Override
    public void printGraph() {
        for (int i = 0; i < numOfNodes; i++) {
            System.out.print(i + " : ");
            for (int j = 0; j < numOfNodes; j++) {
                if (isSetMatrix[i][j]) {
                    System.out.print("[" + j + "," + matrix[i][j] + "] ->");
                }
            }
            System.out.println();
        }
    }

    public boolean hasEdge(int source, int destination) {
        //simply look at the boolean matrix
        return isSetMatrix[source][destination];
    }

    public Integer getEdgeValue(int source, int destination) {
        if (!weighted || !isSetMatrix[source][destination])
            return null;
        return matrix[source][destination];
    }

    public int getMinimumVertex(boolean[] mst, int[] key) {
        int minKey = Integer.MAX_VALUE;
        int vertex = -1;
        for (int i = 0; i < numOfNodes; i++) {
            if (mst[i] == false && minKey > key[i]) {
                minKey = key[i];
                vertex = i;
            }
        }
        return vertex;
    }

    @Override //using Dijkstra Algorithm
    public void SSSP(int sourceVertex) {
        int dist[] = new int[numOfNodes];
        boolean[] spt = new boolean[numOfNodes];
        int INFINITY = Integer.MAX_VALUE;


        //initialize all the distance to infinity
        for (int i = 0; i < numOfNodes; i++) {
            dist[i] = INFINITY;
        }

        // begin with source equals zero dist.
        dist[sourceVertex] = 0;

        for (int i = 0; i < numOfNodes; i++) {
            //get the vertex with the minimum distance which is not included in SPT
            int vertex_U = getMinimumVertex(spt, dist);

            //set true of existance in SPT
            spt[vertex_U] = true;

            //iterate through adjacent verticies of current vertex and update the keys
            for (int vertex_V = 0; vertex_V < numOfNodes; vertex_V++) {
                //check the edge between u and v vertex
                if (matrix[vertex_U][vertex_V] > 0) {

                    if (!spt[vertex_V] && matrix[vertex_U][vertex_V] != INFINITY) {
                        //need of changing the key
                        int newKey = matrix[vertex_U][vertex_V] + dist[vertex_U];
                        if (newKey < dist[vertex_V]) {
                            dist[vertex_V] = newKey;
                        }
                    }
                }
            }
        }
        printDijkstra(sourceVertex, dist);
    }

    private void printDijkstra(int sourceVertex, int[] key) {
        System.out.println("Dijkstra Algorithm: (Adjacency Matrix)");
        for (int i = 0; i < numOfNodes; i++) {
            System.out.println("Source Vertex: " + sourceVertex + " to vertex " + +i +
                    " distance: " + key[i]);
        }
    }

    @Override //using Primm's Algorithm
    public void MST() {
        boolean[] mst = new boolean[numOfNodes];
        ResultSet[] resultSet = new ResultSet[numOfNodes];
        int[] key = new int[numOfNodes];

        for (int i = 0; i < numOfNodes; i++) {
            key[i] = Integer.MAX_VALUE;
            resultSet[i] = new ResultSet();
        }
        //start from the vertex 0
        key[0] = 0;
        resultSet[0] = new ResultSet();
        resultSet[0].parent = -1;

        for (int i = 0; i < numOfNodes; i++) {
            //get the vertex with the minimum key
            int vertex = getMinimumVertex(mst, key);
            //set vertex existance in mst
            mst[vertex] = true;
            //iterate through all the adjacent vertices of vertex
            for (int j = 0; j < numOfNodes; j++) {
                if (matrix[vertex][j] > 0) {
                    //check if this vertex 'j' already in mst and
                    //if no then check if key needs an update or no
                    if (!mst[j] && matrix[vertex][j] < key[j]) {
                        //update the key
                        key[j] = matrix[vertex][j];
                        //update the result set
                        resultSet[j].parent = vertex;
                        resultSet[j].weight = key[j];
                    }
                }
            }
        }

        printMST(resultSet);
    }

    public void printMST(ResultSet[] resultSet) {
        int total_min_weight = 0;
        System.out.println("Minimum Spanning Tree: (Prim's Algorithm) ");
        for (int i = 1; i < numOfNodes; i++) {
            System.out.println("Edge: " + i + " - " + resultSet[i].parent +
                    " key: " + resultSet[i].weight);
            total_min_weight += resultSet[i].weight;
        }
        System.out.println("Total minimum key: " + total_min_weight);
    }

    public int verticies() {
        return numOfNodes;
    }
}
