
public class A5Driver {

	public static void main(String[] args) throws Exception {
		//System.out.println((-5)%2);
		A5Cuckoo<Integer> hashTable = new A5Cuckoo<>(1);//force tables to start at size 2, total of 4 spaces
		hashTable.insert(0);
		hashTable.insert(1);
		System.out.println(hashTable);
		hashTable.insert(2);//should perform rehash because more than half full
		//You will need to continue to insert values that cause collisions to fully test your insert
		//depending on how you write the hashForTable1/hashForTable2 methods, you may need to try several different numbers to cause collisions
		System.out.println(hashTable);
		//if (true) return;
		A5Cuckoo<Integer> table = new A5Cuckoo(0);
		final int N = 10000000;
		System.out.printf("%d == %d\n",0,table.getUsedSpace());
		//System.out.printf("true %d\n",table.truecount());
		for (int i = 0; i < N; i++) 
		{
			table.insert(i);
			//if (i % 10 == 0) System.out.println(table);
		}
		System.out.printf("%d == %d\n",N,table.getUsedSpace());
		//System.out.printf("true %d\n",table.truecount());
		//System.out.println(table);
		for (int i = 0; i < N; i++)
		{
			if (!table.contains(i)) throw new Exception();
		}
		for (int i = 0; i < N; i++)
		{
			if (!table.remove(i)) throw new Exception();
		}
		System.out.printf("%d == %d\n",0,table.getUsedSpace());
		//System.out.printf("true %d\n",table.truecount());
		for (int i = 0; i < N; i++)
		{
			if (table.contains(i)) throw new Exception();
		}
		for (int i = 0; i < N; i++)
		{
			if (table.remove(i)) throw new Exception();
		}
		//System.out.println(table);
	}

}
