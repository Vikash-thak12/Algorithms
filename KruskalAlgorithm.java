import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Edge implements Comparable<Edge> {
    int source, destination, weight;

    public Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}

class UnionFind {
    private int[] parent, rank;

    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);

        if (rootX != rootY) {
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootX] = rootY;
                rank[rootY]++;
            }
        }
    }
}

public class KruskalAlgorithm {

    public static void kruskalMST(ArrayList<Edge> edges, int vertices) {
        Collections.sort(edges);

        UnionFind unionFind = new UnionFind(vertices);

        System.out.println("Edge   Weight");

        for (Edge edge : edges) {
            int rootSource = unionFind.find(edge.source);
            int rootDest = unionFind.find(edge.destination);

            if (rootSource != rootDest) {
                System.out.println(edge.source + " - " + edge.destination + "    " + edge.weight);
                unionFind.union(rootSource, rootDest);
            }
        }
    }

    public static void main(String[] args) {
        int vertices = 5;
        ArrayList<Edge> edges = new ArrayList<>();

        edges.add(new Edge(0, 1, 2));
        edges.add(new Edge(0, 3, 6));
        edges.add(new Edge(1, 2, 3));
        edges.add(new Edge(1, 3, 8));
        edges.add(new Edge(1, 4, 5));
        edges.add(new Edge(2, 4, 7));
        edges.add(new Edge(3, 4, 9));

        kruskalMST(edges, vertices);
    }
}