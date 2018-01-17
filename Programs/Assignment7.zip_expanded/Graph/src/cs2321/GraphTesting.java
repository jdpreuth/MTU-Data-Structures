package cs2321;

import net.datastructures.Edge;
import net.datastructures.Vertex;

public class GraphTesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AdjListGraph<Integer, String> graph = new AdjListGraph<Integer, String>();
		Vertex<Integer> v1 = graph.insertVertex(1);
		System.out.println(graph.numVertices());
		System.out.println();

		Vertex<Integer> v2 = graph.insertVertex(2);
		System.out.println(graph.numVertices());
		System.out.println();

		Edge<String> e1 = graph.insertEdge(v1, v2, "1 -> 2");
		System.out.println(graph.numEdges());
		System.out.println(graph.outDegree(v1));
		System.out.println(graph.outDegree(v2));
		System.out.println();

		Vertex<Integer> v3 = graph.insertVertex(3);
		System.out.println(graph.numVertices());
		System.out.println();

		Edge<String> e2 = graph.insertEdge(v2, v3, "2 -> 3");
		System.out.println(graph.numEdges());
		System.out.println();

		graph.removeVertex(v2);
		System.out.println(graph.numVertices());
		System.out.println(graph.numEdges());
		System.out.println();

	}

}
