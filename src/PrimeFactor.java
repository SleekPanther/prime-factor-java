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
		//int[] factors = pFact.getPrimeFactors();
		System.out.println( ( pFact.getPrimeFactors(1*2*3*4*5) ) );
	}
	
	public ArrayList<ArrayList<Integer>> getPrimeFactors(int num){
		// (hashset, return array, len=0>prime, 1 & num NOT prime,
		//actually 2d arraylist, do a contains, if(contains){arr.get(index).add(currentVal++), else{add, with 2nd col=1
		
		
		//new stuff
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
		
		
		ArrayList<ArrayList<Integer>> factors = new ArrayList<ArrayList<Integer>>();
		int howManyFactors = 0;
		for(int originalFactor: allFactors){
			factors.add(new ArrayList<Integer>());
			//factors.get(howManyFactors).add(999);
			factors.get(howManyFactors++).add(originalFactor);
		}
		return factors;
	}
}