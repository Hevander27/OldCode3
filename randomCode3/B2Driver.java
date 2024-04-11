
public class B2Driver {

	public static void main(String[] args) throws Exception {
		B2Hopscotch<Integer> h1 = null;
		/*h1 = new B2Hopscotch<>(22,4);//should set size to 23
		System.out.println(h1);
		h1.insert(0);//failed to insert
		System.out.println(h1);
		h1 = new B2Hopscotch<>(22,4);
		h1.insert(0);
		h1.insert(23);//failed to look forward
		System.out.println(h1);
		h1 = new B2Hopscotch<>(22,4);
		h1.insert(0);
		h1.insert(23);
		h1.insert(46);
		h1.insert(3);
		h1.insert(69);//failed to single move
		System.out.println(h1);
		h1 = new B2Hopscotch<>(22,4);
		h1.insert(0);
		h1.insert(23);
		h1.insert(46);
		h1.insert(3);
		h1.insert(4);
		h1.insert(27);
		h1.insert(6);
		h1.insert(69);//failed to double move
		System.out.println(h1);
		h1 = new B2Hopscotch<>(22,4);
		h1.insert(0);
		h1.insert(23);
		h1.insert(46);
		h1.insert(3);
		h1.insert(4);
		h1.insert(27);
		h1.insert(6);
		h1.insert(50);
		h1.insert(29);
		h1.insert(52);
		h1.insert(69);//infinite recursion looking for space, rehash should occur
		System.out.println(h1);
		*/
		h1 = new B2Hopscotch();
		// N=10,000,000 and per=1 causes OutOfMemoryError
		final int N = 1000000; 
		final int per = 6;
		System.out.printf("%d == %d\n",0,h1.getUsedSpace());
		for (int i = 0; i < N; i++) 
		{
			for (int p = 0; p < per; p++) 
				h1.insert(i);
		}
		//System.out.println(h1);
		System.out.printf("%d == %d\n",N*per,h1.getUsedSpace());
		for (int i = 0; i < N; i++)
		{
			if (!h1.contains(i)) throw new Exception();
		}
		for (int i = 0; i < N; i++)
		{
			if (h1.get(i) != i) throw new Exception();
			for (int p = 0; p < per; p++) 
				if (!h1.remove(i)) throw new Exception();
		}
		//System.out.println(h1);
		System.out.printf("%d == %d\n",0,h1.getUsedSpace());
		for (int i = 0; i < N; i++)
		{
			if (h1.contains(i)) throw new Exception();
		}
		for (int i = 0; i < N; i++)
		{
			if (h1.remove(i)) throw new Exception();
		}
	}

}
