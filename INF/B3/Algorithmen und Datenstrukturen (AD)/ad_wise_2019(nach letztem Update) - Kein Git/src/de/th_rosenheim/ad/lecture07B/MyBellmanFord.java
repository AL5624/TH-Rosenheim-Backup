package de.th_rosenheim.ad.lecture07B;

import java.util.Stack;

// taken from Sedgewick et al, adjusted by W. Mühlbauer
public class MyBellmanFord {

    private double[] dist;                 // distTo[v] = distance  of shortest s->v path
    private int[] pred;                    // predecessor ("pi") in shorted path tree pi
    private boolean hasNegativeCycle;      // negative cycle detected
    private int start;                     // what is the start node

    // computes a shortest path tree from s to every other vertex in the edge-weighted digraph
    public MyBellmanFord(EdgeWeightedDiGraph G, int s) {
        hasNegativeCycle = false;
        this.start = s;
        dist = new double[G.V()];   // set all distances to infinity
        for (int v = 0; v < G.V(); v++)
            dist[v] = Double.POSITIVE_INFINITY;

        pred = new int[G.V()];      // set all predecessors to -1
        for (int v = 0; v < G.V(); v++)
            pred[v] = -1;

        dist[s] = 0.0;              // set distance and pred of start node
        pred[s] = s;

        // Calculate distances, iterate |V|-1 times
        for (int i = 0; i < G.V() - 1; i++) {

            // iterate over all edges (u,v)
            // TODO
            for (DirectedEdge edge : G.edges()) {
                int u = edge.from();
                int v = edge.to();
                double newDist = dist[u] + edge.weight();
                if (newDist < dist[v]) {
                    dist[v] = newDist;
                    pred[v] = u;
                }
            }
        }

        // last round to detect possibly existing negative cycles
        for (DirectedEdge edge: G.edges()) {
            int u = edge.from();
            int v = edge.to();
            double newDist = dist[u] + edge.weight();
            if (newDist < dist[v]) {
                hasNegativeCycle = true;
            }
        }
    }



    // is there a negative cycle reachable from source vertex s
    public boolean hasNegativeCycle() {
        return hasNegativeCycle;
    }

    // returns length of a shortest path from the source vertex s to vertex v
    public double distTo(int v) {
        if (hasNegativeCycle())
            throw new UnsupportedOperationException("Negative cost cycle exists");
        return dist[v];
    }


    // is there a path from source s to vertex v?
    public boolean hasPathTo(int v) {
        return dist[v] < Double.POSITIVE_INFINITY;
    }

    // returns a shorted path from source s to vertex v
    public Iterable<Integer> pathTo(int v) {
        if (hasNegativeCycle())
            throw new UnsupportedOperationException("Negative cost cycle exists");
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int node = v; node != start; node = pred[node]) {
            path.push(node);
        }
        return path;
    }





    public static void main(String[] args) {
        EdgeWeightedDiGraph graph1 = new EdgeWeightedDiGraph(3);
        DirectedEdge e1 = new DirectedEdge(0, 1, 1);
        DirectedEdge e2 = new DirectedEdge(1, 2, 2);
        DirectedEdge e3 = new DirectedEdge(2, 0, -4);
        graph1.addEdge(e1);
        graph1.addEdge(e2);
        graph1.addEdge(e3);

        MyBellmanFord bf = new MyBellmanFord(graph1, 0);
        boolean negativeCycle = bf.hasNegativeCycle();
        System.out.println("Graph sieht so aus: " + graph1.toString());
        System.out.println("Der Graph enthält EINEN negativen Zyklus? " + negativeCycle);

    }

}

