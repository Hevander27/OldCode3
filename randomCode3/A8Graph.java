/*
 * Complete topologicalSort() method - 3pts
 * Complete topologicalSortStack() method - 2pts
 * Modify addEdge(String v1, String v2, int weight) method - 1pt
 */
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;
import java.util.TreeMap;

public class A8Graph {
	private Map<String, A8Vertex> map;
	
	/*
	 * Grading:
	 * Correctly implement Pseudo code - 3pts
	 */
	/*
		Explanation of `indegree_temp`:
			The `_temp` is commented out since modifying A8Vertex is not allowed.
			However the idea is to allow sorting the graph multiple times,
			whereas without copying to `_temp` the actual `indegree` is clobbered
			by running a sort, and so running another sort would not produce 
			desirable results.
	*/
	public String topologicalSort()
	{
		//perform a topological sort on the vertices in the graph
		//add the name and a comma to an output string in the order they should be visited
		//Below is the pseudo code from the textbook
		/*
		 * PSEUDO CODE
		 * Queue<Vertex> q = new Queue<Vertex>();
		 * int counter = 0;
		 * for each Vertex v
		 * {
		 * 		if(v.indegree == 0)
		 * 		{
		 * 			q.enqueue(v);
		 * 		}
		 * }
		 * while(!q.isEmpty())
		 * {
		 * 		Vertex v = q.dequeue();
		 * 		v.topNum = ++counter;//NOTE FROM JET - this is where you add vertex name to output string
		 * 		for each Vertex w adjacent to v
		 * 		{
		 * 			if(--w.indegree == 0)
		 * 			{
		 * 				q.enqueue(w);
		 * 			}
		 * 		}
		 * }
		 */
		
		Queue<A8Vertex> queue = new LinkedList();
		for (String key : map.keySet())
		{
			A8Vertex vert = map.get(key);
			/*vert.indegree_temp = vert.indegree;*/
			if (vert.indegree/*_temp*/ == 0) queue.add(vert);
		}
		StringBuilder out = new StringBuilder();
		int extinguished = 0;
		while (!queue.isEmpty())
		{
			A8Vertex vert = queue.poll();
			extinguished++;
			out.append(vert.getName());
			out.append(" ");
			TreeMap<String,Integer> adj = vert.getAdjacencyList();
			for (String key : vert.getAdjacencyList().keySet())
			{
				A8Vertex to = map.get(key);
				if (--to.indegree/*_temp*/ == 0) queue.add(to);
			}
		}
		String outstr;
		if (extinguished != map.keySet().size()) outstr = "(Impossible)";
		else outstr = out.toString();
		
		return outstr;
	}
	/*
	 * Grading:
	 * Correctly implement method using a stack instead of a queue - 2pts
	 */
	public String topologicalSortStack()
	{
		/*
		 * Use the same process as the previous method, using a stack instead of queue
		 */
		
		Stack<A8Vertex> stack = new Stack();
		for (String key : map.keySet())
		{
			A8Vertex vert = map.get(key);
			/*vert.indegree_temp = vert.indegree;*/
			if (vert.indegree/*_temp*/ == 0) stack.add(vert);
		}
		StringBuilder out = new StringBuilder();
		int extinguished = 0;
		while (!stack.isEmpty())
		{
			A8Vertex vert = stack.pop();
			extinguished++;
			out.append(vert.getName());
			out.append(" ");
			TreeMap<String,Integer> adj = vert.getAdjacencyList();
			for (String key : vert.getAdjacencyList().keySet())
			{
				A8Vertex to = map.get(key);
				if (--to.indegree/*_temp*/ == 0) stack.add(to);
			}
		}
		String outstr;
		if (extinguished != map.keySet().size()) outstr = "(Impossible)";
		else outstr = out.toString();
		
		return outstr;
	}
	/*
	 * Grading:
	 * Indegree incremented for correct vertex - 1pt
	 */
	public void addEdge(String v1, String v2, int weight)
	{
		//MODIFY - increment indegree for correct vertex
		
		//ensure each vertex exists in map
		addVertex(v1);
		addVertex(v2);
		//connect vertex1 to vertex2 with given weight
		map.get(v1).addEdge(v2, weight);
		
		map.get(v2).indegree++; // <- new code
	}
	
	
	
	
	
	
	
	
	
