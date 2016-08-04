import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Main {
	private static int numR = 0, numNodes = 0, totalArmy = 0, numBoarder;
	private static int[] ArmyType = null;
	private static Graph g = null;
	private static int[][] cap = null;
	private static Stack<Integer> st = null;

	public static void main(String[] args) throws FileNotFoundException {
//		File in = new File("res/example.in");
//		Scanner scan = new Scanner(in);
		 Scanner scan = new Scanner(System.in);
		// user input
		int numCase = scan.nextInt();

		int[] ArmyArr = null, EnemyType = null;

		// manage input for each case
		for (int i = 0; i < numCase; i++) {
			// get number of regions
			numR = scan.nextInt();

			// to construct flow graph

			numNodes = numR * 2 + 2;
			totalArmy = 0;
			numBoarder = 0;
			ArmyArr = new int[numR];
			ArmyType = new int[numR]; // region = 0, boarder = 1.
			EnemyType = new int[numR];// not enemy = 0, enemy = 1.

			g = new Graph(numNodes);
			cap = new int[numNodes][numNodes];

			// get number of armies on each region
			// and construct graph g

			for (int j = 0; j < numR; j++) {
				ArmyArr[j] = scan.nextInt();
				totalArmy += ArmyArr[j];
				if (ArmyArr[j] == 0) {
					EnemyType[j] = 1; // is enemy region
				} else {
					g.getNode(j).addEdge(j + numR, ArmyArr[j]);
					cap[j][j + numR] = ArmyArr[j];
				}
			}

			// get connectivities, and number of boarders
			for (int row = 0; row < numR; row++) {
				String line = scan.next();
				for (int col = 0; col < numR; col++) {
					if (line.charAt(col) == 'Y' && EnemyType[row] != 1) {
						if (EnemyType[col] == 1) {
							if (ArmyType[row] != 1) {
								ArmyType[row] = 1;
								numBoarder = numBoarder + 1;
							}
						} else {
							// connect v' to enemy, with cap v to v'
							cap[row + numR][col] = cap[row][row + numR];
							g.getNode(row + numR).addEdge(col, cap[row][row + numR]);
						}
					}
				}
			}
			// System.out.println("cap: " + Arrays.deepToString(cap));

			// connect node s to each v, with cap v to v'
			for (int m = 0; m < numR; m++) {
				if (EnemyType[m] != 1) {
					cap[numR * 2][m] = cap[m][m + numR] - 1;
					g.getNode(numR * 2).addEdge(m, cap[m][m + numR] - 1);
				}
			}
//			System.out.println("cap: " + Arrays.deepToString(cap));
//			System.out.println("totalArmy: " + totalArmy);
//			System.out.println("ArmyType: " + Arrays.toString(ArmyType));
//			System.out.println("EnemyType: " + Arrays.toString(EnemyType));
//			System.out.println("numBoarder: " + numBoarder);

			int answer = bSearch(0, totalArmy / numBoarder);
			System.out.println(answer);

		} // End for loop
		scan.close();
	}

	private static int bSearch(int l, int r) {
		int a, b;
		a = (l + r) / 2;
		if ((l + r) % 2 != 0) {
			a++;
		}
		// base case
		if (l == r) {
			return l + 1;
		}
		for (int i = 0; i < numR; i++) {
			if (ArmyType[i] == 1) {
				if (g.getNode(i).getAdjV(g.getNode(i).getListV().size() - 1) != (numNodes - 1)) {
					cap[i][numNodes - 1] = a;
					g.getNode(i).addEdge(numNodes - 1, a);
				}
			}
		} // end for loop

		b = mFlow(numNodes - 2, numNodes - 1);
		
//		System.out.println("numNodes: " + numNodes);
//		System.out.println("numR: "  + numR);
//		System.out.println("mFlow!!");

		for (int i = 0; i < numNodes; i++) {
			for (int j = 0; j < g.getNode(i).getListV().size(); j++) {
				cap[i][g.getNode(i).getAdjV(j)] = g.getNode(i).getAdjCap(j);
			}
		}

		if (b < numBoarder * a) {
			return bSearch(l, a - 1);
		} else {
			return bSearch(a, r);
		}
	}

	private static int mFlow(int s, int t) {
		st = new Stack<Integer>();
		int p, n, bfsV, fl;

		while ((bfsV = bfs(s, t, st)) > 0) {
//			System.out.println("bfsV: " + bfsV);
			p = st.peek();
			st.pop();

			while (!st.isEmpty()) {
				n = st.peek();
				st.pop();
				cap[p][n] = cap[p][n] - bfsV;

				if (cap[n][p] == 0) {
					g.getNode(n).addEdge(p, 0);
				}
				cap[n][p] = cap[n][p] + bfsV;
				p = n;
			} // End inner while loop
		} // End outer while loop
//		System.out.println("done while loop in max flow!");
		fl = 0;
//		System.out.println("g node s list v size: " + g.getNode(s).getListV().size());
		for (int i = 0; i < g.getNode(s).getListV().size(); i++) {
			int temp = g.getNode(s).getAdjV(i);
			fl = fl + cap[temp][s];
		}
		return fl;
	}

	private static int bfs(int s, int t, Stack<Integer> st) {
		boolean isDone = false;
		int v, min;
		int[] pre = new int[250];
		Queue<Integer> q = new LinkedList<Integer>();
		for (int i = 0; i < numNodes; i++) {
			g.getNode(i).setVisited(false);
		}
		g.getNode(s).setVisited(true);
		pre[s] = -1;
		q.add(s);
		while (!q.isEmpty() && !isDone) {
			v = q.peek();
			q.remove();
			for (int i = 0; i < g.getNode(v).adjV.size() && !isDone; i++) {
				int adjV = g.getNode(v).getAdjV(i);
				if (cap[v][adjV] != 0) {
					if (!g.getNode(adjV).isVisited()) {
						g.getNode(adjV).setVisited(true);
						pre[adjV] = v;
						q.add(adjV);
						if (adjV == t) {
							isDone = true;
						}
					}
				}
			} // End for loop
		} // End while loop
		
//		System.out.println("done while loop in bfs!");
		
		if (isDone) {
			min = cap[pre[t]][t];
			v = t;
			while (v != s) {
				st.push(v);
				if (cap[pre[v]][v] < min) {
					min = cap[pre[v]][v];
				}
				v = pre[v];
			}
			st.push(s);
			return min;
		}
		return -1;

	}

	static class Graph {
		private ArrayList<Node> g;

		public Graph(int numNodes) {
			g = new ArrayList<Node>();
			for (int i = 0; i < numNodes; i++) {
				g.add(new Node());
			}
		}

		public Node getNode(int index) {
			return g.get(index);
		}
	}

	static class Node {
		private ArrayList<Integer> adjV, adjCap;
		private boolean visited;

		public Node() {
			adjV = new ArrayList<Integer>();
			adjCap = new ArrayList<Integer>();
			this.visited = false;
		}

		public void addEdge(int v, int cap) {
			adjV.add(v);
			adjCap.add(cap);
		}

		public int getAdjV(int index) {
			return adjV.get(index);
		}
		public ArrayList<Integer> getListV(){
			return adjV;
		}

		public int getAdjCap(int index) {
			return adjCap.get(index);
		}
		public ArrayList<Integer> getListC(){
			return adjCap;
		}

		public boolean isVisited() {
			return visited;
		}

		public void setVisited(boolean visited) {
			this.visited = visited;
		}
	}
}
