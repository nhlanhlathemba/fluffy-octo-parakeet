package graphADT;
import java.util.ArrayList;
/**
 * @author 
 * 
	 * This class models a vertex in a graph.
	 * For ease of the reader, a label for this vertex is required.
	 * Note that the Graph object only accepts one Vertex per label,
	 * so uniqueness of labels is important.
	 * This vertex's neighborhood is described by the Edges incident to it.
 */
public class Vertex {

	private ArrayList<Edge> neighborhood;
	//private List<Edge> neighborhood;
	private String nodeLabel;
	
	private int x;
	private int y;
	private Vertex prev;
	private double distance;
	private boolean Listed;
	
	/**
	 * Vertex Constructor
	 * @param nodeLabel
	 * @param x
	 * @param y
	 */
	public Vertex(String nodeLabel, int x, int y) {
		this.nodeLabel=nodeLabel;
		neighborhood=new ArrayList<Edge>();
		//neighborhood=new LinkedList<Edge>();
		this.x=x;
		this.y=y;
		this.prev=null;
		
	}
	
	
	
	
	/**
	 *  returns true if the edge is contained( a node has an edge)
	 * @return
	 */
	public boolean hasEdge(Edge edge) {
		return this.getNeighborhood().contains(edge);
	}
	
	/**
	 *  get the edge
	 * @param index
	 * @return
	 */
	public Edge getEdge(int index) {
		return this.getNeighborhood().get(index);
	}
	/**
	 *  remove edge at the specified index
	 * @param index
	 * @return
	 */
	public Edge removeEdge(int index) {
		return this.getNeighborhood().remove(index);
	}
	
	/**
	 * The edge to remove 
	 * @param e
	 */
	public void removeEdge(Edge e) {
		this.getNeighborhood().remove(e);
	}
	
	/**
	 *  return the number of edges
	 * @return
	 */
	public int getEdgeCount() {
		return this.getNeighborhood().size();
	}
	
	/**
	 *  get this node label
	 * @return
	 */
	public String getNodeLabel() {
		return this.nodeLabel;
	}
   /*---------------------------------------------------------------------------------------------*/
	/**
	 * 
	 * @return
	 */
	public int getX() {
		return x;
	}
     
    /**
     * 
     * @param x
     */
	public void setX(int x) {
		this.x = x;
	}

    /**
     * 
     * @return
     */
	public int getY() {
		return y;
	}

    /**
     * 
     * @param y
     */
	public void setY(int y) {
		this.y = y;
	}

    /**
     * 
     * @return
     */
	public ArrayList<Edge> getNeighborhood() {
		return neighborhood;
	}

   /**
    * 
    * @param neighborhood
    */
	public void setNeighborhood(ArrayList<Edge> neighborhood) {
		this.neighborhood = neighborhood;
	}
	
	public Vertex getPrev() {
		return prev;
	}


	public void setPrev(Vertex prev) {
		this.prev = prev;
	}


	public double getDistance() {
		return distance;
	}


	public void setDistance(double distance2) {
		this.distance = distance2;
	}


	public void setNodeLabel(String nodeLabel) {
		this.nodeLabel = nodeLabel;
	}


	public boolean isListed() {
		return Listed;
	}


	public void setListed(boolean listed) {
		Listed = listed;
	}
	/**
	 *  return this vertex hash code
	 */
	public int hashCode() {
		return this.nodeLabel.hashCode();
	}
	
	/**
	 * Add Edge
	 * @param edge
	 */
	public void addEdge(Edge edge) {
		
		//if(this.getNeighborhood().contains(edge)) {  // check has alredy an edge
		//	return;
		//}
		neighborhood.add(edge);
		//getNeighborhood().add(edge);
	}
	/**
	 *  checks if the node share the name
	 * @param otherNode
	 * @return
	 */
	public boolean equalLabel(Object otherNode) {
		if (otherNode == null) {
		      return false;
		    }
		if(!(otherNode instanceof Vertex)) {
			return false;
		}
		if (this == otherNode) {
		      return true;
		    }
		
		
		Vertex e=(Vertex) otherNode;  // cast object to node
		return this.nodeLabel.equals(e.nodeLabel);
	}
	
	/**
	 *  Vertex string representation
	 */
	public String toString() {
		return nodeLabel  + " [" + x + " " + y + "]";
	}
}
