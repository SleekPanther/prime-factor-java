import java.util.ArrayList;

/*
 * Find prime factors of a number in 3 steps
 * 1) Find ALL prime factors
 * 2) Remove duplicated & increment the value in another list storing exponents for repeated numbers
 * 3) Copy the modified 2 arrayLists (all factors & corresponding exponents) to a new 2d ArrayList
 * Maybe not the most efficient way since it takes 3 passes, but it gives the desired result
 */
public class PrimeFactor {
	public static void main(String[] args) {
		PrimeFactor pFact = new PrimeFactor();
		System.out.println( ( pFact.getPrimeFactors(300) ) );
		
		//if len=0, then need try catch validation
		//if len = 1, then it's a prime (on separate line)
	}
	
	/**
	 * Get Prime factorization of a number when for multiplying it all out
	 * @param num	a number to get the prime factorization of
	 * @return a 2d arraylist. Uuter array represents a number as a 2-element arraylist, the 1st element is the actual number, the 2nd is the exponent it's raised to 
	 */
	public ArrayList<ArrayList<Integer>> getPrimeFactors(int num){
		ArrayList<Integer> allFactors = new ArrayList<Integer>();
		for(int divisor = 2; num>1; divisor++){
			for(; num%divisor == 0; num /=divisor){
				allFactors.add(divisor);		//add the prime divisor to list
			}
		}
		
		//remove duplicates & add to exponents array
		ArrayList<Integer> exponents = new ArrayList<Integer>();
		//for(int possibleRepeatedFactor : allFactors){
		for(int i = 0; i< allFactors.size(); i++){	//i is really the index for exponents
			exponents.add(i, 1);		//add a 1 @ the current index. Looping over allFactors, so all numbers when multiplied together must have 1 as their exponent @ the least, 2*2*3 is really [(2^1)*(2^1)*(3^1)] which will become (2^2)*(3^1)
			int temp = allFactors.remove(i);		//get the thing from the old arrayList
			while(allFactors.contains(temp)){
				System.out.println("yo temp" + temp);
				allFactors.remove( allFactors.indexOf(temp) );
				exponents.set(i,  (exponents.get(i)+1)  );
			}
			//System.out.println(temp);
			allFactors.add(i, temp);
		}
		System.out.println("exponnt  " + exponents);
		//allFactors is now modified & duplicates are removed
		
		ArrayList<ArrayList<Integer>> factors = new ArrayList<ArrayList<Integer>>();		//2d list holds the numbers as 1d 2-element arraylists with the 1st element representing the actual number & the 2nd the exponent it's raised to  
		for(int i = 0; i < allFactors.size(); i++){			//loop over allFactors (could loop over exponents since they're the same size now
			factors.add(new ArrayList<Integer>());		//add a black arraylist to the inner arraylist
			factors.get(i).add(allFactors.get(i));			//add the number
			factors.get(i).add(exponents.get(i));			//add the exponent
		}
		return factors;
	}
}