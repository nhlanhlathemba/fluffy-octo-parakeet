package graphADT;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;



/** This class models a simple, undirected graph using an
	 * incidence list representation. Vertices are identified
	 * uniquely by their labels, and only unique vertices are allowed.
	 * At most one Edge per vertex pair is allowed in this Graph.
	 * Note that the Graph is designed to manage the Edges. You
	 * should not attempt to manually add Edges yourself.
	 */
public class Graph {

	private HashMap<String,Vertex> vertices;  // vertices containing string onjects
	private HashMap<Integer,Edge> edges;  //edges with weights
	
	private StringBuilder builder;
	
	
	public Graph() {
		vertices=new HashMap<String,Vertex>();
		edges=new HashMap<Integer,Edge>();	
	}
	
	/** this constructor accepts an ArrayList<Nodes> and populates this vertexs
        If multiple Nodes have the same label then the last Vertex with the 
        given label is used
     */
	public Graph(ArrayList<Vertex> vertices) {
		
		this.vertices=new HashMap<String,Vertex>();
		this.edges=new HashMap<Integer,Edge>();
		
		for(Vertex n: vertices) {
			this.vertices.put(n.getNodeLabel(), n);  // put vertices in an arraylist
		}
	}
	
	/*--------------------------------------------------------------------------------------------
	 *                       EDGES
	 *-------------------------------------------------------------------------------------------*/
	
	public boolean addEdge(Vertex first,Vertex second) {
		return addEdge(first,second,1);
	}
	/** Accepts two vertices and a weight and add an edge if no edge relating the two exist in the graph
	 * @param first
	 * @param second
	 * @param weight
	 * @return true if no edge already exists in the graph
	 */
	public boolean addEdge(Vertex first,Vertex second,int weight) {  /**/
		
		// Vertex level
		if(first==second) {
			return false;    // edge already exist  
		}
		// key level  ensures the Edge is not in the Graph
		Edge edge=new Edge(first,second,weight);
		
		if(edges.containsKey(edge.hashCode())) {
			return false;  // edge already exist
		}
		//and that the Edge isn't already incident to one of the vertices
		else if(first.hasEdge(edge) || second.hasEdge(edge)) {
			return false;
		}
		
		edges.put(edge.hashCode(), edge);
		first.addEdge(edge);
		second.addEdge(edge);
		
		return true;
	}
	
	/**
	 * checks if the map contains the edge
	 * @param edge
	 * @return
	 */
	public boolean containsEdge(Edge edge) {
		
		if(edge.getFirst()==null || edge.getSecond()==null) {
			return false;
		}
		
		return this.edges.containsKey(edge.hashCode());
	}
	
	/**
	 *  remove the specified edge
	 * @param edge
	 * @return
	 */
	public Edge removeEdge(Edge edge) {
		
		edge.getFirst().removeEdge(edge);
		edge.getSecond().removeEdge(edge);
		
		return edges.remove(edge.hashCode());
	}
	
	
	/*--------------------------------------------------------------------------------------------
	 *                       VERTICES
	 *-------------------------------------------------------------------------------------------*/
	/**
	 *  checks if the map contains the node
	 * @param vertex
	 * @return
	 */
	public boolean containsNode(Vertex vertex) {
		return this.vertices.get(vertex.getNodeLabel()) !=null;
	}
	/**
	 *  get the node from the map
	 * @param label
	 * @return
	 */
	public Vertex getNode(String label) {
		return vertices.get(label);
	}
	
	/**
	 *  add node to the graph
	 * @param vertex
	 * @param overwrite
	 * @return
	 */
	public boolean addNode(Vertex vertex,boolean overwrite) {
		
		Vertex current=this.vertices.get(vertex.getNodeLabel());
		
		if(current !=null) { //check if does exist
			
			if(!overwrite) {  //if not overwritten
				return false;
			}
			// the Edges incident to it are all removed from the Graph.
			while(current.getEdgeCount()>0) {
				this.removeEdge(current.getEdge(0));
			}
		}
		
		// then add a new node
		vertices.put(vertex.getNodeLabel(), vertex);
		return true;
	}
	/**
	 *  remove the node with that label
	 * @param label
	 * @return
	 */
	public Vertex removeNode(String label) {
		
		Vertex vertex=vertices.remove(label);  // get the node remove
		
		while(vertex.getEdgeCount()>0) {  
			
			this.removeEdge(vertex.getEdge(0));  // remove its neighbours
		}
		
		return vertex;
	}
	
	/**
	 *  return unique labels of the graph node objects
	 * @return
	 */
	public Set<String> nodeKeys(){
		return this.vertices.keySet();
	}
	
	/**
	 *  return edegs of this graph
	 * @return
	 */
	public Set<Edge> getEdges(){
		
		HashSet<Edge> hash=new HashSet<Edge>(this.edges.values());
		
		return hash;
	}
    
