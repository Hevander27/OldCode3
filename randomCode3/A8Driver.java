
public class A8Driver {

	static A8Graph quizgraph ()
	{
		A8Graph graph = new A8Graph();
		graph.addEdge("L","Y");
		graph.addEdge("J","L");
		graph.addEdge("G","L");
		graph.addEdge("J","O");
		graph.addEdge("Q","J");
		graph.addEdge("G","R");
		graph.addEdge("N","O");
		graph.addEdge("N","H");
		graph.addEdge("Q","H");
		graph.addEdge("W","H");
		graph.addEdge("R","W");
		graph.addEdge("N","V");
		graph.addEdge("W","V");
		graph.addEdge("W","D");
		graph.addEdge("S","V");
		graph.addEdge("D","S");
		return graph;
	}
	public static void main(String[] args) {
		System.out.println(quizgraph().topologicalSortAlphaSort());
		System.out.println(quizgraph().topologicalSortStackAlphaSort());
		/*A8Graph graph = new A8Graph();
		graph.addEdge("D", "S");
		graph.addEdge("S", "V");
		graph.addEdge("W", "D");
		graph.addEdge("W", "V");
		graph.addEdge("W", "H");
		graph.addEdge("R", "W");
		graph.addEdge("G", "R");
		graph.addEdge("G", "L");
		graph.addEdge("L", "Y");
		graph.addEdge("J", "L");
		graph.addEdge("J", "O");
		graph.addEdge("Q", "J");
		graph.addEdge("Q", "H");
		graph.addEdge("N", "V");
		graph.addEdge("N", "H");
		graph.addEdge("N", "O");
		System.out.println(graph.topologicalSort());
		graph = new A8Graph();
		graph.addEdge("D", "S");
		graph.addEdge("S", "V");
		graph.addEdge("W", "D");
		graph.addEdge("W", "V");
		graph.addEdge("W", "H");
		graph.addEdge("R", "W");
		graph.addEdge("G", "R");
		graph.addEdge("G", "L");
		graph.addEdge("L", "Y");
		graph.addEdge("J", "L");
		graph.addEdge("J", "O");
		graph.addEdge("Q", "J");
		graph.addEdge("Q", "H");
		graph.addEdge("N", "V");
		graph.addEdge("N", "H");
		graph.addEdge("N", "O");
		System.out.println(graph.topologicalSortStack());*/
	}

}
