import java.util.*;

/**
 * A representation of a graph.
 * Assumes that we do not have negative cost edges in the graph.
 */
public class MyGraph1 implements Graph {
    private Collection<Vertex> myVertices;  // the vertices in this graph
    private Collection<Edge> myEdges;   // the edges in this graph
    //private Vertex[] arr; 
    //private int[][] cost;
    /**
     * Creates a MyGraph object with the given collection of vertices
     * and the given collection of edges.
     * @param v a collection of the vertices in this graph
     * @param e a collection of the edges in this graph
     */
    public MyGraph1(Collection<Vertex> v, Collection<Edge> e) {

        myVertices = new TreeSet<Vertex>();
        myEdges = new TreeSet<Edge>();
        myVertices = v;
        myEdges = e;
        /*arr = new Vertex[myVertices.size()];
        int i = 0;
        for(Vertex a : myVertices)
            arr[i++] = a;
        for(i = 0 ; i < arr.length ; i++)
            arr[i].index = i;
        cost = new int[arr.length][arr.length];
        for(i = 0 ; i < arr.length ; i++)
            for(int j = 0 ; j < arr.length ; j++)
                if(i != j)
                    cost[i][j] = Integer.MAX_VALUE;
                else
                    cost[i][j] = 0;
        for(Edge ed : myEdges) {
            Vertex a = ed.from;
            Vertex b = ed.to;
            cost[a.index][b.index] = ed.w;*/
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
        Collection<Vertex> adjacent = new TreeSet<Vertex>();
        for(Edge e : edges()) {         
            Vertex a = e.from;
            if(v.equals(a))
                adjacent.add(e.to);
        }
        /////////////
        System.out.print("Adjacent Edges : ");
        for(Vertex adj : adjacent)
            System.out.print(adj + " ");
        ////////////
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
        if(!vertices().contains(a) || !vertices().contains(b))
            throw new IllegalArgumentException();
        Collection<Edge> adjEdges = correspondingAdjacentEdges(a);
        for(Edge e : adjEdges) {
            if(e.to == b)
                return e.w;
        }    
        return -1;
    }
    
    private Collection<Edge> correspondingAdjacentEdges(Vertex a) {
        Collection<Edge> adjacentEdges = new TreeSet<Edge>();
        for(Edge ed : edges()) {
            if(ed.from == a)
                adjacentEdges.add(ed);
        }
        /////////////
        System.out.print("Adjacent Edges : ");
        for(Edge e : adjacentEdges)
            System.out.print(e);
        ////////////
        return adjacentEdges;
    }
    
    /*private int verticesSize() {
        return myVertices.size();
    }*/

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
        dijkstra(a);
        Stack<Vertex> s = new Stack<Vertex>();
        Vertex cur = b;
        while(cur.previous!=null) {
            s.push(cur);
            cur = cur.previous;
        }
        while(!s.isEmpty())
            path.add(s.pop());
        /////////////
        System.out.print("Elements in list 'path' : ");
        for(Vertex test : path)
        	System.out.print(test + " ");
        ////////////
        return b.cost();
    }
    
    private void dijkstra(Vertex start) {
        PriorityQueue<Vertex> pq = new PriorityQueue<Vertex>();
        pq.add(start);
        start.cost = 0;
        start.known = true;
        
        while(!pq.isEmpty()) {
            Vertex curr = pq.remove();
            if(curr.known)
                continue;
            curr.known = true;
            System.out.print(curr + " ");
            
            for(Edge adj : correspondingAdjacentEdges(curr)) {
                //Vertex curr = adj.from;
                Vertex dest = adj.to;
                int pathCost = adj.w;
                if(dest.cost > (curr.cost + pathCost)) {
                    dest.cost = curr.cost + pathCost;
                    dest.previous = curr;
                    pq.add(dest);
                }
            }
            
        }
        
    }    
}