/*
 * Complete the playGame(int players, int passes) method
 * Complete the addPlayers(int players) method
 * Complete the passPotatoe(int passes) method
 * No other methods/variables should be added/modified
 */
public class A3CircleLL {
	/*
	 * Grading:
	 * Correctly uses helpers to play game - 1pt
	 * Prints correct winner when game is complete - 0.5pt
	 */
	public void playGame(int players, int passes) {
		/*
		 * Use the helper methods addPlayers and passPotatoe to play the game
		 * Continue passing the potatoe until only 1 player remains
		 * Print the winning players number
		 * 
		 * For players = 5 and passes = 3, the winner should be 1. Players should be removed in this order:
		 * - 4, 3, 5, 2
		 */
		if (players < 1) return; // invalid
		addPlayers(players);
		while (count > 1) passPotatoe(passes);
		System.out.println(/*"Winner: "+*/start.value);
		
	}
	/*
	 * Grading:
	 * Correctly creates circular linked list of size amount - 1pt
	 */
	private void addPlayers(int amount) {
		/*
		 * Set up this method to create a Node for each player
		 * The value of each Node, should be the player number, starting at 1
		 * For example, if the amount is 5, there should be Nodes 1-5
		 * Node 1 should always be set as the start
		 * Make list circular by connecting the last player Node to the first
		 */
		Node prev = null;
		for (int i = 1; i <= amount; i++)
		{
			Node node = new Node(i);
			if (prev == null) start = node;
			else prev.next = node;
			prev = node;
		}
		prev.next = start;
		count = amount;
	}
	/*
	 * Grading:
	 * Correctly removes the player the number of passes away from the start - 1pt
	 * Correctly changes the start to the player after the one being removed - 0.5pt
	 */
	private void passPotatoe(int passes) {
		/*
		 * Set up this method to play a single round of the game
		 * Move through the list the number of passes from the start
		 * Remove the player/Node at this position
		 * Set the start equal to the player/Node after this position
		 * Do not play a round if there is one 1 player remaining
		 * Print the player number that was removed and the player with the potatoe
		 */
		if (count <= 1) return; // "do not play a round if there is 1 playerremaining"
		while (passes-- > 1) start = start.next;
		//System.out.println("Removing: "+start.next.value);
		start = start.next = start.next.next;
		count--;
	}

	private Node start;
	private int count;
	public A3CircleLL() {
		start = null;
		count = 0;
	}
	public String printList() {
		String output = "";
		if(start != null) {
			Node current = start;
			do {
				output += current.value + ",";
				current = current.next;
			}while(current != start);
		}
		return output;
	}
	public String toString() {
		return this.printList();
	}
	private class Node {
		Integer value;
		Node next;
		public Node(Integer v) {
			value = v;
			next = null;
		}
	}
}