	class VerticesCompareName implements Comparator<A8Vertex>
	{
		@Override
		public int compare (A8Vertex t, A8Vertex t1) 
		{
			return t.getName().compareTo(t1.getName());
		}
	}
	public String topologicalSortAlphaSort ()
	{
		Queue<A8Vertex> queue = new LinkedList();
		for (String key : map.keySet())
		{
			A8Vertex vert = map.get(key);
			/*vert.indegree_temp = vert.indegree;*/
			if (vert.indegree/*_temp*/ == 0) queue.add(vert);
		}
		VerticesCompareName compare = new VerticesCompareName();
		Collections.sort((LinkedList)queue,compare);
		StringBuilder out = new StringBuilder();
		int extinguished = 0;
		while (!queue.isEmpty())
		{
			int chunk = queue.size();
			extinguished += chunk;
			LinkedList<A8Vertex> to_add = new LinkedList();
			for (int i = 0; i < chunk; i++)
			{
				A8Vertex vert = queue.poll();
				out.append(vert.getName());
				out.append(" ");
				TreeMap<String,Integer> adj = vert.getAdjacencyList();
				for (String key : vert.getAdjacencyList().keySet())
				{
					A8Vertex to = map.get(key);
					if (--to.indegree/*_temp*/ == 0) to_add.add(to);
				}
			}
			Collections.sort(to_add,compare);
			for (A8Vertex vert : to_add)
			{
				queue.add(vert);
			}
		}
		String outstr;
		if (extinguished != map.keySet().size()) outstr = "(Impossible)";
		else outstr = out.toString();
		
		return outstr;
	}
	public String topologicalSortStackAlphaSort ()
	{
		Stack<A8Vertex> stack = new Stack();
		for (String key : map.keySet())
		{
			A8Vertex vert = map.get(key);
			/*vert.indegree_temp = vert.indegree;*/
			if (vert.indegree/*_temp*/ == 0) stack.add(vert);
		}
		VerticesCompareName compare = new VerticesCompareName();
		Collections.sort(stack,compare);
		StringBuilder out = new StringBuilder();
		int extinguished = 0;
		while (!stack.isEmpty())
		{
			int chunk = stack.size();
			extinguished += chunk;
			LinkedList<A8Vertex> to_add = new LinkedList();
			for (int i = 0; i < chunk; i++)
			{
				A8Vertex vert = stack.pop();
				out.append(vert.getName());
				out.append(" ");
				TreeMap<String,Integer> adj = vert.getAdjacencyList();
				for (String key : vert.getAdjacencyList().keySet())
				{
					A8Vertex to = map.get(key);
					if (--to.indegree/*_temp*/ == 0) to_add.add(to);
				}
			}
			Collections.sort(to_add,compare);
			for (A8Vertex vert : to_add)
			{
				stack.add(vert);
			}
		}
		String outstr;
		if (extinguished != map.keySet().size()) outstr = "(Impossible)";
		else outstr = out.toString();
		
		return outstr;
	}
	
	
	
	
	
	
	
	
	
	
	
	/*DO NOT MODIFY*/
	public A8Graph()
	{
		map = new TreeMap<>();
	}

	
	public void addEdge(String v1, String v2)
	{
		addEdge(v1, v2, 1);
	}
	public void addUndirectedEdge(String v1, String v2, int weight)
	{
		addEdge(v1, v2, weight);
		addEdge(v2, v1, weight);
	}
	public void addUndirectedEdge(String v1, String v2)
	{
		addUndirectedEdge(v1, v2, 1);
	}

	private void addVertex(String v)
	{
		if(!map.containsKey(v))//if vertex isn't already in the map
		{
			map.put(v, new A8Vertex(v));//create vertex and add to map
		}
	}
	public String toString()
	{
		String output = "Graph:\n";

		if(map.size() > 0)
		{
			for(Entry<String,A8Vertex> e : map.entrySet())
			{
				output += "("+e.getKey()+", "+e.getValue()+")\n";
			}
		}

		return output;
	}

