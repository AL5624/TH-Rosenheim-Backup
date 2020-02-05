package de.th_rosenheim.ad.lecture07A;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class UndirectedGraph {


    private final int V;
    private int E;
    private ArrayList<Integer>[] adj;





    // needed for BFS
    private int[] d;

    // needed for DFS
    private int time = 0;
    int[] discoveryTime;
    int[] finishTime;

    // needed for DFS and BFS
    private enum Color {
        WHITE, GRAY, BLACK;
    }
    private Color[] color;
    private int[] pi;

    /**
     * Initializes an empty graph with {@code V} vertices and 0 edges.
     * param V the number of vertices
     *
     * @param  V number of vertices
     * @throws IllegalArgumentException if {@code V < 0}
     */
    public UndirectedGraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (ArrayList<Integer>[]) new ArrayList[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new ArrayList<Integer>();
        }
    }


    /**
     * Returns the number of vertices in this graph.
     *
     * @return the number of vertices in this graph
     */
    public int V() {
        return V;
    }

    /**
     * Returns the number of edges in this graph.
     *
     * @return the number of edges in this graph
     */
    public int E() {
        return E;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    /**
     * Adds the undirected edge v-w to this graph.
     *
     * @param  v one vertex in the edge
     * @param  w the other vertex in the edge
     * @throws IllegalArgumentException unless both {@code 0 <= v < V} and {@code 0 <= w < V}
     */
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        E++;
        adj[v].add(w);
        adj[w].add(v);
    }


    /**
     * Returns the vertices adjacent to vertex {@code v}.
     *
     * @param  v the vertex
     * @return the vertices adjacent to vertex {@code v}, as an iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the degree of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the degree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int degree(int v) {
        validateVertex(v);
        return adj[v].size();
    }


    /**
     * Returns a string representation of this graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + "\n");
        for (int v = 0; v < V; v++) {
            s.append(v + ": ");
            for (int w : adj[v]) {
                s.append(w + " ");
            }
            s.append("\n");
        }
        return s.toString();
    }


    /**
     * Perform a breadth-first search on graph G starting from node s
     * @param s start node
     */
    public void bfs(int s) {

        d = new int[adj.length];                  // u.d: keeps distances for each node to start node
        color = new Color[adj.length];         // u.color: keeps colors for each nodes
        pi = new int[adj.length];                 // u.pi: keeps predecessor in BFS tree
        for (int v = 0; v < V(); v++) {
            d[v] = Integer.MAX_VALUE;
            color[v] = Color.BLACK;
            pi[v] = Integer.MAX_VALUE;          // means that predecessor is not known.
        }

        d[s] = 0;                               // initialize start value
        color[s] = Color.GRAY;


        // TODO
        Queue<Integer> q = new LinkedList<Integer>();
        q.add(s);


        while (!q.isEmpty()) {

            int u = q.remove();
            System.out.println("BFS Visiting Node" + u);
            for (int v : adj(u)) {
                if (d[v] == Integer.MAX_VALUE) {
                    color[v] = Color.GRAY;
                    d[v] = d[u] + 1;
                    pi[v] = u;
                    q.add(v);
                }
                color[u] = Color.BLACK;
            }
        }
    }

    /**
     * Perform a depth-first search on graph G
     */
    public void dfs() {

        discoveryTime = new int[adj.length];
        finishTime = new int[adj.length];
        color = new Color[adj.length];
        pi = new int[adj.length];

        for (int u = 0; u < V(); u++) {
            color[u] = Color.WHITE;
            pi[u] = Integer.MAX_VALUE;
        }
        time = 0;
        for (int u = 0; u < V(); u++) {
            if (color[u] == Color.WHITE) {
                dfs(u);
            }
        }
    }

    /**
     * DFS starting on a particular node
     * @param u start node
     */
    private void dfs(int u) {
        time++;
        System.out.println("DFS - Discovering node " + u + " at time " + time);
        discoveryTime[u] = time;
        color[u] = Color.GRAY;
        // TODO
        for (int v : adj(u)) {
            if (color[v] == Color.WHITE) {
                pi[v] = u;
                dfs(v);  // recursion
            }
        }
        color[u] = Color.BLACK;
        time++;
        finishTime[u] = time;
    }



    /**
     * Unit tests the {@code Graph} data type.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {

        UndirectedGraph G = new UndirectedGraph(10);
        G.addEdge(2,6);
        G.addEdge(2, 8);
        System.out.println(G.toString());
        G.bfs( 2);
        G.dfs();
    }

}
