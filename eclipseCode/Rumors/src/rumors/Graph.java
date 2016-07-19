package rumors;

import java.util.ArrayList;

public class Graph {
	
	private class Vertex{
		private ArrayList<Edge> neighbor;
		private int id;
		
		public Vertex(int id){
			this.neighbor = new ArrayList<Edge>();
			this.id = id;
		}
		public void addNeighbot(Edge e){
			if(this.neighbor.contains(e)){
				return;
			}
			this.neighbor.add(e);
		}
		public boolean containsNeighbor(Edge e){
			return this.neighbor.contains(e);
		}
		public Edge getNeighbor(int index){
			return this.neighbor.get(index);
		}
		public void removeNeighbor(Edge e){
			this.neighbor.remove(e);
		}
		public int getNeighborSize(){
			return this.neighbor.size();
		}
		public int getID(){
			return this.id;
		}
		public ArrayList<Edge> getNeighbors(){
			return new ArrayList<Edge>(this.neighbor);
		}
	}
	private class Edge{
			private Vertex one, two;
			private int weight;
	}
}

