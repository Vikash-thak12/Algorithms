import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Edge implements Comparable<Edge> {
    int destination, weight;

    public Edge(int destination, int weight) {
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}


// Main function of the code
public class PrimAlgorithm {

    public static void primMST(int[][] graph) {
        int vertices = graph.length;
        boolean[] visited = new boolean[vertices];
        int[] parent = new int[vertices];
        int[] key = new int[vertices];

        Arrays.fill(key, Integer.MAX_VALUE);
        key[0] = 0;
        parent[0] = -1;

        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Edge(0, 0));

        while (!priorityQueue.isEmpty()) {
            int u = priorityQueue.poll().destination;
            visited[u] = true;

            for (int v = 0; v < vertices; v++) {
                if (graph[u][v] != 0 && !visited[v] && graph[u][v] < key[v]) {
                    key[v] = graph[u][v];
                    parent[v] = u;
                    priorityQueue.add(new Edge(v, key[v]));
                }
            }
        }

        // Calling the primMst Function
        printMST(parent, graph);
    }

    private static void printMST(int[] parent, int[][] graph) {
        System.out.println("Edge   Weight");
        for (int v = 1; v < graph.length; v++) {
            System.out.println(parent[v] + " - " + v + "    " + graph[v][parent[v]]);
        }
    }

    // Actual input i am giving which is the graph
    public static void main(String[] args) {
        int[][] graph = {
                {0, 2, 0, 6, 0},
                {2, 0, 3, 8, 5},
                {0, 3, 0, 0, 7},
                {6, 8, 0, 0, 9},
                {0, 5, 7, 9, 0}
        };

        primMST(graph);
    }
}