	/*-------------------------------------------------------------------
	 *                 ALGORITHMS          
	 -------------------------------------------------------------------*/
	/**
	 * Calculate distance between two points
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 * @return
	 */
	public double calculateDistance(double x1,double y1,double x2,double y2) {
		
		double distanceX=Math.pow(x1-x2, 2);
		double distanceY=Math.pow(y1-y2, 2);
		
		return Math.sqrt(distanceX+distanceY);
	}
	/**
	 * Calculate distance of all edges
	 */
	public void calculateAllDistances() {
		for(Vertex vetices: getVertices()) {
			for(Edge edges : vetices.getNeighborhood()) {
				Vertex vect= edges.getSecond();
				double distance=calculateDistance(vetices.getX(),vect.getY(),vect.getX(),vetices.getY());
				edges.setWeight(distance);
			}
		}
	}
	/*--------------------------------------------------------------------------------------------
	 *                      SHORTEST PATH 
	 *-------------------------------------------------------------------------------------------*/
	
	public List<Edge> getShortestPath(String from,String destination){
		
		//ShortestPath(from);
		List<Edge> list=new LinkedList<>();
		
		Vertex v1=vertices.get(destination);
		
		while(v1.getPrev() != null) 
		{   
			double distance =calculateDistance(v1.getPrev().getX(), v1.getPrev().getY(),v1.getX(), v1.getY());
			Edge e=new Edge(v1.getPrev(),v1,distance);
			list.add(e);
			v1=v1.getPrev();
		}
		
		return list;
		
	}
	
 /**    public void ShortestPath(String from) {
    	PriorityQueue<CompareAlgorithms> queue =new PriorityQueue<>();
    	
		for(Vertex vertex : getVertices())  
		{
			vertex.setDistance(Double.POSITIVE_INFINITY);
			vertex.setListed(false);
			vertex.setPrev(null);
		}
		
		Vertex v1=vertices.get(from);
		v1.setListed(true);
		v1.setDistance(0.0);
		queue.offer(new CompareAlgorithms(v1,0.0));
		
		
		next(v1,queue);
		
	}
	
     public void next(Vertex v1,PriorityQueue<CompareAlgorithms> queue) {
 		
 		while(!queue.isEmpty()) {
 			CompareAlgorithms next=queue.poll();  
 			v1=next.getVertex();
 			v1.setListed(true);
 			
 			
 			for(Edge edges : v1.getNeighborhood()) {
 				Vertex v2=edges.getSecond();
 				double distance=edges.getWeight() + v1.getDistance();
 				
 				if(!v2.isListed() && distance < v2.getDistance() ) {
 					v2.setPrev(v1);
 					v2.setDistance(distance);
 					queue.offer(new CompareAlgorithms(v2,v2.getDistance()));
 				}
 			}
 		}
 	}
     /**
      * Graph representation 
      */
     public String toString() {
    	 String result="";
    	 builder=new StringBuilder();
    	 
    	 for(String r:vertices.keySet()) {
    		 builder.append(r);
    		 builder.append("[");
    		 
    		 for(Edge edge : vertices.get(r).getNeighborhood()) {
    			 
    			 builder.append(edge.getSecond().getNodeLabel());
    			 builder.append("-");
    			 builder.append(edge.getWeight());
    			 builder.append("km");
    		 }
    		 
    		 builder.append("]");
    		 builder.append("\n");
    		 result=builder.toString();
    	 }
    	 
    	 return result;
     }
     
     /*--------------------------------------------------------------------------------------------
 	 *                      GRAPH
 	 *-------------------------------------------------------------------------------------------*/
     /**
 	 * Adds vertex to the graph
 	 * @param vertex
 	 */
 	public void addVertex(Vertex vertex) {
 		if(vertices.containsKey(vertex.getNodeLabel())) {
 			throw new IllegalArgumentException("Cannot create new vertex with existing name");}	
 		vertices.put(vertex.getNodeLabel(), vertex);
 		
 	}
     /** 
      * iterate through vertices in the graph
      * @return
      */
 	public Collection<Vertex> getVertices() {
     return vertices.values();
   }
 	
   public Vertex getVertex(String name) {
 	    return vertices.get(name);
 	 }
   
   /**
	 * Adds and undirected Graph
	 * @param nodeLabel
	 * @param nodeLabel2
	 * @param y
	 */
	public void addUndirectedEdge(String nodeLabel,String nodeLabel2,double y ) {
		addDirectedEdge(nodeLabel,nodeLabel2,y );
		addDirectedEdge(nodeLabel2,nodeLabel,y );
	}
	
	public void addDirectedEdge(String nodeLabel,String nodeLabel2,double y ) {
		if(!vertices.containsKey(nodeLabel)){throw new IllegalArgumentException(nodeLabel + " does not exist. Cannot create edge from source");}
		if(!vertices.containsKey(nodeLabel2)){throw new IllegalArgumentException(nodeLabel2 + " does not exist. Cannot create edge. to destination");}
		
		 Vertex source = vertices.get(nodeLabel);
		 Vertex target = vertices.get(nodeLabel2);
		 
		 Edge newEdge = new Edge(source, target, y);
		 source.addEdge(newEdge);
	}
 	
}
