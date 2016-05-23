import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

public class PrimeFactor {
	public static void main(String[] args) {
		PrimeFactor pFact = new PrimeFactor();
		//int[] factors = pFact.getPrimeFactors();
		System.out.println( ( pFact.getPrimeFactors(90) ) );
	}
	
	public ArrayList<Integer> getPrimeFactors(int num){
		// (hashset, return array, len=0>prime, 1 & num NOT prime,
		//actually 2d arraylist, do a contains, if(contains){arr.get(index).add(currentVal++), else{add, with 2nd col=1
		
//		TreeSet<Integer> factors = new TreeSet<Integer>();
//		factors.add(3);
//		factors.add(4);
//		factors.add(1);
//		factors.add(3);
//		System.out.println( factors );
		
		//new stuff
		ArrayList<Integer> factors = new ArrayList<Integer>();
		for(int divisor = 2; num>1; divisor++){
			for(; num%divisor ==0; num /=divisor){
				factors.add(divisor);		//add the prime divisor to list
			}
		}
		return factors;
				
//		int[] factorsArray = new int[5];
//		factorsArray[0] = 3;
//		return factorsArray;
	}
}