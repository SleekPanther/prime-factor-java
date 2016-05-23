import java.util.ArrayList;
import java.util.Arrays;

public class PrimeFactor {
	public static void main(String[] args) {
		PrimeFactor pFact = new PrimeFactor();
		//int[] factors = pFact.getPrimeFactors();
		System.out.println( ( pFact.getPrimeFactors(360) ) );
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
			int temp = allFactors.remove(i);		//get the thing from the old arrayList
			while(allFactors.contains(temp)){
				System.out.println("yo temp" + temp);
				allFactors.remove( allFactors.indexOf(temp) );
			}
			//System.out.println(temp);
			allFactors.add(i, temp);
		}
		
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