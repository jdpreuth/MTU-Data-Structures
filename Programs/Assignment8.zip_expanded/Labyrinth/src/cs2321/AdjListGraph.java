package cs2321;

import net.datastructures.Edge;
import net.datastructures.Graph;
import net.datastructures.Position;
import net.datastructures.Vertex;

/*
 * Implement Graph interface. A graph can be declared as either directed or undirected.
 * In the case of an undirected graph, methods outgoingEdges and incomingEdges return the same collection,
 * and outDegree and inDegree return the same value.
 * 
 * @author CS2321 Instructor
 */
public class AdjListGraph<V, E> implements Graph<V, E> {

	// Graph Edge implementation
	private class GraphEdge implements Edge<E> {
		private E element;
		Vertex<V>[] endPoints;
		private Position<Edge<E>> position;

		@SuppressWarnings("unchecked")
		public GraphEdge(E element, Vertex<V> u, Vertex<V> v) {
			this.element = element;
			endPoints = (Vertex<V>[]) new Vertex[] { u, v };
		}

		@Override
		public E getElement() {
			// TODO Auto-generated method stub
			return this.element;
		}

		public void setElement(E element) {
			this.element = element;
		}

		public Vertex<V>[] getEndPoints() {
			return this.endPoints;
		}

		public Position<Edge<E>> getPosition() {
			return this.position;
		}

		public void setPosition(Position<Edge<E>> position) {
			this.position = position;
		}

	}

	// Graph Vertex implementation
	private class GraphVertex implements Vertex<V> {
		private V element;
		private Position<Vertex<V>> position;
		private HashMap<Vertex<V>, Edge<E>> outgoing, incoming;

		public GraphVertex(V element, boolean isDirected) {
			this.element = element;
			outgoing = new HashMap<Vertex<V>, Edge<E>>();
			if (isDirected) {
				incoming = new HashMap<Vertex<V>, Edge<E>>();
			} else {
				incoming = outgoing;
			}
		}

		@Override
		public V getElement() {
			// TODO Auto-generated method stub
			return this.element;
		}

		public void setElement(V element) {
			this.element = element;
		}

		public Position<Vertex<V>> getPosition() {
			return this.position;
		}

		public void setPosition(Position<Vertex<V>> position) {
			this.position = position;
		}

		public HashMap<Vertex<V>, Edge<E>> getOutgoing() {
			return outgoing;
		}

		public HashMap<Vertex<V>, Edge<E>> getIncoming() {
			return incoming;
		}

	}

	/*
	 * Casts a Vertex to a GraphVertex
	 */
	private GraphVertex cast(Vertex<V> v) throws IllegalArgumentException {
		if (!(v instanceof AdjListGraph.GraphVertex)) {
			throw new IllegalArgumentException("The passed vertex is not part of the graph");
		}
		return (GraphVertex) v;
	}

	/*
	 * Casts a Edge to a GraphEdge
	 */
	private GraphEdge cast(Edge<E> e) throws IllegalArgumentException {
		if (!(e instanceof AdjListGraph.GraphEdge)) {
			throw new IllegalArgumentException("The passed edge is not part of the graph");
		}
		return (GraphEdge) e;
	}

	private boolean isDirected; // Boolean to determine if the graph is directed
	private DoublyLinkedList<Vertex<V>> vertices = new DoublyLinkedList<Vertex<V>>(); // List
																						// of
																						// verticies
																						// in
																						// the
																						// graph
	private DoublyLinkedList<Edge<E>> edges = new DoublyLinkedList<Edge<E>>(); // List
																				// of
																				// edges
																				// in
																				// the
																				// graph

	/*
	 * Constructor that decides if a a graph is directed or not
	 */
	public AdjListGraph(boolean directed) {
		// TODO directed graph constructor
		isDirected = directed;
	}

