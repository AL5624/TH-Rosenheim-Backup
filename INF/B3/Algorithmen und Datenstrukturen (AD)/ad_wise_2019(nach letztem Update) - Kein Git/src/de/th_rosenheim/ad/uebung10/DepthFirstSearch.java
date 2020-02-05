package de.th_rosenheim.ad.uebung10;

import java.util.Stack;

/**
 * from Sedgewick et al., adjusted by W. Mühlbauer
 */
public class DepthFirstSearch {

    private int time;                   // used as timer during DFS
    private int[] discoveryTime;
    private int[] finishTime;

    // used to color nodes during DFS
    private enum Color {
        WHITE, GRAY, BLACK;
    }
    private Color[] color;              // used for coloring nodes during DFS

    /**
     * Performs a DFS  on graph {@code G} starting from source vertex {@code s}.
     *
     * @param G the graph
     * @param s the source vertex
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     */
    public DepthFirstSearch(Graph G, int s) {

        discoveryTime = new int[G.V()];
        finishTime = new int[G.V()];
        color = new Color[G.V()];
        time = 0;

        for (int u = 0; u < G.V(); u++) {
            color[u] = Color.WHITE;
        }

        //dfs(G, s);       // use this one, if normal DFS with recursion should be used instead
        iterativeDFS(G, s);
    }



    // recursive version of DFS, see lecture
    private void dfs(Graph G, int u) {
        time++;
        discoveryTime[u] = time;
        color[u] = Color.GRAY;
        for (int v : G.adj(u)) {
            if (color[v] == Color.WHITE) {
                 dfs(G, v);  // recursion
            }
        }
        color[u] = Color.BLACK;
        time++;
        finishTime[u] = time;
    }
ö

    // TODO: iterative version of DFS, DFS shall be started from node u
    private void iterativeDFS(Graph G, int u) {

        time = 1;

        Stack<Integer> stack = new Stack<Integer>();
        discoveryTime[u] = 1;
        color[u] = Color.GRAY;

        stack.push(u);

        while (!stack.isEmpty()) {
            // peeks leaves node on stack, only returns node on top of stack
            int currentNode = stack.peek();

            // check if there is any WHITE neighbor of currentNode that hasn't been seen yet
            int undiscoveredNode = -1;
            for (int n : G.adj(currentNode)) {
                if (color[n] == Color.WHITE) {
                    undiscoveredNode = n;
                    break;
                }
            }
            if (undiscoveredNode != -1) {        // mark it as discovered, go into depth!
                time++;
                discoveryTime[undiscoveredNode] = time;
                color[undiscoveredNode] = Color.GRAY;
                System.out.println("Node " + undiscoveredNode + "-> Discovering / GRAY");
                stack.push(undiscoveredNode);
            } else {                             // all neighbors of current nodes have already been discovered
                stack.pop();
                time++;
                color[currentNode] = Color.BLACK;
                finishTime[currentNode] = time;
                System.out.println("Node " + currentNode + "-> Finishing / BLACK");
            }

        }
    }



    public static void main(String[] args) {
        Graph G = new Graph(4);
        G.addEdge(0, 1);
        G.addEdge(1, 2);
        G.addEdge(2, 3);
        G.addEdge(3, 1);
        G.addEdge(0, 3);


        DepthFirstSearch dfs = new DepthFirstSearch(G, 0);
        for (int v = 0; v < G.V(); v++) {
            System.out.println("Node " + v + " has discovery time " + dfs.discoveryTime[v] + " and finish time " + dfs.finishTime[v]);
        }
    }

}

