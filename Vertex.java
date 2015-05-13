/**
 * Representation of a graph vertex
 */
public class Vertex implements Comparable<Vertex>{
	private final String label;   // label attached to this vertex
	public boolean known;
	public int cost;
	//public int index;
	public Vertex previous;
	/**
	 * Construct a new vertex
	 * @param label the label attached to this vertex
	 */
	public Vertex(String label) {
		if(label == null)
			throw new IllegalArgumentException("null");
		this.label = label;
		this.known = false;
		this.cost = Integer.MAX_VALUE;
		//this.index = -1;
		this.previous = null;
	}

	/**
	 * Get a vertex label
	 * @return the label attached to this vertex
	 */
	public String getLabel() {
		return label;
	}
	
	/**
	 * A string representation of this object
	 * @return the label attached to this vertex
	 */
	public String toString() {
		return label;
	}

	//auto-generated: hashes on label
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}

	//auto-generated: compares labels
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Vertex other = (Vertex) obj;
		if (label == null) {
			if (other.label != null)
				return false;
		} else if (!label.equals(other.label))
			return false;
		return true;
	}

	public boolean isKnown() {
		return known;
	}
	
	public int cost() {
		return cost;
	}
	
	/*public int getIndex() {
		return index;
	}*/
	
	public int compareTo(Vertex other) {
		return this.cost < other.cost ? -1 : this.cost > other.cost ? 1 : 0;
	}
	
}