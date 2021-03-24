package graphADT;


public class Edge implements Comparable<Edge>{

	private Vertex first;
	private Vertex second;
	private double weight;
	
	/**
	 *  create and edge between two vertices
	 * @param first
	 * @param second
	 */
	public Edge(Vertex first,Vertex second) {
		this(first,second,1);
		
		
	}
	
	/**
	 * create and edge between two vertices , with weight
	 * @param first
	 * @param second
	 * @param y
	 */
	public Edge(Vertex first, Vertex second, double y) {
		this.weight=y;
		this.first=first;
		this.second=second;
	}


	/**
	 *  compare the current edge weight to the other edge weight
	 */
	@Override
	public int compareTo(Edge otherEdge) {
		return (int) (this.weight - otherEdge.weight);
	}
	
	
	/**
	 * check if the edges have the same vertices
	 */
    public boolean equals(Object otherEdge) {
    	if(!(otherEdge instanceof Edge)) { // check if the other edge is this edge
    		return false;
    	}
    	
    	Edge edge=(Edge) otherEdge; // Get the other edge 
    	Vertex otherFirst=edge.first;
    	Vertex otherSecond=edge.second;
    	
    	return otherFirst.equals(this.first) && otherSecond.equals(this.second);
    }
    
    
	/**
	 *  return the neighbor of the current node
	 * @param currentNode
	 * @return
	 */
	public Vertex getNeighbor(Vertex currentNode) {
		
		if(!(currentNode==first || currentNode==second)) {
			return null; 
		}
		
		return currentNode.equals(first) ? second : first; 
	}
	
	/** set and get methods 
	 * @return
	 */
	public Vertex getFirst() {
		return this.first;
	}
	public Vertex getSecond() {
		return this.second;
	}
	public double getWeight() {
		return weight;
	} 
	public void setWeight(double weight) {
		this.weight=weight;
	}
	
	/** @return String A String representation of this Edge
	 */
	public String toString() {
		return "["+first+"--"+second+",edge weight :"+weight+"]";
	}
	
	/**
	 *  @return integer The hash code for this Edge
	 */
	public int hashCode() {
		return (first.getNodeLabel()+second.getNodeLabel()).hashCode();
	}
	
}
