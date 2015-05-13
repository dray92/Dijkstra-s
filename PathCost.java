public class PathCost implements Comparable<PathCost>{

	public int c;
	public Vertex dest;
	
	public PathCost(Vertex d , int cost) {
		dest = d;
		c = cost;
	}
	
	public int compareTo(PathCost other) {
		return this.c < other.c ? -1 : this.c > other.c ? 1 : 0;
	}
}