	/*
	 * Default constructor assumes an undirected graph
	 */
	public AdjListGraph() {
		// TODO non directed graph constructor
		isDirected = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.datastructures.Graph#edges()
	 */
	@TimeComplexity("O(1)")
	public Iterable<Edge<E>> edges() {
		// TODO Auto-generated method stub
		/*
		 * TCJ: The list of edges is iterable so returning it is a single
		 * operation
		 */
		return edges;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.datastructures.Graph#endVertices(net.datastructures.Edge)
	 */
	@TimeComplexity("O(1)")
	public Vertex[] endVertices(Edge<E> e) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * TCJ: The list of end points are stored within each edge so they just
		 * are returned
		 */
		GraphEdge edge = cast(e);
		return edge.getEndPoints();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.datastructures.Graph#insertEdge(net.datastructures.Vertex,
	 * net.datastructures.Vertex, java.lang.Object)
	 */
	@TimeComplexity("O(n)")
	@TimeComplexityExpected("O(1)")
	public Edge<E> insertEdge(Vertex<V> u, Vertex<V> v, E o) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * TCJ: The relevant references are stored within the vertex and edge
		 * objects. The origin and destination nodes have to add the edge to
		 * their hashmap via the put method which is expected O(1) but worse
		 * case is O(n)
		 */
		if (getEdge(u, v) == null) {
			GraphEdge edge = new GraphEdge(o, u, v);
			edges.addLast(edge);
			edge.setPosition(edges.last());
			GraphVertex origin = (GraphVertex) u;
			GraphVertex destination = (GraphVertex) v;
			origin.getOutgoing().put(v, edge);
			destination.getIncoming().put(u, edge);
			return edge;
		} else {
			throw new IllegalArgumentException("U and V are already have an edge");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.datastructures.Graph#insertVertex(java.lang.Object)
	 */
	@TimeComplexity("O(1)")
	public Vertex<V> insertVertex(V o) {
		// TODO Auto-generated method stub
		/*
		 * This method simply creates a new vertex and appends it into the
		 * linked list. All of which are single operations
		 */
		GraphVertex vertex = new GraphVertex(o, isDirected);
		vertices.addLast(vertex);
		vertex.setPosition(vertices.last());
		return vertex;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.datastructures.Graph#numEdges()
	 */
	@TimeComplexity("O(1)")
	public int numEdges() {
		// TODO Auto-generated method stub
		/*
		 * TCJ: This is just the size method for the doublylinkedlist that
		 * stores the edges which is O(1)
		 */
		return edges.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.datastructures.Graph#numVertices()
	 */
	@TimeComplexity("O(1)")
	public int numVertices() {
		// TODO Auto-generated method stub
		/*
		 * TCJ: This is just the size method for the doublylinkedlist that sores
		 * the vertices which is O(1)
		 */
		return vertices.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.datastructures.Graph#opposite(net.datastructures.Vertex,
	 * net.datastructures.Edge)
	 */
	@TimeComplexity("O(1)")
	public Vertex<V> opposite(Vertex<V> v, Edge<E> e) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * TCJ: The vertex and edge objects store the nessecary information to
		 * make basic comparisons all of which are single operations
		 */
		GraphVertex vertex = cast(v);
		GraphEdge edge = cast(e);
		Vertex<V>[] endPoints = edge.getEndPoints();
		if (endPoints[0] == vertex || endPoints[1] == vertex) {
			if (endPoints[0] == vertex) {
				return endPoints[1];
			} else {
				return endPoints[0];
			}
		} else {
			throw new IllegalArgumentException("The vertex/edge pairing don't match");
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.datastructures.Graph#removeEdge(net.datastructures.Edge)
	 */
	@TimeComplexityExpected("O(1)")
	@TimeComplexity("O(n)")
	public void removeEdge(Edge<E> e) throws IllegalArgumentException {
		/*
		 * TCJ: This method deletes the edge which is a single operation becuase
		 * the edge is location aware. The end vertices then need to dereference
		 * the edge by removing them from their internal hashmap which is an
		 * expected O(1) but worse case O(n) operation
		 */
		GraphEdge edge = cast(e);
		Position<Edge<E>> pos = edge.getPosition();
		edges.remove(pos);
		Vertex<V>[] endPoints = edge.endPoints;
		GraphVertex origin = cast(endPoints[0]);
		GraphVertex destination = cast(endPoints[1]);
		origin.getOutgoing().remove(destination);
		destination.getIncoming().remove(origin);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.datastructures.Graph#removeVertex(net.datastructures.Vertex)
	 */
	@TimeComplexity("O(m)")
	public void removeVertex(Vertex<V> v) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Removign the vertex is a single operation because the vertex is
		 * location aware. All the edges that reference the vertex must also be
		 * removed and worse case the vertex is connected to every edge.
		 */
		GraphVertex vertex = cast(v);
		Position<Vertex<V>> pos = vertex.getPosition();
		vertices.remove(pos);
		Iterable<Edge<E>> outgoing = vertex.getOutgoing().values();
		for (Edge<E> edge : outgoing) {
			removeEdge(edge);
		}
		Iterable<Edge<E>> incoming = vertex.getIncoming().values();
		for (Edge<E> edge : incoming) {
			removeEdge(edge);
		}

	}

	@TimeComplexity("O(1)")
	public E replace(Edge<E> p, E o) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * TCJ: This simply updates the element stored in an edge which is a
		 * single operation
		 */
		GraphEdge edge = cast(p);
		E element = edge.getElement();
		edge.setElement(o);
		return element;
	}

	@TimeComplexity("O(1)")
	public V replace(Vertex<V> p, V o) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * TCJ: This simply updates the element stored in a vertex which is a
		 * single operation
		 */
		GraphVertex vertex = cast(p);
		V element = vertex.getElement();
		vertex.setElement(o);
		return element;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see net.datastructures.Graph#vertices()
	 */
	@TimeComplexity("O(1)")
	public Iterable<Vertex<V>> vertices() {
		// TODO Auto-generated method stub
		/*
		 * TCJ: The doublylinkedlist that stores the vertices is iterable so
		 * this method just returns that collection
		 */
		return vertices;
	}

	@Override
	@TimeComplexity("O(1)")
	public int outDegree(Vertex<V> v) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Each vertex keeps track of its outdegree so that size is simply
		 * returned
		 */
		GraphVertex vertex = cast(v);
		return vertex.getOutgoing().size();
	}

	@Override
	@TimeComplexity("O(1)")
	public int inDegree(Vertex<V> v) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * TCJ: Each vertex keeps track of its indegree so that size is simply
		 * returned
		 */
		GraphVertex vertex = cast(v);
		return vertex.getIncoming().size();
	}

	@Override
	@TimeComplexity("O(m)")
	public Iterable<Edge<E>> outgoingEdges(Vertex<V> v) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * TCJ: This method calls the vertex outgoing vertices hashmap's values
		 * function to get an iterable collection which is O(m)
		 */
		GraphVertex vertex = cast(v);
		return vertex.getOutgoing().values();
	}

	@Override
	@TimeComplexity("O(m)")
	public Iterable<Edge<E>> incomingEdges(Vertex<V> v) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * TCJ: This method calls the vertex outgoing vertices hashmap's values
		 * function to get an iterable collection which is O(m)
		 */
		GraphVertex vertex = cast(v);
		return vertex.getIncoming().values();
	}

	@Override
	@TimeComplexityExpected("O(1)")
	@TimeComplexity("O(m)")
	public Edge<E> getEdge(Vertex<V> u, Vertex<V> v) throws IllegalArgumentException {
		// TODO Auto-generated method stub
		/*
		 * TCJ: The origin vertex can search it's outgoing edge list which is
		 * just the hashmap get function which is expected O(1) but is worse
		 * case O(m)
		 */
		GraphVertex origin = cast(u);
		GraphEdge edge = (GraphEdge) origin.getOutgoing().get(v);
		return edge;
	}

}
