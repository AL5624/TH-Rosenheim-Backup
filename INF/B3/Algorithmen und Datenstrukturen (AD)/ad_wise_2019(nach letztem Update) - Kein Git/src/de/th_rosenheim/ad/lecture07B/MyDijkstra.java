package de.th_rosenheim.ad.lecture07B;


import java.util.Stack;

public class MyDijkstra {

    private double[] dist;                 // distTo[v] = distance  of shortest s->v path
    private int[] pred;                    // predecessor ("pi") in shorted path tree pi
    private int start;                     // what is the start node

    /**
     * Computes a shortest-paths tree from the source vertex {@code s} to every other
     * vertex in the edge-weighted digraph {@code G}.
     *
     * @param  G the edge-weighted digraph
     * @param  s the source vertex
     * @throws IllegalArgumentException if an edge weight is negative
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    // computes a shortest-path tree from source vertex s to every other vertex in the edge-weighted digraph G
    public MyDijkstra(EdgeWeightedDiGraph G, int s) {
        for (DirectedEdge e : G.edges()) {
            if (e.weight() < 0)
                throw new IllegalArgumentException("edge " + e + " has negative weight");
        }

        dist = new double[G.V()];           // set all distances to infinity
        for (int v = 0; v < G.V(); v++)
            dist[v] = Double.POSITIVE_INFINITY;

        pred = new int[G.V()];               // set all predecessors to -1
        for (int v = 0; v < G.V(); v++)
            pred[v] = -1;

        dist[s] = 0.0;              // set distance and pred of start node
        pred[s] = s;


        // add vertices in order of distance from s  -> priority queue
        MyPriorityQueue pq = new MyPriorityQueue<Double>(G.V());
        pq.insert(s, dist[s]);

        while (!pq.isEmpty()) {

            // extract node with lowest key from p priority queue
            int u = pq.extractMin();
            System.out.println("Visiting node " + u);

            // iterate over all edges that start from u
            for (DirectedEdge edge : G.adj(u)) {
                int v = edge.to();
                double newDist = dist[u] + edge.weight();
                if (newDist < dist[v]) {
                    System.out.println("New shortest distance for node " + v + " is " + newDist);
                    dist[v] = newDist;
                    pred[v] = u;
                    if (pq.contains(v))
                        pq.decreaseKey(v, dist[v]);
                    else
                        pq.insert(v, dist[v]);
                }
            }
        }
    }

    // returns length of a shortest path from the source vertex s to vertex v
    public double distTo(int v) {
        return dist[v];
    }


    // is there a path from source s to vertex v?
    public boolean hasPathTo(int v) {
        return dist[v] < Double.POSITIVE_INFINITY;
    }

    // returns a shorted path from source s to vertex v
    public Iterable<Integer> pathTo(int v) {
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
        DirectedEdge e2 = new DirectedEdge(1, 2, 1);
        DirectedEdge e3 = new DirectedEdge(2, 0, 4);
        graph1.addEdge(e1);
        graph1.addEdge(e2);
        graph1.addEdge(e3);

        MyDijkstra dj = new MyDijkstra(graph1, 0);
        System.out.println("Graph sieht so aus: " + graph1.toString());
    }
}


