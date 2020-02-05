package de.th_rosenheim.ad.uebung11;

import java.util.ArrayList;

public class HausDesNikolaus {
	
	// adjacency matrix: nodes are numbered from 0 to 4, see picture
	// a[i][j]==true means that there exists edge between node i and node j
	private static boolean adjMatrix[][] = { 
		 { false, true,  true,  true, false },
	     { true,  false, true,  true, false },
		 { true,  true,  false, true,  true },
		 { true, true, true,  false, true },
		 { false,  false,  true,  true,  false }
	};
	

	// sequence of visited nodes (8 edges must be crossed-> final sol array must contain 9 nodes)
	private static ArrayList<Integer> sol = new ArrayList<Integer>();
	
	// counts number of found solutions
	private static int solCount;
	
	
	private static void printSolution() {
		System.out.print("Loesung " + solCount + ": ");
		for (int i = 0; i < sol.size(); i++) {
			System.out.print(sol.get(i) + " ");
		}
		System.out.println();
	}
	
	// recursive backtracking
	// nodeCount: ".-th" node is now visited
	public static void back() {
	
		// solution found: we reached 9th node -> 8 edges passed
		if (sol.size() == 9) {
			solCount++;
			printSolution();
		}
		
		// not yet there at a complete solution
		else {

			// try out each neighbor of node that was added last (current Pos)
			int currentIndex = sol.size() - 1;
			int currentNode = sol.get(currentIndex);

			for (int i = 0; i < 5; i++) {     // all edges 0, 1, 2, 3, 4, 5, see figure

				if (adjMatrix[i][currentNode]) {     // does edge exist to this neighbor?

					// add node to solution, solution grows
					sol.add(i);
					
					// delete edge from adjMatrix to avoid that it is later in recursion used again
					adjMatrix[i][currentNode] = false;
					adjMatrix[currentNode][i] = false;

					// continue, add solution step
					back();
					
					// re-add edge from adjMatrix to continue search for solution in "other directions"
					adjMatrix[i][currentNode] = true;
					adjMatrix[currentNode][i] = true;
					sol.remove(sol.size() - 1);

				}
			}
		}
	}
	
	
	public static void main(String[] args) {
		solCount = 0;
		sol.add(0); 			// start with node 0
		back();
	}

}
