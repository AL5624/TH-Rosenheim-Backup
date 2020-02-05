package de.th_rosenheim.ad.lecture07B;

import java.util.ArrayList;
import java.util.List;

// taken from Sedgewick et al., adjusted by W. Mühlbauer
public class EdgeWeightedDiGraph {

    private final int V;                    // number of vertices in this digraph
    private int E;                          // number of edges in this digraph
    private ArrayList<DirectedEdge>[] adj;  // adj[v] = adjacency list for vertex v

    private int[] indegree;             // indegree[v] = indegree of vertex v


    // initialize an empty edge-weighted digraph with V vertices and 0 edges
    public EdgeWeightedDiGraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.V = V;
        this.E = 0;
        this.indegree = new int[V];
        adj = (ArrayList<DirectedEdge>[]) new ArrayList[V];
        for (int v = 0; v < V; v++)
            adj[v] = new ArrayList<DirectedEdge>();
    }

    // returns the number of vertices in this edge-weighted digraph.
    public int V() {
        return V;
    }

    // returns number of edges in this edge-weighted digraph
    public int E() {
        return E;
    }


    // Adds the directed edge e to this edge-weighted digraph.
    public void addEdge(DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        adj[v].add(e);
        indegree[w]++;
        E++;
    }

    // returns directed edges incident from vertex v
    public Iterable<DirectedEdge> adj(int v) {
        return adj[v];
    }

    // returns number of directed edges incident from vertex v
    public int outdegree(int v) {
        return adj[v].size();
    }

    // returns number of directed edges incident to vertex v
    public int indegree(int v) {
        return indegree[v];
    }


    // returns all directed edges in this edge-weighted digraph
    public Iterable<DirectedEdge> edges() {
        List<DirectedEdge> list = new ArrayList<DirectedEdge>();
        for (int v = 0; v < V; v++) {
            for (DirectedEdge e : adj(v)) {
                list.add(e);
            }
        }
        return list;
    }

    // returns a string representation of thsi edge-weighted digraph
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " " + E + "\n");
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (DirectedEdge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append("\n");
        }
        return s.toString();
    }



}