	public void printPath(String vs, String ve, String type)
	{
		if(map.containsKey(vs) && map.containsKey(ve))
		{
			System.out.println(type.toUpperCase());
			A8Vertex s = map.get(vs);
			if(type.toLowerCase().equals("unweighted"))
			{
				unweighted(s);
			}
			else if(type.toLowerCase().equals("weighted"))
			{
				weighted(s);
			}
			else if(type.toLowerCase().equals("negative"))
			{
				negative(s);
			}
			A8Vertex e = map.get(ve);
			/*
			 * Pseudocode
			if(e.dist != INFINITY){
				String path = "";
				Vertex curr = e;
				while(curr.path != null){
					path += curr;
					curr = curr.path;
				}
				path = s + path;
				print(path)
				print(dist)
			}else{
				print("can not reach end");
			}
			 */
			if(e.dist != Integer.MAX_VALUE)
			{
				String path = "";
				A8Vertex curr = e;
				while(curr.path != null)
				{
					if(path != "")
					{
						path = curr.getName() + "," + path;
					}
					else
					{
						path = curr.getName();
					}
					curr = curr.path;
				}
				path = s.getName() + "," + path;
				System.out.println(path);
				System.out.println(e.dist);
			}
			else
			{
				System.out.println("End is not reachable from Start");
			}
		}
	}
	public void unweighted(A8Vertex s)
	{
		/*
		 * Pseudocode from textbook PG 372
		Queue<Vertex> q = new Queue<Vertex>();
		for each Vertex v{
			v.dist = INFINITY;
			v.path = null;//added to make sure we clear the path between runs of pathing methods
		}
		s.dist = 0;
		q.enqueue(s);
		while(!q.isEmpty()){
			Vertex v = q.dequeue();
			for each Vertex w adjacent to v{
				if(w.dist == INFINITY){
					w.dist = v.dist + 1;
					w.path = v;
					q.enqueue(w);
				}
			}
		}
		 */
		LinkedList<A8Vertex> q = new LinkedList<>();//can be used like a queue
		for(Entry<String, A8Vertex> vertexEntry : map.entrySet())
		{
			A8Vertex v = vertexEntry.getValue();
			v.dist = Integer.MAX_VALUE;//~2+ billion ~INFINITY
			v.path = null;//added to make sure we clear the path between runs of pathing methods
		}
		s.dist = 0;
		q.addLast(s);//queue.enqueue is FIFO, so add at end and take off beginning
		while(!q.isEmpty())
		{
			A8Vertex v = q.removeFirst();//q.dequeue();, remove from beginning because adding to end
			for(Entry<String, Integer> adjacent : v.getAdjacencyList().entrySet())
			{
				A8Vertex w = map.get(adjacent.getKey());//adjacent.getKey is name of adjacent vertex
				if(w.dist == Integer.MAX_VALUE)//INFINITY
				{
					w.dist = v.dist + 1;
					w.path = v;//how we got to this vertex
					q.addLast(w);//q.enqueue(w);
				}
			}
		}
	}
	public void weighted(A8Vertex s)
	{
		/*
		 * Pseudocode
		PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();
		//implement Comparable<Vertex> based on distance for PriorityQueue
		for each Vertex v{
			v.dist = INFINITY;
			v.path = null;//added to make sure we clear the path between runs of pathing methods
			v.known = false;
		}
		s.dist = 0;
		q.enqueue(s);
		while(!q.isEmpty()){
			Vertex v = q.dequeue();//smallest distance in queue
			v.known = true;
			for each Vertex w adjacent to v{
				if(w.dist > v.dist + w.weight){
					w.dist = v.dist + w.weight;
					w.path = v;
				}
				if(!w.known){
					q.enqueue(w);
				}
			}
		}
		 */
		PriorityQueue<A8Vertex> q = new PriorityQueue<>();
		for(Entry<String, A8Vertex> vertexEntry : map.entrySet())
		{
			A8Vertex v = vertexEntry.getValue();
			v.dist = Integer.MAX_VALUE;//INFINITY
			v.path = null;//added to make sure we clear the path between runs of pathing methods
			v.known = false;
		}
		s.dist = 0;
		q.offer(s);//q.enqueue(s);
		while(!q.isEmpty())
		{
			A8Vertex v = q.poll();//q.dequeue();//smallest distance in queue
			//System.out.println(v.getName()+":"+q.size());
			v.known = true;
			for(Entry<String, Integer> adjacent : v.getAdjacencyList().entrySet())
			{
				A8Vertex w = map.get(adjacent.getKey());//adjacent.getKey is name of adjacent vertex
				Integer weight = adjacent.getValue();
				if(w.dist > v.dist + weight){
					w.dist = v.dist + weight;
					w.path = v;
				}
				if(!w.known){
					q.offer(w);//q.enqueue(w);
				}
			}
		}

	}
	public void negative(A8Vertex s)
	{
		/*
		 * Pseudocode
		Queue<Vertex> q = new Queue<Vertex>();
		for each Vertex v{
			v.dist = INFINITY;
			v.path = null;//added to make sure we clear the path between runs of pathing methods
		}
		s.dist = 0;
		q.enqueue(s);
		while(!q.isEmpty()){
			Vertex v = q.dequeue();
			for each Vertex w adjacent to v{
				if(w.dist > v.dist + w.weight){
					w.dist = v.dist + w.weight;
					w.path = v;
					if(!q.contains(w)){
						q.enqueue(w);
					}
				}
			}
		}
		 */
		LinkedList<A8Vertex> q = new LinkedList<>();//can be used like a queue
		for(Entry<String, A8Vertex> vertexEntry : map.entrySet())
		{
			A8Vertex v = vertexEntry.getValue();
			v.dist = Integer.MAX_VALUE;//~2+ billion ~INFINITY
			v.path = null;//added to make sure we clear the path between runs of pathing methods
		}
		s.dist = 0;
		q.addLast(s);//queue.enqueue is FIFO, so add at end and take off beginning
		while(!q.isEmpty())
		{
			A8Vertex v = q.removeFirst();//q.dequeue();, remove from beginning because adding to end
			for(Entry<String, Integer> adjacent : v.getAdjacencyList().entrySet())
			{
				A8Vertex w = map.get(adjacent.getKey());//adjacent.getKey is name of adjacent vertex
				Integer weight = adjacent.getValue();
				if(w.dist > v.dist + weight){
					w.dist = v.dist + weight;
					if(w.dist < 0)//stop negative cycle infinite recursion
						w.dist = 0;
					w.path = v;
					if(!q.contains(w)){
						q.addLast(w);//q.enqueue(w);
					}
				}
			}
		}
	}


	public void printMaxDistance()
	{
		int maxDist = 0;
		String vert = "";
		for(Map.Entry<String, A8Vertex> vertex : map.entrySet())
			if(vertex.getValue().dist != Integer.MAX_VALUE && vertex.getValue().dist > maxDist)
			{
				maxDist = vertex.getValue().dist;
				vert = vertex.getKey();
			}
		System.out.println("MAX:"+vert+":"+maxDist);
	}
}
