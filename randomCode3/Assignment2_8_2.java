
import java.util.*;

public class Assignment2_8_2 {
	
	  private static boolean check(int[] array, int x, int index) {
	        for (int i = 0; i < index && i < array.length; i++)
	            if (array[i] == x)
	                return true; 
	        return false;
	    }
		
	
	/*public static int[] generate(int n) {
        int[] perm = new int[n];
        Random rand = new Random();
        
        for (int i = 0; i < perm.length; i++) {
            int num = rand.nextInt(n) + 1;
            while (check(perm, num, i))
                num = rand.nextInt(n) + 1;
            perm[i] = num;
        }
        return perm;
    }*/
	
	public static int[] generate(int n) {
        int[] perm = new int[n];
        boolean[] used = new boolean[n + 1];
        Random rand = new Random();
        
        for (int i = 0; i < perm.length; i++) {
            int num = rand.nextInt(n) + 1;
            while (used[num])
               num = rand.nextInt(n) + 1;
            used[num] = true;
            perm[i] = num;
        }

        return perm;
    }
    
   
  
	
	public static void main(String args []) {
		
		Assignment2_8_2 obj = new Assignment2_8_2();
		
		System.out.println("Enter array size: ");
		
		Scanner scan = new Scanner(System.in);
		
		int size = scan.nextInt();
		
		int array[] = new int[size];
		
		boolean used[] = new boolean[size];
		
		for(int i  = 0; i < size; i++) {
			used[i] = false;
		}
		
		int low = 1;
		int high = size + 1;
		int ran;
		int a = 0;
		long sum = 0;
		
		Random r = new Random();
		while(a < 10) {
		
			long startTime = System.nanoTime();
		
			for(int i = 0; i < size; i++) {
			
				do {
					ran = r.nextInt(high-low)+low;
				
					if(used[ran-1] == false) {
						array[i] = ran;
						break;
					}
				}while(used[ran-1]==true);
			}
		
		a++;
		
			long endTime = System.nanoTime();
			
			System.out.printf("\nRuntime : " + String.format( "%12.10f",
					((endTime-startTime)/Math.pow(10,9))) + " seconds");
	
			sum = sum + ((endTime-startTime));	 
		
	}
		
	
		System.out.println();
	
		System.out.printf("\nAvg_Runtime : " + String.format( "%12.10f",
				((sum/a)/Math.pow(10,9))) + " seconds");
	
		System.out.println("\n");
		
	//for(int i = 0; i < size; i++) { System.out.println(array[i]);}
		}
	}


	
