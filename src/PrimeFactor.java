import java.util.Arrays;
import java.util.TreeSet;

public class PrimeFactor {
	public static void main(String[] args) {
		PrimeFactor pFact = new PrimeFactor();
		//int[] factors = pFact.getPrimeFactors();
		System.out.println( Arrays.toString( pFact.getPrimeFactors() ) );
	}
	
	public int[] getPrimeFactors(){
		// (hashset, return array, len=2>prime, always add 1  & num to set, loop 2 to floor(sqrt(num), optional return set (comment out line), 
		TreeSet<Integer> factors = new TreeSet();
		factors.add(3);
		factors.add(4);
		factors.add(1);
		factors.add(3);
		//loop
		
		
		System.out.println( factors );
		
		int[] factorsArray = new int[5];
		factorsArray[0] = 3;
		return factorsArray;
	}
}