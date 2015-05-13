import java.util.*;

/**
 * A representation of a graph.
 * Assumes that we do not have negative cost edges in the graph.
 */
public class MyGraph implements Graph {
	private Collection<Vertex> myVertices;	// the vertices in this graph
	private Collection<Edge> myEdges;	// the edges in this graph

	/**
	 * Creates a MyGraph object with the given collection of vertices
	 * and the given collection of edges.
	 * @param v a collection of the vertices in this graph
	 * @param e a collection of the edges in this graph
	 */
	public MyGraph(Collection<Vertex> v, Collection<Edge> e) {
	    myVertices = v;
	    myEdges = e;
	}


    /** 
     * Return the collection of vertices of this graph
     * @return the vertices as a collection (which is anything iterable)
     */
    public Collection<Vertex> vertices() {
    	return myVertices;
    }

    /** 
     * Return the collection of edges of this graph
     * @return the edges as a collection (which is anything iterable)
     */
    public Collection<Edge> edges() {
    	return myEdges;
    }

    /**
     * Return a collection of vertices adjacent to a given vertex v.
     *   i.e., the set of all vertices w where edges v -> w exist in the graph.
     * Return an empty collection if there are no adjacent vertices.
     * @param v one of the vertices in the graph
     * @return an iterable collection of vertices adjacent to v in the graph
     * @throws IllegalArgumentException if v does not exist.
     */
    public Collection<Vertex> adjacentVertices(Vertex v) {
    	if(!vertices().contains(v))
    		throw new IllegalArgumentException();
    	Collection<Vertex> adjacent = new LinkedList<Vertex>();
    	for(Edge e : edges()) {
    		if(e.from == v)
    			adjacent.add(v);
    	}
    	return adjacent;
    }

    /**
     * Test whether vertex b is adjacent to vertex a (i.e. a -> b) in a directed graph.
     * Assumes that we do not have negative cost edges in the graph.
     * @param a one vertex
     * @param b another vertex
     * @return cost of edge if there is a directed edge from a to b in the graph, 
     * return -1 otherwise.
     * @throws IllegalArgumentException if a or b do not exist.
     */
    public int isAdjacent(Vertex a, Vertex b) {
    	if( !(vertices().contains(a)  &&  vertices().contains(b) ) )
    		throw new IllegalArgumentException();
    	for(Edge e : edges()) {
    		if(e.from == a  && e.to == b)
    			return e.w;
    	}
    	return -1;
    }

    /**
     * Returns the shortest path from a to b in the graph.  Assumes positive
     * edge weights.  Uses Dijkstra's algorithm.
     * @param a the starting vertex
     * @param b the destination vertex
     * @param path a list in which the path will be stored, in order, the first
     * being the start vertex and the last being the destination vertex.  The
     * list will be empty if no such path exists.  NOTE: the list will be
     * cleared of any previous data.  path is not expected to contain any data
     * needed by the method when the method is called.  It is used to allow
     * us to return multiple values from the function.
     * @return the length of the shortest path from a to b, -1 if no such path
     * exists.
     * @throws IllegalArgumentException if a or b does not exist.
     */
    public int shortestPath(Vertex a, Vertex b, List<Vertex> path) {
    	
    	path = new LinkedList<Vertex>();
    	
    	PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
    	pq.add(a);
    	a.cost = 0;
    	a.known = true;
    	while(!pq.isEmpty()) {
    		Vertex cur = pq.poll();
    		if(cur.known)
    			continue;
    		cur.known = true;
    		for(Vertex dest : adjacentVertices(cur)) {
    			int pathCost = isAdjacent(cur, dest);
    			int totalCostToDest = cur.cost + pathCost;
    			if(dest.cost > totalCostToDest) {
    				dest.cost = totalCostToDest;
    				dest.previous = cur;
    				pq.add(dest);
    			}
    		}
    	}
    	
    	Stack<Vertex> stack = new Stack<Vertex>();
    	for(Vertex v = b ; v != null ; v = v.previous)
    		stack.push(v);
    	while(!stack.isEmpty())
    		path.add(stack.pop());
    	
    	return b.cost;    // ????
    	
    	
    	
    }

}